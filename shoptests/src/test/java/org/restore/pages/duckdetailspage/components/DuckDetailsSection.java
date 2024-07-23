package org.restore.pages.duckdetailspage.components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;

public class DuckDetailsSection {

    private WebElement rootDuckDetailsWe;
    private By duckRegularPriceBy = By.cssSelector(".regular-price,.price");
    private By duckCampaignPriceBy = By.cssSelector(".campaign-price");


    public DuckDetailsSection(WebElement rootDuckDetailsWe) {
        this.rootDuckDetailsWe = rootDuckDetailsWe;
    }


    public HashMap<String, String> getDuckData() {
        HashMap<String, String> DuckDetails = new HashMap<String, String>();
        WebElement regularPriceWe = rootDuckDetailsWe.findElement(duckRegularPriceBy);

        DuckDetails.put("regularPrice", regularPriceWe.getText());
        DuckDetails.put("regularPriceColor", regularPriceWe.getCssValue("color"));
        DuckDetails.put("regularPriceDecoration", regularPriceWe.getCssValue("text-decoration"));
        DuckDetails.put("regularPriceFontWeight", regularPriceWe.getCssValue("font-weight"));
        DuckDetails.put("regularPriceFontSize", regularPriceWe.getCssValue("font-size"));

        List<WebElement> campaignPriceWEs = rootDuckDetailsWe.findElements(duckCampaignPriceBy);

        if (campaignPriceWEs.size() > 0) {
            DuckDetails.put("campaignPrice", campaignPriceWEs.get(0).getText());
            DuckDetails.put("campaignPriceColor", campaignPriceWEs.get(0).getCssValue("color"));
            DuckDetails.put("campaignPriceDecoration", campaignPriceWEs.get(0).getCssValue("text-decoration"));
            DuckDetails.put("campaignPriceFontWeight", campaignPriceWEs.get(0).getCssValue("font-weight"));
            DuckDetails.put("campaignPriceFontSize", campaignPriceWEs.get(0).getCssValue("font-size"));
        } else {
            DuckDetails.put("campaignPrice","");
            DuckDetails.put("campaignPriceColor", "");
            DuckDetails.put("campaignPriceDecoration","");
            DuckDetails.put("campaignPriceFontWeight", "");
            DuckDetails.put("campaignPriceFontSize", "");
        }

        return DuckDetails;
    }
}
