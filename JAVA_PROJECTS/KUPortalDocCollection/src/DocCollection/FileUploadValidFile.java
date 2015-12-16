package DocCollection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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

public class FileUploadValidFile {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public HomePage uiHomePageObjects;		
			public LoginPage uiLoginPageObjects;
			public MyAccountDocumentsPage uiMyAccDocPageObjects;
			public FileUploadPage uiFileUploadPageObjects;
			public String sDocName= "";
						
			//variables
			public String mainwinhandle, mainwintitle,upFilewinhandle;
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
								
				//Navigate to My Accounts Documents
				uiHomePageObjects = new HomePage(driver);
				uiHomePageObjects.NavigateToMyAccDocs(driver);
				driver.findElement(By.xpath("//th/span[contains(text(),'Document Name')]")).click();
				sDocName = driver.findElement(By.xpath(".//*[@id='overdueDocumentsGrid']/tbody/tr[1]/td[4]/span")).getText().trim();
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
  @Test
  public void UploadValidFile() {
	  Reporter.log(sDocName);
	  Reporter.log("<p>"+upFilewinhandle);
	  Reporter.log("<p>"+mainwinhandle);
	  Reporter.log("<p>"+EnvironmentVariables.sFile_Validfile);
	  uiFileUploadPageObjects.btnBrowToSeFile.sendKeys(EnvironmentVariables.sFile_Validfile);
	  uiFileUploadPageObjects.btnUpload.click();
	  Assert.assertFalse(driver.findElement(By.id("imgDirectory")).isDisplayed(), "Upload Alert is displayed. Error uploading file");
	  
//	  WebDriverWait w= new WebDriverWait(driver, 90);
//		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("uploadButton")));
//				//Switch to main window
	  driver.switchTo().window(mainwinhandle);
	  //WAIT FOR THE DOCUMENT TO GET LOADED
	  try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Reporter.log(e.getMessage());
		}
	  
	  driver.findElement(By.xpath("//th/span[contains(text(),'Document Name')]")).click();
	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Reporter.log(e.getMessage());
		}
		//Assert that the uploaded document is not present in the OverDue List
	  	Assert.assertFalse(driver.findElement(By.xpath(".//*[@id='overdueDocumentsGrid']/tbody/tr[1]/td[4]/span")).getText().trim().equalsIgnoreCase(sDocName) , "Document is visible in OverDue list as it is not uploaded");
 }
  
  @AfterClass
  public void AfterNavigation(){
	  driver.switchTo().window(mainwinhandle);
	  uiHomePageObjects.lnkLogout.click();
	  if (driver !=null) {driver.quit();}
			  
  }
  }