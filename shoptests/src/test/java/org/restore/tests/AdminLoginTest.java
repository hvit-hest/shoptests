package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.datamodels.AdminLoginTestDataModel;
import org.restore.dataproviders.AdminLoginTestData;
import org.restore.pages.adminpage.AdminPage;
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

    @Test(dataProvider = "loginData", dataProviderClass = AdminLoginTestData.class)
    public void loginTest(AdminLoginTestDataModel testData) {
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
