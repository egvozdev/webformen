package core.base;

import core.driver.DriverManager;
import core.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver = DriverManager.getDriver();

    protected WebDriverWait wait =
            new WebDriverWait(driver,
                    Duration.ofSeconds(ConfigurationManager.getTimeout()));
}