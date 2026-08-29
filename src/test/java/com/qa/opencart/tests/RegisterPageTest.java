package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

    @BeforeClass
    public void registrationSetup() {
        registerPage = loginPage.navigateToRegisterPage();
    }


    @DataProvider
    public Object[][] userRegisterTestData() {
        return new Object[][]{
                {"Manish", "Kumar", "0123456789", "Test@123", "Yes"},
                {"Gargi", "Singhwal", "0123456789", "Test@123", "Yes"},
                {"Virat", "Singh", "0123456789", "Test@123", "Yes"}
        };
    }


    //Using this method we are fetching data from excel sheet
    @DataProvider
    public Object[][] getUserRegistrationData() {
        Object regData[][] = ExcelUtil.getTestData(REGISTER_SHEET_NAME);
        return regData;
    }

    @Test(dataProvider = "getUserRegistrationData")
    public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        Assert.assertTrue(registerPage.userRegistration(firstName, lastName, telephone, password, subscribe));
    }
}
