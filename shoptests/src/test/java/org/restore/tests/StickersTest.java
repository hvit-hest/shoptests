package org.restore.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.restore.pages.usermainpage.UserMainPage;
import org.restore.pages.usermainpage.components.DucksBlock;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class StickersTest extends BaseTest {
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
    public void stickersTest() {
        String stickerCSS = "[class^='sticker']";
        SoftAssert softAssertion = new SoftAssert();
        userMainPage.getDucksWEs(DucksBlock.CAMPAIGNS).forEach(
                we -> {
                    List<WebElement> stickersPerDuck = we.findElements(By.cssSelector(stickerCSS));
                    softAssertion.assertTrue(stickersPerDuck.size() == 1 &&
                            !stickersPerDuck.get(0).getText().trim().isEmpty(),
                            String.format("%s\n-----------\n", we.getText()));
                }
        );
        softAssertion.assertAll("Test did not pass");
    }
}