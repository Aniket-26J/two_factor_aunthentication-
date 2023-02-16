package com.selenium.two_factor_authentication.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.two_factor_authentication.base.TestBase;
import com.selenium.two_factor_authentication.page.OutlookHomePage;
import com.selenium.two_factor_authentication.page.OutlookLoginPage;
import com.selenium.two_factor_authentication.page.SteamHomePage;
import com.selenium.two_factor_authentication.page.SteamLoginPage;

public class SteamLoginTest extends TestBase {
	
	private OutlookHomePage outlookHomePage;
	private SteamHomePage steamHomePage;
	private SteamLoginPage steamLoginPage;
	
	@BeforeMethod
	public void beforeMethod() {
		super.setUp("https://store.steampowered.com/");
		steamHomePage = PageFactory.initElements(driver, SteamHomePage.class);
		steamLoginPage = steamHomePage.getLoginPage();
	}
	
	@Test
	public void test() throws InterruptedException {
		String steamUsername = System.getProperty("steam_username");
		String steamPassword = System.getProperty("steam_password");
		
		steamLoginPage.login(steamUsername, steamPassword);
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://outlook.live.com/");
		
		String outlookUsername = System.getProperty("outlook_username");
		String outlookPassword = System.getProperty("outlook_password");
		
		OutlookLoginPage outlookLoginPage = PageFactory.initElements(driver, OutlookLoginPage.class);
		outlookHomePage = outlookLoginPage.login(outlookUsername, outlookPassword);
		
		Thread.sleep(5000);
		
		String otp = outlookHomePage.getOTP();
		
		driver.switchTo().window(windowHandles.get("steam"));
		steamLoginPage.enterSteamGuard(otp);
		
		Assert.assertEquals(driver.findElement(By.id("account_pulldown")).getText().contains(System.getProperty("steam_username")), true);
	}
}