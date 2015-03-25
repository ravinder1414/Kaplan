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
import uiMap_Orion3.TicketAdmin.ActionCategoryManagerPageObjects;
import uiMap_Orion3.TicketAdmin.ActionResultsMgrFCDispositionPageObjects;
import uiMap_Orion3.TicketAdmin.StudentManagerPanelPageObjects;
import uiMap_Orion3.TicketAdmin.TicketQueueManagerPageObjects;
import uiMap_Orion3.TicketAdmin.TicketTypeManagerPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class TicketAdminTab {
	

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Page Object Model
	public HomePageObjects uiHomePageObjects;
	public ActionResultsMgrFCDispositionPageObjects uiActionResultMgr;
	public ActionCategoryManagerPageObjects uiActionCategoryMgr;
	public TicketTypeManagerPageObjects uiTicketTypeMgr;
	public TicketQueueManagerPageObjects uiTicketQueueMgr;
	public StudentManagerPanelPageObjects uiStudentMgrPanel;
	
	
		
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
	public void verifyActionResultManager(Method objMethod)
	{
		uiActionResultMgr= uiHomePageObjects.ClickActionResultManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiActionResultMgr.lblNamePlateLeft.getText().trim(), uiActionResultMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiActionResultMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiActionResultMgr.btnView);
		Assert.assertEquals(uiActionResultMgr.btnView.getAttribute("Value").trim(), uiActionResultMgr.strViewLabel, "View button Label on Action Result Manager is not displaying as expected");
		ReportExtn.Pass(objMethod,"View button on Action Result Manager is displaying as expected");
	}

	@Test
	public void verifyActionCategoryManager(Method objMethod)
	{
		uiActionCategoryMgr = uiHomePageObjects.ClickActionCategoryManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiActionCategoryMgr.lblNamePlateLeft.getText().trim(), uiActionCategoryMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiActionCategoryMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiActionCategoryMgr.lnkSearch);
		Assert.assertTrue(uiActionCategoryMgr.lnkSearch.isDisplayed(), "Search link on Action Category Manager is not displaying as expected");
		ReportExtn.Pass(objMethod,"Search link on Action Category Manager is displaying as expected");
	}

	@Test
	public void verifyTicketTypeManager(Method objMethod)
	{
		uiTicketTypeMgr = uiHomePageObjects.ClickTicketTypeManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiTicketTypeMgr.lblNamePlateLeft.getText().trim(), uiTicketTypeMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiTicketTypeMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiTicketTypeMgr.btnSearch);
		Assert.assertEquals(uiTicketTypeMgr.btnSearch.getAttribute("Value").trim(),uiTicketTypeMgr.strSearchLabel, "View button on TicketTypeManager page is not displayed as expected");
		ReportExtn.Pass(objMethod,"View button on TicketTypeManager page is displayed as expected");
	}

	@Test
	public void verifyTicketQueueManager(Method objMethod)
	{
		uiTicketQueueMgr = uiHomePageObjects.ClickTicketQueueManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiTicketQueueMgr.lblNamePlateLeft.getText().trim(), uiTicketQueueMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiTicketQueueMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiTicketQueueMgr.btnSearch);
		Assert.assertEquals(uiTicketQueueMgr.btnSearch.getAttribute("Value").trim(), uiTicketQueueMgr.strSearchLabel, "Search button on TicketQueueManager is not displayed");
		ReportExtn.Pass(objMethod,"Search button on TicketQueueManager is displayed as expected");
	}

	@Test
	public void verifyEmailTemplateMgrAdmissions(Method objMethod)
	{
		uiStudentMgrPanel = uiHomePageObjects.ClickStudentManagerPanel(driver);
		//Left Name Plate
		Assert.assertEquals(uiStudentMgrPanel.lblNamePlateLeft.getText().trim(), uiStudentMgrPanel.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiStudentMgrPanel.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiStudentMgrPanel.btnView);
		Assert.assertEquals(uiStudentMgrPanel.btnView.getAttribute("Value").trim(), uiStudentMgrPanel.strViewLabel, "View button on Student Manager panel is not displayed as expected");
		ReportExtn.Pass(objMethod,"View button on Student Manager panel is displayed as expected");
	}


}
