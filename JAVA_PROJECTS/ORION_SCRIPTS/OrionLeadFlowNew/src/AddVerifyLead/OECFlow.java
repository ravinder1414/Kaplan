package AddVerifyLead;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OECFlow {
	private WebDriver driver;

	
	@Parameters({"browser", "port"})
	//@Test(groups={"OECFlow"})
	@BeforeClass
	public void openBrowser(String browser, String port) throws MalformedURLException{
	//public void openBrowser() throws MalformedURLException{
		//driver=new FirefoxDriver();
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName(browser);
		//driver = new RemoteWebDriver(new URL("http://10.78.4.12:".concat(port).concat("/wd/hub")), capability);
		driver = new RemoteWebDriver(new URL("http://localhost:".concat(port).concat("/wd/hub")), capability);
		
		driver.get(TestData.OEC_URL);  //Dev OEC:-    http://10.78.6.59
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
	}
	
	//@Test(groups={"OECFlow"}, dependsOnMethods={"openBrowser"})
	@Test(groups={"OECFlow"})
	//@Test
	public void loginOEC() throws InterruptedException{
		Reporter.log("Login OEC Application URL:-");
		driver.findElement(By.id("ctl00_Login1_UserName")).sendKeys(TillAppReceived.varOECUID);
		driver.findElement(By.id("ctl00_Login1_Password")).sendKeys(TillAppReceived.varOECPWD);
		driver.findElement(By.id("ctl00_Login1_LoginButton")).click();
		Thread.sleep(3000);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtNewPassword")).sendKeys("Password1");
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtReenterNewPassword")).sendKeys("Password1");
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnChangePassword")).click();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_lnkContinue")).click();
	}
	
	@Test(groups={"OECFlow"}, dependsOnMethods={"loginOEC"})
	public void funOECFlow() throws InterruptedException {
		System.out.println("7. OEC_Flow.Html");
		if(driver.findElement(By.id("ctl00_ctl10_lblSM")).getText().contains("STEP 1 OF 5"))
			{System.out.println("Step 1 of 5 - correctly disp");Reporter.log("Step 1 of 5 - correctly disp");}
		else {System.out.println("Step 1 of 5 - incorrectly disp");Reporter.log("Step 1 of 5 - incorrectly disp");}
		
		driver.findElement(By.cssSelector("a.subnavcurrent")).click();
		driver.findElement(By.id("ctl00_ctl21_3062")).sendKeys("222-22-2222");
		driver.findElement(By.id("ctl00_ctl22_3063")).sendKeys("11/11/1980");
		driver.findElement(By.id("ctl00_ctl24_3065")).sendKeys("9545152585");
		driver.findElement(By.id("ctl00_stepNav_bN")).click();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		
		if (driver.findElement(By.id("ctl00_ctl10_lblSM")).getText().contains("STEP 2 OF 5"))
			{System.out.println("Step-2 of 5 is displaying correctly");Reporter.log("Step-2 of 5 is displaying correctly");}
			else {System.out.println("Step-2 of 5 is displaying incorrectly");Reporter.log("Step-2 of 5 is displaying incorrectly");}
		
		driver.findElement(By.id("ctl00_stepNav_bN")).click();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		
		if (driver.findElement(By.id("ctl00_ctl10_lblSM")).getText().contains("STEP 3 OF 5"))
			{System.out.println("Step-3 of 5 is displaying correctly");Reporter.log("Step-3 of 5 is displaying correctly");}
		else {System.out.println("Step-3 of 5 is displaying incorrectly");Reporter.log("Step-3 of 5 is displaying incorrectly");}
		
		driver.findElement(By.id("ctl00_stepNav_bN")).click();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		
		if (driver.findElement(By.id("ctl00_ctl10_lblSM")).getText().contains("STEP 4 OF 5"))
			{System.out.println("Step-4 of 5 is displaying correctly");Reporter.log("Step-4 of 5 is displaying correctly");}
		else {System.out.println("Step-4 of 5 is displaying incorrectly");Reporter.log("Step-4 of 5 is displaying incorrectly");}
		
		driver.findElement(By.id("ctl00_ctl11_cbConfirmPacket")).click();
		driver.findElement(By.id("ctl00_ctl12_rblSignatureOptions_1")).click();
		driver.findElement(By.id("ctl00_ctl12_btnChooseSignatureType")).click();
		driver.findElement(By.id("ctl00_ctl13_cbConfirmRequirements")).click();
		driver.findElement(By.id("ctl00_stepNav_bN")).click();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		
		if (driver.findElement(By.id("ctl00_ctl10_lblSM")).getText().contains("STEP 5 OF 5"))
			{System.out.println("Step-5 of 5 is displaying correctly");Reporter.log("Step-5 of 5 is displaying correctly");}
		else {System.out.println("Step-5 of 5 is displaying incorrectly");Reporter.log("Step-5 of 5 is displaying incorrectly");}
		try{
		Assert.assertTrue(driver.findElement(By.id("lblError")).getText().contains("You have completed the Online Enrollment Center."));
		}catch(Exception e){System.out.println("incorrect msg - You have completed the Online Enrollment Center.");Reporter.log("incorrect msg - You have completed the Online Enrollment Center."+e.getMessage());}
		if (driver.findElement(By.id("lblError")).getText().contains("You have completed the Online Enrollment Center."))
			{System.out.println("You have completed the Online Enrollment Center.");Reporter.log("You have completed the Online Enrollment Center.");}
			else{System.out.println("incorrect msg - You have completed the Online Enrollment Center.");Reporter.log("incorrect msg - You have completed the Online Enrollment Center.");}
		
		
  }

@AfterClass(groups={"OECFlow"})
public void closeBrowser(){
	driver.quit();
}
}