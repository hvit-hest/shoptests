package org.restore.pages.usermainpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.restore.utils.TestProperties;

public class UserLoginForm {
    private WebDriver driverHere;
    private String emailFieldCSS = "[name='email']";
    private String passwordFieldCSS = "[name='password']";
    private String buttonCSS ="button[name='login']";

    public UserLoginForm (WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public void login(String userEmail, String userPassword) {
        driverHere.findElement(By.cssSelector(emailFieldCSS)).sendKeys(userEmail);
        driverHere.findElement(By.cssSelector(passwordFieldCSS)).sendKeys(userPassword);
        driverHere.findElement(By.cssSelector(buttonCSS)).click();
    }
}