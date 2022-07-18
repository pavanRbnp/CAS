package com.generic_library;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


public class BaseTest implements IAutoConstants {
 
	WebDriver driver ;
	
	@BeforeClass
	public void openBrowser() throws IOException {
		FileLibrary flib = new FileLibrary();
		WebDriverCommonLib clib = new WebDriverCommonLib();
		if(flib.getPropertyOf(BaseTest.propPath,"chromeBrowser").equalsIgnoreCase("chrome")) {
			clib.chromeDriverSetup();
		}
		else if(flib.getPropertyOf(BaseTest.propPath,"firefoxBrowser").equalsIgnoreCase("firefox")) {
			clib.firefoxDriverSetup();
		}
		else {
			Reporter.log("Enter proper browser name", true);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(flib.getPropertyOf(propPath, "url"));
		clib.verify(flib.getPropertyOf(propPath,
				"signInPageTitle"), 
				driver.getTitle(),
				"signIn");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	
	
}
