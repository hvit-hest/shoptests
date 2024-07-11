package org.restore.pages.adminpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.restore.pages.adminpage.components.MainAdminMenu;

import java.time.Duration;

public class AdminPage {

    private String adminPageUrl = "http://localhost/litecart/admin";
    private WebDriver driverHere;

    @FindBy(css = "input[name = 'username']")
    private WebElement inputName;

    @FindBy(css = "input[name = 'password']")
    private WebElement inputPassword;

    @FindBy(css = "button[name = 'login']")
    private WebElement loginButton;

    @FindBy(css = "[title='Logout'] i")
    private WebElement logoutButton;

    public AdminPage(WebDriver driverHere) {
        this.driverHere = driverHere;
        PageFactory.initElements(driverHere, this);
    }


    public void open() {
        driverHere.navigate().to("http://localhost/litecart/admin");
        driverHere.manage().window().maximize();
    }

    public boolean isAdminPageOpen() {
        return driverHere.findElements(By.cssSelector("[title='Logout'] i")).size() != 0;
    }

    public boolean isAdminLoginFormOpen() {
        return driverHere.findElements(By.cssSelector("input[name = password]")).size() != 0;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void logout() {
        clickLogoutButton();
    }

    public void login(String adminName, String adminPassword) {
        if (isAdminLoginFormOpen()) {
            new WebDriverWait(driverHere, Duration.ofSeconds(10)).
                    until(ExpectedConditions.elementToBeClickable(inputName));

            inputName.clear();
            inputName.click();
            inputName.sendKeys(adminName);
            inputPassword.clear();
            inputPassword.click();
            inputPassword.sendKeys(adminPassword);

            clickLoginButton();
        }
    }

    public boolean headerIsFound(String headerToFind) {
        String searchHeaderStringXpath = String.format("//h1[normalize-space(.) = '%s']", headerToFind);
        return driverHere.findElement(By.xpath(searchHeaderStringXpath)).isDisplayed();
    }

    public MainAdminMenu getMainAdminMenu() {return new MainAdminMenu(driverHere);}
}
