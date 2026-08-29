package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private Map<String, String> productMap;
    private final By productHeader = By.tagName("h1");
    private final By productImage = By.cssSelector("ul.thumbnails img");
    private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getProductHeader() {
        String header = elementUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
        System.out.println("Product Header: " + header);
        return header;
    }

    public int getProductImagesCount() {
        int imageCount = elementUtil.waitForAllElementVisible(productImage, MEDIUM_DEFAULT_TIMEOUT).size();
        System.out.println("Product Image Count: " + imageCount);
        return imageCount;

    }

    //Having all the product detail
    public Map<String, String> getProductDetailsMap() {
        productMap = new HashMap<String, String>();
        productMap.put("productheader", getProductHeader());
        productMap.put("productimages", String.valueOf(getProductImagesCount()));
        getProductMetaData();
        getProductPriceData();
        System.out.println("Full Product Details: " + productMap);
        return productMap;
    }

    private void getProductMetaData() {
        List<WebElement> metaDataList = elementUtil.waitForAllElementVisible(productMetaData, DEFAULT_TIMEOUT);
        for (WebElement e : metaDataList) {
            String metaData = e.getText();
            String[] meta = metaData.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productMap.put(metaKey, metaValue);
        }
    }

    private void getProductPriceData() {
        List<WebElement> priceList = elementUtil.waitForAllElementVisible(productPriceData, DEFAULT_TIMEOUT);
        String productPrice = priceList.get(0).getText();
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productprice", productPrice);
        productMap.put("extaxprice", exTaxPrice);
    }


}
