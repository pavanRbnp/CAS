package com.generic_library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverCommonLib {
	
	WebDriver driver;

	public void getTitle() {
		
	}
	
	// chromeDriverManager setup
	public void chromeDriverSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	// firefoxDriverManager setup
	public void firefoxDriverSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new FirefoxDriver();	
	}
	
	public void verify(String actTitle,String expTitle,String pageName) {
		Assert.assertEquals(actTitle,expTitle);
		Reporter.log(pageName+" is displayed, PASS",true);
		 
	}
}
