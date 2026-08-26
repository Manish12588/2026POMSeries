package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPageTest extends BaseTest {
    //Pre-condition for Account Test Page is user should be logged-in

    @BeforeClass
    public void accPageSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void accPageTitleTest() {
        Assert.assertEquals(accountsPage.getAccountPageTitle(), HOME_PAGE_TITLE);
    }

    @Test
    public void accPageUrlTest() {
        Assert.assertTrue(accountsPage.getAccountPageUrl().contains(HOME_PAGE_FRACTION_URL));
    }

}
