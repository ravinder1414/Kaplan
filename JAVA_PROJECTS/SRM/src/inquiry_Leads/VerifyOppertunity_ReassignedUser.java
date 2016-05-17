package inquiry_Leads;


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

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import uiMap_Orion3_SRM.ValidateCollege_InformationPageObjects;
import uiMap_Orion3_SRM.VerifyOppertunities_ReassignedUserPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

			public class VerifyOppertunity_ReassignedUser {
				
						//Remote Web driver for remote execution
						public RemoteWebDriver driver = null;
						
						//BrowseManagement to set the browser capabilities
						public BrowserManagement objBrowserMgr = null;
						
						//Home Page Page Object Model
						public HomePageObjects uiHomePageObjects;
						public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
						public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
						public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
						public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
						public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
						public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
						public ValidateCollege_InformationPageObjects uiValidateCollege_Information;
						public SEP_CreateAccount_PageObjects uiSEP_CreateAccount_PageObjects;
						public VerifyOppertunities_ReassignedUserPageObjects  uiVerifyOppertunities_ReassignedUserPageObjects;
						
						
						
						//Variables from Properties file
						public String CollegeName;
						public String sep_url;
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
						
						String sRandStr1 = RandomStringUtils.randomNumeric(3);
						public String sSocialSecurityNumber = "563257" + sRandStr1;
						String sRandStr = RandomStringUtils.randomAlphabetic(5);
						public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
						public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
						public String sEmailAddress = sFirstName + "IC@kap.com";
						public String sDayPhone = "9545151234";
						public String sZipCode = "30256";
						
						
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
							
							sDependedUsMilitary =objProperties.getProperty("sDependedUsMilitary");
							sEmployer =objProperties.getProperty("sEmployer");
						
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
							uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							uiValidateCollege_Information =new ValidateCollege_InformationPageObjects(driver);
							uiVerifyOppertunities_ReassignedUserPageObjects =new VerifyOppertunities_ReassignedUserPageObjects(driver);
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

						
						@Test
						public void BrowseToReferralLeadPage(Method objMethod) throws InterruptedException
						{
							try

							{

							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
							uiVerifyOppertunities_ReassignedUserPageObjects =new VerifyOppertunities_ReassignedUserPageObjects(driver);
		                    Thread.sleep(10000);
		                    
		                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);

							
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
							
							Thread.sleep(10000);
							
							}
							catch (Exception e)
													
							{
							Reporter.log(e.getMessage());
							System.out.println(e.getMessage());
							System.out.println(e.getStackTrace());
							}



							
						}			
			
						
						@Test(dependsOnMethods={"BrowseToReferralLeadPage"})
						public void VerifyWorkflowLead(Method objMethod) throws InterruptedException
						
						{
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							driver.get(EnvironmentVariables.sSRM_Url);
							Thread.sleep(1000);
							uiVerifyOppertunities_ReassignedUserPageObjects =new VerifyOppertunities_ReassignedUserPageObjects(driver);
							uiAddNewLeadsPageObjects.search_SRM.clear();
							uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
							uiAddNewLeadsPageObjects.btnsearch_SRM.click();
							uiVerifyOppertunities_ReassignedUserPageObjects.lnkSetup.click();
							uiVerifyOppertunities_ReassignedUserPageObjects.lnkCreate.click();
							Thread.sleep(10000);
							uiVerifyOppertunities_ReassignedUserPageObjects.lnkWorkflowApproval.click();
							Thread.sleep(10000);
							
							uiVerifyOppertunities_ReassignedUserPageObjects.lnkWorkflowRules.click();
							
							Thread.sleep(10000);
							
							uiVerifyOppertunities_ReassignedUserPageObjects.btnContinue.click();
							
							uiVerifyOppertunities_ReassignedUserPageObjects.lnkG.click();
							
							if(uiVerifyOppertunities_ReassignedUserPageObjects.lnkDeactivateUser.getText().trim().equalsIgnoreCase("Deactivate"))
							{

								uiVerifyOppertunities_ReassignedUserPageObjects.lnkDeactivateUser.click();

							}
							else
							{
								
							}

							
							
							
							
						
							Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
							
							Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
									
							
						
						}
						
						
						@Test(dependsOnMethods={"BrowseToReferralLeadPage"})
						public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
						
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
						

						@Test(dependsOnMethods={"VerifyLeadInSRM"})
						public void Lead_Flow(Method objMethod) throws InterruptedException

						{
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
							uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
							uiSRM_LeadFlow_PageObjects.btnEdit.click();
							
							Select ddlInquiry = new Select(uiSRM_LeadFlow_PageObjects.ddInquiryDropDown);				
							
							ddlInquiry.selectByVisibleText("Contacted");
							
							Thread.sleep(30000);
							
							uiSRM_LeadFlow_PageObjects.btnSave.click();
							
						
							
							Thread.sleep(30000);
							
					        Actions action = new Actions(driver);
					 
					        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
					        
					        Thread.sleep(1000);
					        
					        uiSRM_LeadFlow_PageObjects.lnkSendSEPEmail.click();
					        
					        Thread.sleep(30000);
					        Alert act=driver.switchTo().alert();
					        act.accept();
					        
					        Thread.sleep(20000);
					        
					       
					        sep_url = uiSRM_LeadFlow_PageObjects.lnkSEPURL.getAttribute("href");
					        
					        System.out.println(sep_url);
						}
					        
					        @Test(dependsOnMethods={"Lead_Flow"})
							public void SRM__Lead_Details(Method objMethod) throws InterruptedException

							{try{
					        	uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
					        	
					        	WebDriverWait wait = new WebDriverWait(driver, 10000);
					        	
					        	driver.get(sep_url);
					        	Thread.sleep(30000);
					        	
					        	UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtNewPassword);
					        	
					        	uiSRM_LeadFlow_PageObjects.txtNewPassword.sendKeys("qwer1234@");
					        	
					        	uiSRM_LeadFlow_PageObjects.txtConfirmPassword.sendKeys("qwer1234@");
					        	
					        	
					        	uiSRM_LeadFlow_PageObjects.btnOK.click();
					        	
					        	Thread.sleep(20000);
					        	
					        	UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtPermanentStreetAddress);
					        	
					        	if(uiSRM_LeadFlow_PageObjects.lnkSignout.getText().trim().equalsIgnoreCase("Sign Out"))
								{



								}
								else
								{
									uiSRM_LeadFlow_PageObjects.txtsignPassword.sendKeys("qwer1234@");
								Thread.sleep(5000);
								uiSRM_LeadFlow_PageObjects.btnSignIn.click();
								}

					        
					        	
					        	uiSRM_LeadFlow_PageObjects.txtPermanentStreetAddress.sendKeys("test123");
					        	
					        	uiSRM_LeadFlow_PageObjects.txtPermanentCity.sendKeys("test234");
					        	
					        	uiSRM_LeadFlow_PageObjects.checkBoxShippingAddress.click();
					        	
					        	Thread.sleep(10000);
					        	
					        	uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
					        	
					        	
					        	
					        	uiSRM_LeadFlow_PageObjects.txtDateofBirth.sendKeys("28051987");
					        	
					        	Thread.sleep(10000);
					        	
					        	//Select Gender
								Select ddlGender = new Select(uiSRM_LeadFlow_PageObjects.ddGender);
								ddlGender.selectByVisibleText("Male");
								
								//Marital Status
								
								Thread.sleep(10000);
								
								Select ddlMarrital = new Select(uiSRM_LeadFlow_PageObjects.ddMaritalStatus);
								ddlMarrital.selectByVisibleText("Married");
								
								
								//Radio button
								
								uiSRM_LeadFlow_PageObjects.rbtnIncarcerate1d_No.click();
								
								
								//Citizenship Hispanic Radio Button
								
								uiSRM_LeadFlow_PageObjects.rbtnHispanic_No.click();
								
								//Check Box
								
								uiSRM_LeadFlow_PageObjects.checkBoxAmericanIndian.click();
								
								
								
								//Citizenship DropDown
								
								UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.ddCitizenshipStatus);
								
								Select ddlCitizenship = new Select(uiSRM_LeadFlow_PageObjects.ddCitizenshipStatus);
								ddlCitizenship.selectByVisibleText("U.S. Citizen");
								
								
								
								//Social Security Number
								uiSRM_LeadFlow_PageObjects.txtSocialSecurityNumber.sendKeys(sSocialSecurityNumber);
								
								System.out.println(sSocialSecurityNumber);
								
								
								
								
								//Dependent of US Military
								
								if(sDependedUsMilitary.equalsIgnoreCase("yes"))
								{
									uiSRM_LeadFlow_PageObjects.rbtnUsMilitary_Yes.click();					
								}
								else
								{
									uiSRM_LeadFlow_PageObjects.rbtnUsMilitary_No.click();
								}
								
								//Radio Button Employer
								

								if(sEmployer.equalsIgnoreCase("yes"))
								{
									uiSRM_LeadFlow_PageObjects.rbtnEmployer_Yes.click();					
								}
								else
								{
									uiSRM_LeadFlow_PageObjects.rbtnEmployer_No.click();
								}
								
								Thread.sleep(10000);
								
								uiSRM_LeadFlow_PageObjects.rbnSave.click();
								
								Thread.sleep(10000);
								
								uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
								

		               	Thread.sleep(80000);
		               	
		               	
		                   Select ddHighSchoolType = new Select(uiSRM_LeadFlow_PageObjects.ddlHighSchoolType);
		                   ddHighSchoolType.selectByIndex(2);
							
							Thread.sleep(50000);
							
							
							Select ddlGraduationSchoolState = new Select(uiSRM_LeadFlow_PageObjects.ddlHighSchoolState);
							ddlGraduationSchoolState.selectByIndex(2);
							
							Thread.sleep(30000);
							
							//Graduation Date
							
							uiSRM_LeadFlow_PageObjects.txtGraduationCertificate.sendKeys("112015");
							
							//CheckBox Terms and Condition
							
							uiSRM_LeadFlow_PageObjects.checkboxTerms.click();
							
							Thread.sleep(10000);
							
							uiSRM_LeadFlow_PageObjects.rbnSave.click();
							
							Thread.sleep(10000);
							
							uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
							
							Thread.sleep(10000);
							
							
							uiSRM_LeadFlow_PageObjects.rbtnAttendedCollege_Yes.click();
							
							Thread.sleep(30000);
							
							Select ddlState = new Select(uiValidateCollege_Information.ddState);
							ddlState.selectByVisibleText("Florida");
							
							
							
							Thread.sleep(10000);
							
							WebDriverWait wait1 = new WebDriverWait(driver, 10000);
							
							Thread.sleep(10000);
						
							
							Select ddlCity = new Select(uiValidateCollege_Information.ddCity);
							ddlCity.selectByValue("15");
							
							Thread.sleep(10000);
							
							Select ddlSelectCollege = new Select(uiValidateCollege_Information.ddSelectCollege);
							ddlSelectCollege.selectByValue("1");
							
							
							Thread.sleep(10000);
							
							Select ddlDegreeType = new Select(uiValidateCollege_Information.ddDegreeType);
							ddlDegreeType.selectByIndex(1);
							
							Thread.sleep(10000);
							
							uiValidateCollege_Information.ddAreaOfStudy.clear();
							
							uiValidateCollege_Information.ddAreaOfStudy.sendKeys("Test");
							
							Thread.sleep(10000);
							
							uiValidateCollege_Information.txtAttendedFrom1.sendKeys("102010");
							
							Thread.sleep(10000);
							
							uiValidateCollege_Information.txtAttendedFrom2.sendKeys("102012");
							
							uiValidateCollege_Information.rbtnTransferCreditsNo.click();
							Thread.sleep(10000);
							
							uiValidateCollege_Information.btnDone.click();
							
						String CollegeName=	uiValidateCollege_Information.txtCollege.getText();
						
						System.out.println(CollegeName);
						
						uiSRM_LeadFlow_PageObjects.rbtnEquivalentCredit_No.click();
						
						uiSRM_LeadFlow_PageObjects.rbnSave.click();
							
							
						Thread.sleep(10000);
						  
							
							
							}catch (Exception e)
							{Reporter.log(e.getMessage());
								
							}
							}
				
					        @Test(dependsOnMethods={"SRM__Lead_Details"})

							public void VerifyOpportunityInSRM(Method objMethod) throws InterruptedException
							
							{
								uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
								uiValidateCollege_Information =new ValidateCollege_InformationPageObjects(driver);
								
								driver.get(EnvironmentVariables.sSRM_Url);
								Thread.sleep(1000);
								uiAddNewLeadsPageObjects.search_SRM.clear();
								uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
								uiAddNewLeadsPageObjects.btnsearch_SRM.click();
								
								
	                         //call Wait for Search Opportunity Method
								SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
								
								uiValidateCollege_Information.lnkOppurtunitiesEdit.click();
								Thread.sleep(10000);
								
								uiValidateCollege_Information.txtBilingMethodID.getText();
								
								System.out.println(uiValidateCollege_Information.txtBilingMethodID.getText());
								
								Assert.assertEquals(uiValidateCollege_Information.txtBilingMethodID.getText(), "CVue Billing Method Id");
								
								Actions action = new Actions(driver);
								 
						        action.moveToElement(uiValidateCollege_Information.lnkStudentEnrollmentPortalData).build().perform();
						        Thread.sleep(10000);
						        
						        WebDriverWait wait1 = new WebDriverWait(driver, 50000);
						      
								driver.switchTo().frame("RLPanelFrame");
								
								
								uiValidateCollege_Information.lnkSEPDataNumber.click();
								
								Thread.sleep(20000);
								
								
								//action.sendKeys(Keys.PAGE_DOWN);
								
								//WebElement scroll = driver.findElement(By.xpath(".//*[@class='pbBody']/table/tbody/tr[2]/th/a"));
								//scroll.sendKeys(Keys.PAGE_DOWN);
								
								
								Thread.sleep(10000);
								
								SRM_ReusableMethods.WaitCollegeUniversity(driver, 40000);
								
								String SrmCollegeName =uiValidateCollege_Information.lnkCollegeInformation.getText();
								System.out.println(SrmCollegeName);
								
								
								
								//Opportunity Stage changes
								
								
		                             Thread.sleep(10000);
								
								
								uiAddNewLeadsPageObjects.search_SRM.clear();
								uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
								uiAddNewLeadsPageObjects.btnsearch_SRM.click();
								
								
	                         //call Wait for Search Opportunity Method
								SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
								
								uiValidateCollege_Information.lnkOppurtunitiesEdit.click();
								Thread.sleep(10000);
								
								uiSRM_LeadFlow_PageObjects.btnEdit.click();
								Thread.sleep(5000);
								
								Select ddlOppurtunityStage = new Select(uiValidateCollege_Information.ddStageRemarketing);
								ddlOppurtunityStage.selectByVisibleText("Remarketing");
								
								
								//Remarketing Sub Reason
								
								Select ddlRemarketingReason = new Select(uiValidateCollege_Information.ddRemarketingReason);
								ddlRemarketingReason.selectByVisibleText("Test Lead");
								
								//Remarketing Sub Reason
								
								Select ddlRemarketingSubReason = new Select(uiValidateCollege_Information.ddRemarketingSubReason);
								ddlRemarketingSubReason.selectByVisibleText("Test Lead");
								
								//Button Save
								
								uiValidateCollege_Information.btnSave.click();
								
								Thread.sleep(20000);
								
								
								uiAddNewLeadsPageObjects.search_SRM.clear();
								uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
								uiAddNewLeadsPageObjects.btnsearch_SRM.click();
								
								
	                         // call Wait for Search Opportunity Method
								SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
								
								
								uiAddNewLeadsPageObjects.txtStatusOppertunities.getText();							
								System.out.println(uiAddNewLeadsPageObjects.txtStatusOppertunities.getText());
								
								Assert.assertEquals(uiAddNewLeadsPageObjects.txtStatusOppertunities.getText(), "Remarketing");
								
								
								
								
							
							
							}
					        
					        
					        @Test(dependsOnMethods={"VerifyOpportunityInSRM"})
							public void VerifyNewOpportunityInSRM(Method objMethod) throws InterruptedException
							
							{
								uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
								uiValidateCollege_Information =new ValidateCollege_InformationPageObjects(driver);
								uiSRM_LeadFlow_PageObjects=new SRM_LeadFlow_PageObjects(driver);
								uiSEP_CreateAccount_PageObjects=new SEP_CreateAccount_PageObjects(driver);
								
								
								driver.get(EnvironmentVariables.sSEP_Url);
								Thread.sleep(1000);
								WebDriverWait wait = new WebDriverWait(driver, 10000);
								
								uiSEP_CreateAccount_PageObjects.lblBeginApplication.click();
								
								uiValidateCollege_Information.lnkSEPSignOut.click();
								Thread.sleep(10000);
								uiSEP_CreateAccount_PageObjects.lblBeginApplication.click();
								uiValidateCollege_Information.lnkSEPSignin.click();
								
								uiValidateCollege_Information.txtSEPUsername.sendKeys(sEmailAddress1);
								uiValidateCollege_Information.txtSEPPassword.sendKeys("qwer1234@");
								
								uiValidateCollege_Information.btnSignIn.click();
								
								Thread.sleep(15000);
								
								
								uiSRM_LeadFlow_PageObjects.rbtnEquivalentCredit_No.click();
								
			                    Thread.sleep(5000);
								
								uiSRM_LeadFlow_PageObjects.rbnSave.click();
								
								Thread.sleep(5000);
								
								uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
								
								
								WebDriverWait wait1 = new WebDriverWait(driver, 5000);
								
								Thread.sleep(40000);
								
								
								//Area of Study
								
								Select ddlAreaofStudy = new Select(uiSRM_LeadFlow_PageObjects.ddAreaofStudy);
								ddlAreaofStudy.selectByValue("2");
								
								//Degree Level
								
								Thread.sleep(30000);
								
								Select ddlDegreeLevel = new Select(uiSRM_LeadFlow_PageObjects.ddDegreeLevel);
								ddlDegreeLevel.selectByVisibleText("Master of Science");
								
								
								
								//Program of Study
								
								Thread.sleep(30000);
								
								Select ddlProgramofStudy = new Select(uiSRM_LeadFlow_PageObjects.ddProgramofStudy);
								ddlProgramofStudy.selectByValue("4");
								
			                   //Emphasis Type
								
								Select ddlEmphasisArea = new Select(uiSRM_LeadFlow_PageObjects.ddEmphasisArea);
								ddlEmphasisArea.selectByValue("1");
								
								
								
								//Enrollment Type
								
								Thread.sleep(30000);
								
								Select ddlEnrollmentType = new Select(uiSRM_LeadFlow_PageObjects.ddEnrollmentType);
								ddlEnrollmentType.selectByVisibleText("Full Time");
								
								//Select Terms Start Date
								
								Thread.sleep(1000);
								
								Select ddlTermStartDate = new Select(uiSRM_LeadFlow_PageObjects.ddTermStartDate);
								ddlTermStartDate.selectByIndex(1);
								
			                    Thread.sleep(10000);
								
								uiSRM_LeadFlow_PageObjects.rbnSave.click();
								
								Thread.sleep(10000);
								
								uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
								
								Thread.sleep(10000);
								
								
								driver.get(EnvironmentVariables.sSRM_Url);
								Thread.sleep(1000);
								uiAddNewLeadsPageObjects.search_SRM.clear();
								uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
								uiAddNewLeadsPageObjects.btnsearch_SRM.click();
								
								
	                         //call Wait for Search Opportunity Method
								SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
								
								uiValidateCollege_Information.lnkOppurtunitiesEdit.click();
								Thread.sleep(10000);
								
								Actions action = new Actions(driver);
								 
						        action.moveToElement(uiValidateCollege_Information.lnkStudentEnrollmentPortalData).build().perform();
						        Thread.sleep(10000);
						        
						      
								driver.switchTo().frame("RLPanelFrame");
								
								
								uiValidateCollege_Information.lnkSEPDataNumber.click();
								
								Thread.sleep(20000);
								
							
								
								SRM_ReusableMethods.WaitCollegeUniversity(driver, 40000);
								
								String SrmCollegeName =uiValidateCollege_Information.lnkCollegeInformation.getText();
								System.out.println(SrmCollegeName);
								
								
								
							
							
							}
			
			
			
			}

