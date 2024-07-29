package org.restore.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.restore.dataproviders.AddWindowTestData;
import org.restore.pages.adminpage.AdminPage;
import org.restore.pages.countiespage.CountriesPage;
import org.restore.pages.editcountrypage.EditCountryPage;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import static org.restore.utils.Utils.anyWindowOtherThan;
import static org.restore.utils.Utils.getLinkAsString;


public class AddWindowTest extends BaseTest {
    private WebDriver driverHere;

    @BeforeClass
    public void beforeClass() {

        driverHere = getWebDriver();
        String adminName = TestProperties.getAdminNameFromProperties();
        String adminPassword = TestProperties.getAdminNameFromProperties();
        AdminPage adminPage = new AdminPage(driverHere);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Countries");
    }

    @Test(dataProvider = "addWindowTestData", dataProviderClass = AddWindowTestData.class)
    public void addWindowTest(Map<String, String> testData) {
        CountriesPage countriesPage = new CountriesPage(driverHere);
        countriesPage.clickCountry("Russian Federation");
        EditCountryPage editCountryPage = new EditCountryPage(driverHere);
        SoftAssert softAssertion = new SoftAssert();

        WebDriverWait wait = new WebDriverWait(driverHere, Duration.ofSeconds(7));

        editCountryPage.getHrefList().forEach(href -> {
            String originalWindow = driverHere.getWindowHandle();
            Set<String> existingWindows = driverHere.getWindowHandles();
            String link = getLinkAsString(href);
            href.click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driverHere.switchTo().window(newWindow);
            String header = driverHere.findElement(By.cssSelector("h1")).getText();
            softAssertion.assertEquals(testData.get(header), link,
                    String.format("header '%s' is not expected for '%s'", header, link));
            driverHere.close();
            driverHere.switchTo().window(originalWindow);
        });
        softAssertion.assertAll();
    }
}