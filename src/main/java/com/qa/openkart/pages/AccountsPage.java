package com.qa.openkart.pages;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.Constants;
import com.qa.openkart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By header=By.cssSelector("div#logo a");
	private By Accountsheadersection=By.cssSelector("div#content h2");
	private By SearchField=By.name("search");
	private By Searchbtn=By.cssSelector("div#search button");
	private By logoutlink=By.linkText("Logout");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	public String getAccountPageTitle() {
		return eleutil.doGetTitleWithFraction(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	public String getAccountPageHeaderTest() {
		return eleutil.doGetText(header);
	}
	public boolean isLogoutLinkExist() {
		return eleutil.doIsDisplayed(logoutlink);
	}
	public void logout() {
		if(isLogoutLinkExist()) {
			eleutil.doClick(logoutlink);
		}
	}
	public List<String> getAccountSecList() {
		List<WebElement> accSecList = eleutil.waitForElementsToBeVisible(Accountsheadersection, 10);
		List<String>accSecValueList=new ArrayList<String>();  //blanklist
		for(WebElement e:accSecList) {
			String text = e.getText();
			accSecValueList.add(text);
		}
		return accSecValueList; 
	}
	public boolean isSearchExists() {
		return eleutil.doIsDisplayed(SearchField);
	}
	public SearchResultPage doSearch(String productname) {
		System.out.println("Searching the product : "+productname);
		eleutil.doSendKeys(SearchField, productname);
		eleutil.doClick(Searchbtn);
		return new SearchResultPage(driver);   //Test Driven Approach
		
	}
}
