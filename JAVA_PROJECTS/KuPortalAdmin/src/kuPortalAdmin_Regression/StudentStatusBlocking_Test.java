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
import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uiMap_KuPortal.LoginPage;
import uiMap_KuPortalAdmin.HomePage;
import uiMap_KuPortalAdmin.LandingPage;
import uiMap_KuPortalAdmin.portalContent.StudentStatusBlockingPage;
import uiMap_KuPortalAdmin.portalUsers.SearchExistingUserPage;
import uiMap_KuPortalAdmin.portalUsers.ViewEditPortalUserPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class StudentStatusBlocking_Test {

	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public LandingPage uiLandingPageObjects;	
	public HomePage uiHomePageObjects;
	public StudentStatusBlockingPage uiStudentStatusBlockingPageObjects;
	
	//Application Variable
	public String sSSB_HoldCode, sSSB_Page, sSSB_StudentStatus;
	public String sCampus = "042 Kaplan University, Online (43)";
	
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
		
		//Read Application Variables from Properties File
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
		
		sSSB_HoldCode = objProperties.getProperty("StudentStatusBlocking_HoldCode");
		sSSB_StudentStatus = objProperties.getProperty("StudentStatusBlocking_StudentStatus");
		sSSB_Page = objProperties.getProperty("StudentStatusBlocking_Page");
		
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
	public void StudentStatusBlocking_HoldCodeManagement()
	{
		String sRandomStr = RandomStringUtils.randomAlphabetic(4);
		String sBlockMessage = "This is a Random Block Message: " + sRandomStr;
				
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkStudentStatusBlocking.click();
		
		uiStudentStatusBlockingPageObjects = new StudentStatusBlockingPage(driver);
		//Select Campus
		Select ddlCampus = new Select(uiStudentStatusBlockingPageObjects.ddlCampus);
		ddlCampus.selectByVisibleText(sCampus);
		
		//Select a hold Code to View and Edit
		WebElement rblHoldCode = uiStudentStatusBlockingPageObjects.tblAllHoldCode.findElement(By.xpath("//input[@id='HoldCode'][@value='" + sSSB_HoldCode + "']"));
		rblHoldCode.click();
		uiStudentStatusBlockingPageObjects.btnViewEdit.click();
		Assert.assertTrue(uiStudentStatusBlockingPageObjects.lblHoldCodeName.getText().trim().equalsIgnoreCase(sSSB_HoldCode), "Selected hold code is not the adTranscript Hold code");
		System.out.println("Previous Block message: - " + uiStudentStatusBlockingPageObjects.txtBlockMessage.getText().trim());
		
		uiStudentStatusBlockingPageObjects.txtBlockMessage.clear();
		uiStudentStatusBlockingPageObjects.txtBlockMessage.sendKeys(sBlockMessage);
		uiStudentStatusBlockingPageObjects.btnSave.click();
		
		uiStudentStatusBlockingPageObjects.btnSave_Confirmation.click();

		ddlCampus.selectByVisibleText(sCampus);
		rblHoldCode.click();
		uiStudentStatusBlockingPageObjects.btnViewEdit.click();
		Assert.assertTrue(uiStudentStatusBlockingPageObjects.lblHoldCodeName.getText().trim().equalsIgnoreCase("" + sSSB_HoldCode + ""), "Selected hold code is not the adTranscript Hold code");
		Assert.assertEquals(uiStudentStatusBlockingPageObjects.txtBlockMessage.getText().trim(), sBlockMessage, "Block Message is not saved");
	}
	
	@Test
	public void StudentStatusBlocking_StudentPermissions_ByHoldCode()
	{
		String sChecked = "false";
		String sTempHoldCode="", sBlockedPages, sUnBlockedPages;
		int iTotalRows;
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkStudentStatusBlocking.click();
		
		uiStudentStatusBlockingPageObjects = new StudentStatusBlockingPage(driver);
		uiStudentStatusBlockingPageObjects.lnkStudentPermissions.click();
		
		//Select Campus
		Select ddlCampus = new Select(uiStudentStatusBlockingPageObjects.ddlCampus);
		ddlCampus.selectByVisibleText(sCampus);
		//Select View Permission By
		Select ddlViewBy = new Select(uiStudentStatusBlockingPageObjects.ddlViewBy);
		ddlViewBy.selectByVisibleText("Hold Code");
		//Select View By Type
		Select ddlViewByType = new Select(uiStudentStatusBlockingPageObjects.ddlViewByType);
		ddlViewByType.selectByVisibleText(sSSB_HoldCode);
		
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.btnBlock);
		WebElement chkPage = uiStudentStatusBlockingPageObjects.tblAllPagesFeatures.findElement(By.xpath("//td[text()[contains(.,'" + sSSB_Page + "')]]/input"));
		//WebElement chkPage = chkPageTD.findElement(By.xpath("/input"));
		try		
		{			
			sChecked = chkPage.getAttribute("Checked");
		}		
		catch(Exception ex)
		{
		}
		//Select the Page to mark as block
		if(sChecked==null)
		{
			chkPage.click();
		}
		uiStudentStatusBlockingPageObjects.btnBlock.click();
		uiStudentStatusBlockingPageObjects.btnSave_Confirmation.click();
		
		//Navigate to Reports
		uiStudentStatusBlockingPageObjects.lnkReports.click();
		ddlCampus.selectByVisibleText(sCampus);
		ddlViewBy.selectByVisibleText("Hold Code");
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.tblReports);
		List<WebElement> objRows = driver.findElements(By.xpath("//*[@id='ReportTable']/table/tbody/tr"));
		iTotalRows = objRows.size();
		int iRow;
		for(iRow = 2;iRow <= iTotalRows;iRow++)
		{
			if(driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td/strong")).getText().equalsIgnoreCase(sSSB_HoldCode))
			{
				break;
			}
		}		
		sBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_Blocked +"]")).getText();
		Assert.assertTrue(sBlockedPages.contains(sSSB_Page), "Page is not appearing as Block page");
		
		
		//Back to Student Permissions to Uncheck the Page toUnblock
		uiStudentStatusBlockingPageObjects.lnkStudentPermissions.click();
		//Select Campus
		ddlCampus.selectByVisibleText(sCampus);
		//Select View Permission By
		ddlViewBy.selectByVisibleText("Hold Code");
		//Select View By Type
		ddlViewByType.selectByVisibleText(sSSB_HoldCode);
				
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.btnBlock);
		chkPage = uiStudentStatusBlockingPageObjects.tblAllPagesFeatures.findElement(By.xpath("//td[text()[contains(.,'" + sSSB_Page + "')]]/input"));
		try		
		{			
			sChecked = chkPage.getAttribute("Checked");
		}		
		catch(Exception ex)
		{
		}		
		//UnSelect the Page to mark as Unblock
		if(sChecked==null)
		{
			
		}
		else
		{
			chkPage.click();
		}
		uiStudentStatusBlockingPageObjects.btnBlock.click();
		uiStudentStatusBlockingPageObjects.btnSave_Confirmation.click();
		
		//Navigate to Reports
		uiStudentStatusBlockingPageObjects.lnkReports.click();
		ddlCampus.selectByVisibleText(sCampus);
		ddlViewBy.selectByVisibleText("Hold Code");
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.tblReports);
		objRows = driver.findElements(By.xpath("//*[@id='ReportTable']/table/tbody/tr"));
		iTotalRows = objRows.size();
		System.out.println(iTotalRows);
		for(iRow = 2;iRow <= iTotalRows;iRow++)
		{
			if(driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td/strong")).getText().equalsIgnoreCase(sSSB_HoldCode))
			{
				break;
			}
		}		
		sBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_Blocked +"]")).getText();
		Assert.assertFalse(sBlockedPages.contains(sSSB_Page), "Page is still appearing as Block Page");
		sUnBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_UnBlocked +"]")).getText();
		Assert.assertTrue(sUnBlockedPages.contains(sSSB_Page), "Page is not appearing as Unblock page.");
	}
	
	@Test
	public void StudentStatusBlocking_StudentPermissions_ByStudentStatus()
	{
		String sChecked = "false";
		String sTempHoldCode="", sBlockedPages, sUnBlockedPages;
		int iTotalRows;
		UserExtension.MouseOver(driver, uiHomePageObjects.lnkPortalcontent);
		uiHomePageObjects.lnkStudentStatusBlocking.click();
		
		uiStudentStatusBlockingPageObjects = new StudentStatusBlockingPage(driver);
		uiStudentStatusBlockingPageObjects.lnkStudentPermissions.click();
		
		//Select Campus
		Select ddlCampus = new Select(uiStudentStatusBlockingPageObjects.ddlCampus);
		ddlCampus.selectByVisibleText(sCampus);
		//Select View Permission By
		Select ddlViewBy = new Select(uiStudentStatusBlockingPageObjects.ddlViewBy);
		ddlViewBy.selectByVisibleText("Student Status");
		//Select View By Type
		Select ddlViewByType = new Select(uiStudentStatusBlockingPageObjects.ddlViewByType);
		ddlViewByType.selectByVisibleText(sSSB_StudentStatus);
		
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.btnBlock);
		WebElement chkPage = uiStudentStatusBlockingPageObjects.tblAllPagesFeatures.findElement(By.xpath("//td[text()[contains(.,'" + sSSB_Page + "')]]/input"));
		//WebElement chkPage = chkPageTD.findElement(By.xpath("/input"));
		try		
		{			
			sChecked = chkPage.getAttribute("Checked");
		}		
		catch(Exception ex)
		{
		}
		//Select the Page to mark as block
		if(sChecked==null)
		{
			chkPage.click();
		}
		uiStudentStatusBlockingPageObjects.btnBlock.click();
		uiStudentStatusBlockingPageObjects.btnSave_Confirmation.click();
		
		//Navigate to Reports
		uiStudentStatusBlockingPageObjects.lnkReports.click();
		ddlCampus.selectByVisibleText(sCampus);
		ddlViewBy.selectByVisibleText("Student Status");
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.tblReports);
		List<WebElement> objRows = driver.findElements(By.xpath("//*[@id='ReportTable']/table/tbody/tr"));
		iTotalRows = objRows.size();
		int iRow;
		for(iRow = 2;iRow <= iTotalRows;iRow++)
		{
			if(driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td/strong")).getText().equalsIgnoreCase(sSSB_StudentStatus))
			{
				break;
			}
		}		
		sBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_Blocked +"]")).getText();
		Assert.assertTrue(sBlockedPages.contains(sSSB_Page), "Page is not appearing as Block page");
		
		
		//Back to Student Permissions to Uncheck the Page toUnblock
		uiStudentStatusBlockingPageObjects.lnkStudentPermissions.click();
		//Select Campus
		ddlCampus.selectByVisibleText(sCampus);
		//Select View Permission By
		ddlViewBy.selectByVisibleText("Student Status");
		//Select View By Type
		ddlViewByType.selectByVisibleText(sSSB_StudentStatus);
				
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.btnBlock);
		chkPage = uiStudentStatusBlockingPageObjects.tblAllPagesFeatures.findElement(By.xpath("//td[text()[contains(.,'" + sSSB_Page + "')]]/input"));
		try		
		{			
			sChecked = chkPage.getAttribute("Checked");
		}		
		catch(Exception ex)
		{
		}		
		//UnSelect the Page to mark as Unblock
		if(sChecked==null)
		{
			
		}
		else
		{
			chkPage.click();
		}
		uiStudentStatusBlockingPageObjects.btnBlock.click();
		uiStudentStatusBlockingPageObjects.btnSave_Confirmation.click();
		
		//Navigate to Reports
		uiStudentStatusBlockingPageObjects.lnkReports.click();
		ddlCampus.selectByVisibleText(sCampus);
		ddlViewBy.selectByVisibleText("Student Status");
		UserExtension.IsElementPresent(driver, uiStudentStatusBlockingPageObjects.tblReports);
		objRows = driver.findElements(By.xpath("//*[@id='ReportTable']/table/tbody/tr"));
		iTotalRows = objRows.size();
		System.out.println(iTotalRows);
		for(iRow = 2;iRow <= iTotalRows;iRow++)
		{
			if(driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td/strong")).getText().equalsIgnoreCase(sSSB_StudentStatus))
			{
				break;
			}
		}		
		sBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_Blocked +"]")).getText();
		Assert.assertFalse(sBlockedPages.contains(sSSB_Page), "Page is still appearing as Block Page");
		sUnBlockedPages = driver.findElement(By.xpath("//*[@id='ReportTable']/table/tbody/tr[" + iRow + "]/td[" + uiStudentStatusBlockingPageObjects.iCol_UnBlocked +"]")).getText();
		Assert.assertTrue(sUnBlockedPages.contains(sSSB_Page), "Page is not appearing as Unblock page.");
	}
	
}
