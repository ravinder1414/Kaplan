package orion1ProgramRollupProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
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
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.Orion1ProgramRollupMappingPageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

		public class Add_And_Edit_new_program_rollup_Orion_Salesforce {
			
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
					
					
					
					
					
					//Global Variable for Wait
//					WebDriverWait wait = new WebDriverWait(driver, 10000);
//					WebElement element;
					
					// Global Variable for Program Name & Program Emphasis
					public String sPath_ResultProperties="";
					public static String sRandStri = RandomStringUtils.randomAlphabetic(4);
					public String programName;
					public String programEmphasis;
					public String parentWindow;
					public String programVersionToBeVerifiedOnRollUPPage;
					public String partialTextOfTopValueProgramVersion;
					public static String programNameToBeSelected=sRandStri+" "+"AAS in Account";
					public static String programNameToBeSelectedText=programNameToBeSelected.toUpperCase();
					public static String editedProgramNameToBeSelectedText="edit"+programNameToBeSelectedText;
					public static String programEmphasisNameFromPropertyFile;
					
					
					
					
					
					
					
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
					public String ProgramGroupNameFromScript;
					public String ProgramRollupSchoolNameFromScript;
					public String ProgramEmphasisNameFromScript;
					
					
					
					
					
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
						ProgramGroupNameFromScript = objProperties.getProperty("ProgramGroupNameFromScript");
						ProgramRollupSchoolNameFromScript = objProperties.getProperty("ProgramRollupSchoolNameFromScript");
						ProgramEmphasisNameFromScript = objProperties.getProperty("ProgramEmphasisNameFromScript");
									
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

							// Writing to Result Property File

							System.out.println("Inside After class");
							
							System.out.println("Before method writing to Result Property file");
							
							SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "editedProgramNameToBeSelectedTextFromAdd_And_Edit_new_program_rollup_Orion_Salesforce", editedProgramNameToBeSelectedText);
							
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
					
					
					
					
					
								
					
					
					@Test
					public void Orion1ProgramRollupAddNewProgram(Method objMethod) throws InterruptedException
					{
						try
						{
							
						// Implicit wait	
							
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							
						uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
						
						
						
						// click on Program Maintenance button
						driver.switchTo().frame("Orion");
						uiOrion1ProgramRollupMappingPageObjects.programMaintenancehomepage.click();
						
						
						
						// Click on Program Rollup Maintenance button
						
						
						uiOrion1ProgramRollupMappingPageObjects.programRollupMaintenance.click();
						
						
						
						// Click on Program Rollup  button
						uiOrion1ProgramRollupMappingPageObjects.programRollupButton.click();
						
					
						
						// Click Add new Program Rollup
						
						uiOrion1ProgramRollupMappingPageObjects.addNewButtonForProgramRollup.click();
						
						
						// Enter details for Adding new Program Roll up
						
						// Select Program Group
						// Get The Program Group Name from Property file
						String programGroupNameFromPropertyFile=ProgramGroupNameFromScript+" "+"("+ProgramRollupSchoolNameFromScript+")";
						System.out.println("Program Group to be selected"+programGroupNameFromPropertyFile);
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.programGroupDropDownButton,programGroupNameFromPropertyFile);
						
						//selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.programGroupDropDownButton,uiOrion1ProgramRollupMappingPageObjects.programGroupToBeSelectedText);
						
						
						// Type Program Name
						Thread.sleep(1000);
						uiOrion1ProgramRollupMappingPageObjects.programNameButton.sendKeys(programNameToBeSelectedText);
						
						
						// Select Program Emphasis
						Thread.sleep(1000);
						// Get The Program Emphasis Name from Property file
						programEmphasisNameFromPropertyFile=ProgramEmphasisNameFromScript;
						System.out.println("Program Emphasis to be selected"+programEmphasisNameFromPropertyFile);

						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.programEmphasisButton,programEmphasisNameFromPropertyFile);
						
						// Click update button
						Thread.sleep(2000);
						uiOrion1ProgramRollupMappingPageObjects.updateButtonForProgramRollup.click();
						
						System.out.println("Program Roll up Name just created"+" "+programNameToBeSelectedText);
						
						// Verification for Update Successfully message
						System.out.println("Before Assert");
						Thread.sleep(1000);
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.updateSuccessfulMessageForProgramRollup.getText(), uiOrion1ProgramRollupMappingPageObjects.updateSuccessfulMessageForProgramRollupText, "Insertion of Program Roll up is not correct");
						System.out.println("After Assert");
						
						
						
						
						
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						
					}
					
					
					
					
					
					
					
					
					
					

					@Test(dependsOnMethods={"Orion1ProgramRollupAddNewProgram"})
					public void verifyInOrion(Method objMethod) throws InterruptedException
					{
						try
						{	
						
							
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							
							// Search for the program just created
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.programNameButtonForSearch.sendKeys(programNameToBeSelectedText);
							
							// Click Search
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.searchButtonProgramADDNewRollupPage.click();
							Thread.sleep(5000);
							// Verify Program name After Search
							
							String textOfProgramNameToBeVerified=driver.findElement(By.xpath("//a[contains(text(),"+"'" +programNameToBeSelectedText+"'" +")]")).getText();
							
							System.out.println("Before Assert");
							Thread.sleep(2000);
							Assert.assertEquals(textOfProgramNameToBeVerified, programNameToBeSelectedText, "Program Name is not correct after Search");
							System.out.println("After Assert");
							Thread.sleep(1000);
					
					
					
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					}
					
					
					
					
					
					
					
					
					
					
					
					@Test(dependsOnMethods={"verifyInOrion"})
					public void verifyInSalesforce(Method objMethod) throws InterruptedException
					{
						try
						{	
						
						
							
							// Enter URL of sales force	for Verification
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
								
								Thread.sleep(2000);
								driver.get(EnvironmentVariables.sSRM_Url);
								driver.manage().window().maximize();
								
								driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
								 
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
								
								
								Thread.sleep(15000);
								
								
								
							
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.menuBarExtensionPlusSign);
								
								uiOrion1ProgramRollupMappingPageObjects.menuBarExtensionPlusSign.click();
								//driver.findElement(By.xpath("//img[@alt='All Tabs']")).click();
								
							// wait for page to load
								
								
							// Click product	
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsLink);
								uiOrion1ProgramRollupMappingPageObjects.productsLink.click();
								
							// wait for page to load	
								
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsInputBox);
								
							// Type in input box 	
								
							uiOrion1ProgramRollupMappingPageObjects.productsInputBox.sendKeys(programNameToBeSelectedText);
							
							
							// Click Find Product link
							
							uiOrion1ProgramRollupMappingPageObjects.findProductsButton.click();
								
								
							// wait for page to load	
							UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.moreFilterLink);
						
							
							// Click "More Filters" for advance search so that we could search with both program name & program emphasis
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.moreFilterLink.click();
							
							// Enter details for more filter advance search
							
							
							// For First Row
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldFirstRow);
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldFirstRow);
							
							
							uiOrion1ProgramRollupMappingPageObjects.thirdFieldFirstRow.sendKeys(programEmphasisNameFromPropertyFile);
							
							// For Second Row
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldSecondRow);
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldSecondRow);
							
							Thread.sleep(2000);
							uiOrion1ProgramRollupMappingPageObjects.thirdFieldSecondRow.sendKeys(programNameToBeSelectedText);
							
							
							uiOrion1ProgramRollupMappingPageObjects.searchButton.click();	
							
							Thread.sleep(2000);
							
							System.out.println("Before Click the program Name");
							driver.findElement(By.xpath("//a[contains(text(),"+"'" +programNameToBeSelectedText+"'" +")]")).click();
							System.out.println("After Click the program Name");
							
							// Verify The Program name Finally 
							
							
							String finalVerifyProgramName=driver.findElement(By.xpath("//td[@id='Name_ilecell']")).getText();
							
							System.out.println("Before Verification Of Program name Finally");
							
							Thread.sleep(2000);
							Assert.assertEquals(finalVerifyProgramName, programNameToBeSelectedText, "Program Name is not correct on Final Page");
						
							
							System.out.println("Afetr Verification Of Program name Finally");
							
					
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						
						
						
						}
					
					
					
					
					
					
					@Test(dependsOnMethods={"verifyInSalesforce"})
					public void editInOrion(Method objMethod) throws InterruptedException
					{
						try
						{	
						
							
							driver.get(EnvironmentVariables.sOrion1_URL);
							driver.manage().window().maximize();
						
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
							
							// click on Program Maintenance button
							driver.switchTo().frame("Orion");
							uiOrion1ProgramRollupMappingPageObjects.programMaintenancehomepage.click();
							
							
							
							// Click on Program Rollup Maintenance button
							
							uiOrion1ProgramRollupMappingPageObjects.programRollupMaintenance.click();
							
							
							
							// Click on Program Rollup  button
							uiOrion1ProgramRollupMappingPageObjects.programRollupButton.click();
							
							
							// Search for the Program Rollup just crested
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.programNameButtonForSearch.sendKeys(programNameToBeSelectedText);
							
							// Click Search
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.searchButtonProgramADDNewRollupPage.click();
							Thread.sleep(5000);
							
							// Verify Program name After Search
							String textOfProgramNameToBeVerified=driver.findElement(By.xpath("//a[contains(text(),"+"'" +programNameToBeSelectedText+"'" +")]")).getText();
							
							System.out.println("Before Assert");
							Thread.sleep(2000);
							Assert.assertEquals(textOfProgramNameToBeVerified, programNameToBeSelectedText, "Program Name is not correct after Search(For Editing purpose)");
							System.out.println("After Assert");
							Thread.sleep(1000);
							
							
							// Click Edit 
							uiOrion1ProgramRollupMappingPageObjects.editButtonProgramADDNewRollupPage.click();
							
							
							// Type the edited program Rollup name
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.programNameButton.clear();
							uiOrion1ProgramRollupMappingPageObjects.programNameButton.sendKeys(editedProgramNameToBeSelectedText);
							
							// Click update button
							Thread.sleep(2000);
							uiOrion1ProgramRollupMappingPageObjects.updateButtonForProgramRollup.click();
							
							System.out.println("Program Roll up Name just edited"+" "+editedProgramNameToBeSelectedText);
							
							// Verification for Update Successfully message
							System.out.println("Before Assert");
							Thread.sleep(1000);
							Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.updateSuccessfulMessageForProgramRollup.getText(), uiOrion1ProgramRollupMappingPageObjects.updateSuccessfulMessageForProgramRollupAfterEditText, "Edit of Program Roll up is not correct");
							System.out.println("After Assert");
							
							
							
							
							
					
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						}
			
			
					
					
					
					
					
					
					@Test(dependsOnMethods={"editInOrion"})
					public void verifyEditedInSalesForce(Method objMethod) throws InterruptedException
					{
						try
						{	
						
						
							
							// Enter URL of sales force	for Verification
							uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
							uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
								
								Thread.sleep(2000);
								driver.get(EnvironmentVariables.sSRM_Url);
								driver.manage().window().maximize();
								
								driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
								 
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
								
								
								Thread.sleep(15000);
								
								
								
							
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.menuBarExtensionPlusSign);
								
								uiOrion1ProgramRollupMappingPageObjects.menuBarExtensionPlusSign.click();
								//driver.findElement(By.xpath("//img[@alt='All Tabs']")).click();
								
							// wait for page to load
								
								
							// Click product	
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsLink);
								uiOrion1ProgramRollupMappingPageObjects.productsLink.click();
								
							// wait for page to load	
								
								UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsInputBox);
								
							// Type in input box 	
								
							uiOrion1ProgramRollupMappingPageObjects.productsInputBox.sendKeys(editedProgramNameToBeSelectedText);
							
							
							// Click Find Product link
							
							uiOrion1ProgramRollupMappingPageObjects.findProductsButton.click();
								
								
							// wait for page to load	
							UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.moreFilterLink);
						
							
							// Click "More Filters" for advance search so that we could search with both program name & program emphasis
							Thread.sleep(1000);
							uiOrion1ProgramRollupMappingPageObjects.moreFilterLink.click();
							
							// Enter details for more filter advance search
							
							
							// For First Row
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldFirstRow);
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldFirstRow);
							
							
							uiOrion1ProgramRollupMappingPageObjects.thirdFieldFirstRow.sendKeys(programEmphasisNameFromPropertyFile);
							
							// For Second Row
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldSecondRow);
							
							selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldSecondRow);
							
							Thread.sleep(2000);
							uiOrion1ProgramRollupMappingPageObjects.thirdFieldSecondRow.sendKeys(editedProgramNameToBeSelectedText);
							
							
							uiOrion1ProgramRollupMappingPageObjects.searchButton.click();	
							
							Thread.sleep(2000);
							
							System.out.println("Before Click the program Name");
							driver.findElement(By.xpath("//a[contains(text(),"+"'" +editedProgramNameToBeSelectedText+"'" +")]")).click();
							System.out.println("After Click the program Name");
							
							// Verify The Program name Finally 
							
							
							String finalVerifyProgramName=driver.findElement(By.xpath("//td[@id='Name_ilecell']")).getText();
							
							System.out.println("Before Verification Of Program name Finally");
							
							Thread.sleep(2000);
							Assert.assertEquals(finalVerifyProgramName, editedProgramNameToBeSelectedText, "Program Name is not correct on Final Page");
						
							
							System.out.println("After Verification Of Program name Finally");
							
					
						}
						catch (Exception e)
												
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
						
						}
					
					
					
			
				
					
		}
					

		




 