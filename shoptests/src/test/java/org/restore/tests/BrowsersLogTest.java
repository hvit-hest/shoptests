package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.restore.pages.adminpage.AdminPage;
import org.restore.pages.catalogpage.CatalogPage;
import org.restore.pages.editproductpage.EditProductPage;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BrowsersLogTest extends BaseTest {

    private final String adminLogin = TestProperties.getAdminNameFromProperties();
    private final String adminPassword = TestProperties.getAdminPasswordFromProperties();
    private WebDriver driverHere;
    private AdminPage adminPage;

    @BeforeClass
    public void beforeClass () {
        driverHere = getWebDriver();
        AdminPage adminPage = new AdminPage(driverHere);
        adminPage.open();
        adminPage.login(adminLogin, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Catalog");
    }

    @Test
    public void browsersLogTest() {
       CatalogPage catalogPage =  new CatalogPage(driverHere);
       catalogPage.open();
       //1. open all folders in the catalog
       catalogPage.openAllFoldersInCatalog();
       //2. how many ducks to sell?
       List<WebElement> ducks = catalogPage.getAllDucks();
       int ducksNumber = ducks.size();

        SoftAssert softAssertion = new SoftAssert();
        //3. Clean the log
        driverHere.manage().logs().get("browser");

        for (int i = 0; i < ducksNumber; i++) {
            String ducksName = ducks.get(i).getText();
            //open edit page
            ducks.get(i).click();
            EditProductPage editProductPage = new EditProductPage(driverHere);
            //check 'edit product' pages' name
            softAssertion.assertTrue(editProductPage.getPageHeader().contains(ducksName));

            List<LogEntry> browsersLog = driverHere.manage().logs().get("browser").getAll();
            //check records in log
            softAssertion.assertTrue(browsersLog.size() == 0,
                    String.format("new '%s' records in browser's log, page '%s'",
                    browsersLog.size(),
                    driverHere.getCurrentUrl()));
            //if browsersLog.size() == 0 It will do nothing
            browsersLog.forEach(System.out::println);

            //return ti list of ducks/catalog
            driverHere.navigate().back();

            //renew page object
            catalogPage =  new CatalogPage(driverHere);

            //take new ducks list instead of stale one
            catalogPage.getAllDucks();
        }
        softAssertion.assertAll();
    }
}