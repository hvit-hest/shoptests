package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.datamodels.ProductDataModel;
import org.restore.dataproviders.AddProductTestData;
import org.restore.pages.addnewproductpage.AddNewProductPage;
import org.restore.pages.addnewproductpage.components.NewProductTabs;
import org.restore.pages.adminpage.AdminPage;
import org.restore.pages.catalogpage.CatalogPage;
import org.restore.utils.TestProperties;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

public class AddProductTest extends BaseTest {

    private final String adminLogin = TestProperties.getAdminNameFromProperties();
    private final String adminPassword = TestProperties.getAdminPasswordFromProperties();
    private WebDriver myPersonalDriver;
    private LinkedHashMap<String, String> informationData;
    private LinkedHashMap<String, Object> generalData;
    private LinkedHashMap<String, String> pricesData;

    @BeforeClass
    public void beforeClass() {

        myPersonalDriver = getWebDriver();
        AdminPage adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminLogin, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Catalog");
    }

    @Test(dataProvider = "addProductTestData", dataProviderClass = AddProductTestData.class)
    public void addProductTest(ProductDataModel productData) {
        generalData = productData.getGeneralData();
        informationData = productData.getInformationData();
        pricesData = productData.getPricesData();
        CatalogPage catalogPage = new CatalogPage(myPersonalDriver);
        catalogPage.clickAddNewProductButton();
        AddNewProductPage addNewProductPage = new AddNewProductPage(myPersonalDriver);
        //explicit wait in all 'fill forms' methods was used before putting data into forms
        addNewProductPage.getGeneralSection().fillFormProductGeneral(generalData);
        addNewProductPage.openTab(NewProductTabs.INFORMATION);
        addNewProductPage.getInformationSection().fillFormProductInformation(informationData);
        addNewProductPage.openTab(NewProductTabs.PRICES);
        addNewProductPage.getPricesSection().fillFormProductPrices(pricesData);
        addNewProductPage.clickButtonSave();
        catalogPage = new CatalogPage(myPersonalDriver);

        Assert.assertTrue(catalogPage.getCatalogTable().
                        getCellsByText((String) generalData.get("name")).size() > 0,
                String.format("Duck '%s' is not found", generalData.get("name")));
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}
