 package validateTCPA_EWC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;  
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import srm_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.DuplicateLeadCompletionPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class TCPA_YesFuctionality {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser cap
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
	public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
	public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
	public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
	public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
	
	
	//Variables from Properties file
	String sFirstName1;
    String sLastName1;
    String sEmailAddress1;
	public String sMilitaryType;
	public String sSpouseMilitary;
	public String sTCPA;
	public String sHighestEducation;
	
	//Static variable
	public String sPath_ResultProperties="";
	public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public static String sFirstName = "TestNGFNEWCYES_" + sRandStr;
	public static String sLastName = "TestNGLNEWCYES_" + sRandStr;			
	public static String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545154479";
	public String sZipCode = "33309";
	public String sSyStuID= "";
	public String sEmailID="";
	public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";
	
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
		
		
		//Reading from Properties file
	
		sTCPA = objProperties.getProperty("sTCPADisclosure");
		sMilitaryType = objProperties.getProperty("sMilitaryType");
		sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
		sHighestEducation = objProperties.getProperty("sHighestEduction");
	
					
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
		driver.get(EnvironmentVariables.sSRM_Url);
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);			
	}
	catch(Exception e)
	{
		Reporter.log(e.getMessage());
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
			
			SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromTCPA_YesFuctionality", sEmailAddress);
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void BrowseToAddNewReferralLeadPage(Method objMethod) throws InterruptedException
	{
		try{
			uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
		
		uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
		uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
        Thread.sleep(3000);
        
        uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
		
		
		WebDriverWait wait = new WebDriverWait(driver, 1000);			
		driver.switchTo().frame("ext-comp-1005");
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
		
		
		//Select Referral Radio Button
		uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral.click();
		
		Thread.sleep(10000);
		
		uiAddInquiry_Referral_Lead_Pageobjects.txtFirstName.sendKeys(sFirstName);
		uiAddInquiry_Referral_Lead_Pageobjects.txtLastName.sendKeys(sLastName);
		uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.sendKeys(sEmailAddress);
		System.out.println(sEmailAddress);
		
		sEmailAddress1 =uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.getAttribute("value");
		
		uiAddInquiry_Referral_Lead_Pageobjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
		uiAddInquiry_Referral_Lead_Pageobjects.txtZipCode.sendKeys(sZipCode);
		
		
		//TCPA Disclosure
		if(sTCPA.equalsIgnoreCase("yes"))
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_No.click();					
		}
		else
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_Yes.click();
		}
		
		
		//Spouse Military Status
		if(sSpouseMilitary.equalsIgnoreCase("yes"))
		{
			
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnSpouse_Yes.click();
			
		}
		else
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnSpouse_No.click();
		}
		
		
		//Highest Level of Education
		Select ddlHightestEdution = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddHighestLevelEducation);
		ddlHightestEdution.selectByVisibleText(sHighestEducation);
		
		Thread.sleep(3000);
		
     
		uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
		
		Thread.sleep(3000);
		
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);				
		Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
		
		driver.navigate().refresh();
		
		uiReusableMethods_PageObjects.lnkDropDown.click();
		Thread.sleep(3000);
		uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
		Thread.sleep(1000);
		}
		
		catch (Exception e)
		
		{
			Reporter.log(e.getMessage());
		}
		}			

	@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
	public void VerifyingSubmittedLeadEWCStatusViaMyInquiries(Method objMethod) throws InterruptedException
	{
		try{
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			driver.get(EnvironmentVariables.sSRM_Url);
			
			//Navigate to My Inquiries
			
			//Clicking on Add Symbol for menu extension
			uiAddNewLeadsPageObjects.ExtMenu.click();
			Thread.sleep(10000);
			
			//Clicking on Inquiries link 
			uiAddNewLeadsPageObjects.lnkAllInquiry.click();
			UserExtension.IsElementPresent(driver, driver.findElement(By.xpath(".//*[@name='fcf']")));
			Thread.sleep(5000);
			
					
			//Verify inquiry
			String InqStr =  "//a[text()="+"'"+sLastName+", "+sFirstName+"']";
			System.out.println(InqStr);
			Assert.assertTrue(driver.findElement(By.xpath(InqStr)).isDisplayed());

			//Verify EWC Status
			driver.findElement(By.xpath(InqStr)).click();
			
			//Verifying Ewc Value is Yes 
			uiAddNewLeadsPageObjects.txtEwcField.getText();
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "Yes");
			System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
			
			//Verifying Do Not Email Value is No 
			uiAddNewLeadsPageObjects.txtDoNotEmail.getText();
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtDoNotEmail.getText(), "No");
	        System.out.println(uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
		    Thread.sleep(10000);
		}
		catch (Exception e)
		
		{
			Reporter.log(e.getMessage());
		}
		
	}
	
	@Test(dependsOnMethods={"VerifyingSubmittedLeadEWCStatusViaMyInquiries"})
	public void VerifyEWCandDonotEmailStatusInSRM(Method objMethod) throws InterruptedException
	
	{try{
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		//driver.get(EnvironmentVariables.sSRM_Url);
		uiAddNewLeadsPageObjects.BtnHomeButton.click();
		Thread.sleep(5000);
		
		//Clearing the search field 
		uiAddNewLeadsPageObjects.search_SRM.clear();
		
		//sending keys to the search field 
		uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
			
		//clicking on search button  
		
		uiAddNewLeadsPageObjects.btnsearch_SRM.click();
		//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
		Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
		
		//Click on The Inquiry Lead 
		uiAddNewLeadsPageObjects.lnkSearchResult.click();
		
		//Verifying Ewc Value is Yes 
		uiAddNewLeadsPageObjects.txtEwcField.getText();
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "Yes");
		System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
		
		//Verifying Do Not Email Value is No 
		uiAddNewLeadsPageObjects.txtDoNotEmail.getText();
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtDoNotEmail.getText(), "No");
        System.out.println(uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
	    Thread.sleep(10000);
        
	    
	    // Clicking on Edit to Change EWC Value to No 
        uiAddNewLeadsPageObjects.lblEditButton.click();
        
        //Clicking on Ewc Drop Down Button To change The Value to "NO"
        uiAddNewLeadsPageObjects.DrpdnBtnEWC.click();
        
        //Selecting EWC Value NO from the DRp Dwn 
        uiAddNewLeadsPageObjects.lstNoEWC.click();
        
        //Saving the Edited 
        uiAddNewLeadsPageObjects.TabSave.click();
        Thread.sleep(10000);
        
        //Verifying Inquiry History Changes
        uiAddNewLeadsPageObjects.lstInquiryHistory1.getText();
        Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText(), "Changed EWC from Yes to No.");
        System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText());
        Thread.sleep(10000); 
	}
	
	catch (Exception e)
	
	{
		Reporter.log(e.getMessage());
	}
	
	}
	@Test(dependsOnMethods={"VerifyEWCandDonotEmailStatusInSRM"})
	public void VerifyEWCandDonotEmailStatusChanges(Method objMethod) throws InterruptedException
	{
		try{
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			
			//Clicking on Edit Button for Changes 
			uiAddNewLeadsPageObjects.lblEditButton.click();
			
			//Clicking on Ewc Drop Down Button To change The Value to "YES"
	        uiAddNewLeadsPageObjects.DrpdnBtnEWC.click();
	        
	        //Clicking on "YES" from the Drop Down 
	        uiAddNewLeadsPageObjects.lstYesEWC.click();
	        
	        //Clicking on Do Not Email Drop Down Button to Change the Value to "YES"
	        uiAddNewLeadsPageObjects.DrpDnBttnDoNotEmail.click();
	        
	        //Clicking on "YES" from the Drop Down 
	        uiAddNewLeadsPageObjects.lstDoNotEmailYes.click();
	        
	        
	        //Clicking on Save for the changes Done 
	        uiAddNewLeadsPageObjects.TabSave.click();
	        Thread.sleep(1000);
	         
	        //Verifying Ewc Value is Yes after changes 
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "Yes");
			System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
			Thread.sleep(20000);
			
			//Verifying Do Not Email Value is Yes after changes 
			System.out.println("donotemail"+uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
			Assert.assertTrue(uiAddNewLeadsPageObjects.txtDoNotEmail.getText().trim().contains("Yes"));
			System.out.println(uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
		    Thread.sleep(20000);
		    
		    //verifying EWC changes in Inquiry history 
		    uiAddNewLeadsPageObjects.lstInquiryHistory1.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText(), "Changed EWC from No to Yes.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText());
		    
		    //Verifying Do Not Email Changes in Inquiry History
		    System.out.println("DoNotEmailFromNoToYes"+uiAddNewLeadsPageObjects.lstInquiryHistory2.getText());
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory2.getText(), "Changed Do Not Email from No to Yes.");
			
			
		}
		catch (Exception e)
		
		{
			Reporter.log(e.getMessage());
		}
	
	}
	@Test(dependsOnMethods={"VerifyEWCandDonotEmailStatusChanges"})
	public void ConvertingLeadFomNewToContacted(Method objMethod) throws InterruptedException
	{
		try
		{
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		
		
		//Clicking On Edit Button 
		uiAddNewLeadsPageObjects.lblEditButton.click();
		UserExtension.IsElementPresent(driver, driver.findElement(By.id("lea13")));
		
		//Changing Lead status from New to Contacted 
		Select dropdown = new Select(driver.findElement(By.id("lea13")));
		dropdown.selectByVisibleText("Contacted");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		
		
		//Clicking on Save for the changes Done 
        uiAddNewLeadsPageObjects.TabSave.click();
        Thread.sleep(5000);
        
        //verifying Inquiry history 
        uiAddNewLeadsPageObjects.lstInquiryHistory1.getText();
	    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText(), "Changed Inquiry Status from New to Contacted.");
	    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory1.getText());
	    
	  
						
		}
	catch (Exception e)
		
		{
			Reporter.log(e.getMessage());
		}
	}
	@Test(dependsOnMethods={"ConvertingLeadFomNewToContacted"})
	public void ConvertingLeadFomContactedToRemarketing(Method objMethod) throws InterruptedException
	{
		try{
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			
			//Clicking On Edit Button 
			uiAddNewLeadsPageObjects.lblEditButton.click();
			UserExtension.IsElementPresent(driver, driver.findElement(By.id("lea13")));
			
			//Changing Lead status from Contacted to Remarketing 
			Select dropdown = new Select(driver.findElement(By.id("lea13")));
			dropdown.selectByVisibleText("Remarketing");
			System.out.println(dropdown.getFirstSelectedOption().getText());
			
			//Remarketing Sub Reason
			Select dropdown1 = new Select(driver.findElement(By.id("00Ni000000Ea5pj")));
			dropdown1.selectByVisibleText("Student Requested No Further Contact (Internal)");
			
			
			//Remarketing Sub Reason
			Select dropdown2 = new Select(driver.findElement(By.id("00Ni000000Ea5pk")));
			dropdown2.selectByVisibleText("Do Not Call, Email or Mail - Request for Do Not Call Policy (Internal)");
			
			
			
			//Clicking on Save for the changes Done 
	        uiAddNewLeadsPageObjects.TabSave.click();
	        Thread.sleep(5000);
	        
	        //verifying Inquiry history 
	        uiAddNewLeadsPageObjects.lstInquiryHistory2.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory2.getText(), "Changed EWC from Yes to No.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory2.getText());
		    
		    
		    uiAddNewLeadsPageObjects.lstInquiryHistory3.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory3.getText(), "Changed Do Not Call from false to true.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory3.getText());
		    
		    
		    uiAddNewLeadsPageObjects.lstInquiryHistory4.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory4.getText(), "Changed Remarketing Flag to File Close.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory4.getText());
		    

		    uiAddNewLeadsPageObjects.lstInquiryHistory5.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory5.getText(), "Changed Remarketing Sub Reason to Do Not Call, Email or Mail - Request for Do Not Call Policy (Internal).");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory5.getText());
		}
		catch (Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
	

}
