package AddVerifyLead;

//import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.internal.selenesedriver.ExecuteScript;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PendingEnrollment {
	private WebDriver driver;
	private String winHandle, varifyPhone=TestData.Home_Phone, varCampus="848 "+TestData.Area_Of_Study; //"848 Concord Law School"; "(954) 515-2211"
	
	
	//@Test(groups={"pendingEnrlmt"})
	@Parameters({"browser","port"})
	@BeforeClass
	public void openBrowser(String browser,String port) throws IOException, InterruptedException{
	//public void openBrowser() throws IOException, InterruptedException{
		//driver = new FirefoxDriver();
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName(browser);
		
		//driver = new RemoteWebDriver(new URL("http://10.78.4.12:".concat(port).concat("/wd/hub")), capability);
		driver = new RemoteWebDriver(new URL("http://localhost:".concat(port).concat("/wd/hub")), capability);
			
		Runtime.getRuntime().exec("wscript C:\\LoginWindow.vbs");
		//Runtime.getRuntime().exec("wscript \\10.78.4.12\\login\\LoginWindow.vbs");
		//Runtime.getRuntime().exec("C:\\Authentication.exe");
		//driver.get(TillAppReceived.AppURL);
		driver.get(TestData.URL);
		winHandle = driver.getWindowHandle();
		Thread.sleep(4000);
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");
		//TillAppReceived.varLeadId =   20926673;
		/*
	    driver.findElement(By.xpath("//tr[@id='_ctl2_TopNavRow']/td[2]")).click();
		driver.switchTo().window(winHandle);
	    driver.switchTo().frame("Orion");
	    driver.findElement(By.id("btnGo")).click();
		driver.switchTo().window(winHandle);
	    driver.switchTo().frame("Orion");
	    driver.findElement(By.linkText("Search")).click();
		driver.switchTo().window(winHandle);
	    driver.switchTo().frame("Orion");
			*/
		/*TillAppReceived.varLeadId=20926626;
		TillAppReceived.fn=164;
		TillAppReceived.ln=40;*/
		
	}
	
  //@Test(groups="pendingEnrlmt", dependsOnMethods="openBrowser", alwaysRun= true)
  @Test(groups="pendingEnrlmt")
  public void pendingEnrollment() throws InterruptedException {
	  
	  System.out.println("9. MOVE_LEAD_TO_PENDING_ENROLLMET.Html");
	  Reporter.log("9. MOVE_LEAD_TO_PENDING_ENROLLMET.Html");
	   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//driver.switchTo().frame("Orion");	
	
		driver.findElement(By.xpath("//*[@id='_ctl2_TopNavRow']/td[2]")).click();
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		driver.switchTo().window(winHandle);
	    driver.switchTo().frame("Orion");
	    
		driver.findElement(By.name("btnGo")).click();
		System.out.println("Go button clicked successfully");
		
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");
		
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
		driver.findElement(By.id("tbSearchAllLeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
		
		driver.findElement(By.id("btnSearchAllFilter")).click();
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		
		driver.findElement(By.id("nm"+TillAppReceived.varLeadId)).click();
		//driver.findElement(By.id("nm"+Integer.toString(TillAppReceived.varLeadId))).click();
		driver.switchTo().window("StudentInfo");
		driver.findElement(By.xpath("//a[contains(text(),'Appointments')]")).click();
		driver.findElement(By.id("_ctl3_dpInterviewHour")).sendKeys("11");
		driver.findElement(By.id("_ctl3_dpInterviewMinute")).sendKeys("45");
		driver.findElement(By.id("_ctl3_tbnotes")).sendKeys("Pending Enrollement");
		driver.findElement(By.id("_ctl3_btnSave")).click();
		
		try{
		
		if(driver.findElement(By.xpath("//input[@id='_ctl3_btnSaveYes']")).isDisplayed()){
			driver.findElement(By.xpath("//input[@id='_ctl3_btnSaveYes']")).click();
		}
		}catch(Exception e){System.out.println(""+e.getMessage());}
		
		
		//Click on Contact Information link
		driver.findElement(By.xpath("//a[contains(text(),'Contact Information')]")).click();
		Thread.sleep(4000);
		try{
			Assert.assertTrue(driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText().contains("Pending Enrollment"));
			System.out.println("Lead Status changed to Pending Enrollment");Reporter.log("Lead Status changed to Pending Enrollment");
		}catch(Exception e){System.out.println("Lead Status not changed to Pending Enrollment");Reporter.log("Lead Status not changed to Pending Enrollment");}
		
		if(driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText().contains("Pending Enrollment"))
			{System.out.println("Lead Status changed to Pending Enrollment");Reporter.log("Lead Status changed to Pending Enrollment");} 
		else
			{System.out.println("Lead Status not changed to Pending Enrollment");Reporter.log("Lead Status not changed to Pending Enrollment");}
		driver.close();
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");
		driver.findElement(By.id("tbSearchAllLeadID")).clear();
		System.out.println("MOVE_LEAD_TO_PENDING_ENROLLMET done");
				
  }
  

	//@Test(groups={"pendingEnrlmt"}, dependsOnMethods={"openBrowser"}, alwaysRun=false)
  @Test(groups={"pendingEnrlmt"}, dependsOnMethods={"pendingEnrollment"}, alwaysRun=false)
  public void searchLead() throws InterruptedException
  {
	  
	  System.out.println("10. SEARCH_LEAD.Html");
	  Reporter.log("10. SEARCH_LEAD.Html");
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  try{
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='lblBeginDate']")).getText(), "Lead Date Begin:", "Lead Date Begin: is correct");
	  }catch(Exception e){Reporter.log("Lead Date Begin: is incorrect"+e.getMessage());System.out.println("Lead Date Begin: is incorrect");}
	  try{
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='lblEndDate']")).getText(), "Lead Date End:", "Lead Date End: correctly displaying");
	  }catch(Exception e){Reporter.log("Lead Date End: incorrectly displaying"+e.getMessage());System.out.println("Lead Date End: incorrectly displaying");}
	  try{
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='lblFirstName']")).getText(), "First Name:", "First Name: corretly displaying");
	  }catch(Exception e){Reporter.log("First Name: incorretly displaying"+e.getMessage());System.out.println("First Name: incorretly displaying");}
	  try{
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='lblLastName']")).getText(), "Last Name:", "Last Name: correctl displaying");
	  }catch(Exception e){Reporter.log("Last Name: incorrectly displaying"+e.getMessage());System.out.println("Last Name: incorrectly displaying");}
	  
	  if(driver.findElement(By.xpath("//span[@id='lblEmail']")).getText().contains("Email:"))
		  {System.out.println("Email displaying correctly");Reporter.log("Email displaying correctly");}
	  else {System.out.println("Email not displaying correctly");Reporter.log("Email not displaying correctly");}
	  if(driver.findElement(By.xpath("//span[@id='lblPhone']")).getText().contains("Phone:"))
		  {System.out.println("Phone: - displaying correctly");Reporter.log("Phone: - displaying correctly");}
		  else {System.out.println("Phone: - not displaying correctly"); Reporter.log("Phone: - not displaying correctly");  }
	  
	  
	  //driver.findElement(By.id("tbSearchAllFname")).sendKeys("AutoJan"+Integer.toString(TillAppReceived.fn));
	  //driver.findElement(By.id("tbSearchAllLname")).sendKeys("AutoJan"+Integer.toString(TillAppReceived.ln));//
	  //--------------
	  driver.findElement(By.id("tbSearchAllFname")).sendKeys(TestData.Lead_First_Name);
	  driver.findElement(By.id("tbSearchAllLname")).sendKeys(TestData.Lead_Last_Name);
	  //--------------------
	  
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  
	  Thread.sleep(3000);
	  
	  //driver.findElement(By.id("nm"+TillAppReceived.varLeadId)).click();
	  driver.findElement(By.id("nm"+Integer.toString(TillAppReceived.varLeadId))).click();
	  driver.switchTo().window("StudentInfo");
	  
	  //tempPhNo = driver.findElement(By.id("_ctl3_ciphone")).getText();
	  //TempLeadStatus = driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText();
	  
	  driver.close();
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  
	  
	  driver.findElement(By.id("tbSearchAllFname")).clear();
	  driver.findElement(By.id("tbSearchAllLname")).clear();
	  //driver.findElement(By.id("tbSearchAllEmail")).sendKeys("AutoJan"+TillAppReceived.fn+"@gmail.com");
	  driver.findElement(By.id("tbSearchAllEmail")).sendKeys(TestData.Email);
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  
	  Thread.sleep(3000);
	  //if (driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[6]")).getText().contains("AutoJan"+TillAppReceived.fn+"@gmail.com"))
	  if (driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[6]")).getText().contains(TestData.Email))
		  {System.out.println("Email is displaying in search grid");Reporter.log("Email is displaying in search grid");}
	  else {System.out.println("Email is not displaying in search grid");Reporter.log("Email is not displaying in search grid");}
	  
	  driver.findElement(By.id("tbSearchAllEmail")).clear();
	  
	  driver.findElement(By.id("tbSearchAllPhone")).sendKeys(varifyPhone);
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(6000);
	  	  
	  try{
	  	 //Assert.assertEquals(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[4]")).getText().trim(), varifyPhone.trim(), "Phone no: correctly displaying");
		 if(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[4]")).getText().contains(varifyPhone))
			 System.out.println("Phone no displaying correctly");else System.out.println("Phone no not displaying correctly"); 
		 
	  }catch (Exception e){System.out.println("Phone no :either unable to locate in grid or incoorectly searched"+e.getMessage());Reporter.log("Phone no :incorrectly displaying in search grid");}
	  
	  try{
	  
		  driver.switchTo().window(winHandle);
		  driver.switchTo().frame("Orion");
	  driver.findElement(By.id("tbSearchAllPhone")).clear();
	  }catch(Exception e){System.out.println("Phone no not clear from text box, in the search page");}
	  try{
	  new Select(driver.findElement(By.id("ddlStatus"))).selectByVisibleText("New Assigned");
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(3000);

	  Assert.assertEquals(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[10]")).getText(), "New Assigned", "New Assigned :- is displaying incorrectly");
	  if(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[10]")).getText().contains("New Assigned"))
		  {System.out.println("New Assigned:- displaying correctly on search table");Reporter.log("New Assigned:- displaying correctly on search table");}
	  else {System.out.println("New Assigned:- displaying in correctly on search table");Reporter.log("New Assigned:- displaying in correctly on search table");}
	  }catch(Exception e){System.out.println("New Assigned:- displaying incorrectly on search table");Reporter.log("New Assigned:- displaying in correctly on search table"+e.getMessage());}
	  
	  try{
	  new Select(driver.findElement(By.id("ddlStatus"))).selectByVisibleText("");
	  new Select(driver.findElement(By.id("ddlState"))).selectByVisibleText("Georgia");
	  
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(6000);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[8]")).getText(), "GA", "Georgia not displaying correctly");
	  if(driver.findElement(By.xpath("//*[@id='dgSearchAll']/tbody/tr[2]/td[8]")).getText().contains("GA"))
		 System.out.println("Georgia displaying correctly in search results");else System.out.println("Georgia displaying incorrectly in search results");
	  }catch(Exception e){Reporter.log("Georgia - State is not searched or taking too long to search");System.out.println("Georgia - State is not searched or taking too long to search");}
	  
	  try{
	  new Select(driver.findElement(By.id("ddlState"))).selectByVisibleText("");
	  new Select(driver.findElement(By.id("ddlProgram"))).selectByIndex(1);
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(6000);
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[12]")).getText(), "Executive JD Program", "Executive JD Program:- not displaying correctly");
	  if (driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[12]")).getText().contains("Executive JD Program"))
		  {System.out.println("Executive JD Program:- in search results correct");Reporter.log("Executive JD Program:- in search results correct");}
	  else {System.out.println("Executive JD Program:- in search results incorrect");Reporter.log("Executive JD Program:- in search results incorrect");} 
	  }catch(Exception e){Reporter.log("Executive JD Program:- is either not searched or taking too long to search"); System.out.println("Executive JD Program:- is either not searched or taking too long to search");}
	  
	  try{
	  new Select(driver.findElement(By.id("ddlProgram"))).selectByVisibleText("");
	  driver.findElement(By.id("tbSearchAllLeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(3000);
//	  if (driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[6]")).getText().contains("AutoJan"+TillAppReceived.fn+"@gmail.com"))
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[6]")).getText(), TestData.Email, "Email is displaying in-correctly");
		  if (driver.findElement(By.xpath("//table[@id='dgSearchAll']/tbody/tr[2]/td[6]")).getText().contains("AutoJan18@gmail.com"))
		  {System.out.println("email displaying correctly");Reporter.log("email displaying correctly");}
		  else {System.out.println("email displaying in-correctly");Reporter.log("email displaying in-correctly");}
	  }catch(Exception e){Reporter.log("email search either not searched or taking too long to search");System.out.println("email search either not searched or taking too long to search");}
	  driver.navigate().back();
	  driver.navigate().refresh();
	  Thread.sleep(4000);  
  }
  
  //@Test(groups={"pendingEnrlmt"}, dependsOnMethods={"searchLead"}, alwaysRun=true)
  @Test(groups={"pendingEnrlmt"}, dependsOnMethods={"searchLead"}, alwaysRun=false)
  public void reassignLead()throws InterruptedException{
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");
	  //driver.findElement(By.xpath("//tr[@id='_ctl2_TopNavRow']/td[2]")).click();
	  driver.findElement(By.xpath("//*[@id='_ctl2_TopNavRow']/td[2]")).click();
	  
	  							  
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  //driver.switchTo().frame("Orion");
	  driver.findElement(By.xpath("//a[contains(text(),'Admin')]")).click();
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  //driver.switchTo().frame("Orion");
	  driver.findElement(By.xpath("//a[contains(text(),'Reassign Leads')]")).click();
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  //driver.switchTo().frame("Orion");
	  new Select(driver.findElement(By.id("dpCampus_ddlCampus"))).selectByVisibleText(varCampus);
	  driver.findElement(By.id("tbLeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
	  //driver.findElement(By.id("tbLeadID")).sendKeys(Integer.toString(TillAppReceived.varLeadId));
	  
	  driver.findElement(By.id("btnSearchLeads")).click();
	  
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  //if (driver.findElement(By.xpath("//table[@id='dgLeads']/tbody/tr[2]/td[2]")).getText().contentEquals(Integer.toString(TillAppReceived.varLeadId)))
		  if (driver.findElement(By.xpath("//table[@id='dgLeads']/tbody/tr[2]/td[2]")).getText().contentEquals(Integer.toString(TillAppReceived.varLeadId)))
			  	{System.out.println("Lead id correctly displaying in search grid");Reporter.log("Lead id correctly displaying in search grid");}
		  else {System.out.println("Lead id incorrectly displaying in search grid");Reporter.log("Lead id incorrectly displaying in search grid");}  
	
		  driver.findElement(By.id("cbLeadID")).click(); 
	  
	  new Select(driver.findElement(By.id("dpTeamTo"))).selectByVisibleText("Term - Terminated Users");
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  try{	  
	  driver.findElement(By.id("cbLeadID")).click();
	  if (!driver.findElement(By.id("cbLeadID")).isSelected()){
		  driver.findElement(By.id("cbSelectAll")).click();
	  }
	  }catch(Exception e){System.out.println("Lead did not selected in grid");Reporter.log("Lead did not selected in grid");}
	  Thread.sleep(2000);
	  driver.findElement(By.id("lbToReps")).sendKeys("Gupta, Anuj");
	  	  
	  try{
	  
	  driver.findElement(By.id("btnAssign")).click();
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  
	  if(driver.findElement(By.id("lblReassignComplete")).getText().contentEquals("Reassign Successful."))
		  {System.out.println("Reassign Successful.:- displaying correctly");Reporter.log("Reassign Successful.:- displaying correctly");}
	  else {System.out.println("Reassign Successful.:- displaying incorrectly");Reporter.log("Reassign Successful.:- displaying incorrectly");}
	  }catch(Exception e){Reporter.log("Reassign UnSuccessful");System.out.println("Reassign UnSuccessful");}

	  new Select(driver.findElement(By.id("dpTeamFrom"))).selectByVisibleText("Term - Terminated Users");
	  new Select(driver.findElement(By.id("dpRepFrom"))).selectByVisibleText("Gupta, Anuj");
	  new Select(driver.findElement(By.id("dpStatus"))).selectByVisibleText("Reassigned");
	  driver.findElement(By.id("btnSearchLeads")).click();
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  new Select(driver.findElement(By.id("dpTeamTo"))).selectByVisibleText("Term - Terminated Users");
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  new Select(driver.findElement(By.id("dpRepFrom"))).selectByVisibleText("Gupta, Anuj");
	  //Hyde, Eric - select the user from text area
	  try{	  
	  driver.findElement(By.id("cbLeadID")).click();
	  driver.findElement(By.id("lbToReps")).sendKeys("Hyde, Eric");
	  driver.findElement(By.id("btnAssign")).click();
	  
	  if(driver.findElement(By.id("lblReassignComplete")).getText().contains("Reassign Successful."))
		  {System.out.println("Reassign Successfull");Reporter.log("Reassign Successfull");}
	  	else {System.out.println("Reassign unSuccessfull");Reporter.log("Reassign unSuccessfull");}
	  }catch(Exception e){Reporter.log("Reassign UnSuccessful"+e.getMessage());System.out.println("Reassign UnSuccessful"+e.getMessage());}
	
  }
  @AfterClass(groups={"pendingEnrlmt"})
  public void closeBrowser(){
	  //driver.quit();
  }
  
}
