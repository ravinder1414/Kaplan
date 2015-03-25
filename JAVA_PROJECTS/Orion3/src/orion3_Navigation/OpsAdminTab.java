package orion3_Navigation;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;








import orion3_Variables.EnvironmentVariables;
import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.OpsAdmin.ALCCampMngUploadLeadsPageObjects;
import uiMap_Orion3.OpsAdmin.ARAManageTiersPageObjects;
import uiMap_Orion3.OpsAdmin.EmailTemplateMgrAdmPageObjects;
import uiMap_Orion3.OpsAdmin.EmailTemplateMgrPostEnrollPageObjects;
import uiMap_Orion3.OpsAdmin.IQMCancelLeadTransferPageObjects;
import uiMap_Orion3.OpsAdmin.IQMLeadTransferPageObjects;
import uiMap_Orion3.OpsAdmin.StudentSuccessManagerPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class OpsAdminTab {

	//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Page Object Model
		public HomePageObjects uiHomePageObjects;
		public ARAManageTiersPageObjects uiARAManageTiers;
		public StudentSuccessManagerPageObjects uiStudentSuccessMrg;
		public EmailTemplateMgrAdmPageObjects uiEmailTemplateMgrAdm;
		public EmailTemplateMgrPostEnrollPageObjects uiEmailTemplatePostEnroll;
		public IQMLeadTransferPageObjects uiIQMLeadTransfer;
		public IQMCancelLeadTransferPageObjects uiIQMCancelLeadTransfer;
		public ALCCampMngUploadLeadsPageObjects uiALCCampMgrUploadLead;
		
		
			
		//Method which will executed before the class loads
		//Browser Parameter received from TestNg.xml
		@Parameters({"Browser"})
		@BeforeClass
		public void BeforeNavigation(String sBrowser) throws MalformedURLException
		{
			
			//Edit Browser Capabilities as per project
			//Fire fox Profile		
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_Orion3);
			

			//Chrome Options
			ChromeOptions options = new ChromeOptions();
			options.addArguments("auth-server-whitelist="+EnvironmentVariables.sUrl_Orion3);
			options.addArguments("--start-maximized");
			
			//Capability
			objBrowserMgr = new BrowserManagement(sBrowser);
			if(sBrowser.equalsIgnoreCase("firefox"))
			{
				objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);
			}
			else if(sBrowser.equalsIgnoreCase("chrome"))
			{
				objBrowserMgr.capability.setCapability(ChromeOptions.CAPABILITY, options);
			}
			
				
				
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
		public void verifyARA_ManageTiers(Method objMethod)
		{
			uiARAManageTiers = uiHomePageObjects.ClickARA_ManageTiers(driver);
			//Left Name Plate
			Assert.assertEquals(uiARAManageTiers.lblNamePlateLeft.getText().trim(), uiARAManageTiers.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiARAManageTiers.strNamePlateLeft + " is appearing as expected");
			//Leads table
			UserExtension.IsElementPresent(driver, uiARAManageTiers.tblAsssmentTiers);
			Assert.assertTrue(uiARAManageTiers.tblAsssmentTiers.isDisplayed(), "Assement table is NOT appering as expected");
			ReportExtn.Pass(objMethod,"Assement table is appearing as expected");
			
		}
		
		@Test
		public void verifyStudentSuccessManager(Method objMethod)
		{
			uiStudentSuccessMrg = uiHomePageObjects.ClickStudentSuccessMgr(driver);
			//Left Name Plate
			Assert.assertEquals(uiStudentSuccessMrg.lblNamePlateLeft.getText().trim(), uiStudentSuccessMrg.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiStudentSuccessMrg.strNamePlateLeft + " is appearing as expected");
			//Payout and bonus Tabs
			UserExtension.IsElementPresent(driver, uiStudentSuccessMrg.lnkPayoutDates);
			Assert.assertTrue(uiStudentSuccessMrg.btnSearchPayoutDates.isDisplayed(), "Search button on Payout Dates tab is not displayed");
			ReportExtn.Pass(objMethod,"Search button on Payout Dates tab is displayed");
			
			uiStudentSuccessMrg.lnkBonusCredential.click();
			UserExtension.IsElementPresent(driver, uiStudentSuccessMrg.btnSearchBonusCredentials);
			Assert.assertTrue(uiStudentSuccessMrg.btnSearchBonusCredentials.isDisplayed(), "Search button on Bonus Credentials tab is not displayed");
			ReportExtn.Pass(objMethod, "Search button on Bonus Credentials tab is displayed");
			
			uiStudentSuccessMrg.lnkBonusEligibility.click();
			UserExtension.IsElementPresent(driver, uiStudentSuccessMrg.tblBonusEligibility);
			Assert.assertTrue(uiStudentSuccessMrg.tblBonusEligibility.isDisplayed(), "Bonus Eligibility table on Bonus Eligibility tab is not displayed");
			ReportExtn.Pass(objMethod, "Bonus Eligibility table on Bonus Eligibility tab is not displayed");

			uiStudentSuccessMrg.lnkManageBonus.click();
			UserExtension.IsElementPresent(driver, uiStudentSuccessMrg.btnSearchBonusAdjustment);
			Assert.assertTrue(uiStudentSuccessMrg.btnSearchBonusAdjustment.isDisplayed(), "Search button on Manage Bonus tab is not displayed");
			ReportExtn.Pass(objMethod, "Search button on Manage Bonus tab is displayed");
			
		}
		
		@Test
		public void verifyEmailTemplateMgrAdmissions(Method objMethod)
		{
			uiEmailTemplateMgrAdm = uiHomePageObjects.ClickEmailTemplateMgrAdm(driver);
			//Left Name Plate
			Assert.assertEquals(uiEmailTemplateMgrAdm.lblNamePlateLeft.getText().trim(), uiEmailTemplateMgrAdm.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiEmailTemplateMgrAdm.strNamePlateLeft + " is appearing as expected");
			//Search button
			UserExtension.IsElementPresent(driver, uiEmailTemplateMgrAdm.btnSearch);
			Assert.assertTrue(uiEmailTemplateMgrAdm.btnSearch.isDisplayed(), "Search button on EmailTemplateManager_Admissions is not displayed");
			ReportExtn.Pass(objMethod,"Search button on EmailTemplateManager_Admissions is displayed");
		}
		
		@Test
		public void verifyEmailTemplateMgrPostEnroll(Method objMethod)
		{
			uiEmailTemplatePostEnroll = uiHomePageObjects.ClickEmailTemplateMgrPostEnroll(driver);
			//Left Name Plate
			Assert.assertEquals(uiEmailTemplatePostEnroll.lblNamePlateLeft.getText().trim(), uiEmailTemplatePostEnroll.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiEmailTemplatePostEnroll.strNamePlateLeft + " is appearing as expected");
			//Search button
			UserExtension.IsElementPresent(driver, uiEmailTemplatePostEnroll.btnSearch);
			Assert.assertTrue(uiEmailTemplatePostEnroll.btnSearch.isDisplayed(), "Search button on EmailTemplate Post Enrollment is not displayed");
			ReportExtn.Pass(objMethod,"Search button on EmailTemplate Post Enrollment is displayed");
		}

		@Test
		public void verifyIQM_LeadTransfer(Method objMethod)
		{
			uiIQMLeadTransfer = uiHomePageObjects.ClickIQM_LeadTransfer(driver);
			//Left Name Plate
			Assert.assertEquals(uiIQMLeadTransfer.lblNamePlateLeft.getText().trim(), uiIQMLeadTransfer.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiIQMLeadTransfer.strNamePlateLeft + " is appearing as expected");
			//Search button
			UserExtension.IsElementPresent(driver, uiIQMLeadTransfer.btnSendTransfer);
			Assert.assertEquals(uiIQMLeadTransfer.btnSendTransfer.getAttribute("value").trim(), uiIQMLeadTransfer.sSendTransfer, "Send Transfer Request label on button is not displayed");
			ReportExtn.Pass(objMethod,"Send Transfer Request label on button is displayed");
		}
		
		@Test
		public void verifyIQM_CancelLeadTransfer(Method objMethod)
		{
			uiIQMCancelLeadTransfer = uiHomePageObjects.ClickIQM_CancelLeadTransfer(driver);
			//Left Name Plate
			Assert.assertEquals(uiIQMCancelLeadTransfer.lblNamePlateLeft.getText().trim(), uiIQMCancelLeadTransfer.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiIQMCancelLeadTransfer.strNamePlateLeft + " is appearing as expected");
			//Search button
			UserExtension.IsElementPresent(driver, uiIQMCancelLeadTransfer.btnSendTransfer);
			Assert.assertEquals(uiIQMCancelLeadTransfer.btnSendTransfer.getAttribute("value").trim(), uiIQMCancelLeadTransfer.sSendTransfer, "Send Cancel Request label on button is not displayed");
			ReportExtn.Pass(objMethod,"Send Cancel Request label on button is displayed");
		}
		
		@Test
		public void verifyALCCampaignManagerUploadLeads(Method objMethod)
		{
			uiALCCampMgrUploadLead = uiHomePageObjects.ClickALCCampaignManagerUplodLeads(driver);
			//Left Name Plate
			Assert.assertEquals(uiALCCampMgrUploadLead.lblNamePlateLeft.getText().trim(), uiALCCampMgrUploadLead.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiALCCampMgrUploadLead.strNamePlateLeft + " is appearing as expected");
			//Search button
			UserExtension.IsElementPresent(driver, uiALCCampMgrUploadLead.btnBrowseUpload);
			Assert.assertTrue(uiALCCampMgrUploadLead.btnBrowseUpload.isDisplayed(), "Browse button is not displayed");
			ReportExtn.Pass(objMethod,"Browse button is not displayed");
		}


}
