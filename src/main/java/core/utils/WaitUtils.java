package core.utils;

import core.driver.DriverManager;
import core.config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait waitSec() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ConfigReader.getInt("timeout"))
        );
    }

    public static WebElement waitForVisible(By locator) {
        return waitSec().until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return waitSec().until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForInvisibility(By locator) {
        waitSec().until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForUrlContains(String text) {
        waitSec().until(
                ExpectedConditions.urlContains(text));
    }
}