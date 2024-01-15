package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.Constants;
import com.qa.openkart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	//1
	private WebDriver driver;
	private ElementUtil eleutil;
	//2
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	//3
	private By emailid=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By loginErrorMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//4 . Page Action
	@Step("getting Login Page Title ....")
	public String getLoginPageTitleTest() {
	return eleutil.doGetTitleWithFraction(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	@Step("getting Login Page URL ....")
	public boolean getLoginPageUrl() {
		return eleutil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	@Step("Checking Forgot Password link exist or not ....")
	public boolean isForgotPwdLinkExst() {
		return  eleutil.doIsDisplayed(forgotPwdLink);
	}
	@Step("Checking Register link exist or not ....")
	public boolean isRegsterLinkExst() {
		return eleutil.doIsDisplayed(registerLink);
	}
	@Step("Login with username (0) password (1)")
	public AccountsPage dologin(String un,String pwd) {
		System.out.println("login with : "+un +":"+pwd);
		eleutil.doSendKeys(emailid, un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	@Step("Login with wrong username (0) wrong password (1)")
	public boolean doLoginwithWrongCredentials(String un,String pwd) {
		System.out.println("Try to login with wrong credentials: "+un +":"+pwd);
		eleutil.doSendKeys(emailid, un);
		eleutil.doSendKeys(emailid, un);
		eleutil.doClick(loginbtn);
		
		String errormsg=eleutil.doGetText(loginErrorMsg);
		System.out.println(errormsg);
		if(errormsg.contains(Constants.LOGIN_ERROR_MSG)) {
			System.out.println("Loging is not Succefully...");	
			return false;
		}
		return true;
	}
	@Step("Navigating to Registeration Page...")
	public RegistrationPage gotoRegisterPage() {
	   eleutil.doClick(registerLink);
	   return new RegistrationPage(driver);
	}
	
}

//scoop install allure
 