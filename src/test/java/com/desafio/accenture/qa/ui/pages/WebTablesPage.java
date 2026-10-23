package com.desafio.accenture.qa.ui.pages;

import com.desafio.accenture.qa.support.RandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebTablesPage extends BasePage {

    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "age")
    private WebElement age;

    @FindBy(id = "salary")
    private WebElement salary;

    @FindBy(id = "department")
    private WebElement department;

    @FindBy(id = "submit")
    private WebElement submit;

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    private String lastCreatedEmail;
    private String lastEditedDepartment;
    private final List<String> createdEmails = new ArrayList<>();
    private final List<String> lastDeletedEmails = new ArrayList<>();

    public String createRecord() {
        clearSearch();
        RegistrationData data = randomRegistration();
        fillAndSubmit(data);
        lastCreatedEmail = data.email;
        createdEmails.add(data.email);
        filterBy(data.email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cellByText(data.email)));
        clearSearch();
        return data.email;
    }

    public List<String> createRecords(int quantity) {
        ensurePageSize(50);
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            emails.add(createRecord());
        }
        return emails;
    }

    public void editLastCreatedRecord() {
        filterBy(lastCreatedEmail);
        click(By.xpath("//tr[.//td[normalize-space()='" + lastCreatedEmail + "']]//span[@title='Edit']"));
        wait.until(ExpectedConditions.visibilityOf(department));
        lastEditedDepartment = "Quality" + RandomData.suffixSafe();
        fillField(department, lastEditedDepartment);
        jsClick(submit);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal.show")));
        filterBy(lastCreatedEmail);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cellByText(lastEditedDepartment)));
        clearSearch();
    }

    public boolean isEditedRecordVisible() {
        filterBy(lastCreatedEmail);
        boolean visible = isDisplayed(cellByText(lastEditedDepartment));
        clearSearch();
        return visible;
    }

    public void deleteLastCreatedRecord() {
        deleteByEmail(lastCreatedEmail);
        createdEmails.remove(lastCreatedEmail);
    }

    public void deleteCreatedRecords() {
        clearSearch();
        lastDeletedEmails.clear();
        lastDeletedEmails.addAll(createdEmails);
        for (String email : lastDeletedEmails) {
            List<WebElement> buttons = driver.findElements(
                    By.xpath("//tr[.//td[normalize-space()='" + email + "']]//span[@title='Delete']")
            );
            if (buttons.isEmpty()) {
                continue;
            }
            jsClick(buttons.get(0));
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(cellByText(email)));
        }
        createdEmails.clear();
        clearSearch();
    }

    public boolean recordExists(String email) {
        filterBy(email);
        boolean exists = !driver.findElements(cellByText(email)).isEmpty();
        clearSearch();
        return exists;
    }

    public boolean noneOfCreatedRecordsExist() {
        return lastDeletedEmails.stream().noneMatch(this::recordExists);
    }

    public List<String> getCreatedEmails() {
        return createdEmails;
    }

    private void deleteByEmail(String email) {
        clearSearch();
        By deleteButton = By.xpath("//tr[.//td[normalize-space()='" + email + "']]//span[@title='Delete']");
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(deleteButton));
        jsClick(button);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(cellByText(email)));
    }

    private void fillAndSubmit(RegistrationData data) {
        removeAds();
        click(addButton);
        wait.until(ExpectedConditions.visibilityOf(firstName));
        fillField(firstName, data.firstName);
        fillField(lastName, data.lastName);
        fillField(userEmail, data.email);
        fillField(age, String.valueOf(data.age));
        fillField(salary, String.valueOf(data.salary));
        fillField(department, data.department);
        jsClick(submit);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal.show")));
    }

    private void fillField(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    private void filterBy(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        setInputValue(searchBox, text);
    }

    private void clearSearch() {
        if (!searchBox.isDisplayed()) {
            return;
        }
        String currentValue = String.valueOf(js.executeScript("return arguments[0].value;", searchBox));
        if (!currentValue.isBlank()) {
            setInputValue(searchBox, "");
        }
    }

    private void setInputValue(WebElement input, String value) {
        js.executeScript(
                "const input = arguments[0];"
                        + "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
                        + "setter.call(input, arguments[1]);"
                        + "input.dispatchEvent(new Event('input', {bubbles: true}));"
                        + "input.dispatchEvent(new Event('change', {bubbles: true}));",
                input,
                value
        );
    }

    private void ensurePageSize(int size) {
        String value = String.valueOf(size);
        List<WebElement> selects = driver.findElements(By.cssSelector("select"));
        for (int index = selects.size() - 1; index >= 0; index--) {
            Select select = new Select(selects.get(index));
            boolean hasValue = select.getOptions().stream().anyMatch(option -> value.equals(option.getAttribute("value")));
            if (hasValue) {
                select.selectByValue(value);
                return;
            }
        }
    }

    private By cellByText(String text) {
        return By.xpath("//table//td[normalize-space()='" + text + "']");
    }

    private RegistrationData randomRegistration() {
        String name = RandomData.firstName();
        return new RegistrationData(
                name,
                RandomData.lastName(),
                RandomData.email(name),
                RandomData.age(),
                RandomData.salary(),
                RandomData.department()
        );
    }

    private static final class RegistrationData {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final int age;
        private final int salary;
        private final String department;

        private RegistrationData(String firstName, String lastName, String email, int age, int salary, String department) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }
    }
}
