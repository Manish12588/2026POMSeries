package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    //Locators
    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");
    private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
    private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
    private final By agreeCheckBox = By.name("agree");
    private final By continueButton = By.xpath("//input[@value='Continue']");

    private final By successMsg = By.cssSelector("div#content h1");
    private final By logoutLink = By.linkText("Logout");
    private final By registerLink = By.linkText("Register");


    public boolean userRegistration(String firstName, String lastName, String telephone, String password, String subscribe) {
        elementUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
        elementUtil.doSendKeys(this.lastName, lastName);
        elementUtil.doSendKeys(this.email, StringUtil.getRandomEmailId());
        elementUtil.doSendKeys(this.telephone, telephone);
        elementUtil.doSendKeys(this.password, password);
        elementUtil.doSendKeys(this.confirmPassword, password);
        if (subscribe.equalsIgnoreCase("Yes")) {
            elementUtil.doClick(subscribeYes);
        } else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(continueButton);

        if (elementUtil.doElementGetText(successMsg).contains(REGISTER_SUCCESS_MESSAGE)) {
            elementUtil.doClick(logoutLink);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }


}
