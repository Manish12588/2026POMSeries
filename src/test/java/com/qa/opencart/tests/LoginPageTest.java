package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    //Here we will be writing testng code

    @Test
    public void loginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title, "Account Login");
    }

    @Test
    public void loginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actualUrl.contains("route=account/login"));
    }

    @Test
    public void forgotPasswordLinkExistsTest() {
        Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
    }

    @Test (priority = Short.MAX_VALUE)
    public void loginTest() throws InterruptedException {
        String title = loginPage.doLogin("manishkumar@gmail.com", "Automation@123");
        Assert.assertEquals(title, "My Account");
    }

}
