package org.restore.pages.geozonespage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.commoncomponents.CommonTable;

public class GeoZonesPage {

    private String geoZonesPageUrl = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    private WebDriver driverHere;
    private String countryXpath = "//table[@class='dataTable']//td/a[@href][.= '%s']";

    @FindBy(css = "h1")
    private WebElement pageHeader;

    public GeoZonesPage(WebDriver myPersonalDriver) {
        driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(geoZonesPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCountriesTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.cssSelector("tr.row"),
                By.cssSelector("td"));
    }

    public void clickCountry(String country) {
        driverHere.findElement(By.xpath(String.format(countryXpath, country))).click();
    }
}