package com.qa.opencart.factory;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class DriverFactory {
    //Responsible to initialize my driver
    WebDriver driver;
    Properties properties;
    public static String highlight;
    OptionsManager optionsManager;
    public static final Logger log = LogManager.getLogger(DriverFactory.class); //WARN, INFO, ERROR, FATAL

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is used to initialize the browser on the basis of given browser name
     *
     * @param properties
     */
    public WebDriver initDriver(Properties properties) {
        log.info("Properties: " + properties);
        String browserName = properties.getProperty("browser");
        log.info("Browser Name: " + browserName);
        optionsManager = new OptionsManager(properties); //Create an object of OptionManager class
        highlight = properties.getProperty("highlight");

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                if (Boolean.parseBoolean(properties.getProperty("remote"))) {
                    //RUn on selenium grid
                    initRemoteDriver("chrome");
                } else {
                    //run it on local
                    tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                }
                break;
            case "edge":
                if (Boolean.parseBoolean(properties.getProperty("remote"))) {
                    initRemoteDriver("edge");
                } else {
                    tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                }
                break;
            case "firefox":
                if (Boolean.parseBoolean(properties.getProperty("remote"))) {
                    initRemoteDriver("firefox");
                } else {
                    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                }
                break;
            case "safari":
                if (Boolean.parseBoolean(properties.getProperty("remote"))) {
                    initRemoteDriver("safari");
                } else {
                    tlDriver.set(new SafariDriver());
                }
                break;
            default:
                log.error("Please pass the valid browser name..." + browserName);
                throw new BrowserException("===== INVALID BROWSER =======");
        }
        getDriver().get(properties.getProperty("url"));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }

    private void initRemoteDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                try {
                    tlDriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getChromeOptions()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "firefox":
                try {
                    tlDriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getFirefoxOptions()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "edge":
                try {
                    tlDriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getEdgeOptions()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("This browser is not supported on selenium grid server: " + browserName);
                throw new BrowserException("===== INVALID BROWSER =====");
        }
    }

    /**
     * getDriver: Get the local copy of threadDriver
     *
     * @return
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * This is used to initialize the config properties
     *
     * @return
     */
    public Properties initProp() {
        String envName = System.getProperty("env");   //Read the environment variable from command line
        FileInputStream fis = null;
        properties = new Properties();

        try {
            if (envName == null) {
                log.warn("env is null, hence running test on QA environment.");
                fis = new FileInputStream("./src/test/resources/config/config.properties");
            } else {
                log.info("Running test on env: {}", envName);
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "uat":
                        fis = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    case "prod":
                        fis = new FileInputStream("./src/test/resources/config/prod.config.properties");
                        break;
                    default:
                        log.error("----- Invalid Environment Name -------");
                        throw new FrameworkException("=== INVALID ENV NAME ==== :" + envName);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * TakeScreenShots
     */
    public static File getScreenshotFile() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public static byte[] getScreenshotByte() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String getScreenshotBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }


}
