package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.restore.datamodels.AdminMenuTestDataModel;
import org.restore.utils.JsonDealer;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;


public class AdminMenuTestData {

    private Type type = new TypeToken<List<AdminMenuTestDataModel>>() {}.getType();
    private static String jsonArrayFile = "AdminMenuTestData.json";

    @DataProvider(name = "adminMenuTestData")
    public Object[] getData() {
        return JsonDealer.readJson(jsonArrayFile, type).toArray();
    }
}