package com.qa.opencart.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.LogUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

//@Listeners(ChainTestListener.class) //If you dont want to use Listener annotation here then you can add this in runner xml file.
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

    @AfterMethod  //It will run after each @Test method (In case of failure test it will attached screenshot)
    public void attachScreenshot(ITestResult result) {
        if (!result.isSuccess()) { //Only run for failure test case
            ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
        }
    }

    //Post-condition
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
