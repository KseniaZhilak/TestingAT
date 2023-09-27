package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageLinks {
    protected WebDriver chromeDriver;
    protected WebElement createdLink;
    protected WebElement noContentLink;
    protected WebElement movedLink;
    protected WebElement badRequestLink;
    protected WebElement unauthorizedLink;
    protected WebElement forbiddenLink;
    protected WebElement notFoundLink;


    public PageLinks(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.createdLink = chromeDriver.findElement(By.id("created"));
        this.noContentLink = chromeDriver.findElement(By.id("no-content"));
        this.movedLink = chromeDriver.findElement(By.id("moved"));
        this.badRequestLink = chromeDriver.findElement(By.id("bad-request"));
        this.unauthorizedLink = chromeDriver.findElement(By.id("unauthorized"));
        this.forbiddenLink = chromeDriver.findElement(By.id("forbidden"));
        this.notFoundLink = chromeDriver.findElement(By.id("invalid-url"));
    }

    public void clickCreatedLink() {
        createdLink.click();
    }

    public void clickNoContentLink() {
        noContentLink.click();
    }

    public void clickMovedLink() {
        movedLink.click();
    }

    public WebElement getLinkResponse() {
        return chromeDriver.findElement(By.id("linkResponse"));
    }
}



