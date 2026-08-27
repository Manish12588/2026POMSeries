package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private final By productHeader = By.tagName("h1");
    private final By productImage = By.cssSelector("ul.thumbnails img");

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


}
