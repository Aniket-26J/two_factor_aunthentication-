package com.selenium.two_factor_authentication.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.two_factor_authentication.base.TestBase;

public class SteamHomePage extends TestBase {
	
	@FindBy(xpath = "//a[text()='login']")
	private WebElement loginLink;
	
	public SteamLoginPage getLoginPage() {
		windowHandles.put("steam", driver.getWindowHandle());
		loginLink.click();
		return PageFactory.initElements(driver, SteamLoginPage.class);
	}
	
}