package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.PracticeFormPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormSteps {

    private PracticeFormPage page;

    @E("preenche todo o formulario com valores aleatorios")
    public void preencheTodoOFormularioComValoresAleatorios() {
        page().fillRandomForm();
    }

    @E("faz upload de um arquivo txt")
    public void fazUploadDeUmArquivoTxt() {
        page().uploadTxtFile();
    }

    @E("submete o formulario")
    public void submeteOFormulario() {
        page().submitForm();
    }

    @Entao("um popup deve ser exibido apos o submit")
    public void umPopupDeveSerExibidoAposOSubmit() {
        assertThat(page().isSuccessPopupDisplayed()).isTrue();
    }

    @E("o popup e fechado")
    public void oPopupEFechado() {
        page().closePopup();
    }

    private PracticeFormPage page() {
        if (page == null) {
            page = new PracticeFormPage();
        }
        return page;
    }
}
