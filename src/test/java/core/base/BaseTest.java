package core.base;

import core.driver.DriverFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setup() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}