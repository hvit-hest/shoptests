package org.restore.utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDealer {

    public static List<?> readJson (String jsonFile, Type type) {
        Gson gson = new Gson();
        com.google.gson.stream.JsonReader reader = null;
        try {
            //InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream (jsonFile);
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(jsonFile);
            reader = new com.google.gson.stream.JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file " + jsonFile);
        }
        return gson.fromJson(reader, type);
    }
}
