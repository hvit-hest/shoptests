package org.restore.pages.checkoutpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.restore.pages.checkoutpage.components.CartFormSection;
import org.restore.pages.commoncomponents.CommonTable;

import java.time.Duration;

public class CheckOutPage {

    private String checkOutPageUrl = "http://http://localhost/litecart/en/checkout";
    private String allDucksRemovedMsgXpath = "//p[. = 'There are no items in your cart.']";
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    private By paymentDueBy = By.xpath("//tr[./*[.='Payment Due:']]");

    private By orderSummaryTableBy = By.cssSelector("table.dataTable");

    public CheckOutPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

     public void open() {
        driverHere.navigate().to(checkOutPageUrl);
        driverHere.manage().window().maximize();
    }

    public CartFormSection getCartFormSection() {
        return new CartFormSection(driverHere);
    }

    public CommonTable getOrderSummaryTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"), By.cssSelector("tr"), By.cssSelector("td"));
    }

    public By getPaymentDueBy() {
        return paymentDueBy;
    }

    public By getOrderSummaryTableBy() {
        return orderSummaryTableBy;
    }

    public String getNoItemMessage() {
        return driverHere.findElement(By.xpath("//p/em")).getText();
    }

    public boolean thereIsNoMoreDucksInCartMessage() {
        return driverHere.findElements(By.xpath(allDucksRemovedMsgXpath)).size() > 0;
    }

    public void stopRunningLineOfDucks() {
        getCartFormSection().stopRunningLineOfDucks();
    }

    public WebElement getOrderSummaryTableWE() {
        return driverHere.findElement(orderSummaryTableBy);
    }

    public void removeAllDucks() {
        WebDriverWait wait = new WebDriverWait(driverHere, Duration.ofSeconds(4));

        while (getCartFormSection().getEnabledRemoveDuckButtons().size() != 0) {
            //the button will be stale
            WebElement oldButton = getCartFormSection().getEnabledRemoveDuckButtons().get(0);
            //the table will be stale
            WebElement oldPaymentDueTable = getOrderSummaryTableWE();
            //CLICK!!!
            oldButton.click();
            //wait until stale button disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldButton));
            //wait until stale table disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldPaymentDueTable));
        }
    }
}