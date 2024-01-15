package com.qa.openkart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.utils.Constants;
import com.qa.openkart.utils.Errors;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accpage=lppage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority = 1)
	public void accPageTitleTest() throws InterruptedException {
		String actitle = accpage.getAccountPageTitle();
		Thread.sleep(3000);
		System.out.println("Account Page Title is : "+actitle);
		Assert.assertEquals(actitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	@Test(priority = 2)
	public void aaccPageHeaderTest() {
		String header = accpage.getAccountPageHeaderTest();
		System.out.println("AccountPage Header is :"+header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER,Errors.ACCOUNT_PAGE_HEADER_NOT_FOUND_ERROR_MSG);
	}
	@Test(priority = 3)
	public void isLogoutLikTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}
    @Test(priority = 4)
    public void accPageSecTest() {
    	List<String> actseclist = accpage.getAccountSecList();
    	Assert.assertEquals(actseclist, Constants.getExpectedAccSecList());
    }
    @DataProvider
    public Object[][] productdata() {
    	return new Object[][] {
    		{"MacBook"},
    		{"Apple"},
    		{"Samsung"}
    	};
    }
    @Test(priority = 5,dataProvider = "productdata")
    public void searchTest(String productname) {
    	searcrstpge=accpage.doSearch(productname);
        Assert.assertTrue(searcrstpge.getProductListCount()>0);
    }
    @DataProvider
    public Object[][] selectproductdata() {
    	return new Object[][] {
    		{"MacBook","MacBook Pro"},
    		{"iMac","iMac"},
    		{"SamSung","Samsung SyncMaster 941BW"},
    		{"Apple","Apple Cinema 30\""}
    	};
    }
    
    @Test(priority = 6,dataProvider = "selectproductdata")
    public void selectProductTest(String productname,String mainproductname) throws InterruptedException {
    	searcrstpge=accpage.doSearch(productname);
    	Thread.sleep(3000);
    	prdinfopge=searcrstpge.selectProduct(mainproductname);
        Assert.assertEquals(prdinfopge.getProductHeader(),mainproductname);
    }

}
