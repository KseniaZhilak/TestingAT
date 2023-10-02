package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageWebTables {
    protected WebDriver chromeDriver;
    protected WebElement addButton;
    protected List<WebElement> listHead;
    protected List<WebElement> listRows;
    protected List<Map<String, String>> collectHeadersRows = new ArrayList<>();

    public PageWebTables(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.addButton = chromeDriver.findElement(By.id("addNewRecordButton"));
        this.listHead = chromeDriver.findElements(
                By.xpath("//div[@class='rt-table']//div[@role='columnheader']"));
        this.listRows = chromeDriver.findElements(
                By.xpath("//div[@class='rt-tbody']//div[@role='row']")
        );
    }

    public void clickButtonAdd() {
        addButton.click();
    }

    public List<Map<String, String>> collectHeadersRows() {
        this.listRows = chromeDriver.findElements(
                By.xpath("//div[@class='rt-tbody']//div[@role='row']"));

        for (int i = 0; i < listRows.size(); i++) {
            // Для каждой строки временная Map
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < listHead.size(); j++) {
                collectRow.put(
                        listHead.get(j).getText(),
                        listRows.get(i).findElement(
                                By.xpath("./*[@role='gridcell'][" + (j + 1) + "]")).getText()
                );
            }
            collectHeadersRows.add(collectRow);
        }
        return collectHeadersRows;
    }

    public class RegistrationForm {
        protected WebElement registrationForm;
        protected WebElement firstName;
        protected WebElement lastName;
        protected WebElement email;
        protected WebElement age;
        protected WebElement salary;
        protected WebElement department;
        protected WebElement submitBtn;

        public RegistrationForm() {
            this.registrationForm = chromeDriver.findElement(
                    By.xpath("//div[@role='document']"));
            this.firstName = chromeDriver.findElement(By.id("firstName"));
            this.lastName = chromeDriver.findElement(By.id("lastName"));
            this.email = chromeDriver.findElement(By.id("userEmail"));
            this.age = chromeDriver.findElement(By.id("age"));
            this.salary = chromeDriver.findElement(By.id("salary"));
            this.department = chromeDriver.findElement(By.id("department"));
            this.submitBtn = chromeDriver.findElement(By.id("submit"));
        }

        public void setFieldFirstName(String word) {
            firstName.click();
            firstName.sendKeys(word);
        }

        public void setFieldLastName(String word) {
            lastName.click();
            lastName.sendKeys(word);
        }

        public void setFieldEmail(String word) {
            email.click();
            email.sendKeys(word);
        }

        public void setFieldAge(String word) {
            age.click();
            age.sendKeys(word);
        }

        public void setFieldSalary(int word) {
            salary.click();
            salary.sendKeys(String.valueOf(word));
        }

        public void setFieldDepartment(String word) {
            department.click();
            department.sendKeys(word);
        }

        public void clickButtonSubmit() {
            submitBtn.click();
        }
    }
}


