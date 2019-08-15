package in.amazon.selenium.testcases;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import in.amazon.selenium.commons.OpenBrowser;
import in.amazon.selenium.commons.RegressionCommons;

public class TC003 extends RegressionCommons
{
	
	OpenBrowser ob = new OpenBrowser();
	
	@BeforeSuite
	public void Report() throws Exception
	{
		try
		{
		extent = new ExtentReports(reportPath,true);
		
		}catch(Exception e)
		{
			System.out.println("Report method issue : " + e.getMessage() );
		}
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void config(String browser) throws Exception
	{
		test = extent.startTest("TC003 - Amazon Home Page");
		test.assignAuthor("Indu");
		test.assignCategory(browser);
		prop=configLoad(configPath);
		System.out.println(configPath);
		System.out.println(prop.getProperty("URL"));
	
	}
	
	
	@Parameters("browser")
	@Test
	public void TC003(String browser) throws Exception
	{
		try
		{
			FileInputStream fis = new FileInputStream(testdataPath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("TestData");
			for(int i = 1; i<= sheet.getLastRowNum() ; i++)
			{
				if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("TC003") && sheet.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("Yes"))
				{

					driver = ob.browserOpen(driver, browser);
					navigateURL(driver,test, prop.getProperty("URL"));
					validateTitle(driver,test, prop.getProperty("HomePageTitle"));
					driver.close();
				}
				
				if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("TC003") && sheet.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("No"))
				{
					test.log(LogStatus.SKIP, "TC003 Run mode is No");

					throw new SkipException("TC003 Run mode is No");
				}
			}
			
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	@AfterMethod
	public void configend() throws Exception
	{
		try
		{
			
			extent.endTest(test);

			extent.flush();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			
		
	}

}
