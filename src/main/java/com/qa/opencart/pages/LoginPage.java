package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    //1. Maintain private By Locator
    private final By email = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgotPwdLink = By.linkText("Forgotten Password");

    //2. Supply the driver: Public constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //3. Public page method/actions
    public String getLoginPageTitle() {
        String title = driver.getTitle();
        System.out.println("Login page title: " + title);
        return title;
    }

    public String getLoginPageUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Login page url: " + url);
        return url;
    }

    public boolean isForgotPasswordLinkExist() {
        return driver.findElement(forgotPwdLink).isDisplayed();
    }

    public String doLogin(String uname, String pwd) throws InterruptedException {
        System.out.println("user credentials: " + uname + " : " + pwd);
        driver.findElement(email).sendKeys(uname);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginBtn).click();
        Thread.sleep(2000);    //Added Hard coded sleep will need to remove this later on
        String title = driver.getTitle();
        System.out.println("Account page title: " + title);
        return title;
    }


}
