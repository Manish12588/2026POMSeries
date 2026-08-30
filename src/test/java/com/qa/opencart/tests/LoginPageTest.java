package com.qa.opencart.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPageTest extends BaseTest {
    //Here we will be writing testng code

    @Test(description = "Checking Login Page Title")
    public void loginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        ChainTestListener.log("Checking Login Page Title: " + title);  //To generate log using ChainTestListener
        Assert.assertEquals(title, LOGIN_PAGE_TITLE);
    }

    @Test(description = "Checking Login Page URL")
    public void loginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_FRACTION_URL));
    }

    @Test(description = "Validating Forgot Password Link Existence")
    public void forgotPasswordLinkExistsTest() {
        Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
    }

    @Test(priority = Short.MAX_VALUE, description = "Validating Successful Login")
    public void loginTest() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccountPageTitle(), HOME_PAGE_TITLE);
    }

}
