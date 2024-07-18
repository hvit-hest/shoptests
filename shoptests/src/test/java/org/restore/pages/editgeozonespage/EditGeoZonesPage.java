package org.restore.pages.editgeozonespage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.commoncomponents.CommonTable;

public class EditGeoZonesPage {
    private WebDriver driverHere;


    @FindBy(css = "h1")
    private WebElement pageHeader;

    public EditGeoZonesPage(WebDriver driver) {
        driverHere = driver;
        PageFactory.initElements(driverHere, this);
    }


    public CommonTable getGeoZonesTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.xpath(".//tr[not(@class='header') and .//input]"),
                By.cssSelector("td"));
    }
}
