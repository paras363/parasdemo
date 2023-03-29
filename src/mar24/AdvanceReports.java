package mar24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvanceReports
{

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
@BeforeTest
public void generateReport()
{
	report=new ExtentReports("./ExtentReports/Demo.html");
	
}

@BeforeMethod
public void setUp()
{
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://google.com");
	
}
	
@Test
public void testcase1()
{
	
	test=report.startTest("Pass Test");
	test.assignAuthor("Er.Paras");
	test.assignCategory("Functional Testing");
	
	String expected_title="Google";
	String actual_title=driver.getTitle();
	
	if(expected_title.equalsIgnoreCase(actual_title))
	{
		test.log(LogStatus.PASS, "Title is matching:::"+ expected_title+"  "+actual_title);
	}
	else
	{
		test.log(LogStatus.FAIL, "Title is not matching:::"+ expected_title+"  "+actual_title);
	}	
}

@Test
public void testcase2()
{
	test=report.startTest("Fail Test");
	test.assignAuthor("Er.Paras");
	test.assignCategory("Functional Testing");
	
	String expected_title="Gmail";
	String actual_title=driver.getTitle();
	
	if(expected_title.equalsIgnoreCase(actual_title))
	{
		
		test.log(LogStatus.PASS, "title is matching::"+expected_title+"  "+actual_title);
	}
	else
	{
		test.log(LogStatus.FAIL, "title is not matching::"+expected_title+"  "+actual_title);
	}
}
@AfterMethod
public void tearDown()
{
	report.endTest(test);
	report.flush();
	driver.quit();
}
}
