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
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.Academics.AutoAssignHistoryPageObjects;
import uiMap_Orion3.Academics.RepGroupMgrPageObjects;


public class AcademicsTab {

		//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Home Page Page Object Model
		public HomePageObjects uiHomePageObjects;
		public RepGroupMgrPageObjects uiRepGroupManagerPageObjects;
		public AutoAssignHistoryPageObjects uiAutoAssignHistoryPageObjects;
		
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
		public void  verifyAutoAssignHistoryPage(Method objMethod)		
		{			
			uiAutoAssignHistoryPageObjects = uiHomePageObjects.ClickEducationAdvisorAutoAssignHistory(driver);
			UserExtension.IsElementPresent(driver, uiAutoAssignHistoryPageObjects.lblNamePlateLeft);
			//Left Name Plate
			Assert.assertEquals(uiAutoAssignHistoryPageObjects.lblNamePlateLeft.getText().trim(), uiAutoAssignHistoryPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiAutoAssignHistoryPageObjects.strNamePlateLeft + " is appearing as expected");
			//Auto Assign history table
			UserExtension.IsElementPresent(driver, uiAutoAssignHistoryPageObjects.tblMostRecentAssignment);
			Assert.assertEquals(uiAutoAssignHistoryPageObjects.tblMostRecentAssignment.getText().trim(), uiAutoAssignHistoryPageObjects.strMostRecentAssignment, "Most Recent Assignment table header is NOT appering as expected");
			ReportExtn.Pass(objMethod, uiAutoAssignHistoryPageObjects.strMostRecentAssignment + " table header is appearing as expected");
		}
		@Test
		public void  verifyRepManagerPage(Method objMethod)		
		{			
			uiRepGroupManagerPageObjects = uiHomePageObjects.ClickEducationAdvisorRepManager(driver);
			UserExtension.IsElementPresent(driver, uiRepGroupManagerPageObjects.lblNamePlateLeft);
			//Left Name Plate
			Assert.assertEquals(uiRepGroupManagerPageObjects.lblNamePlateLeft.getText().trim(), uiRepGroupManagerPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiRepGroupManagerPageObjects.strNamePlateLeft + " is appearing as expected");
			//Auto Assign history table
			Assert.assertEquals(uiRepGroupManagerPageObjects.btnView.getAttribute("Value").trim(), uiRepGroupManagerPageObjects.strViewButtonLabel, "Most Recent Assignment table header is NOT appering as expected");
			ReportExtn.Pass(objMethod, "View button is appearing as expected");
		}
		
}
