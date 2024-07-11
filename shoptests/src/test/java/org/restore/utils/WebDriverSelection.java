package org.restore.utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class WebDriverSelection {

    private final String webDriverName = TestProperties.getWebDriverNameFromProperties();
    private final String remoteIp = TestProperties.getRemoteIpFromProperties();

    public WebDriver getDriverFromProperties() {
        WebDriver myPersonalDriver = null;
        switch (webDriverName.toLowerCase()) {
            case "firefox":
                myPersonalDriver = new FirefoxDriver();
                break;
            case "chrome":
                myPersonalDriver = new ChromeDriver();
                break;
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                //options.setBinary("C://Users//AlexSh//AppData//Local//Yandex//YandexBrowser//Application//browser.exe");
                System.setProperty("webdriver.chrome.driver", "C://WebDrivers//yandexdriver.exe");
                myPersonalDriver = new ChromeDriver(options);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C://WebDrivers//edgedriver.exe");
                myPersonalDriver = new EdgeDriver();
                break;
            case "remote_chrome":
                try {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    myPersonalDriver = new RemoteWebDriver(new URL(remoteIp), chromeOptions);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "remote_firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    myPersonalDriver = new RemoteWebDriver(new URL(remoteIp), firefoxOptions);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "chrome_saucelab":
                try {

                    MutableCapabilities sauceOpts = new MutableCapabilities();
                    /** In order to use w3c you must set the seleniumVersion **/
                    sauceOpts.setCapability("seleniumVersion", "3.141.59");
                    sauceOpts.setCapability("name", "my name");

                    /**
                     * in this exercise we set additional capabilities below that align with
                     * testing best practices such as tags, timeouts, and build name/numbers.
                     *
                     * Tags are an excellent way to control and filter your test automation
                     * in Sauce Analytics. Get a better view into your test automation.
                     */
                    List<String> tags = Arrays.asList("sauceDemo", "demoTest", "moduleXXX", "javaTest");
                    sauceOpts.setCapability("tags", tags);
                    /** Another of the most important things that you can do to get started
                     * is to set timeout capabilities for Sauce based on your organizations needs. For example:
                     * How long is the whole test allowed to run?*/
                    sauceOpts.setCapability("maxDuration", 3600);
                    /** A Selenium crash might cause a session to hang indefinitely.
                     * Below is the max time allowed to wait for a Selenium command*/
                    sauceOpts.setCapability("commandTimeout", 600);
                    /** How long can the browser wait for a new command */
                    sauceOpts.setCapability("idleTimeout", 1000);

                    /** Setting a build name is one of the most fundamental pieces of running
                     * successful test automation. Builds will gather all of your tests into a single
                     * 'test suite' that you can analyze for results.
                     * It's a best practice to always group your tests into builds. */
                    sauceOpts.setCapability("build", "Onboarding Sample App - Java-TestNG");
                    ChromeOptions chromeOpts = new ChromeOptions();
                    chromeOpts.setExperimentalOption("w3c", true);

                    /** Set a second MutableCapabilities object to pass Sauce Options and Chrome Options **/
                    MutableCapabilities capabilities = new MutableCapabilities();
                    capabilities.setCapability("sauce:options", sauceOpts);
                    capabilities.setCapability("goog:chromeOptions", chromeOpts);
                    capabilities.setCapability("browserName", "chrome");
                    capabilities.setCapability("platformName", "Win10");
                    capabilities.setCapability("browserVersion", "latest");

                    myPersonalDriver = new RemoteWebDriver(new URL(remoteIp), capabilities);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "proxy_chrome":
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("localhost:8888");
                /*DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("proxy", proxy);
                myPersonalDriver = new ChromeDriver(caps);*/

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("proxy", proxy);
                myPersonalDriver = new ChromeDriver(chromeOptions);

                break;
            default:
                Assert.fail("Error! Check your browser's type");
        }
        return myPersonalDriver;
    }
}
