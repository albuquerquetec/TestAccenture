package com.desafio.accenture.qa.ui.steps;

import com.desafio.accenture.qa.ui.pages.ProgressBarPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgressBarSteps {

    private ProgressBarPage page;

    @E("inicia a progress bar e para antes dos 25%")
    public void iniciaAProgressBarEParaAntesDos25() {
        page().startAndStopBefore25Percent();
    }

    @Entao("o valor da progress bar deve ser menor ou igual a 25%")
    public void oValorDaProgressBarDeveSerMenorOuIgualA25() {
        assertThat(page().currentPercent()).isBetween(1, 25);
    }

    @Quando("inicia novamente e aguarda 100%")
    public void iniciaNovamenteEAguarda100() {
        page().startAndWaitUntilComplete();
        assertThat(page().currentPercent()).isEqualTo(100);
    }

    @E("reseta a progress bar")
    public void resetaAProgressBar() {
        page().reset();
    }

    @Entao("a progress bar deve voltar ao inicio")
    public void aProgressBarDeveVoltarAoInicio() {
        assertThat(page().currentPercent()).isEqualTo(0);
    }

    private ProgressBarPage page() {
        if (page == null) {
            page = new ProgressBarPage();
        }
        return page;
    }
}
