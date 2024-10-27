package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	//define repository for login
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjReset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement Objuser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement Objpass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement Objloginbtn;
//write method for login
	public void adminLogin(String username,String password)
	{
		ObjReset.click();
		
		Objuser.sendKeys(username);
		Objpass.sendKeys(password);
		Objloginbtn.click();
	}





}
