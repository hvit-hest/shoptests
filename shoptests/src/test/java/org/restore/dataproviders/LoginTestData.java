package org.restore.dataproviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.restore.datamodels.LoginTestDataModel;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class LoginTestData {

    @DataProvider(name = "loginData")
    public static Object[] getData() {
        return readLoginTestDataJson("LoginTestData.json").toArray();
    }

    private static List<LoginTestDataModel> readLoginTestDataJson(String jsonArrayFile) {
        Type dataType = new TypeToken<List<LoginTestDataModel>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            //InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, dataType);
    }
}
