package com.desafio.accenture.qa.ui.pages;

import com.desafio.accenture.qa.support.RandomData;
import com.desafio.accenture.qa.support.ResourceFiles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PracticeFormPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "userNumber")
    private WebElement mobile;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;

    @FindBy(id = "subjectsInput")
    private WebElement subjects;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPicture;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(id = "submit")
    private WebElement submit;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    @FindBy(id = "closeLargeModal")
    private WebElement closeModal;

    public void fillRandomForm() {
        String name = RandomData.firstName();
        type(firstName, name);
        type(lastName, RandomData.lastName());
        type(email, RandomData.email(name));
        click(By.xpath("//label[text()='Male']"));
        type(mobile, RandomData.phone());

        replaceText(dateOfBirth, "15 Oct 1992");
        dateOfBirth.sendKeys(Keys.ENTER);

        subjects.sendKeys("Maths");
        subjects.sendKeys(Keys.ENTER);

        click(By.xpath("//label[text()='Sports']"));
        click(By.xpath("//label[text()='Reading']"));

        type(currentAddress, RandomData.address());

        int stateIndex = RandomData.stateIndex();
        selectReactOption("state", RandomData.state(stateIndex));
        selectReactOption("city", RandomData.city(stateIndex));
    }

    public void uploadTxtFile() {
        uploadPicture.sendKeys(ResourceFiles.absolutePath("upload/sample.txt"));
    }

    public void submitForm() {
        click(submit);
    }

    public boolean isSuccessPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(modalTitle));
        return modalTitle.getText().contains("Thanks for submitting the form");
    }

    public void closePopup() {
        removeAds();
        js.executeScript("var m=document.querySelector('.modal-body'); if(m){m.scrollTop=m.scrollHeight;}");
        jsClick(closeModal);
        if (isDisplayed(By.id("example-modal-sizes-title-lg"))) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        }
        if (isDisplayed(By.id("example-modal-sizes-title-lg"))) {
            js.executeScript(
                    "document.querySelectorAll('.modal.show,.modal-backdrop').forEach(function(el){el.remove();});"
                            + "document.body.classList.remove('modal-open');"
                            + "document.body.style.overflow='auto';"
            );
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
    }

    private void selectReactOption(String containerId, String value) {
        click(By.id(containerId));
        WebElement input = waitVisible(By.cssSelector("#" + containerId + " input"));
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }
}
