package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private final By resultsProduct = By.cssSelector("div.product-thumb");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public int getResultsProductCount() {
        int searchCount = elementUtil.waitForAllElementVisible(resultsProduct, MEDIUM_DEFAULT_TIMEOUT).size();
        System.out.println("Total number of search products: " + searchCount);
        return searchCount;
    }

    public ProductInfoPage selectProduct(String productName){
        System.out.println("Product Name is: "+productName);
        elementUtil.doClick(By.linkText(productName));
        return new ProductInfoPage(driver);
    }
}
