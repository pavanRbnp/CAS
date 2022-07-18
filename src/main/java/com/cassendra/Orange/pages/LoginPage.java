package com.cassendra.Orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath="//input[@id='txtUsername']") private WebElement userName;
	@FindBy(id="txtPassword") private WebElement password;
	@FindBy(className="button") private WebElement loginBtn;
	@FindBy(xpath="//a[text()='Forgot your password?']") private WebElement forgotLink;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	
	public WebElement getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		userName.sendKeys(username);
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(String passWord) {
		password.sendKeys(passWord);
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn() {
		loginBtn.click();
	}


	public void setForgotLink() {
		forgotLink.click();
	}
	
	

}
