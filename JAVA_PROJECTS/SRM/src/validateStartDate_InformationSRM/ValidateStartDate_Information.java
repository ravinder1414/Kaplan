package validateStartDate_InformationSRM;

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
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class ValidateStartDate_Information {
			
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
					public String sFirstName = "TestNGFNVSDATE_" + sRandStr;
					public String sLastName = "TestNGLNVSDATE_" + sRandStr;			
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
					public void BrowseToAddInfoCallLeadPage(Method objMethod) throws InterruptedException
					{
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
						uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);

	                    Thread.sleep(30000);
	                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
						
	    				
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						
						
						driver.switchTo().frame("ext-comp-1005");
						
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.rbnInternet);
						Thread.sleep(15000);
						uiInfoCallLeadPageObjects.rbnInternet.click();
						Thread.sleep(20000);
						//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:prmotionid")));
						//Thread.sleep(30000);
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddPromotional);
						Select ddlPromotionCode = new Select(uiInfoCallLeadPageObjects.ddPromotional);
						ddlPromotionCode.selectByIndex(1);
						Thread.sleep(20000);
						
						
						//Select Area of Study
						
						Select ddlAreaOfStudy = new Select(uiInfoCallLeadPageObjects.ddAreaofStudy);				
					
						ddlAreaOfStudy.selectByIndex(1);
						
						//Program of Interest
						
						//WebElement element6 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:progid")));
						Thread.sleep(15000);
						Select ddlProgramOfInterest = new Select(uiInfoCallLeadPageObjects.ddProgramofInterest);
						
						ddlProgramOfInterest.selectByIndex(1);
						Thread.sleep(15000);
						
						uiInfoCallLeadPageObjects.txtFirstName.sendKeys(sFirstName);
						uiInfoCallLeadPageObjects.txtLastName.sendKeys(sLastName);
						uiInfoCallLeadPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
						
						sEmailAddress1 =uiInfoCallLeadPageObjects.txtEmailAddress.getAttribute("value");
						Thread.sleep(1000);
						uiInfoCallLeadPageObjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
						uiInfoCallLeadPageObjects.txtZipCode.sendKeys(sZipCode);
						
						//Spouse Military Status
						if(sSpouseMilitary.equalsIgnoreCase("yes"))
						{
							
							uiInfoCallLeadPageObjects.rbtnSpouse_Yes.click();
							
						}
						else
						{
							uiInfoCallLeadPageObjects.rbtnSpouse_No.click();
						}
						
						Thread.sleep(1000);
	                   //	Military Type DropDown values
						
						Select ddlMilitaryType = new Select(uiInfoCallLeadPageObjects.dropDownMilitaryType);				
						
						ddlMilitaryType.selectByIndex(1);
						
						//TCPA Disclosure
						if(sTCPA.equalsIgnoreCase("yes"))
						{
							uiInfoCallLeadPageObjects.rbtnTCPA_Disclosure_Yes.click();					
						}
						else
						{
							uiInfoCallLeadPageObjects.rbtnTCPA_Disclosure_No.click();
						}
						
						
						//Highest Level of Education
						Select ddlHightestEdution = new Select(uiInfoCallLeadPageObjects.ddHighestLevelEducation);
						ddlHightestEdution.selectByVisibleText(sHighestEducation);
						
						Thread.sleep(40000);
						
						//WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:addALeadButtonId")));
						uiInfoCallLeadPageObjects.txtAddAnInquiry.click();
						
						Thread.sleep(30000);
						
						//WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:successmsgid")));
						
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.txtCreatedLeadSuccess);
						Assert.assertEquals(uiInfoCallLeadPageObjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
						
						driver.navigate().refresh();
						
						uiReusableMethods_PageObjects.lnkDropDown.click();
						Thread.sleep(30000);
						uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
						//uiReusableMethods_PageObjects.BackToKaplanSRM(driver);
						Thread.sleep(20000);
						
						
					}			
		
					
					
					@Test(dependsOnMethods={"BrowseToAddInfoCallLeadPage"})
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
						
						Thread.sleep(20000);
						
						uiSRM_LeadFlow_PageObjects.btnSave.click();
						WebDriverWait wait = new WebDriverWait(driver, 50000);
						
						//WebElement element8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Activity History']")));
						
						Thread.sleep(50000);
						
				        Actions action = new Actions(driver);
				 
				        action.moveToElement(uiSRM_LeadFlow_PageObjects.lnkActivityHistory).build().perform();
				        
				        Thread.sleep(20000);
				        
				        uiSRM_LeadFlow_PageObjects.lnkSendSEPEmail.click();
				        
				        Thread.sleep(50000);
				        Alert act=driver.switchTo().alert();
				        act.accept();
				        
				        Thread.sleep(50000);
				        
				        driver.navigate().refresh();
				        
				        //uiSRM_LeadFlow_PageObjects.lnkSEPURL.click();
				        
				       
				        sep_url = uiSRM_LeadFlow_PageObjects.lnkSEPURL.getAttribute("href");
				        
				       //uiSRM_LeadFlow_PageObjects.lnkSEPURL.getAttribute("href").;
				        
				        System.out.println(sep_url);
				        
				       
			}
			
				        
				        @Test(dependsOnMethods={"Lead_Flow"})
						public void SRM__Lead_Details(Method objMethod) throws InterruptedException

						{try{
				        	uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
				        	
				        	WebDriverWait wait = new WebDriverWait(driver, 10000);
				        	//uiSRM_LeadFlow_PageObjects.lnkSEPURL.click();
				        	driver.get(sep_url);
				        	Thread.sleep(50000);
				        	
				        	UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtNewPassword);
				        	
				        	uiSRM_LeadFlow_PageObjects.txtNewPassword.sendKeys("qwer1234@");
				        	
				        	uiSRM_LeadFlow_PageObjects.txtConfirmPassword.sendKeys("qwer1234@");
				        	
				        	
				        	uiSRM_LeadFlow_PageObjects.btnOK.click();
				        	
				        	System.out.println(123);
				        	
                            Thread.sleep(50000);
				        	
				        	if(!uiSRM_LeadFlow_PageObjects.lnkSignout.getText().trim().equalsIgnoreCase("Sign Out"))
							{

				        		uiSRM_LeadFlow_PageObjects.txtsignPassword.sendKeys("qwer1234@");
								Thread.sleep(5000);
								uiSRM_LeadFlow_PageObjects.btnSignIn.click();

							}
							

				        	
				        	Thread.sleep(10000);
				        	
				        	//uiSRM_LeadFlow_PageObjects.txtsignPassword.sendKeys("qwer1234@");
				        	
				        	//uiSRM_LeadFlow_PageObjects.btnSignIn.click();
				        	
				        	
				        	
				        	
				        	
				        	
				        	Thread.sleep(10000);
				        	
				        	//uiSRM_LeadFlow_PageObjects.txtPassword.sendKeys("qwer1234@");
				        	
				        	//WebElement element9 = wait.until(ExpectedConditions.elementToBeClickable(By.name("PermanentStreetAddress")));
				        	
				        	uiSRM_LeadFlow_PageObjects.txtPermanentStreetAddress.sendKeys("test123");
				        	
				        	uiSRM_LeadFlow_PageObjects.txtPermanentCity.sendKeys("test234");
				        	
				        	uiSRM_LeadFlow_PageObjects.checkBoxShippingAddress.click();
				        	
				        	Thread.sleep(10000);
				        	
				        	uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
				        	
				        	//WebElement element10 = wait.until(ExpectedConditions.elementToBeClickable(By.name("DateOfBirth")));
				        	
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
							
							/*if(sDependedUsMilitary.equalsIgnoreCase("yes"))
							{
								//uiSRM_LeadFlow_PageObjects.rbtnUsMilitary_Yes.click();					
							}
							//else
							{*/
								uiSRM_LeadFlow_PageObjects.rbtnUsMilitary_No.click();
							
							
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
							
							Thread.sleep(50000);
							
							uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
							

	               	Thread.sleep(80000);
	               	
	               	
	                   Select ddHighSchoolType = new Select(uiSRM_LeadFlow_PageObjects.ddlHighSchoolType);
	                   ddHighSchoolType.selectByIndex(2);
						
						Thread.sleep(50000);
						
						
						Select ddlGraduationSchoolState = new Select(uiSRM_LeadFlow_PageObjects.ddlHighSchoolState);
						ddlGraduationSchoolState.selectByIndex(2);
						
						Thread.sleep(50000);
						
						//Graduation Date
						
						uiSRM_LeadFlow_PageObjects.txtGraduationCertificate.sendKeys("112015");
						
						//CheckBox Terms and Condition
						
						uiSRM_LeadFlow_PageObjects.checkboxTerms.click();
						
						Thread.sleep(10000);
						
						uiSRM_LeadFlow_PageObjects.rbnSave.click();
						
						Thread.sleep(10000);
						
						uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
						
						Thread.sleep(10000);
						
						
						uiSRM_LeadFlow_PageObjects.rbtnAttendedCollege_No.click();
						
						Thread.sleep(30000);
						
						
					uiSRM_LeadFlow_PageObjects.rbtnEquivalentCredit_No.click();
					
					uiSRM_LeadFlow_PageObjects.rbnSave.click();
						
						
					Thread.sleep(20000);
					  
					
					uiSRM_LeadFlow_PageObjects.rbtnEquivalentCredit_No.click();
					
                    Thread.sleep(10000);
					
					uiSRM_LeadFlow_PageObjects.rbnSave.click();
					
					Thread.sleep(10000);
					
					uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
					
					Thread.sleep(30000);
					
					
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
					
					//String startDate=ddlTermStartDate.selectByIndex(1)
					
                    Thread.sleep(10000);
					
					uiSRM_LeadFlow_PageObjects.rbnSave.click();
					
					Thread.sleep(50000);
					
					uiSRM_LeadFlow_PageObjects.rbtnSaveAndContinue.click();
					
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
							
							Actions action = new Actions(driver);
							 
					        action.moveToElement(uiValidateCollege_Information.lnkStudentEnrollmentPortalData).build().perform();
					        Thread.sleep(10000);
					        
					      
							driver.switchTo().frame("RLPanelFrame");
							
							
							uiValidateCollege_Information.lnkSEPDataNumber.click();
							
							Thread.sleep(10000);
							
							SRM_ReusableMethods.WaitStartDate(driver, 50000);
							
							String StartDate=uiValidateCollege_Information.txtStartDate.getText().trim();
							
							System.out.println(StartDate);
						
							
						}
				        
				       
		
		
		
		}
		
		
		
				        
				    
