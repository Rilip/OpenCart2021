package com.qa.openkart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.openkart.factory.DriverFactory;
import com.qa.openkart.pages.AccountsPage;
import com.qa.openkart.pages.LoginPage;
import com.qa.openkart.pages.ProductInfoPage;
import com.qa.openkart.pages.RegistrationPage;
import com.qa.openkart.pages.SearchResultPage;

public class BaseTest {

	
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	LoginPage lppage;
	AccountsPage accpage;
	SearchResultPage searcrstpge;
	ProductInfoPage prdinfopge;
	RegistrationPage registrationPage;
	SoftAssert softassert;
	
	
	
	@BeforeTest
	public void setup() {
	 df=new DriverFactory();
	 prop=df.init_prop();
	 driver=df.init_driver(prop);
	 lppage=new LoginPage(driver);
	 softassert= new SoftAssert();
	 
	}
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(300);
	driver.quit();	
	}
	
}
