package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.pages.duckdetailspage.DuckDetailsPage;
import org.restore.pages.duckdetailspage.components.DuckDetailsSection;
import org.restore.pages.usermainpage.UserMainPage;
import org.restore.utils.GoodsDetailsComparisonUtils;
import org.restore.utils.TestProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.restore.pages.usermainpage.components.DucksBlock.CAMPAIGNS;
import static org.restore.utils.GoodsDetailsComparisonUtils.*;

public class GoodsDetailsTest extends BaseTest {
    private WebDriver driverHere;
    private String userEmail;
    private String userPassword;
    private UserMainPage userMainPage;

    @BeforeClass
    public void beforeClass() {
        userPassword = TestProperties.getUserPasswordFromProperties();
        userEmail = TestProperties.getUserEmailFromProperties();
        driverHere = getWebDriver();
        userMainPage = new UserMainPage(driverHere);
        userMainPage.open();
        userMainPage.login(userEmail, userPassword);
    }

    @Test
    public void goodsDetailsTest() {
        SoftAssert softAssertion = new SoftAssert();

        //It was said to check picture/data of first duck in Campaign on Main Page
        Map<String, String> duckData = userMainPage.getDuck(CAMPAIGNS, 0).getDuckData();
        String duckOnMainPageName = duckData.get("duckName");
        String duckOnMainPageRegularPrice = duckData.get("regularPrice");
        String duckOnMainPageRegularColor = duckData.get("regularPriceColor");
        String duckOnMainPageRegularPriceDecoration = duckData.get("regularPriceDecoration");
        String duckOnMainPageRegularPriceFontSize = duckData.get("regularPriceFontSize");
        String duckOnMainPageCampaignPrice = duckData.get("campaignPrice");
        String duckOnMainPageCampaignPriceColor = duckData.get("campaignPriceColor");
        String duckOnMainPageCampaignFontWeight = duckData.get("campaignPriceFontWeight");
        String duckOnMainPageCampaignPriceFontSize = duckData.get("campaignPriceFontSize");

        //Asserts for UserMainPage

        //regular price is grey on MainPage
        List<Integer> regularPriceIntMainPage = getNumberOfColor(duckOnMainPageRegularColor);
        softAssertion.assertTrue(
                regularPriceIntMainPage.get(0) == regularPriceIntMainPage.get(1) &&
                        regularPriceIntMainPage.get(1) == regularPriceIntMainPage.get(2),
                String.format("Colour is grey when the numbers '%s' '%s' '%s' are equal, Main Page",
                        regularPriceIntMainPage.get(0),
                        regularPriceIntMainPage.get(1),
                        regularPriceIntMainPage.get(2)));


    //regular price is crossed out on MainPage
        softAssertion.assertTrue(duckOnMainPageRegularPriceDecoration.contains("line-through"),
                "Regular price is NOT crossed out on Main Page");

        //Campaign price FONT is more than Regular price font on Main page
        softAssertion.assertTrue(
                compareFonts(duckOnMainPageCampaignPriceFontSize, duckOnMainPageRegularPriceFontSize) > 0,
                String.format("Campaign price font size '%s' has to be more than regular price font size '%s', Main Page",
                        duckOnMainPageCampaignPriceFontSize, duckOnMainPageRegularPriceFontSize)
        );


        //Compare regular price and campaign price on MainPage
        softAssertion.assertTrue(comparePrices(duckOnMainPageCampaignPrice, duckOnMainPageRegularPrice) < 0,
                String.format("Campaign price '%s' has to be less than regular price '%s', Main Page",
                        duckOnMainPageCampaignPrice, duckOnMainPageRegularPrice));

        //Click!
        //It was said to check first duck in Campaign so click it
        userMainPage.openParticularDuckDetailsPage(CAMPAIGNS, 0);

        //Get data from DuckDetailsPage
        DuckDetailsPage duckDetailsPage = new DuckDetailsPage(driverHere);
        DuckDetailsSection duckDetailsSection = duckDetailsPage.getDuckDetailsSection();
        Map<String, String> duckDataOnDuckDetailsPage = duckDetailsSection.getDuckData();

        String duckOnDetailsPageName = duckDetailsPage.getDuckName();
        String duckOnDetailsPageRegularPrice = duckDataOnDuckDetailsPage.get("regularPrice");
        String duckOnDetailsPageRegularPriceColor = duckDataOnDuckDetailsPage.get("regularPriceColor");
        String duckOnDetailsPageRegularPriceDecoration = duckDataOnDuckDetailsPage.get("regularPriceDecoration");
        String duckOnDetailsPageRegularPriceFontSize = duckData.get("regularPriceFontSize");
        String duckOnDetailsPageCampaignPrice = duckDataOnDuckDetailsPage.get("campaignPrice");
        String duckOnDetailsPageCampaignPriceColor = duckDataOnDuckDetailsPage.get("campaignPriceColor");
        String duckOnDetailsPageCampaignFontWeight = duckData.get("campaignPriceFontWeight");
        String duckOnDetailsPageCampaignPriceFontSize = duckData.get("campaignPriceFontSize");

        //regular price is grey of color on DuckDetails Page
        List<Integer> regularPriceColorsIntDetailsPage = getNumberOfColor(duckOnDetailsPageRegularPriceColor);
        softAssertion.assertTrue(
                regularPriceColorsIntDetailsPage.get(0) == regularPriceColorsIntDetailsPage.get(1) &&
                        regularPriceColorsIntDetailsPage.get(1) == regularPriceColorsIntDetailsPage.get(2),
                String.format("Colour is grey when the numbers '%s' '%s' '%s' are equal, DuckDetails Page",
                        regularPriceColorsIntDetailsPage.get(0),
                        regularPriceColorsIntDetailsPage.get(1),
                        regularPriceColorsIntDetailsPage.get(2))
        );

        //regular price is crossed out on DuckDetails Page
        softAssertion.assertTrue(duckOnDetailsPageRegularPriceDecoration.contains("line-through"),
                "Regular price is NOT crossed out on Details Page");

        //campaign price is red of color
        List<Integer> campaignPriceColorsIntDetailsPage = getNumberOfColor(duckOnDetailsPageCampaignPriceColor);
        softAssertion.assertTrue(
                campaignPriceColorsIntDetailsPage.get(1) == 0 &&
                        campaignPriceColorsIntDetailsPage.get(2) == 0,
                String.format("Colour is red when the numbers '%s' '%s' are 0, DuckDetails Page",
                        regularPriceColorsIntDetailsPage.get(1),
                        regularPriceColorsIntDetailsPage.get(2)));

        //campaign price has "bold" font-weight on DuckDetails Page
        softAssertion.assertTrue(
                duckOnDetailsPageCampaignFontWeight.equals("bold") ||
                        Integer.parseInt(duckOnDetailsPageCampaignFontWeight) >= 700,
                "Campaign price is NOT bold out on DuckDetails Page");

        //Campaign price font is more than Regular price font on DuckDetails Page
        softAssertion.assertTrue(
                compareFonts(duckOnDetailsPageCampaignPriceFontSize,
                        duckOnDetailsPageRegularPriceFontSize) > 0,
                String.format("Campaign price font '%s' has to be more than regular price font '%s', Details Page",
                        duckOnDetailsPageCampaignPrice, duckOnDetailsPageRegularPrice));

        //Compare regular price and campaign price on DuckDetails Page

        softAssertion.assertTrue(comparePrices(duckOnDetailsPageCampaignPrice, duckOnDetailsPageRegularPrice) < 0,
                String.format("Campaign price '%s' has to be less than regular price '%s', DuckDetails Page",
                        duckOnDetailsPageCampaignPrice, duckOnDetailsPageRegularPrice));


        //Asserts to compare Duck's prices on both pages - Main page and DuckDetails page
        //Compare ducks' names
        softAssertion.assertEquals(duckOnMainPageName, duckOnDetailsPageName,
                String.format("'%s' vs '%s' Ducks' names are different", duckOnMainPageName, duckOnDetailsPageName));

        //Compare regular prices on both pages

        softAssertion.assertTrue(comparePrices(duckOnDetailsPageRegularPrice, duckOnMainPageRegularPrice) == 0,
                String.format("Regular price '%s' on Main page  has to be equal regular price '%s' on DuckDetails page",
                        duckOnMainPageRegularPrice, duckOnDetailsPageRegularPrice));

        //Compare campaign prices on both pages

        softAssertion.assertTrue(comparePrices(duckOnDetailsPageCampaignPrice, duckOnMainPageCampaignPrice) == 0,
                String.format("Campaign price '%s' on Main page  has to equal campaign price '%s' on DuckDetails page",
                        duckOnMainPageCampaignPrice, duckOnDetailsPageCampaignPrice));

        softAssertion.assertAll();
}
}
