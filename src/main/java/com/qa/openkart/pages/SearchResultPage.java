package com.qa.openkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productresult=By.cssSelector("div.caption a");
	
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	public int getProductListCount() {
		int resultcount = eleutil.waitForElementsToBeVisible(productresult, 10, 2000).size();
		System.out.println("the search product count : "+resultcount);
		return resultcount;
	}
	
	public ProductInfoPage selectProduct(String mainproductname) {
		System.out.println("Main Product Name is: "+mainproductname);
		List<WebElement>searchlist = eleutil.waitForElementsToBeVisible(productresult, 10, 2000);
		for(WebElement e:searchlist) {
			String text=e.getText();
			if(text.equals(mainproductname)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
