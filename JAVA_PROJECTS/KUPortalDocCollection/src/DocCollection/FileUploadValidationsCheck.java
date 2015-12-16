package DocCollection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_DocCollection.FileUploadPage;
import uiMap_DocCollection.LoginPage;
import uiMap_DocCollection.HomePage;
import uiMap_DocCollection.MyAccountDocumentsPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class FileUploadValidationsCheck {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public HomePage uiHomePageObjects;		
			public LoginPage uiLoginPageObjects;
			public MyAccountDocumentsPage uiMyAccDocPageObjects;
			public FileUploadPage uiFileUploadPageObjects;
						
			//variables
			public String mainwinhandle, mainwintitle;
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
				//instantiating page objects
				uiLoginPageObjects = new LoginPage(driver)	;
				uiLoginPageObjects.LoginToKUPortal(EnvironmentVariables.sUserName, EnvironmentVariables.sPassword); 
								
				//getting window handle for main window
				mainwinhandle = driver.getWindowHandle();
				mainwintitle= driver.getTitle();
				Reporter.log(mainwinhandle+"\n"+mainwintitle);
								
				uiHomePageObjects = new HomePage(driver);
				uiHomePageObjects.NavigateToMyAccDocs(driver);
				uiMyAccDocPageObjects = new MyAccountDocumentsPage(driver);
				uiMyAccDocPageObjects.icouploadFile.click();
				uiFileUploadPageObjects= new FileUploadPage(driver);
				
				//switch to File upload window				
				Set<String> handles =driver.getWindowHandles(); handles.remove(mainwinhandle);
				String upFilewinhandle= (String) handles.iterator().next();
				driver.switchTo().window(upFilewinhandle);
				WebDriverWait w= new WebDriverWait(driver, 30);
				w.until(ExpectedConditions.visibilityOf(uiFileUploadPageObjects.btnUpload));
			}
	
  //File Size validation check
  @Test (priority=1)
  public void ExceedFilesizeCheck() {
	  uiFileUploadPageObjects.btnBrowToSeFile.sendKeys(EnvironmentVariables.sFile_Exceedsize);
	  uiFileUploadPageObjects.btnUpload.click();
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.visibilityOf(uiFileUploadPageObjects.imgAlertES));
	  Assert.assertTrue(uiFileUploadPageObjects.imgAlertES.isDisplayed(), "Imgage Alert is not displayed");
	  Assert.assertTrue(uiFileUploadPageObjects.msgSizeExceed.isDisplayed(), "Exceed File Size message is not displayed");
	  Assert.assertEquals(uiFileUploadPageObjects.msgSizeExceed.getText().trim(), uiFileUploadPageObjects.smsgSizeExceed);
	  
	    }
  
//Invalid File Type validation check
  @Test(priority=2)
  public void InvalidFileTypeCheck() {
	  driver.get(driver.getCurrentUrl());
	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  uiFileUploadPageObjects.btnBrowToSeFile.sendKeys(EnvironmentVariables.sFile_Invalidtype);
	  uiFileUploadPageObjects.btnUpload.click();
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.visibilityOf(uiFileUploadPageObjects.imgAlertIFT));
	  Assert.assertTrue(uiFileUploadPageObjects.imgAlertIFT.isDisplayed(), "Imgage Alert is not displayed");
	  Assert.assertTrue(uiFileUploadPageObjects.msgInvalidFiletype.isDisplayed(), "Invalid File Type message is not displayed");
	  Assert.assertEquals(uiFileUploadPageObjects.msgInvalidFiletype.getText().trim(), uiFileUploadPageObjects.smsgInvalidFileType);
	  
	    }
  
  
  @AfterClass
  public void AfterNavigation(){
	  uiFileUploadPageObjects.btnClose.click();
	  driver.switchTo().window(mainwinhandle);
	  uiHomePageObjects.lnkLogout.click();
	  if (driver !=null) {driver.quit();}
			  
  }
  }