package pages;

import core.base.BasePage;
import core.config.ConfigReader;
import core.utils.LoggerUtils;
import core.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    //private final By searchInput = By.id("x3364b");

    private static final Logger log =
            LoggerUtils.getLogger(HomePage.class);

    By searchInput = By.xpath("//input[@type='search'][@name='w']");

    By russianTranslation = By.xpath("//span[contains(text(),'wonderful')]");

    By acceptButton = By.xpath("//button[contains(.,'Accept and continue')]");

    public void open() {
        log.info("Opening URL: {}",
                ConfigReader.get("baseUrl"));
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void search(String word) {
        log.info("Searching for word: {}", word);
        WaitUtils.waitForVisible(searchInput)
                .sendKeys(word + Keys.ENTER);
        try {
            // Пауза 4000 миллисекунд (4 секунды)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handlePopupIfPresent();
    }

    private void handlePopupIfPresent() {

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // если popup внутри iframe
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            if (!iframes.isEmpty()) {
                System.out.println("inside iframe");
                for (WebElement iframe : iframes) {
                    driver.switchTo().frame(iframe);
                    if (driver.findElements(acceptButton).size() > 0) {
                        wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
                        driver.switchTo().defaultContent();
                        return;
                    }
                    driver.switchTo().defaultContent();
                }
            }

            // если обычный overlay
            if (driver.findElements(acceptButton).size() > 0) {
                System.out.println("inside layout");
                wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
            }

        } catch (Exception ignored) {
        }
    }

    public boolean isRussianTranslationDisplayed() {
        //handlePopupIfPresent();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement translation = wait.until(
                ExpectedConditions.visibilityOfElementLocated(russianTranslation)
        );
        return translation.isDisplayed();
    }

    public boolean isTranslationDisplayed(String word) {
        //handlePopupIfPresent();
        By transLoc = By.xpath("//span[contains(text(),'"+word+"')]");

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement translation = wait.until(
                ExpectedConditions.visibilityOfElementLocated(transLoc)
        );
        return translation.isDisplayed();
    }

    public Map<String, String> getActualFormsFromPage() {

        List<WebElement> cells = driver.findElements(By.xpath("(//*[text() = 'Презенс'])[1]/..//table//td"));


        List<String> cellValues = cells.stream()
                .map(cell -> cell.getText().replaceAll("\\s+", " ").trim())
                .collect(Collectors.toList());
        Map <String, String> map = new HashMap<>();
        for (int i = 0; i < cellValues.size()-1; i++) {
            map.put(cellValues.get(i), cellValues.get(i+1).replaceAll("[^a-zA-Z]+", ""));
        }
        return map;
        // Здесь selenium / playwright / jsoup — парсинг HTML-таблицы
//        return Map.of(
//                "ich", "gehe",
//                "du", "gehst",
//                "er/sie/es", "geht"
//        );
    }
}


