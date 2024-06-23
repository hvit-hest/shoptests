package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.utils.TestProperties;
import org.restore.utils.WebDriverSelection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver myPersonalDriver;

    @BeforeClass
    public void start() {
        myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
        myPersonalDriver.manage().timeouts().
                implicitlyWait(TestProperties.getImplicitWaitFromProperties(), TimeUnit.SECONDS);

    }

    public WebDriver getWebDriver() {
        return myPersonalDriver;
    }

    @AfterClass
    public void stop() {
        if (myPersonalDriver != null) ;
        {
            myPersonalDriver.quit();
            myPersonalDriver = null;
        }
    }
}
