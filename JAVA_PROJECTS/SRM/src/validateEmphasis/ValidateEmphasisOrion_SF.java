package validateEmphasis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion1.ProgramGroupPageObjects;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.Orion1ProgramRollupMappingPageObjects;
import uiMap_Orion3_SRM.SEP_CreateAccount_PageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import uiMap_Orion3_SRM.ValidateCollege_InformationPageObjects;
import uiMap_Orion3_SRM.ValidateEmphasisPageObject;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class ValidateEmphasisOrion_SF {
			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//Variables from Properties file
					 //String sEmphasisName;
				     
					
					//Static variable
					public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public static String sEmphasisName = "TestEmphasis" + sRandStr;
					
					public static String sEmphasisName1 = "TestEmphasisUpdated" + sRandStr;
					
					//Home Page Page Object Model
					public HomePageObjects uiHomePageObjects;
					public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
					public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
					public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
					public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
					public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
					public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
					public ValidateCollege_InformationPageObjects uiValidateCollege_Information;
					public SEP_CreateAccount_PageObjects uiSEP_CreateAccount_PageObjects;
					public ValidateEmphasisPageObject uiValidateEmphasisPageObject;
					public ProgramGroupPageObjects uiProgramGroupPageObjects;
					public Orion1ProgramRollupMappingPageObjects uiOrion1ProgramRollupMappingPageObjects;
					
					
					
					
					
					
					
					
					
					
					
					public String sPath_ResultProperties="";
					public String sPath_AppProperties="";
					public FileOutputStream objFileOutputStream=null;
					public FileInputStream objFileInputStream = null;
					public Properties objProperties = new Properties();
					
					
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
						/*String sPath_AppProperties="";
						FileInputStream objFileInputStream = null;
						Properties objProperties = new Properties();*/
						
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
						uiHomePageObjects = new HomePageObjects(driver);
						uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
						uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						uiValidateCollege_Information =new ValidateCollege_InformationPageObjects(driver);
						uiValidateEmphasisPageObject= new ValidateEmphasisPageObject(driver);
						
						uiProgramGroupPageObjects= new ProgramGroupPageObjects(driver);
						driver.switchTo().frame("Orion");
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

							System.out.println("Inside After class");
							
							System.out.println("Before method writing to Result Property file");
							
							SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmphasisName1FromValidateEmphasisOrion_SF", sEmphasisName1);
							
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
					public void EmphasisDetails(Method objMethod) throws InterruptedException
					{
						try
						{


						uiValidateEmphasisPageObject= new ValidateEmphasisPageObject(driver);
						  uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
						  //driver.get(EnvironmentVariables.sOrion1_URL);
						  Thread.sleep(15000);
						  
						  
							// click on Program Maintenance button
							
							uiProgramGroupPageObjects.TbprogramMaintenancehomepage.click();
						
						
						Thread.sleep(15000);
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						
						
						uiValidateEmphasisPageObject.lnkProgramRollMaintenance.click();
						
						
						
						uiValidateEmphasisPageObject.lnkProgramEmphasis.click();
						
						uiValidateEmphasisPageObject.lnkAddNewItem.click();
						
						uiValidateEmphasisPageObject.txtEmphasisTextArea.clear();
					uiValidateEmphasisPageObject.txtEmphasisTextArea.sendKeys(sEmphasisName);
						uiValidateEmphasisPageObject.txtActiveCheckBox.click();
						Thread.sleep(15000);
						
						uiValidateEmphasisPageObject.btnUpdate.click();
						Thread.sleep(5000);
						uiValidateEmphasisPageObject.lnkProgramRollup.click();
						Thread.sleep(5000);
						uiValidateEmphasisPageObject.lnkAddNewItem.click();
						
                       Select ddlProgramGroup = new Select(uiValidateEmphasisPageObject.ddProgramGroup);				
						
                       ddlProgramGroup.selectByIndex(2);
						
						Thread.sleep(10000);
						
						uiValidateEmphasisPageObject.txtProgramDescription.clear();
						uiValidateEmphasisPageObject.txtProgramDescription.sendKeys("TestProgramDescription");
						
			              Select ddlProgramEmphasis = new Select(uiValidateEmphasisPageObject.ddProgramEmphasis);				
							
			              ddlProgramEmphasis.selectByVisibleText(sEmphasisName);
			              
			              uiValidateEmphasisPageObject.btnUpdate_Rollup.click();
						}
						
						catch (Exception e)
																	
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}
						
					}
					
					
					
					
					@Test(dependsOnMethods={"EmphasisDetails"})
					public void VerifyEmphasisInSRM(Method objMethod) throws InterruptedException
					
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
						

						// wait for page to load


						// Click product	
						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsLink);
						uiOrion1ProgramRollupMappingPageObjects.productsLink.click();

						// wait for page to load	

						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsInputBox);
						
						uiOrion1ProgramRollupMappingPageObjects.productsInputBox.clear();
						uiOrion1ProgramRollupMappingPageObjects.productsInputBox.sendKeys(sEmphasisName);
						
						

						// Type in input box 
						System.out.println(sEmphasisName);
						
						
						uiAddInquiry_Referral_Lead_Pageobjects.btnFindProduct.click();
						
						Thread.sleep(10000);
						
						Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtEmphasisPresent.getText(), sEmphasisName);
						
						}
						
						catch (Exception e)
																	
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}

					}
					
					@Test(dependsOnMethods={"VerifyEmphasisInSRM"})
					public void EmphasisUpdatedDetails(Method objMethod) throws InterruptedException
					{
						
			        
						try
						{
			              uiValidateEmphasisPageObject= new ValidateEmphasisPageObject(driver);
						  uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
						  Thread.sleep(5000);
						  driver.get(EnvironmentVariables.sOrion1_URL);
						  driver.switchTo().frame("Orion");
						  
						  driver.manage().window().maximize();

							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							
						  Thread.sleep(15000);
							// click on Program Maintenance button
							
							uiProgramGroupPageObjects.TbprogramMaintenancehomepage.click();
						
						
						Thread.sleep(15000);
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						
						
						uiValidateEmphasisPageObject.lnkProgramRollMaintenance.click();
						
						uiValidateEmphasisPageObject.lnkProgramEmphasis.click();
						
						uiValidateEmphasisPageObject.txtProgramEmphasisSearch.clear();
						
						uiValidateEmphasisPageObject.txtProgramEmphasisSearch.sendKeys(sEmphasisName);
						
						uiValidateEmphasisPageObject.btnEmphasisSearch.click();
						uiValidateEmphasisPageObject.lnkEmphasicEdit.click();
						
						
						
						uiValidateEmphasisPageObject.txtEmphasisTextArea.clear();
					uiValidateEmphasisPageObject.txtEmphasisTextArea.sendKeys(sEmphasisName1);
						//uiValidateEmphasisPageObject.txtActiveCheckBox.click();
						Thread.sleep(15000);
						
						uiValidateEmphasisPageObject.btnUpdate.click();
						Thread.sleep(5000);
						
						
						uiValidateEmphasisPageObject.lnkProgramRollup.click();
						Thread.sleep(5000);
						uiValidateEmphasisPageObject.txtProgramSearchEmphasis.clear();
						uiValidateEmphasisPageObject.txtProgramSearchEmphasis.sendKeys(sEmphasisName1);
						
						uiValidateEmphasisPageObject.btnRollupSearch.click();
						Thread.sleep(5000);
						uiValidateEmphasisPageObject.lnkProgramEmphasisEdit.click();
						
						 Select ddlProgramGroup = new Select(uiValidateEmphasisPageObject.ddProgramGroup);				
							
	                       ddlProgramGroup.selectByIndex(3);
							
							Thread.sleep(10000);
						
					
						
			              Select ddlProgramEmphasis = new Select(uiValidateEmphasisPageObject.ddProgramEmphasis);				
							
			              Thread.sleep(10000);
			              ddlProgramEmphasis.selectByVisibleText(sEmphasisName1);
			              
			              uiValidateEmphasisPageObject.btnUpdate_Rollup.click(); 
						
			          
						}
						
						catch (Exception e)
																	
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}

			              
			              
						
					}
					
					
					
					
					@Test(dependsOnMethods={"EmphasisUpdatedDetails"})
					public void VerifyeUpdatedEmphasisInSRM(Method objMethod) throws InterruptedException
					
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
						

						// wait for page to load


						// Click product	
						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsLink);
						uiOrion1ProgramRollupMappingPageObjects.productsLink.click();

						// wait for page to load	

						UserExtension.IsElementPresent(driver, uiOrion1ProgramRollupMappingPageObjects.productsInputBox);
						
						uiOrion1ProgramRollupMappingPageObjects.productsInputBox.clear();
						uiOrion1ProgramRollupMappingPageObjects.productsInputBox.sendKeys(sEmphasisName1);
						
						Thread.sleep(20000);

						// Type in input box 
						System.out.println(sEmphasisName1);
						
						
						uiAddInquiry_Referral_Lead_Pageobjects.btnFindProduct.click();
						
						Thread.sleep(10000);
						
						Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtEmphasisPresent.getText(), sEmphasisName1);
						
						
						
						
						Thread.sleep(10000);
			              
				           // Writing Edit ed Program Emphasis Name to Property file
				      		
				      		System.out.println("Before method writing Program Emphasis Name to Property file");
				      		
				      		SRM_ReusableMethods.writeToPropertyFile(sPath_AppProperties, "ProgramEmphasisNameFromScript", sEmphasisName1);
				      		
				      		System.out.println("After method writing Program Emphasis Name to Property file");
				      		
						}
						
						catch (Exception e)
																	
						{
						Reporter.log(e.getMessage());
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						}

				      	

					}
	}
		

					
				        
				       
		
		
