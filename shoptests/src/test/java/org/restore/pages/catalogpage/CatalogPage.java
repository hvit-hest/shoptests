package org.restore.pages.catalogpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.commoncomponents.CommonTable;

public class CatalogPage {

    private String catalogPageUrl = "http://localhost/litecart/admin/?app=catalog&doc=catalog";
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[@class='button'][contains(.,'Add New Product')]")
    private WebElement addNewProductButton;

    public CatalogPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(catalogPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCatalogTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.xpath(".//tr[not(@class='header') and not(@class = 'footer') and .//a[not(.='[Root]')]]"),
                By.cssSelector("td"));
    }

    public void clickAddNewProductButton() {
        addNewProductButton.click();
    }
}
