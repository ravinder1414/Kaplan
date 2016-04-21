package sepDyanamicURL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.SEPDyanamicURL_Pageobject;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import uiMap_Orion3_SRM.ValidateCollege_InformationPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class SEPDyanamicUrl { 
				
						//Remote Web driver for remote execution
						public RemoteWebDriver driver = null;
						
						//BrowseManagement to set the browser capabilities
						public BrowserManagement objBrowserMgr = null;
						
						//Home Page Page Object Model
						
						public SEPDyanamicURL_Pageobject uiSEPDyanamicURL_Pageobject;
						
						public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
						public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
						public HomePageObjects uiHomePageObjects;
						public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
						public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
						public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
						public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
						public ValidateCollege_InformationPageObjects uiValidateCollege_Information;
						public SEP_CreateAccount_PageObjects uiSEP_CreateAccount_PageObjects;
						

						//Variables from Properties file
						public String CollegeName;
						public String sep_url;
						public String sepurl_PriorUpdate;
						public String sepurl_AfterUpdate;
						public String sep_url3;
						 String sFirstName1;
					      String sLastName1;
					     String sEmailAddress1;
						public String sMilitaryType;
						public String sSpouseMilitary;
						public String sTCPA;
						public String sHighestEducation;
						public String sDependedUsMilitary;
						public String sEmployer;
						
						//Static variable
						public String sPath_ResultProperties="";
						public static String sRandStr1 = RandomStringUtils.randomNumeric(3);
						public static String sSocialSecurityNumber = "563257" + sRandStr1;
						public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
						public static String sFirstName = "TestNGFNSEPDURL_" + sRandStr;
						public static String sLastName = "TestNGLNSEPDURL_" + sRandStr;			
						public static String sEmailAddress = sFirstName + "IC@kap.com";
						public String sDayPhone = "9545151234";
						public String sZipCode = "30256";
						
						
						
						
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
							
							
							
							
							
							
							
							
////////////////////////////////////////////*********************************************////////////////////////////
							
							
//Set file path as per environment for Result Property File

if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
{
	sPath_ResultProperties = ".//Resources//ResultProperties/DevResultProperties.properties";
	
}
else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
{
	sPath_ResultProperties = ".//Resources//ResultProperties/StageResultProperties.properties";	
}
else
{
	sPath_ResultProperties = ".//Resources//ResultProperties/TestResultProperties.properties";	
}




////////////////////////////////////////////*********************************************////////////////////////////

							
							
							
							
							
							
							
							
							
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
							
							profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sTrusted_Uris);
							//Capability
							objBrowserMgr = new BrowserManagement(sBrowser);
							objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
								
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
							driver.get(EnvironmentVariables.sSRM_Url);
							driver.manage().window().maximize();
							uiSEPDyanamicURL_Pageobject = new SEPDyanamicURL_Pageobject(driver);
							uiHomePageObjects = new HomePageObjects(driver);
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							uiValidateCollege_Information =new ValidateCollege_InformationPageObjects(driver);
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


								// Writing to Result Property File

								System.out.println("Inside After class");
								
								System.out.println("Before method writing to Result Property file");
								
								SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromSEPDyanamicUrl", sEmailAddress);
								
								System.out.println("After method writing to Result Property file");


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
						
						
						
						
						
						
						
						
						
						
						@Test
						public void BrowseToAddReferralLeadPage(Method objMethod) throws InterruptedException
						{
							try{uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
		                    Thread.sleep(30000);
		                    
		                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
							
		    				
							WebDriverWait wait = new WebDriverWait(driver, 10000);
							
							
							driver.switchTo().frame("ext-comp-1005");
							
							UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
							//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:j_id33:1")));
							
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
							
							Thread.sleep(30000);
							
		                 
							uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
							
							Thread.sleep(30000);
							
							UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);
							
							//WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:successmsgid")));
							
							Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
							
							driver.navigate().refresh();
							
							uiReusableMethods_PageObjects.lnkDropDown.click();
							Thread.sleep(10000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							//uiReusableMethods_PageObjects.BackToKaplanSRM(driver);
							Thread.sleep(10000);
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
	}
	
							
							
			
						
						
						@Test(dependsOnMethods={"BrowseToAddReferralLeadPage"})
						public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
						
						{
							try

							{

							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							driver.get(EnvironmentVariables.sSRM_Url);
							Thread.sleep(1000);
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
							System.out.println(sEmailAddress1);
							
							SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
						
							Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
							
							Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
									
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						}
						

						@Test(dependsOnMethods={"VerifyLeadInSRM"})
						public void Lead_Flow(Method objMethod) throws InterruptedException

						{try{
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							Select ddlInquiry = new Select(uiSRM_LeadFlow_PageObjects.ddInquiryDropDown);				
							
							ddlInquiry.selectByVisibleText("Contacted");
							
							Thread.sleep(30000);
							
							uiSRM_LeadFlow_PageObjects.btnSave.click();
							WebDriverWait wait = new WebDriverWait(driver, 50000);
							
							//WebElement element8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
							
							Thread.sleep(50000);
							
					        Actions action = new Actions(driver);
					 
					        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
					        
					        Thread.sleep(1000);
					        
					        uiSRM_LeadFlow_PageObjects.lnkSendSEPEmail.click();
					        
					        Thread.sleep(50000);
					        Alert act=driver.switchTo().alert();
					        act.accept();
					        
					        Thread.sleep(50000);
					        
					       
					        sep_url = uiSRM_LeadFlow_PageObjects.lnkSEPURL.getAttribute("href");
					        
					        System.out.println(sep_url);
					        
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						}  
						
					        

						
						@Test(dependsOnMethods={"Lead_Flow"})
						public void SEPUrl(Method objMethod) throws InterruptedException
						{try{
							uiSEPDyanamicURL_Pageobject = new SEPDyanamicURL_Pageobject(driver);
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							
							Thread.sleep(10000);
							
							if(!uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
							{

								uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.click();
							Thread.sleep(30000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							
							Thread.sleep(20000);
							

							}
							
							
							uiSEPDyanamicURL_Pageobject.lnkSetup.click();
							
							uiSEPDyanamicURL_Pageobject.lnkManageUsers.click();
							
							uiSEPDyanamicURL_Pageobject.lnkProfiles.click();
							
							uiSEPDyanamicURL_Pageobject.lnkAdmissionConsole.click();
							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkViewUsers.click();
							

							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkAdmissionAdvisorLoginUser.click();
							

							Thread.sleep(10000);
							
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
							System.out.println(sEmailAddress1);
							
							SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
							
							uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_PriorUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							Thread.sleep(20000);
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.lnkSEPUrl.sendKeys(sepurl_PriorUpdate+"Updated");
						
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_AfterUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							Thread.sleep(20000);
							
							Assert.assertEquals(sepurl_PriorUpdate, sepurl_AfterUpdate);
							
							uiSEPDyanamicURL_Pageobject.lnkLoggedinUser.click();
							Thread.sleep(5000);
							
							uiSEPDyanamicURL_Pageobject.lnkLogout.click();
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						}
							
							
							
						
						
						@Test(dependsOnMethods={"SEPUrl"})
						public void StudentSuccessUser(Method objMethod) throws InterruptedException
						{try{
							uiSEPDyanamicURL_Pageobject = new SEPDyanamicURL_Pageobject(driver);
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							
							Thread.sleep(10000);
							
							if(!uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
							{

								uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.click();
							Thread.sleep(30000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							
							Thread.sleep(20000);
							

							}
							
							
							uiSEPDyanamicURL_Pageobject.lnkSetup.click();
							
							uiSEPDyanamicURL_Pageobject.lnkManageUsers.click();
							
							uiSEPDyanamicURL_Pageobject.lnkProfiles.click();
							
							uiSEPDyanamicURL_Pageobject.lnkProfileS.click();
							
							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkStudentSuccess.click();
							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkViewUsers.click();
							

							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkLoginStudentSuccess.click();
							

							Thread.sleep(10000);
							
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
							System.out.println(sEmailAddress1);
							
							SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
							
							uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_PriorUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							Thread.sleep(20000);
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.lnkSEPUrl.sendKeys(sepurl_PriorUpdate+"Updated");
						
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_AfterUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							Thread.sleep(20000);
							
							Assert.assertEquals(sepurl_PriorUpdate, sepurl_AfterUpdate);
							
							uiSEPDyanamicURL_Pageobject.lnkLoggedinUser.click();
							Thread.sleep(5000);
							
							uiSEPDyanamicURL_Pageobject.lnkLogout.click();
							
						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						}
							
		                    
						
						
						
						@Test(dependsOnMethods={"StudentSuccessUser"})
						
						public void RowenRoweUser(Method objMethod) throws InterruptedException
						{try{
							uiSEPDyanamicURL_Pageobject = new SEPDyanamicURL_Pageobject(driver);
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							
							Thread.sleep(10000);
							
							if(!uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
							{

								uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.click();
							Thread.sleep(30000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							
							Thread.sleep(20000);
							

							}
							
							
							uiSEPDyanamicURL_Pageobject.lnkSetup.click();
							
							uiSEPDyanamicURL_Pageobject.lnkManageUsers.click();
							
							uiSEPDyanamicURL_Pageobject.lnkUsers.click();
							
							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkProfileR.click();
							
							Thread.sleep(10000);
							
							
							try{
							if(uiSEPDyanamicURL_Pageobject.lnkMoreUsers.getText().trim().equalsIgnoreCase("more"))
							{	

									uiSEPDyanamicURL_Pageobject.lnkMoreUsers.click();
								
							

							}
							}
							catch(Exception e){
								System.out.println("MOre is already clicked");
							}
							
							
							
							

							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.lnkRoweRowanUser.click();
							
							
							Thread.sleep(10000);
							
							uiSEPDyanamicURL_Pageobject.btnRoweRowanLogin.click();
							
							Thread.sleep(10000);
							
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							
							System.out.println(sEmailAddress1);
							
							SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
							
							uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_PriorUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							Thread.sleep(20000);
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.lnkSEPUrl.sendKeys(sepurl_PriorUpdate+"Updated");
						
							Thread.sleep(20000);
							
							uiSEPDyanamicURL_Pageobject.btnSave.click();
							
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							sepurl_AfterUpdate=uiSEPDyanamicURL_Pageobject.lnkSEPUrl.getText().trim();
							
							Thread.sleep(20000);
							
							Assert.assertEquals(sepurl_PriorUpdate, sepurl_AfterUpdate);
							
							uiSEPDyanamicURL_Pageobject.lnkLoggedinUser.click();
							Thread.sleep(5000);
							
							uiSEPDyanamicURL_Pageobject.lnkLogout.click();
							

						}catch (Exception e)
						{Reporter.log(e.getMessage());
							
						}
						} 
						}
						

	
			
	
