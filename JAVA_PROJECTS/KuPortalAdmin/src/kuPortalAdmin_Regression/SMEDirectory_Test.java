package kuPortalAdmin_Regression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.record.SSTRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import uiMap_KuPortalAdmin.portalContent.InquiryViewPage;
import uiMap_KuPortalAdmin.portalContent.SMEDirectoryPage;
import uiMap_KuPortalAdmin.portalContent.StudentStatusBlockingPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class SMEDirectory_Test {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public SMEDirectoryPage uiSMEDirectoryPageObjects;
	public InquiryViewPage uiInquiryViewPageObjects;
	public String[] sTableColumnHeaders;
	
	//Application Variable

	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeStudentStatusBlocking(String sBrowser) throws MalformedURLException
	{
		
		//Edit Browser Capabilities as per project
		//Fire fox Profile		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_KuportalAdmin);
		
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
		driver.get(EnvironmentVariables.sUrl_KuportalAdmin);
		driver.manage().window().maximize();
		uiLandingPageObjects = new LandingPage(driver);
		uiLandingPageObjects.LoginToAdminPortal(EnvironmentVariables.sAdminUserName, EnvironmentVariables.sAdminPassword);
		uiHomePageObjects = new HomePage(driver);
		
		/*//Read Application Variables from Properties File
		FileInputStream objFileInputStream = null;
		Properties objProperties = new Properties();
		File objFile = new File(EnvironmentVariables.sApplicationVariablePath);
		try {
				objFileInputStream = new FileInputStream(objFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
				objProperties.load(objFileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	//Recovery Scenario
	@BeforeMethod
	public void BackToHomePage()
	{
		
		driver.get(EnvironmentVariables.sUrl_KuportalAdmin);	
	}

	@AfterClass
	public void AfterSearchExistingUsers()
	{
		//Quit the test after test class execution
		if(driver != null)
		{
			driver.quit();			
		}
	}

	@Test
	public void SMEDirectory_DetailsDisplayed()
	{
		int iteration;
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMEDirectoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		
		//Page Elements Verification
		Assert.assertTrue(uiSMEDirectoryPageObjects.lnkAddNewSME_UploadSMEPhoto.isDisplayed(), "AddNewSME link is not displayed");
		Assert.assertTrue(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.isDisplayed(), "SMEDirectory Data Table is not displayed");
		
		
		//Verify Table Columns
		sTableColumnHeaders =  uiSMEDirectoryPageObjects.sColumnSequence.split("-");
		Assert.assertEquals(sTableColumnHeaders.length, uiSMEDirectoryPageObjects.iCol_TotalColumns, "No of Columns do not match for SMEDirectory Table");
		for(iteration=1;iteration<=uiSMEDirectoryPageObjects.iCol_TotalColumns;iteration++)
		{
			Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[1]/th[" + iteration + "]")).getText().trim(), sTableColumnHeaders[iteration-1], "Column Headers are not as expected");			
		}		
	}
	
	@Test(dependsOnMethods={"SMEDirectory_DetailsDisplayed"})
	public void SMEDirectory_AddNewSME()
	{
		String sSME_name = RandomStringUtils.randomAlphabetic(5);
		String sFistName = "First_" + sSME_name;
		String sLastName = "Last_" + sSME_name;
		String sJobTitle = "SME_" + sSME_name;
		int iteration, iRow = 2, iRowCount;
		String sImgSrc;
		
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMEDirectoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		
		//Click Add New SME
		uiSMEDirectoryPageObjects.lnkAddNewSME_UploadSMEPhoto.click();
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.txtFirstName);
		
		//Enter Details		
		uiSMEDirectoryPageObjects.txtFirstName.sendKeys("First_" + sSME_name);
		uiSMEDirectoryPageObjects.txtLastName.sendKeys("Last_" + sSME_name);
		uiSMEDirectoryPageObjects.txtJobTitle.sendKeys(sJobTitle);
		uiSMEDirectoryPageObjects.btnSelectPhotoPicker.click();
		sImgSrc = uiSMEDirectoryPageObjects.imgSelectPhotoPicker.getAttribute("Src");
		uiSMEDirectoryPageObjects.btnSave.click();		
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		iRowCount = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElements(By.xpath("//tbody/tr")).size();
		
			
		sTableColumnHeaders =  uiSMEDirectoryPageObjects.sColumnSequence.split("-");		
		for(iteration=1;iteration<=uiSMEDirectoryPageObjects.iCol_TotalColumns;iteration++)
		{
			
			if(sTableColumnHeaders[iteration-1].equalsIgnoreCase("Last Name"))
			{
				innerloop:
				for(iRow=2;iRow<=iRowCount;iRow++)	
				{
					if(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr["+ iRow + "]/td[" + iteration + "]")).getText().trim().equalsIgnoreCase(sLastName))
					{
						break innerloop;
					}						
				}	
			Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr["+ iRow + "]/td[" + iteration + "]")).getText().trim(), sLastName, "First Name of the Newly added SME is NOT appearing in the SME Table as expected");
			}
			if(sTableColumnHeaders[iteration-1].equalsIgnoreCase("First Name"))
			{
				Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr["+ iRow + "]/td[" + iteration + "]")).getText().trim(), sFistName, "Last Name of the Newly added SME is NOT appearing in the SME Table as expected");
			}
			if(sTableColumnHeaders[iteration-1].equalsIgnoreCase("Job Title"))
			{
				Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr["+ iRow + "]/td[" + iteration + "]")).getText().trim(), sJobTitle, "Job Title of the Newly added SME is NOT appearing in the SME Table as expected");
			}
			if(sTableColumnHeaders[iteration-1].equalsIgnoreCase("Photo"))
			{
				Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr["+ iRow + "]/td[" + iteration + "]/img")).getAttribute("Src").trim(), sImgSrc, "Image of the Newly added SME is NOT appearing in the SME Table as expected");
			}
		}
		
	}

	@Test(dependsOnMethods={"SMEDirectory_DetailsDisplayed"})
	public void SMEDirectory_EditSME()
	{
		String sSME_name = RandomStringUtils.randomAlphabetic(5);
		String sJobTitle = "SME_" + sSME_name;
		int iteration;
		
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMEDirectoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		
		//Click Edit SME
		WebElement lnkEdit = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[5]/a"));
		lnkEdit.click();
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.txtFirstName);
		
		//Edit Details
		uiSMEDirectoryPageObjects.txtJobTitle.clear();
		uiSMEDirectoryPageObjects.txtJobTitle.sendKeys(sJobTitle);
		uiSMEDirectoryPageObjects.btnSave.click();		
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		sTableColumnHeaders =  uiSMEDirectoryPageObjects.sColumnSequence.split("-");
		for(iteration=1;iteration<=uiSMEDirectoryPageObjects.iCol_TotalColumns;iteration++)
		{
			if(sTableColumnHeaders[iteration-1].equalsIgnoreCase("Job Title"))
			{
				Assert.assertEquals(uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[" + iteration + "]")).getText().trim(), sJobTitle, "Job Title of the edited SME is NOT appearing in the SME Table as expected");
			}				
		}	
	
	}
	
	@Test(dependsOnMethods={"SMEDirectory_DetailsDisplayed"})
	public void SMEDirectory_DisableSME()
	{
		String sFirstName, sLastName, sJobTitle, sCurrentStatus;
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMEDirectoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		
		//Note Down the SME Details
		sLastName = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText();
		sFirstName = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText();
		sJobTitle = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[3]")).getText();
		
		//Verify that the Disable link is appearing for the First SME in SME Directory Table
		WebElement lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		sCurrentStatus = lnkAction.getText();
		
		System.out.println(sCurrentStatus);
		//Change the Status to Disable if already Enable
		if(sCurrentStatus.equalsIgnoreCase("Enable"))
		{
			lnkAction.click();
		}
		
		lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		Assert.assertEquals(lnkAction.getText().trim(), "Disable", "Unable to set the Actions as Disable in SME Directory Table");
		
		//Click on Disable to disable the First SME in SME Directory Table
		lnkAction.click();
		lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		Assert.assertEquals(lnkAction.getText().trim(), "Enable", "Action Status did not change to Enable in SME Directory Table");
		
		//Verify that the SME is Disable
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkInquiryView.click();
		uiInquiryViewPageObjects = new InquiryViewPage(driver);
		
		WebElement lnkAdd = uiInquiryViewPageObjects.tblInquiryDataTable.findElement(By.xpath("//tbody/tr[2]/td[5]/a"));
		lnkAdd.click();
		
		Select ddlSelectSME = new Select(uiInquiryViewPageObjects.ddlResponseBySME);
		try		
		{
			ddlSelectSME.selectByVisibleText(sFirstName + " " + sLastName + ", " + sJobTitle);
			Assert.assertFalse(true, sFirstName + " " + sLastName + ", " + sJobTitle + " is appearing in available SME. SME not disabled");
		}
		catch(NoSuchElementException exNSE)
		{
			Reporter.log(sFirstName + " " + sLastName + ", " + sJobTitle + " is not appearing in available SME");
		}		
	}

	@Test(dependsOnMethods={"SMEDirectory_DetailsDisplayed"})
	public void SMEDirectory_EnableSME()
	{
		String sFirstName, sLastName, sJobTitle, sCurrentStatus;
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkSMEDirectory.click();
		uiSMEDirectoryPageObjects = new SMEDirectoryPage(driver);
		UserExtension.IsElementPresent(driver, uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable);
		
		//Note Down the SME Details
		sLastName = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText();
		sFirstName = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText();
		sJobTitle = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[3]")).getText();
		
		//Verify that the Disable link is appearing for the First SME in SME Directory Table
		WebElement lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		sCurrentStatus = lnkAction.getText();
		
		//Change the Status to Disable if already Enable
		if(sCurrentStatus.equalsIgnoreCase("Disable"))
		{
			lnkAction.click();
		}
		
		lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		Assert.assertEquals(lnkAction.getText().trim(), "Enable", "Unable to set the Actions as Enable in SME Directory Table");
		
		//Click on Disable to disable the First SME in SME Directory Table
		lnkAction.click();
		lnkAction = uiSMEDirectoryPageObjects.tblSMEDirectoryDataTable.findElement(By.xpath("//tbody/tr[2]/td[6]/a"));
		Assert.assertEquals(lnkAction.getText().trim(), "Disable", "Action Status did not change to Disable in SME Directory Table");
		
		//Verify that the SME is Disable
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkInquiryView.click();
		uiInquiryViewPageObjects = new InquiryViewPage(driver);
		
		WebElement lnkAdd = uiInquiryViewPageObjects.tblInquiryDataTable.findElement(By.xpath("//tbody/tr[2]/td[5]/a"));
		lnkAdd.click();
		
		Select ddlSelectSME = new Select(uiInquiryViewPageObjects.ddlResponseBySME);
		try		
		{
			ddlSelectSME.selectByVisibleText(sFirstName + " " + sLastName + ", " + sJobTitle);
			Reporter.log(sFirstName + " " + sLastName + ", " + sJobTitle + " is appearing in available SME.");
		}
		catch(NoSuchElementException exNSE)
		{
			Assert.assertTrue(false, sFirstName + " " + sLastName + ", " + sJobTitle + " is appearing in available SME.");
		}		
	}

}
