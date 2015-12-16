package kuPortalAdmin_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import uiMap_KuPortalAdmin.CoursesAndAttendance.KuAttendancePage;
import uiMap_KuPortalAdmin.CoursesAndAttendance.KuAttendancePostingPage;
import uiMap_KuPortalAdmin.portalContent.EventsPage;
import uiMap_KuPortalAdmin.rolesAndPermissions.CreateRolesPage;
import uiMap_KuPortalAdmin.rolesAndPermissions.ViewRolesAndPermissionsPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class CoursesAndAttendancePages_Nav {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public KuAttendancePage uiKuAttendancePageObjects;
	public KuAttendancePostingPage uiKuAttendancePostingPageObjects;
	
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
		uiLandingPageObjects.LoginToAdminPortal(EnvironmentVariables.sAdminUserName, EnvironmentVariables.sAdminPassword);
		uiHomePageObjects = new HomePage(driver);
	}
	
	//Recovery Scenario
	@BeforeMethod
	public void BackToHomePage()
	{
		
		driver.get(EnvironmentVariables.sUrl_KuportalAdmin);
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
	public void verifyKuAttendancePage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkCourses_Attendance);
		uiHomePageObjects.lnkKUAttendance.click();
		uiKuAttendancePageObjects = new KuAttendancePage(driver);
		UserExtension.IsElementPresent(driver, uiKuAttendancePageObjects.btnView);		
		Assert.assertEquals(uiKuAttendancePageObjects.lblPageHeader.getText().trim(), uiKuAttendancePageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiKuAttendancePageObjects.btnView.isDisplayed(), "View button is not displayed");
		Assert.assertEquals(uiKuAttendancePageObjects.btnView.getAttribute("Value").trim(), uiKuAttendancePageObjects.sViewText, "View button text is not as expected");
	}

	@Test
	public void verifyKuAttendancePostingPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkCourses_Attendance);
		uiHomePageObjects.lnkKUAttendancePosting.click();
		uiKuAttendancePostingPageObjects = new KuAttendancePostingPage(driver);
		UserExtension.IsElementPresent(driver, uiKuAttendancePostingPageObjects.btnSubmit);		
		Assert.assertEquals(uiKuAttendancePostingPageObjects.lblPageHeader.getText().trim(), uiKuAttendancePostingPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiKuAttendancePostingPageObjects.btnSubmit.isDisplayed(), "Submit button is not displayed");
		Assert.assertEquals(uiKuAttendancePostingPageObjects.btnSubmit.getAttribute("Value").trim(), uiKuAttendancePostingPageObjects.sSubmitText, "View button text is not as expected");
	}

}
