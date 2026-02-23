package core.utils;

import core.driver.DriverManager;
import org.openqa.selenium.*;

public class JSUtils {

    private static JavascriptExecutor js() {
        return (JavascriptExecutor)
                DriverManager.getDriver();
    }

    public static void click(By locator) {
        WebElement element =
                DriverManager.getDriver().findElement(locator);

        js().executeScript("arguments[0].click();", element);
    }

    public static void scrollTo(By locator) {
        WebElement element =
                DriverManager.getDriver().findElement(locator);

        js().executeScript(
                "arguments[0].scrollIntoView(true);",
                element);
    }

    public static String getTitle() {
        return (String) js()
                .executeScript("return document.title;");
    }
}