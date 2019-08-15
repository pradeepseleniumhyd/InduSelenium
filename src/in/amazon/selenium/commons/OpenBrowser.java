package in.amazon.selenium.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class OpenBrowser extends RegressionCommons
{

	public WebDriver browserOpen(WebDriver driver,String browsername)
	{
		try
		{
			if(browsername.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", chromepath);
				 driver = new ChromeDriver();
			
			}else if(browsername.equalsIgnoreCase("ie"))
			{
				/*System.setProperty("webdriver.ie.driver",iepath);
				 driver = new InternetExplorerDriver();
			*/
				
			}else if(browsername.equalsIgnoreCase("firefox"))
			{
				/*System.setProperty("webdriver.gecko.driver",firefoxpath);
				 driver = new FirefoxDriver();
			*/
				
			}else
			{
				System.out.println(browsername + " is not valid");
			}
			
			
		}catch(Exception e)
		{
			System.out.println("Browser Code method issue");
		}
		
		driver.manage().window().maximize();
		return driver;
	}

}
