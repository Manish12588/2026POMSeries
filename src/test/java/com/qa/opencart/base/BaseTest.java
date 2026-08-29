package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    DriverFactory driverFactory;
    protected LoginPage loginPage;
    protected Properties properties;
    protected AccountsPage accountsPage;
    protected SearchResultPage searchResultPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;


    //Pre-condition
    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        driverFactory = new DriverFactory();
        properties = driverFactory.initProp();

        //Browser name is passed from .xml file
        if (browserName != null) {
            properties.setProperty("browser", browserName);
        }

        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    //Post-condition
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
