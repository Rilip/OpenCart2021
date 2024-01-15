	package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.Constants;
import com.qa.openkart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By firstname=By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmpassword=By.id("input-confirm");
	
	private By subsribeYes=By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subsribeNo=By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
    
	private By checkbox=By.name("agree");
	private By continuebutton=By.xpath("//input[@value=\"Continue\"]");
	private By successmsg=By.cssSelector("div#content h1");       //("//div[@id=\"content\"]/h1"); //div[@id='content']/h1
	
	private By logoutlink=By.linkText("Logout");
	private By registerlink=By.linkText("Register");
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	public void registertitle() {
		eleutil.doGetTitleWithFraction(Constants.REGISTRATION_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	public boolean accountRegistration(String firstname,String lastname,String emailid
			,String telephone,String password,String subscribe) {
		
		eleutil.doSendKeys(this.firstname, firstname);
		eleutil.doSendKeys(this.lastname, lastname);
		eleutil.doSendKeys(this.email, emailid);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equals("Yes")) {
			eleutil.doClick(subsribeYes);
		}else {
			eleutil.doClick(subsribeNo);
		}
		
		eleutil.doClick(checkbox);
		eleutil.doClick(continuebutton);
		String msg=eleutil.waitForElementToBeVisible(successmsg, 35, 1000).getText();
		
		if(msg.contains(Constants.REGISTER_SUCC_MSG)) {
			eleutil.doClick(logoutlink);
			eleutil.doClick(registerlink);
			return true;
		}
		return false;
	}
}
