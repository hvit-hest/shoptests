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

    private By cartQuantityBy = By.cssSelector("#cart .quantity");
    private String quantityTemplateXpath = "//*[@id='cart']//*[@class='quantity'][.='%s']";


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

    public By getCartQuantityBy() {
        return cartQuantityBy;
    }

    public String getQuantityTemplateXpath() {
        return quantityTemplateXpath;
    }

    public WebElement getCartCounter() {
        return driverHere.findElement(cartQuantityBy);
    }

}