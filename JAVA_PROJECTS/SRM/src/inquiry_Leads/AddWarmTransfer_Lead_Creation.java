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
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		public class AddWarmTransfer_Lead_Creation {
			
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
					public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
					
					//Variables from Properties file
					 String sFirstName1;
				      String sLastName1;
				     String sEmailAddress1;
					public String sMilitaryType;
					public String sSpouseMilitary;
					public String sTCPA;
					public String sHighestEducation;
					
					//Static variable
					public String sPath_ResultProperties="";
					public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public static String sFirstName = "TestNGFNWT_" + sRandStr;
					public static String sLastName = "TestNGLNWT_" + sRandStr;			
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
						uiHomePageObjects = new HomePageObjects(driver);
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
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
							
							SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromAddWarmTransfer_Lead_Creation", sEmailAddress);
							
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
					public void BrowseToAddInfoCallLeadPage(Method objMethod) throws InterruptedException
					{
						try

						{

						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
						uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
	                     Thread.sleep(30000);
	                     
	                     uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
	 					
	     				
	 					WebDriverWait wait = new WebDriverWait(driver, 10000);
	 					
	 					
	 					driver.switchTo().frame("ext-comp-1005");
						
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.rbnWarmTransfer);
						//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:j_id33:3")));
						
						//Select Referral Radio Button
						uiInfoCallLeadPageObjects.rbnWarmTransfer.click();
						
						Thread.sleep(30000);
					
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.rbnInternet);
						
						uiInfoCallLeadPageObjects.rbnInternet.click();
						Thread.sleep(30000);
						//WebElement element8 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:prmotionid")));
						Thread.sleep(30000);
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddPromotional);
						Select ddlPromotionCode = new Select(uiInfoCallLeadPageObjects.ddPromotional);
						ddlPromotionCode.selectByIndex(2);
						
						//Select Area of Study
						Thread.sleep(30000);
						Select ddlAreaOfStudy = new Select(uiInfoCallLeadPageObjects.ddAreaofStudy);				
					
						ddlAreaOfStudy.selectByIndex(3);
						
						//Program of Interest
						
						//WebElement element6 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:progid")));
						Thread.sleep(10000);
						UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddProgramofInterest);
						Select ddlProgramOfInterest = new Select(uiInfoCallLeadPageObjects.ddProgramofInterest);
						
						ddlProgramOfInterest.selectByIndex(2);
						
						
						uiInfoCallLeadPageObjects.txtFirstName.sendKeys(sFirstName);
						uiInfoCallLeadPageObjects.txtLastName.sendKeys(sLastName);
						uiInfoCallLeadPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
						
						sEmailAddress1 =uiInfoCallLeadPageObjects.txtEmailAddress.getAttribute("value");
						
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
						
						Thread.sleep(30000);
						
						//WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:addALeadButtonId")));
						uiInfoCallLeadPageObjects.txtAddAnInquiry.click();
						
						Thread.sleep(30000);
						
						//WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:successmsgid")));
						
						Assert.assertEquals(uiInfoCallLeadPageObjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
						
						driver.navigate().refresh();
						
						uiReusableMethods_PageObjects.lnkDropDown.click();
						Thread.sleep(30000);
						uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
						//uiReusableMethods_PageObjects.BackToKaplanSRM(driver);
						Thread.sleep(10000);
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					}			
		
					
					
					@Test(dependsOnMethods={"BrowseToAddInfoCallLeadPage"})
					public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
					
					{try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						driver.get(EnvironmentVariables.sSRM_Url);
						uiAddNewLeadsPageObjects.search_SRM.clear();
						uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
						WebDriverWait wait = new WebDriverWait(driver, 5000);
						//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
							
						uiAddNewLeadsPageObjects.btnsearch_SRM.click();
						
						SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
						
						//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
						UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
						Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
						
						Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
					}catch (Exception e)
					{Reporter.log(e.getMessage());
						
					}
					}
		}
		
