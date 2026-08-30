package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    //1. Maintain private By Locator
    private final By email = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgotPwdLink = By.linkText("Forgotten Password");
    private final By registerLink = By.linkText("Register");

    //2. Supply the driver: Public constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    //3. Public page method/actions
    @Step("Getting Login Page Title")
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
        System.out.println("Login page title: " + title);
        return title;
    }

    @Step("Getting Login Page URL")
    public String getLoginPageUrl() {
        String url = elementUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
        System.out.println("Login page url: " + url);
        return url;
    }


    @Step("Checking Forgot Password Link Exist.")
    public boolean isForgotPasswordLinkExist() {
        return elementUtil.isElementDisplayed(forgotPwdLink);
    }

    @Step("Login With Valid Username: {0} and Password: {1}")
    public AccountsPage doLogin(String uname, String pwd) {
        System.out.println("user credentials: " + uname + " : " + pwd);
        elementUtil.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(uname);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }

    @Step("Navigating to the user register page")
    public RegisterPage navigateToRegisterPage() {
        elementUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
        return new RegisterPage(driver);
    }


}
