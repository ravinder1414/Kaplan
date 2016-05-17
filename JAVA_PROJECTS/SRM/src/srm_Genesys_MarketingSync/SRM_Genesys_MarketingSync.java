package srm_Genesys_MarketingSync;



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
import uiMap_Orion3_SRM.SRM_Genesys_MarketingPageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

			public class SRM_Genesys_MarketingSync {
				
						//Remote Web driver for remote execution
						public RemoteWebDriver driver = null;
						
						//BrowseManagement to set the browser capabilities
						public BrowserManagement objBrowserMgr = null;
						
						//Home Page Page Object Model
						public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
						public HomePageObjects uiHomePageObjects;
						public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
						public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
						public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
						public LeadImport_PageObjects uiLeadImport_PageObjects;
						public SRM_Genesys_MarketingPageObjects uiSRM_Genesys_MarketingPageObjects;
						public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
						public String LeadImportID1;
										
						public String sSearchMain_WindowName ="";
						
						public String mainwinhandle;
						int upchannelid;
						
						
						//Variables from Properties file
						public String sFirstName1;
						 public String sLastName1;
					     public String sEmailAddress1;
					     public String mkvendorLeaddescription;
					     public String marketingChanneltext;
					     public  String mkleadchannelID;
					     public String txtMarketingchannelGOK;
					     
						
						
						//Static variable
						String sRandStr = RandomStringUtils.randomAlphabetic(5);
						public String sFirstName = "TestNGFNSRMGM_" + sRandStr;
						public String sLastName = "TestNGLNSRMGM_" + sRandStr;			
						public String sEmailAddress = sFirstName + "IC@kap.com";
						public String sDayPhone = "9545151234";
						public String sZipCode = "30256";
						//public String SIF1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><sif><sifheader VendorID=\"61439\" SourceCode=\"248691\" SIFID=\"279\" SIFVersionID=\"2258\" VendorDate=\"04/22/2013\" AffiliateID=\"1\" /><sifdetail><Program>AASMA</Program><AreaStudy>HS</AreaStudy><FName>";
						public String SIF1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><sif><sifheader VendorID=\"60791\" SourceCode=\"305850\" SIFID=\"279\" SIFVersionID=\"2258\" VendorDate=\"04/22/2013\" AffiliateID=\"1\" /><sifdetail><Program>AASMA</Program><AreaStudy>HS</AreaStudy><FName>";
						public String SIF2="</FName><LName>";
						public String SIF3="</LName><Email>";
						public String SIF4="</Email><HPhone>9545154045</HPhone><WPhone>9545154046</WPhone><MPhone>9545154047</MPhone><Address>Address 1</Address><Address2>Address 2</Address2><City>Fort Lauderdale</City><State>Florida</State><Zip>33302</Zip><Country>United States</Country><USCitizen>YES</USCitizen><HighestEducation>GED</HighestEducation><GradYear>2008-2003</GradYear><Military>Yes</Military><Military2>Active</Military2><AGGLeadID>44D07075-3539-DD00-87B4-2FE57C3A99CC</AGGLeadID><TCPA_Disc>Yes</TCPA_Disc><syUserID>2328</syUserID><URL>http://www.degrees.info/forms/form.jsp</URL></sifdetail></sif>";
						public String SifFinal=SIF1+sFirstName+SIF2+sLastName+SIF3+sEmailAddress+SIF4;
						public String smkVendorID="60791";
						public String sStudentid;
						public String ChannelDesc_update;
						public String ChannelID_update;
						
						
						
						
								
								
						
						
						
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
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
							uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						}
						
						@AfterClass
						public void AfterNavigation()
						
							{try{
								
								uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
								uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
								uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
								uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
								driver.get(EnvironmentVariables.sSRM_Url);
								Thread.sleep(10000);
								
								if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
								{



								}
								else
								{
								uiReusableMethods_PageObjects.lnkDropDown.click();
								Thread.sleep(20000);
								uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
								}
								

								Thread.sleep(20000);
								UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
								
								uiAddNewLeadsPageObjects.search_SRM.clear();
								uiAddNewLeadsPageObjects.search_SRM.sendKeys(smkVendorID);
								WebDriverWait wait = new WebDriverWait(driver, 5000);
									
								uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
								Thread.sleep(10000);
								
								uiSRM_Genesys_MarketingPageObjects.lnkAccountName.click();
								Thread.sleep(10000);
								
								
								uiSRM_Genesys_MarketingPageObjects.btnEditSRM.click();
								
								
								
								
								uiSRM_Genesys_MarketingPageObjects.txtmarketingChannelLookup.click();
								
								Thread.sleep(10000);
								
								driver.switchTo().window("lookup");
								
								driver.switchTo().frame("searchFrame");
								
								
								UserExtension.IsElementPresent(driver, uiSRM_Genesys_MarketingPageObjects.txtLookupSearch);
								
								
								uiSRM_Genesys_MarketingPageObjects.txtLookupSearch.clear();
								uiSRM_Genesys_MarketingPageObjects.txtLookupSearch.sendKeys(mkvendorLeaddescription);
								
								uiSRM_Genesys_MarketingPageObjects.btnGo.click();
								
								System.out.println(mkvendorLeaddescription);

								
								Thread.sleep(20000);
								
								driver.switchTo().defaultContent();
								
								driver.switchTo().frame("resultsFrame");
								
								
								UserExtension.IsElementPresent(driver, uiSRM_Genesys_MarketingPageObjects.lnkEmailResultSRM);
								uiSRM_Genesys_MarketingPageObjects.lnkEmailResultSRM.click();
								
								
								driver.switchTo().window("");
								Thread.sleep(20000);
								
								uiSRM_Genesys_MarketingPageObjects.btnSaveSRM.click();
								
						
							
							
							//Quit the test after test class execution
							if(driver != null)
							{
								driver.quit();			
							}
						}
							catch(Exception e){Reporter.log(e.getMessage());
							}
							
						}
						

						
						@Test 
						public void Search_mkMarketingchannelid(Method objMethod) throws InterruptedException
						{try{
							uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
							uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
							
																
							Thread.sleep(20000);
					
					
							String qStringmarketingchannelid="Select A.mkmarketingchannelid,B.Description  From Orion.dbo.mkVendor as A inner join orion..mkmarketingchannels as B on A.mkmarketingchannelid = B.mkmarketingchannelid Where A.mkVendorID="+smkVendorID;
							
							try {
								 ResultSet rs = QueryDB.getDBQueryResult(qStringmarketingchannelid, EnvironmentVariables.sConnString);
								 rs.next();
								 
								 System.out.println(rs.getString(1));
								 
								 System.out.println(rs.getString(2));
								 
								  mkleadchannelID=rs.getString(1);
								 // upchannelid = Integer.parseInt(mkleadchannelID)+1;
								 // System.out.println(upchannelid);
								  mkvendorLeaddescription=rs.getString(2);
								 							 
								//Assert.assertEquals(sEmailAddress, rs.getString(1));	 
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								e.getMessage();
							} 
							
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						
			}
					
						@Test(dependsOnMethods={"Search_mkMarketingchannelid"}) 
						public void FetchDBmkChannelUpdate(Method objMethod) throws InterruptedException
						{try{
							uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
							uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);			
																
							
					
							String qStringmarketingchannelid2="select top 1 description, mkmarketingchannelid from orion..mkmarketingchannels where mkmarketingchannelid<>"+mkleadchannelID+"and active=1";
							
							try {
								 ResultSet rs = QueryDB.getDBQueryResult(qStringmarketingchannelid2, EnvironmentVariables.sConnString);
								 rs.next();
								 
								 
								 							 					 
								  ChannelDesc_update=rs.getString(1);
								  ChannelID_update=rs.getString(2);
								  System.out.println(ChannelDesc_update);
								 
								 System.out.println("channel desc update: "+ChannelDesc_update);
								 
								 							 
								//Assert.assertEquals(sEmailAddress, rs.getString(1));	 
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								e.getMessage();
							} 
							
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						
			}

					@Test(dependsOnMethods={"FetchDBmkChannelUpdate"})
						
						public void SearchVendoridSRM(Method objMethod) throws InterruptedException
						
						
						{try{
							
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							driver.get(EnvironmentVariables.sSRM_Url);
							Thread.sleep(10000);
							
							if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
							{



							}
							else
							{
							uiReusableMethods_PageObjects.lnkDropDown.click();
							Thread.sleep(20000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							}

							Thread.sleep(20000);
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
							
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(smkVendorID);
							WebDriverWait wait = new WebDriverWait(driver, 5000);
								
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
						
							Thread.sleep(10000);
							
							uiSRM_Genesys_MarketingPageObjects.lnkAccountName.click();
							Thread.sleep(10000);
							
							String marketingChanneltext=uiSRM_Genesys_MarketingPageObjects.txtMarketingChannelFix.getText().trim();
							Thread.sleep(10000);
							Assert.assertEquals(mkvendorLeaddescription, marketingChanneltext);
						
							}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						}
						
						 
						@Test(dependsOnMethods={"SearchVendoridSRM"})
						
						public void CreateLeadImport(Method objMethod) throws InterruptedException
						{try{
							 System.out.println("in crateLeadimport");
							uiLeadImport_PageObjects = new LeadImport_PageObjects(driver);
							
							driver.get(EnvironmentVariables.sLead_ImportURL);
							
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
								}
							}
							
						
																
							Thread.sleep(20000);
							try{
						LeadImportID1 = uiLeadImport_PageObjects.txtLeadImport.getText();
						
						Thread.sleep(20000);
						driver.switchTo().window("");}
							catch(Exception e){
								System.out.println(e.getMessage());
							}
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
						System.out.println(e.getMessage());	
						}
						}
						
						
			
				
						

						
			

			@Test(dependsOnMethods={"CreateLeadImport"})
		
			public void SearchStudentidSRM(Method objMethod) throws InterruptedException
			
			
			{try{
				uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
				uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
				uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
				uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
				driver.get(EnvironmentVariables.sSRM_Url);
				
				if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
				{



				}
				else
				{
				uiReusableMethods_PageObjects.lnkDropDown.click();
				Thread.sleep(20000);
				uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
				}
				uiAddNewLeadsPageObjects.search_SRM.clear();
				uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress);
				WebDriverWait wait = new WebDriverWait(driver, 5000);
					
				uiAddNewLeadsPageObjects.btnsearch_SRM.click();
				
				SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
				
				Thread.sleep(20000);
				
				uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
				
				sStudentid=uiSRM_Genesys_MarketingPageObjects.txtSystudentid.getText();
				
				System.out.println(sStudentid);
				
			
				
			
			}catch (Exception e)
			{Reporter.log(e.getMessage());
				
			}
			}
			 
			 @Test(dependsOnMethods={"SearchStudentidSRM"})
			
				public void SearchStudent_IWD(Method objMethod) throws InterruptedException
				{
					try
					{DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					driver.get(EnvironmentVariables.sIWD_URL);
					
					
					 //Login Window And its Credentials of IWD Genesys 
					  driver.findElement(By.id("loginForm:username")).clear();
					  driver.findElement(By.id("loginForm:username")).sendKeys(EnvironmentVariables.sIWD_UName);
					  driver.findElement(By.id("loginForm:password")).clear();
					  driver.findElement(By.id("loginForm:password")).sendKeys(EnvironmentVariables.sIWD_PWD);	
					  driver.findElement(By.id("loginForm:submit")).click();
				
					  Thread.sleep(30000);
					 //Clicking on Global Task l ist link
					 UiDuplicateLeadCompletion.GlobalTaskList.click();
					 UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion .kaplanTESTLink);
				
					 Thread.sleep(30000);
					   
					 //Clicking on Kaplan TEST Link
				     UiDuplicateLeadCompletion .kaplanTESTLink.click();
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CaptureSYStudentID);
				     Thread.sleep(20000);
				     
				     //Searching SyStudent ID in Captured ID field
				     UiDuplicateLeadCompletion.CaptureSYStudentID.clear();
				     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(sStudentid);
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.FindCapturedSYID);
				     Thread.sleep(20000);
				     
				     //Finding the Captured ID from SRM
				     UiDuplicateLeadCompletion.FindCapturedSYID.click();
				     Thread.sleep(30000);
				     UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]")));
				     Thread.sleep(10000);
				     
				     //Counting the searched research list 
				     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
				     int i =  ele.size();
				     System.out.println(i);
				     Assert.assertTrue(i==1, "1 tasks in IWD");
				     
				     
				     //clicking on CaPtured lead ID
				     //UiDuplicateLeadCompletion.CapturedIDRecord.click();
				     Thread.sleep(10000);
				     
				    
				     //clicking on CaPtured lead ID
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CapturedIDRecord);
				     UiDuplicateLeadCompletion.CapturedIDRecord.click();
				    
				     Thread.sleep(10000);
				     System.out.println(UiDuplicateLeadCompletion.VerifymkLeadChannelID.getText());
				    
				     //Verifying lead record data in IWD 
				     Assert.assertEquals(mkleadchannelID, UiDuplicateLeadCompletion.VerifymkLeadChannelID.getText().trim());
				     
				     Thread.sleep(20000);
				     
				     if(!UiDuplicateLeadCompletion.txtStatusCompleted.getText().equalsIgnoreCase("Completed"))
						{

				    	 UiDuplicateLeadCompletion.btnCancelGenesys.click();
				    	 UiDuplicateLeadCompletion.btnConfirmYesGenesys.click();
				    	 Thread.sleep(20000);

						}
						
				     
				   
				    //logging out IWD 
				     driver.findElement(By.xpath("//a[@class='logout']")).click();
				     
					}
					catch(Exception e){Reporter.log(e.getMessage());}	
						//Thread.sleep(20000);
				     
				  
					
				}
			
				
			
			
			@Test(dependsOnMethods={"SearchStudent_IWD"})
			public void ChangeVendorNameSRM(Method objMethod) throws InterruptedException
			
			
			{try{
				
				uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
				uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
				uiSRM_Genesys_MarketingPageObjects =new SRM_Genesys_MarketingPageObjects(driver);
				uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
				driver.get(EnvironmentVariables.sSRM_Url);
				Thread.sleep(10000);
				
				if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
				{



				}
				else
				{
				uiReusableMethods_PageObjects.lnkDropDown.click();
				Thread.sleep(20000);
				uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
				}
				


				Thread.sleep(20000);
				UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
				
				uiAddNewLeadsPageObjects.search_SRM.clear();
				uiAddNewLeadsPageObjects.search_SRM.sendKeys(smkVendorID);
				WebDriverWait wait = new WebDriverWait(driver, 5000);
					
				uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			
				Thread.sleep(10000);
				
				uiSRM_Genesys_MarketingPageObjects.lnkAccountName.click();
				Thread.sleep(10000);
				
				
				uiSRM_Genesys_MarketingPageObjects.btnEditSRM.click();
				
				
				
				
				uiSRM_Genesys_MarketingPageObjects.txtmarketingChannelLookup.click();
				
				Thread.sleep(10000);
				
				driver.switchTo().window("lookup");
				
				driver.switchTo().frame("searchFrame");
				
				
				UserExtension.IsElementPresent(driver, uiSRM_Genesys_MarketingPageObjects.txtLookupSearch);
				
				
				uiSRM_Genesys_MarketingPageObjects.txtLookupSearch.clear();
				uiSRM_Genesys_MarketingPageObjects.txtLookupSearch.sendKeys(ChannelDesc_update);
				uiSRM_Genesys_MarketingPageObjects.btnGo.click();
				
				System.out.println(ChannelDesc_update);

				
				Thread.sleep(20000);
				
				driver.switchTo().defaultContent();
				
				driver.switchTo().frame("resultsFrame");
				
				
				UserExtension.IsElementPresent(driver, uiSRM_Genesys_MarketingPageObjects.lnkEmailResultSRM);
				uiSRM_Genesys_MarketingPageObjects.lnkEmailResultSRM.click();
				
				Thread.sleep(10000);
				driver.switchTo().window("");
				Thread.sleep(10000);
				
				uiSRM_Genesys_MarketingPageObjects.btnSaveSRM.click();
				
				uiAddNewLeadsPageObjects.search_SRM.clear();
				uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress);
				
				
				UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.btnsearch_SRM);
				
				
				Thread.sleep(20000);
					
				uiAddNewLeadsPageObjects.btnsearch_SRM.click();
				
				
				
				Thread.sleep(20000);
				
				uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
				
				uiSRM_Genesys_MarketingPageObjects.btnEditSRM.click();
				
				uiSRM_Genesys_MarketingPageObjects.txtPrimaryPhoneNumber.clear();
				
				uiSRM_Genesys_MarketingPageObjects.txtPrimaryPhoneNumber.sendKeys("9555565447");
				Thread.sleep(20000);
				
				UserExtension.IsElementPresent(driver, uiSRM_Genesys_MarketingPageObjects.btnSaveSRM);
				
				Thread.sleep(20000);
				
				uiSRM_Genesys_MarketingPageObjects.btnSaveSRM.click();
				Thread.sleep(10000);
				
				
			
				}catch (Exception e)
			{Reporter.log(e.getMessage());
				
			}
			}
			
			 @Test(dependsOnMethods={"ChangeVendorNameSRM"})
				
				public void SearchStudentAfterPhoneModification_IWD(Method objMethod) throws InterruptedException
				{
					try
					{DuplicateLeadCompletionPageObjects UiDuplicateLeadCompletion = new DuplicateLeadCompletionPageObjects(driver);
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					driver.get(EnvironmentVariables.sIWD_URL);
					
					
					 //Login Window And its Credentials of IWD Genesys 
					  driver.findElement(By.id("loginForm:username")).clear();
					  driver.findElement(By.id("loginForm:username")).sendKeys(EnvironmentVariables.sIWD_UName);
					  driver.findElement(By.id("loginForm:password")).clear();
					  driver.findElement(By.id("loginForm:password")).sendKeys(EnvironmentVariables.sIWD_PWD);	
					  driver.findElement(By.id("loginForm:submit")).click();
				
					  Thread.sleep(30000);
					 //Clicking on Global Task l ist link
					  
					  UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion .GlobalTaskList);
					 UiDuplicateLeadCompletion.GlobalTaskList.click();
					 Thread.sleep(30000);
					 UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion .kaplanTESTLink);
				
					 Thread.sleep(20000);
					   
					 //Clicking on Kaplan TEST Link
				     UiDuplicateLeadCompletion .kaplanTESTLink.click();
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CaptureSYStudentID);
				     Thread.sleep(20000);
				     
				     //Searching SyStudent ID in Captured ID field
				     UiDuplicateLeadCompletion.CaptureSYStudentID.clear();
				     UiDuplicateLeadCompletion.CaptureSYStudentID.sendKeys(sStudentid);
				     
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.FindCapturedSYID);
				     Thread.sleep(20000);
				     
				     //Finding the Captured ID from SRM
				     UiDuplicateLeadCompletion.FindCapturedSYID.click();
				     Thread.sleep(20000);
				     UserExtension.IsElementPresent(driver, driver.findElement(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]")));
				     Thread.sleep(5000);
				     
				     //Counting the searched research list 
				     List <WebElement> ele= driver.findElements(By.xpath("//div[contains(@id, 'mainForm:managerRegion:tasks_table-row')]"));
				     int i =  ele.size();
				     System.out.println(i);
				     Assert.assertTrue(i==2, "2 tasks in IWD");
				     
				     
				     //clicking on CaPtured lead ID
				     //UiDuplicateLeadCompletion.CapturedIDRecord.click();
				     Thread.sleep(20000);
				     
				    
				     //clicking on CaPtured lead ID
				     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CapturedIDRecord);
				     UiDuplicateLeadCompletion.CapturedIDRecord.click();
				    
				     Thread.sleep(10000);
				     System.out.println(UiDuplicateLeadCompletion.VerifymkLeadChannelID.getText());
				    
				     //Verifying lead record data in IWD 
				    Assert.assertEquals(ChannelID_update, UiDuplicateLeadCompletion.VerifymkLeadChannelID.getText().trim());
				     
				     Thread.sleep(20000);
				    
				     
				   
				    //logging out IWD 
				     driver.findElement(By.xpath("//a[@class='logout']")).click();
				     
					}
					catch(Exception e){Reporter.log(e.getMessage());}	
						//Thread.sleep(20000);
				     
				  
					
				}
			 
			 
			}
			
			
				
							
						
			

			




