package org.restore.pages.duckdetailspage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.duckdetailspage.components.DuckDetailsSection;

public class DuckDetailsPage {
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;


    public DuckDetailsPage(WebDriver mePersonalDriver) {
        this.driverHere = mePersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public String getPageName() {
        return pageHeader.getText();
    }

    public String getDuckName() {
        return getPageName();
    }

    public DuckDetailsSection getDuckDetailsSection() {
        return new DuckDetailsSection(driverHere.findElement(By.cssSelector("#box-product")));
    }
}
