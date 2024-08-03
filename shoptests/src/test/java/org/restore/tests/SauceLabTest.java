package org.restore.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SauceLabTest extends BaseTest {

    private WebDriver driverHere;
    private final String w3schoolPage = "https://www.w3schools.com";
    private String titleToCheck = "w3schools online web tutorials";

    @BeforeClass
    public void beforeClass() {
        driverHere = getWebDriver();
    }

    @Test
    public void openPageTest() {

        //Open page
        driverHere.get(w3schoolPage);
        driverHere.manage().window().maximize();
        //Check title (Also it is possible to look for some elements on the page)
        Assert.assertTrue(driverHere.getTitle().toLowerCase().contains(titleToCheck),
                "the test does not pass");
    }


    @AfterClass
    public void afterClass() {
        ((JavascriptExecutor) driverHere).executeScript("sauce:job-result=" + ("passed"));
    }
}