package org.restore.dataproviders;

import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.restore.utils.JsonDealer.readJson;

public class AddWindowTestData {

        private static final String jsonArrayFile = "AddWindowTest.json";
        private final Type type = new TypeToken<List<Map<String,String>>>() {
        }.getType();

        @DataProvider(name = "addWindowTestData")
        public Object[] getData() {
            return readJson(jsonArrayFile, type).toArray();
        }
}
