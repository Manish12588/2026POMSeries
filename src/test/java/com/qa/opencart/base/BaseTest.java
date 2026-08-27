package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    DriverFactory driverFactory;
    protected LoginPage loginPage;
    protected Properties properties;
    protected AccountsPage accountsPage;
    protected SearchResultPage searchResultPage;
    protected ProductInfoPage productInfoPage;



    //Pre-condition
    @BeforeTest
    public void setup() {
        driverFactory = new DriverFactory();
        properties = driverFactory.initProp();
        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    //Post-condition
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
