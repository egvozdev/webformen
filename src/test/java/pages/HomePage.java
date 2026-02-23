package pages;

import core.base.BasePage;
import core.config.ConfigReader;
import core.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    //private final By searchInput = By.id("x3364b");

    By searchInput = By.xpath("//input[@type='search'][@name='w']");

    By russianTranslation = By.xpath("//span[contains(text(),'wonderful')]");

    By acceptButton = By.xpath("//button[contains(.,'Accept and continue')]");

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void search(String word) {
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
}


