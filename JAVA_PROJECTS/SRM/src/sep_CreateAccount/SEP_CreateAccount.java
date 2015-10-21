package sep_CreateAccount;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

	public class SEP_CreateAccount {
		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				//Home Page Page Object Model
				public HomePageObjects uiHomePageObjects;
				public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				public SEP_CreateAccount_PageObjects uiSEP_CreateAccount_PageObjects;
				public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
				public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
				
				//Variables from Properties file
				 String sFirstName1;
			     String sLastName1;
			     String sEmailAddress1;
				public String sLeadType;
				public String sChannelGroup;
				public String sAreaOfStudy;
				public String sProgramofInterest;
				public String sMilitaryType;
				public String sSpouseMilitary;
				public String sTCPA;
				public String sAttendedKaplanRadiobtn;
				public String sCountry;
				public String sDegree;
				public String sNurshing;
				
				//Static variable
				String sRandStr = RandomStringUtils.randomAlphabetic(5);
				public String sFirstName = "TestNGFNCR_" + sRandStr;
				public String sLastName = "TestNGLNCR_" + sRandStr;			
				public String sEmailAddress = sFirstName + "IC@kap.com";
				public String sPhone = "9545151234";
				public String Password ="Qwer1234$";
				
				
				
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
					sLeadType = objProperties.getProperty("sLeadType");
					sChannelGroup = objProperties.getProperty("sChannelGroup");
					sTCPA = objProperties.getProperty("sTCPADisclosure");
					sMilitaryType = objProperties.getProperty("sMilitaryType");
					sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
					sAreaOfStudy = objProperties.getProperty("sAreaOfStudy");
					sProgramofInterest = objProperties.getProperty("sProgramofInterest");
					sCountry = objProperties.getProperty("sCountry");
					sAttendedKaplanRadiobtn = objProperties.getProperty("sAttendedKaplanRadiobtn");
					sDegree = objProperties.getProperty("sDegree");
					sNurshing = objProperties.getProperty("sNurshing");
								
					//Edit Browser Capabilities as per project
					//Fire fox Profile		
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sSRM_OnlyUrl);
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
					driver.get(EnvironmentVariables.sSEP_Url);
					driver.manage().window().maximize();
					uiSEP_CreateAccount_PageObjects = new SEP_CreateAccount_PageObjects(driver);
					uiAddNewLeadsPageObjects = new AddNewLeadPageObjects(driver);
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
				public void CreateSEPAccount(Method objMethod) throws InterruptedException
				{
					
					uiSEP_CreateAccount_PageObjects = new SEP_CreateAccount_PageObjects(driver);
					WebDriverWait wait = new WebDriverWait(driver, 10000);
					WebElement element5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='hero-wrapper']/div/div[1]/div/ul/li[1]/span/div/div/div/p[3]/a/img")));
					uiSEP_CreateAccount_PageObjects.lblBeginApplication.click();
					UserExtension.IsElementPresent(driver, uiSEP_CreateAccount_PageObjects.rbnAttendedKaplanYes);
					
					//Radio Button Kaplan University Attended
					if(sAttendedKaplanRadiobtn.equalsIgnoreCase("yes"))
					{
						
						uiSEP_CreateAccount_PageObjects.rbnAttendedKaplanYes.click();
						
					}
					else
					{
						uiSEP_CreateAccount_PageObjects.rbnAttendedKaplanNo.click();
					}
					
					Select ddCountry = new Select(uiSEP_CreateAccount_PageObjects.ddCountry);
					ddCountry.selectByVisibleText(sCountry);
					
					Thread.sleep(10000);
					
					//First Name
					uiSEP_CreateAccount_PageObjects.txtFirstName.sendKeys(sFirstName);
					sFirstName1 =uiSEP_CreateAccount_PageObjects.txtFirstName.getAttribute("value");
					//Last Name
					uiSEP_CreateAccount_PageObjects.txtLastName.sendKeys(sLastName);
					sLastName1 =uiSEP_CreateAccount_PageObjects.txtLastName.getAttribute("value");
					
					//Primary Phone
					
					uiSEP_CreateAccount_PageObjects.txtPrimaryPhoneNo.sendKeys(sPhone);
					
					//Email Address
					
					uiSEP_CreateAccount_PageObjects.txtEmailAddress.sendKeys(sEmailAddress);
					sEmailAddress1 =uiSEP_CreateAccount_PageObjects.txtEmailAddress.getAttribute("value");
					System.out.println(sEmailAddress1);
					//Confirm Email Address
					uiSEP_CreateAccount_PageObjects.txtConfirmEmail.sendKeys(sEmailAddress);
					
					//Spouse Radio Button
				
					if(sSpouseMilitary.equalsIgnoreCase("yes"))
					{
						uiSEP_CreateAccount_PageObjects.rbtnSpouse_Yes.click();					
					}
					else
					{
						uiSEP_CreateAccount_PageObjects.rbtnSpouse_No.click();
					}
					
					//Degree Radio Button
					
					uiSEP_CreateAccount_PageObjects.rbtnDegreeGraduate.click();					
					
			
					//Nursing Program Radio Button
				
					if(sNurshing.equalsIgnoreCase("yes"))
					{
						uiSEP_CreateAccount_PageObjects.rbtnNurshing_Yes.click();					
					}
					else
					{
						uiSEP_CreateAccount_PageObjects.rbtnNurshing_No.click();
					}
					
					//Create Password
					
					uiSEP_CreateAccount_PageObjects.txtPassword.sendKeys(Password);
					
					//Confirm Password
					
					uiSEP_CreateAccount_PageObjects.txtConfirmPassword.sendKeys(Password);
					
					//Check Box Terms and Conditions
					
					//uiSEP_CreateAccount_PageObjects.checkBoxTCPA.click();
					
					//Click on Create Account Link
					
					
					uiSEP_CreateAccount_PageObjects.btnCreateAccount.click();
					
					//Account Creation Message
					
					//WebDriverWait wait = new WebDriverWait(driver, 100);
					
					WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='account-creation-modal']/div[1]")));
					
					UserExtension.IsElementPresent(driver, uiSEP_CreateAccount_PageObjects.txtAccountCreationMessage);
					
					//Click on Ok button
					
					uiSEP_CreateAccount_PageObjects.btnOK.click();
					
				}
				
				
				@Test(dependsOnMethods={"CreateSEPAccount"})
				public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
				{
					uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
					uiAddNewLeadsPageObjects = new AddNewLeadPageObjects(driver);
					uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
					driver.get(EnvironmentVariables.sSRM_Url);
					
					Thread.sleep(60000);
					
					 
					if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
					{
						
						
						
					}
					else
					{
						uiReusableMethods_PageObjects.lnkDropDown.click();
						Thread.sleep(30000);
						uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
						//uiReusableMethods_PageObjects.BackToKaplanSRM(driver);
						Thread.sleep(10000);
					}
					Thread.sleep(10000);
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.search_SRM);
					
					uiAddNewLeadsPageObjects.search_SRM.clear();
					uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
					WebDriverWait wait = new WebDriverWait(driver, 50000);
					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
					uiAddNewLeadsPageObjects.btnsearch_SRM.click();
					WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("secondSearchText")));
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					
					Thread.sleep(180000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					Thread.sleep(60000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					Thread.sleep(60000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					
					Thread.sleep(80000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					
					Thread.sleep(80000);
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_again.click();
					//WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3/span[contains(text(), 'Opportunities')]")));
					Thread.sleep(50000);
					
					//Assert.assertEquals(uiAddNewLeadsPageObjects.headingOppertunities.getText().trim(), "New");
					System.out.println(uiAddNewLeadsPageObjects.headingOppertunities.getText().trim());
					
					
					
							
				
				}			
				

	}
				

	

