package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.restore.datamodels.AdminMenuTestDataModel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;

import static org.restore.utils.JsonDealer.readJson;


public class AddProductTestData {

    private static final String jsonArrayFile = "ProductForm.json";
    private final Type type = new TypeToken<List<AdminMenuTestDataModel>>() {
    }.getType();

    @DataProvider(name = "addProductTestData")
    public Object[] getData() {
        return readJson(jsonArrayFile, type).toArray();
    }
}