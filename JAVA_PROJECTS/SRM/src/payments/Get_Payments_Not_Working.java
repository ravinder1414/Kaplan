package payments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;









import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reusableMethods_PageObject.ReusableMethods_PageObjects;
import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.GetPaymentsNotWorkingPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.Orion1ProgramRollupMappingPageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		public class Get_Payments_Not_Working {
			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//Home Page Page Object Model
					public HomePageObjects uiHomePageObjects;
					public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
					public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
					public GetPaymentsNotWorkingPageObjects uiGetPaymentsNotWorkingPageObjects;
					public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
					public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
					
					
					
					

					
					
					//Variables from Properties file for Searching Student & Opportunity for Get Payments
					 String StudentForGetPayments;
				     String OpportunityForGetPayments;
				     String AmountToVerifyForGetPayments;
				     public String parentWindow;
				     
					
					
					
					
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
						StudentForGetPayments = objProperties.getProperty("StudentForGetPayments");
						OpportunityForGetPayments = objProperties.getProperty("OpportunityForGetPayments");
						AmountToVerifyForGetPayments = objProperties.getProperty("AmountToVerifyForGetPayments");
						
						
									
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
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
						}
						catch(Exception ex)
						{	
							ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
						}
						driver.get(EnvironmentVariables.sSRM_Url);
						driver.manage().window().maximize();

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
					public void verify_Payments(Method objMethod) throws InterruptedException
					{
						try
						{
							
						// Implicit wait	
							
						driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
							
						uiGetPaymentsNotWorkingPageObjects = new GetPaymentsNotWorkingPageObjects(driver);
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
						
						
						// mOVE TO kAPLAN srm pAGE IF NOT PRESENT
						 
						if(uiAddInquiry_Referral_Lead_Pageobjects.lnkDropDown.getText().equalsIgnoreCase("Kaplan SRM"))
						{
							
							
							
						}
						else
						{
							uiReusableMethods_PageObjects.lnkDropDown.click();
							Thread.sleep(30000);
							uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
							
							Thread.sleep(10000);
						}
						
						
						Thread.sleep(5000);
						System.out.println("Values from propert file "+StudentForGetPayments+" "+OpportunityForGetPayments);
						
						// Type the student name in search input box
						uiGetPaymentsNotWorkingPageObjects.searchInputTextBox.sendKeys(StudentForGetPayments);
						
						// Click Search Button
						uiGetPaymentsNotWorkingPageObjects.searchButton.click();
						
						// Click opportunity button 
						driver.findElement(By.xpath("//a[contains(text(),"+"'" +OpportunityForGetPayments+"'" +")]")).click();
						
						// Click Recent Payments button
						uiGetPaymentsNotWorkingPageObjects.recentPaymentsButton.click();
						
						
						// Switch To Another Window
						
						Thread.sleep(10000);
						//Window Handles
						String parentHandle = driver.getWindowHandle();
						Set <String> winHandles = driver.getWindowHandles();
						for (String currentWindowHandle : winHandles) 
						{
						if (!currentWindowHandle.equals(parentHandle)) 
							{
						driver.switchTo().window(currentWindowHandle);
						System.out.println("Current Window"+currentWindowHandle);
							}
						
						}
						
						System.out.println("Parent Window"+parentHandle);
						
						
					/*	parentWindow=driver.getWindowHandle();		
								
						driver.switchTo().window("006i000000PT6az");		
						*/
						
						// Verify On Payments Window
						
						String amountOnVerifyPage=uiGetPaymentsNotWorkingPageObjects.paymentAmountVerify.getText();		
						System.out.println("Amount on Verfiy Page is"+amountOnVerifyPage);
						
						System.out.println("Before Final Assert");
						Assert.assertEquals(amountOnVerifyPage, AmountToVerifyForGetPayments, uiGetPaymentsNotWorkingPageObjects.paymentAmountVerifyText);
						System.out.println("After Final Assert");
						
						
						
						
						
						
					
						}
						
						catch (Exception e)
																	
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					
					}		
					
					
					
				
					
		}
					

		




 