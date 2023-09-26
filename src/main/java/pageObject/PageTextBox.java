package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageTextBox {
    protected WebDriver chromeDriver;
    protected WebElement fieldFullName;
    protected WebElement fieldEmail;
    protected WebElement areaCurrentAddress;
    protected WebElement areaPermanentAddress;
    protected WebElement buttonSubmit;

    public PageTextBox(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.fieldFullName = chromeDriver.findElement(By.id("userName"));
        this.fieldEmail = chromeDriver.findElement(By.id("userEmail"));
        this.areaCurrentAddress = chromeDriver.findElement(By.id("currentAddress"));
        this.areaPermanentAddress = chromeDriver.findElement(By.id("permanentAddress"));
        this.buttonSubmit = chromeDriver.findElement(By.id("submit"));
    }

    public void setFieldFullName(String word){
        fieldFullName.click();
        fieldFullName.sendKeys(word);
    }
    public void setFieldEmail(String word){
        fieldEmail.click();
        fieldEmail.sendKeys(word);
    }
    public void setAreaCurrentAddress(String word){
        areaCurrentAddress.click();
        areaCurrentAddress.sendKeys(word);
    }
    public void setAreaPermanentAddress(String word){
        areaPermanentAddress.click();
        areaPermanentAddress.sendKeys(word);
    }
    public void clickButtonSubmit(){
        buttonSubmit.click();
    }

    public Map<String,String> getResultsMap() {
        Map<String,String> result = new HashMap<>();
        chromeDriver.findElements(By.xpath("//*[@id='output']//p")).stream()
                .map(WebElement::getText)
                .forEach(s -> result.put(s.split(":")[0].trim(),s.split(":")[1].trim()));

        return result;
    }
}
