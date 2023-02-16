package com.selenium.two_factor_authentication.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	protected static WebDriver driver;
	private Properties driverConfigProperties;
	
	protected static Map<String, String> windowHandles = new HashMap<>();

	public TestBase() {
		super();
		
		driverConfigProperties = new Properties();
		
		try(FileInputStream fis = new FileInputStream("./src/test/java/com/selenium/steam_2fa/config/driver_config.properties")) {
			driverConfigProperties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setUp(String url) {
		String browser = driverConfigProperties.getProperty("browser");
		int pageLoadTimeout = Integer.parseInt(driverConfigProperties.getProperty("pageLoadTimeout"));
		int implicitWaitTimeout = Integer.parseInt(driverConfigProperties.getProperty("implicitWaitTimeout"));
		
		if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeout));
		
		driver.manage().window().maximize();
		
		if(url == null) {
			url = driverConfigProperties.getProperty("url");
		}
		
		driver.get(url);
	}
	
	protected void clearAndSendKeys(WebElement webElement, String keys) {
		webElement.clear();
		webElement.sendKeys(keys);
	}
	
	protected void switchToLastTab() {
		String lastWindowhandle = driver.getWindowHandles().stream().reduce((current, next) -> next).orElse(null);
		driver.switchTo().window(lastWindowhandle);
	}
	
	protected void tearDown() {
		driver.quit();
	}
	
}