package orion3_LeadCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
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

import orion3_Variables.EnvironmentVariables;
import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.StudentManagerPageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;

import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class StudentManager {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
	public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
	//public StudentManagerPageObjects uiStudentManagerPageObjects;
	StudentManagerPageObjects uiStudentManagerPageObjects;
	
	//Variables from Properties file
	String sFirstName1;
    String sLastName1;
	public String sLeadType;
	public String sChannelGroup;
	public String sAreaOfStudy;
	public String sProgramofInterest;
	public String sMilitaryType;
	public String sSpouseMilitary;
	public String sTCPA;
	public String sHighestEducation;
	
	//Static variable
	String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
	public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
	public String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545151234";
	public String sZipCode = "30256";
	
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
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
		sLeadType = objProperties.getProperty("sLeadType");
		sChannelGroup = objProperties.getProperty("sChannelGroup");
		sTCPA = objProperties.getProperty("sTCPADisclosure");
		sMilitaryType = objProperties.getProperty("sMilitaryType");
		sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
		sHighestEducation = objProperties.getProperty("sHighestEduction");
		sAreaOfStudy = objProperties.getProperty("sAreaOfStudy");
		sProgramofInterest = objProperties.getProperty("sProgramofInterest");
					
		//Edit Browser Capabilities as per project
		//Fire fox Profile		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_Orion3);
		
		//Capability
		objBrowserMgr = new BrowserManagement(sBrowser);
		objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
			
		//Create the Remote Driver Instance
		try
		{					
			driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
		}
		catch(Exception ex)
		{	
			ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
		}
		driver.get(EnvironmentVariables.sUrl_Orion3);
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);			
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

	
	@Test
	public void BrowseToAddNewLeadPage(Method objMethod)
	{
		uiAdmissionMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
		uiAddNewLeadsPageObjects = uiAdmissionMgrPageObjects.ClickAddNewLeadLink(driver);
		driver.switchTo().window(uiAddNewLeadsPageObjects.sAddNewLead_WindowName);
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall);
	}
	
	
	@Test(dependsOnMethods={"BrowseToAddNewLeadPage"})
	public void Leads_ClassificationDetails(Method objMethod)
	
	{
		Assert.assertEquals(uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"), "true", "Info Call Lead Type is not getting selected");
		UserExtension.IsElementNotPresent(driver, uiAddNewLeadsPageObjects.imgProgressPanel);
		
		
		//UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.ddlPromotionCode);
		Select ddlPromotionCode = new Select(uiAddNewLeadsPageObjects.ddlPromotionCode);
		//Select ChannelGroup
		//uiAddNewLeadsPageObjects.ddlPromotionCode = UserExtension.GetStaleElement(driver, uiAddNewLeadsPageObjects.ddlPromotionCodeLocator);
		Assert.assertEquals(ddlPromotionCode.getFirstSelectedOption().getText().trim(), uiAddNewLeadsPageObjects.sPromotionCodeDefaultText_before, "Default Selected options for Promotion Code is not Correct");
		uiAddNewLeadsPageObjects.SelectChannelGroupAs(sChannelGroup);
		UserExtension.WaitTillAttributeValueIs(driver, uiAddNewLeadsPageObjects.imgProgressPanel, "style", "display: block;");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_DdlPromotionCode")));
		Assert.assertEquals(ddlPromotionCode.getFirstSelectedOption().getText().trim(), uiAddNewLeadsPageObjects.sPromotionCodeDefaultText_after, "Selected option for Promotion Code is not Correct");		
		//Select Promotion
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		ddlPromotionCode.selectByIndex(1);			
	}			
	

	@Test(dependsOnMethods={"Leads_ClassificationDetails"})
	public void Leads_ProgramOfInterest(Method objMethod)
	{					
		Select ddlAreaOfStudy = new Select(uiAddNewLeadsPageObjects.ddlAreaOfStudyTemp);				
		//Area of Study
		Assert.assertEquals(ddlAreaOfStudy.getFirstSelectedOption().getText(), uiAddNewLeadsPageObjects.sAreaofStudyDefaultText, "Default Selected options for Area of Studty is incorrect");
		ddlAreaOfStudy.selectByVisibleText(sAreaOfStudy);
		//Program of Interest
		Select ddlProgramOfInterest = new Select(uiAddNewLeadsPageObjects.ddlProgramOfInterest);
		Assert.assertEquals(ddlProgramOfInterest.getFirstSelectedOption().getText(), uiAddNewLeadsPageObjects.sProgramOfInterest, "Default Selected options for Program of Interest is incorrect");
		ddlProgramOfInterest.selectByVisibleText(sProgramofInterest);				
	}
	
	@Test(dependsOnMethods={"Leads_ProgramOfInterest"})
	public void Leads_Details(Method objMethod)
	{
		uiAddNewLeadsPageObjects.txtFirstName.sendKeys(sFirstName);
		sFirstName1 =uiAddNewLeadsPageObjects.txtLastName.getAttribute("value");
		uiAddNewLeadsPageObjects.txtLastName.sendKeys(sLastName);
		sLastName1 =uiAddNewLeadsPageObjects.txtLastName.getAttribute("value");
		uiAddNewLeadsPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
		uiAddNewLeadsPageObjects.txtDayPhone.sendKeys(sDayPhone);
		uiAddNewLeadsPageObjects.txtZipCode.sendKeys(sZipCode);
		//Spouse Military Status
		if(sSpouseMilitary.equalsIgnoreCase("yes"))
		{
			uiAddNewLeadsPageObjects.rbtnMilitarySpouse_Yes.click();					
		}
		else
		{
			uiAddNewLeadsPageObjects.rbtnMilitarySpouse_No.click();
		}
		//Military Status
		Select ddlMilitary = new Select(uiAddNewLeadsPageObjects.ddlMilitary);
		if(sMilitaryType.equalsIgnoreCase("yes"))
		{
			ddlMilitary.selectByVisibleText("Yes-Active Duty");					
		}
		else
		{
			ddlMilitary.selectByVisibleText("No");
		}
		//TCPA Disclosure
		if(sTCPA.equalsIgnoreCase("yes"))
		{
			uiAddNewLeadsPageObjects.rtbnTCPA_Yes.click();					
		}
		else
		{
			uiAddNewLeadsPageObjects.rtbnTCPA_No.click();
		}
		//Highest Level of Education
		Select ddlHightestEdution = new Select(uiAddNewLeadsPageObjects.ddlHightestEduction);
		ddlHightestEdution.selectByVisibleText(sHighestEducation);								
	}
	
	@Test(dependsOnMethods={"Leads_Details"})
	public void SubmitLead(Method objMethod)
	{
		uiAddNewLeadsPageObjects.btnAddALead.click();
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lblSuccessMsg);
		Assert.assertEquals(uiAddNewLeadsPageObjects.lblSuccessMsg.getText().trim(), uiAddNewLeadsPageObjects.sLeadSuccessMsg, "Lead successfully submitted message did not appear as expected");
		Assert.assertTrue(uiAddNewLeadsPageObjects.btnClosePage.isDisplayed(), "Close Page button did not appeared as expected");
		ReportExtn.Pass(objMethod, "Close Page button did appear as expected");
		Assert.assertTrue(uiAddNewLeadsPageObjects.btnResetPage.isDisplayed(), "Reset Page button did not appeared as expected");
		ReportExtn.Pass(objMethod, "Reset Page button did appear as expected");
		uiAddNewLeadsPageObjects.btnClosePage.click();
		driver.switchTo().window(uiAdmissionMgrPageObjects.sAdmissionMgr_WindowName);				
	}
	
	@Test(dependsOnMethods={"SubmitLead"})
	public void VerifyLeadInAdmisssionManager(Method objMethod)
	{
		driver.switchTo().window(uiAdmissionMgrPageObjects.sAdmissionMgr_WindowName);
		uiAdmissionMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
		driver.navigate().refresh();
		UserExtension.IsElementPresent(driver, uiAdmissionMgrPageObjects.lnkFirstLeadInTable);
		UserExtension.WaitTillGetTextValueIs(driver, uiAdmissionMgrPageObjects.lnkFirstLeadInTable, sLastName + ", " + sFirstName);
		uiAdmissionMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
		Assert.assertEquals(uiAdmissionMgrPageObjects.lnkFirstLeadInTable.getText().trim(), sLastName1 + ", " + sFirstName1, "Lead not found at the top of the new leads table");				
	}
	
