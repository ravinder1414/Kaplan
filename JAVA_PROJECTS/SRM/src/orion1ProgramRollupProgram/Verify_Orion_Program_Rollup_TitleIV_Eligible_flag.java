package orion1ProgramRollupProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.Orion1ProgramRollupMappingPageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.QueryDB;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		public class Verify_Orion_Program_Rollup_TitleIV_Eligible_flag {
			
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
					public Orion1ProgramRollupMappingPageObjects uiOrion1ProgramRollupMappingPageObjects;
					
					
					
					
					
					// Global Variable for Wait
//					WebDriverWait wait = new WebDriverWait(driver, 10000);
//					WebElement element;
					
					// Global Variable for Program Name & Program Emphasis
					public static String sRandStri = RandomStringUtils.randomAlphabetic(4);
					public String programName;
					public String programEmphasis;
					public String parentWindow;
					public String programVersionToBeVerifiedOnRollUPPage;
					public String partialTextOfTopValueProgramVersion;
					public static String programNameToBeSelected=sRandStri+" "+"AAS in Account";
					public static String programNameToBeSelectedText=programNameToBeSelected.toUpperCase();
					public static String editedProgramNameToBeSelectedText="edit"+programNameToBeSelectedText;
					public String TitleIVEligibleFlagFromDB ;
					
					
					
					
					
					
					
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
						driver.get(EnvironmentVariables.sOrion1_URL);
						driver.manage().window().maximize();
						uiSEP_CreateAccount_PageObjects = new SEP_CreateAccount_PageObjects(driver);
						uiAddNewLeadsPageObjects = new AddNewLeadPageObjects(driver);
						uiHomePageObjects = new HomePageObjects(driver);	
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

					
					
					// General Function to Select a value based on text
					public static void selectTheDropDownList(WebElement dropDown,String text)
					{
						try
						{
					    Select select = new Select(dropDown);
					    select.selectByVisibleText(text); 
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
					    
					}
					
					
					
					
					
					
					
					//This method will fetch the TitleIV Eligible Flag from DB & will do the verification
					@Test
					public void FetchTitleIVEligibleFlagfromDB(Method objMethod){
						//QUERY String to fetch the TitleIV Eligible Flag from DB
						String qString="Select top 1 * from Orion.dbo.mkProgramRollup order by 1 desc";
						System.out.println("Inside DB Test");
						//Call getDBQueryResult function in CommonLibrary project   
						try {
							System.out.println("Inside DB try catch Test");
							 ResultSet rs = QueryDB.getDBQueryResult(qString, EnvironmentVariables.sConnString);
							 System.out.println("After DBQuery Method in Orion Program");
							 rs.next();
							 //System.out.println("Resultset in Orion Method"+rs.getString(2));
							 TitleIVEligibleFlagFromDB=rs.getMetaData().getColumnLabel(12);
							 System.out.println("After fetching from DB the value of TitleIVEligibleFlag is: "+TitleIVEligibleFlagFromDB);
							//Write DB values in Reporter log
							 Reporter.log(TitleIVEligibleFlagFromDB);
							 
							 Assert.assertEquals(TitleIVEligibleFlagFromDB, "IsTitleIVEligible", "TitleIVEligible Field is not present in DB");
							 
						
							 
						} catch (Exception e) {
							Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						}
						
						
					}
					
					
					
					
					
								
					
					
					@Test(dependsOnMethods={"FetchTitleIVEligibleFlagfromDB"})
					public void Title_IV_Eligible_Field_Enable_And_Options_Present(Method objMethod) throws InterruptedException
					{
						try
						{
							
						// Implicit wait	
							
						driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							
						uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
						
						
						
						// click on Program Maintenance button
						driver.switchTo().frame("Orion");
						uiOrion1ProgramRollupMappingPageObjects.programMaintenancehomepage.click();
						
						
						
						// Click on Program Rollup Maintenance button
						
						
						uiOrion1ProgramRollupMappingPageObjects.programRollupMaintenance.click();
						
						
						
						// Click on Program Rollup  button
						uiOrion1ProgramRollupMappingPageObjects.programRollupButton.click();
						
					
						// Title IV Eligible field is enabled and present on the page
						System.out.println("Before Assert Title IV Eligible field is enabled and present on the page");
						Thread.sleep(1000);
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton.isDisplayed(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButtonPresence);
						System.out.println("After Assert Title IV Eligible field is enabled and present on the page");
						Thread.sleep(1000);
						
						//uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton.click();
						
						
						System.out.println("Before Assert");
						Thread.sleep(1000);
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton.isEnabled(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButtonStatus);
						System.out.println("After Assert");
						Thread.sleep(1000);
				
						
						
						// Title IV Eligible field should have "All", "Yes" and "No" values to be selected
						
						
						List <String> toBeCompared=new ArrayList<String>();
						toBeCompared.add("Yes");
						toBeCompared.add("No");
						toBeCompared.add("All");
						
						List <String> valuesOfTitleIVFalg=new ArrayList<String>();
						
						List<WebElement> flagValues=uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton.findElements(By.xpath(".//option"));
						
						for(WebElement values:flagValues)
						{
							
							valuesOfTitleIVFalg.add(values.getText());
							System.out.println("iNSIDE LOOP");
							System.out.println(values.getText());
							
						}
						
						
						
						System.out.println("Flag Button Values"+valuesOfTitleIVFalg.size());
						
						
						
						System.out.println("Before Assert Title IV Eligible field have All, Yes and No values");
						Thread.sleep(1000);
						Assert.assertEquals(valuesOfTitleIVFalg.containsAll(toBeCompared), true, "Title IV Eligible field does not have All, Yes and No values to be selected");
						System.out.println("After Assert Title IV Eligible field have All, Yes and No values");
						Thread.sleep(1000);
						
						
						
						
						
						
						
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					}
					
					
					
					
					
					
					
					
					
					

					@Test(dependsOnMethods={"Title_IV_Eligible_Field_Enable_And_Options_Present"})
					public void Execute_Search_And_Verify (Method objMethod) throws InterruptedException
					{
						try
						{	
						
							
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							
							Thread.sleep(5000);
							
							// Title IV Eligible" field should also display under search results grid
							System.out.println("Before Assert Title IV Eligible field should also display under search results grid");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagingridSearch.isDisplayed(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagingridSearchText);
							System.out.println("After Assert Title IV Eligible field should also display under search results grid");
							Thread.sleep(1000);
							
							
							
							// Select Yes & Verify
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton,"Yes");
							Thread.sleep(1000);
							
							// Click Search
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.searchButtonProgramADDNewRollupPage.click();
							Thread.sleep(5000);
							
							// click edit
							uiOrion1ProgramRollupMappingPageObjects.editButtonTitleIVEligbleFlag.click();
							
							Thread.sleep(5000);
							//Verify After Search TitleIV Eligible is selected
							
							System.out.println("Before Assert Yes");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerification.isSelected(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerificationText);
							System.out.println("After Assert Yes");
							Thread.sleep(1000);
					
							
							
							
							
							
							
							
							
							
							
							// Select No & Verify
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton,"No");
							Thread.sleep(2000);
							
							// Click Search
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.searchButtonProgramADDNewRollupPage.click();
							Thread.sleep(5000);
							
							// click edit
							uiOrion1ProgramRollupMappingPageObjects.editButtonTitleIVEligbleFlag.click();
							
							Thread.sleep(5000);
							//Verify After Search TitleIV Eligible is selected
							
							System.out.println("Before Assert No");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerification.isSelected(), false, uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerificationTextforNo);
							System.out.println("After Assert No");
							Thread.sleep(1000);
							
							
							
							// Select All & Verify
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleDropdownButton,"All");
							Thread.sleep(2000);
							
							// Click Search
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.searchButtonProgramADDNewRollupPage.click();
							Thread.sleep(5000);
							
							
							// Sort TitleIV Eligible button
							
							uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagingridSearch.click();
							Thread.sleep(1000);

							// Verify for unselected TitleIV Eligible buton
							
							// click edit
							uiOrion1ProgramRollupMappingPageObjects.editButtonTitleIVEligbleFlag.click();
							
							Thread.sleep(5000);
							//Verify After Search TitleIV Eligible is selected
							
							System.out.println("Before Assert No");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerification.isSelected(), false, uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerificationTextforNo);
							System.out.println("After Assert No");
							Thread.sleep(1000);
							
							
							
							Thread.sleep(5000);
							
							
							// Again do sorting for Selected one
							
							
							uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagingridSearch.click();
							Thread.sleep(1000);

							// Verify for unselected TitleIV Eligible buton
							
							// click edit
							uiOrion1ProgramRollupMappingPageObjects.editButtonTitleIVEligbleFlag.click();
							
							Thread.sleep(5000);
							//Verify After Search TitleIV Eligible is selected
							
							System.out.println("Before Assert No");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerification.isSelected(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligbleIVFlagafterSearchforVerificationTextforNo);
							System.out.println("After Assert No");
							Thread.sleep(1000);
							
							
							
							
							
							
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					}
					
					
					
					
					
					
					
					
					
					
					
					@Test(dependsOnMethods={"Execute_Search_And_Verify"})
					public void Verify_ON_Program_Maintenance_Popup_Window (Method objMethod) throws InterruptedException
					{
						try
						{	
						
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);	
							Thread.sleep(5000);
							
							// Click On Any Program to Navigate to another window
							// Click the first record of Program Rollup list
							uiOrion1ProgramRollupMappingPageObjects.programNametobeSelected.click();
							
							
							
							
							parentWindow=driver.getWindowHandle();		
									
							driver.switchTo().window("ProgInfo");		
									
									
							
							
							
							Thread.sleep(5000);
							// Program Mapping tab is present		
							UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.verifyProgramMappingTabPresent);
							
							
							// Click Program Rollup button
							uiOrion1ProgramRollupMappingPageObjects.programRollupButtonOnMappingWindow.click();		
									
							// Verify that the "Title IV Eligible" check box is enabled and present on the Program Maintenance 
							
							System.out.println("Before Assert Final");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleCheckBox.isEnabled(), true, uiOrion1ProgramRollupMappingPageObjects.titleIVEligibleCheckBoxText);
							System.out.println("After Assert Final");
							Thread.sleep(1000);
					
							
							
							
							
					
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						
						
						
						}
					
					
					
					
			
				
					
		}
					

		




 