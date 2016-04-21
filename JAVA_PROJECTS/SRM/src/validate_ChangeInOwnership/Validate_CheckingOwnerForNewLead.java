package validate_ChangeInOwnership;

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
import org.openqa.selenium.Alert;
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

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.DuplicateLeadCompletionPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import uiMap_Orion3_SRM.ValidateChangeInOwnerShipPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class Validate_CheckingOwnerForNewLead {
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
	public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
	public ValidateChangeInOwnerShipPageObjects uiValidateChangeInOwnerShipPageObjects;
    public ReusableMethods_PageObjects uiSRM_ReusableMethods;
    
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
	public static String sFirstName = "TestNGFNCIOWN_" + sRandStr;
	public static String sLastName = "TestNGLNCIOWN_" + sRandStr;			
	public static String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545151234";
	public String sZipCode = "30256";
	public String sSyStuID= "";
	public String sEmailID="";
	public String sOpportunityName="";
	public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";
	public String sEmailTemplate="Career Journey";
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

	//Quit the test after test class execution

			// Writing to Result Property File

			System.out.println("Inside After class");
			
			System.out.println("Before method writing to Result Property file");
			
			SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromValidate_CheckingOwnerForNewLead", sEmailAddress);
			
			System.out.println("After method writing to Result Property file");


					
			
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
	
	
	
	
	
	
	

	//Create Inquiry Lead
	@Test
	public void BrowseToAddNewReferralLeadPage(Method objMethod) throws InterruptedException
	{
		try

		{
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
		
		sEmailAddress1 =uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.getAttribute("value");
		
		uiAddInquiry_Referral_Lead_Pageobjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
		uiAddInquiry_Referral_Lead_Pageobjects.txtZipCode.sendKeys(sZipCode);
		
		
		//TCPA Disclosure
		if(sTCPA.equalsIgnoreCase("yes"))
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_Yes.click();					
		}
		else
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_No.click();
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
		Thread.sleep(10000);
		uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
		Thread.sleep(1000);
		}
		catch (Exception e)
								
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

		
	}			

	//Verify LEad in SRM and check Owner in Email Template	
	@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
	public void VerifyLeadInSRMandCheckOwnerInTemplate(Method objMethod) throws InterruptedException
	
	{try{
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
		uiValidateChangeInOwnerShipPageObjects =new ValidateChangeInOwnerShipPageObjects(driver);
		driver.get(EnvironmentVariables.sSRM_Url);
		
		//Clearing the search field 
		uiAddNewLeadsPageObjects.search_SRM.clear();
		
		//Sending keys on serach field 
		uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
			
		//Clicking on search button
		uiAddNewLeadsPageObjects.btnsearch_SRM.click();
		
		SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
		//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
		Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
						
		//click on inquiry Lead
		driver.findElementByXPath(".//*[@id='Lead_body']/table/tbody/tr[2]/th/a").click();
		Thread.sleep(10000);
		
		//Verifying email Id 
		sEmailID = driver.findElementByXPath("html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[4]/div[2]/div[10]/table/tbody/tr[11]/td[2]/div/a").getText();
		System.out.println(sEmailID);
		
		//Fetching owner name in Inquiry Lead details 
		System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnernameLeadPage.getText());
		Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnernameLeadPage.getText(), "Piyush Mishra");
		System.out.println("Owner name is fetched successfully");
		
		//WebElement element3= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
		Thread.sleep(15000);
		
		//Switching to the frame 
        Actions action = new Actions(driver);
        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
        Thread.sleep(1000);
        System.out.println("Switched to activity history frame ");
        
        //Clicking on process email tab under activity history 
        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
        Thread.sleep(15000);
        

       // Selecting Template from the dropdown 
    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
		ddlEmailTemplate.selectByVisibleText("Career Journey");
		Thread.sleep(5000);
		
		//Clicking on preview Template 
		uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
		Thread.sleep(10000);
		driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
		UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate);
		
		
		//Verifying Lead owner name 
		System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText());
		Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText(), "Piyush Mishra");
		System.out.println("Owner found Sucessfully AND Verified Successfully");
		Thread.sleep(10000);
		
		//Clicking on Home Button
		uiAddNewLeadsPageObjects.BtnHomeButton.click();
        
	}catch (Exception e)
	
	{
		Reporter.log(e.getMessage());
		
	}
	
	}
	//Change Owner of Inquiry and Verify Owner in Email Template 
	@Test(dependsOnMethods={"VerifyLeadInSRMandCheckOwnerInTemplate"})
	public void ChangeOwnerAndVerifyTheOwnerInTemplate(Method objMethod) throws InterruptedException

	{
		
		try{
			//Initializing Drivers 
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			uiValidateChangeInOwnerShipPageObjects =new ValidateChangeInOwnerShipPageObjects(driver);
			System.out.println("Entered Change owner method sucessfully");
			driver.get(EnvironmentVariables.sSRM_Url);
			
			//Clearing the search field 
			uiAddNewLeadsPageObjects.search_SRM.clear();
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
			
			//Sending keys on serach field 
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
				
			//Clicking on search button
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
			//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
			
			//click on inquiry Lead
			driver.findElementByXPath(".//*[@id='Lead_body']/table/tbody/tr[2]/th/a").click();
			Thread.sleep(10000);
			
			//Clicking on Change link to change the owner 
			uiValidateChangeInOwnerShipPageObjects.lnkChange.click();
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.DrpDwnOwnerType);
			
			//Selecting Option in Dropdown List of owner search Type 
			Select ddlOwnerType = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnOwnerType);
			ddlOwnerType.selectByVisibleText("User");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.TxtNewOwnerName);
			
			//Sending Text to owner Search Field 
			uiValidateChangeInOwnerShipPageObjects.TxtNewOwnerName.sendKeys("Manish Narang");
			
			//Clicking on save Button for the changes 
			uiValidateChangeInOwnerShipPageObjects.TabsaveOwner.click();
			Thread.sleep(10000);
			
			//Fetching Updated owner name in Inquiry Lead details 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnernameLeadPage.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnernameLeadPage.getText(), "Manish Narang");
			System.out.println("Owner name is fetched successfully");
			
			//WebElement element3= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action = new Actions(driver);
	        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to activity history frame ");
	        
	        //Clicking on process email tab under activity history 
	        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
	        Thread.sleep(15000);
	        

	       // Selecting Template from the dropdown 
	    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
			ddlEmailTemplate.selectByVisibleText("Career Journey");
			
			Thread.sleep(5000);
			
			//Clicking on preview Template 
			uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
			Thread.sleep(10000);
			driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
			System.out.println("SWITCHED TO CONTENT FRAME ");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate);
			
			
			//Verifying Lead owner name 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate.getText(), "Manish Narang");
			System.out.println("Owner found Sucessfully AND Verified Successfully");
			Thread.sleep(10000);
			
			
			
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());
		}
}
	
	//Convert the given Lead to Opportunity 
	@Test(dependsOnMethods={"ChangeOwnerAndVerifyTheOwnerInTemplate"})
	public void ConvertingChangedOwnerLeadToOppurtunity(Method objMethod) throws InterruptedException
	{
		try{
			//Initializing Drivers 
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			uiValidateChangeInOwnerShipPageObjects =new ValidateChangeInOwnerShipPageObjects(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			//Navigate to Homepage by opening the URL
			driver.get(EnvironmentVariables.sSRM_Url);
			
			//search the lead text box 
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.btnsearch_SRM);
			
			//click on search button 
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
			Thread.sleep(5000);
	        //WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
			
			//click on inquiry Lead
			driver.findElementByXPath(".//*[@id='Lead_body']/table/tbody/tr[2]/th/a").click();
			Thread.sleep(10000);
			
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.BtnConvert);
			
			//Clicking on Convert Button 
			uiAddNewLeadsPageObjects.BtnConvert.click();
			Thread.sleep(10000);
			
			//Clicking on Home Button
			uiAddNewLeadsPageObjects.BtnHomeButton.click();
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
			
			//search the lead text box 
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			
			//click on search button 
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
			//call Wait for Search Opportunity Method
			SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
									
			//Opportunities and Accounts Elements
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
			WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));

			//Assert Opportunities and Accounts
			Assert.assertTrue(ele.isDisplayed());
			Assert.assertTrue(ele1.isDisplayed());
			
			//click to open the Opportunity
			uiSRM_LeadFlow_PageObjects.LnkOppLead.click();
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerOpportunity);
			
			
			//Fetching Updated owner name in Inquiry Lead details 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerOpportunity.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerOpportunity.getText(), "Manish Narang");
			System.out.println("Owner name is fetched successfully");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.TxtOpportunityname);
		    
		    //fetching Owner Name From Opportunity
			sOpportunityName=uiValidateChangeInOwnerShipPageObjects.TxtOpportunityname.getText();
			System.out.println(sOpportunityName);
			Thread.sleep(15000);
			
			//WebElement element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
			//Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action = new Actions(driver);
	        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to activity history frame ");
	        
	        //Clicking on process email tab under activity history 
	        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
	        Thread.sleep(15000);
	        

	       // Selecting Template from the dropdown 
	    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
			ddlEmailTemplate.selectByVisibleText("Career Journey");
			
			Thread.sleep(5000);
			
			//Clicking on preview Template 
			uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
			Thread.sleep(10000);
			driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
			System.out.println("SWITCHED TO CONTENT FRAME ");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate);
			
			
			//Verifying Lead owner name 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtChangedOwnerNameintemplate.getText(), "Manish Narang");
			System.out.println("Owner found Sucessfully AND Verified Successfully");
			Thread.sleep(10000);
			
					
			
			
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
	
	//Change Owner of Opportunity and verify owner in Email Template  
	@Test(dependsOnMethods={"ConvertingChangedOwnerLeadToOppurtunity"})
	public void ChangingownernameOfConvertedOppurtunity(Method objMethod) throws InterruptedException
	{
		try
		{
			//Initializing Drivers 
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			uiValidateChangeInOwnerShipPageObjects =new ValidateChangeInOwnerShipPageObjects(driver);
			uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
			
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			System.out.println("Changing Owner Of converted Oppurtunity ");
			
			//Navigate to Homepage by opening the URL
			driver.get(EnvironmentVariables.sSRM_Url);
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
			
			//Search for the Lead/opportunity
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			//uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
			//call Wait for Search Opportunity Method
			SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
									
			//Opportunities and Accounts Elements
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
			WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));
		
			//Assert Opportunities and Accounts
			Assert.assertTrue(ele.isDisplayed());
			Assert.assertTrue(ele1.isDisplayed());
			Thread.sleep(10000);
			//click to open the Opportunity
			driver.findElement(By.xpath(".//*[@id='Opportunity_body']/table/tbody/tr[2]/th/a")).click();
			//uiSRM_LeadFlow_PageObjects.LnkOppLead.click();
			Thread.sleep(10000);			
			System.out.println("Entered opp page owner name of oppurtunity ");
			if (driver.findElement(By.xpath("//h2[text()='Opportunity Detail']")).isDisplayed()){
				System.out.println("Entered the opp details page");
			}
			
			//Clicking on Change link to change the owner 
			uiValidateChangeInOwnerShipPageObjects.lnkChangeOppurtunity.click();
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.DrpDwnOwnerType);
			
			//Selecting Option in Dropdown List of owner search Type 
			Select ddlOwnerType = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnOwnerType);
			ddlOwnerType.selectByVisibleText("User");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.TxtNewOwnerName);
			
			//Sending Text to owner Search Field 
			uiValidateChangeInOwnerShipPageObjects.TxtNewOwnerName.sendKeys("Jitendra Kumar");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.TabsaveOwner);
			
			//Clicking on save Button for the changes 
			uiValidateChangeInOwnerShipPageObjects.TabsaveOwner.click();
			Thread.sleep(10000);
			
			//Fetching Updated owner name in Inquiry Lead details 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnernameOpportunityPage.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnernameOpportunityPage.getText(), "Jitendra Kumar");
			System.out.println("Owner name is fetched successfully");
			
			//WebElement element3= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action = new Actions(driver);
	        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to activity history frame ");
	        
	        //Clicking on process email tab under activity history 
	        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
	        Thread.sleep(15000);
	        

	       // Selecting Template from the dropdown 
	    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
			ddlEmailTemplate.selectByVisibleText("Career Journey");
			
			Thread.sleep(5000);
			
			//Clicking on preview Template 
			uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
			Thread.sleep(10000);
			driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
			System.out.println("SWITCHED TO CONTENT FRAME ");
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtCovertedOppurtunityOwnerNameintemplate);
			
			
			//Verifying Lead owner name 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtCovertedOppurtunityOwnerNameintemplate.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtCovertedOppurtunityOwnerNameintemplate.getText(), "Jitendra Kumar");
			System.out.println("Owner found Sucessfully AND Verified Successfully");
			Thread.sleep(10000);
			
			
			
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
	
	@Test(dependsOnMethods={"ChangingownernameOfConvertedOppurtunity"})
	public void ClosingConvertedOppurtunityandCreatingNewOpportunityWithORS(Method objMethod) throws InterruptedException
	{
		try
		{
			//Initializing Drivers 
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			uiValidateChangeInOwnerShipPageObjects =new ValidateChangeInOwnerShipPageObjects(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			
			
			System.out.println("Entering Change Owner Name Of Converted Oppurtunity");
			//Navigate to Homepage by opening the URL
			driver.get(EnvironmentVariables.sSRM_Url);
			
			//search the lead text box 
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.btnsearch_SRM);
			
			//click on search button 
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
			//call Wait for Search Opportunity Method
			SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
									
			//Opportunities and Accounts Elements
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
			WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));
		
			//Assert Opportunities and Accounts
			Assert.assertTrue(ele.isDisplayed());
			Assert.assertTrue(ele1.isDisplayed());
			
			//click to open the Opportunity
			driver.findElement(By.xpath(".//*[@id='Opportunity_body']/table/tbody/tr[2]/th/a")).click();
			Thread.sleep(10000);
						

			//Clicking On Edit Button 
			uiAddNewLeadsPageObjects.lblEditButton.click();
			UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//label[text()='Stage']/parent::td/following-sibling::td/div/span/select")));
			
			
			//Changing Lead status from opportunity to remarketing
			Select dropdown = new Select(driver.findElement(By.xpath("//label[text()='Stage']/parent::td/following-sibling::td/div/span/select")));
			dropdown.selectByVisibleText("Remarketing");
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.TabSave);
			
			//Clicking on Save for the changes Done 
	        uiAddNewLeadsPageObjects.TabSave.click();
	        Thread.sleep(10000);
	        
	        //Clicking on Account Name To create new Oppurtunity
	        uiValidateChangeInOwnerShipPageObjects.lnkAccountNameopp.click();
	        Thread.sleep(15000);
	        
	        //WebElement element4= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Opportunities']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action = new Actions(driver);
	        action.moveToElement(uiValidateChangeInOwnerShipPageObjects.OppurtunitiesHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to Opportunities history frame to create new opportunity ");
	        
	        //Clicking on New Opportunity button 
	        uiValidateChangeInOwnerShipPageObjects.TabNewOppurtunity.click();
	        Thread.sleep(10000);
	        
	        //Selecting New record Type ORS For New Opportunity 
	    	Select ddlNewRecordType = new Select(uiValidateChangeInOwnerShipPageObjects.DrpdwnNewrecordType);
	    	ddlNewRecordType.selectByVisibleText("KUO Student ORS Enrollment");
	    	
	    	Thread.sleep(10000);    	
	    	
	    	//Clicking on Continue Button 
	    	uiValidateChangeInOwnerShipPageObjects.TabContinue.click();
	    	Thread.sleep(10000);
	    	
	    	//Sending Keys to Opportunity Name field for new opportunity  
	    	uiValidateChangeInOwnerShipPageObjects.TxtOpportunitynameEdit.sendKeys(sOpportunityName);
	    	
	    	//Clicking on Close date for Opportunity
	    	uiValidateChangeInOwnerShipPageObjects.lnkClosedate.click();
	    	Thread.sleep(10000);
	    	
	    	//Selecting Stage Name from The dropDown 
	    	Select ddlStage = new Select(uiValidateChangeInOwnerShipPageObjects.DrpdwnStage);
	    	ddlStage.selectByVisibleText("Reengaged");
	    	UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.BtnSaveOpp);
	    	
	    	//Clicking on Save button to save the opportunity
	    	uiValidateChangeInOwnerShipPageObjects.BtnSaveOpp.click();
	    	Thread.sleep(10000);
	    	
	    	
	    	
	    	//WebElement element3= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action2 = new Actions(driver);
	        action2.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to activity history frame to verify Ors Enrollment");
	        
	        
	        //Clicking on process email tab under activity history 
	        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
	        Thread.sleep(15000);
	        

	       // Selecting Template from the dropdown 
	    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
			ddlEmailTemplate.selectByVisibleText("Career Journey");
			Thread.sleep(5000);
			
			//Clicking on preview Template 
			uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
			Thread.sleep(10000);
			driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate);
			
			
			//Verifying Lead owner name 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText(), "Piyush Mishra");
			System.out.println("Owner found Sucessfully AND Verified Successfully for ORS");
			Thread.sleep(10000);
			
			
	     
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
	@Test(dependsOnMethods={"ClosingConvertedOppurtunityandCreatingNewOpportunityWithORS"})
	public void ClosingNewOpportunityCreatedandCreatingNewOpportunitywithReengage(Method objMethod) throws InterruptedException
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			
			//Navigate to Homepage by opening the URL
			driver.get(EnvironmentVariables.sSRM_Url);
			
			//search the lead text box 
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.btnsearch_SRM);
			
			//click on search button 
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
			//call Wait for Search Opportunity Method
			SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
									
			//Opportunities and Accounts Elements
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
			WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));
		
			//Assert Opportunities and Accounts
			Assert.assertTrue(ele.isDisplayed());
			Assert.assertTrue(ele1.isDisplayed());
			
			//click to open the Opportunity
			driver.findElement(By.xpath(".//*[@id='Opportunity_body']/table/tbody/tr[2]/th/a")).click();
			Thread.sleep(10000);
			

			//Clicking On Edit Button 
			uiAddNewLeadsPageObjects.lblEditButton.click();
			UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//label[text()='Stage']/parent::td/following-sibling::td/div/span/select")));
			
			//Changing Lead status from New to Contacted 
			Select dropdown = new Select(driver.findElement(By.xpath("//label[text()='Stage']/parent::td/following-sibling::td/div/span/select")));
			dropdown.selectByVisibleText("Remarketing");
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.TabSave);
			
			//Clicking on Save for the changes Done 
	        uiAddNewLeadsPageObjects.TabSave.click();
	        Thread.sleep(8000);
			
			
			 //Clicking on Account Name To create new Oppurtunity
	        uiValidateChangeInOwnerShipPageObjects.lnkAccountNameopp.click();
	        Thread.sleep(10000);
	        
	        //WebElement element4= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Opportunities']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action = new Actions(driver);
	        action.moveToElement(uiValidateChangeInOwnerShipPageObjects.OppurtunitiesHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to Opportunities history frame to choose ku reengaged Record ");
	        
	        //Clicking on New Opportunity button 
	        uiValidateChangeInOwnerShipPageObjects.TabNewOppurtunity.click();
	        Thread.sleep(10000);
	        
	        //Selecting New record Type ORS For New Opportunity 
	    	Select ddlNewRecordType = new Select(uiValidateChangeInOwnerShipPageObjects.DrpdwnNewrecordType);
	    	ddlNewRecordType.selectByVisibleText("KUO Student Reengaged");
	    	Thread.sleep(10000);    	
	    	
	    	//Clicking on Continue Button 
	    	uiValidateChangeInOwnerShipPageObjects.TabContinue.click();
	    	Thread.sleep(10000);
	    	
	    	//Sending Keys to Opportunity Name field for new opportunity  
	    	uiValidateChangeInOwnerShipPageObjects.TxtOpportunitynameEdit.sendKeys(sOpportunityName);
	    	
	    	//Clicking on Close date for Opportunity
	    	uiValidateChangeInOwnerShipPageObjects.lnkClosedate.click();
	    	Thread.sleep(10000);
	    	
	    	//Selecting Stage Name from The dropDown 
	    	Select ddlStage = new Select(uiValidateChangeInOwnerShipPageObjects.DrpdwnStage);
	    	ddlStage.selectByVisibleText("Reengaged");
	    	
	    	
	    	//Clicking on Save button to save the opportunity
	    	uiValidateChangeInOwnerShipPageObjects.BtnSaveOpp.click();
	    	Thread.sleep(10000);
	    	
	    	    	
	    	//WebElement element5= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
			Thread.sleep(15000);
			
			//Switching to the frame 
	        Actions action2 = new Actions(driver);
	        action2.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
	        Thread.sleep(1000);
	        System.out.println("Switched to activity history frame for ku Reengaged ");
	        Thread.sleep(15000);
	        
	        //Clicking on process email tab under activity history 
	        uiValidateChangeInOwnerShipPageObjects.tbProcessEmail.click();
	        Thread.sleep(15000);
	        

	       // Selecting Template from the dropdown 
	    	Select ddlEmailTemplate = new Select(uiValidateChangeInOwnerShipPageObjects.DrpDwnEmailTemplate);
			ddlEmailTemplate.selectByVisibleText("Career Journey");
		
		
			Thread.sleep(5000);
			
			//Clicking on preview Template 
			uiValidateChangeInOwnerShipPageObjects.tbPreviewTemplate.click();
			Thread.sleep(10000);
			driver.switchTo().frame(uiValidateChangeInOwnerShipPageObjects.contentframeswitch);
			UserExtension.IsElementPresent(driver, uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate);
			
			
			//Verifying Lead owner name 
			System.out.println(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText());
			Assert.assertEquals(uiValidateChangeInOwnerShipPageObjects.txtOwnerNameintemplate.getText(), "Piyush Mishra");
			System.out.println("Owner found Sucessfully AND Verified Successfully for Ku Reengaged ");
			Thread.sleep(10000);
			
			
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());	
		}
	}


}
