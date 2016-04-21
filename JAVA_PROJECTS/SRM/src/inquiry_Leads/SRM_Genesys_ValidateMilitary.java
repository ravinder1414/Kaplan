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
import org.openqa.selenium.Keys;
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
import uiMap_Orion3_SRM.SRM_Genesys_ValidateMilitary_PageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

				public class SRM_Genesys_ValidateMilitary {
					
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
							public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
							public SRM_Genesys_ValidateMilitary_PageObjects uiSRM_Genesys_ValidateMilitary_PageObjects;
							public String LeadImportID1;
							public String sMilitaryTypeText;
											
							public String sSearchMain_WindowName ="";
							
							public String mainwinhandle;
							int upchannelid;
							

							//Variables from Properties file
							 String sFirstName1;
						      String sLastName1;
						     String sEmailAddress1;
							public String sMilitaryType;
							public String sSpouseMilitary;
							public String sTCPA;
							public String sHighestEducation;
							public String mkvendorLeaddescription;
						     public String marketingChanneltext;
						     public  String mkleadchannelID;
						     public String txtMarketingchannelGOK;
						     public String sMilitarySpouseTypeInquiry;
						     public String sMilitarySpouseAfterUpdateInquiry;
							
							//Static variable
							public String sPath_ResultProperties="";
							public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
							public static String sFirstName = "TestNGFNRL_" + sRandStr;
							public static String sLastName = "TestNGLNRL_" + sRandStr;			
							public static String sEmailAddress = sFirstName + "IC@kap.com";
							public String sDayPhone = "9545151234";
							public String sZipCode = "30256";
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
								
                                //Reading from Properties file
								
								sTCPA = objProperties.getProperty("sTCPADisclosure");
								sMilitaryType = objProperties.getProperty("sMilitaryType");
								sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
								sHighestEducation = objProperties.getProperty("sHighestEduction");
								sMilitarySpouseTypeInquiry =objProperties.getProperty("sMilitarySpouseTypeInquiry");
								sMilitarySpouseAfterUpdateInquiry =objProperties.getProperty("sMilitarySpouseAfterUpdateInquiry");
								
							
											
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
								uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
								uiSRM_Genesys_ValidateMilitary_PageObjects =new SRM_Genesys_ValidateMilitary_PageObjects(driver);
							}
							
							@AfterClass
							public void AfterNavigation()
							{try{
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
							public void BrowseToAddNewReferralLeadPage(Method objMethod) throws InterruptedException
							{
								try

								{
								uiSRM_Genesys_ValidateMilitary_PageObjects =new SRM_Genesys_ValidateMilitary_PageObjects(driver);
								uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
								uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
								uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
			                    Thread.sleep(20000);
			                    
			                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
								
			    				
								WebDriverWait wait = new WebDriverWait(driver, 10000);
								
								
								driver.switchTo().frame("ext-comp-1005");
								
								UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
								
								
								//Select Referral Radio Button
								uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral.click();
								
								Thread.sleep(10000);
								
								uiAddInquiry_Referral_Lead_Pageobjects.txtFirstName.sendKeys(sFirstName);
								uiAddInquiry_Referral_Lead_Pageobjects.txtLastName.sendKeys(sLastName);
								uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.sendKeys(sEmailAddress);
								
								System.out.println(sEmailAddress);
								
								sEmailAddress1 =uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.getAttribute("value");
								
								uiAddInquiry_Referral_Lead_Pageobjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
								uiAddInquiry_Referral_Lead_Pageobjects.txtZipCode.sendKeys(sZipCode);
								
								Thread.sleep(10000);
								
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
								
								Thread.sleep(30000);
								
			                 
								uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
								
								Thread.sleep(30000);
								
								UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);
								
								
								Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
								
								driver.navigate().refresh();
								
								uiReusableMethods_PageObjects.lnkDropDown.click();
								Thread.sleep(10000);
								uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
								
								
								}
								catch (Exception e)
														
								{
								Reporter.log(e.getMessage());
								System.out.println(e.getMessage());
								System.out.println(e.getStackTrace());
								}



								
							}			
				


				@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
			
				public void SearchInquirySRM(Method objMethod) throws InterruptedException
				
				
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
					
					
					uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
					

					Thread.sleep(20000);
					
			        Actions action = new Actions(driver);
			 
			        action.moveToElement(uiSRM_Genesys_ValidateMilitary_PageObjects.lnkStudentInformationSubmission).build().perform();
			        
			        Thread.sleep(10000);
			        
			        driver.switchTo().frame("RLPanelFrame");
			        
			        UserExtension.IsElementPresent(driver, uiSRM_Genesys_ValidateMilitary_PageObjects.lnkStudentInformationUsers);
			        
			        uiSRM_Genesys_ValidateMilitary_PageObjects.lnkStudentInformationUsers.click();
			        
			        Thread.sleep(10000);
			        
			        UserExtension.IsElementPresent(driver, uiSRM_Genesys_ValidateMilitary_PageObjects.txtSyStudentid);
			        
			        sStudentid=uiSRM_Genesys_ValidateMilitary_PageObjects.txtSyStudentid.getText();
			        
			        System.out.println(sStudentid);
			        
			        driver.switchTo().frame("066i0000004ef9y");
			        
			       
			        action.sendKeys(uiSRM_Genesys_ValidateMilitary_PageObjects.lnkSIFAnswers, Keys.PAGE_DOWN);
			        
			        UserExtension.IsElementPresent(driver, uiSRM_Genesys_ValidateMilitary_PageObjects.lnkSIFAnswers);
			        
			        
			        uiSRM_Genesys_ValidateMilitary_PageObjects.lnkSIFAnswers.click();
			        
			       
			        
			        Thread.sleep(30000);
					
					sMilitaryTypeText=uiSRM_Genesys_ValidateMilitary_PageObjects.txtMilitaryTypeLink.getText();
					
					System.out.println(sMilitaryTypeText);
					
				
					
				
				}catch (Exception e)
				{Reporter.log(e.getMessage());
					
				}
				}
				 
				@Test(dependsOnMethods={"SearchInquirySRM"})
				
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
					     
					     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.txtMilitaryType);
					     
					     System.out.println(UiDuplicateLeadCompletion.txtMilitaryType.getText());
					    
					     
					     Assert.assertEquals(sMilitarySpouseTypeInquiry, UiDuplicateLeadCompletion.txtMilitaryType.getText().trim(), "military status matched");
					     
					     
					     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.txtIsMilitary);
					     
					     Assert.assertEquals("True", UiDuplicateLeadCompletion.txtIsMilitary.getText().trim());
					     
					     System.out.println(UiDuplicateLeadCompletion.txtIsMilitary.getText());
					     
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
			
				public void UpdateInquiryMilitaryValueSRM(Method objMethod) throws InterruptedException
				
				
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
					
					uiSRM_LeadFlow_PageObjects.btnEdit.click();
					
					
					//Highest Level of Education
					Select ddlMilitaryStatus = new Select(uiSRM_Genesys_ValidateMilitary_PageObjects.ddMilitaryStatus);
					ddlMilitaryStatus.selectByVisibleText("Active Duty");
					
					uiSRM_LeadFlow_PageObjects.btnSave.click();
					Thread.sleep(20000);
					
					
					
				
				}catch (Exception e)
				{Reporter.log(e.getMessage());
					
				}
				}
				
				
				
				 @Test(dependsOnMethods={"UpdateInquiryMilitaryValueSRM"})
					
					public void SearchMilitaryStatusAfterModification_IWD(Method objMethod) throws InterruptedException
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
					     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.CapturedIDRecord);
					     UiDuplicateLeadCompletion.CapturedIDRecord.click();
					    
					     Thread.sleep(10000);
					     
					     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.txtMilitaryType);
					     
					     System.out.println(UiDuplicateLeadCompletion.txtMilitaryType.getText());
					    
					    
					     
					     Assert.assertEquals(sMilitarySpouseAfterUpdateInquiry, UiDuplicateLeadCompletion.txtMilitaryType.getText().trim(), "military status matched");
					     
					   
					     
					     
					     System.out.println(UiDuplicateLeadCompletion.txtMilitaryType.getText());
					     
					     UserExtension.IsElementPresent(driver, UiDuplicateLeadCompletion.txtIsMilitary);
					     
					     Assert.assertEquals("True", UiDuplicateLeadCompletion.txtIsMilitary.getText().trim());
					     
					     System.out.println(UiDuplicateLeadCompletion.txtIsMilitary.getText());
					     
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
							
					     
					  
						
					}
				}
				
				
				
					
						
