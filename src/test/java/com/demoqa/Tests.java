package com.demoqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.PageLinks;
import pageObject.PageTextBox;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests extends BaseTest {
    @Test
    public void testTextBox() {
        chromeDriver.get("https://demoqa.com/text-box");
        PageTextBox textBoxes = new PageTextBox(chromeDriver);
        textBoxes.setFieldFullName("Ксения");
        textBoxes.setFieldEmail("kzhilak@bk.ru");
        textBoxes.setAreaCurrentAddress("Клин");
        textBoxes.setAreaPermanentAddress("Клин");
        textBoxes.clickButtonSubmit();

        Map<String, String> result = textBoxes.getResultsMap();
        assertEquals("Ксения", result.get("Name"));
        assertEquals("kzhilak@bk.ru", result.get("Email"));
        assertEquals("Клин", result.get("Current Address"));
        assertEquals("Клин", result.get("Permananet Address"));
    }

    @Test
    public void testLinks() {
        chromeDriver.get("https://demoqa.com/links");
        PageLinks pageLinks = new PageLinks(chromeDriver);

        pageLinks.clickCreatedLink();
        String actualResponse = pageLinks.getLinkResponse().getText();
        assertEquals("Link has responded with staus 201 and status text Created", actualResponse);

        pageLinks.clickNoContentLink();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(pageLinks.getLinkResponse(), actualResponse)));
        actualResponse = pageLinks.getLinkResponse().getText();

        Pattern pattern = Pattern.compile("^.+(?<statusCode>\\d{3}) and status text (?<statusText>.+)$");
        Matcher matcher = pattern.matcher(actualResponse);
        assertTrue(matcher.find(), "Ответ не соответствует паттерну");
        assertEquals("204", matcher.group("statusCode"));
        assertEquals("No Content", matcher.group("statusText"));

        pageLinks.clickMovedLink();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(pageLinks.getLinkResponse(), actualResponse)));
        actualResponse = pageLinks.getLinkResponse().getText();
        assertTrue(actualResponse.matches("^.+301.+Moved Permanently$"));
    }
}



