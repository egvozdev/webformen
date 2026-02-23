package pages;

import hooks.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver = Hooks.driver;

    By searchInput = By.xpath("//input[@type='search'][@name='w']");

    By russianTranslation = By.xpath("//span[contains(text(),'wonderful')]");

    By acceptButton = By.xpath("//button[contains(.,'Accept and continue')]");

    public void openHomePage() {
        driver.get("https://www.verbformen.ru/");
    }

    public void enterWord(String word) {
        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(word);
        input.submit();

        try {
            // Пауза 4000 миллисекунд (4 секунды)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handlePopupIfPresent();
    }

    private void handlePopupIfPresent() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement translation = wait.until(
                ExpectedConditions.visibilityOfElementLocated(russianTranslation)
        );
        return translation.isDisplayed();
    }

    public boolean isTranslationDisplayed(String word) {
        //handlePopupIfPresent();
        By transLoc = By.xpath("//span[contains(text(),'"+word+"')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement translation = wait.until(
                ExpectedConditions.visibilityOfElementLocated(transLoc)
        );
        return translation.isDisplayed();
    }
}





