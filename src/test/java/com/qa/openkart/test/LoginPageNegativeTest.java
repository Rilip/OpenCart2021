package com.qa.openkart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginwrongtestdata() {
		return new Object[][] {
			{"test12@gmail.ocm","test@123"},
			{"dilipkumarnaidu5@gmail.com","test@123"},
			{"test12@gmail.ocm","12345"},
			{" ","12345"},
		};
	}
	
	@Test(dataProvider = "loginwrongtestdata")
	public void loginnegativetest(String username,String password) {
	Assert.assertFalse(lppage.doLoginwithWrongCredentials(username, password));
	}
}
