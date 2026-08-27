package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void productHeaderTest() {
        searchResultPage = accountsPage.doSearch("macbook");
        productInfoPage = searchResultPage.selectProduct("MacBook Pro");
        String actualHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actualHeader, "MacBook Pro");
    }

    @Test
    public void getProductImageCountTest(){
        searchResultPage = accountsPage.doSearch("macbook");
        productInfoPage = searchResultPage.selectProduct("MacBook Pro");
        int actualImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(actualImageCount,4);
    }


}
