package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.utils.TestProperties;
import org.restore.utils.WebDriverSelection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver driverHere;

    @BeforeClass
    public void start() {
        driverHere = new WebDriverSelection().getDriverFromProperties();
        driverHere.manage().timeouts().
                implicitlyWait(TestProperties.getImplicitWaitFromProperties(), TimeUnit.SECONDS);

    }

    public WebDriver getWebDriver() {
        return driverHere;
    }

    @AfterClass
    public void stop() {
        if (driverHere != null) ;
        {
            driverHere.quit();
            driverHere = null;
        }
    }
}
