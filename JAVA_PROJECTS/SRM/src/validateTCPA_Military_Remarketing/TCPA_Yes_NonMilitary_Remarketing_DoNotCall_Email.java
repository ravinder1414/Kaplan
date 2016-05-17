package validateTCPA_Military_Remarketing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;

import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class TCPA_Yes_NonMilitary_Remarketing_DoNotCall_Email {

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
	String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public String sFirstName = "TestNGFNTYNMRDNCE_" + sRandStr;
	public String sLastName = "TestNGLNTYNMRDNCE_" + sRandStr;			
	public String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545154479";
	public String sZipCode = "33309";
	public String sSyStuID= "";
	public String sEmailID="";	
	public String sMkLeadImportID="";
	public String sPath_AppProperties="";
	public FileOutputStream objFileOutputStream=null;
	public FileInputStream objFileInputStream = null;
	public Properties objProperties = new Properties();
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		try{
			
		//Read the application properties file
		//Load environment variable from properties file
		
		/*String sPath_AppProperties="";
		FileInputStream objFileInputStream = null;
		Properties objProperties = new Properties();*/
		
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
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
	@Test
	public void BrowseToAddNewReferralLeadPage(Method objMethod) throws InterruptedException
	{
		try{
			System.out.println("Start method BrowseToAddNewReferralLeadPage");
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
		driver.findElementByXPath(".//*[@id='j_id0:addaleadid:leadblock:j_id44:10:j_id50:1']").click();
		
		
		
		//Highest Level of Education
		Select ddlHightestEdution = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddHighestLevelEducation);
		ddlHightestEdution.selectByVisibleText(sHighestEducation);
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.ddMilitaryType);
		
		//Military Type
		Select ddMilitaryType = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddMilitaryType);
		ddMilitaryType.selectByVisibleText("No");
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry);
	
		//clicking on Add Inquiry button      
		uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
		
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);				
		Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
		
		driver.navigate().refresh();
		
		uiReusableMethods_PageObjects.lnkDropDown.click();
		Thread.sleep(3000);
		uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
		Thread.sleep(1000);
		System.out.println("End method BrowseToAddNewReferralLeadPage");
		}
	catch (Exception e)
		
		{
			Reporter.log(e.getMessage());
		}
	}

	@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
	public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
	
	{try
		{
		System.out.println("Start method VerifyLeadInSRM");
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		driver.get(EnvironmentVariables.sSRM_Url);
		uiAddNewLeadsPageObjects.search_SRM.clear();
		uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
			
		
		uiAddNewLeadsPageObjects.btnsearch_SRM.click();
		
		SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
		
		//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
		
		Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
						
		//click on inquiry Lead
		driver.findElementByXPath(".//*[@id='Lead_body']/table/tbody/tr[2]/th/a").click();
		Thread.sleep(10000);
		
		
		//Fetch Systdent id
		sSyStuID =driver.findElementByXPath("//td[text()='SyStudentID']/following-sibling::td[1]/div").getText();
		Reporter.log(sSyStuID);
		System.out.println("Sys Id After fetching from locator"+sSyStuID);
		
		//Fetching email Id from inquiry details page 
		sEmailID = driver.findElementByXPath("//span[text()='Primary Email']/parent::td/following-sibling::td[1]/div").getText();
	    System.out.println(sEmailID);
	    
	    //fetching mkleadimportId from inquiry details page 
	    sMkLeadImportID =driver.findElementByXPath("(//td[text()='mkLeadImportID']/following-sibling::td/div)[1]").getText();
	    System.out.println(sMkLeadImportID);
	    
		//Verifying Ewc Value is Yes 
		uiAddNewLeadsPageObjects.txtEwcField.getText();
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "Yes");
		System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.cbIsMilitarySIF);
		
		//Is Military Sif  
		System.out.println(uiAddNewLeadsPageObjects.cbIsMilitarySIF.getAttribute("alt"));
		Assert.assertEquals(uiAddNewLeadsPageObjects.cbIsMilitarySIF.getAttribute("alt"), "Not Checked");
		System.out.println("This iS Not a Sif ");
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.cbIsMilitary);
		
		//Is Military 
		System.out.println(uiAddNewLeadsPageObjects.cbIsMilitary.getAttribute("alt"));
		Assert.assertEquals(uiAddNewLeadsPageObjects.cbIsMilitary.getAttribute("alt"), "Not Checked");
		System.out.println("This is Non Miltary Lead ");
		
		
		
	
	
	// Writing syStudent Id to Property file
	
	System.out.println("Before method writing SYStudentID to Property file");
	
	SRM_ReusableMethods.writeToPropertyFile(sPath_AppProperties, "SYStudentIDFromTCPANonMilitaryDNCEmail", sSyStuID);
	
	System.out.println("After method writing SYStudentID to Property file");
	
	
	// Writing Email Id to Property file
	System.out.println("Before method writing EmailID to Property file");
	
	SRM_ReusableMethods.writeToPropertyFile(sPath_AppProperties, "EmailIDFromTCPANonMilitaryDNCEmail", sEmailID);
	
	System.out.println("After method writing EmailID to Property file");
	
	// Writing MkLeadImportId to Property file
	System.out.println("Before method writing EmailID to Property file");
		
	SRM_ReusableMethods.writeToPropertyFile(sPath_AppProperties, "MkLeadImportIDFromTCPANonMilitaryDNCEmail", sMkLeadImportID);
		
	System.out.println("After method writing EmailID to Property file");
		
	System.out.println("End method VerifyLeadInSRM");	
		
	}catch (Exception e)
	{Reporter.log(e.getMessage());
		
	}
	}
	@Test(dependsOnMethods={"VerifyLeadInSRM"})
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
			Thread.sleep(7000);
			
			//Remarketing Sub Reason
			Select dropdown1 = new Select(driver.findElement(By.id("00Ni000000Ea5pj")));
			dropdown1.selectByVisibleText("Student Requested No Further Contact (Internal)");
			Thread.sleep(5000);
			
			//Remarketing Sub Reason
			Select dropdown2 = new Select(driver.findElement(By.id("00Ni000000Ea5pk")));
			dropdown2.selectByVisibleText("Do Not Call, Email or Mail - Request for Do Not Call Policy (Internal)");
			
			
			
			//Clicking on Save for the changes Done 
	        uiAddNewLeadsPageObjects.TabSave.click();
	        Thread.sleep(5000);
	        
	        //veriying inquiry history 
		    
		    
		    uiAddNewLeadsPageObjects.lstInquiryHistory4.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory4.getText(), "Changed Do Not Call from false to true.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory4.getText());
		    

		    uiAddNewLeadsPageObjects.lstInquiryHistory5.getText();
		    Assert.assertEquals(uiAddNewLeadsPageObjects.lstInquiryHistory5.getText(), "Changed Remarketing Flag to File Close.");
		    System.out.println(uiAddNewLeadsPageObjects.lstInquiryHistory5.getText());
		    
		}
		catch (Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
	@AfterClass
	public void AfterNavigation()
{
//Quit the test after test class execution

if(driver != null)
{
driver.quit();			
}
}



}
