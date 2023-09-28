package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageButtons {
    protected WebDriver chromeDriver;
    protected WebElement doubleClickMe;
    protected WebElement doubleClickMessage;
    protected WebElement rightClickMe;
    protected WebElement rightClickMessage;
    protected WebElement clickMe;
    protected WebElement dynamicClickMessage;

    public PageButtons(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.doubleClickMe = chromeDriver.findElement(By.id("doubleClickBtn"));
        this.rightClickMe = chromeDriver.findElement(By.xpath("//button[@id='rightClickBtn']"));
        this.clickMe = chromeDriver.findElement(By.xpath("//button[text()='Click Me']"));
    }

    public void doubleClick() {
        Actions act = new Actions(chromeDriver);
        act.moveToElement(doubleClickMe).doubleClick(doubleClickMe).perform();
    }

    public void rightClick() {
        Actions act = new Actions(chromeDriver);
        act.moveToElement(rightClickMe).contextClick(rightClickMe).perform();
    }

    public void clickMe() {
        Actions act = new Actions(chromeDriver);
        act.moveToElement(clickMe).click(clickMe).perform();
    }


    public String getDoubleClickMessage() {
        doubleClickMessage = chromeDriver.findElement(By.id("doubleClickMessage"));
        return doubleClickMessage.getText();
    }

    public String getRightClickMessage() {
        rightClickMessage = chromeDriver.findElement(By.id("rightClickMessage"));
        return rightClickMessage.getText();
    }

    public String getDynamicClickMessage() {
        dynamicClickMessage = chromeDriver.findElement(By.id("dynamicClickMessage"));
        return dynamicClickMessage.getText();
    }
}
