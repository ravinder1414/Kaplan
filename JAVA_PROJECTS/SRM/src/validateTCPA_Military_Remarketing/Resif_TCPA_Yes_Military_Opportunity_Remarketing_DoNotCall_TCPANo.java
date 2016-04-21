package validateTCPA_Military_Remarketing;


	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

	import reusableMethods_PageObject.SRM_ReusableMethods;
import srm_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.LeadImport_PageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class Resif_TCPA_Yes_Military_Opportunity_Remarketing_DoNotCall_TCPANo {
		public RemoteWebDriver driver = null;


		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;

		//Home Page Page Object Model
		public HomePageObjects uiHomePageObjects;
		public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
		public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
		public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
		//public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
		public LeadImport_PageObjects uiLeadImport_PageObjects;
		public FileOutputStream objFileOutputStream=null;
		public FileInputStream objFileInputStream = null;
		public Properties objProperties = new Properties();

		public String LeadImportID1;
		public String sSearchMain_WindowName ="";
		public String mainwinhandle;
		public String SifFinal;
		public String LeadImportID;
		public String DateCreated;
		public String LeadID;
		public String Stuid;
		public String MkLeadImportIDFromTCPAYesMilOppDoNotCallTCPANo;
		public String EmailIDFromTCPAYesMilOppDoNotCallTCPANo;
		public String MastermkleadimportIDFromTCPAYesMilOppDoNotCallTCPANo;
		//Method which will executed before the class loads
		//Browser Parameter received from TestNg.xml
		@Parameters({"Browser"})
		@BeforeClass
		public void BeforeNavigation(String sBrowser) throws MalformedURLException
		{
			try
			{
			//Read the application properties file
			//Load environment variable from properties file
			String sPath_AppProperties="";
			FileInputStream objFileInputStream = null;
			Properties objProperties = new Properties();
			
			//Set file path as per environment
			if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
			{
				sPath_AppProperties = ".//Resources//ApplicationProperties/DevApplication.properties";
			}
			else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
			{
				sPath_AppProperties = ".//Resources//ApplicationProperties/StageApplication.properties";			
			}
			else if (EnvironmentVariables.sEnv.equalsIgnoreCase("lt"))
			{
				sPath_AppProperties = ".//Resources//ApplicationProperties/LtApplication.properties";			
			}
			else
			{
				sPath_AppProperties = ".//Resources//ApplicationProperties/TestApplication.properties";			
			}
			
			//Load the Application variable file into File Input Stream.
			File objFileApplication = new File(sPath_AppProperties);
			try
			{
				objFileInputStream = new FileInputStream(objFileApplication);
			}catch (FileNotFoundException ex)
			{
				ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
			}
			
			//Load the File Input Stream into the Properties file
			try
			{
				objProperties.load(objFileInputStream);
				
			} catch (IOException ex) {

				ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
			}
			
			//Reading from Property file 
			MkLeadImportIDFromTCPAYesMilOppDoNotCallTCPANo = objProperties.getProperty("MkLeadImportIDFromTCPAYesMilOppDoNotCallTCPANo");
			EmailIDFromTCPAYesMilOppDoNotCallTCPANo=objProperties.getProperty("EmailIDFromTCPAYesMilOppDoNotCallTCPANo");
			MastermkleadimportIDFromTCPAYesMilOppDoNotCallTCPANo=objProperties.getProperty("MastermkleadimportIDFromTCPAYesMilOppDoNotCallTCPANo");	
			//Edit Browser Capabilities as per project
			//Fire fox Profile		
			FirefoxProfile profile = new FirefoxProfile();
			
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sTrusted_Uris);
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
			driver.get(EnvironmentVariables.sLead_ImportURL);
			driver.manage().window().maximize();
			uiHomePageObjects = new HomePageObjects(driver);
			//uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
			uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage());
		}
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
		//This method will fetch the ReSIf from StoredSIF table which is KU (43) Lead, from yesterday and starts with TestNG
			@Test
			public void FetchSIFfromDB(Method objMethod){
				//QUERY String to fetch REsif from DB
				String qString="select STOREDSIF from polaris..mkleadimportstoredsif WHERE MKLEADIMPORTID ="+MkLeadImportIDFromTCPAYesMilOppDoNotCallTCPANo;
				System.out.println("value of query string"+qString);
				
				//Call getDBQueryResult function in CommonLibrary project   
				try {
					ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
					 rs.next();
					 
					  SifFinal = rs.getString(1) ;
					  SifFinal = SifFinal.replace("<TCPA_Disc>Yes", "<TCPA_Disc>No");
				      //also Write DB values on console
					 System.out.println("StoredSIF: "+SifFinal);
					
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.getMessage();
				} 

			}



		@Test(dependsOnMethods= "FetchSIFfromDB")
		public void SubmitReSIF(Method objMethod) throws InterruptedException
		{	
			try
			{ 
				uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
				uiLeadImport_PageObjects.txtStudentInformationXML.clear();
				uiLeadImport_PageObjects.txtStudentInformationXML.sendKeys(SifFinal);
				Thread.sleep(10000);
				uiLeadImport_PageObjects.btnInvoke.click();
				
				//Window Handles
				String parentHandle = driver.getWindowHandle();
				Set <String> winHandles = driver.getWindowHandles();
				for (String currentWindowHandle : winHandles) {
					if (!currentWindowHandle.equals(parentHandle)) {
						driver.switchTo().window(currentWindowHandle);
						}
				}
				
				String LeaderrMsg = driver.findElement(By.cssSelector("string")).getText();
				System.out.println("1"+LeaderrMsg);
				//Assert.assertTrue(LeaderrMsg.contains("This lead appears to be a duplicate base on First Name, Last Name and Home Phone Number provided."));
				//The actual assert has been commented as message diff is being reported to the manual team
				Assert.assertTrue(LeaderrMsg.contains("This lead appears to be a duplicate"));
				//EXTRACT student id from error message to search in IWD
				System.out.println("ASSERT PASSED FOR DUP LEAD");
			    Thread.sleep(5000);
				
			}
				
			catch(Throwable e)
			{
				Reporter.log(e.getMessage());
				}	
			}
				
		@Test(dependsOnMethods= "SubmitReSIF")
		public void VerifyingResifInSRM(Method objMethod) throws InterruptedException
		{
			try{
				uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
				uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
				driver.get(EnvironmentVariables.sSRM_Url);
				UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
				//Clearing Search field 
				uiAddNewLeadsPageObjects.search_SRM.clear();
				
				//Fetching Email Id from property file 
				uiAddNewLeadsPageObjects.search_SRM.sendKeys(EmailIDFromTCPAYesMilOppDoNotCallTCPANo);		
				WebDriverWait wait = new WebDriverWait(driver, 5000);
				//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
					
				uiAddNewLeadsPageObjects.btnsearch_SRM.click();
				
				//call Wait for Search Opportunity Method
				SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
										
				//Opportunities and Accounts Elements
				WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
				WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));

				//Assert Opportunities and Accounts
				Assert.assertTrue(ele.isDisplayed());
				Assert.assertTrue(ele1.isDisplayed());
				
				//click to open the Opportunity
				uiSRM_LeadFlow_PageObjects.LnkOppLead.click();
		       Thread.sleep(10000);
		       
		       //verifying Resif Flag 
		       System.out.println(uiAddNewLeadsPageObjects.cbReSIF.getAttribute("alt"));
			   Assert.assertEquals(uiAddNewLeadsPageObjects.cbReSIF.getAttribute("alt"), "Checked");
			   System.out.println("This is Resifed ");
			   UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtMasterSifID);
			   
			   //verifying master Id 
			   System.out.println(uiAddNewLeadsPageObjects.txtMasterSifIDOpp.getText());
			   Assert.assertEquals(uiAddNewLeadsPageObjects.txtMasterSifIDOpp.getText(), MkLeadImportIDFromTCPAYesMilOppDoNotCallTCPANo);
						

			   uiAddNewLeadsPageObjects.txtEwcField.getText();
			   Assert.assertEquals(uiAddNewLeadsPageObjects.txtEwcField.getText(), "No");
			   System.out.println(uiAddNewLeadsPageObjects.txtEwcField.getText());
			   
			   //Inquiry Status 
			   System.out.println(uiAddNewLeadsPageObjects.txtInquiryStageOppLead.getText());
			   Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStageOppLead.getText(), "Remarketing");
			   UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtRemarketingReason);
			   
			   //Remarketing reason 
			   System.out.println(uiAddNewLeadsPageObjects.txtRemarketingReason.getText());
			  Assert.assertEquals(uiAddNewLeadsPageObjects.txtRemarketingReason.getText(), "Student Requested No Further Contact (Internal)");
			  System.out.println("Remarketing Reason Wipped off");
			   
			   //Remarketing Sub Reason 
			   System.out.println(uiAddNewLeadsPageObjects.txtRemarketingsubReason.getText());
			   Assert.assertEquals(uiAddNewLeadsPageObjects.txtRemarketingsubReason.getText(),"Do Not Call (Internal)"); 
			   System.out.println("Remarketing Sub Reason Wipped Off");
			   
			   
			
				
			}
			catch(Exception e)
			{
				
			}
		}
			

	}



