package kuPortalAdmin_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortalAdmin.LandingPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;


public class LandingPage_Nav {
	
	
		//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Home Page Page Object Model
		public LandingPage uiLandingPageObjects;		
		
		//Method which will executed before the class loads
		//Browser Parameter received from TestNg.xml
		@Parameters({"Browser"})
		@BeforeClass
		public void BeforeNavigation(String sBrowser) throws MalformedURLException
		{
			
			//Edit Browser Capabilities as per project
			//Fire fox Profile		
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_KuportalAdmin);
			
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
			driver.get(EnvironmentVariables.sUrl_KuportalAdmin);
			driver.manage().window().maximize();
			uiLandingPageObjects = new LandingPage(driver);
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
		public void verifyUsername()
		{
			Assert.assertTrue(uiLandingPageObjects.txtUserName.isDisplayed(), "UserName Text box is not displayed");
			Assert.assertTrue(uiLandingPageObjects.lblUsername.isDisplayed(), "UserName Label is not displayed");
		}
		@Test
		public void verifyPassword()
		{
			Assert.assertTrue(uiLandingPageObjects.txtPassword.isDisplayed(), "Password Text box is not displayed");
			Assert.assertTrue(uiLandingPageObjects.lblPassword.isDisplayed(), "Password Label is not displayed");
		}
		@Test
		public void verifyLoginButton()
		{
			Assert.assertTrue(uiLandingPageObjects.btnLogin.isDisplayed(), "Login button is not displayed");
		}
		@Test
		public void verifyLoginDomain()
		{
			Assert.assertTrue(uiLandingPageObjects.ddlLoginDomain.isDisplayed(), "Login Domain is not displayed");
			Select ddlLoginType = new Select(uiLandingPageObjects.ddlLoginDomain);
			ddlLoginType.selectByVisibleText("Faculty");
			ddlLoginType.selectByVisibleText("Admin");
			ddlLoginType.selectByVisibleText("Student");
		}
		


}
