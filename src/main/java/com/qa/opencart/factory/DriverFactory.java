package com.qa.opencart.factory;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    //Responsible to initialize my driver
    WebDriver driver;
    Properties properties;
    public static String highlight;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is used to initialize the browser on the basis of given browser name
     *
     * @param properties
     */
    public WebDriver initDriver(Properties properties) {
        String browserName = properties.getProperty("browser");
        System.out.println("Browser name: " + browserName);
        OptionsManager optionsManager = new OptionsManager(properties); //Create an object of OptionManager class
        highlight = properties.getProperty("highlight");

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "edge":
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Please pass the valid browser name..." + browserName);
                throw new BrowserException("===== INVALID BROWSER =======");
        }
        getDriver().get(properties.getProperty("url"));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();
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
                System.out.println("env is null, hence running test on QA environment.");
                fis = new FileInputStream("./src/test/resources/config/config.properties");
            } else {
                System.out.println("Running test on env: " + envName);
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
                        System.out.println("Please pass the correct environment.");
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
