package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.BrowserWindowsPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import static org.assertj.core.api.Assertions.assertThat;

public class BrowserWindowsSteps {

    private BrowserWindowsPage page;

    @E("clica no botao New Window")
    public void clicaNoBotaoNewWindow() {
        page().openNewWindow();
    }

    @Entao("uma nova janela deve ser aberta com a mensagem {string}")
    public void umaNovaJanelaDeveSerAbertaComAMensagem(String message) {
        assertThat(page().newWindowMessage()).isEqualTo(message);
    }

    @E("a nova janela e fechada")
    public void aNovaJanelaEFechada() {
        page().closeNewWindow();
        assertThat(page().isOriginalWindowActive()).isTrue();
    }

    private BrowserWindowsPage page() {
        if (page == null) {
            page = new BrowserWindowsPage();
        }
        return page;
    }
}
