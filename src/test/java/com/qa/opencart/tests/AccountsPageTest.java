package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPageTest extends BaseTest {
    //Pre-condition for Account Test Page is user should be logged-in
    @BeforeClass
    public void accPageSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Owner("Manish Kumar")
    @Test(description = "Validating Title of Account Page.")
    public void accPageTitleTest() {
        Assert.assertEquals(accountsPage.getAccountPageTitle(), HOME_PAGE_TITLE);
    }

    @Owner("Manish Kumar")
    @Test(description = "Validating the URL of Account Page.")
    public void accPageUrlTest() {
        Assert.assertTrue(accountsPage.getAccountPageUrl().contains(HOME_PAGE_FRACTION_URL));
    }


    @Owner("Manish Kumar")
    @Test(description = "Validating the Headers on Account Page.")
    public void accPageHeadersTest() {
        List<String> actualHeaderList = accountsPage.getAccountsPageHeader();
        Assert.assertEquals(actualHeaderList, expectedAccPageHeaderList);
    }


}
