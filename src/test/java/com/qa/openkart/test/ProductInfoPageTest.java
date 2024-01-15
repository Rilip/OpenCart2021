package com.qa.openkart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void PridInfoSetup() {
		accpage=lppage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productheaderTest() throws InterruptedException {
		searcrstpge=accpage.doSearch("Macbook");
		Thread.sleep(3000);
		prdinfopge= searcrstpge.selectProduct("MacBook Pro");
	    Assert.assertEquals(prdinfopge.getProductHeader(),"MacBook Pro");
	}
	@Test
	public void productimagescountTest() throws InterruptedException {
		searcrstpge=accpage.doSearch("imac");
		Thread.sleep(3000);
		prdinfopge= searcrstpge.selectProduct("iMac");
    	Assert.assertEquals(prdinfopge.getProductImsgesCount(),Constants.IMAC_IMAGES_COUNT);
	}
	@DataProvider
	public Object[][] selectmainprod(){
	 return new Object[][]{
	 {"Apple","Apple Cinema 30\""},
	 {"SamSung","Samsung SyncMaster 941BW"}
	 
	};
	}
	@Test(priority = 3,dataProvider = "selectmainprod")
	public void ImagsCount(String productname,String mainproductname) throws InterruptedException {
	searcrstpge	=accpage.doSearch(productname);
	Thread.sleep(3000);
    prdinfopge=searcrstpge.selectProduct(mainproductname);
    Assert.assertEquals(prdinfopge.getProductImsgesCount(), Constants.APPLE_IMAGES_COUNT,Constants.SAMSUNG_IMAGES_COUNT);
	}
	@DataProvider
	public Object[][] productdatadetails(){
		return new Object[][] {
			 {"Apple","Apple Cinema 30\""},
			 {"SamSung","Samsung SyncMaster 941BW"},
			 {"Macbook","MacBook Pro"}
		};
	}
	
	@Test(priority = 4,dataProvider ="productdatadetails" )
	public void productInfoTest(String productname,String mainproductname) throws InterruptedException {
		searcrstpge=accpage.doSearch(productname);
		Thread.sleep(3000);
		prdinfopge= searcrstpge.selectProduct(mainproductname);
		Map<String, String> actualproductinfomap = prdinfopge.getProductInfo();
		actualproductinfomap.forEach((k,v)-> System.out.println(k + " : "+v));
		//Assert.assertEquals(actualproductinfomap.get("name"), "mainproductname");   //HardAssertion
//   	Assert.assertEquals(actualproductinfomap.get("Brand"), "Apple ");
//		Assert.assertEquals(actualproductinfomap.get("Product Code"), " ");
//		Assert.assertEquals(actualproductinfomap.get("ExtPrice"), "[$2,000.00]");
//		softassert.assertEquals(actualproductinfomap.get("name"), mainproductname);    //softassert
//		softassert.assertEquals(actualproductinfomap.get("Brand"), "[[AppleProduct Code] ] ");
//		softassert.assertEquals(actualproductinfomap.get("Product Code"), "[ ]");
//		softassert.assertEquals(actualproductinfomap.get("ExtPrice"), "[$2,000.00]");
//		softassert.assertAll();  //compulsary
	}
}
