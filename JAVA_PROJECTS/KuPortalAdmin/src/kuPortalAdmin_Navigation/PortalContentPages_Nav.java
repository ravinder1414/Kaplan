package kuPortalAdmin_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import uiMap_KuPortalAdmin.portalContent.ECollegeResourceCenterPage;
import uiMap_KuPortalAdmin.portalContent.EventsPage;
import uiMap_KuPortalAdmin.portalContent.InquiryArchivePage;
import uiMap_KuPortalAdmin.portalContent.InquiryViewPage;
import uiMap_KuPortalAdmin.portalContent.PresentationManagerPage;
import uiMap_KuPortalAdmin.portalContent.SMEDirectoryPage;
import uiMap_KuPortalAdmin.portalContent.StudentStatusBlockingPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class PortalContentPages_Nav {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public ECollegeResourceCenterPage uiECollegeResCenterObjects;
	public EventsPage uiEventPageObjects;
	public PresentationManagerPage uiPresentationMgrPageObjects;
	public StudentStatusBlockingPage uiStudentStatusBlockingPageObjects;
	public InquiryViewPage uiInquiryViewPageObjects;
	public InquiryArchivePage uiInquiryArchivePageObjects;
	public SMEDirectoryPage uiSMIDirctoryPageObjects;
	
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
	public void verifyECollegeResourceCenterPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkeCollegeResourceCenter.click();
		uiECollegeResCenterObjects = new ECollegeResourceCenterPage(driver);
		UserExtension.IsElementPresent(driver, uiECollegeResCenterObjects.btnFilter);
		Assert.assertEquals(uiECollegeResCenterObjects.lblPageHeader.getText().trim(), uiECollegeResCenterObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiECollegeResCenterObjects.btnFilter.isDisplayed(), "Search Filter button is not displayed");
		Assert.assertTrue(uiECollegeResCenterObjects.btnAsssignToAllResults.isDisplayed(), "AssignToAllResults button is not displayed");
		Assert.assertTrue(uiECollegeResCenterObjects.tblDataTable.isDisplayed(), "Data Table is not displayed");
	}

	@Test
	public void verifyEventsPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkEvents.click();
		uiEventPageObjects = new EventsPage(driver);
		UserExtension.IsElementPresent(driver, uiEventPageObjects.btnActionStatus);		
		Assert.assertEquals(uiEventPageObjects.lblPageHeader.getText().trim(), uiEventPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiEventPageObjects.btnActionStatus.isDisplayed(), "Action Status(Enable\\Disable) button is not displayed");
		Assert.assertTrue(uiEventPageObjects.tblNotification.isDisplayed(), "Notification Table is not displayed");
	}


	@Test
	public void verifyPresentationManagerPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkPresentationManager.click();
		uiPresentationMgrPageObjects = new PresentationManagerPage(driver);
		UserExtension.IsElementPresent(driver, uiPresentationMgrPageObjects.btnSearch);		
		Assert.assertEquals(uiPresentationMgrPageObjects.lblPageHeader.getText().trim(), uiPresentationMgrPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiPresentationMgrPageObjects.btnSearch.isDisplayed(), "Search button is not displayed");
		Assert.assertTrue(uiPresentationMgrPageObjects.lnkCreatePresentation.isDisplayed(), "CreatePresentation Link is not displayed");
		Assert.assertTrue(uiPresentationMgrPageObjects.lnkAssignPresentationsToSchool.isDisplayed(), "AssignPresentationsToSchool Link is not displayed");
		Assert.assertTrue(uiPresentationMgrPageObjects.lnkAssignPresentationsToGroup.isDisplayed(), "AssignPresentationsToGroup Link is not displayed");
	}

	@Test
	public void verifyStudentStatusBlockingPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkStudentStatusBlocking.click();
		uiStudentStatusBlockingPageObjects = new StudentStatusBlockingPage(driver);
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.lnkReports);		
		Assert.assertEquals(uiStudentStatusBlockingPageObjects.lblPageHeader.getText().trim(), uiStudentStatusBlockingPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiStudentStatusBlockingPageObjects.lnkReports.isDisplayed(), "Reports Link is not displayed");
		Assert.assertTrue(uiStudentStatusBlockingPageObjects.lnkStudentPermissions.isDisplayed(), "StudentPermissions Link is not displayed");
		uiStudentStatusBlockingPageObjects.lnkReports.click();
		uiStudentStatusBlockingPageObjects = new StudentStatusBlockingPage(driver);
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.lnkHoldCodeManagement);
		Assert.assertTrue(uiStudentStatusBlockingPageObjects.lnkHoldCodeManagement.isDisplayed(), "HoldCodeManagement Link is not displayed");
	}

	@Test
	public void verifyInquiryViewPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkInquiryView.click();
		uiInquiryViewPageObjects = new InquiryViewPage(driver);
		UserExtension.IsElementPresent(driver, uiInquiryViewPageObjects.btnView);		
		Assert.assertEquals(uiInquiryViewPageObjects.lblPageHeader.getText().trim(), uiInquiryViewPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiInquiryViewPageObjects.btnView.isDisplayed(), "View button is not displayed");
		Assert.assertTrue(uiInquiryViewPageObjects.tblInquiryDataTable.isDisplayed(), "InquiryDataTable is not displayed");		
	}

	@Test
	public void verifyInquiryArchivePage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkInquiryView.click();
		uiInquiryArchivePageObjects = new InquiryArchivePage(driver);
		UserExtension.IsElementPresent(driver, uiInquiryArchivePageObjects.btnView);		
		Assert.assertEquals(uiInquiryArchivePageObjects.lblPageHeader.getText().trim(), uiInquiryArchivePageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiInquiryArchivePageObjects.btnView.isDisplayed(), "View button is not displayed");
		Assert.assertTrue(uiInquiryArchivePageObjects.tblInquiryDataTable.isDisplayed(), "InquiryDataTable is not displayed");		
	}

	@Test
	public void verifySMEDirectoryPage()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMIDirctoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMIDirctoryPageObjects.lnkAddNewSME_UploadSMEPhoto);		
		Assert.assertEquals(uiSMIDirctoryPageObjects.lblPageHeader.getText().trim(), uiSMIDirctoryPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiSMIDirctoryPageObjects.lnkAddNewSME_UploadSMEPhoto.isDisplayed(), "AddNewSME_UploadSMEPhoto is not displayed");
		Assert.assertTrue(uiSMIDirctoryPageObjects.tblSMEDirectoryDataTable.isDisplayed(), "SMEDirectoryDataTable is not displayed");		
	}

	
}
