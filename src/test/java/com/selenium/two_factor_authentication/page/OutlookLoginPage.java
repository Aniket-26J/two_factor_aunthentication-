package com.selenium.two_factor_authentication.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.two_factor_authentication.base.TestBase;

public class OutlookLoginPage extends TestBase {
	
	@FindBy(xpath = "//a[@data-task='signin']")
	private WebElement loginLink;
	
	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Next']")
	private WebElement nextButton;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Sign in']")
	private WebElement signInButton;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Yes']")
	private WebElement staySignedInButton;
	
	public OutlookHomePage login(String email, String password) {
		loginLink.click();
		
		clearAndSendKeys(emailInput, email);
		nextButton.click();
		
		clearAndSendKeys(passwordInput, password);
		signInButton.click();
		
		staySignedInButton.click();
		
		windowHandles.put("outlook", driver.getWindowHandle());
		
		return PageFactory.initElements(driver, OutlookHomePage.class);
	}
	
}