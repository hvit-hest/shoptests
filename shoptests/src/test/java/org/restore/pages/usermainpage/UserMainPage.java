package org.restore.pages.usermainpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.restore.pages.usermainpage.components.DuckDetails;
import org.restore.pages.usermainpage.components.DucksBlock;
import org.restore.pages.usermainpage.components.UserLoginForm;

import java.util.List;

public class UserMainPage {

    private final String userMainPageUrl = "http://localhost/litecart/en/";
    private final WebDriver driverHere;

    @FindBy(css = "#rslides1_s0")
    private WebElement bigDuckPicture;

    @FindBy(css = "ul.products>li.product")
    private List<WebElement> duckProducts;

    @FindBy(xpath = "//aside//li/a[.='Logout']")
    private WebElement logoutButton;

    public UserMainPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(userMainPageUrl);
        driverHere.manage().window().maximize();
    }

    public void login(String userEmail, String userPassword) {
        if (isUserLoginFormOpened())
            new UserLoginForm(driverHere).login(userEmail, userPassword);
    }

    public boolean isUserLoginFormOpened() {
        return driverHere.findElements(By.cssSelector("form[name='login_form']")).size() > 0;
    }

    public boolean isUserLoggedIn() {
        return driverHere.findElements(By.xpath("//aside//li/a[.='Logout']")).size() > 0;
    }

    public void logout() {
        if (isUserLoggedIn()) {
            logoutButton.click();
        }
    }

    public List<WebElement> getDuckProducts() {
        return duckProducts;
    }

    public List<WebElement> getDucksWEs(DucksBlock ducksBlock) {
        return driverHere.findElement(ducksBlock.getBlockSelector()).
                findElements(By.cssSelector("li.product"));
    }

    public DuckDetails getDuck(DucksBlock ducksBlock, int numberInList) {
        DuckDetails duckDetails = null;
        List<WebElement> ducksList = getDucksWEs(ducksBlock);
        if (ducksList.size() != 0)
            duckDetails = new DuckDetails(ducksList.get(numberInList));
        return duckDetails;
    }

    public void openParticularDuckDetailsPage(DucksBlock ducksBlock, int numberInList) {
        List<WebElement> getDucksWEs = getDucksWEs(ducksBlock);
        if (getDucksWEs.size() > 0) {
            getDucksWEs.get(numberInList).click();
        }
    }
}