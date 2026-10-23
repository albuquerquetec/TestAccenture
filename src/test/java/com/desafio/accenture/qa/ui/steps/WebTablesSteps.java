package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.WebTablesPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTablesSteps {

    private WebTablesPage page;

    @E("cria um novo registro")
    public void criaUmNovoRegistro() {
        page().createRecord();
    }

    @E("edita o novo registro criado")
    public void editaONovoRegistroCriado() {
        page().editLastCreatedRecord();
    }

    @Entao("o registro editado deve estar visivel")
    public void oRegistroEditadoDeveEstarVisivel() {
        assertThat(page().isEditedRecordVisible()).isTrue();
    }

    @E("o novo registro e deletado")
    public void oNovoRegistroEDeletado() {
        String email = page().getCreatedEmails().get(0);
        page().deleteLastCreatedRecord();
        assertThat(page().recordExists(email)).isFalse();
    }

    @E("{int} novos registros sao criados de forma dinamica")
    public void novosRegistrosSaoCriadosDeFormaDinamica(Integer quantity) {
        page().createRecords(quantity);
        assertThat(page().getCreatedEmails()).hasSize(quantity);
    }

    @Entao("todos os novos registros criados sao deletados")
    public void todosOsNovosRegistrosCriadosSaoDeletados() {
        page().deleteCreatedRecords();
        assertThat(page().noneOfCreatedRecordsExist()).isTrue();
    }

    private WebTablesPage page() {
        if (page == null) {
            page = new WebTablesPage();
        }
        return page;
    }
}
