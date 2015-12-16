package AddVerifyLead;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FundingConfrm {
	private WebDriver driver;
	//private int leadID = 21128421;
	private String varWinhndl;
	
	//@Test
	//@Test(groups={"fundingConfirm"}, dependsOnGroups={"OECFlow"})
	@Parameters({"browser", "port"})
	//@Test(groups={"fundingConfirm"})
	@BeforeClass
	public void login(String browser, String port ) throws IOException, MalformedURLException{
	//public void login() throws IOException, MalformedURLException{
		//driver=new FirefoxDriver();
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName(browser);
		//driver = new RemoteWebDriver(new URL("http://10.78.4.12:".concat(port).concat("/wd/hub")), capability); 
		driver = new RemoteWebDriver(new URL("http://localhost:".concat(port).concat("/wd/hub")), capability);
		
		//Runtime.getRuntime().exec("C:\\Authentication1.exe");
		Runtime.getRuntime().exec("wscript C:\\LoginWindow1.vbs");
		//Runtime.getRuntime().exec("wscript \\10.78.4.12\\login\\LoginWindow1.vbs");
		driver.get(TestData.URL);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		varWinhndl = driver.getWindowHandle();
		//TillAppReceived.varLeadId = 20926627;
		TestData objTestData = new TestData();
		objTestData.ssoLogin(driver, "vmamgain", "kaplan");
	}
		
	//@Test(groups={"fundingConfirm"}, dependsOnMethods={"login"})
	@Test(groups={"fundingConfirm"})
  public void fundingConfirm()throws InterruptedException {
		System.out.println("8. Funding Confirmed.Html");
		Reporter.log("8. Funding Confirmed.Html");
	  //Funding Confirm
	  //driver.findElement(By.xpath("//tr[@id='_ctl2_TopNavRow']/td[6]")).click();
		driver.switchTo().window(varWinhndl);
		driver.switchTo().frame("Orion");
	  driver.findElement(By.xpath("//tr[@id='_ctl2_TopNavRow']/td[2]")).click();	  
	  
	  driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	  //driver.switchTo().frame("Orion");
	  driver.findElement(By.id("btnGo")).click();
	  driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	  
	  driver.findElement(By.id("tbNewLeads_LeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
	  driver.findElement(By.id("btnNewLeads_Filter")).click();
	  Thread.sleep(2000);
	  
	  //driver.findElement(By.id("nm"+Integer.toString(TillAppReceived.varLeadId))).click();
	  driver.findElement(By.id("nm"+TillAppReceived.varLeadId)).click();
	  
	  //driver.switchTo().window("win"+Integer.toString(TillAppReceived.varLeadId));
	  driver.switchTo().window("win"+TillAppReceived.varLeadId);
	  
	  driver.findElement(By.id("_ctl3_btnStatusChange")).click(); ////*[@id='_ctl3_btnStatusChange']
	  Thread.sleep(2000);
	  
	  if(driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText().contains("Funding Confirmed"))
		  {System.out.println("Lead status changed to:- Funding Confirmed");Reporter.log("Lead status changed to:- Funding Confirmed");}
	  else {System.out.println("Lead status not changed to:- Funding Confirmed");Reporter.log("Lead status not changed to:- Funding Confirmed");}
	  
	  driver.close();
	  
	  driver.switchTo().window(varWinhndl);
	  driver.switchTo().frame("Orion");
	  /*
	  //driver.navigate().refresh();
	   driver.findElement(By.xpath("//tr[2]/td/table/tbody/tr/td")).click();
	  
	   driver.findElement(By.xpath("//*[@id='_ctl2_TopNavRow']/td[2]")).click();
	   driver.findElement(By.name("btnGo")).click();
		System.out.println("Go button clicked successfully");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		//Search Link click
		driver.switchTo().window(varWinhndl);
		driver.switchTo().frame("Orion");
	   driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
		  
	  driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		  
	  driver.findElement(By.id("tbSearchAllLeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
	  driver.findElement(By.id("chkAllLeads")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(2000);
	 
	  if(driver.findElement(By.id("ss"+Integer.toString(TillAppReceived.varLeadId))).getText().contains("Funding Confirmed"))
		  {System.out.println("Lead Status correct displaying in search result grid as- Funding Confirmed");Reporter.log("Lead Status correct displaying in search result grid as- Funding Confirmed");}
	  else{System.out.println("Lead Status in-correct displaying in search result grid as- Funding Confirmed");Reporter.log("Lead Status in-correct displaying in search result grid as- Funding Confirmed");}
	    */	   
	  System.out.println("Finding confirm done");
	  Reporter.log("Finding confirm done");
  }
	
	@AfterClass(groups={"fundingConfirm"})
	public void closeBrowser(){
		driver.quit();
	}
}
