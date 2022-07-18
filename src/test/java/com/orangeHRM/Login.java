package com.orangeHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cassendra.Orange.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	
	WebDriver driver;
	
	@BeforeMethod
	public void preCondtion() {
		
		WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
	}
	
	@Test
	public void loginTest() {
		
		LoginPage lp = new LoginPage(driver);
		
		// userName Entering
		if (lp.getUserName().isDisplayed()) {
			Reporter.log("user name textBox is displayed", true);
			if (lp.getUserName().isEnabled()) {

				lp.setUserName("Admin");
				Reporter.log("username successfully entered", true);
			} else {
				Reporter.log("userName textBox is not enabled", true);
			}

		} else {
			Reporter.log("userName textBox is not displayed", true);
		}
		
		//password entering
		if (lp.getPassword().isDisplayed()) {
			Reporter.log("password textBox is displayed", true);
			if (lp.getPassword().isEnabled()) {

				lp.setPassword("admin123");
				Reporter.log("password successfully entered", true);
			} else {
				Reporter.log("password textBox is not enabled", true);
			}

		} else {
			Reporter.log("password textBox is not displayed", true);
		}
		
		//Login button click
		if (lp.getLoginBtn().isDisplayed()) {
			Reporter.log("login button is displayed", true);
			if (lp.getLoginBtn().isEnabled()) {

				lp.setLoginBtn();
				Reporter.log("login button successfully clicked", true);
			} else {
				Reporter.log("login button is not enabled", true);
			}

		} else {
			Reporter.log("login button is not displayed", true);
		}	
	}
	
	@AfterMethod
	public void closeTest() {
		driver.quit();
	}

}
