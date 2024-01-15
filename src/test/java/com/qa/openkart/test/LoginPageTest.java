package com.qa.openkart.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openkart.listners.TestAllureListener;
import com.qa.openkart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic Id: 100:  Design  Open  Cart  Apps -- Login Page ")
@Story("US 101:  Open Cart  Login  Design with  multiple features.")
@Listeners(TestAllureListener.class)

public class LoginPageTest extends BaseTest{

	@Description("loginPageTitleTest")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String acttitle = lppage.getLoginPageTitleTest();
		System.out.println("Page title is : "+acttitle);
		Assert.assertEquals(acttitle, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("loginPageUrlTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(lppage.getLoginPageUrl());
	}
	@Description("forgotPwdLinkTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(lppage.isForgotPwdLinkExst());
	}
	@Description("RegisterLinkTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void RegisterLinkTest() {
		Assert.assertTrue(lppage.isRegsterLinkExst());
	}
	@Description("Login Test with Valid Credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accpage=lppage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	   Assert.assertEquals(accpage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
}
