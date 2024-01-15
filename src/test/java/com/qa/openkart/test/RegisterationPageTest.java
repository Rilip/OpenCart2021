package com.qa.openkart.test;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterationPageTest extends BaseTest {

	@BeforeClass
	public void setupRegistration() {
		registrationPage=lppage.gotoRegisterPage();
	}
	@Test()
	public void titletest() {
		registrationPage.registertitle();
	}
	
	@Test()
	public void userRegistrationTest() {
		registrationPage.accountRegistration("Praseanth", "LeN", getRandomEmail() , getRandomPhonenum(), "pr@89", "Yes");
	}
	public String getRandomEmail() {
		Random randomgenerator=new Random();
		String email= "automation"+randomgenerator.nextInt(10000)+"@gmail.com";
		return email;
	}
	public String getRandomPhonenum() {
		Random randomgene=new Random();
		String phonen = "987574"+randomgene.nextInt(10000);
		return phonen;
	}
//	@DataProvider
//	public Object[][] getRegisterData() {
//		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
//	}
//	@Test(dataProvider = "getRegisterData")
//	public void userRegistrationTest(String firstname,String lastname,String emailid
//			                         ,String telephone,String password,String subscribe)  {
//		Assert.assertTrue(registrationPage.accountRegistration( firstname, lastname, emailid , telephone, password, subscribe));
//	}
//	
}
