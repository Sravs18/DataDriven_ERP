package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.LoginPage;
import commonFunctions.LogoutPage;

public class AppUtil1 {
	public static WebDriver driver;
	public static Properties conpro;
	
	@BeforeTest
	public static void setUp() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			login.adminLogin("admin", "master");
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			login.adminLogin("admin", "master");
		}
		else
		{
			Reporter.log("Browser value is not matching",true);
		}
	}
	@AfterTest
	public static void tearDown()
	{
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		logout.adminLogout();
		driver.quit();
		
	}

}
