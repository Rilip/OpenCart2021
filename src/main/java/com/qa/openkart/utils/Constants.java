package com.qa.openkart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Constants {

	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	
	public static final String ACCOUNT_PAGE_HEADER="";

	public static final String LOGIN_PAGE_URL_FRACTION = "account/login";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int IMAC_IMAGES_COUNT = 3;
	public static final int MACKBOOKPRO_IMAGES_COUNT = 4;
	public static final int MACBOOKAIR_IMAGES_COUNT = 4;
	public static final int APPLE_IMAGES_COUNT =6;
	public static final int SAMSUNG_IMAGES_COUNT =6;
	public static final String LOGIN_ERROR_MSG = "No match for E-Mail Address and/or Password";
	public static final String REGISTRATION_TITLE = "Register Account";
	public static final String REGISTER_SUCC_MSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "registrationsheet  ";  
	
	
	public static List<String> getExpectedAccSecList() {
		List<String>expectedSecList=new ArrayList<String>();
		expectedSecList.add("My Account");
		expectedSecList.add("My Orders");
		expectedSecList.add("My Affiliate Account");
		expectedSecList.add("Newsletter");
		return expectedSecList;
	}
}
