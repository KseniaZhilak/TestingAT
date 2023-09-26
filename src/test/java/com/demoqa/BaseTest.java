package com.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
        protected WebDriver chromeDriver;

        @BeforeEach
        public void before(){
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
            ChromeOptions opt = new ChromeOptions();
            opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeDriver = new ChromeDriver(opt);
            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        @AfterEach
        public void closeMyTest(){
            chromeDriver.quit();
        }
    }


