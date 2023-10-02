package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageDynamicProperties {
    protected WebDriver chromeDriver;
    protected WebElement textRandomId;
    protected WebElement buttonEnableAfter;
    protected WebElement buttonColorChange;
    protected WebElement buttonVisibleAfter;

    public PageDynamicProperties(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.textRandomId = chromeDriver.findElement(By.xpath("//div[./button]/p"));
        this.buttonEnableAfter = chromeDriver.findElement(By.id("enableAfter"));
        this.buttonColorChange = chromeDriver.findElement(By.id("colorChange"));
    }


    public String getTextRandomId() {
        return textRandomId.getText();
    }

    public WebElement getButtonEnableAfter() {
        return buttonEnableAfter;
    }

    public WebElement getButtonColorChange() {
        return buttonColorChange;
    }

    public WebElement getButtonVisibleAfter() {
        buttonVisibleAfter = chromeDriver.findElement(By.id("visibleAfter"));
        return buttonVisibleAfter;
    }
}
