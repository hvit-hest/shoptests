package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.restore.datamodels.AccountFormData;
import org.restore.pages.adminpage.AdminPage;
import org.restore.pages.commoncomponents.CommonTable;
import org.restore.pages.createaccoutpage.CreateAccountPage;
import org.restore.pages.securitysettingpage.SecuritySettingsPage;
import org.restore.pages.usermainpage.UserMainPage;
import org.restore.utils.TestProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    private WebDriver driverHere;
    private final String itemName = "Settings";
    private final String subMenuItemName = "Security";


    @BeforeClass
    public void beforeClass() {

        //Turn off CAPTCHA
        driverHere = getWebDriver();
        String adminName = TestProperties.getAdminNameFromProperties();
        String adminPassword = TestProperties.getAdminPasswordFromProperties();
        AdminPage adminPage = new AdminPage(driverHere);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption(itemName).selectSubMenuOption(subMenuItemName);
        SecuritySettingsPage securitySettingsPage = new SecuritySettingsPage(driverHere);
        CommonTable securitySettingTable = securitySettingsPage.getSettingsSecurityTable();
        WebElement row = securitySettingTable.getRowByText("CAPTCHA");
        securitySettingTable.clickCellInRow(row, 2);
        securitySettingsPage.setCaptchaRadioButton("False");
        securitySettingsPage.clickButtonSave();
    }

    @Test
    public void userRegistrationTest() {
        AccountFormData accountFormData = new AccountFormData();
        UserMainPage mainPage = new UserMainPage(driverHere);
        mainPage.open();
        mainPage.clickNewCustomerLink();
        CreateAccountPage createAccountPage = new CreateAccountPage(driverHere);
        createAccountPage.fillUsersForm(accountFormData);
        mainPage = new UserMainPage(driverHere);
        Assert.assertTrue(mainPage.isLogoutButtonPresent(), "Login was not successful");
        mainPage.logout();
        Assert.assertTrue(mainPage.isUserLoginFormOpened(), "Logout was not successful");
        mainPage.login(accountFormData.getEmail(), accountFormData.getPassword());
        Assert.assertTrue(mainPage.isLogoutButtonPresent(), "Login was not successful");
        mainPage.logout();
        Assert.assertTrue(mainPage.isUserLoginFormOpened(), "Logout was not successful");
    }
}