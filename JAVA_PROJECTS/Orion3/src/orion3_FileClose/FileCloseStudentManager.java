package orion3_FileClose;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import orion3_Variables.EnvironmentVariables;

import uiMap_Orion3.FileCloseStudentManagerPage;
import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.SearchLeadsPageObjects;
import uiMap_Orion3.StudentManagerPageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
	

	public class FileCloseStudentManager {


			//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			
			
			//Home Page Page Object Model
			
			//Home Page Page Object Model
			public HomePageObjects uiHomePageObjects;
			public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
			public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
			
			StudentManagerPageObjects uiStudentManagerPageObjects;
			
			public SearchLeadsPageObjects uiSearchLeadsPageObjects;
			
			public FileCloseStudentManagerPage uiFileCloseStudentManagerPage;
			public String mainwinhandle;
			
			
			
			//Static variable
			String sRandStr = RandomStringUtils.randomAlphabetic(5);
			public String sFirstName;
			public String sLastName;		
			public String sEmailAddress;
			public String sleadId;
			public String sDayPhone = "9545151234";
			public String sZipCode = "30256";
			
			//Browser Parameter received from TestNg.xml
			@Parameters({"Browser"})
			@BeforeClass
			public void BeforeNavigation(String sBrowser) throws MalformedURLException
			{
				
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
				}
				catch(Exception ex)
				{	
					Reporter.log("Unable to create the Remotedriver" +  ex.getMessage());		
				}
				driver.get(EnvironmentVariables.sUrl_Orion3);
				mainwinhandle = driver.getWindowHandle();
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
			driver.switchTo().window(uiAdmissionMgrPageObjects.sAdmissionMgr_WindowName);
			uiAdmissionMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
			uiAdmissionMgrPageObjects.lnkFirstLeadInTable.click();
			
					uiStudentManagerPageObjects = new StudentManagerPageObjects(driver);
					driver.switchTo().window(uiStudentManagerPageObjects.sStudentManager_WindowName);	
					UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
					uiStudentManagerPageObjects.ContactInformationTab.click();
					

						uiSearchLeadsPageObjects=new SearchLeadsPageObjects(driver);
						
						sFirstName =uiSearchLeadsPageObjects.txtFirstName.getAttribute("value");

						sLastName =uiSearchLeadsPageObjects.txtLastName.getAttribute("value");
						sEmailAddress =uiSearchLeadsPageObjects.txtEmailAddress.getAttribute("value");
						
						sleadId =uiSearchLeadsPageObjects.LeadID.getText();
						uiFileCloseStudentManagerPage =new FileCloseStudentManagerPage(driver);
						uiFileCloseStudentManagerPage.lnkAppointmentTab.click();
						//Click on File Close Button
						
						UserExtension.IsElementPresent(driver, uiFileCloseStudentManagerPage.btnFileClose);
						
						uiFileCloseStudentManagerPage.btnFileClose.click();
						
						//Navigate to File Close Window
						
						driver.switchTo().window(uiFileCloseStudentManagerPage.sStudent_ManagerFileClose_WindowName);
						
						//Select DropDown Reason
						Select ddlReason = new Select(uiFileCloseStudentManagerPage.ddSelectReason);
						
						ddlReason.selectByIndex(1);
						
						//Select Sub Reason DropDown
						
		               //Select ddlSubReason = new Select(uiFileCloseStudentManagerPage.ddSubReason);
						
		                 //ddlSubReason.selectByIndex(2);
		                 
		                 //Select Notes Fields
		                 uiFileCloseStudentManagerPage.txtNotesRequired.clear();
		                 uiFileCloseStudentManagerPage.txtNotesRequired.sendKeys("test");
		                 
		                 //Click On Save Button
		                 uiFileCloseStudentManagerPage.btnSave.click();
		                 
		                 Alert alert=driver.switchTo().alert();
		                 alert.accept();
						
						//switch to main windowHandle
						
						driver.switchTo().window(mainwinhandle);
						driver.navigate().refresh();
						
				}
						
			@Test(dependsOnMethods={"ClickOnFirstLeads"})
				public void SearchInformationDetails(Method objMethod)
				
				{
				
						uiSearchLeadsPageObjects = new SearchLeadsPageObjects(driver);
						uiSearchLeadsPageObjects.ClickSearchLeads(driver);
						
						// Get Window Frame ID
						driver.switchTo().frame(driver.findElement(By.id("ctl00_PageBodyPlaceHolder_ifContent")));
						
						driver.manage().window().maximize();
						
						//Search on the basis of LeadID
						
						uiSearchLeadsPageObjects.txtSearchLeadID.clear();
						
						uiSearchLeadsPageObjects.txtSearchLeadID.sendKeys(sleadId);
						
					uiSearchLeadsPageObjects.btnLeadsSearch.click();
						
					Assert.assertEquals(uiSearchLeadsPageObjects.resultsEmailField.getText().trim(), sEmailAddress);
						
						
					uiSearchLeadsPageObjects.txtSearchLeadsFirstName.clear();;
					uiSearchLeadsPageObjects.txtSearchLeadsFirstName.sendKeys(sFirstName);
					
					uiSearchLeadsPageObjects.txtSearchLeadsLastName.clear();
						
						uiSearchLeadsPageObjects.txtSearchLeadsLastName.sendKeys(sLastName);
						
						uiSearchLeadsPageObjects.txtSearchLeadsEmail.clear();
						
						uiSearchLeadsPageObjects.txtSearchLeadsEmail.sendKeys(sEmailAddress);
						
						uiSearchLeadsPageObjects.btnLeadsSearch.click();
						Assert.assertEquals(uiSearchLeadsPageObjects.resultsEmailField.getText().trim(), sEmailAddress);
				

					//click on Leads search button
					uiSearchLeadsPageObjects.btnLeadsSearch.click();
					
					//Verify the search Results
					
					uiFileCloseStudentManagerPage =new FileCloseStudentManagerPage(driver);
					Assert.assertEquals(uiFileCloseStudentManagerPage.txtFileCloseStatus.getText().trim(), "File Close");
					
					
			}
	
				}
	


