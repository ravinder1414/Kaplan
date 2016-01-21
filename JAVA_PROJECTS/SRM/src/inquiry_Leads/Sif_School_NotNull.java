package inquiry_Leads;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

	import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import srm_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.DuplicateLeadCompletionPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.LeadImport_PageObjects;
//import uiMap_Orion3_SRM.SRMLead_CreationPageObject;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		public class Sif_School_NotNull {
			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//Home Page Page Object Model
					public HomePageObjects uiHomePageObjects;
					public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
					public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
					public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
					public LeadImport_PageObjects uiLeadImport_PageObjects;
					//public SRMLead_CreationPageObject uiSRMLead_CreationPageObject;
					public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
					
					
					//Variables from Properties file
					 String sFirstName1;
				     String sLastName1;
				     String sEmailAddress1;
					 String sCurrentSchoolID;
				     public String LeadImportID1;
				     public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";
					 public String sSyStuID= "";
					 public String sEmailID="";
					 public String sSearchMain_WindowName ="";
					 public String mainwinhandle;
					//Static variable
					String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
					public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
					public String sEmailAddress = sFirstName + "IC@kap.com";
					public String sDayPhone = "9545151234";
					public String sZipCode = "30256";
					public String sProgram = "BSHS";
					public String sAreaStudy = "HS";
					public String SIF1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><sif><sifheader VendorID=\"61439\" SourceCode=\"248691\" SIFID=\"279\" SIFVersionID=\"2258\" VendorDate=\"04/22/2013\" AffiliateID=\"1\" /><sifdetail><Program>";
					// AASMA
					public String SIFn1="</Program><AreaStudy>";
					//HS
					public String SIfn2= "</AreaStudy><FName>";
					public String SIF2="</FName><LName>";
					public String SIF3="</LName><Email>";
					public String SIF4="</Email><HPhone>9545154045</HPhone><WPhone>9545154046</WPhone><MPhone>9545154047</MPhone><Address>Address 1</Address><Address2>Address 2</Address2><City>Fort Lauderdale</City><State>Florida</State><Zip>33302</Zip><Country>United States</Country><USCitizen>YES</USCitizen><HighestEducation>GED</HighestEducation><GradYear>2008-2003</GradYear><Military>Yes</Military><Military2>Active</Military2><AGGLeadID>44D07075-3539-DD00-87B4-2FE57C3A99CC</AGGLeadID><TCPA_Disc>Yes</TCPA_Disc><syUserID>2328</syUserID><URL>http://www.degrees.info/forms/form.jsp</URL></sifdetail></sif>";
					public String SifFinal=SIF1+sProgram.toLowerCase()+SIFn1+sAreaStudy.toUpperCase()+SIfn2+sFirstName+SIF2+sLastName+SIF3+sEmailAddress+SIF4;
					
							
							
					
					
					
					//Method which will executed before the class loads
					//Browser Parameter received from TestNg.xml
					@Parameters({"Browser"})
					@BeforeClass
					public void BeforeNavigation(String sBrowser) throws MalformedURLException
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
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
						}
						catch(Exception ex)
						{	
							ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
						}
						driver.get(EnvironmentVariables.sLead_ImportURL);
						driver.manage().window().maximize();
						uiHomePageObjects = new HomePageObjects(driver);
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
					}
					
					

					
					@Test
					public void LeadImportPage(Method objMethod) throws InterruptedException
					{try{
						
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
								System.out.println(sEmailAddress);
								System.out.println(SifFinal);
								
							}
						}
															
						Thread.sleep(20000);
						try{
					LeadImportID1 = uiLeadImport_PageObjects.txtLeadImport.getText();
					System.out.println(LeadImportID1);
						}
						
						catch(Exception e){
							System.out.println(e.getMessage());
						}				
						
						
					}catch (Exception e)
					{Reporter.log(e.getMessage());
						
					}
					}
					
					@Test(dependsOnMethods={"LeadImportPage"})
					public void CopyingsyStudentID_SRM(Method objMethod) throws InterruptedException
					{
						try{
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							driver.get(EnvironmentVariables.sSRM_Url);
							UserExtension.IsElementPresent(driver, uiReusableMethods_PageObjects.lnkDropDown);
							Thread.sleep(20000);
							
							//uiReusableMethods_PageObjects.lnkDropDown.click();
							//Thread.sleep(3000);
							//uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							//Thread.sleep(1000);
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress);
							WebDriverWait wait = new WebDriverWait(driver, 5000);
							WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
								
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
							SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
							WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
							Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
							
							Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress), "Email searched successfully");
									
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						
						//click on inquiry Lead
						driver.findElementByXPath("html/body/div[1]/div[2]/table/tbody/tr/td/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[2]/table/tbody/tr[2]/th/a").click();
						Thread.sleep(10000);
						
						
						//Fetch Systudent ID
						sSyStuID =driver.findElementByXPath("//td[text()='SyStudentID']/following-sibling::td[1]/div").getText();
						Reporter.log(sSyStuID);
						System.out.println(sSyStuID);
						
						//Fetching Email ID 
						sEmailID = driver.findElementByXPath(".//*[@id='lea11_ileinner']/a").getText();
					    System.out.println(sEmailID);
						
					}
					
					@Test(dependsOnMethods={"CopyingsyStudentID_SRM"})
					public void SearchingCapturedStudentID_Genesys(Method objMethod) throws InterruptedException
					{

						DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						//driver.get(EnvironmentVariables.sIWD_Url);
						driver.get(IWD_Url);
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						
						 //Login Window And its Credentials of IWD Genesys 
						  driver.findElement(By.id("loginForm:username")).sendKeys("chliang");
						  driver.findElement(By.id("loginForm:password")).sendKeys("test");	
						  driver.findElement(By.id("loginForm:submit")).click();
					
						 //Clicking on Global Task list link
						 UiDuplicateLeadCompletion.GlobalTaskList.click();	
						 Thread.sleep(10000);
						 
						 //Clicking on Kaplan TEST Link
					     UiDuplicateLeadCompletion .kaplanTESTLink.click();
					     Thread.sleep(10000);
					     
					     //Searching SyStudent ID in Captured ID field
					     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(sSyStuID);
					     Thread.sleep(5000);
					     
					     //Finding the Captured ID from SRM
					     UiDuplicateLeadCompletion.FindCapturedSYID.click();
					     Thread.sleep(10000);
					     
					     //Counting the searched research list 
					     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
					     int i =  ele.size();
					     System.out.println(i);
						 Thread.sleep(10000);
						 
					     //clicking on CaPtured lead ID
					     UiDuplicateLeadCompletion.CapturedIDRecord.click();
					     Thread.sleep(20000);
					     UiDuplicateLeadCompletion.VerifyCurrentSchoolID.getText().trim();
					     System.out.println(UiDuplicateLeadCompletion.VerifyCurrentSchoolID.getText().trim());

                        // Verifying Current School ID Value is not NULL || 0
					     
					    if(UiDuplicateLeadCompletion.VerifyCurrentSchoolID.getText() == "" ||UiDuplicateLeadCompletion.VerifyCurrentSchoolID.getText() == "0" )
					     {
					    	   
			              Assert.assertTrue(false ,"Current School ID is NULL");		    
					     }
					    else{
					      Assert.assertTrue( true,"Current School ID is NOT A  NULL");
					    	    
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
						
		}	
		

		

