package com.qa.opencart.factory;

import com.qa.opencart.exceptions.BrowserException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    //Responsible to initialize my driver
    WebDriver driver;

    /**
     * This method is used to initialize the browser on the basis of given browser name
     *
     * @param browserName
     */
    public WebDriver initDriver(String browserName) {
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
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }
}
