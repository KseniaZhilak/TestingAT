package com.demoqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testLinksNewTab() throws InterruptedException {
        chromeDriver.get("https://demoqa.com/links");
        PageLinks pageLinks = new PageLinks(chromeDriver);
        String currentWindow = chromeDriver.getWindowHandle();
        pageLinks.clickHome();

        chromeDriver.getWindowHandles().stream()
                .filter(Predicate.not(currentWindow::equals)) // .filter(e -> !currentWindow.equals(e)))
                .findFirst()
                .ifPresent(handle -> chromeDriver.switchTo().window(handle));

        assertTrue(chromeDriver.getCurrentUrl().endsWith("demoqa.com/"),
                "В Url " + chromeDriver.getCurrentUrl() + " не содержится ссылка 'demoqa.com'");
    }

    @Test
    public void testButtons() {
        chromeDriver.get("https://demoqa.com/buttons");
        PageButtons pageButtons = new PageButtons(chromeDriver);

        pageButtons.doubleClick();
        assertEquals("You have done a double click", pageButtons.getDoubleClickMessage(),
                "Отсутствует текст '" + pageButtons.getDoubleClickMessage() + "'после двойного клика мыши");

        pageButtons.rightClick();
        assertEquals("You have done a right click", pageButtons.getRightClickMessage(),
                "Отсутствует текст '" + pageButtons.getRightClickMessage() + "'после клика правой кнопкой мыши");

        pageButtons.clickMe();
        assertEquals("You have done a dynamic click", pageButtons.getDynamicClickMessage(),
                "Отсутствует текст '" + pageButtons.getDynamicClickMessage() + "'после клика левой кнопкой мыши");
    }

    @Test
    public void testDynamicPropertiesButton() {
        chromeDriver.get("https://demoqa.com/dynamic-properties");
        PageDynamicProperties pageDynamicProperties = new PageDynamicProperties(chromeDriver);

        assertEquals("This text has random Id", pageDynamicProperties.getTextRandomId(),
                "Отсутствует текст - " + pageDynamicProperties.getTextRandomId());

        assertFalse(pageDynamicProperties.getButtonEnableAfter().isEnabled());
        wait.until(ExpectedConditions.elementToBeClickable(pageDynamicProperties.getButtonEnableAfter()));
        assertTrue(pageDynamicProperties.getButtonEnableAfter().isEnabled());

    }

    @Test
    public void testDynamicPropShowUpBtn() {
        chromeDriver.get("https://demoqa.com/dynamic-properties");
        PageDynamicProperties pageDynamicProperties = new PageDynamicProperties(chromeDriver);

        Duration implicitWaitTimeout = chromeDriver.manage().timeouts().getImplicitWaitTimeout();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        assertEquals(0, chromeDriver.findElements(By.id("visibleAfter")).size(),
                "На странице присутствует кнопка с id = visibleAfter");
        chromeDriver.manage().timeouts().implicitlyWait(implicitWaitTimeout);
        wait.until(ExpectedConditions.visibilityOf(pageDynamicProperties.getButtonVisibleAfter()));
        assertTrue(pageDynamicProperties.getButtonVisibleAfter().isDisplayed(),
                "Кнопка с id = visibleAfter на странице не отображается");
    }

    @Test
    public void testDynamicPropertiesColor() {
        chromeDriver.get("https://demoqa.com/dynamic-properties");
        PageDynamicProperties pageDynamicProperties = new PageDynamicProperties(chromeDriver);

        WebElement btnColorChange = pageDynamicProperties.getButtonColorChange();
        String colorNow = btnColorChange.getCssValue("color");
        wait.until(ExpectedConditions.attributeContains(btnColorChange, "class", "text-danger"));
        String colorChange = btnColorChange.getCssValue("color");

        assertNotEquals(colorNow, colorChange,
                "Кнопка не изменила цвет");
    }

    @Test
    void testWebTables() {
        chromeDriver.get("https://demoqa.com/webtables");
        PageWebTables pageWebTables = new PageWebTables(chromeDriver);
        pageWebTables.clickButtonAdd();

        PageWebTables.RegistrationForm form = pageWebTables.new RegistrationForm();
        form.setFieldFirstName("Ksenia");
        form.setFieldLastName("Zhilak");
        form.setFieldEmail("email@bk.ru");
        form.setFieldAge("24");
        form.setFieldSalary(50_000);
        form.setFieldDepartment("Programming");
        form.clickButtonSubmit();

        List<Map<String, String>> maps = pageWebTables.collectHeadersRows();

        assertTrue(maps.stream().anyMatch(x ->
            x.get("First Name").equals("Ksenia")&&
            x.get("Last Name").equals("Zhilak")&&
            x.get("Age").equals("24")&&
            x.get("Email").equals("email@bk.ru")&&
            x.get("Salary").equals(String.valueOf(50_000))&&
            x.get("Department").equals("Programming")
        ), "Данные в новой строке не совпадают");
    }
}





