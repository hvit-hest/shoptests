package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.pages.adminpage.AdminPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminPageManuTest  extends BaseTest {

    private WebDriver myPersonalDriver;
    private AdminPage adminPage;

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getWebDriver();
        adminPage = new AdminPage(myPersonalDriver);
    }

    @Test
    public void adminMenuTest() {

    }



}
