package com.qa.openkart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsmanager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(Properties prop) {
		String BrowserName = prop.getProperty("browser");
		System.out.println("Browser Name is :"+BrowserName);
		highlight=prop.getProperty("highlight");
		optionsmanager=new OptionsManager(prop);
		
		if(BrowserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			
			//driver=new ChromeDriver(optionsmanager.ChromeOptions());
			
			tlDriver.set(new ChromeDriver(optionsmanager.ChromeOptions()));
		}else if (BrowserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			//driver=new FirefoxDriver(optionsmanager.FirefoxOptions());
			
			tlDriver.set(new FirefoxDriver(optionsmanager.FirefoxOptions()));
		}else if (BrowserName.equals("Safari")) {
			
		   //driver=new SafariDriver();	
			
			tlDriver.set(new SafariDriver());
		}else {
			System.out.println("Please Pass the Right browser : "+BrowserName);
		}
		getdriver().manage().window().fullscreen();   //thread Local driver
		getdriver().manage().deleteAllCookies();
		getdriver().get(prop.getProperty("url"));
	//	openUrl(prop.getProperty(url));
	//	URL url=new URL(prop.getProperty("url"));
		
		return getdriver();
	}
	
	
	/*
	 * 
	 * getdriver();  it will return a thread local copy of  the webdriver
	 * 
	 */
	public static synchronized WebDriver getdriver() {
		return tlDriver.get();
	}
	
	public Properties init_prop() {
	    prop=new Properties();
	    FileInputStream ip=null;
	    
	    
	    String envName=System.getProperty("env");
	    
	    if(envName == null) {
	    	System.out.println("Running on PROD  env: ");  //qa/dev/stage/uat   //cd C:/Framework
	    	 try {
				ip=new FileInputStream("./src/test/resources/configdata/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}   
	    }
	    else {  //mvn clean install -DenvName="qa"--mvn cmd
			System.out.println("Running on environment : "+envName);
			try {
			switch (envName.toLowerCase()) {
			case "qa":
				ip=new FileInputStream("./src/test/resources/configdata/qa.config.properties");
				break;
			case "dev":
				ip=new FileInputStream("./src/test/resources/configdata/dev.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/configdata/stage.config.properties");
				break;
			case "uat":
				ip=new FileInputStream("./src/test/resources/configdata/uat.config.properties");
				break;
				
			default:
				System.out.println("please pass the right environment.....");
				break;
			}
		}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }
	    try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return prop;
	}
	
	/*
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getdriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destiation=new File(path);
		try {
			FileUtils.copyFile(src, destiation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/*
	 * launch url method
	 * 
	 */
	
	public void openUrl(String url) {
		try {
		if(url==null) {
			throw new Exception("url is null");
		}
		}catch (Exception e) {
		
		}
		getdriver().get(url);
	}
	//overload this method
	
	public void openUrl(URL url) {
		try {
		if(url==null) {
			throw new Exception("url is null");
		}
		}catch (Exception e) {
		
		}
		getdriver().navigate().to(url);;
	}
	//Overload one more time
	public void openUrl(String baseurl,String path) {
		try {
		if(baseurl==null) {
			throw new Exception("baseurl is null");
		}
		}catch (Exception e) {
		
		}
		  //     base url     path
		//http://amazon.com/accpage/users.html
		getdriver().get(baseurl+"/"+path);
	}
	//overloaded
	public void openUrl(String baseurl,String path,String queryparam) {
		try {
		if(baseurl==null) {
			throw new Exception("baseurl is null");
		}
		}catch (Exception e) {
		
		}
		  //     base url     path
		//http://amazon.com/accpage/users.html     
		 //           baseurl                 accpage           queryparam
		//https://naveenautomationlabs.com/opencart/index.php?route=account/register
		getdriver().get(baseurl+"/"+path+"?"+queryparam);
	}
}
