package org.restore.pages.countriespage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.commoncomponents.CommonTable;

public class CountriesPage {

    private final String countriesPageUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private final WebDriver driverHere;
    private String countryByNameXpathTemplate = "//table[@class='dataTable']//td/a[@href][.= '%s']";

    @FindBy(css = "h1")
    private WebElement pageHeader;

    public CountriesPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(countriesPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCountriesTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.cssSelector("tr.row"),
                By.cssSelector("td"));
    }

    public void clickCountry(String country) {
           driverHere.findElement(By.xpath(String.format(countryByNameXpathTemplate, country))).click();
    }
}
