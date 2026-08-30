package com.qa.opencart.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;

@Epic("Epic 100: Design pages for open cart application")
@Feature("F 01: Open Cart - Login Feature")
@Story("US 101: Implement login page for open cart application")

public class LoginPageTest extends BaseTest {
    //Here we will be writing testng code

    @Description("Checking Open Cart Login Page Title...")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish Kumar")
    @Test(description = "Checking Login Page Title")
    public void loginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        ChainTestListener.log("Checking Login Page Title: " + title);  //To generate log using ChainTestListener
        Assert.assertEquals(title, LOGIN_PAGE_TITLE);
    }

    @Description("Checking Open Cart Login Page Url...")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Manish Kumar")
    @Test(description = "Checking Login Page URL")
    public void loginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_FRACTION_URL));
    }

    @Description("Checking Open Cart Login Page Has Forgot Password Link...")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Manish Kumar")
    @Test(description = "Validating Forgot Password Link Existence")
    public void forgotPasswordLinkExistsTest() {
        Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
    }


    @Description("Check User is able to login with valid user credentials...")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Manish Kumar")
    @Test(priority = Short.MAX_VALUE, description = "Validating Successful Login")
    public void loginTest() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccountPageTitle(), HOME_PAGE_TITLE);
    }

}
