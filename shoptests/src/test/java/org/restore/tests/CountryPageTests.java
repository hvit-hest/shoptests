package org.restore.tests;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.restore.pages.adminpage.AdminPage;
import org.restore.pages.commoncomponents.CommonTable;
import org.restore.pages.countiespage.CountriesPage;
import org.restore.pages.countrydatapage.CountryDataPage;
import org.restore.pages.editgeozonespage.EditGeoZonesPage;
import org.restore.pages.geozonespage.GeoZonesPage;
import org.restore.utils.TestProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryPageTests extends BaseTest {

    private WebDriver driverHere;
    private String adminName;
    private String adminPassword;

    @BeforeClass
    public void beforeClass() {
        driverHere = getWebDriver();
        adminName = TestProperties.getAdminNameFromProperties();
        adminPassword = TestProperties.getAdminPasswordFromProperties();
        AdminPage adminPage = new AdminPage(driverHere);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
    }
    @Test
    public void countriesAlphabetOrderTest() {
        CountriesPage countriesPage = new CountriesPage(driverHere);
        countriesPage.open();
        List<String> countries =countriesPage.getCountriesTable().getColumnsText(4);
        Assert.assertTrue( countries.size() !=0 && Ordering.natural().isOrdered(new ArrayList()));
    }

    @Test
    public void timeZonesAlphabetOrderTest() {
        CountriesPage countriesPage = new CountriesPage(driverHere);
        countriesPage.open();
        CommonTable countriesTable = countriesPage.getCountriesTable();

        //the countries with tz > 0
        Map<String, Integer> countries = countriesTable.getRows().stream().
                filter(rr -> Integer.parseInt(countriesTable.getRowCells(rr).get(5).getText().trim()) > 0).
                collect(Collectors.toMap(r -> countriesTable.getRowCells(r).get(4).getText().trim(),
                        r -> Integer.parseInt(countriesTable.getRowCells(r).get(5).getText())));


       /* take a country with tz > 0, click to open country data page, check order, return back to countries page,
        take next country with tz > 0 and  click it again, etc
*/

        SoftAssert softAssertion = new SoftAssert();

        countries.entrySet().forEach( r -> {
            countriesPage.open();

            /*countriesPage.getCountriesTable().getCellByTextFromColumn(4, r.getKey()).
                    findElement(By.cssSelector("a[href]")).
                    click();*/
            countriesPage.clickCountry(r.getKey());

            CountryDataPage timeZonesPage = new CountryDataPage(driverHere);
            List<String> timeZonesList = timeZonesPage.getTimeZonesTable().getColumnsText(2);
            softAssertion.assertTrue(timeZonesList.size() == r.getValue()
                    && Ordering.natural().isOrdered(timeZonesList));
        });

        softAssertion.assertAll("Test did not pass");
    }
    @Test
    public void geoZonesAlphabetOrderTest() {

        GeoZonesPage geoZonesPage = new GeoZonesPage(driverHere);
        geoZonesPage.open();
        CommonTable countriesTable = geoZonesPage.getCountriesTable();

        List<WebElement> rows = geoZonesPage.getCountriesTable().getRows();
        //Just List of countries' names
        List<String> countries = rows.stream().map(r -> countriesTable.getRowCells(r).get(2).getText()).
                collect(Collectors.toList());

        SoftAssert softAssertion = new SoftAssert();
        //click every country and check time zones order

        countries.forEach( country -> {
            geoZonesPage.open();
           /* geoZonesPage.getCountriesTable().getCellByTextFromColumn(2,country).findElement(By.cssSelector("a[href]")).click();*/
            geoZonesPage.clickCountry(country);
            EditGeoZonesPage editGeoZonesPage = new EditGeoZonesPage(driverHere);
            List<String> timeZonesList = editGeoZonesPage.getGeoZonesTable().
                    getColumnsText(2, By.cssSelector("[selected]"));
            softAssertion.assertTrue(Ordering.natural().isOrdered(timeZonesList));
        });
        softAssertion.assertAll("Test did not pass");
    }
}