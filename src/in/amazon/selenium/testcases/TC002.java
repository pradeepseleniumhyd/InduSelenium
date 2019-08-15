package in.amazon.selenium.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import com.relevantcodes.extentreports.LogStatus;

import in.amazon.selenium.commons.OpenBrowser;
import in.amazon.selenium.commons.RegressionCommons;


public class TC002 extends RegressionCommons
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
		test = extent.startTest("TC002 - Amazon");
		test.assignAuthor("Indu");
		test.assignCategory(browser);
		prop=configLoad(configPath);
		System.out.println(configPath);
		System.out.println(prop.getProperty("URL"));
		driver = ob.browserOpen(driver, browser);
		navigateURL(driver,test, prop.getProperty("URL"));
	}
	
	
	
	@Test
	public void TC002() throws Exception
	{
		try
		{
			validateAttribute(driver, test, "(//a[contains(@class,'logo')])[1]", "aria-label", "Amazon");
			click(driver, test, "//a[text()='Your Amazon.in']", "Your Amazon.in");	
			validateText(driver, test, "//label[contains(text(),'Email')]", "Email or mobile phone number");
			validateText(driver, test, "//h1[contains(text(),'Login')]", "LoginTest");
			validateText(driver, test, "//span[contains(text(),'Amazon')]", "1996-2019, Amazon.com, Inc. or its affiliates");
				
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
			driver.close();
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			
		
	}

}
