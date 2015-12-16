package DocCollection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_DocCollection.LoginPage;
import uiMap_DocCollection.HomePage;
import uiMap_DocCollection.MyAccountDocumentsPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class MyAccountDocuments {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public HomePage uiHomePageObjects;		
			public LoginPage uiLoginPageObjects;
			public MyAccountDocumentsPage uiMyAccDocPageObjects;
						
			//Method which will executed before the class loads
			//Browser Parameter received from TestNg.xml
			@Parameters({"Browser"})
			@BeforeClass
			public void BeforeNavigation(String sBrowser) throws MalformedURLException
			{
				
				//Edit Browser Capabilities as per project
				//Fire fox Profile		
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_Kuportal);
				
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
					Reporter.log("Unable to create the Remotedriver" +  ex.getMessage());		
				}
				driver.get(EnvironmentVariables.sUrl_Kuportal);
				driver.manage().window().maximize();
				uiLoginPageObjects = new LoginPage(driver)	;
				uiLoginPageObjects.LoginToKUPortal(EnvironmentVariables.sUserName, EnvironmentVariables.sPassword);
				uiHomePageObjects = new HomePage(driver);
				uiHomePageObjects.NavigateToMyAccDocs(driver);
				uiMyAccDocPageObjects = new MyAccountDocumentsPage(driver);
						
			}
	//Document Tracking Heading	
  @Test
  public void VerifyDocTrackingHeading() {
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocTracking.isDisplayed(),"Document Tracking heading is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocTracking.getText().equalsIgnoreCase(uiMyAccDocPageObjects.sdocTracking), "Document Tracking Heading not as expected");
  }
  //Document History Heading
  @Test
  public void VerifyDocHistoryHeading() {
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocHistory.isDisplayed(),"Document History heading is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocHistory.getText().equalsIgnoreCase(uiMyAccDocPageObjects.sdocHistory), "Document History Heading not as expected");
  }
  //Live Help
  @Test
  public void VerifyLiveHelp() {
	  Assert.assertTrue(uiMyAccDocPageObjects.lblLiveHelp.isDisplayed(),"Live Help is not displayed");
  }
  
  //Document Currently Due
  @Test
  public void VerifyDocCurrDue() {
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocCurrDue.isDisplayed(), "Document Currently Due heading not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.lblDocCurrDue.getText().equalsIgnoreCase(uiMyAccDocPageObjects.sdocCurrDue), "Documents Curr Due heading is not as expected");
  }
  
  //Document Currently Due Column heading
  @Test
  public void VerifyDocCurrDueTableColumns() {
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngFile.isDisplayed(), "File Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngUpload.isDisplayed(), "Upload Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngFaxCoverSheet.isDisplayed(), "Fax Cover Sheet Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngDocumentName.isDisplayed(), "Document Name Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngStatus.isDisplayed(), "Status Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngDateDue.isDisplayed(), "Date Due Column is not displayed");
	  Assert.assertTrue(uiMyAccDocPageObjects.colhdngDateRequested.isDisplayed(), "Date Requested Column is not displayed");
  }
	  
//File Upload Icon
  @Test
  public void VerifyFileUploadIcon() {
	  Assert.assertTrue(uiMyAccDocPageObjects.icouploadFile.isDisplayed(), "File Upload Icon is not displayed");
}
  
//Fax Cover Sheet Icon
  @Test
  public void VerifyFaxCoverSheetIcon() {
	  Assert.assertTrue(uiMyAccDocPageObjects.icouploadFile.isDisplayed(), "File Cover Sheet Icon is not displayed");
}
  
  @AfterClass
  public void AfterNavigation(){
	  uiHomePageObjects.lnkLogout.click();
	  if (driver !=null) {driver.quit();}
			  
  }
  }