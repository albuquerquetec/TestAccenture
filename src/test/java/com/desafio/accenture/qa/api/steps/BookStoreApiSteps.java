package com.desafio.accenture.qa.api.steps;

import com.desafio.accenture.qa.api.client.AccountClient;
import com.desafio.accenture.qa.api.client.BookStoreClient;
import com.desafio.accenture.qa.api.context.ApiScenarioContext;
import com.desafio.accenture.qa.api.models.AddBooksRequest;
import com.desafio.accenture.qa.api.models.IsbnItem;
import com.desafio.accenture.qa.api.models.UserRequest;
import com.desafio.accenture.qa.support.RandomData;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BookStoreApiSteps {

    private final ApiScenarioContext context;
    private final AccountClient accountClient = new AccountClient();
    private final BookStoreClient bookStoreClient = new BookStoreClient();

    public BookStoreApiSteps(ApiScenarioContext context) {
        this.context = context;
    }

    @Dado("que um usuario e criado")
    public void queUmUsuarioECriado() {
        context.setUserName(RandomData.uniqueUserName());
        context.setPassword(RandomData.strongPassword());

        Response response = accountClient.createUser(credentials());
        assertThat(response.statusCode()).isEqualTo(201);
        context.setUserId(response.jsonPath().getString("userID"));
        assertThat(context.getUserId()).isNotBlank();
    }

    @Quando("um token de acesso e gerado")
    public void umTokenDeAcessoEGerado() {
        Response response = accountClient.generateToken(credentials());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("status")).isEqualTo("Success");
        context.setToken(response.jsonPath().getString("token"));
        assertThat(context.getToken()).isNotBlank();
    }

    @E("o usuario e confirmado como autorizado")
    public void oUsuarioEConfirmadoComoAutorizado() {
        Response response = accountClient.isAuthorized(credentials());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).containsIgnoringCase("true");
    }

    @E("os livros disponiveis sao listados")
    public void osLivrosDisponiveisSaoListados() {
        Response response = bookStoreClient.listBooks();
        assertThat(response.statusCode()).isEqualTo(200);
        List<String> isbns = response.jsonPath().getList("books.isbn");
        assertThat(isbns).hasSizeGreaterThanOrEqualTo(2);
        context.getAvailableIsbns().addAll(isbns);
    }

    @E("dois livros sao alugados")
    public void doisLivrosSaoAlugados() {
        List<String> chosen = context.getAvailableIsbns().subList(0, 2);
        context.getRentedIsbns().addAll(chosen);

        List<IsbnItem> collection = chosen.stream()
                .map(IsbnItem::new)
                .collect(Collectors.toList());

        Response response = bookStoreClient.addBooks(
                new AddBooksRequest(context.getUserId(), collection),
                context.getToken()
        );
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @Entao("os detalhes do usuario devem conter os livros reservados")
    public void osDetalhesDoUsuarioDevemConterOsLivrosReservados() {
        Response response = accountClient.getUser(context.getUserId(), context.getToken());
        assertThat(response.statusCode()).isEqualTo(200);
        String returnedUserId = response.jsonPath().getString("userId");
        if (returnedUserId == null) {
            returnedUserId = response.jsonPath().getString("userID");
        }
        assertThat(returnedUserId).isEqualTo(context.getUserId());
        List<String> userBooks = response.jsonPath().getList("books.isbn");
        assertThat(userBooks).containsExactlyInAnyOrderElementsOf(context.getRentedIsbns());
    }

    private UserRequest credentials() {
        return new UserRequest(context.getUserName(), context.getPassword());
    }
}
