package validateIWDTasks;

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

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.DuplicateLeadCompletionPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.LeadImport_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class DuplicateLeadCompletion {
		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser cap
				public BrowserManagement objBrowserMgr = null;
				
				//Home Page Page Object Model
				public HomePageObjects uiHomePageObjects;
				public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
				public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
				public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
				public LeadImport_PageObjects uiLeadImport_PageObjects;
				public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";

				
				//Variables from Properties file
				String sFirstName1;
			    String sLastName1;
			    String sEmailAddress1;			     
			    public String Stuid;
			    public String MKLeadID;			     
				public String sMilitaryType;
				public String sSpouseMilitary;
				public String sTCPA;
				public String sHighestEducation;
				
				//Static variable
				String sRandStr = RandomStringUtils.randomAlphabetic(5);
				public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
				public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
				public String sEmailAddress = sFirstName + "IC@kap.com";
				public String sDayPhone = "9545151234";
				public String sZipCode = "30256";
				public String sSyStuID= "";
				public String sEmailID="";
				public String sErrorNumber="";
		        public static String S2;
		        public String MKLID;
		        public String SIF1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><sif><sifheader VendorID=\"61439\" SourceCode=\"248691\" SIFID=\"279\" SIFVersionID=\"2258\" VendorDate=\"04/22/2013\" AffiliateID=\"1\" /><sifdetail><Program>AASMA</Program><AreaStudy>HS</AreaStudy><FName>";
				public String SIF2="</FName><LName>";
				public String SIF3="</LName><Email>";
				public String SIF4="</Email><HPhone>9545154045</HPhone><WPhone>9545154046</WPhone><MPhone>9545154047</MPhone><Address>Address 1</Address><Address2>Address 2</Address2><City>Fort Lauderdale</City><State>Florida</State><Zip>33302</Zip><Country>United States</Country><USCitizen>YES</USCitizen><HighestEducation>GED</HighestEducation><GradYear>2008-2003</GradYear><Military>Yes</Military><Military2>Active</Military2><AGGLeadID>44D07075-3539-DD00-87B4-2FE57C3A99CC</AGGLeadID><TCPA_Disc>Yes</TCPA_Disc><syUserID>2328</syUserID><URL>http://www.degrees.info/forms/form.jsp</URL></sifdetail></sif>";
		        public String SifFinal=SIF1+sFirstName+SIF2+sLastName+SIF3+sEmailAddress+SIF4;
		        
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
					
					//Reading from Properties file
				
					sTCPA = objProperties.getProperty("sTCPADisclosure");
					sMilitaryType = objProperties.getProperty("sMilitaryType");
					sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
					sHighestEducation = objProperties.getProperty("sHighestEduction");
				
								
					//Edit Browser Capabilities as per project
					//Fire fox Profile		
					FirefoxProfile profile = new FirefoxProfile();
					
					//profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sSRM_Url);
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
					driver.get(EnvironmentVariables.sSRM_Url);
					driver.manage().window().maximize();
					uiHomePageObjects = new HomePageObjects(driver);			
				}
				
				

				
				@Test
				public void BrowseToAddDuplicateReferralLeadPage(Method objMethod) throws InterruptedException
				{
					uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
					uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
					uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
                    Thread.sleep(3000);
                    
                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
					
    				
					WebDriverWait wait = new WebDriverWait(driver, 1000);
					
					driver.switchTo().frame("ext-comp-1005");
					
					UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
					
					
					//Select Referral Radio Button
					uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral.click();
					
					Thread.sleep(10000);
					
					uiAddInquiry_Referral_Lead_Pageobjects.txtFirstName.sendKeys(sFirstName);
					uiAddInquiry_Referral_Lead_Pageobjects.txtLastName.sendKeys(sLastName);
					uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.sendKeys(sEmailAddress);
					
					sEmailAddress1 =uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.getAttribute("value");
					
					uiAddInquiry_Referral_Lead_Pageobjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
					uiAddInquiry_Referral_Lead_Pageobjects.txtZipCode.sendKeys(sZipCode);
					
					//TCPA Disclosure
					
					if(sTCPA.equalsIgnoreCase("yes"))
					{
						uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_Yes.click();					
					}
					else
					{
						uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_No.click();
					}
					
					
					//Spouse Military Status
					
					if(sSpouseMilitary.equalsIgnoreCase("yes"))
					{
						
						uiAddInquiry_Referral_Lead_Pageobjects.rbtnSpouse_Yes.click();
						
					}
					else
					{
						uiAddInquiry_Referral_Lead_Pageobjects.rbtnSpouse_No.click();
					}
					
					//Highest Level of Education
					
					Select ddlHightestEdution = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddHighestLevelEducation);
					ddlHightestEdution.selectByVisibleText(sHighestEducation);
					
					Thread.sleep(10000);
					
                    //Adding Lead Inquiry
					
					uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
					
					Thread.sleep(10000);
					
					UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);
					
										
					//verify Lead Success Message 
					
					Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
					Thread.sleep(1000);
		
			        //Clicking again on add lead inquiry button
					
					uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
					Thread.sleep(10000);
				
				    //Catching Duplicate lead inquiry message 
					String s=uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadDuplicate.getText();
					Thread.sleep(15000);
					
					//Catching Student ID from the Duplicate Lead Inquiry Message 
					
					 System.out.println(s.contains("Error Number: 1096 This lead appears to be a duplicate"));
					 Assert.assertTrue(s.contains("Error Number: 1096 This lead appears to be a duplicate"));
			
					 int bindex= s.indexOf("Student ID");
					 int eindex= s.indexOf("/");
					 String S1= s.substring(bindex, eindex);
					 System.out.println(S1);
					 String Stuid = S1.replace("Student ID ", "").trim();
					 System.out.println(Stuid);
					 S2=S1.replace("Student ID ", "");

				      
				    }

				@Test(dependsOnMethods={"BrowseToAddDuplicateReferralLeadPage"})
				public void VerifyLeadResubmissionInIWD(Method objMethod) throws InterruptedException
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
				     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(S2);
				     Thread.sleep(5000);
				     
				     //Finding the Captured ID from SRM
				     UiDuplicateLeadCompletion.FindCapturedSYID.click();
				     Thread.sleep(5000);
				     
				     //Counting the searched research list 
				     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
				     int i =  ele.size();
				     System.out.println(i);
					 Thread.sleep(10000);
					 
				     //clicking on CaPtured lead ID
				     UiDuplicateLeadCompletion.CapturedIDRecord.click();
				     Thread.sleep(20000);
				     
				     //Capturing LeadImportId
				     MKLID=UiDuplicateLeadCompletion.MkLeadImportID.getText();
				     System.out.println(MKLID);
				     
				}
				
				
				/*@Test(dependsOnMethods={"VerifyLeadResubmissionInIWD"})	
				public void LeadImportPage(Method objMethod) throws InterruptedException
				{try{
					
					uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
					DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
					
					//driver.get(EnvironmentVariables.sConnString);
				
					
					//uiLeadImport_PageObjects.txtStudentInformationXML.clear();
					//uiLeadImport_PageObjects.txtStudentInformationXML.sendKeys(SifFinal);
					//Thread.sleep(10000);
					//uiLeadImport_PageObjects.btnInvoke.click();
					
					//Window Handles
					String parentHandle = driver.getWindowHandle();
				
					Set <String> winHandles = driver.getWindowHandles();
					for (String currentWindowHandle : winHandles) {
						if (!currentWindowHandle.equals(parentHandle)) {
							
							driver.switchTo().window(currentWindowHandle);
							System.out.println(sEmailAddress);
						}
					}
														
					Thread.sleep(20000);
					try{
						MKLID=UiDuplicateLeadCompletion.MkLeadImportID.getText();}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
					
			
					String qString="select  emailaddress,mkleadid from polaris..mkleadimport where mkleadimportid="+MKLeadID;
					
					try {
						 ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
						
						 rs.next();
											 							 
						Assert.assertEquals(sEmailAddress, rs.getString(1));
						Assert.assertEquals(MKLeadID,rs.getString(2));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}catch (Exception e)
				{Reporter.log(e.getMessage());
					
				}
				}*/

		
	
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