package hooks;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.utils.ScreenshotUtils;
import io.cucumber.java.*;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(scenario.getName());
        }

        DriverFactory.quitDriver();
    }
}