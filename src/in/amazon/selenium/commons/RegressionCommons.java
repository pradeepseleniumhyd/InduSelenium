package in.amazon.selenium.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class RegressionCommons 
{

	public static String chromepath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static String iepath = "D:\\INDU\\Basics\\driver\\IEDriverServer.exe";
	public static String firefoxpath = "D:\\INDU\\Basics\\driver\\geckodriver.exe";
	
	public static String configPath = System.getProperty("user.dir")+ "\\Resources\\config.properties"; 
	//Excel Path
	public String testdataPath =  System.getProperty("user.dir")+ "\\TestData\\Amazon.xlsx"; 
	
	
	public String reportPath = System.getProperty("user.dir") + "\\Reports\\AmazonReport.html";
	
	public FileInputStream fis;
	public Properties prop;
	public WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest test;
	
	
	public void navigateURL(WebDriver driver,ExtentTest test,String url) 
	{
		try
		{
			driver.get(url);
			System.out.println("URL is entered : " + url);
			test.log(LogStatus.PASS, "URL is entered : " + url);
			validateTitle(driver, test, "Online Shopping site in India");
		}catch(Exception e)
		{
			System.out.println("Navigate URL method is not working : " + e.getMessage());
			try
			{
			String imagepath = screenshot(driver, "URL");
			test.log(LogStatus.FAIL, "Navigate URL method is not working : " + e.getMessage() + test.addScreenCapture(imagepath));
			}catch(Exception e2)
			{
				System.out.println(e2.getMessage());
			}
		}
		
	}
	
	public void click(WebDriver driver,ExtentTest test,String xpathvalue, String Title)
	{
		try
		{
			driver.findElement(By.xpath(xpathvalue)).click();
			test.log(LogStatus.PASS, Title + " is clicked");
			
		}catch(Exception e)
		{
			System.out.println("Navigate URL method is not working : " + e.getMessage());
			test.log(LogStatus.FAIL, Title + " is not clicked " + " : click method is not working : " + e.getMessage());

		}
		
	}
	
	
	public Properties configLoad(String config_path) throws Exception
	{
		fis = new FileInputStream(config_path);
		prop = new Properties();
		prop.load(fis);
		return prop;
	
	}

	public void validateTitle(WebDriver driver,ExtentTest test,String ExpectedTitle)
	{
		try
		{
			String title = driver.getTitle();
			System.out.println(title);
			if(title.equalsIgnoreCase(ExpectedTitle))
			{
				System.out.println(title + " is matched");
				Reporter.log(title + " is matched");
				test.log(LogStatus.PASS, title + " is matched");
			}else
			{
				System.out.println(title + " is not matched");
				Reporter.log(title + " is not matched");
				String imagepath = screenshot(driver, ExpectedTitle);
				test.log(LogStatus.FAIL, title + " is not matched" + test.addScreenCapture(imagepath));

			}
			
		}catch(Exception e)
		{
			System.out.println("ValidateTitle method is not working : " + e.getMessage());
			test.log(LogStatus.FAIL, "ValidateTitle method is not working : " + e.getMessage());
		}
		
	}
	
	public void validateAttribute(WebDriver driver,ExtentTest test,String xpathvalue,String attributename, String expectedattributevalue)
	{
		try
		{
			String value = driver.findElement(By.xpath(xpathvalue)).getAttribute(attributename);

			System.out.println(value);
			if(value.equalsIgnoreCase(expectedattributevalue))
			{
				System.out.println(value + " is matched");
				Reporter.log(value + " is matched");
				test.log(LogStatus.PASS, value + " is matched");
			}else
			{
				System.out.println(value + " is not matched");
				Reporter.log(value + " is not matched");
				test.log(LogStatus.FAIL, value + " is not matched");

			}
			
		}catch(Exception e)
		{
			System.out.println("Validate Attribute method is not working : " + e.getMessage());
			test.log(LogStatus.FAIL, "Validate Attribute method is not working : " + e.getMessage());
		}
		
	}
	
	public String screenshot(WebDriver driver, String screenshotname) throws IOException
	{
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imgpath = System.getProperty("user.dir")+"\\Screenshots\\"+ screenshotname + ".jpeg";
		FileUtils.copyFile(srcfile, new File(imgpath));
		return imgpath;
	}
	
	public void validateText(WebDriver driver,ExtentTest test,String xpathvalue, String expectedvalue) throws Exception
	{
		try
		{
			String value = driver.findElement(By.xpath(xpathvalue)).getText();

			System.out.println(value);
			if(value.equalsIgnoreCase(expectedvalue))
			{
				System.out.println(value + " is matched");
				Reporter.log(value + " is matched");
				test.log(LogStatus.PASS, value + " is matched");
			}else
			{
				System.out.println(value + " is not matched");
				Reporter.log(value + " is not matched");
			String	imagepath = screenshot(driver, expectedvalue);
				test.log(LogStatus.FAIL, value + " is not matched" + test.addScreenCapture(imagepath));

			}
			
		}catch(Exception e)
		{
			System.out.println("Validate Text method is not working : " + e.getMessage());
			String	imagepath = screenshot(driver, expectedvalue);
			test.log(LogStatus.FAIL, "Validate Text method is not working : " + e.getMessage() + test.addScreenCapture(imagepath));
		}
		
	}
	
	
}
