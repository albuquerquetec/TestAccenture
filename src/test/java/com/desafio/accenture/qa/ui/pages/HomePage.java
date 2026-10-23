package com.desafio.accenture.qa.ui.pages;

import com.desafio.accenture.qa.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage open() {
        driver.get(TestConfig.UI_BASE_URL);
        removeAds();
        return this;
    }

    public void chooseCard(String cardName) {
        removeAds();
        WebElement card = waitVisible(By.xpath("//h5[text()='" + cardName + "']"));
        click(card);
        removeAds();
    }

    public void openSubmenu(String submenuName) {
        removeAds();
        WebElement item = waitVisible(By.xpath("//span[text()='" + submenuName + "']"));
        click(item);
        removeAds();
    }
}
