package srm_Navigation;
//Import files
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import srm_Variables.EnvironmentVariables;
import uiMap_Orion3_SRM.AddALeadReferralPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class AddALeadReferralPage {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AddALeadReferralPageObjects uiAddALeadReferralPage;
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		try
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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
		}
		catch(Exception ex)
		{	
			ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
		}
		driver.get(EnvironmentVariables.sUrl_Orion3);
		uiHomePageObjects = new HomePageObjects(driver);
		//Click Add a lead Referral
		if(uiHomePageObjects.lnkAddaLeadReferral.isDisplayed())
		{
			uiAddALeadReferralPage = uiHomePageObjects.ClickAddaLeadReferralLink(driver);
		}
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
		
	}
	
	
	

	@AfterClass
	public void AfterNavigation()
	{

		try
		{

		//Quit the test after test class execution
		if(driver != null)
		{
			driver.quit();			
		}
		}
			
		catch (Exception e)
									
		{
			Reporter.log(e.getMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
	
	
	

	//Add a lead Referral text
	@Test(priority=1)
	public void verifyAddaLeadReferralText(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblAddALeadReferral.getText().trim(), uiAddALeadReferralPage.strAddALeadReferral);
		}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
	}
		
	
	
	//verifyFirstNameLabelAndField
	@Test(priority=1)
	public void verifyFirstNameLabelAndField(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblFirstName.getText().trim(),uiAddALeadReferralPage.strFirstName, "First Name Label Text is not correct");
		ReportExtn.Pass(objMethod, "First Name Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtFirstName.isDisplayed(), "First Name Field is not displayed" );
		ReportExtn.Pass(objMethod, "First Name Field is displayed");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
	
	
	
	//verifyLastNameLabelAndField
	@Test(priority=1)
	public void verifyLastNameLabelAndField(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.strLastName, uiAddALeadReferralPage.lblLastName.getText().trim(), "Last Name Label Text is not correct");
		ReportExtn.Pass(objMethod, "Last Name Label Text is Visible");
		}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
	}
	
	
	//Required Information Text
	@Test(priority=1)
	public void verifyRequiredInformationText(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblRequiredInfo.getText().trim(),uiAddALeadReferralPage.strRequiredInfo, "Required Information Text is not correct");
		ReportExtn.Pass(objMethod, "Required Information Text Text is Visible");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

	}
	
	
	
	//EmailAddress Label Text and Field
	@Test(priority=1)
	public void verifyEmailAddressLabelAndField(Method objMethod)
	{
		try
		{


		Assert.assertEquals(uiAddALeadReferralPage.lblEmailAddress.getText().trim(), uiAddALeadReferralPage.strEmailAddress, "EmailAddress Label Text is not correct");
		ReportExtn.Pass(objMethod, "EmailAddress Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtLastName.isDisplayed(), "EmailAddress Field is not displayed");
		ReportExtn.Pass(objMethod, "EmailAddress Field is displayed");
		}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}

	}
	
	
	
	//DayTimePhone Label Text
	@Test(priority=1)
	public void verifyDaytimePhoneLabelAndField(Method objMethod)
	{
		try
		{


		Assert.assertEquals(uiAddALeadReferralPage.lblDaytimePhone.getText().trim(), uiAddALeadReferralPage.strDaytimePhone, "DayTimePhone Label Text is not correct");
		ReportExtn.Pass(objMethod, "DayTimePhone Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtDayPhone.isDisplayed(), "DayTimePhone Field is not displayed");
		ReportExtn.Pass(objMethod, "DayTimePhone Field is displayed");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
		
	}
	
	
	
	//Evening Phone Label Text
	@Test(priority=1)
	public void verifyEveningtimePhoneLabelAndField(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblEveningPhone.getText().trim(), uiAddALeadReferralPage.strEveningPhone, "Evening Phone Label Text is not correct");
		ReportExtn.Pass(objMethod, "Evening Phone Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtNightPhone.isDisplayed(), "EveningTime Phone Field is not displayed");
		ReportExtn.Pass(objMethod, "EveningTime Phone Field is displayed");
		}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
	}
	
	
	
	//Other Phone Label Text
	@Test(priority=1)
	public void verifyOtherPhoneLabelAndField(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblOtherPhone.getText().trim(), uiAddALeadReferralPage.strOtherPhone, "Other Phone Label Text is not correct");
		ReportExtn.Pass(objMethod, "Other Phone Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtOtherPhone.isDisplayed(), "Other Phone Field is not displayed");
		ReportExtn.Pass(objMethod, "Other Phone Field is displayed");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

	}
	
	
	
	//City Label Text
	@Test(priority=1)
	public void verifyCityLabelAndField(Method objMethod)
	{
		try
		{

		Assert.assertEquals(uiAddALeadReferralPage.lblCity.getText().trim(), uiAddALeadReferralPage.strCity, "City Label Text is not correct");
		ReportExtn.Pass(objMethod, "City Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtCity.isDisplayed(), "City Field is not displayed");
		ReportExtn.Pass(objMethod, "City Field is displayed");
		}
		
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}

	}

	
	
	//State Label Text
	@Test(priority=1)
	public void verifyStateLabelAndField(Method objMethod)
	{
		try
		{

		Assert.assertEquals(uiAddALeadReferralPage.lblState.getText().trim(), uiAddALeadReferralPage.strState, "State Label Text is not correct");
		ReportExtn.Pass(objMethod, "State Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.ddlState.isDisplayed(), "State drop down is not displayed");
		ReportExtn.Pass(objMethod, "State drop down is displayed");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

	}
	
	
	
	//TCPA Label Text
	@Test(priority=1)
	public void verifyTCPALabelAndField(Method objMethod)
	{
		try
		{

		Assert.assertEquals(uiAddALeadReferralPage.lblTCPA.getText().trim(), uiAddALeadReferralPage.strTCPA, "TCPA Label Text is not correct");
		ReportExtn.Pass(objMethod, "State Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.ddlTCPA.isDisplayed(), "State drop down is not displayed");
		ReportExtn.Pass(objMethod, "State drop down is displayed");
		}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}

	}

	
	
	//Student Referring Label Text
	@Test(priority=1)
	public void verifyStudentReferrringIDLabelAndField(Method objMethod)
	{
		try
		{
		Assert.assertEquals(uiAddALeadReferralPage.lblSYStudentID.getText().trim(), uiAddALeadReferralPage.strSYStudentID, "StudentReferrring Student ID Label Text is not correct");
		ReportExtn.Pass(objMethod, "StudentReferrring Student ID Label Text is Visible");
		Assert.assertTrue(uiAddALeadReferralPage.txtStudentIDSR.isDisplayed(), "StudentReferrringID is not displayed");
		ReportExtn.Pass(objMethod, "StudentReferrring Student ID  is displayed");
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
		
	
}
