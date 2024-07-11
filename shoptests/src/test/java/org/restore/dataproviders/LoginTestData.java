package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.restore.datamodels.LoginTestDataModel;
import org.restore.utils.JsonDealer;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;

public class LoginTestData {

    private static final Type type = new TypeToken<List<LoginTestDataModel>>() {
    }.getType();
    private static final String jsonArrayFile = "LoginTestData.json";

    @DataProvider(name = "loginData")
    public static Object[] getData() {
        return JsonDealer.readJson(jsonArrayFile, type).toArray();
    }
}
