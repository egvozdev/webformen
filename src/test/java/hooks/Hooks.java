package hooks;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.utils.LoggerUtils;
import core.utils.ScreenshotUtils;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Hooks {

    private static final Logger log =
            LoggerUtils.getLogger(Hooks.class);

    @Before
    public void setup() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            log.error("Scenario FAILED: {}",
                    scenario.getName());
        } else {
            log.info("Scenario PASSED: {}",
                    scenario.getName());
        }
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(scenario.getName());
        }

        DriverFactory.quitDriver();
    }
}