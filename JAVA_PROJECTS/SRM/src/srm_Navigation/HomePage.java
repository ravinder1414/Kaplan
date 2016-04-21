package srm_Navigation;
//Import files
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;






import java.util.concurrent.TimeUnit;




import org.openqa.selenium.chrome.ChromeOptions;
//Selenium
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
//TestNG
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;










import srm_Variables.EnvironmentVariables;
//Page Object
import uiMap_Orion3_SRM.AddALeadReferralPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;

//Custom
import commonfunctions.*;

public class HomePage {
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	
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
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);
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
	
	
	
	
	//Test Method to VerifyHomeTabPresent on home page.
	@Test(priority=0)
	public void VerifyHomeTabPresent(Method objMethod)
	{	
		try
		{

		if(uiHomePageObjects.VerifyHomeTabPresent())
		{
			ReportExtn.Pass(objMethod, "Home Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Home Tab is not Visible");
		}
	}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}


	}

	//Test Method to VerifyAdmissionsTabPresent on home page.
	@Test(priority=0)
	public void VerifyAdmissionsTabPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyAdmissionsTabPresent())
		{
			ReportExtn.Pass(objMethod, "Admissions Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Admissions Tab is not Visible");
		}
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

	}	
	
	
	
	//Test Method to VerifyTicketsTabPresent on home page.
	@Test(priority=0)
	public void VerifyTicketsTabPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyTicketsTabPresent())
		{
			ReportExtn.Pass(objMethod, "Tickets Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Tickets Tab is not Visible");
		}
	}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}

	}
	
	
	
	
	
	//Test Method to VerifyAcademicsTabPresent on home page.
	@Test(priority=0)
	public void VerifyAcademicsTabPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyAcademicsTabPresent())
		{
			ReportExtn.Pass(objMethod, "Academics Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Academics Tab is not Visible");
		}
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
	
	
	
	
	//Test Method to VerifyFinanceTabPresent on home page.
	@Test(priority=0)
	public void VerifyFinanceTabPresent(Method objMethod)
	{	
		try
		{

		if(uiHomePageObjects.VerifyFinanceTabPresent())
		{
			ReportExtn.Pass(objMethod, "Finance Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Finance Tab is not Visible");
		}
	}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
		
	}
	
	
	
	//Test Method to VerifyFinancialAidTabPresent on home page.
	@Test(priority=0)
	public void VerifyFinancialAidTabPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyFinancialAidTabPresent())
		{
			ReportExtn.Pass(objMethod, "Finanical Aid Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Financial Aid Tab is not Visible");
		}
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
	
	
	
	//Test Method to VerifyOpsAdminTabPresent on home page.
	@Test(priority=0)
	public void VerifyOpsAdminTabPresent(Method objMethod)
	{	
		try
		{
		if(uiHomePageObjects.VerifyOpsAdminTabPresent())
		{
			ReportExtn.Pass(objMethod, "OpsAdmin Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "OpsAdmin Tab is not Visible");
		}
	}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}
	}
	
	
	
	//Test Method to VerifySystemAdminTabPresent on home page.
	@Test(priority=0)
	public void VerifySystemAdminTabPresent(Method objMethod)
	{		
		try
		{
		if(uiHomePageObjects.VerifySystemAdminTabPresent())
		{
			ReportExtn.Pass(objMethod, "System Admin Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "System Admin Tab is not Visible");
		}	
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}

	}
	
	
	
	//Test Method to VerifyTicketAdminTabPresent on home page.
	@Test(priority=0)
	public void VerifyTicketAdminTabPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyTicketAdminTabPresent())
		{
			ReportExtn.Pass(objMethod, "Ticket Admin Tab is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Ticket Admin Tab is not Visible");
		}
	}
	
	catch (Exception e)
												
	{
	Reporter.log(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e.getStackTrace());
	}

	}
	
	
	
	
	//Test Method to VerifyAddaLeadRefferalLinkPresent on home page.
	
	@Test(priority=0)
	public void VerifyAddaLeadRefferalLinkPresent(Method objMethod)
	{		
		try
		{

		if(uiHomePageObjects.VerifyAddaLeadReferalLinkPresent())
		{
			ReportExtn.Pass(objMethod, "Add a lead Refferal link is Visible");
		}
		else
		{
			ReportExtn.Fail(objMethod, "Add a lead Refferal link is not Visible");
		}		
		}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}	

	
	
	//Test Method to Verify AddaLeadRefferal page
	@Test(priority=1)
	public void VerifyAddaLeadReferralPage(Method objMethod)
	{
		try
		{
		//Click Add a lead Referral
		if(uiHomePageObjects.lnkAddaLeadReferral.isDisplayed())
		{			
			AddALeadReferralPageObjects uiAddALeadReferralPage = uiHomePageObjects.ClickAddaLeadReferralLink(driver);
			//Verify Add a lead Referral text
			UserExtension.IsElementPresent(driver, uiAddALeadReferralPage.txtFirstName);			
			if(uiAddALeadReferralPage.lblAddALeadReferral.getText().equals(uiAddALeadReferralPage.strAddALeadReferral))
			{
				ReportExtn.Pass(objMethod, "Add a lead Refferal text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strAddALeadReferral, uiAddALeadReferralPage.lblFirstName.getText(), "Add a lead Refferal text is not correct" );
			}
			
			//First Name Label Text
			if(uiAddALeadReferralPage.lblFirstName.getText().equals(uiAddALeadReferralPage.strFirstName))
			{
				ReportExtn.Pass(objMethod, "First Name Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strFirstName, uiAddALeadReferralPage.lblFirstName.getText(), "First Name Label Text is not correct" );
			}
			
			
			//Required Information Text
			if(uiAddALeadReferralPage.lblRequiredInfo.getText().equals(uiAddALeadReferralPage.strRequiredInfo))
			{
				ReportExtn.Pass(objMethod, "Required Information Text Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strRequiredInfo, uiAddALeadReferralPage.lblRequiredInfo.getText(), "Required Information Text is not correct" );
			}
			
			//Last Name Label Text
			if(uiAddALeadReferralPage.lblLastName.getText().equals(uiAddALeadReferralPage.strLastName))
			{
				ReportExtn.Pass(objMethod, "Last Name Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strLastName, uiAddALeadReferralPage.lblLastName.getText(), "Last Name Label Text is not correct" );
			}

			//EmailAddress Label Text
			if(uiAddALeadReferralPage.lblEmailAddress.getText().equals(uiAddALeadReferralPage.strEmailAddress))
			{
				ReportExtn.Pass(objMethod, "EmailAddress Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strEmailAddress, uiAddALeadReferralPage.lblEmailAddress.getText(), "EmailAddress Label Text is not correct" );
			}

			//DayTimePhone Label Text
			if(uiAddALeadReferralPage.lblDaytimePhone.getText().equals(uiAddALeadReferralPage.strDaytimePhone))
			{
				ReportExtn.Pass(objMethod, "DayTimePhone Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strDaytimePhone, uiAddALeadReferralPage.lblDaytimePhone.getText(), "DayTimePhone Label Text is not correct" );
			}
			
			//Evening Phone Label Text
			if(uiAddALeadReferralPage.lblEveningPhone.getText().equals(uiAddALeadReferralPage.strEveningPhone))
			{
				ReportExtn.Pass(objMethod, "Evening Phone Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strEveningPhone, uiAddALeadReferralPage.lblEveningPhone.getText(), "Evening Phone Label Text is not correct" );
			}
			
			//Other Phone Label Text
			if(uiAddALeadReferralPage.lblOtherPhone.getText().equals(uiAddALeadReferralPage.strOtherPhone))
			{
				ReportExtn.Pass(objMethod, "Other Phone Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strOtherPhone, uiAddALeadReferralPage.lblOtherPhone.getText(), "Other Phone Label Text is not correct" );
			}

			//City Label Text
			if(uiAddALeadReferralPage.lblCity.getText().equals(uiAddALeadReferralPage.strCity))
			{
				ReportExtn.Pass(objMethod, "City Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strCity, uiAddALeadReferralPage.lblCity.getText(), "City Label Text is not correct" );
			}
			
			//State Label Text
			if(uiAddALeadReferralPage.lblState.getText().equals(uiAddALeadReferralPage.strState))
			{
				ReportExtn.Pass(objMethod, "State Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strState, uiAddALeadReferralPage.lblState.getText(), "State Label Text is not correct" );
			}

			//TCPA Label Text
			if(uiAddALeadReferralPage.lblTCPA.getText().equals(uiAddALeadReferralPage.strTCPA))
			{
				ReportExtn.Pass(objMethod, "TCPA Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strTCPA, uiAddALeadReferralPage.lblTCPA.getText(), "TCPA Label Text is not correct" );
			}

			//Sy Student Label Text
			if(uiAddALeadReferralPage.lblSYStudentID.getText().equals(uiAddALeadReferralPage.strSYStudentID))
			{
				ReportExtn.Pass(objMethod, "Sy Student Label Text is Visible");
			}
			else
			{
				ReportExtn.Fail(objMethod, uiAddALeadReferralPage.strSYStudentID, uiAddALeadReferralPage.lblSYStudentID.getText(), "Sy Student Label Text is not correct" );
			}
			
		
			
		}
}
		
		catch (Exception e)
													
		{
		Reporter.log(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
		}
	}
	
}