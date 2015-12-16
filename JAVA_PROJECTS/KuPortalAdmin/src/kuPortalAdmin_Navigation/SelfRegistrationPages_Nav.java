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
import uiMap_KuPortalAdmin.rolesAndPermissions.CreateRolesPage;
import uiMap_KuPortalAdmin.rolesAndPermissions.ViewRolesAndPermissionsPage;
import uiMap_KuPortalAdmin.selfRegistration.CourseCatalogueConfigurationPage;
import uiMap_KuPortalAdmin.selfRegistration.MilitaryCDLCostPerCreditPage;
import uiMap_KuPortalAdmin.selfRegistration.UserConfigurationPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class SelfRegistrationPages_Nav {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public UserConfigurationPage uiUserConfigurationPageObjects;
	public CourseCatalogueConfigurationPage uiCourseCatalogueConfigurationPageObjects;
	public MilitaryCDLCostPerCreditPage uiMilitaryCDLCostPerCreditPageObjects;
	
	
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
	public void verifyUserConfigurationPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
		uiHomePageObjects.lnkUserConfiguration.click();
		uiUserConfigurationPageObjects = new UserConfigurationPage(driver);
		UserExtension.IsElementPresent(driver, uiUserConfigurationPageObjects.tblStudentConfigHeader);
		Assert.assertEquals(uiUserConfigurationPageObjects.lblPageHeader.getText().trim(), uiUserConfigurationPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiUserConfigurationPageObjects.tblStudentConfigHeader.isDisplayed(), "Student Configuration Table is not displayed");
		Assert.assertEquals(uiUserConfigurationPageObjects.tblStudentConfigHeader.getText().trim(), uiUserConfigurationPageObjects.sTableHeader, "Table Column Header is not as expected");
	}

	@Test
	public void verifyCourseCatalogueConfigPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
		uiHomePageObjects.lnkCourseCatalogue.click();
		uiCourseCatalogueConfigurationPageObjects = new CourseCatalogueConfigurationPage(driver);
		UserExtension.IsElementPresent(driver, uiCourseCatalogueConfigurationPageObjects.tblCourseReqHeader);
		Assert.assertEquals(uiCourseCatalogueConfigurationPageObjects.lblPageHeader.getText().trim(), uiCourseCatalogueConfigurationPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiCourseCatalogueConfigurationPageObjects.tblCourseReqHeader.isDisplayed(), "Course Requirement Table is not displayed");
		Assert.assertEquals(uiCourseCatalogueConfigurationPageObjects.tblCourseReqHeader.getText().trim(), uiCourseCatalogueConfigurationPageObjects.sTableHeader, "Table Column Header is not as expected");
	}

	@Test
	public void verifyMilitaryCDLCostPerCreditPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
		uiHomePageObjects.lnkMilitaryCDL_CPC.click();
		uiMilitaryCDLCostPerCreditPageObjects = new MilitaryCDLCostPerCreditPage(driver);
		UserExtension.IsElementPresent(driver, uiMilitaryCDLCostPerCreditPageObjects.btnAddNew);
		Assert.assertEquals(uiMilitaryCDLCostPerCreditPageObjects.lblPageHeader.getText().trim(), uiMilitaryCDLCostPerCreditPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiMilitaryCDLCostPerCreditPageObjects.btnAddNew.isDisplayed(), "Add New Button is not displayed");
	}

}
