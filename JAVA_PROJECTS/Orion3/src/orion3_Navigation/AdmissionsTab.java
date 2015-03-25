package orion3_Navigation;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import orion3_Variables.EnvironmentVariables;

import com.google.common.base.Predicate;
import com.thoughtworks.selenium.webdriven.commands.GetCssCount;

import uiMap_Orion3.HomePageObjects;
import uiMap_Orion3.Academics.AutoAssignHistoryPageObjects;
import uiMap_Orion3.Academics.RepGroupMgrPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3.Admissions.EnrollmentExceptionsPageObjects;
import uiMap_Orion3.Admissions.OpsUpdateArchivePageObjects;
import uiMap_Orion3.Admissions.ReassignLeadDelegationPageObjects;
import uiMap_Orion3.Admissions.ReassignLeadsPageObjects;
import uiMap_Orion3.Admissions.SearchLeadPageObjects;
import uiMap_Orion3.Admissions.SearchLeadsAllNewPageObjects;
import uiMap_Orion3.Admissions.SearchRepsPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class AdmissionsTab {
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionsMgrPageObjects;
	public EnrollmentExceptionsPageObjects uiEnrollmentExceptPageObjects;
	public OpsUpdateArchivePageObjects uiOpsupdateArchivePageObjects;
	public ReassignLeadsPageObjects uiReassignLeadsPageObjects;
	public ReassignLeadDelegationPageObjects uiReassignLeadDelegatePageObjects;
	public SearchLeadPageObjects uiSearchLeadPageObjects;
	public SearchLeadsAllNewPageObjects uiSearchLeadAllNewPageObjects;
	public SearchRepsPageObjects uiSearchRepsPageObjects;
	
		
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
	public void  verifyAdmissionsManagerPage(Method objMethod)		
	{			
		uiAdmissionsMgrPageObjects = uiHomePageObjects.ClickAdmissionsManager(driver);
		UserExtension.IsElementPresent(driver, uiAdmissionsMgrPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiAdmissionsMgrPageObjects.lblNamePlateLeft.getText().trim(), uiAdmissionsMgrPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiAdmissionsMgrPageObjects.strNamePlateLeft + " is appearing as expected");
		//Leads table
		UserExtension.IsElementPresent(driver, uiAdmissionsMgrPageObjects.tblLeadsTable);
		Assert.assertTrue(uiAdmissionsMgrPageObjects.tblLeadsTable.isDisplayed(), "Leads table is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Leads table is appearing as expected");
		
		
	}
	@Test(priority=0)
	public void  verifyEnrollmentExceptionsPage(Method objMethod)		
	{			
		uiEnrollmentExceptPageObjects = uiHomePageObjects.ClickEnrollmentExceptions(driver);
		UserExtension.IsElementPresent(driver, uiAdmissionsMgrPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiEnrollmentExceptPageObjects.lblNamePlateLeft.getText().trim(), uiEnrollmentExceptPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiEnrollmentExceptPageObjects.strNamePlateLeft + " is appearing as expected");
		//Search button lable
		UserExtension.IsElementPresent(driver, uiEnrollmentExceptPageObjects.btnSearch);
		Assert.assertEquals(uiEnrollmentExceptPageObjects.btnSearch.getAttribute("Value").trim(), uiEnrollmentExceptPageObjects.strSearchLabel, "Search button lable is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Search button label is appearing as expected");
	}
	@Test(priority=0)
	public void  verifyOpsUpdateArchivePage(Method objMethod)		
	{			
		uiOpsupdateArchivePageObjects = uiHomePageObjects.ClickOpsUpdateArchive(driver);
		UserExtension.IsElementPresent(driver, uiAdmissionsMgrPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiOpsupdateArchivePageObjects.lblNamePlateLeft.getText().trim(), uiOpsupdateArchivePageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiOpsupdateArchivePageObjects.strNamePlateLeft + " is appearing as expected");
		//Search button label
		UserExtension.IsElementPresent(driver, uiOpsupdateArchivePageObjects.btnSearch);
		Assert.assertEquals(uiOpsupdateArchivePageObjects.btnSearch.getAttribute("Value").trim(), uiOpsupdateArchivePageObjects.strSearchLabel, "Search button lable is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Search button label is appearing as expected");
	}
	@Test(priority=1)
	public void  verifyReassignLeadsPage(Method objMethod)		
	{			
		uiReassignLeadsPageObjects = uiHomePageObjects.ClickReassignLeads(driver);
		UserExtension.IsElementPresent(driver, uiReassignLeadsPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiReassignLeadsPageObjects.lblNamePlateLeft.getText().trim(), uiReassignLeadsPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiReassignLeadsPageObjects.strNamePlateLeft + " is appearing as expected");
		//Reassign button label
		UserExtension.IsElementPresent(driver, uiReassignLeadsPageObjects.btnReassignLeads);
		Assert.assertEquals(uiReassignLeadsPageObjects.btnReassignLeads.getAttribute("Value").trim(), uiReassignLeadsPageObjects.strReassignBtnLabel, "Reassign button lable is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Reassign button label is appearing as expected");
	}
	@Test(priority=1)
	public void  verifyReassignLeadsDelegationPage(Method objMethod)		
	{			
		uiReassignLeadDelegatePageObjects = uiHomePageObjects.ClickReassignLeadsDelegation(driver);
		UserExtension.IsElementPresent(driver, uiReassignLeadDelegatePageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiReassignLeadDelegatePageObjects.lblNamePlateLeft.getText().trim(), uiReassignLeadDelegatePageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiReassignLeadDelegatePageObjects.strNamePlateLeft + " is appearing as expected");
		//Search button label
		UserExtension.IsElementPresent(driver, uiReassignLeadDelegatePageObjects.btnSearch);
		Assert.assertEquals(uiReassignLeadDelegatePageObjects.btnSearch.getAttribute("Value").trim(), uiReassignLeadDelegatePageObjects.strSearchLabel, "Search button lable is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Search button label is appearing as expected");
	}
	@Test(priority=0)
	public void  verifySearchLeadPage(Method objMethod)		
	{			
		uiSearchLeadPageObjects = uiHomePageObjects.ClickSearchLeads(driver);
		UserExtension.IsElementPresent(driver, uiSearchLeadPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiSearchLeadPageObjects.lblNamePlateLeft.getText().trim(), uiSearchLeadPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiSearchLeadPageObjects.strNamePlateLeft + " is appearing as expected");
		
		
		//Search button label
		driver.switchTo().frame(uiSearchLeadPageObjects.frmSearchLead);
		UserExtension.IsElementPresent(driver, uiSearchLeadPageObjects.btnSearchLeads);
		Assert.assertEquals(uiSearchLeadPageObjects.btnSearchLeads.getAttribute("Value").trim(), uiSearchLeadPageObjects.strSearchButtonLabel, "Search button lable is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Search button label is appearing as expected");
		driver.switchTo().defaultContent();
	}
	
	@Test(priority=1)
	public void  verifySearchLeadAllNewPage(Method objMethod)		
	{			
		uiSearchLeadAllNewPageObjects = uiHomePageObjects.ClickSearchLeadsAllNew(driver);
		UserExtension.IsElementPresent(driver, uiSearchLeadAllNewPageObjects.lblNamePlateLeft);
		//Left Name Plate		
		Assert.assertEquals(uiSearchLeadAllNewPageObjects.lblNamePlateLeft.getText().trim(), uiSearchLeadAllNewPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiSearchLeadAllNewPageObjects.strNamePlateLeft + " is appearing as expected");
		//Search button label
		driver.switchTo().frame(uiSearchLeadAllNewPageObjects.frmSearchLead);
		UserExtension.IsElementPresent(driver, uiSearchLeadAllNewPageObjects.btnSearchLeads);
		Assert.assertTrue(uiSearchLeadAllNewPageObjects.btnSearchLeads.isDisplayed(), "Search button is NOT appering as expected");
		ReportExtn.Pass(objMethod,"Search button is appearing as expected");
		driver.switchTo().defaultContent();
//		try
//		{
//			WebDriverWait alertwait = new WebDriverWait(driver, 60);
//			alertwait.until(ExpectedConditions.alertIsPresent());
//			Alert alert = driver.switchTo().alert();	
//			alert.accept();	
//			driver.switchTo().defaultContent();
//		}
//		catch(Exception e){}
//		
//		WebDriverWait alertwaitt = new WebDriverWait(driver, 60);
//		alertwaitt.until((Predicate<WebDriver>)driver.executeScript("return document.readyState", "complete"));
		
	}
	@Test(priority=0)
	public void  verifySearchRepPage(Method objMethod)		
	{			
		uiSearchRepsPageObjects = uiHomePageObjects.ClickSearchReps(driver);
		UserExtension.IsElementPresent(driver, uiSearchRepsPageObjects.lblNamePlateLeft);
		//Left Name Plate
		Assert.assertEquals(uiSearchRepsPageObjects.lblNamePlateLeft.getText().trim(), uiSearchRepsPageObjects.strNamePlateLeft, "Left Name plate is NOT appearing as expected");
		ReportExtn.Pass(objMethod, uiSearchRepsPageObjects.strNamePlateLeft + " is appearing as expected");
		//Search button label
		UserExtension.IsElementPresent(driver, uiSearchRepsPageObjects.btnView);
		Assert.assertEquals(uiSearchRepsPageObjects.btnView.getAttribute("Value").trim(), uiSearchRepsPageObjects.strViewLabel, "View button label is NOT appering as expected");
		ReportExtn.Pass(objMethod,"View button label is appearing as expected");
	}


	
}
