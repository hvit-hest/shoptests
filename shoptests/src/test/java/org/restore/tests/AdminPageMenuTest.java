package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.datamodels.AdminMenuTestDataModel;
import org.restore.dataproviders.AdminMenuTestData;
import org.restore.pages.adminpage.AdminPage;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AdminPageMenuTest extends BaseTest {

    private final String adminLogin = TestProperties.getAdminNameFromProperties();
    private final String adminPassword = TestProperties.getAdminPasswordFromProperties();
    private WebDriver myPersonalDriver;
    private AdminPage adminPage;

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getWebDriver();
        adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminLogin, adminPassword);
    }

    @Test(dataProvider = "adminMenuTestData", dataProviderClass = AdminMenuTestData.class)
    public void adminMenuTest(AdminMenuTestDataModel itemData) {
        SoftAssert softAssert = new SoftAssert();
        String itemName = itemData.getItemName();
        String pageHeaderExpected = itemData.getPageHeader();
        List<AdminMenuTestDataModel> subMenu = itemData.getSubMenu();
        adminPage.getMainAdminMenu().selectMenuOption(itemName);
        softAssert.assertTrue(adminPage.headerIsFound(pageHeaderExpected),
                String.format("For '%s' main menu item - header '%s' was not found", itemName, pageHeaderExpected));
        /*if (subMenu.size() != 0) {
            for (int i = subMenu.size()-1; i >= 0; i--) {
                String subMenuItemName = subMenu.get(i).getItemName();
                pageHeaderExpected = subMenu.get(i).getPageHeader();
                adminPage.getMainAdminMenu().selectSubMenuOption(subMenuItemName);
                softAssert.assertTrue(adminPage.headerIsFound(pageHeaderExpected),
                        String.format("For '%s' sub menu item - header '%s' was not found", subMenuItemName, pageHeaderExpected));
            }
        }*/
  /*      if (subMenu.size() != 0) {
            subMenu.forEach(subItem -> {
                String subMenuItemName = subItem.getItemName();
                String subPageHeaderExpected = subItem.getPageHeader();
                adminPage.getMainAdminMenu().selectSubMenuOption(subMenuItemName);
                softAssert.assertTrue(adminPage.headerIsFound(subPageHeaderExpected),
                        String.format("For '%s' sub menu item - header '%s' was not found", subMenuItemName, subPageHeaderExpected));
            });
        }*/

        if (subMenu.size() != 0) {
            for (AdminMenuTestDataModel subItem : subMenu) {
                String subMenuItemName = subItem.getItemName();
                pageHeaderExpected = subItem.getPageHeader();
                adminPage.getMainAdminMenu().selectSubMenuOption(subMenuItemName);
                softAssert.assertTrue(adminPage.headerIsFound(pageHeaderExpected),
                        String.format("For '%s' sub menu item - header '%s' was not found", subMenuItemName, pageHeaderExpected));
            }
        }
        softAssert.assertAll();
    }
}
