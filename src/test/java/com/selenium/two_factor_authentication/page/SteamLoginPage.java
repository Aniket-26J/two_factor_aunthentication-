package com.selenium.two_factor_authentication.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.two_factor_authentication.base.TestBase;

public class SteamLoginPage extends TestBase {

	@FindBy(xpath = "//input[(@type='text') and contains(@Class,'newlogindialog')]")
	private WebElement usernameInput;
	
	@FindBy(xpath = "//input[(@type='password')]")
	private WebElement passwordInput;
	
	@FindBy(tagName = "button")
	private WebElement loginButton;
	
	public void login(String username, String password) {
		clearAndSendKeys(usernameInput, username);
		clearAndSendKeys(passwordInput, password);
		loginButton.click();
	}
	
	public void enterSteamGuard(String otp) {
		driver.switchTo().window(windowHandles.get("steam"));
		String[] otpCharacters = otp.split("");
		List<WebElement> inputFields = driver.findElements(By.xpath("//div[contains(@Class,'Segmented')]//input"));
		
		for(int i=0; i<otpCharacters.length; i++) {
			clearAndSendKeys(inputFields.get(i), otpCharacters[i]);
		}
	}
	
}