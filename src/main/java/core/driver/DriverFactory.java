package core.driver;

import core.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static void initDriver() {

        String browser = ConfigurationManager.getBrowser();

        WebDriver driver = BrowserFactory.createDriver(browser);

        driver.manage().window().maximize();

        DriverManager.setDriver(driver);
    }

    public static void quitDriver() {
        DriverManager.getDriver().quit();
        DriverManager.unload();
    }
}