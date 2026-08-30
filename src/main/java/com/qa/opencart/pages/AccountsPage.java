package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger log = LogManager.getLogger(AccountsPage.class);

    private final By headers = By.cssSelector("div#content > h2");
    private final By search = By.name("search");
    private final By searchIcon = By.cssSelector("div#search button");


    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public String getAccountPageTitle() {
        String title = elementUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
        log.info("Home page title: " + title);
        return title;
    }

    public String getAccountPageUrl() {
        String url = elementUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
        log.info("Login page url: " + url);
        return url;
    }

    public List<String> getAccountsPageHeader() {
        List<WebElement> headerList = elementUtil.getElements(headers);
        List<String> headerValueList = new ArrayList<>();
        for (WebElement e : headerList) {
            String text = e.getText();
            headerValueList.add(text);
        }
        log.info("Account Page Header List: {}", headerValueList);
        return headerValueList;
    }

    public SearchResultPage doSearch(String searchKey) {
        log.info("Search Key: {}", searchKey);
        elementUtil.doSendKeys(search, searchKey);
        elementUtil.doClick(searchIcon);
        return new SearchResultPage(driver);
    }


}
