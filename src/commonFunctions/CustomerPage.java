package commonFunctions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
WebDriver driver;
//constructor for accessing webdriver methods
public CustomerPage(WebDriver driver)
{
	this.driver=driver;
}
//define repository
@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
WebElement ClickCustomer;
@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement ClickAddIcon;
@FindBy(name = "x_Customer_Number")
WebElement CustomerNumber;
@FindBy(name = "x_Customer_Name")
WebElement EnterCustomerName;
@FindBy(name = "x_Address")
WebElement EnterAddress;
@FindBy(name = "x_City")
WebElement EnterCity;
@FindBy(name = "x_Country")
WebElement EnterCountry;
@FindBy(name = "x_Contact_Person")
WebElement EnterConactPerson;
@FindBy(name = "x_Phone_Number")
WebElement EnterPhoneNumber;
@FindBy(name = "x__Email")
WebElement Enteremail;
@FindBy(name = "x_Mobile_Number")
WebElement EnterMobileNumber;
@FindBy(name = "x_Notes")
WebElement EnterNotes;
@FindBy(id = "btnAction")
WebElement ClickAddBtn;
@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement clickConfirmOk;
@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
WebElement clickAlertOk;
@FindBy(xpath = "//span[@data-caption='Search']")
WebElement ClickSearchPanel;
@FindBy(xpath = "//input[@id='psearch']")
WebElement EnterSearch;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement ClickSearch;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
WebElement webtable;
//method for add customer
public boolean addCustomer(String customername,String Address,String City,String Country,String ContactPerson,
		String phoneNumber,String Email,String Mobilenumber,String Notes) throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(this.ClickCustomer).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(this.ClickAddIcon).click().perform();
	Thread.sleep(2000);
	//capture customer number
	String Exp_Data = this.CustomerNumber.getAttribute("value");
	this.EnterCustomerName.sendKeys(customername);
	this.EnterAddress.sendKeys(Address);
	this.EnterCity.sendKeys(City);
	this.EnterCountry.sendKeys(Country);
	this.EnterConactPerson.sendKeys(ContactPerson);
	this.EnterPhoneNumber.sendKeys(phoneNumber);
	this.Enteremail.sendKeys(Email);
	this.EnterMobileNumber.sendKeys(Mobilenumber);
	this.EnterNotes.sendKeys(Notes);
	this.ClickAddBtn.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	this.clickConfirmOk.click();
	Thread.sleep(2000);
	this.clickAlertOk.click();
	Thread.sleep(2000);
	//click search panel if textbox not displayed
	if(!this.EnterSearch.isDisplayed())
		this.ClickSearchPanel.click();
	this.EnterSearch.clear();
	this.EnterSearch.sendKeys(Exp_Data);
	this.ClickSearch.click();
	Thread.sleep(3000);
	String Act_Data=webtable.getText();
	if(Exp_Data.equals(Act_Data))
	{
		Reporter.log("Add customer sucess::"+Exp_Data+"                "+Act_Data,true);
		return true;
	}
	else
	{
		Reporter.log("Add customer fail::"+Exp_Data+"                "+Act_Data,true);
		return false;
	}
	
	
}

}
