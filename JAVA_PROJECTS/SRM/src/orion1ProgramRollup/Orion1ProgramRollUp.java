package orion1ProgramRollup;

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
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class Orion1ProgramRollUp {
			
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
					
					public String programName;
					public String programEmphasis;
					public String parentWindow;
					public String programVersionToBeVerifiedOnRollUPPage;
					public String partialTextOfTopValueProgramVersion;
					public String stateCode="AK";
					
					
					
					
					
					
					
					
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
						profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sOrion1_URL);
						//Capability
						objBrowserMgr = new BrowserManagement(sBrowser);
						objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
							
						//Create the Remote Driver Instance
						try
						{					
							driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
					public void Orion1ProgramRollupNavigation(Method objMethod) throws InterruptedException
					{
						try
						{
						uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
						
						// Wait For Verify User Name Label present
						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.VerifyUserNameLabelPresent);
						
						
						// click on Program Maintenance button
						driver.switchTo().frame("Orion");
						uiOrion1ProgramRollupMappingPageObjects.programMaintenancehomepage.click();
						
						
						
						// Click on Program Rollup Maintenance button
						
						uiOrion1ProgramRollupMappingPageObjects.programRollupMaintenance.click();
						
						
						
						// Click on Program Rollup  button
						uiOrion1ProgramRollupMappingPageObjects.programRollupButton.click();
						
					
						
						// Click the first record of Program Rollup list
						uiOrion1ProgramRollupMappingPageObjects.programNametobeSelected.click();
						
						
						
						// Variables to be used further for verification
						programName=uiOrion1ProgramRollupMappingPageObjects.programNametobeSelected.getText();
						System.out.println("Program Name in First test"+" "+programName);
						
						programEmphasis=uiOrion1ProgramRollupMappingPageObjects.programEmphasis.getText();
						System.out.println("Program Emphasis in First test"+" "+programEmphasis);
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
							System.out.println(e.getStackTrace());
						}
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					// Function to Select Multiple values of Program Version based on index
					public static void selectTheDropDownListOfProgramVersion(WebElement dropDown,int index)
					{
					    Select select = new Select(dropDown);
					    select.selectByIndex(index);       
					    
					}
					
					// Function to Select Multiple values of Program Version based on text
					public static void selectTheDropDownListOfProgramVersion(WebElement dropDown,String text)
					{
					    Select select = new Select(dropDown);
					    select.selectByVisibleText(text); 
					    
					}
					
					
					
					// General Function to Select a value based on text
					public static void selectTheDropDownList(WebElement dropDown,String text)
					{
					    Select select = new Select(dropDown);
					    select.selectByVisibleText(text); 
					    
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					@Test(dependsOnMethods={"Orion1ProgramRollupNavigation"})
					public void VerifyDataOnMappingPage(Method objMethod) throws InterruptedException
					{
						
						try
						{
							
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
						
				parentWindow=driver.getWindowHandle();		
						
				driver.switchTo().window("ProgInfo");		
						
						
				
				uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);	
				
					
				// Program Mapping tab is present		
				UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.verifyProgramMappingTabPresent);
				
				
				// Click Program Mapping button
				uiOrion1ProgramRollupMappingPageObjects.programMapping.click();		
						
			
				// If there are more than two Program version ID in Box two then remove all of them leaving two only in box two
				
				// Get the count of all Program version ID in Box two
				Thread.sleep(5000);
				WebElement ProgramVersionBoxTwoForMultipleAdd2=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxTwo;

				List<WebElement> allValluesForMultipleAddBoxTwo2=ProgramVersionBoxTwoForMultipleAdd2.findElements(By.xpath(".//option"));
				
				
				int countOfBox2Values=allValluesForMultipleAddBoxTwo2.size();
				
				
				
				if(countOfBox2Values>2)
				{
					System.out.println("Yes Need to remove elements from box two");
					System.out.println("Total Valuie in Box 2 checking purposse"+" "+countOfBox2Values);
					
					for(int i=2;i<countOfBox2Values;i++)
					{
						System.out.println("In Removing loop");
						selectTheDropDownListOfProgramVersion(ProgramVersionBoxTwoForMultipleAdd2, i);
						
					}
					
					uiOrion1ProgramRollupMappingPageObjects.removeButton.click();
				}
				
				else
				{
					System.out.println("No need to remove any elemnt from box2");
					System.out.println("Total Valuie in Box 2 for checking purposse"+countOfBox2Values);
				}
				
				
				
		
				
			// Partial Text Search & verification
				
				Thread.sleep(2000);
			// Full Text of the top value of Program version	
			String fullTextOfTopValueProgramVersion=uiOrion1ProgramRollupMappingPageObjects.firstSelectedValueOfProgramVersion.getText();	
			
		
			// Getting the partial text
			int upperlimittoSubString=(fullTextOfTopValueProgramVersion.length()/6);
			
			System.out.println("Value of upperlimit after divison"+fullTextOfTopValueProgramVersion.length()/6);
			
			partialTextOfTopValueProgramVersion=fullTextOfTopValueProgramVersion.substring(0, upperlimittoSubString);
			
			System.out.println("Complete Text Of Program version"+fullTextOfTopValueProgramVersion);
			System.out.println("Partial Text Of Program version"+partialTextOfTopValueProgramVersion);
			
			
			// Type Partial Text in input box
			uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.clear();
			uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.sendKeys(partialTextOfTopValueProgramVersion);
			
				
			// To Check if partial text is present in all the program version now appearing	
			Thread.sleep(5000);	
			WebElement listOfProgramVersionBoxOne=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxOne;

			List<WebElement> allVallues=listOfProgramVersionBoxOne.findElements(By.xpath(".//option"));
			
			int countOfPartialTextItems=0;
			
			for(WebElement allv:allVallues)
			{
				
				
				System.out.println("Inside loop"+allv.getText());
				Assert.assertEquals(allv.getText().trim().contains(partialTextOfTopValueProgramVersion), true, uiOrion1ProgramRollupMappingPageObjects.partialTextNotPresentInStringText);
				countOfPartialTextItems++;
				
			}	
			Thread.sleep(5000);
			System.out.println("Total Number of partial Text items"+countOfPartialTextItems);
			
			
			Thread.sleep(5000);
			uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.clear();
			Thread.sleep(1000);	
			uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.sendKeys(" ");	
			Thread.sleep(15000);
				
				
			// Multiple add & verify
				
			
			WebElement ProgramVersionBoxOneForMultipleAdd=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxOne;

			List<WebElement> allValluesForMultipleAdd=ProgramVersionBoxOneForMultipleAdd.findElements(By.xpath(".//option"));
			
			// Get the count of all program versions
			
			int countOfProgramVersionForMultipleAdd=allValluesForMultipleAdd.size();
			
			System.out.println("Total count Of Program Version For Multiple Add"+countOfProgramVersionForMultipleAdd);
			
			int loopCount=(countOfProgramVersionForMultipleAdd/100);
			
			System.out.println("loop count or total items selected for multiple add"+loopCount);
			
			Thread.sleep(5000);
			
			for(int i=0;i<loopCount;i++)
			{
				
			selectTheDropDownListOfProgramVersion(ProgramVersionBoxOneForMultipleAdd,i);
			
			
			}
			
			// Store All the Selected options in a list
			
			// New List for All the selected values for Box One (called list one)
			
			Thread.sleep(5000);
			List<String> allSelectedValuesToVerifyForMultipleAdd=new ArrayList<String>();
			
			Select select = new Select(ProgramVersionBoxOneForMultipleAdd);
			List<WebElement> allSelectedElementsForMultipleAdd=select.getAllSelectedOptions();
			
			for(WebElement allValues:allSelectedElementsForMultipleAdd)

			{
				allSelectedValuesToVerifyForMultipleAdd.add(allValues.getText());
			}
			
			
			// Print items stored in list(for Box one) for multiple add
			Thread.sleep(5000);
			
			for(String a:allSelectedValuesToVerifyForMultipleAdd)
			{
				System.out.println("Program versions selected for multiple add in list(for Box one)"+a);
			}
			
			
			Thread.sleep(5000);
			
			// Add Button Click
			uiOrion1ProgramRollupMappingPageObjects.addButton.click();
			
			// Add Wait here
			
			
			// Verification for Multiple Added program versions
			
			
			// Store All options present in Box Two  After addition of multiple program versions
			Thread.sleep(5000);
			List<String> allProgramVersionValuesPresentInBoxTwo=new ArrayList<String>();
			
			WebElement ProgramVersionBoxTwoForMultipleAdd=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxTwo;

			List<WebElement> allValluesForMultipleAddBoxTwo=ProgramVersionBoxTwoForMultipleAdd.findElements(By.xpath(".//option"));
			
			for(WebElement allv:allValluesForMultipleAddBoxTwo)
			{
				// This is list two (Box two values)
				
				allProgramVersionValuesPresentInBoxTwo.add(allv.getText());
				
			}	
			
			// Print items stored in list(for Box two ) for multiple add
			Thread.sleep(5000);
			for(String a:allProgramVersionValuesPresentInBoxTwo)
			{
				
				System.out.println("Program versions selected for multiple add in list(for Box two) after addition "+a);
			}
			
			Thread.sleep(5000);
			
			// Click Save mappings
			
			uiOrion1ProgramRollupMappingPageObjects.saveMappingsButton.click();
			
			
			
			// Check List two (Box two values) contains the list One (Box One values)
			Thread.sleep(2000);
			Assert.assertEquals(allProgramVersionValuesPresentInBoxTwo.containsAll(allSelectedValuesToVerifyForMultipleAdd), true, uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxTwoText);
			
			
			
			
		
			
			
			
			
			
			
			// Multiple Remove & verify
			

			Thread.sleep(5000);
			WebElement ProgramVersionBoxTwoForMultipleRemove=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxTwo;
			
			//Again select All the added options
			for(String allVal:allSelectedValuesToVerifyForMultipleAdd)
			{
				
				selectTheDropDownListOfProgramVersion(ProgramVersionBoxTwoForMultipleRemove,allVal);
				System.out.println("Contents of removal programs "+allVal);
				
				
			}
			
			Thread.sleep(15000);
			// Click Remove button
			uiOrion1ProgramRollupMappingPageObjects.removeButton.click();
			
		
			uiOrion1ProgramRollupMappingPageObjects.saveMappingsButton.click();
			
			Thread.sleep(5000);
			// Add Wait here
			
			// Verify Box One has all the items added in it from Box two
			
			// Store All options present in Box One After removal from box two
			
						List<String> allValuesInBoxOneAfterRemoval=new ArrayList<String>();
						

						WebElement ProgramVersionBoxOneAfterMultipleRemove=uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxOne;

						List<WebElement> allValluesAfterMultipleRemoval=ProgramVersionBoxOneAfterMultipleRemove.findElements(By.xpath(".//option"));
						
						for(WebElement allv:allValluesAfterMultipleRemoval)
						{
							
							allValuesInBoxOneAfterRemoval.add(allv.getText());
							
						}	
						
						for(String a:allValuesInBoxOneAfterRemoval)
						{
							System.out.println("Program Version ID Values in Box one after removal"+a);
							
						}
			
					
						// Check whether list for Box One contains list of Removed items
						
						Thread.sleep(2000);
						Assert.assertEquals(allValuesInBoxOneAfterRemoval.containsAll(allSelectedValuesToVerifyForMultipleAdd), true, uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxOneAfterRemovalText);
						
							
			
			
			
			
			
				
				
			// Full Text Search
						
						Thread.sleep(1000);
						WebElement firstSelectedValueFullText=uiOrion1ProgramRollupMappingPageObjects.firstSelectedValueOfProgramVersion;
						
						System.out.println("First Selected value For Full Text Search"+firstSelectedValueFullText.getText());
						
						Thread.sleep(2000);
						
						uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.clear();
						
						Thread.sleep(2000);
						
						uiOrion1ProgramRollupMappingPageObjects.InputBoxofProgramVersion.sendKeys(firstSelectedValueFullText.getText());
						
						Thread.sleep(2000);
						
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxOne,firstSelectedValueFullText.getText());
						
						
						// Program Version to be verified at Roll up Page
						Thread.sleep(1000);
						programVersionToBeVerifiedOnRollUPPage=firstSelectedValueFullText.getText();
						
						Thread.sleep(2000);
						
						// click add
						
						uiOrion1ProgramRollupMappingPageObjects.addButton.click();
						
						
						uiOrion1ProgramRollupMappingPageObjects.saveMappingsButton.click();
						
					
						// To Check if Assign State Button is Enable		
						Thread.sleep(2000);
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.assignStateButton.isEnabled(), true, uiOrion1ProgramRollupMappingPageObjects.assignStateText);
						
						
						
						// To Verify that Assign State drop down list has all two character alphabets
						Thread.sleep(2000);
						Select assignStateDropdown=new Select(uiOrion1ProgramRollupMappingPageObjects.assignStateDropDownButton);
						
						Thread.sleep(2000);
						
						assignStateDropdown.selectByVisibleText("AR");
						
						Thread.sleep(2000);
						
						List <WebElement> assignStateDropdownValues=uiOrion1ProgramRollupMappingPageObjects.assignStateDropDownButton.findElements(By.xpath(".//option"));
						
						
						int count=0;
						
						for(WebElement dropDownValues : assignStateDropdownValues)
						{
							
							
							
							if(dropDownValues.getText().equalsIgnoreCase("None"))
							{
								System.out.println("PASS");
								System.out.println("Inside None Loop");
							}
							else
							{
								
								Assert.assertEquals(dropDownValues.getText().length(), 2, "The length of assign state code is not 2");
								System.out.println("Inside other Loop");
								System.out.println("Charter inside"+dropDownValues.getText());
								count++;
								
							}
						}
						
						System.out.println("Total count is "+count);
								
						
						
						
			// 	Assign code for the newly Added program version & save that code for verification in sales force
						
						Thread.sleep(5000);
						// Select the Newly Added program version 
						
					
						System.out.println("Text to be selected in Box two in assign state code frame"+programVersionToBeVerifiedOnRollUPPage);
						
						//selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.programVersionValuesBoxTwo,programVersionToBeVerifiedOnRollUPPage);
						
						// Assign State code
				Thread.sleep(5000);
						
						//selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.assignStateDropDownButton,stateCode);
						
				Select assignStateDropdown_1=new Select(uiOrion1ProgramRollupMappingPageObjects.assignStateDropDownButton);
				
				assignStateDropdown_1.selectByVisibleText(stateCode);
				
				Thread.sleep(5000);
				
				// Click Assign Code
				System.out.println("Before assign state click");
				uiOrion1ProgramRollupMappingPageObjects.assignStateButton.click();
				System.out.println("After assign state click");		
			// Click Save mappings	
			
				Thread.sleep(5000);
				System.out.println("Before Save Mapping");
				
						uiOrion1ProgramRollupMappingPageObjects.saveMappingsButton.click();
						
						System.out.println("After Save Mapping");	
						Thread.sleep(5000);
				
			// Close window
				
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
							System.out.println(e.getStackTrace());
						}
			
				
					}			
				
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				
						@Test(dependsOnMethods={"VerifyDataOnMappingPage"})
						public void VerifyInSalesForce(Method objMethod) throws InterruptedException
						{
						try
						{	
							
							
							
							/*
							 * 
							 Issues remaining in this @Test
							 1) xpath for program roll should be trimmed like ## & end terminal
							 2)  state code verify,date verify, program verify
							 
							 * 
							 */
							
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
						Thread.sleep(5000);
						driver.switchTo().window(parentWindow);
						Thread.sleep(2000);
	
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
							uiOrion1ProgramRollupMappingPageObjects = new Orion1ProgramRollupMappingPageObjects(driver);
							
							
						// Enter URL of sales force	for Verification
							
							driver.get(EnvironmentVariables.sSRM_Url);
							 
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
							
							// Use of NTLM etc
							
							
						// Click + icon button
							//Thread.sleep(15000);
							
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
							
						uiOrion1ProgramRollupMappingPageObjects.productsInputBox.sendKeys(programName);
						
						
						// Click Find Product link
						
						uiOrion1ProgramRollupMappingPageObjects.findProductsButton.click();
							
							
						// wait for page to load	
						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.moreFilterLink);
					
						
						// Click "More Filters" for advance search so that we could search with both program name & program emphasis
						
						uiOrion1ProgramRollupMappingPageObjects.moreFilterLink.click();
						
						// Enter details for more filter advance search
						
						
						// For First Row
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldFirstRow);
						
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldFirstRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldFirstRow);
						
						
						uiOrion1ProgramRollupMappingPageObjects.thirdFieldFirstRow.sendKeys(programEmphasis);
						
						// For Second Row
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.firstFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForFirstFieldSecondRow);
						
						selectTheDropDownList(uiOrion1ProgramRollupMappingPageObjects.secondFieldSecondRow,uiOrion1ProgramRollupMappingPageObjects.valueToEnterForSecondFieldSecondRow);
						
						Thread.sleep(2000);
						uiOrion1ProgramRollupMappingPageObjects.thirdFieldSecondRow.sendKeys(programName);
						
						
						uiOrion1ProgramRollupMappingPageObjects.searchButton.click();	
						
						uiOrion1ProgramRollupMappingPageObjects.programNameOnSalesforcePage.click();
							
						
						
						
						// Program version id trimming for verification "programVersionToBeVerifiedOnRollUPPage"
						
						Thread.sleep(5000);
						
						int lastIndexOfProgramVersionId=programVersionToBeVerifiedOnRollUPPage.lastIndexOf("-");
						
						System.out.println("last index of Program Version ID to be verified"+programVersionToBeVerifiedOnRollUPPage.lastIndexOf("-"));
						
						String finalStringOfProgramVersionIDToBeVerified=programVersionToBeVerifiedOnRollUPPage.substring(3, lastIndexOfProgramVersionId).trim();
						
						System.out.println("Final String of  Program Version ID to be verified on Rollup page"+finalStringOfProgramVersionIDToBeVerified);
						
						
						
						// Click Program Roll up
						driver.findElement(By.xpath("//a[text()="+"'" +finalStringOfProgramVersionIDToBeVerified+"'" +"]/parent::td/preceding-sibling::th/a")).click();
						
						
						
						
						// Verify data on Roll up-Version Junction Object Detail page
				
						// Verify Program Version ID
						
						// Get text of Program version ID from Screen
						System.out.println("Before Program version ID verification");
						
						String textOfProgramVersionIDOnRollUpPage=driver.findElement(By.xpath("//a[contains(text(),"+"'"+finalStringOfProgramVersionIDToBeVerified+"'"+")]")).getText();
						
						Assert.assertEquals(textOfProgramVersionIDOnRollUpPage.trim(), finalStringOfProgramVersionIDToBeVerified,uiOrion1ProgramRollupMappingPageObjects.programVersionIDForRollUpText );
						
						System.out.println("After Program version ID verification");
						// Verify State Code
						// Get text of State code from Screen
						
						System.out.println("Before state code verification");
						
					
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.stateCodeForRollUp.getText().trim(), stateCode,uiOrion1ProgramRollupMappingPageObjects.stateCodeForRollUpText);
						System.out.println("After state code verification");
						// Verify Current date
						
						// Get the current date in mm/dd/yyyy format
						Thread.sleep(2000);
						
						SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
						Date date = new Date();
						String dateToCompare = sdf.format(date);
						Thread.sleep(2000);
						System.out.println("Before date verification");
						System.out.println("Today's date for comparison after converting to proper format"+dateToCompare);
						System.out.println("date from screen "+uiOrion1ProgramRollupMappingPageObjects.dateFromRollUp.getText().trim());
						System.out.println("Before Assertion");
						Assert.assertEquals(uiOrion1ProgramRollupMappingPageObjects.dateFromRollUp.getText().trim().contains(dateToCompare), true, uiOrion1ProgramRollupMappingPageObjects.dateFromRollUpText);
						System.out.println("After date verification");
						
						
						
						
						
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
							System.out.println(e.getStackTrace());
						}
							
						}
				
				
				
				
				
				
				
				
					
		}
					

		




 