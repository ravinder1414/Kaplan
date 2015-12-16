package testCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class CheckAdmin {
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		
		//Edit Browser Capabilities as per project
		//Fire fox Profile
		FirefoxProfile profile = new FirefoxProfile();
			
		//Capability
		objBrowserMgr = new BrowserManagement(sBrowser);
		objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
			
		//Create the Remote Driver Instance
		try
		{						
			driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
		}
		catch(Exception ex)
		{	
			Reporter.log("Unable to create the Remotedriver" +  ex.getMessage());		
		}
	}
	
	//Data Provider
	@DataProvider(name="OpenApp")
	public static Object[][] URLS(){
		return new Object [][] {{"http://10.71.5.57"},{"http://10.71.5.58"},{"http://10.71.5.59"},{"http://10.71.5.60"}};
		
	}

	//Monitor KHEC Quad Prod Servers
	@Test(dataProvider="OpenApp")
	public void MonitorKHECQuadServer(String sURL ){
	//open URL
	driver.get(sURL);
	driver.manage().window().maximize();
	//Verify Login Page
	Assert.assertTrue(driver.findElement(By.cssSelector("input.cmsbutton1")).isDisplayed(), "Login button not displayed");
	//Enter Credentials and login
	driver.findElement(By.id("username")).sendKeys(EnvironmentVariables.sUserName_Admin);
	driver.findElement(By.id("password")).sendKeys(EnvironmentVariables.sPassword_Admin);
	driver.findElement(By.cssSelector("input.cmsbutton1")).click();
	//Verify Logout button
	Assert.assertTrue(driver.findElement(By.id("Label1")).isDisplayed(), "Logout button not displayed. Login unsucessful");
	
	//********Assert home Page elements
	//Assert home tab
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Home']")).isDisplayed(), "Home Tab is not displayed");
	//Assert Portal Content Section
	Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Portal Content']")).isDisplayed(), "Portal Content Section is not displayed");
	//Assert Portal Users Section
	Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Portal Users')]")).isDisplayed(), "Portal Users Section is not displayed");
	//Assert Portal Roles and Permissions Section
	Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Roles and Permissions')]")).isDisplayed(), "Roles and Permissions Section is not displayed");
	//Assert Courses and Attendance  Section
	Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Courses & Attendance')]")).isDisplayed(), "Courses and Attendance Section is not displayed");
	//Assert Miscellaneous  Section
	Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Miscellaneous')]")).isDisplayed(), "Miscellaneous Section is not displayed");
	
	//*****Check Search Existing User 
	//Assert Search Existing User link
	Assert.assertTrue(driver.findElement(By.linkText("Search Existing User")).isDisplayed(), "Search Exisiting User link is not displayed");
	driver.findElement(By.linkText("Search Existing User")).click();
	//Assert Search Portal Users heading
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Search Portal Users']")).isDisplayed(), "Search Portal Users heading is not displayed");
	driver.findElement(By.xpath("//a[text()='Home']")).click();
	
	//*****Check Portal Preview 
	//Assert Portal Preview link
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='podInner']/ul/li/ul/li/a[text()='Portal Preview']")).isDisplayed(), "Portal Preview link is not displayed");
	driver.findElement(By.xpath("//div[@class='podInner']/ul/li/ul/li/a[text()='Portal Preview']")).click();
	//Assert Portal Preview heading
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Portal Preview']")).isDisplayed(), "Portal Preview heading is not displayed");
	driver.findElement(By.xpath("//a[text()='Home']")).click();

	//*****Check Attendance Group 
	//Assert Attendance group link
	Assert.assertTrue(driver.findElement(By.xpath("//div/ul/li/a[text()='Attendance Group']")).isDisplayed(), "Attendance Group link is not displayed");
	driver.findElement(By.xpath("//div/ul/li/a[text()='Attendance Group']")).click();
	//Assert Search Active Directory label
	Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Search Active Directory:']")).isDisplayed(), "Attendance Group: Search Active Directory label is not displayed");
	//Assert Search button
	Assert.assertTrue(driver.findElement(By.id("btnSearch")).isDisplayed(), "Attendance Group: Search button is not displayed");
	driver.findElement(By.xpath("//a[text()='Home']")).click();
	
}

	@AfterClass
	public void AfterNavigation(){
	//Logout from App
	driver.findElement(By.id("Label1")).click();
	if (driver !=null) {driver.quit();}
	  
}
}
