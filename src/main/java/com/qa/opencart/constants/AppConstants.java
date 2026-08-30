package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
    public static final String LOGIN_PAGE_TITLE = "Account Login11";
    public static final String HOME_PAGE_TITLE = "My Account";
    public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
    public static final String HOME_PAGE_FRACTION_URL = "route=account/account";

    public static final int DEFAULT_TIMEOUT = 5;
    public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
    public static final int LONG_DEFAULT_TIMEOUT = 15;

    public static List<String> expectedAccPageHeaderList = List.of("My Account","My Orders","My Affiliate Account","Newsletter");

    public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";

    //********** SHEET NAMES *****************//
    public static final String REGISTER_SHEET_NAME = "Register";
    public static final String PRODUCT_SHEET_NAME = "Product";



}
