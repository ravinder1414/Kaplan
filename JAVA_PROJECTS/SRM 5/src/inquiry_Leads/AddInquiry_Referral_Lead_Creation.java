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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import orion3_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_SRM5_PageObjects.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_SRM5_PageObjects.HomePageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class AddInquiry_Referral_Lead_Creation {
		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				//Home Page Page Object Model
				public HomePageObjects uiHomePageObjects;
				public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
				
				
				//Variables from Properties file
				 String sFirstName1;
			      String sLastName1;
			     String sEmailAddress1;
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

                    Thread.sleep(30000);
                    
					if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Admissions Console"))
					{
						
						
						//uiAddInquiry_Referral_Lead_Pageobjects.lnkBackToAdmissionConsole.click();
						
						
					}
					else
					{
						driver.findElement(By.xpath("//*[@id='tsid-arrow']")).click();
						Thread.sleep(30000);
						uiAddInquiry_Referral_Lead_Pageobjects.lnkAdmissionConsole.click();
					}
					
				
					WebDriverWait wait = new WebDriverWait(driver, 10000);
					driver.switchTo().frame("ext-comp-1006");
					
					UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:j_id33:1")));
					
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
					
					WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:addALeadButtonId")));
					uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
					
					Thread.sleep(30000);
					
					WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:successmsgid")));
					
					Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
					
					driver.navigate().refresh();
					
					driver.findElement(By.xpath("//*[@id='tsid-arrow']")).click();
					Thread.sleep(30000);
					uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
					Thread.sleep(10000);
					
					
				}			
	
				
				
				@Test(dependsOnMethods={"BrowseToAddNewReferralLeadPage"})
				public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
				
				{
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					driver.get(EnvironmentVariables.sSRM_Url);
					uiAddNewLeadsPageObjects.search_SRM.clear();
					uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
					WebDriverWait wait = new WebDriverWait(driver, 5000);
					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
						
					uiAddNewLeadsPageObjects.btnsearch_SRM.click();
					
					Thread.sleep(10000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					Thread.sleep(70000);
					
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					Thread.sleep(70000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					Thread.sleep(30000);
					
					WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
					Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
					
					Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
							
					//driver.close();
					

				
				}
	}

	

