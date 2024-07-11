package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.restore.datamodels.AdminMenuTestDataModel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;

import static org.restore.utils.JsonDealer.readJson;


public class AdminMenuTestData {

    private static final String jsonArrayFile = "AdminMenuTestData.json";
    private final Type type = new TypeToken<List<AdminMenuTestDataModel>>() {
    }.getType();

    @DataProvider(name = "adminMenuTestData")
    public Object[] getData() {
        return readJson(jsonArrayFile, type).toArray();
    }
}