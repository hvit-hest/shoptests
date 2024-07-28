package org.restore.pages.checkoutpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartFormSection {

    private WebDriver driverHere;
    private By shortCutsBy = By.cssSelector(".shortcuts>li");
    private By removeCartButtonBy = By.cssSelector("button[name='remove_cart_item']");


    public CartFormSection(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public List<WebElement> getShortCuts() {
        return driverHere.findElements(shortCutsBy);
    }

    public void clickRemoveButton() {
        driverHere.findElement(removeCartButtonBy).click();
    }

    public By getRemoveCartButtonBy() {
        return removeCartButtonBy;
    }


    public void removeAllItems() {
        WebDriverWait wait = new WebDriverWait(driverHere, Duration.ofSeconds(4));
        //stop running line of ducks
        if (getShortCuts().size() > 0)
            try {
                getShortCuts().get(0).click();
            } catch (StaleElementReferenceException se) {
                getShortCuts().get(0).click();
            }

        //press enabled "remove" button if exist and not stale
        while (driverHere.findElements(removeCartButtonBy).stream().
                filter(WebElement::isEnabled).collect(Collectors.toList()).size() != 0) {

            WebElement we = driverHere.findElements(removeCartButtonBy).stream().
                    filter(WebElement::isEnabled).collect(Collectors.toList()).get(0);

            driverHere.findElements(removeCartButtonBy).stream().
                    filter(WebElement::isEnabled).collect(Collectors.toList()).get(0).click();

            wait.until(ExpectedConditions.stalenessOf(we));

        }
    }
}