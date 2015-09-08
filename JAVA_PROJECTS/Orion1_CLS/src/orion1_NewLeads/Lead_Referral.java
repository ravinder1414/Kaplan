package orion1_NewLeads;
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
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxProfile;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
	import orion1_NewLeadsPageObject.AddNewLeadPageObjects;
	import commonfunctions.BrowserManagement;
	import commonfunctions.ReportExtn;
	import commonfunctions.ScreenShotOnTestFailure;
	import commonfunctions.UserExtension;
	import environment.EnvironmentVariables;

	public class Lead_Referral {


			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					public String mainwinhandle;
					
					//Home Page Page Object Model
					//public HomePageObjects uiHomePageObjects;
					//public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
					public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
					
					
					//Variables from Properties file
					public String sLeadType;
					public String sChannelGroup;
					public String sAreaOfStudy;
					public String sProgramofInterest;
					public String sBechalorDegree;
					public String sSpouseMilitary;
					public String sSpouseMilitaryType;
					public String sTCPA;
					public String sHighestEduction;
					
					//Static variable
					String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
					public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
					public String sEmailAddress = sFirstName + "IC@kap.com";
					public String sAddressLine1 = "kaplan";
					public String sCity = "NewYork";
					public String sDayTimePhone ="9545151234";
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
						sLeadType = objProperties.getProperty("sLeadType");
						sChannelGroup = objProperties.getProperty("sChannelGroup");
						sTCPA = objProperties.getProperty("sTCPADisclosure");
						//sBechalorDegree = objProperties.getProperty("sBechalorDegree");
						sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
						sHighestEduction = objProperties.getProperty("sHighestEduction");
						sAreaOfStudy = objProperties.getProperty("sAreaOfStudy");
						sProgramofInterest = objProperties.getProperty("sProgramofInterest");
									
						//Edit Browser Capabilities as per project
						//Fire fox Profile		
						FirefoxProfile profile = new FirefoxProfile();
						profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_Orion1);
						
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
						driver.get(EnvironmentVariables.sUrl_Orion1);
						mainwinhandle = driver.getWindowHandle();
						driver.manage().window().maximize();
						driver.switchTo().frame("Orion");		
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
					public void BrowseToAddNewLeadPage(Method objMethod)
					{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						
						uiAddNewLeadsPageObjects.tabAdmissions.click();
						
						uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
						
						UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lblAddNewLeadReferral);
						
						uiAddNewLeadsPageObjects.lblAddNewLeadReferral.click();
						
					
						driver.switchTo().window(uiAddNewLeadsPageObjects.sAddNewLead_WindowName);

						Assert.assertEquals(uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"), "true", "Info Call Lead Type is not getting selected");
						uiAddNewLeadsPageObjects.rbtnLeadType_Referral.click();
						
					}
					
					@Test(dependsOnMethods={"BrowseToAddNewLeadPage"})
					public void Leads_Submit(Method objMethod)
					{

						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						
						uiAddNewLeadsPageObjects.txtFirstName.sendKeys(sFirstName);
						uiAddNewLeadsPageObjects.txtLastName.sendKeys(sLastName);
						uiAddNewLeadsPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
						
						uiAddNewLeadsPageObjects.txtDayTime.sendKeys(sDayTimePhone);
						
						uiAddNewLeadsPageObjects.txtZipCodeReferral.sendKeys(sZipCode);
						
				//TCPA Disclosure
				if(sTCPA.equalsIgnoreCase("yes"))
				{
					uiAddNewLeadsPageObjects.rtbnTCPA_Yes.click();					
				}
				else
				{
					uiAddNewLeadsPageObjects.rtbnTCPA_No.click();
				}
						//Spouse Military Status
						if(sSpouseMilitary.equalsIgnoreCase("yes"))
						{
							uiAddNewLeadsPageObjects.rbtnSpouse_Yes.click();					
						}
						else
						{
							uiAddNewLeadsPageObjects.rbtnSpouse_Yes.click();
						}
						
						Select ddHighestEducation = new Select(uiAddNewLeadsPageObjects.ddHighestEducation);
						
						ddHighestEducation.selectByVisibleText(sHighestEduction);
						
						uiAddNewLeadsPageObjects.btnAddALead.click();
						
													
					}
				
									
						
						@Test(dependsOnMethods={"Leads_Submit"})
						public void VerifyLeadInAdmisssionManager(Method objMethod)
						{
							
							driver.switchTo().window(mainwinhandle);
							driver.switchTo().frame("Orion");
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							uiAddNewLeadsPageObjects.tabAdmissions.click();
							
							uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
							
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")));
							UserExtension.WaitTillGetTextValueIs(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")), sLastName + ", " + sFirstName);
							
						}
						
			
					}			
					
