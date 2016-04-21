package validateTCPA_EWC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.LeadImport_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class TCPA_NoFunctionality {
	
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
	public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
	public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
	public LeadImport_PageObjects uiLeadImport_PageObjects;
	public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
	
	
	//Variables from Properties file
	 String sFirstName1;
     String sLastName1;
     String sEmailAddress1;
	 String sCurrentSchoolID;
     public String LeadImportID1;
     public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";
	 public String sSyStuID= "";
	 public String sEmailID="";
	 public String sSearchMain_WindowName ="";
	 public String mainwinhandle;
	//Static variable
	 public String sPath_ResultProperties="";
	public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public static String sFirstName = "TestNGFNEWCNO_" + sRandStr;
	public static String sLastName = "TestNGLNEWCNO_" + sRandStr;			
	public static String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545151234";
	public String sZipCode = "30256";
	public String sProgram = "BSHS";
	public String sAreaStudy = "HS";
	public String SIF1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><sif><sifheader VendorID=\"61439\" SourceCode=\"248691\" SIFID=\"279\" SIFVersionID=\"2258\" VendorDate=\"04/22/2013\" AffiliateID=\"1\" /><sifdetail><Program>";
	// AASMA
	public String SIFn1="</Program><AreaStudy>";
	//HS
	public String SIfn2= "</AreaStudy><FName>";
	public String SIF2="</FName><LName>";
	public String SIF3="</LName><Email>";
	public String SIF4="</Email><HPhone>9545154045</HPhone><WPhone>9545154046</WPhone><MPhone>9545154047</MPhone><Address>Address 1</Address><Address2>Address 2</Address2><City>Fort Lauderdale</City><State>Florida</State><Zip>33302</Zip><Country>United States</Country><USCitizen>YES</USCitizen><HighestEducation>GED</HighestEducation><GradYear>2008-2003</GradYear><Military>No</Military><Military2>Active</Military2><AGGLeadID>44D07075-3539-DD00-87B4-2FE57C3A99CC</AGGLeadID><TCPA_Disc>No</TCPA_Disc><syUserID>2328</syUserID><URL>http://www.degrees.info/forms/form.jsp</URL></sifdetail></sif>";
	public String SifFinal=SIF1+sProgram.toLowerCase()+SIFn1+sAreaStudy.toUpperCase()+SIfn2+sFirstName+SIF2+sLastName+SIF3+sEmailAddress+SIF4;
	
			
			
	
	
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		try{
			
		
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
		driver.get(EnvironmentVariables.sLead_ImportURL);
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);
		uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
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
			
			SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromTCPA_NoFunctionality", sEmailAddress);
			
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
	public void LeadSubmissionByLeadImport(Method objMethod) throws InterruptedException
	{
		try{
		
		uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
		
	
		
		uiLeadImport_PageObjects.txtStudentInformationXML.clear();
		uiLeadImport_PageObjects.txtStudentInformationXML.sendKeys(SifFinal);
		Thread.sleep(10000);
		uiLeadImport_PageObjects.btnInvoke.click();
		
		//Window Handles
		String parentHandle = driver.getWindowHandle();
	
		Set <String> winHandles = driver.getWindowHandles();
		for (String currentWindowHandle : winHandles) {
			if (!currentWindowHandle.equals(parentHandle)) {
				
				driver.switchTo().window(currentWindowHandle);
				System.out.println(sEmailAddress);
				System.out.println(SifFinal);
				
			}
		}
											
		Thread.sleep(20000);
		try{
	LeadImportID1 = uiLeadImport_PageObjects.txtLeadImport.getText();
	System.out.println(LeadImportID1);
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}				
		
		
	}catch (Exception e)
	{Reporter.log(e.getMessage());
		
	}
	}
	
	@Test(dependsOnMethods={"LeadSubmissionByLeadImport"})
	public void VerifyingSubmittedTCPANoLead(Method objMethod) throws InterruptedException
	{
		try{
			uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			
			driver.get(EnvironmentVariables.sSRM_Url);
			UserExtension.IsElementPresent(driver, uiReusableMethods_PageObjects.lnkDropDown);
			Thread.sleep(10000);
			
			uiAddNewLeadsPageObjects.search_SRM.clear();
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress);
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
				
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
			SRM_ReusableMethods.WaitSearchInquiry(driver, 10000);
			//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
			
			Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress), "Email searched successfully");
			Thread.sleep(5000);		
			

			//Click on The Inquiry Lead 
			uiAddNewLeadsPageObjects.lnkSearchResult.click();
			
			//Verifying Ewc Value is Yes 
			uiAddNewLeadsPageObjects.txtEwcField.getText();
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "No");
			System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
			
			//Verifying Do Not Email Value is No 
			uiAddNewLeadsPageObjects.txtDoNotEmail.getText();
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtDoNotEmail.getText(), "No");
	        System.out.println(uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
		    Thread.sleep(10000);
		    
		    
		}catch (Exception e)
		{Reporter.log(e.getMessage());
			
		}
		
		
		
	}
	
	@Test(dependsOnMethods={"VerifyingSubmittedTCPANoLead"})
	public void VerifyinquiryinList(Method objMethod) throws InterruptedException{
		try{
		//Navigate to My Inquiries
		
		//Clicking on Add Symbol for menu extension
		uiAddNewLeadsPageObjects.ExtMenu.click();
		
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
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "No");
		System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
		
		//Verifying Do Not Email Value is No 
		uiAddNewLeadsPageObjects.txtDoNotEmail.getText();
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtDoNotEmail.getText(), "No");
        System.out.println(uiAddNewLeadsPageObjects.txtDoNotEmail.getText());
	    Thread.sleep(10000);
	    
		
		}
		
		
		
		catch (Exception e){
			Reporter.log(e.getMessage());
			System.out.println(e.getMessage());
			
		}
		
	}
 

}
