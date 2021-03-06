package orion3_SearchLeads;

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

import orion3_Variables.EnvironmentVariables;
import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.ReassignLeadsPageObjects;
import uiMap_Orion3.SearchLeadsPageObjects;
import uiMap_Orion3.StudentManagerPageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;

import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class ReassignLeads {


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
		public SearchLeadsPageObjects uiSearchLeadsPageObjects;
		public ReassignLeadsPageObjects uiReassignLeadsPageObjects;
		
		
		//Static variable
		String sRandStr = RandomStringUtils.randomAlphabetic(5);
		
		public String sleadId;
		
		
		
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
		public void ClickOnFirstLeads(Method objMethod)
		{
			uiAdmissionMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
			uiAdmissionMgrPageObjects =new AdmissionsManagerPageObjects(driver);
			driver.navigate().refresh();
			UserExtension.IsElementPresent(driver, uiAdmissionMgrPageObjects.lnkFirstLeadInTable);
			UserExtension.IsElementPresent(driver, uiAdmissionMgrPageObjects.lnkFirstLeadInTable);
				uiAdmissionMgrPageObjects.lnkFirstLeadInTable.click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				uiStudentManagerPageObjects = new StudentManagerPageObjects(driver);
				driver.switchTo().window(uiStudentManagerPageObjects.sStudentManager_WindowName);	
				UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
				//Assert.assertEquals(uiStudentManagerPageObjects.ContactInformationTab.getText().trim(), "Contact Information");
				uiStudentManagerPageObjects.ContactInformationTab.click();
				uiSearchLeadsPageObjects =new SearchLeadsPageObjects(driver);
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				sleadId =uiSearchLeadsPageObjects.LeadID.getText();
				
				driver.close();
				
		driver.switchTo().window(uiSearchLeadsPageObjects.sSearchMain_WindowName);
		}
		
		@Test(dependsOnMethods={"ClickOnFirstLeads"})
		
		public void  ResassignLead()
		{
			//click on Reassign Leads
			uiReassignLeadsPageObjects = new ReassignLeadsPageObjects(driver);
			uiReassignLeadsPageObjects.ClickReassignLeads(driver);
			
			// Select value from Lead Type DropDown
			
			Select ddLeadType = new Select(uiReassignLeadsPageObjects.leadTypeDropDown);
			WebDriverWait wait = new WebDriverWait(driver, 35);

			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ddlTaskTypes2")));
			ddLeadType.selectByVisibleText("KU Admissions");
			
			
			// Enter LeadID in Text Box
			uiReassignLeadsPageObjects.txtLeadID.clear();
			uiReassignLeadsPageObjects.txtLeadID.sendKeys(sleadId);
			uiReassignLeadsPageObjects.btnSearchLeads.click();
			System.out.println(1);
			
			//Wait for check box appear
			
			UserExtension.IsElementPresent(driver, uiReassignLeadsPageObjects.selectCheckBox);
			
			Assert.assertEquals(uiReassignLeadsPageObjects.txtSearchLeadsID.getText().trim(), sleadId);
			//Click on Check Box
			uiReassignLeadsPageObjects.selectCheckBox.click();
			
			//Test Team dropDown value
			
			Select ddTeamValue = new Select(uiReassignLeadsPageObjects.txtTeamDropDown);
			ddTeamValue.selectByVisibleText("Test Team");
			UserExtension.WaitTillAttributeValueIs(driver, driver.findElement(By.id("ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_UpdateProgress1")), "style", "display: non;");
			
			Actions act = new Actions(driver);

           //Assign Leads DropDown value
			
			uiReassignLeadsPageObjects.txtAssignLeadsDropDown.click();
			//WebElement objOption = uiReassignLeadsPageObjects.txtAssignLeadsDropDown.findElement(By.xpath("//option[5]"));
			//objOption.click();
			
			driver.findElement(By.xpath("//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lbRepList']")).click();
			driver.findElement(By.xpath("//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lbRepList']//option[8]")).click();
            
			uiReassignLeadsPageObjects.btnReAssignLeads.click();
			//Click on Pop OK button
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			//Click on Processing close button		
			UserExtension.IsElementPresent(driver, uiReassignLeadsPageObjects.btnProcessingClose);
			uiReassignLeadsPageObjects.btnProcessingClose.click();
			
			//Assert.assertEquals(uiReassignLeadsPageObjects.txtReassignedText.getText().trim(), "Reassigned");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			UserExtension.IsElementPresent(driver, uiReassignLeadsPageObjects.txtCurrentRepText);
			
			//Checking Assigned Repo
			
			UserExtension.WaitTillGetTextValueIs(driver, uiReassignLeadsPageObjects.txtCurrentRepText, "Biggs, Douglas");
			Assert.assertEquals(uiReassignLeadsPageObjects.txtCurrentRepText.getText().trim(), "Biggs, Douglas");
			
		
			
			
		
		}	

}
