package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoSetup() {
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductTestData() {
        return new Object[][]{
                {"macbook", "MacBook Pro"},
                {"macbook", "MacBook Air"},
                {"imac", "iMac"},
                {"samsung", "Samsung SyncMaster 941BW"},
                {"samsung", "Samsung Galaxy Tab 10.1"}
        };
    }

    @Test(dataProvider = "getProductTestData")
    public void productHeaderTest(String searchKey, String productName) {
        searchResultPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultPage.selectProduct(productName);
        String actualHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actualHeader, productName);
    }

    @DataProvider
    public Object[][] getProductImageTestData() {
        return new Object[][]{
                {"macbook", "MacBook Pro", 4},
                {"macbook", "MacBook Air", 4},
                {"imac", "iMac", 3},
                {"samsung", "Samsung SyncMaster 941BW", 1},
                {"samsung", "Samsung Galaxy Tab 10.1", 7}
        };
    }

    @Test(dataProvider = "getProductImageTestData")
    public void getProductImageCountTest(String searchKey, String productName, int expectedImageCount) {
        searchResultPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultPage.selectProduct(productName);
        int actualImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(actualImageCount, expectedImageCount);
    }

    @Test
    public void getProductInformationTest() {
        searchResultPage = accountsPage.doSearch("macbook");
        productInfoPage = searchResultPage.selectProduct("MacBook Pro");
        Map<String, String> actualProductMap = productInfoPage.getProductDetailsMap();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductMap.get("Brand"), "Apple");
        softAssert.assertEquals(actualProductMap.get("Product Code"), "Product 18");
        softAssert.assertEquals(actualProductMap.get("Availability"), "Out Of Stock");
        softAssert.assertEquals(actualProductMap.get("productprice"), "$2,000.00");
        softAssert.assertAll();
    }

}
