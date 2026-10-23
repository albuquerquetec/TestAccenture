package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.SortablePage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import static org.assertj.core.api.Assertions.assertThat;

public class SortableSteps {

    private SortablePage page;

    @E("ordena os elementos da lista em ordem crescente")
    public void ordenaOsElementosDaListaEmOrdemCrescente() {
        page().sortListAscending();
    }

    @Entao("os elementos devem estar na ordem crescente")
    public void osElementosDevemEstarNaOrdemCrescente() {
        assertThat(page().currentOrder()).containsExactlyElementsOf(page().expectedAscendingOrder());
    }

    private SortablePage page() {
        if (page == null) {
            page = new SortablePage();
        }
        return page;
    }
}
