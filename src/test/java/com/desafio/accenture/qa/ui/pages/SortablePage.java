package com.desafio.accenture.qa.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortablePage extends BasePage {

    private static final List<String> ASCENDING = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
    private static final int MAX_SORT_ATTEMPTS = 8;
    private static final long SORT_DEADLINE_MILLIS = 25_000;

    @FindBy(id = "demo-tab-list")
    private WebElement listTab;

    public void sortListAscending() {
        click(listTab);
        removeAds();
        long deadline = System.currentTimeMillis() + SORT_DEADLINE_MILLIS;

        if (isSorted()) {
            dragItemBefore("Six", "One");
            if (isSorted()) {
                throw new IllegalStateException("Drag and drop nao moveu o item Six.");
            }
        }

        int attempts = 0;
        while (!isSorted()) {
            if (attempts++ >= MAX_SORT_ATTEMPTS || System.currentTimeMillis() > deadline) {
                throw new IllegalStateException("Nao foi possivel ordenar a lista. Ordem atual: " + currentOrder());
            }
            List<String> order = currentOrder();
            for (int targetIndex = 0; targetIndex < ASCENDING.size(); targetIndex++) {
                String expected = ASCENDING.get(targetIndex);
                int currentIndex = order.indexOf(expected);
                if (currentIndex != targetIndex) {
                    dragItemBefore(expected, order.get(targetIndex));
                    break;
                }
            }
        }
    }

    public List<String> currentOrder() {
        return items().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> expectedAscendingOrder() {
        return ASCENDING;
    }

    private boolean isSorted() {
        return ASCENDING.equals(currentOrder());
    }

    private void dragItemBefore(String sourceText, String targetText) {
        WebElement source = item(sourceText);
        WebElement target = item(targetText);
        scrollIntoView(source);
        js.executeScript(
                "const dataTransfer = new DataTransfer();"
                        + "const fire = (element, type) => element.dispatchEvent(new DragEvent(type, {bubbles: true, cancelable: true, dataTransfer: dataTransfer}));"
                        + "fire(arguments[0], 'dragstart');"
                        + "fire(arguments[1], 'dragenter');"
                        + "fire(arguments[1], 'dragover');"
                        + "fire(arguments[1], 'drop');"
                        + "fire(arguments[0], 'dragend');",
                source,
                target
        );
    }

    private WebElement item(String text) {
        return waitVisible(By.xpath(
                "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and normalize-space()='" + text + "']"
        ));
    }

    private List<WebElement> items() {
        return driver.findElements(By.cssSelector("#demo-tabpane-list .list-group-item"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
    }
}
