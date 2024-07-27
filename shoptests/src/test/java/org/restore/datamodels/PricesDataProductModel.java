package org.restore.datamodels;

import java.util.LinkedHashMap;

public class PricesDataProductModel {
    private String purchasePrice;
    private String currency;
    private String taxClass;
    private String priceUSD;
    private String priceEURO;

    public LinkedHashMap<String, String> getPricesData() {
        return new LinkedHashMap<String, String>() {{
            put("purchasePrice", purchasePrice);
            put("currency", currency);
            put("taxClass", taxClass);
            put("priceUSD", priceUSD);
            put("priceEURO", priceEURO);
        }};
    }
}
