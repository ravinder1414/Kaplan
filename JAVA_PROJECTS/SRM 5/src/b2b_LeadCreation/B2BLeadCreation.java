package b2b_LeadCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import orion3_Variables.EnvironmentVariables;
import uiMap_SRM5_PageObjects.B2BLeadPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		           public class B2BLeadCreation {
			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//B2B Home Page Page Object Model
					
					public B2BLeadPageObjects uiB2BLeadPageObjects;
					
					
					//Variables from Properties file
					public String sSpouseMilitary;
					public String sCommunityCode;
					public String sStudentDiscountCoupon;
					public String sTCPA;
					
					//Static variable
					String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public String sFirstName = "TestNGB2B_" + sRandStr;
					public String sLastName = "TestNGLNB2B_" + sRandStr;			
					public String sEmailAddress = sFirstName + "IC@kap.com";
					public String sDayPhone = "9545151234";
					public String sZipCode = "33309";
					
					
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
					
						sSpouseMilitary =objProperties.getProperty("sSpouseMilitary");
						sTCPA = objProperties.getProperty("sTCPADisclosure");
						sCommunityCode =objProperties.getProperty("sCommunityCode");
						sStudentDiscountCoupon =objProperties.getProperty("sStudentDiscountCoupon");
						
									
						//Edit Browser Capabilities as per project
						//Fire fox Profile		
						FirefoxProfile profile = new FirefoxProfile();
						profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sOrion3_B2B_Url);
						
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
						driver.get(EnvironmentVariables.sOrion3_B2B_Url);
						driver.manage().window().maximize();
						uiB2BLeadPageObjects = new B2BLeadPageObjects(driver);			
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
					public void BrowseToAddB2BNewLeadPage(Method objMethod)
					
					
					{
						//Event ID and category
						uiB2BLeadPageObjects = new B2BLeadPageObjects(driver);
						uiB2BLeadPageObjects.txtEventID.sendKeys("3");
						
						UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.ddEventCategory);
						Select ddlEventCategory = new Select(uiB2BLeadPageObjects.ddEventCategory);
						ddlEventCategory.selectByVisibleText("Corporate");	
						
						//Event Date
						
						Date dt = new Date();
				         String modifiedDate= new SimpleDateFormat("MMddYYYY").format(dt);
				         uiB2BLeadPageObjects.txtEventDate.sendKeys(modifiedDate);
				         
				         //Event Type
				         
				         UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.txtEventType);
				         
				     	UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.txtEventType);
						Select ddlEventType = new Select(uiB2BLeadPageObjects.txtEventType);
						ddlEventType.selectByVisibleText("Lunch and Learn");
						
						//Area of Study you interested
						
					     UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.ddAreaOfStudy);
				         
							Select ddlAreaOfStudy = new Select(uiB2BLeadPageObjects.ddAreaOfStudy);
							ddlAreaOfStudy.selectByVisibleText("Education");
							
							//Program you most interested
							
						     UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.ddProgram);
					         
								Select ddlProgram = new Select(uiB2BLeadPageObjects.ddProgram);
								ddlProgram.selectByVisibleText("MS in Education");
								
								//Details likes FirstName,Email etc
								
								uiB2BLeadPageObjects.txtFirstName.sendKeys(sFirstName);
								uiB2BLeadPageObjects.txtLastName.sendKeys(sLastName);
								
								   UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.txtEventType);
							         
							     	//State
							     	
							     	UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.txtState);
									Select ddltxtState = new Select(uiB2BLeadPageObjects.txtState);
									ddltxtState.selectByVisibleText("FL - Florida");
									
									//ZIP
									
									uiB2BLeadPageObjects.txtZIP.sendKeys(sZipCode);
									
									//Email Address
									
									uiB2BLeadPageObjects.txtEmail.sendKeys(sEmailAddress);
									
									//Country
									
									UserExtension.IsElementPresent(driver, uiB2BLeadPageObjects.ddCountry);
									Select ddlCountry= new Select(uiB2BLeadPageObjects.ddCountry);
									ddlCountry.selectByVisibleText("United States");
									
									//Employer
									
									uiB2BLeadPageObjects.txtEmployer.sendKeys("test");
									
									//Tuition Assistant Annual
									
									uiB2BLeadPageObjects.txtTutionAssistant.sendKeys("0");
								//Spouse Military Status
								if(sSpouseMilitary.equalsIgnoreCase("yes"))
								{
									uiB2BLeadPageObjects.rbnSpouse_Military_Yes.click();					
								}
								else
								{
									uiB2BLeadPageObjects.rbnSpouse_Military_No.click();
								}
												
								
								//Highest Degree Current Held
								Select ddlHightestDegree = new Select(uiB2BLeadPageObjects.ddHighestDegree);
								ddlHightestDegree.selectByVisibleText("AS");
								
								//Corporate Alliance Partner
								
								Select ddlCorporateAPPartner = new Select(uiB2BLeadPageObjects.ddCorporateAPPartner);
								ddlCorporateAPPartner.selectByVisibleText("Non Alliance");
								
								//Best Day of Week
								
								Select ddlBestDay = new Select(uiB2BLeadPageObjects.ddBestDay);
								ddlBestDay.selectByVisibleText("Monday");
								
								//Best Time to Contact
								
								Select ddlBestTime = new Select(uiB2BLeadPageObjects.ddBestTime);
								ddlBestTime.selectByVisibleText("2 pm");
								
								
								//Day Phone
								
								uiB2BLeadPageObjects.ddDayTimePhone.sendKeys(sDayPhone);
								
								//Radio Button Community College
								
								if(sCommunityCode.equalsIgnoreCase("yes"))
								{
									uiB2BLeadPageObjects.rbnCommunityCollege_Yes.click();					
								}
								else
								{
									uiB2BLeadPageObjects.rbnCommunityCollege_No.click();
								}
								
								//Radio Button Discount Coupon
								if(sStudentDiscountCoupon.equalsIgnoreCase("yes"))
								{
									uiB2BLeadPageObjects.rbnDiscounted_Yes.click();					
								}
								else
								{
									uiB2BLeadPageObjects.rbnDiscounted_No.click();
								}
								
								//Source Code
								
								Select ddlSourceCode= new Select(uiB2BLeadPageObjects.ddSourceCode);
								ddlSourceCode.selectByVisibleText("CHAT");
								
								//TCPA Disclosure
								if(sTCPA.equalsIgnoreCase("yes"))
								{
									uiB2BLeadPageObjects.rbnTCPADisclosure_Yes.click();					
								}
								else
								{
									uiB2BLeadPageObjects.rbnTCPADisclosure_No.click();
								}
								
								
								uiB2BLeadPageObjects.btnSubmit.click();
								
								//Checking created Lead Message
								
								UserExtension.WaitTillGetTextValueIs(driver, uiB2BLeadPageObjects.txtLeadCreatedMessage, "Lead Successfully Added");
								Assert.assertEquals(uiB2BLeadPageObjects.txtLeadCreatedMessage.getText().trim(), "Lead Successfully Added");
							}
								
		}


