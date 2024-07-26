package org.restore.pages.securitysettingpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.commoncomponents.CommonTable;

public class SecuritySettingsPage {
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    public SecuritySettingsPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements( driverHere, this);
    }

    public void open() {
        driverHere.manage().window().maximize();
    }

    public String getPageName() { return pageHeader.getText(); }

    public CommonTable getSettingsSecurityTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.xpath(".//tr[not(@class='header')]"),
                By.cssSelector("td")) {
            @Override
            public void clickCellInRow( WebElement row, int cellOrder) {
                getRowCells(row).get(cellOrder).findElement(By.cssSelector("a[href]")).click();
            }
        };
    }

    public void setCaptchaRadioButton(String trueOrFalse) {
        driverHere.findElement(By.xpath(String.format("//table[@class='dataTable']//label[contains(.,'%s')]",
                trueOrFalse))).click();
    }

    public void clickButtonSave() {driverHere.findElement(By.cssSelector("button[name='save']"));}
}