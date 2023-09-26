package com.demoqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.PageTextBox;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class Tests extends BaseTest{

    @Test
    public void testTextBox() {
        chromeDriver.get("https://demoqa.com/text-box");
        PageTextBox textBoxes = new PageTextBox(chromeDriver);
        textBoxes.setFieldFullName("Ксения");
        textBoxes.setFieldEmail("kzhilak@bk.ru");
        textBoxes.setAreaCurrentAddress("Клин");
        textBoxes.setAreaPermanentAddress("Клин");
        textBoxes.clickButtonSubmit();

        Map<String,String> result = textBoxes.getResultsMap();
        assertEquals("Ксения", result.get("Name"));
        assertEquals("kzhilak@bk.ru", result.get("Email"));
        assertEquals("Клин", result.get("Current Address"));
        assertEquals("Клин", result.get("Permananet Address"));

    }
}
