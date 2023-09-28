package com.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver chromeDriver;
    protected WebDriverWait wait;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions opt = new ChromeOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeDriver = new ChromeDriver(opt);
        // Ожидание на поиск элемента, ждет каждый раз при новом поиске findElement
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Создание объекта явного ожидания чего-либо
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void closeMyTest() {
        chromeDriver.quit();
    }
}


