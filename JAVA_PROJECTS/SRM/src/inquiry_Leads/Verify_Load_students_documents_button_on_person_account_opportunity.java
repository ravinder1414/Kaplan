package inquiry_Leads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
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

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion1.DocSchedulePageObject;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class Verify_Load_students_documents_button_on_person_account_opportunity {
		
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
				public DocSchedulePageObject uiDocScheulePage;
				
				
				//Variables from Properties file
				String sFirstName1;
				String sLastName1;
			    String sEmailAddress1;
				public String sMilitaryType;
				public String sSpouseMilitary;
				public String sTCPA;
				public String sHighestEducation;
				public  String sCampus= "";
				public  String sDocCategory= "";
				public  String sDocName= "";
				
				
				//Static variable
				String sRandStr = RandomStringUtils.randomAlphabetic(5);
				public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
				public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
				public String sEmailAddress = sFirstName + "IC@kap.com";
				public String sDayPhone = "9545151234";
				public String sZipCode = "30256";
				public String sStunum, sStuid;
				
				
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
					sCampus = objProperties.getProperty("Campus");
					sDocCategory = objProperties.getProperty("DocCategory");
					sDocName = objProperties.getProperty("DocName");
					
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
					uiDocScheulePage = new DocSchedulePageObject(driver);
					
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
				public void BrowseToAddNewReferralLeadPage(Method objMethod) throws InterruptedException
				{
					uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
					uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
					uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
                    Thread.sleep(3000);
                    
                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
					
    				
							
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
					
					Thread.sleep(3000);
					
                 
					uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
					
					Thread.sleep(3000);
					
					UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);				
					Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
					
					driver.navigate().refresh();
					
					uiReusableMethods_PageObjects.lnkDropDown.click();
					Thread.sleep(3000);
					uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
					Thread.sleep(1000);
					
					
				}			
	
				
				
				@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
				public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
				
				{try{
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					driver.get(EnvironmentVariables.sSRM_Url);
					uiAddNewLeadsPageObjects.search_SRM.clear();
					uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
					WebDriverWait wait = new WebDriverWait(driver, 5000);
					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
						
					
					uiAddNewLeadsPageObjects.btnsearch_SRM.click();
					
					SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
					
					WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
					Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
					
					Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
									
					
				}catch (Exception e)
				{Reporter.log(e.getMessage());
					
				}
				}


				
				//Progress Lead to contacted through SRM	
				@Test (dependsOnMethods="VerifyLeadInSRM")
				public void ProgressLeadtoContacted(Method obMethod) throws InterruptedException{
					try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
						uiSRM_LeadFlow_PageObjects.btnEdit.click();
						Select ddlInquiry = new Select(uiSRM_LeadFlow_PageObjects.ddInquiryDropDown);				
						ddlInquiry.selectByVisibleText("Contacted");
						Thread.sleep(3000);
						uiSRM_LeadFlow_PageObjects.btnSave.click();
				}
					catch(Exception e){Reporter.log(e.getMessage());}
				}
				
				//convert Lead to OppoRtunity
				@Test (dependsOnMethods="ProgressLeadtoContacted")
				public void ConvertOpportunityAccounts(Method obMethod) throws InterruptedException{
					try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						uiSRM_LeadFlow_PageObjects.btnConvert.click();
						Thread.sleep(10000);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.hdngConvInquiry);
						Thread.sleep(10000);
						Assert.assertTrue(uiSRM_LeadFlow_PageObjects.hdngConvInquiry.isDisplayed());
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}
				
				//Verify Opportunity and Lead in SRM SalesForce
				@Test (dependsOnMethods="ConvertOpportunityAccounts")
				public void VerifyOpportunityAccounts(Method obMethod) throws InterruptedException{
					try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						
						System.out.println("Initial Executing Verify Opportunity and Lead in SRM SalesForce last method");
						
						//Navigate to Homepage by opening the URL
						driver.get(EnvironmentVariables.sSRM_Url);
						
						//Search for the Lead/opportunity
						uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
						//uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
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
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtCVUEStunum);
						
						System.out.println("End Executing Verify Opportunity and Lead in SRM SalesForce last method");
						
						//Store CVUE Student Number and SystudentID
						sStunum = uiSRM_LeadFlow_PageObjects.txtCVUEStunum.getText().trim() ; 
						sStuid = uiSRM_LeadFlow_PageObjects.txtSyStudentID.getText().trim() ; 
						Reporter.log(sStunum +", " +sStuid);
						
						
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}

				
				
				
				//Verify Opportunity and Lead in SRM SalesForce
				@Test (dependsOnMethods="VerifyOpportunityAccounts")
				public void verifyLoadStudentsDocuments(Method obMethod) throws InterruptedException{
					try{
						
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						
						System.out.println("Inside final Test");
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						
						Thread.sleep(2000);
						driver.switchTo().frame(uiSRM_LeadFlow_PageObjects.idIFrame);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.btnLoadStuDocs);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.tabDefault);
						uiSRM_LeadFlow_PageObjects.btnLoadStuDocs.click();
						
						
						Thread.sleep(2000);
						
						System.out.println("Returning to main page again to check in account");
						
						driver.navigate().back();
						
						// Click Search again
						
						uiAddNewLeadsPageObjects.search_SRM.clear();;
						
						uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
					
						uiAddNewLeadsPageObjects.btnsearch_SRM.click();
						
						// Click Accounts
						
						driver.findElement(By.xpath(".//*[@id='Account_body']/table/tbody/tr[2]/th/a")).click();
						
						Thread.sleep(2000);
					
						driver.switchTo().frame("066i0000004AzFP");
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.btnLoadStuDocs);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.tabDefault);
						uiSRM_LeadFlow_PageObjects.btnLoadStuDocs.click();
						System.out.println("Done");
						Reporter.log(sStunum +", " +sStuid);
						
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}
				
				
				
		
				
				
				
			
				
	}
				
				
	

	


