package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.datamodels.LoginTestDataModel;
import org.restore.dataproviders.LoginTestData;
import org.restore.pages.adminpage.AdminPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminLoginTest extends BaseTest {

    private WebDriver myPersonalDriver;
    private AdminPage adminPage;

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getWebDriver();
        adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginTestData.class)
    public void loginTest(LoginTestDataModel testData) {
        String login = testData.getAdminLogin();
        String password = testData.getAdminPassword();
        boolean testPass = testData.getTestPass();
        if (adminPage.isAdminLoginFormOpen())
            adminPage.login(login, password);
        else {
            adminPage.logout();
            adminPage.login(login, password);
        }


        if (!testPass)
            /*Did login fail? It has to be...
              Add more negative test data (later)
             */
            assertTrue(adminPage.isAdminLoginFormOpen());
        else {
            //Did login succeed? It has to be...
            assertTrue(adminPage.isAdminPageOpen());
        }
    }
}
