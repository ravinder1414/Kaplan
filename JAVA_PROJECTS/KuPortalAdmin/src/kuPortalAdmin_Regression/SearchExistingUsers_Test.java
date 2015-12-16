package kuPortalAdmin_Regression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortal.LoginPage;
import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import uiMap_KuPortalAdmin.portalUsers.SearchExistingUserPage;
import uiMap_KuPortalAdmin.portalUsers.ViewEditPortalUserPage;
import uiMap_KuPortalAdmin.selfRegistration.CourseCatalogueConfigurationPage;
import uiMap_KuPortalAdmin.selfRegistration.MilitaryCDLCostPerCreditPage;
import uiMap_KuPortalAdmin.selfRegistration.UserConfigurationPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class SearchExistingUsers_Test {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public SearchExistingUserPage uiSearchExistingUserPageObjects;
	public ViewEditPortalUserPage uiViewEditPortalUserPagePageObjects;
	public LoginPage uiKuPortalLoginPageObjects;
	
	//Application Variable
	public String sStudent_FirstName;
	public String sStudent_LastName;
	public String sStudent_UserName;
	public String sFaculty_FirstName;
	public String sFaculty_LastName;
	public String sFaculty_UserName;
	public String sStudent_Password;
	public String sReset_Password;
	public String sReset_UserName;
	
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeSearchExistingUsers(String sBrowser) throws MalformedURLException
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
		
		//Read Application Variables from Properties File
		FileInputStream objFileInputStream = null;
		Properties objProperties = new Properties();
		File objFile = new File(EnvironmentVariables.sApplicationVariablePath);
		try {
				objFileInputStream = new FileInputStream(objFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
				objProperties.load(objFileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sFaculty_UserName = objProperties.getProperty("Faculty_UserName");
		sFaculty_FirstName = objProperties.getProperty("Faculty_FirstName");
		sFaculty_LastName = objProperties.getProperty("Faculty_LastName");
		sStudent_UserName = objProperties.getProperty("Student_UserName");
		sStudent_FirstName = objProperties.getProperty("Student_FirstName");
		sStudent_LastName = objProperties.getProperty("Student_LastName");
		sStudent_Password = objProperties.getProperty("Student_Password");
		sReset_Password = objProperties.getProperty("Reset_Password");
		sReset_UserName = objProperties.getProperty("Reset_UserName");
		
		
	}
	
	//Recovery Scenario
	@BeforeMethod
	public void BackToHomePage()
	{
		
		driver.get(EnvironmentVariables.sUrl_KuportalAdmin);	
	}

	@AfterClass
	public void AfterSearchExistingUsers()
	{
		//Quit the test after test class execution
		if(driver != null)
		{
			driver.quit();			
		}
	}
	
	@Test
	public void SearchExistingUser_DetailsDisplayed()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		Assert.assertEquals(uiSearchExistingUserPageObjects.lblPageHeader.getText().trim(), uiSearchExistingUserPageObjects.sPageHeaderTxt, "Page Header is not as expected");
		Assert.assertTrue(uiSearchExistingUserPageObjects.btnSearch.isDisplayed(), "Search Button is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.lblLastName.isDisplayed(), "Last Name Label is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.lblFirstName.isDisplayed(), "First Name Label is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.lblUserName.isDisplayed(), "UserName Label is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.lblUserType.isDisplayed(), "UserType Label is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.lblCampus.isDisplayed(), "Campus Label is not displayed");
		
		Assert.assertTrue(uiSearchExistingUserPageObjects.txtLastName.isDisplayed(), "Last Name Text box is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.txtFirstName.isDisplayed(), "First Name Text box is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.txtUserName.isDisplayed(), "UserName Text box is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.ddlUserType.isDisplayed(), "UserType drop down is not displayed");
		Assert.assertTrue(uiSearchExistingUserPageObjects.ddlCampusId.isDisplayed(), "Campus drop down is not displayed");
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Admin");
		ddlUsertype.selectByVisibleText("Student");
		ddlUsertype.selectByVisibleText("Instructor");
		ddlUsertype.selectByVisibleText("NonMapped");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		ddlCampus.selectByVisibleText("-- Any Campus --");
	}
	
	@Test
	public void SearchExistingUser_SearchByFirstName()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		uiSearchExistingUserPageObjects.txtFirstName.sendKeys(sStudent_FirstName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_FirstName + "]"));
		Assert.assertTrue(objElement.getText().contains(sStudent_FirstName), "Search Results are not as expected");
	}
	
	@Test
	public void SearchExistingUser_SearchByLastName()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		uiSearchExistingUserPageObjects.txtLastName.sendKeys(sStudent_LastName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_LastName + "]"));
		Assert.assertTrue(objElement.getText().contains(sStudent_LastName), "Search Results are not as expected");
	}
	
