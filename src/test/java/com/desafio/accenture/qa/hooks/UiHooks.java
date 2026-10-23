package com.desafio.accenture.qa.hooks;

import com.desafio.accenture.qa.ui.driver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class UiHooks {

    @Before("@ui")
    public void startBrowser() {
        WebDriverFactory.createDriver();
    }

    @After("@ui")
    public void stopBrowser(Scenario scenario) {
        if (scenario.isFailed() && WebDriverFactory.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        WebDriverFactory.quitDriver();
    }
}
