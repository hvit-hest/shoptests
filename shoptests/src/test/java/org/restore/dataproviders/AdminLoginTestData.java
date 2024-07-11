package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.restore.datamodels.AdminLoginTestDataModel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;

import static org.restore.utils.JsonDealer.readJson;

public class AdminLoginTestData {

    private static final Type type = new TypeToken<List<AdminLoginTestDataModel>>() {
    }.getType();
    private static final String jsonArrayFile = "AdminLoginTestData.json";

    @DataProvider(name = "loginData")
    public static Object[] getData() {
        return readJson(jsonArrayFile, type).toArray();
    }
}
