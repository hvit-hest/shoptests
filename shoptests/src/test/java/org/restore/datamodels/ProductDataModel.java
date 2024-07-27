package org.restore.datamodels;

import java.util.LinkedHashMap;

public class ProductDataModel {
    private GeneralDataProductModel generalData;
    private InformationDataProductModel informationData;
    private PricesDataProductModel pricesData;

    public LinkedHashMap<String, Object> getGeneralData() {
        return generalData.getGeneralData();
    }
    public LinkedHashMap<String, String> getInformationData() {
        return informationData.getInformationData();
    }
    public LinkedHashMap<String, String> getPricesData() {return pricesData.getPricesData(); }
}
