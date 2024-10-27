package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	public static boolean adminLogin(String username,String password) throws Throwable
	{
		driver.get(conpro.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(3000);
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected)) {
			Reporter.log("Valid Credentials::"+Expected+"----------"+Actual,true);
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			//capture error message
			String error_Message =driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
			Thread.sleep(3000);
			Reporter.log(error_Message+"--------"+Expected+"----------"+Actual,true);
			return false;
		}
		
	}

}