//	@Test
	public void SearchExistingUser_SearchByUserName()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sStudent_UserName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));
		Assert.assertTrue(objElement.getText().contains(sStudent_UserName), "Search Results are not as expected");
	}

//	@Test
	public void SearchExistingUser_SearchFaculty()
	{
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Instructor");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sFaculty_UserName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));
		Assert.assertTrue(objElement.getText().contains(sFaculty_UserName), "Search Results are not as expected");
	}

	@Test
	public void SearchExistingUser_ResetPassword()
	{
		//Search the Student in Search Existing User
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sStudent_UserName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));
		Assert.assertTrue(objElement.getText().contains(sStudent_UserName), "Search Results are not as expected");
		WebElement objViewEditLink = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_ViewEditLink + "]/a")); 
		
		//Edit - Reset Password
		objViewEditLink.click();
		uiViewEditPortalUserPagePageObjects = new ViewEditPortalUserPage(driver);
		uiViewEditPortalUserPagePageObjects.lnkResetStudentPasswordTab.click();
		
		uiViewEditPortalUserPagePageObjects.chkStudentIdentity.click();
		uiViewEditPortalUserPagePageObjects.txtAdminPassword.sendKeys(EnvironmentVariables.sAdminPassword);
		uiViewEditPortalUserPagePageObjects.txtNewPassword.sendKeys(sReset_Password);
		uiViewEditPortalUserPagePageObjects.txtConfirmPassword.sendKeys(sReset_Password);
		uiViewEditPortalUserPagePageObjects.btnChangePassword.click();
		
		UserExtension.IsElementPresent(driver, uiViewEditPortalUserPagePageObjects.lblResetResult);
		UserExtension.WaitTillGetTextValueIs(driver, uiViewEditPortalUserPagePageObjects.lblResetResult, "Password for " + sStudent_UserName + " was changed successfully.");
		Assert.assertEquals(uiViewEditPortalUserPagePageObjects.lblResetResult.getText().trim(), "Password for " + sStudent_UserName + " was changed successfully.", "Reset Sucessfully message did not appear as expected");
		
		uiHomePageObjects.lnkLogout.click();
		
		//Login to KuPortal using new password
		driver.get(EnvironmentVariables.sUrl_Kuportal);
		uiKuPortalLoginPageObjects = new LoginPage(driver);
		uiKuPortalLoginPageObjects.LoginToKuPortal(sStudent_UserName, sReset_Password);
		UserExtension.IsElementPresent(driver, uiKuPortalLoginPageObjects.lnkLogout);
		Assert.assertTrue(uiKuPortalLoginPageObjects.lnkLogout.isDisplayed(), "Unable to Login to KuPortal, after reset the password");
		uiKuPortalLoginPageObjects.lnkLogout.click();
		
		
		//Reset to Old Password
		driver.get(EnvironmentVariables.sUrl_KuportalAdmin);
		uiLandingPageObjects.LoginToAdminPortal(EnvironmentVariables.sAdminUserName, EnvironmentVariables.sAdminPassword);
		
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
		
		ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sStudent_UserName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));
		Assert.assertTrue(objElement.getText().contains(sStudent_UserName), "Search Results are not as expected");
		objViewEditLink = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_ViewEditLink + "]/a")); 
		objViewEditLink.click();
		uiViewEditPortalUserPagePageObjects = new ViewEditPortalUserPage(driver);
		uiViewEditPortalUserPagePageObjects.lnkResetStudentPasswordTab.click();
		
		uiViewEditPortalUserPagePageObjects.chkStudentIdentity.click();
		uiViewEditPortalUserPagePageObjects.txtAdminPassword.sendKeys(EnvironmentVariables.sAdminPassword);
		uiViewEditPortalUserPagePageObjects.txtNewPassword.sendKeys(sStudent_Password);
		uiViewEditPortalUserPagePageObjects.txtConfirmPassword.sendKeys(sStudent_Password);
		uiViewEditPortalUserPagePageObjects.btnChangePassword.click();
		
		UserExtension.IsElementPresent(driver, uiViewEditPortalUserPagePageObjects.lblResetResult);
		UserExtension.WaitTillGetTextValueIs(driver, uiViewEditPortalUserPagePageObjects.lblResetResult, "Password for " + sStudent_UserName + " was changed successfully.");
		Assert.assertEquals(uiViewEditPortalUserPagePageObjects.lblResetResult.getText().trim(), "Password for " + sStudent_UserName + " was changed successfully.", "Reset Sucessfully message did not appear as expected, when Resetting the password back to actual");		
	}
	
	@Test
	public void SearchExistingUser_ResetUserName()
	{
		String sPortalId, sCurrentUsername, sNewUsername;
		
		
		//Search the Student in Search Existing User
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
			
		Select ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		Select ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sReset_UserName);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		WebElement objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));
		Assert.assertTrue(objElement.getText().contains(sReset_UserName), "Search Results are not as expected");
		WebElement objViewEditLink = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_ViewEditLink + "]/a")); 
		sPortalId = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_PortalUserID + "]")).getText();
		//Edit - Generate New UserName
		objViewEditLink.click();	
		uiViewEditPortalUserPagePageObjects = new ViewEditPortalUserPage(driver);
		UserExtension.IsElementPresent(driver, uiViewEditPortalUserPagePageObjects.lnkGenerateNewUserName);
		uiViewEditPortalUserPagePageObjects.lnkGenerateNewUserName.click();
		UserExtension.IsElementPresent(driver, uiViewEditPortalUserPagePageObjects.btnSave);
		
		Assert.assertEquals(uiViewEditPortalUserPagePageObjects.lblGenerateNewUsernameHeading.getText().trim(), uiViewEditPortalUserPagePageObjects.sGenerateUserNameHeading, "Heading label is not as expected");
		sCurrentUsername = uiViewEditPortalUserPagePageObjects.lblCurrentUsername.getText().trim();
		sNewUsername = uiViewEditPortalUserPagePageObjects.lblNewUsername.getText().trim();
		if(sCurrentUsername.equalsIgnoreCase(sNewUsername))
		{
			Assert.assertTrue(false, "Current Username is same as Generated New Username");
		}
		uiViewEditPortalUserPagePageObjects.btnSave.click();

		//Search the Student in Search Existing User
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalusers);
		uiHomePageObjects.lnkSearchExistingUsers.click();
		uiSearchExistingUserPageObjects = new SearchExistingUserPage(driver);
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.btnSearch);
			
		ddlUsertype = new Select(uiSearchExistingUserPageObjects.ddlUserType);
		ddlUsertype.selectByVisibleText("Student");
		
		ddlCampus = new Select(uiSearchExistingUserPageObjects.ddlCampusId);
		ddlCampus.selectByVisibleText("042 Kaplan University, Online");
		uiSearchExistingUserPageObjects.txtUserName.sendKeys(sNewUsername);
		uiSearchExistingUserPageObjects.btnSearch.click();
		UserExtension.IsElementPresent(driver, uiSearchExistingUserPageObjects.tblUser);
		objElement = uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_UserName + "]"));		
		Assert.assertTrue(objElement.getText().contains(sNewUsername), "Unable Search using the New generated Username");		
		Assert.assertEquals(uiSearchExistingUserPageObjects.tblUser.findElement(By.xpath("tbody/tr/td[" + uiSearchExistingUserPageObjects.iCol_PortalUserID + "]")).getText(),sPortalId, "Portal ID is not same as before");
	}
}
