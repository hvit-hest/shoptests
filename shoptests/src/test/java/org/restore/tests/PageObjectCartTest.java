package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.restore.pages.checkoutpage.CheckOutPage;
import org.restore.pages.duckdetailspage.DuckDetailsPage;
import org.restore.pages.usermainpage.UserMainPage;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class PageObjectCartTest extends BaseTest {

    private WebDriver driverHere;
    private String userEmail;
    private String userPassword;
    private UserMainPage userMainPage;

    @BeforeClass
    public void beforeClass() {
        userPassword = TestProperties.getUserPasswordFromProperties();
        userEmail = TestProperties.getUserEmailFromProperties();
        driverHere = getWebDriver();
        userMainPage = new UserMainPage(driverHere);
        userMainPage.open();
        userMainPage.login(userEmail, userPassword);
    }

    @Test
    public void productsCartTest() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driverHere, Duration.ofSeconds(5));

        //add first three ducks
        for (int i = 0; i < 3; i++) {
            userMainPage.selectDuckByItsOrder(i);
            DuckDetailsPage duckDetailsPage = new DuckDetailsPage(driverHere);
            String ducksInCartCounter = duckDetailsPage.getCartCounter();
            duckDetailsPage.getDuckDetailsSection().addDuckToCart(1);
            //Wait cart counter changed
            duckDetailsPage.waitTillDuckCounterChange(ducksInCartCounter);
            //Check cart's counter
            softAssert.assertEquals(duckDetailsPage.getCartCounter(), Integer.toString(Integer.parseInt(ducksInCartCounter) + 1));

            userMainPage = new UserMainPage(driverHere);
            userMainPage.open();
        }

        userMainPage.clickCheckOutLink();
        CheckOutPage checkOutPage = new CheckOutPage(driverHere);

        //1. stop "running line" of ducks' shortcuts
        checkOutPage.stopRunningLineOfDucks();
        //2. remove all ducks
        checkOutPage.removeAllDucks();
        //3. check message all ducks removed
        softAssert.assertTrue(checkOutPage.thereIsNoMoreDucksInCartMessage());
        softAssert.assertAll();
    }
}
