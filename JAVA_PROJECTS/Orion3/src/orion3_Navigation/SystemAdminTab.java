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
import uiMap_Orion3.SystemAdmin.BusinessUnitMgrPageObjects;
import uiMap_Orion3.SystemAdmin.DepartmentMgrPageObjects;
import uiMap_Orion3.SystemAdmin.NavigationMgrPageObjects;
import uiMap_Orion3.SystemAdmin.SecurityGroupMgrPageObjects;
import uiMap_Orion3.SystemAdmin.SecurityRoleMgrPageObjects;
import uiMap_Orion3.SystemAdmin.TeamTypeMgrPageObjects;
import uiMap_Orion3.SystemAdmin.UserAccountMgrPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class SystemAdminTab {
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Page Object Model
	public HomePageObjects uiHomePageObjects;
	public BusinessUnitMgrPageObjects uiBusinessUnitMgr;
	public DepartmentMgrPageObjects uiDepartmentMgr;
	public NavigationMgrPageObjects uiNavigationMgr;
	public SecurityGroupMgrPageObjects uiSecurityGroupMgr;
	public SecurityRoleMgrPageObjects uiSecurityRoleMgr;
	public TeamTypeMgrPageObjects uiTeamTypeMgr;
	public UserAccountMgrPageObjects uiUserAccountMgr;
	
	
		
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
	public void verifyBusinessUnitManager(Method objMethod)
	{
		uiBusinessUnitMgr = uiHomePageObjects.ClickBusinessUnitManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiBusinessUnitMgr.lblNamePlateLeft.getText().trim(), uiBusinessUnitMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiBusinessUnitMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiBusinessUnitMgr.btnView);
		Assert.assertEquals(uiBusinessUnitMgr.btnView.getAttribute("Value").trim(), uiBusinessUnitMgr.strViewLabel, "View button is not displaying as expected on Business Unit Manager page");
		ReportExtn.Pass(objMethod,"View button is displaying as expected on Business Unit Manager page");
	}

	@Test
	public void verifyDepartmentManager(Method objMethod)
	{
		uiDepartmentMgr = uiHomePageObjects.ClickDepartmentManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiDepartmentMgr.lblNamePlateLeft.getText().trim(), uiDepartmentMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiDepartmentMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiDepartmentMgr.btnView);
		Assert.assertEquals(uiDepartmentMgr.btnView.getAttribute("Value").trim(), uiDepartmentMgr.strViewLabel, "View button on Department Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"View button is displaying on Department Manager page as expected");
	}

	@Test
	public void verifyNavigationManager(Method objMethod)
	{
		uiNavigationMgr = uiHomePageObjects.ClickNavigationManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiNavigationMgr.lblNamePlateLeft.getText().trim(), uiNavigationMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiNavigationMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiNavigationMgr.lnkNavigationTree);
		Assert.assertEquals(uiNavigationMgr.lnkNavigationTree.getText().trim(), uiNavigationMgr.strNavigationTreeHeading, "Navigation Tree heading on Navigation Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"Navigation Tree Heading on Navigation manager page is displaying as expected");
	}

	@Test
	public void verifySecurityGroupManager(Method objMethod)
	{
		uiSecurityGroupMgr = uiHomePageObjects.ClickSecurityGroupManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiSecurityGroupMgr.lblNamePlateLeft.getText().trim(), uiSecurityGroupMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiSecurityGroupMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiSecurityGroupMgr.btnView);
		Assert.assertEquals(uiSecurityGroupMgr.btnView.getAttribute("Value").trim(), uiSecurityGroupMgr.strViewLabel, "View button on Security Group Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"View button is displaying on Security Group Manager page as expected");
	}

	@Test
	public void verifySecurityRoleManager(Method objMethod)
	{
		uiSecurityRoleMgr = uiHomePageObjects.ClickSecurityRoleManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiSecurityRoleMgr.lblNamePlateLeft.getText().trim(), uiSecurityRoleMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiSecurityRoleMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiSecurityRoleMgr.btnView);
		Assert.assertEquals(uiSecurityRoleMgr.btnView.getAttribute("Value").trim(), uiSecurityRoleMgr.strViewLabel, "View button on Security Role Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"View button is displaying on Security Role Manager page as expected");
	}

	@Test
	public void verifyTeamTypeManager(Method objMethod)
	{
		uiTeamTypeMgr = uiHomePageObjects.ClickTeamTypeManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiTeamTypeMgr.lblNamePlateLeft.getText().trim(), uiTeamTypeMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiTeamTypeMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiTeamTypeMgr.lnkCreateNewTeamType);
		Assert.assertEquals(uiTeamTypeMgr.lnkCreateNewTeamType.getText().trim(), uiTeamTypeMgr.strlnkText, "Create New Team Type link on Team Type Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"Create New Team Type link on Team Type Manager Page as expected");
	}

	@Test
	public void verifyUserAccountManager(Method objMethod)
	{
		uiUserAccountMgr = uiHomePageObjects.ClickUserAccountManager(driver);
		//Left Name Plate
		Assert.assertEquals(uiUserAccountMgr.lblNamePlateLeft.getText().trim(), uiUserAccountMgr.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiUserAccountMgr.strNamePlateLeft + " is appearing as expected");
		//Search button
		UserExtension.IsElementPresent(driver, uiUserAccountMgr.btnView);
		Assert.assertEquals(uiUserAccountMgr.btnView.getAttribute("Value").trim(), uiUserAccountMgr.strViewLabel, "View button on User Account Manager page is not displaying as expected");
		ReportExtn.Pass(objMethod,"View button is displaying on User Account Manager page as expected");
	}

	
}
