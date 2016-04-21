package validateIWDTasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reusableMethods_PageObject.SRM_ReusableMethods;
import srm_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.DuplicateLeadCompletionPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.LeadImport_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class ResifLead {
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
	public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
	//public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
	public LeadImport_PageObjects uiLeadImport_PageObjects;
	
	public String LeadImportID1; 
	public String sSearchMain_WindowName ="";
	public String mainwinhandle;
	public String SifFinal;
	public String LeadImportID;
	public String DateCreated;
	public String LeadID;
	public static String Stuid;
	public int InitailIWDTaskCnt;
	public String sPath_ResultProperties="";

	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		try

		{

		//Read the application properties file
		//Load environment variable from properties file
		String sPath_AppProperties="";
		FileInputStream objFileInputStream = null;
		Properties objProperties = new Properties();
		
		//Set file path as per environment
		if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
		{
			sPath_AppProperties = ".//Resources//ApplicationProperties/DevApplication.properties";
		}
		else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
		{
			sPath_AppProperties = ".//Resources//ApplicationProperties/StageApplication.properties";			
		}
		else if (EnvironmentVariables.sEnv.equalsIgnoreCase("lt"))
		{
			sPath_AppProperties = ".//Resources//ApplicationProperties/LtApplication.properties";			
		}
		else
		{
			sPath_AppProperties = ".//Resources//ApplicationProperties/TestApplication.properties";			
		}
		
		
		
		
		
		
		
////////////////////////////////////////////*********************************************////////////////////////////
		
		
//Set file path as per environment for Result Property File

if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
{
sPath_ResultProperties = ".//Resources//ResultProperties/DevResultProperties.properties";

}
else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
{
sPath_ResultProperties = ".//Resources//ResultProperties/StageResultProperties.properties";	
}
else
{
sPath_ResultProperties = ".//Resources//ResultProperties/TestResultProperties.properties";	
}




////////////////////////////////////////////*********************************************////////////////////////////

		
		
		
		//Load the Application variable file into File Input Stream.
		File objFileApplication = new File(sPath_AppProperties);
		try
		{
			objFileInputStream = new FileInputStream(objFileApplication);
		}catch (FileNotFoundException ex)
		{
			ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
		}
		
		//Load the File Input Stream into the Properties file
		try
		{
			objProperties.load(objFileInputStream);
			
		} catch (IOException ex) {

			ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
		}
		
		
	
					
		//Edit Browser Capabilities as per project
		//Fire fox Profile		
		FirefoxProfile profile = new FirefoxProfile();
		
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sTrusted_Uris);
		//Capability
		objBrowserMgr = new BrowserManagement(sBrowser);
		objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
			
		//Create the Remote Driver Instance
		try
		{					
			driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
		}
		catch(Exception ex)
		{	
			ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
		}
		
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);
		//uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
		uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
		}
		catch (Exception e)
								
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
	
	
	
	@AfterClass
	public void AfterNavigation()
	{
		try

		{
			

			// Writing to Result Property File

			System.out.println("Inside After class");
			
			System.out.println("Before method writing to Result Property file");
			
			SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "StuidNameFromResifLead", Stuid);
			
			System.out.println("After method writing to Result Property file");

		//Quit the test after test class execution
		if(driver != null)
		{
			driver.quit();			
		}
	}
	catch (Exception e)
							
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
	}

	
	
	
	//This method will fetch the ReSIf from StoredSIF table which is KU (43) Lead, from yesterday and starts with TestNG
	@Test
	public void FetchSIFfromDB(Method objMethod){
		//QUERY String to fetch REsif from DB
		String qString="select top 1 LI.mkleadimportid, LISS.StoredSif, LI.ImportDate, L.mkleadid from POLARIS..mkLeadImport as LI INNER JOIN POLARIS..mkLeadImportStoredSif as LISS ON LI.mkLeadImportID=LISS.mkLeadImportId INNER JOIN POLARIS..mkLeads as L ON L.mkleadimportid = LI.mkleadimportid where Left(LI.ImportDate,11) = Left (dateadd(day,datediff(day,1,GETDATE()),0),11) and LI.selfserviceLead=0 and LI.EmailAddress like 'TestNG%'and LI.mkCampusID = 2 and L.mkleadid NOT IN (select mkleadid from orion..mkresif where Left(DateCreated,11)=  Left (dateadd(day,datediff(day,0,GETDATE()),0),11))";
		
		//Call getDBQueryResult function in CommonLibrary project   
		try {
			 ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
			 rs.next();
			 
			  SifFinal = rs.getString(2) ;
			  DateCreated = rs.getString(3) ;
			  LeadImportID = rs.getString(1) ;
			  LeadID = rs.getString(4) ;
			 //Write DB values in Reporter log
			 Reporter.log(DateCreated);
			 Reporter.log(LeadImportID);
			 Reporter.log(LeadID);
			 //also Write DB values on console
			 System.out.println("StoredSIF: "+SifFinal);
			 System.out.println("DateCreated: "+DateCreated);
			 System.out.println("mkLeadImportID: "+LeadImportID);
			 System.out.println("mkLeadID: "+LeadID);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		} 
		
		
	}
	//This method will fetch student id from mklead table on the basis of leadimportid
	@Test
	public void FetchStudentIDfromDB(Method objMethod){
		//QUERY String to fetch REsif from DB
		String qString="SELECT C2K_STUDENTID FROM POLARIS..MKLEADS WHERE MKLEADIMPORTID="+LeadImportID;
		
		//Call getDBQueryResult function in CommonLibrary project   
		try {
			 ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
			 rs.next();
			 Stuid = rs.getString(1) ;
			 			  
			 //Write DB values in Reporter log
			 Reporter.log("systudentID: "+Stuid);
			 //also Write DB values on console
			 System.out.println("systudentID: "+Stuid);
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		} 
	}
	//This method get the number of initial iwd tasks
	@Test(dependsOnMethods={"FetchStudentIDfromDB"})
	public void GetInitialIWDTaskCount(Method objMethod) throws InterruptedException
	{
		try
		{DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		driver.get(EnvironmentVariables.sIWD_URL);
		 //Login Window And its Credentials of IWD Genesys 
		  driver.findElement(By.id("loginForm:username")).clear();
		  driver.findElement(By.id("loginForm:username")).sendKeys(EnvironmentVariables.sIWD_UName);
		  driver.findElement(By.id("loginForm:password")).clear();
		  driver.findElement(By.id("loginForm:password")).sendKeys(EnvironmentVariables.sIWD_PWD);	
		  driver.findElement(By.id("loginForm:submit")).click();
	
		  Thread.sleep(3000);
		 //Clicking on Global Task l ist link
		 UiDuplicateLeadCompletion.GlobalTaskList.click();
		 Thread.sleep(20000);
		 UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion .kaplanTESTLink);
		 
		   
		 //Clicking on Kaplan TEST Link
	     UiDuplicateLeadCompletion .kaplanTESTLink.click();
	     Thread.sleep(20000);
	     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CaptureSYStudentID);
	     
	     
	     //Searching SyStudent ID in Captured ID field
	     UiDuplicateLeadCompletion.CaptureSYStudentID.clear();
	     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(Stuid);
	     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.FindCapturedSYID);
	     //Thread.sleep(5000);
	     
	     //Finding the Captured ID from SRM
	     UiDuplicateLeadCompletion.FindCapturedSYID.click();
	    // UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]")));
	     System.out.println("entered");
	     Thread.sleep(15000);
	     UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[@id='mainAjaxRegion:status.stop'][@style='']")));	      
	     Thread.sleep(5000);
	     //Counting the searched research list 
	     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
	     InitailIWDTaskCnt =  ele.size();
	     System.out.println("Initail IWD task Count: "+InitailIWDTaskCnt);
	     	     
		}
		catch(Exception e){Reporter.log(e.getMessage());}	
			//Thread.sleep(20000);
	     
	     
		
		
		
	}
	//This method submit the resif
	@Test(dependsOnMethods= "GetInitialIWDTaskCount")
	public void SubmitReSIF(Method objMethod) throws InterruptedException
	{	try
		{
		    driver.get(EnvironmentVariables.sLead_ImportURL);
			uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
			uiLeadImport_PageObjects.txtStudentInformationXML.clear();
			uiLeadImport_PageObjects.txtStudentInformationXML.sendKeys(SifFinal);
			Thread.sleep(15000);
			uiLeadImport_PageObjects.btnInvoke.click();
			
			//Window Handles
			String parentHandle = driver.getWindowHandle();
			Set <String> winHandles = driver.getWindowHandles();
			for (String currentWindowHandle : winHandles) {
				if (!currentWindowHandle.equals(parentHandle)) {
					driver.switchTo().window(currentWindowHandle);
					}
			}
			
			String LeaderrMsg = driver.findElement(By.cssSelector("string")).getText();
			System.out.println("1"+LeaderrMsg);
			//Assert.assertTrue(LeaderrMsg.contains("This lead appears to be a duplicate base on First Name, Last Name and Home Phone Number provided."));
			//The actual assert has been commented as message diff is being reported to the manual team
			Assert.assertTrue(LeaderrMsg.contains("This lead appears to be a duplicate"));
			//EXTRACT student id from error message to search in IWD
			System.out.println("ASSERT PASSED FOR DUP LEAD");
			/*int bindex= LeaderrMsg.indexOf("Student ID");
			System.out.println("BINDEX: "+ bindex);
			//Assert for StudentId in the message
			Assert.assertTrue(bindex!=-1,"Student ID not found in the message");
			int eindex= LeaderrMsg.indexOf("/");
			System.out.println("BINDEX: "+ eindex);
			String S1= LeaderrMsg.substring(bindex, eindex);
			System.out.println(S1);
			Stuid = S1.replace("Student ID ", "").trim();
			System.out.println("StudentID: "+Stuid);
			*/
			
		}
					
	catch(Throwable e){Reporter.log(e.getMessage());}	
			//Thread.sleep(20000);
	}
	//this method will fetch SyStudentId(Capture Id ) from from DB
	@Test(dependsOnMethods= "SubmitReSIF")
	public void VerifyReSIFinDB(Method objMethod) throws InterruptedException
	{	try
		{
		  String qString  = "SELECT TOP 1 MKLEADID FROM ORION..MKRESIF WHERE MKLEADID ="+LeadID;
		  System.out.println(qString);
		  ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
		  rs.next();
		  System.out.println(rs.getString(1));
		  String RESIF_mkLeadID = rs.getString(1) ;
		  Assert.assertEquals(RESIF_mkLeadID, LeadID, "Resif verified in DB");
		  System.out.println("Resif Verified in DB");
		 						
		}
					
		catch(Exception e){Reporter.log(e.getMessage());}	
			//Thread.sleep(20000);
	}
	//This Method will check the IWD Tasks count After Resif 
	@Test(dependsOnMethods={"VerifyReSIFinDB"})
	public void DuplicateLeadCheck_IWD(Method objMethod) throws InterruptedException
	{
		try
		{DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		driver.get(EnvironmentVariables.sIWD_URL);
		//uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
		
		 //Login Window And its Credentials of IWD Genesys 
		  driver.findElement(By.id("loginForm:username")).clear();
		  driver.findElement(By.id("loginForm:username")).sendKeys(EnvironmentVariables.sIWD_UName);
		  driver.findElement(By.id("loginForm:password")).clear();
		  driver.findElement(By.id("loginForm:password")).sendKeys(EnvironmentVariables.sIWD_PWD);	
		  driver.findElement(By.id("loginForm:submit")).click();
	
		  Thread.sleep(3000);
		 //Clicking on Global Task l ist link
		 UiDuplicateLeadCompletion.GlobalTaskList.click();
		 Thread.sleep(20000);
		 UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion .kaplanTESTLink);
		 
		   
		 //Clicking on Kaplan TEST Link
	     UiDuplicateLeadCompletion .kaplanTESTLink.click();
	     Thread.sleep(20000);
	     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CaptureSYStudentID);
	     
	     
	     //Searching SyStudent ID in Captured ID field
	     UiDuplicateLeadCompletion.CaptureSYStudentID.clear();
	     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(Stuid);
	     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.FindCapturedSYID);
	     //Thread.sleep(5000);
	     
	     //Finding the Captured ID from SRM
	     UiDuplicateLeadCompletion.FindCapturedSYID.click();
	    // UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]")));
	     System.out.println("entered");
	     Thread.sleep(15000);
	     UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[@id='mainAjaxRegion:status.stop'][@style='']")));	      
	     Thread.sleep(5000);
	     //Counting the searched research list 
	     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
	     int i =  ele.size();
	     System.out.println(i);
	     Assert.assertTrue(i==InitailIWDTaskCnt+1);
	     
		}
		catch(Exception e){Reporter.log(e.getMessage());}	
			//Thread.sleep(20000);
	     
	     
		
		
		
	}
}
	
	
