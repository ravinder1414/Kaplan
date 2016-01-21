package orion1_ProgramGroup;

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
import org.openqa.selenium.By.ByXPath;
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
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion1.ProgramGroupPageObjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.Orion1ProgramRollupMappingPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;

public class Orion1_ProgramGroup {
	
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
	public ProgramGroupPageObjects uiProgramGroupPageObjects;
	
	
	//Static variable
	String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public String sGroupName = "TestGRPNAME_" + sRandStr;
	public String School ="";
	public String DegreeType="";
	public String OrionSchool="";
	public String SchoolUpdate="";
	public String sGroupnameEdit = "Edit" + sGroupName;
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
		
		uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
		
		uiHomePageObjects = new HomePageObjects(driver);			
	}
	
	
	@Test
	public void Orion1ProgramGroupCreation(Method objMethod) throws InterruptedException
	{
		try
		{
	    uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
		
				
		// click on Program Maintenance button
		driver.switchTo().frame("Orion");
		uiProgramGroupPageObjects.TbprogramMaintenancehomepage.click();
		
		// Clicking on Program Rollup Maintenance button
		uiProgramGroupPageObjects.lnkprogramRollupMaintenance.click();
		
	    // Clicking on Program Group  button
		uiProgramGroupPageObjects.BtnProgramGroup.click();
			
		// Clicking on Add New Extension 
		uiProgramGroupPageObjects.BtnAddnew.click();
		
		//Adding Program group name 
		uiProgramGroupPageObjects.txtAddProgram.sendKeys(sGroupName);
		System.out.println(sGroupName);
		
		//Selecting School From the Drop Down List 
		uiProgramGroupPageObjects.slctSchool.click();
		School=uiProgramGroupPageObjects.slctSchool.getText();
		System.out.println(School);
				
		//Selecting Degree Type From The Drop Down List 
		uiProgramGroupPageObjects.slctDegreeType.click();
		DegreeType=uiProgramGroupPageObjects.slctDegreeType.getText();
		System.out.println(DegreeType);
		
		//Selectiing Orion School From Drop Down List
		uiProgramGroupPageObjects.slctOrionSchool.click();
		OrionSchool=uiProgramGroupPageObjects.slctOrionSchool.getText();
		System.out.println(OrionSchool);
		
		//Clicking on Update Button 
		uiProgramGroupPageObjects.BtnUpdate.click();
		
		UserExtension.IsElementPresent(driver, uiProgramGroupPageObjects.MsgUpdateSuccess);				
		Assert.assertEquals(uiProgramGroupPageObjects.MsgUpdateSuccess.getText().trim(), "Program Group Insert successful in Orion. Program Group Insert successful in Salesforce.");
		System.out.println(uiProgramGroupPageObjects.MsgUpdateSuccess.getText());
		
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
				
	}
	
	
	@Test(dependsOnMethods={"Orion1ProgramGroupCreation"})
	public void VerifyingGroupDetailsInSRM(Method objMethod) throws InterruptedException
	{
		try{
		uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
		driver.get(EnvironmentVariables.sSRM_Url);
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		
		//Searching Group name created in Orion
		uiProgramGroupPageObjects.txtSearchfield.sendKeys(sGroupName);
		
		//Clicking on Search Button 
		uiProgramGroupPageObjects.BtnSearch.click();
		Thread.sleep(15000);
		
		//Clicking on search all link 
		//uiProgramGroupPageObjects.lnkSearchAll.click();
		
		//Verifying Program Group Label Presence 
		uiProgramGroupPageObjects.lblProgramGroup.getText();
		Assert.assertTrue(uiProgramGroupPageObjects.lblProgramGroup.getText().trim().contains("Program Groups"));
	    System.out.println(uiProgramGroupPageObjects.lblProgramGroup.getText());	
	    
		//Clicking on Searched result 
		uiProgramGroupPageObjects.lnkSearchResult.click();
		Thread.sleep(5000);
		
		//Verifying Group name Data in SRM
		uiProgramGroupPageObjects.txtProgramGroupName.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtProgramGroupName.getText(), sGroupName);
		System.out.println(uiProgramGroupPageObjects.txtProgramGroupName.getText());
		
		//Verifying School Name in SRM
		uiProgramGroupPageObjects.txtSchool.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtSchool.getText(), School);
		System.out.println(uiProgramGroupPageObjects.txtSchool.getText());
		
		//Verifying Degree Type in SRM 
		uiProgramGroupPageObjects.txtDegreeType.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtDegreeType.getText(), DegreeType);
		System.out.println(uiProgramGroupPageObjects.txtDegreeType.getText());
				
    }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		
	}
	@Test(dependsOnMethods={"VerifyingGroupDetailsInSRM"})
	public void UpdatingGroupDetailsInOrion1(Method objMethod) throws InterruptedException
	{
		try
		{
	    uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
	    driver.get(EnvironmentVariables.sOrion1_URL);
				
		// click on Program Maintenance button
		driver.switchTo().frame("Orion");
		uiProgramGroupPageObjects.TbprogramMaintenancehomepage.click();
		
		// Clicking on Program Rollup Maintenance button
		uiProgramGroupPageObjects.lnkprogramRollupMaintenance.click();
		
	    // Clicking on Program Group  button
		uiProgramGroupPageObjects.BtnProgramGroup.click();
		
		//Clearing Search Field Before Search
		uiProgramGroupPageObjects.txtGroupNameSearch.clear();
			
		//Searching Program Group in Search Program Group Search Field 
		uiProgramGroupPageObjects.txtGroupNameSearch.sendKeys(sGroupName);
		
		//Clicking on Search Button 
		uiProgramGroupPageObjects.BtnPgmGrpSearch.click();
		
		//Clicking on Edit button 
		uiProgramGroupPageObjects.lnkEdit.click();
		UserExtension.IsElementPresent(driver, uiProgramGroupPageObjects.txtAddProgram);
		Thread.sleep(5000);
		
		//Updating Orion Group Name 
		uiProgramGroupPageObjects.txtAddProgram.clear();
		uiProgramGroupPageObjects.txtAddProgram.sendKeys(sGroupnameEdit);
		System.out.println(sGroupnameEdit);
		
		//Updating Orion School name
		uiProgramGroupPageObjects.txtSchoolUpdate.click();
		SchoolUpdate=uiProgramGroupPageObjects.txtSchoolUpdate.getText();
		System.out.println(uiProgramGroupPageObjects.txtSchoolUpdate.getText());
		
		
		//Clicking on Update Button
		uiProgramGroupPageObjects.BtnUpdate.click();
		
		//Catching Update Success message 
		uiProgramGroupPageObjects.MsgPrgmGrpUpdate.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.MsgPrgmGrpUpdate.getText(), "Program Group Update successful in Orion. Program Group Update successful in Salesforce.");
		System.out.println(uiProgramGroupPageObjects.MsgPrgmGrpUpdate.getText());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
	@Test(dependsOnMethods={"UpdatingGroupDetailsInOrion1"})
	public void VerifyingUpdatedPRGroupDetailsInSRM(Method objMethod) throws InterruptedException
	{
		try{
		uiProgramGroupPageObjects = new ProgramGroupPageObjects(driver);
		driver.get(EnvironmentVariables.sSRM_Url);
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		
		//Searching Group name created in Orion
		uiProgramGroupPageObjects.txtSearchfield.sendKeys(sGroupnameEdit);
		UserExtension.IsElementPresent(driver, uiProgramGroupPageObjects.BtnSearch);
		
		//Clicking on Search Button 
		uiProgramGroupPageObjects.BtnSearch.click();
		SRM_ReusableMethods.WaitSearchProgramGroups(driver, 40000);
		
				
		//Verifying Program Group Label Presence 
		uiProgramGroupPageObjects.lblProgramGroup1.getText();
		Assert.assertTrue(uiProgramGroupPageObjects.lblProgramGroup1.getText().trim().contains("Program Groups"));
		//(uiProgramGroupPageObjects.lblProgramGroup1.getText(), "Program Groups");
		System.out.println(uiProgramGroupPageObjects.lblProgramGroup1.getText());
		Thread.sleep(5000);
				
		//Clicking on Searched result 
		uiProgramGroupPageObjects.lnkSearchResult.click();
		
				
		
		//Verifying Group name Data in SRM
		uiProgramGroupPageObjects.txtProgramGroupName.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtProgramGroupName.getText(), sGroupnameEdit);
		System.out.println(uiProgramGroupPageObjects.txtProgramGroupName.getText());
		
		//Verifying School Name in SRM
		uiProgramGroupPageObjects.txtSchool.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtSchool.getText(), SchoolUpdate);
		System.out.println(uiProgramGroupPageObjects.txtSchool.getText());
		
		//Verifying Degree Type in SRM 
		uiProgramGroupPageObjects.txtDegreeType.getText();
		Assert.assertEquals(uiProgramGroupPageObjects.txtDegreeType.getText(), DegreeType);
		System.out.println(uiProgramGroupPageObjects.txtDegreeType.getText());
				
    }
		
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		
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
	}
