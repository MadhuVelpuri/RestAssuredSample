package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderLooping {
	WebDriver driver;
	
	@Test(dataProvider = "Loop")
	public void Login(String username, String password) throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get("https://login.medscape.com/login/sso/getlogin?ac=401");
		driver.findElement(By.id("userId")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginbtn")).click();
		Thread.sleep(3000);
	}

	@DataProvider(name = "Loop")
	public Object[][] looping() {
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "username";
		obj[0][1] = "password";

		obj[1][0] = "username";
		obj[1][1] = "password";

		obj[2][0] = "username";
		obj[2][1] = "password";

		return obj;
	}
	
	
}
