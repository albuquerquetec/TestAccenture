package com.desafio.accenture.qa.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserWindowsPage extends BasePage {

    @FindBy(id = "windowButton")
    private WebElement newWindowButton;

    @FindBy(id = "sampleHeading")
    private WebElement sampleHeading;

    private String originalWindow;

    public void openNewWindow() {
        originalWindow = driver.getWindowHandle();
        click(newWindowButton);
        switchToNewWindow(originalWindow);
    }

    public String newWindowMessage() {
        wait.until(ExpectedConditions.visibilityOf(sampleHeading));
        return sampleHeading.getText();
    }

    public void closeNewWindow() {
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    public boolean isOriginalWindowActive() {
        return driver.getWindowHandle().equals(originalWindow) && driver.getWindowHandles().size() == 1;
    }
}
