package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @BeforeClass
    public void searchSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }


    @DataProvider
    public Object[][] getProductSearchKey() {
        return new Object[][]{
                {"airtel", 0}
        };
    }

    @Test(dataProvider = "getProductSearchKey" , description = "Search the product and validate the result count.")
    public void searchTest(String searchKey, int searchResult) {
        searchResultPage = accountsPage.doSearch(searchKey);
        int actualProductResultsCount = searchResultPage.getResultsProductCount();
        Assert.assertEquals(actualProductResultsCount, searchResult);
    }

}
