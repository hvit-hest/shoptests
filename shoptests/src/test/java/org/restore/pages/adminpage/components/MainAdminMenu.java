package org.restore.pages.adminpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainAdminMenu {
    private final WebDriver myPersonalDriver;
    private final String searchForMainItemXpath = "//li//span[contains(@class,'icon')]/following-sibling::span[.='%s']";
    private final String searchForSubMenuItemXpath = "//li[starts-with(@id,'doc')]/*[.='%s']";

    public MainAdminMenu(WebDriver myPersonalDriver) {
        this.myPersonalDriver = myPersonalDriver;
    }

    public void selectMenuOption(String menuOption, String searchMenuOptionXpath) {
        WebElement menuOptionWe = myPersonalDriver.
                findElement(By.xpath(String.format(searchMenuOptionXpath, menuOption)));

        menuOptionWe.click();
        /*Sometimes driver does not click (very rare) without any messages and reason when we select
        from bottom item of menu to top item. That is why the second try*/

        menuOptionWe = myPersonalDriver.
                findElement(By.xpath(String.format(searchMenuOptionXpath, menuOption)));

        if (!menuOptionWe.getCssValue("color").equals("rgba(255,0,0,1)")) {
            myPersonalDriver.navigate().refresh();
        }
    }

    public MainAdminMenu selectMenuOption(String menuOption) {
        selectMenuOption(menuOption, searchForMainItemXpath);
        return this;
    }

    public void selectSubMenuOption(String subMenuOption) {
        selectMenuOption(subMenuOption, searchForSubMenuItemXpath);
    }
}

