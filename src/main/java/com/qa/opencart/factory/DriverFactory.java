package com.qa.opencart.factory;

import com.qa.opencart.exceptions.BrowserException;
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

    /**
     * This method is used to initialize the browser on the basis of given browser name
     *
     * @param properties
     */
    public WebDriver initDriver(Properties properties) {
        String browserName = properties.getProperty("browser");
        System.out.println("Browser name: " + browserName);

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
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
     * @return
     */
    public Properties initProp() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
