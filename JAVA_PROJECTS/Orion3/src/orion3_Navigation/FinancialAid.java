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
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3.Admissions.EnrollmentExceptionsPageObjects;
import uiMap_Orion3.Admissions.OpsUpdateArchivePageObjects;
import uiMap_Orion3.Admissions.ReassignLeadDelegationPageObjects;
import uiMap_Orion3.Admissions.ReassignLeadsPageObjects;
import uiMap_Orion3.Admissions.SearchLeadPageObjects;
import uiMap_Orion3.Admissions.SearchLeadsAllNewPageObjects;
import uiMap_Orion3.Admissions.SearchRepsPageObjects;
import uiMap_Orion3.FinanceAid.FASearchPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class FinancialAid {

	//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Page Object Model
		public HomePageObjects uiHomePageObjects;
		public FASearchPageObjects uiFASearchPageObjects;
		
			
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
		public void verifyFASearch(Method objMethod)
		{
			uiFASearchPageObjects = uiHomePageObjects.ClickFASearch(driver);
			UserExtension.IsElementPresent(driver, uiFASearchPageObjects.lblNamePlateLeft);
			//Left Name Plate
			Assert.assertEquals(uiFASearchPageObjects.lblNamePlateLeft.getText().trim(), uiFASearchPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
			ReportExtn.Pass(objMethod, uiFASearchPageObjects.strNamePlateLeft + " is appearing as expected");
			//Search button label
			UserExtension.IsElementPresent(driver, uiFASearchPageObjects.btnSearch);
			Assert.assertEquals(uiFASearchPageObjects.btnSearch.getAttribute("Value").trim(), uiFASearchPageObjects.strSearchLabel, "Search button label is NOT appering as expected");
			ReportExtn.Pass(objMethod,"Search button label is appearing as expected");
		}

}
