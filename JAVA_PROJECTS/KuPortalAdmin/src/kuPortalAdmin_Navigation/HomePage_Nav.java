package kuPortalAdmin_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class HomePage_Nav {

	
	//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Home Page Page Object Model
		public HomePage uiHomePageObjects;		
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
			uiLandingPageObjects.LoginToAdminPortal(EnvironmentVariables.sAdminUserName, EnvironmentVariables.sAdminPassword);
			uiHomePageObjects = new HomePage(driver);
			
			
		}
		
		@Test
		public void verifyHomePage_Heading()
		{
			Assert.assertTrue(uiHomePageObjects.lblHomePageHeading.isDisplayed(), "Home Page Heading is not visible");			
			Assert.assertEquals(uiHomePageObjects.lblHomePageHeading.getText().trim(), uiHomePageObjects.sHomePageHeading, "Home Page Heading is not as expected");
		}
		@Test
		public void verifyHomePage_LogoutLink()
		{
			Assert.assertTrue(uiHomePageObjects.lnkLogout.isDisplayed(), "Logout link is not visible");						
		}
		@Test
		public void verifyPortalContent_eCollegeResourceCenterLink()
		{
			/*WebElement child = uiHomePageObjects.lnkPortalcontent;
			WebElement parentTab = child.findElement(By.xpath(".."));
			*/
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkeCollegeResourceCenter.isDisplayed(), "eCollegeResourceCenter Link is not visible");
		}
		
		@Test
		public void verifyPortalContent_EventLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkEvents.isDisplayed(), "Events Link is not visible");			
		}

		@Test
		public void verifyPortalContent_PresentationManagerLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkPresentationManager.isDisplayed(), "PresentationManager Link is not visible");			
		}

		@Test
		public void verifyPortalContent_StudentStatusBlockingLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkStudentStatusBlocking.isDisplayed(), "StudentStatusBlocking Link is not visible");			
		}
		@Test
		public void verifyPortalContent_InquiryViewLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkInquiryView.isDisplayed(), "InquiryView Link is not visible");			
		}
		@Test
		public void verifyPortalContent_InquiryArchiveLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkInquiryArchive.isDisplayed(), "InquiryArchive Link is not visible");			
		}
		@Test
		public void verifyPortalContent_SMEDirectoryLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
			Assert.assertTrue(uiHomePageObjects.lnkSMEDirectory.isDisplayed(), "SMEDirectory Link is not visible");			
		}
		
		@Test
		public void verifyPortalUsers_NewStudentMappingLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkNewStudentMapping.isDisplayed(), "NewStudentMapping Link is not visible");			
		}
		
		@Test
		public void verifyPortalUsers_NewInstructorMappingLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkNewInstructorMapping.isDisplayed(), "NewInstructorMapping Link is not visible");			
		}
		@Test
		public void verifyPortalUsers_NewAdministratorMappingLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkNewAdministratorMapping.isDisplayed(), "NewAdministratorMapping Link is not visible");			
		}
		@Test
		public void verifyPortalUsers_ProvisioningExceptionsLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkProvisioningExceptions.isDisplayed(), "ProvisioningExceptions Link is not visible");			
		}
		@Test
		public void verifyPortalUsers_SearchExistingUsersLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkSearchExistingUsers.isDisplayed(), "SearchExistingUsers Link is not visible");			
		}
		@Test
		public void verifyPortalUsers_PortalPreviewLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
			Assert.assertTrue(uiHomePageObjects.lnkPortalPreview.isDisplayed(), "PortalPreview Link is not visible");			
		}

		@Test
		public void verifyRolesAndPermissions_CreateRolesLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkRolesandPermissions);
			Assert.assertTrue(uiHomePageObjects.lnkCreateRoles.isDisplayed(), "CreateRoles Link is not visible");			
		}

		@Test
		public void verifyRolesAndPermissions_ViewRolesandPermissionsLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkRolesandPermissions);
			Assert.assertTrue(uiHomePageObjects.lnkViewRolesandPermissions.isDisplayed(), "ViewRolesandPermissions Link is not visible");			
		}

		@Test
		public void verifySelfRegistration_UserConfigurationLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
			Assert.assertTrue(uiHomePageObjects.lnkUserConfiguration.isDisplayed(), "UserConfiguration Link is not visible");			
		}
		
		@Test
		public void verifySelfRegistration_CourseCatalogueLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
			Assert.assertTrue(uiHomePageObjects.lnkCourseCatalogue.isDisplayed(), "CourseCatalogue Link is not visible");			
		}
		
		@Test
		public void verifySelfRegistration_MilitaryCDL_CPCLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkSelfRegistration);
			Assert.assertTrue(uiHomePageObjects.lnkMilitaryCDL_CPC.isDisplayed(), "MilitaryCDL_CPC Link is not visible");			
		}
		
		@Test
		public void verifyCourseAndAttendance_KUAttendanceLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkCourses_Attendance);
			Assert.assertTrue(uiHomePageObjects.lnkKUAttendance.isDisplayed(), "KUAttendance Link is not visible");			
		}

		@Test
		public void verifyCourseAndAttendance_KUAttendancePostingLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkCourses_Attendance);
			Assert.assertTrue(uiHomePageObjects.lnkKUAttendancePosting.isDisplayed(), "KUAttendancePosting Link is not visible");			
		}
		
		@Test
		public void verifyCourseAndAttendance_OnlinePaymentsLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkMiscellaneous);
			Assert.assertTrue(uiHomePageObjects.lnkOnlinePayments.isDisplayed(), "OnlinePayments Link is not visible");			
		}
		
		@Test
		public void verifyLiveHelp_LiveHelpLink()
		{
			UserExtension.MouseOver(driver, uiHomePageObjects.lnkLiveHelp);
			Assert.assertTrue(uiHomePageObjects.lnkLiveHelp.isDisplayed(), "LiveHelp Link is not visible");			
		}
		
		@Test
		public void verifyPodHeadingCourseAndAttendance()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodCourses_Attendance.isDisplayed(), "Courses And Attendance Bucket is not visible");			
		}
		
		@Test
		public void verifyPodHeadingMiscellaneous()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodMiscellaneous.isDisplayed(), "Miscellaneous Bucket is not visible");			
		}
		@Test
		public void verifyPodHeadingPortalContent()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodPortalContent.isDisplayed(), "PortalContent Bucket is not visible");			
		}
		@Test
		public void verifyPodHeadingPortalUsers()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodPortalUsers.isDisplayed(), "PortalUsers Bucket is not visible");			
		}
		@Test
		public void verifyPodHeadingRolesAndPermissions()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodRolesAndPermissions.isDisplayed(), "RolesAndPermissions Bucket is not visible");			
		}
		@Test
		public void verifyPodHeadingSelfRegistrationConfig()
		{
		
			Assert.assertTrue(uiHomePageObjects.lblPodSelfRegistrationConfig.isDisplayed(), "SelfRegistrationConfig Bucket is not visible");			
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
}
