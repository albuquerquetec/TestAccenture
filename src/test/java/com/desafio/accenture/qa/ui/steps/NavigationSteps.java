package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.HomePage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

public class NavigationSteps {

    @Dado("que o usuario acessa o site DemoQA")
    public void queOUsuarioAcessaOSiteDemoQA() {
        new HomePage().open();
    }

    @Quando("escolhe a opcao {string} na pagina inicial")
    public void escolheAOpcaoNaPaginaInicial(String cardName) {
        new HomePage().chooseCard(cardName);
    }

    @E("clica no submenu {string}")
    public void clicaNoSubmenu(String submenuName) {
        new HomePage().openSubmenu(submenuName);
    }
}
