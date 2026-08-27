package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @BeforeClass
    public void searchSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void searchTest() {
        searchResultPage = accountsPage.doSearch("airtel");
        int actualProductResultsCount = searchResultPage.getResultsProductCount();
        Assert.assertEquals(actualProductResultsCount, 0);
    }

}
