package mar24;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Property_File
{

	WebDriver driver;
	Properties con;
	
@BeforeTest
public void setUp() throws Throwable, Throwable
{
	con=new Properties();
	con.load(new FileInputStream("OR.properties"));
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(con.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@Test
public void verifyLogin()
{
	driver.findElement(By.xpath(con.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(con.getProperty("Objuser"))).sendKeys(con.getProperty("EnterUse"));
	driver.findElement(By.xpath(con.getProperty("Objpass"))).sendKeys(con.getProperty("EnterPass"));
	driver.findElement(By.xpath(con.getProperty("ObjLoginbtn"))).click();
	
	String exptitile="Dashboard « Stock Accounting";
	String acttitle=driver.getTitle();
	if(exptitile.equalsIgnoreCase(acttitle))
	{
		Reporter.log("login success::"+exptitile+" "+acttitle,true);
	}
	else
	{
		Reporter.log("login fail::"+exptitile+" "+acttitle,true);
	}
	
}
@AfterTest
public void tearDown()
{
	driver.quit();
}
	
}
