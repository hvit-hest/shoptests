package org.restore.tests;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.restore.pages.checkoutpage.CheckOutPage;
import org.restore.pages.duckdetailspage.DuckDetailsPage;
import org.restore.pages.usermainpage.UserMainPage;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.stream.Collectors;

public class ProductsCartTest extends BaseTest {

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
            String cardCounter = duckDetailsPage.getCartCounter();
            duckDetailsPage.getDuckDetailsSection().addDuckToCart(1);
            //Check cart's counter
            wait.until(ExpectedConditions.invisibilityOfElementWithText(duckDetailsPage.getCartQuantityBy(), cardCounter));
            softAssert.assertEquals( duckDetailsPage.getCartCounter(), Integer.toString(Integer.parseInt(cardCounter) + 1));
            userMainPage = new UserMainPage(driverHere);
            userMainPage.open();
        }

        userMainPage.clickCheckOutLink();
        CheckOutPage checkOutPage = new CheckOutPage(driverHere);


        //1. stop "running line" of ducks' shortcuts
        if (checkOutPage.getCartFormSection().getShortCuts().size() > 0)
            try {
                checkOutPage.getCartFormSection().getShortCuts().get(0).click();
            } catch (StaleElementReferenceException se) {
                checkOutPage.getCartFormSection().getShortCuts().get(0).click();
            }

        //Remove ducks from cart one by one waiting table renew and old "remove" button stale
        //press enabled "remove" button if exist, it has to
        while (driverHere.findElements(checkOutPage.getCartFormSection().getRemoveCartButtonBy()).
                stream().filter(WebElement::isEnabled).collect(Collectors.toList()).size() != 0) {
            //the button will be stale
            WebElement oldButton = driverHere.findElements(checkOutPage.getCartFormSection().getRemoveCartButtonBy()).
                    stream().filter(WebElement::isEnabled).collect(Collectors.toList()).get(0);
            //the table will be stale
            WebElement oldPaymentDueTable = driverHere.findElement(checkOutPage.getOrderSummaryTableBy());
            //CLICK!!!
            oldButton.click();
            //wait until stale button disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldButton));
            //wait until stale table disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldPaymentDueTable));
        }
        softAssert.assertTrue(checkOutPage.thereIsNoMoreDucksInCartMessage());
        softAssert.assertAll();
    }
}