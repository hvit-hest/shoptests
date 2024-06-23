package org.restore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TestProperties {

    private static String testConfigPropertiesPath = "testconfig.properties";
    private static final Properties testProperties = null;

    private static Properties getProperties() {
        if (testProperties != null)
            return testProperties;
        else
            return readProperties(testConfigPropertiesPath);
    }

    private static Properties readProperties(String filePath) {
        Properties prop = new Properties();
        //try (InputStream resourceAsStream = TestProperties.class.getClass().getClassLoader().getResourceAsStream(filePath))
        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath))
        {
            prop.load(resourceAsStream);
        } catch (IOException ioe) {
            System.err.println("Not able to load properties file: " + filePath);
        }
        return prop;
    }

    public void printTestingProperties() {
        getProperties().forEach((k, v) -> System.out.println(k + " " + v));
    }

    public static String getUserPasswordFromProperties() {
        return getProperties().getProperty("userPassword");
    }

    public static String getUserEmailFromProperties() {
        return getProperties().getProperty("userEmail");
    }

    public static String getAdminNameFromProperties() {
        return getProperties().getProperty("adminName");
    }

    public static String getAdminPasswordFromProperties() {
        return getProperties().getProperty("adminPassword");
    }

    public static int getImplicitWaitFromProperties() {
        return Integer.parseInt(getProperties().getProperty("implicitWait"));
    }

    public static String getWebDriverNameFromProperties() {
        return getProperties().getProperty("driver");
    }

    public static String getRemoteIpFromProperties() {
        return getProperties().getProperty("remote_url");
    }
}
