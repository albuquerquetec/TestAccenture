package com.desafio.accenture.qa.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage extends BasePage {

    private static final long STOP_DEADLINE_MILLIS = 4_000;

    @FindBy(id = "startStopButton")
    private WebElement startStopButton;

    @FindBy(id = "resetButton")
    private WebElement resetButton;

    @FindBy(css = "#progressBar .progress-bar")
    private WebElement progressBar;

    public void startAndStopBefore25Percent() {
        click(startStopButton);
        long deadline = System.currentTimeMillis() + STOP_DEADLINE_MILLIS;
        int value = 0;
        while (System.currentTimeMillis() < deadline) {
            value = currentPercent();
            if (value >= 1) {
                click(startStopButton);
                value = currentPercent();
                if (value > 25) {
                    throw new AssertionError("Progress bar passou de 25%. Valor atual: " + value + "%");
                }
                return;
            }
        }
        throw new AssertionError("Progress bar nao avancou dentro do tempo limite. Valor atual: " + value + "%");
    }

    public int currentPercent() {
        String aria = progressBar.getAttribute("aria-valuenow");
        if (aria != null && !aria.isBlank()) {
            return Integer.parseInt(aria.trim());
        }
        String text = progressBar.getText().replace("%", "").trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    public void startAndWaitUntilComplete() {
        if (currentPercent() < 100 && !isStopVisible()) {
            click(startStopButton);
        }
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ignored -> currentPercent() >= 100);
    }

    public void reset() {
        click(wait.until(ExpectedConditions.elementToBeClickable(resetButton)));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ignored -> currentPercent() == 0);
    }

    private boolean isStopVisible() {
        return "Stop".equalsIgnoreCase(startStopButton.getText());
    }
}
