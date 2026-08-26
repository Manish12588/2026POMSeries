package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public String getAccountPageTitle() {
        String title = elementUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
        System.out.println("Home page title: " + title);
        return title;
    }

    public String getAccountPageUrl() {
        String url = elementUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
        System.out.println("Login page url: " + url);
        return url;
    }
}