@Test(dependsOnMethods={"VerifyLeadInAdmisssionManager"})
	
	public void ContactInformationTabDetails(Method objMethod)

{
	
	uiAdmissionMgrPageObjects.lnkFirstLeadInTable.click();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	uiStudentManagerPageObjects = new StudentManagerPageObjects(driver);
	driver.switchTo().window(uiStudentManagerPageObjects.sStudentManager_WindowName);	
	UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
	//Assert.assertEquals(uiStudentManagerPageObjects.ContactInformationTab.getText().trim(), "Contact Information");
	uiStudentManagerPageObjects.ContactInformationTab.click();
	
	UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.ProgramDropDown);
	Select dropdownProgram = new Select(uiStudentManagerPageObjects.ProgramDropDown);
	WebDriverWait wait = new WebDriverWait(driver, 35);
	WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlProgramRollups")));
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		dropdownProgram.selectByIndex(1);
		
		//Address Line1 and city
		
		uiStudentManagerPageObjects.addressLine1.clear();
		uiStudentManagerPageObjects.addressLine1.sendKeys("test123");
		uiStudentManagerPageObjects.city.clear();
		uiStudentManagerPageObjects.city.sendKeys("London");
		
		//Select StartDate
		
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.startDateDropDown);
		Select dropdownStartDate = new Select(uiStudentManagerPageObjects.startDateDropDown);
		
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlStartDate")));
		dropdownStartDate.selectByIndex(1);
		
		//Select Lead Classification
		
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.leadClassificationDropDown);
		Select dropdownLeadClassification = new Select(uiStudentManagerPageObjects.leadClassificationDropDown);
		
		WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlLeadClassification")));
		dropdownLeadClassification.selectByVisibleText("Non-applicable");
		
		//Save Link and Yes button Link
		
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnSave);
		uiStudentManagerPageObjects.btnSave.click();
		
			
		//uiStudentManagerPageObjects.btnYes.click();
		
		//Contact Link
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnContact);
		WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")));
		uiStudentManagerPageObjects.btnContact.click();
		
		//Click On AppointmentTab
		
		WebElement element5= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[2]/a/span")));
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.lnkAppointmentTab);
		uiStudentManagerPageObjects.lnkAppointmentTab.click();
		//Assert.assertEquals(uiStudentManagerPageObjects.txtAppointmentDate.getText().trim(), "Appointment Date");
		
		//Add text in Appointment Notes field
		
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.txtAppointmentNotes);
		uiStudentManagerPageObjects.txtAppointmentNotes.clear();
		uiStudentManagerPageObjects.txtAppointmentNotes.sendKeys("Test");
		uiStudentManagerPageObjects.btnAppointmentSave.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		uiStudentManagerPageObjects.btnAppointmentSave.click();
         uiStudentManagerPageObjects.btnAppointmentSave.click();
         try
 		{
 	
 		if(uiStudentManagerPageObjects.btnYes.isDisplayed())
 			uiStudentManagerPageObjects.btnYes.click();
 		
 		}
 				
 				catch(Exception e){
 					Reporter.log(e.getMessage());
 					
 				}
		
		
		//Click on Appointment Save button
		//UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnAppointmentSave);
		//WebElement element6= wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnSave")));
		//uiStudentManagerPageObjects.btnAppointmentSave.submit();
		
		//Navigate to Contact Information Tab
		uiStudentManagerPageObjects.ContactInformationTab.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //check Interview Schedule text on page
		
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.txtInterviewSchedule);
		Assert.assertEquals(uiStudentManagerPageObjects.txtInterviewSchedule.getText().trim(), "Interview Scheduled");
		
		//Click on Interview Complete link
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnInterviewComplete);
		uiStudentManagerPageObjects.btnInterviewComplete.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Click and Verify Application Received button Link
		WebElement element8= wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")));
		UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnApplicationReceived);
		//Assert.assertEquals(uiStudentManagerPageObjects.btnApplicationReceived.getText(), "Application Received");
		uiStudentManagerPageObjects.btnApplicationReceived.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		//Navigate to Contact Information Tab
		uiStudentManagerPageObjects.ContactInformationTab.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//Enrollment Confirmed
				
				UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnEnrollmentConfirmed);
				uiStudentManagerPageObjects.btnEnrollmentConfirmed.click();
				
		
			
		}
}







