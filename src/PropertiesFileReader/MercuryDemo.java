package PropertiesFileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MercuryDemo 
{
	public WebDriver driver=null;
	
	Properties pro=new Properties();
	
	@Test(priority=1)
	public void openbrowser() throws IOException
	{
		FileInputStream fis=new FileInputStream("E:\\Complete\\MercurySiteDemo\\File1.properties");
		pro.load(fis);
		System.out.println(pro.getProperty("browser"));
		System.out.println(pro.getProperty("url"));
		System.out.println(pro.getProperty("username"));
		System.out.println(pro.getProperty("password"));
		System.out.println(pro.getProperty("usernameinvalid"));
		System.out.println(pro.getProperty("passwordinvalid"));
		if(pro.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","G:\\Software\\Selenium\\Updated\\chromedriver.exe");
			driver=new ChromeDriver();
			System.out.println("ChromeBrowser open");
		}
		
		else if(pro.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","G:\\Software\\Selenium\\Updated\\geckodriver.exe");
			driver=new FirefoxDriver();
			System.out.println("Firefox browser open");
		}
		
		else
		{
			System.setProperty("webdriver.ie.driver","G:\\Software\\Selenium\\Updated\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			System.out.println("IE open ");
		}
	}
	
	@Test(priority=2)
	public void openurl()
	{
		driver.get(pro.getProperty("url"));
		System.out.println(driver.getCurrentUrl()+"  "+"open successfully");
	}
	
	@Test(priority=3)
	public void maxbrowser()
	{
		driver.manage().window().maximize();
	}
	
	@Test(priority=4,description="verify that user able to enter mercurytours with valid data")
	public void loginvalid()
	{
		 driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(pro.getProperty("username"));
		 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pro.getProperty("password"));
		 driver.findElement(By.xpath("//input[@name='login']")).click();
		 List<WebElement> ele=driver.findElements(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img"));
		Assert.assertTrue(ele.size()==1);
		 driver.findElement(By.xpath("//a[text()='SIGN-OFF']")).click();
		 System.out.println("Login Successfully");
	}
	@Test(priority=5,description="verify that user able to enter mercurytours with invalid data")
	public void  invalidlogin()
	{
		 driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(pro.getProperty("usernameinvalid"));
		 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pro.getProperty("passwordinvalid"));
		 driver.findElement(By.xpath("//input[@name='login']")).click();
		  List<WebElement> ele=driver.findElements(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img"));
		Assert.assertFalse(ele.size()==1);
		 System.out.println("Enter invalid data for login ");
	}
}

