package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage {
     /*xpath - надёжен
      xpath - читаем
      xpath - писать быстро*/
    private String selectorExchangeRates =
            "//section[not(@style='display: none;')]//table[@class='card-rates-table cards']";
    private String selectorTableHeaders = ".//thead//th";
    private String selectorTableRows = ".//tbody/tr";

    private WebDriver driver;
    private WebElement exchangeRates;
    private List<Map<String, String>> collectExchangeRates = new ArrayList<>();


    public OpenPage(WebDriver driver) {
        this.driver = driver;
        this.driver.findElement(By.xpath(
                "//span[text()='Закрыть']/ancestor::button")).click();
    }


    // Переход на все курсы
    public void goAllCource() {
        driver.findElement(By.xpath("//a[text()='Все курсы']")).click();
    }

    public List<Map<String, String>> getCollectExchangeRates() {
        // Таблица с валютами
        exchangeRates = driver.findElement(By.xpath(selectorExchangeRates));
        // Заголовки таблицы
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        // Строки таблицы
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));

        for (int i = 0; i < tableRows.size(); i++) {
            // Для каждой строки временная Map
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); j++) {
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()
                );
            }
            collectExchangeRates.add(collectRow);
        }
        return collectExchangeRates;
    }
}
