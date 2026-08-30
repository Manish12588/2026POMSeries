package com.qa.opencart.factory;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    //Responsible to initialize my driver
    WebDriver driver;
    Properties properties;
    public static String highlight;

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
                driver = new ChromeDriver(optionsManager.getChromeOptions());
                break;
            case "edge":
                driver = new EdgeDriver(optionsManager.getEdgeOptions());
                break;
            case "firefox":
                driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Please pass the valid browser name..." + browserName);
                throw new BrowserException("===== INVALID BROWSER=======");
        }
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
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
}
