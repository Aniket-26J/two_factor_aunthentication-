package com.selenium.two_factor_authentication.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.two_factor_authentication.base.TestBase;

public class OutlookHomePage extends TestBase {
	
	@FindBy(xpath = "//span[text()='Other']")
	private WebElement otherMailButton;
	
	public String getOTP() {
		List<WebElement> messages = driver.findElements(By.xpath("//div[@role='listbox']//div[@role='option']"));
		WebElement resetEmail = messages.stream()
				.filter(message -> message.getAttribute("aria-label").contains("Unread Steam Support Your Steam account: Access from new web or mobile device"))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("oops"));
		
		resetEmail.click();
		
		String guardCode = driver.findElement(By.xpath("//div[@aria-label='Email message']//td[contains(@class,'x_a-center')]")).getText();
		return guardCode;
	}
	
}