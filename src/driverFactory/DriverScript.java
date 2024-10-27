package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath ="./FileInput/LoginData.xlsx";
	String outputpath ="./FileOutput/DataDrivenResults.xlsx";
	String TCSheet ="Login";
	@Test
	public void stratTest() throws Throwable
	{
		//creating reference object for Excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows ina sheet
		int rc =xl.rowCount(TCSheet);
		//iterate all rows in a sheet
		for(int i=1;i<=rc;i++)
		{
			//read username and password cell from login sheet
			String username =xl.getCellData(TCSheet, i, 0);
			String password = xl.getCellData(TCSheet, i, 1);
			//call adminlogin method from functionlibaray class
			boolean res =FunctionLibrary.adminLogin(username, password);
			
			if(res)
			{
				//if res is true update into results and status cell
				xl.setCellData(TCSheet, i, 2, "Login Success", outputpath);
				xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
			}
			else
			{
				//if res is false update into results and status cell
				xl.setCellData(TCSheet, i, 2, "Login Fail", outputpath);
				xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
				//take screen shot and store
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy screen shot into local system
				FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"----"+"Loginpage.png"));
			}
			
		}
		
	}



}
