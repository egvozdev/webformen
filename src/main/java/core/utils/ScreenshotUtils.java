package core.utils;

import core.driver.DriverManager;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtils {

    public static void takeScreenshot(String name) {

        try {
            TakesScreenshot ts =
                    (TakesScreenshot) DriverManager.getDriver();

            File source = ts.getScreenshotAs(OutputType.FILE);

            File destination =
                    new File("target/screenshots/" + name + ".png");

            destination.getParentFile().mkdirs();

            Files.copy(source.toPath(), destination.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}