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
import uiMap_KuPortalAdmin.portalContent.ECollegeResourceCenterPage;
import uiMap_KuPortalAdmin.portalContent.EventsPage;
import uiMap_KuPortalAdmin.portalContent.InquiryArchivePage;
import uiMap_KuPortalAdmin.portalContent.InquiryViewPage;
import uiMap_KuPortalAdmin.portalContent.PresentationManagerPage;
import uiMap_KuPortalAdmin.portalContent.SMEDirectoryPage;
import uiMap_KuPortalAdmin.portalContent.StudentStatusBlockingPage;
import uiMap_KuPortalAdmin.portalUsers.NewAdministratorMappingPage;
import uiMap_KuPortalAdmin.portalUsers.NewInstructorMappingPage;
import uiMap_KuPortalAdmin.portalUsers.NewStudentMappingPage;
import uiMap_KuPortalAdmin.portalUsers.PortalPreviewPage;
import uiMap_KuPortalAdmin.portalUsers.ProvisioningExceptionsPage;
import uiMap_KuPortalAdmin.portalUsers.SearchExistingUserPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class PortalUserPages_Nav {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public NewAdministratorMappingPage uiNewAdministratorMappingPageObjects;
	public NewInstructorMappingPage uiNewInstructorMappingPageObjects;
	public NewStudentMappingPage uiNewStudentMappingPageObjects;
	public PortalPreviewPage uiPortalPreviewPageObjects;
	public ProvisioningExceptionsPage uiProvisioningExceptionsPageObjects;
	public SearchExistingUserPage uiSearchExistingUserPageObjects;
	
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
	public void verifyNewAdminstratorMappingPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkNewAdministratorMapping.click();
		uiNewAdministratorMappingPageObjects = new NewAdministratorMappingPage(driver);
		UserExtension.IsElementPresent(driver, uiNewAdministratorMappingPageObjects.btnSearch);
		Assert.assertEquals(uiNewAdministratorMappingPageObjects.lblPageHeader.getText().trim(), uiNewAdministratorMappingPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiNewAdministratorMappingPageObjects.btnSearch.isDisplayed(), "Search button is not displayed");
	}

	@Test
	public void verifyNewInstructorMappingPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkNewInstructorMapping.click();
		uiNewInstructorMappingPageObjects = new NewInstructorMappingPage(driver);
		UserExtension.IsElementPresent(driver, uiNewInstructorMappingPageObjects.btnSearch);
		Assert.assertEquals(uiNewInstructorMappingPageObjects.lblPageHeader.getText().trim(), uiNewInstructorMappingPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiNewInstructorMappingPageObjects.btnSearch.isDisplayed(), "Search button is not displayed");
	}

	@Test
	public void verifyNewStudentMappingPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkNewStudentMapping.click();
		uiNewStudentMappingPageObjects = new NewStudentMappingPage(driver);
		UserExtension.IsElementPresent(driver, uiNewStudentMappingPageObjects.btnSearch);
		Assert.assertEquals(uiNewStudentMappingPageObjects.lblPageHeader.getText().trim(), uiNewStudentMappingPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiNewStudentMappingPageObjects.btnSearch.isDisplayed(), "Search button is not displayed");
	}

	@Test
	public void verifyPortalPreviewPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkPortalPreview.click();
		uiPortalPreviewPageObjects = new PortalPreviewPage(driver);
		UserExtension.IsElementPresent(driver, uiPortalPreviewPageObjects.ddlUserType);
		Assert.assertEquals(uiPortalPreviewPageObjects.lblPageHeader.getText().trim(), uiPortalPreviewPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiPortalPreviewPageObjects.ddlUserType.isDisplayed(), "UserType drop down list is not displayed");
	}

	@Test
	public void verifyProvisioningExceptionsPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkProvisioningExceptions.click();
		uiProvisioningExceptionsPageObjects = new ProvisioningExceptionsPage(driver);
		UserExtension.IsElementPresent(driver, uiProvisioningExceptionsPageObjects.ddlCampus);
		Assert.assertEquals(uiProvisioningExceptionsPageObjects.lblPageHeader.getText().trim(), uiProvisioningExceptionsPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiProvisioningExceptionsPageObjects.ddlCampus.isDisplayed(), "Campus drop down list is not displayed");
	}
	
	@Test
	public void verifySearchExistingUsersPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		Assert.assertEquals(uiSearchExistingUserPageObjects.lblPageHeader.getText().trim(), uiSearchExistingUserPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiSearchExistingUserPageObjects.btnSearch.isDisplayed(), "Search Button is not displayed");
	}


}
