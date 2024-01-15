package com.qa.openkart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productheadername=By.xpath("//div[@id=\"content\"]//h1");
	private By productimages=By.cssSelector("ul.thumbnails img");
	////div[@id="content"]//ul[@class="list-unstyled"]
	private By productmetadata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1)");
	private By productpricedata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2)");
	private By qty=By.id("input-quantity");
	private By addtocartbtn=By.id("button-cart");
	
	private Map<String , String> productinfomap;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String prodheadertext = eleutil.doGetText(productheadername);
		System.err.println("Poduct header is : "+prodheadertext);
		return prodheadertext;
	}
	public int getProductImsgesCount() {
		return eleutil.waitForElementsToBeVisible(productimages, 20).size();
	}
	public Map<String, String> getProductInfo() {
		//productinfomap=new HashMap<String,String>();//Print based on Hash code
	      productinfomap=new LinkedHashMap<String,String>();
		//productinfomap=new TreeMap<String,String>(); //sorted order
		productinfomap.put("name", getProductHeader());
		getProductMetaData();
		getProductPricingData();
		return productinfomap;
		
	}
	private void getProductMetaData() {
		List<WebElement> metadatalist = eleutil.getElements(productmetadata);
		/*
		 * Brand: Apple
           Product Code: Product 18
           Reward Points: 800
           Availability: In Stock
		 */
		for(WebElement e:metadatalist) {
			String text = e.getText();
			String meta[]=text.split(":");
			String metkey = meta[0].trim();
			String metaval = meta[1].trim();
			productinfomap.put(metkey, metaval);
		}
	}
	private void getProductPricingData() {
		List<WebElement> metapricelist = eleutil.getElements(productpricedata);
		/*$2,000.00
         Ex Tax: $2,000.00**/
		String price = metapricelist.get(0).getText().trim();
		String extprice = metapricelist.get(0).getText().trim();
		productinfomap.put("Price", price);
		productinfomap.put("ExtPrice", extprice);
	}
	
}
