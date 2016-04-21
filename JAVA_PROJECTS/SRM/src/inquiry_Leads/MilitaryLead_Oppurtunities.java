package inquiry_Leads;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
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

import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import srm_Variables.EnvironmentVariables;
import uiMap_Orion3.Admissions.AddNewLeadPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3_SRM.AddInquiry_Referral_Lead_Pageobjects;
import uiMap_Orion3_SRM.HomePageObjects;
import uiMap_Orion3_SRM.InfoCallLeadPageObjects;
import uiMap_Orion3_SRM.SRM_LeadFlow_PageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;


public class MilitaryLead_Oppurtunities {
	
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser cap
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public HomePageObjects uiHomePageObjects;
	public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
	public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
	public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
	public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
	public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
	public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
	
	//Variables from Properties file
	String sFirstName1;
    String sLastName1;
    String sEmailAddress1;
	public String sMilitaryType;
	public String sSpouseMilitary;
	public String sTCPA;
	public String sHighestEducation;
	
	//Static variable
	public String sPath_ResultProperties="";
	public static String sRandStr = RandomStringUtils.randomAlphabetic(5);
	public static String sFirstName = "TestNGFNMLOPP_" + sRandStr;
	public static String sLastName = "TestNGLNMLOPP_" + sRandStr;			
	public static String sEmailAddress = sFirstName + "IC@kap.com";
	public String sDayPhone = "9545151234";
	public String sZipCode = "30256";
	public static String sSyStuID= "";
	public static String sEmailID="";
	public String IWD_Url= "http://10.70.90.48:8080/iwd_manager/ui/login.jsf";
	
	
	
	//Method which will executed before the class loads
	//Browser Parameter received from TestNg.xml
	
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeNavigation(String sBrowser) throws MalformedURLException
	{
		try{
			
		
		
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
	
		sTCPA = objProperties.getProperty("sTCPADisclosure");
		sMilitaryType = objProperties.getProperty("sMilitaryType");
		sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
		sHighestEducation = objProperties.getProperty("sHighestEduction");
		
	
					
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
		driver.get(EnvironmentVariables.sSRM_Url);
		driver.manage().window().maximize();
		uiHomePageObjects = new HomePageObjects(driver);			
	}
	
	catch(Exception e)
	{
		Reporter.log(e.getMessage());
	}
	
	}
	
	
	
	
	
	
	
	@AfterClass
	public void AfterNavigation()
	{
		try

		{

			//Quit the test after test class execution

			// Writing to Result Property File

		
			System.out.println("Inside After class");
			
			System.out.println("Before method writing to Result Property file");
			
			SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromMilitaryLead_Oppurtunities", sEmailAddress);
			
			System.out.println("After method writing to Result Property file");

			
			
			
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
	public void BrowseToAddNewMilitaryReferralLeadPage(Method objMethod) throws InterruptedException
	{
		try
		{
			uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
		
		uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);
		uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
        Thread.sleep(3000);
        
        uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
		
		
		WebDriverWait wait = new WebDriverWait(driver, 1000);			
		driver.switchTo().frame("ext-comp-1005");
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral);
		
		
		//Select Referral Radio Button
		uiAddInquiry_Referral_Lead_Pageobjects.rbnReferral.click();
		
		Thread.sleep(10000);
		
		uiAddInquiry_Referral_Lead_Pageobjects.txtFirstName.sendKeys(sFirstName);
		uiAddInquiry_Referral_Lead_Pageobjects.txtLastName.sendKeys(sLastName);
		uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.sendKeys(sEmailAddress);
		System.out.println(sEmailAddress);
		
		sEmailAddress1 =uiAddInquiry_Referral_Lead_Pageobjects.txtEmailAddress.getAttribute("value");
		
		uiAddInquiry_Referral_Lead_Pageobjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
		uiAddInquiry_Referral_Lead_Pageobjects.txtZipCode.sendKeys(sZipCode);
		
		
		//TCPA Disclosure
		if(sTCPA.equalsIgnoreCase("NO"))
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_Yes.click();					
		}
		else
		{
			uiAddInquiry_Referral_Lead_Pageobjects.rbtnTCPA_Disclosure_No.click();
		}
		
		
		//Spouse Military Status
		//driver.findElementByXPath(".//*[@id='j_id0:addaleadid:leadblock:j_id44:10:j_id50:1']").click();
		uiAddInquiry_Referral_Lead_Pageobjects.rbtnSpouse_Yes.click();
		
		
		
		//Highest Level of Education
		Select ddlHightestEdution = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddHighestLevelEducation);
		ddlHightestEdution.selectByVisibleText(sHighestEducation);
		Thread.sleep(5000);
		
		//Military Type
		Select ddMilitaryType = new Select(uiAddInquiry_Referral_Lead_Pageobjects.ddMilitaryType);
		ddMilitaryType.selectByVisibleText("Yes – Active Duty");
     
		//Clicking on Inquiry Button 
		uiAddInquiry_Referral_Lead_Pageobjects.txtAddAnInquiry.click();
		Thread.sleep(3000);
		UserExtension.IsElementPresent(driver, uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess);	
		
		//Assertion in Lead Created Success Message 
		Assert.assertEquals(uiAddInquiry_Referral_Lead_Pageobjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
		
		driver.navigate().refresh();
		
		uiReusableMethods_PageObjects.lnkDropDown.click();
		Thread.sleep(3000);
		uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
		Thread.sleep(1000);
		
		
			
	}
	catch(Exception e)
	{
		Reporter.log(e.getMessage());
	}
	}
	
	@Test(dependsOnMethods={"BrowseToAddNewMilitaryReferralLeadPage"})
	public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
	
	{try{
		uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
		driver.get(EnvironmentVariables.sSRM_Url);
		uiAddNewLeadsPageObjects.search_SRM.clear();
		uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
			
		//Searching Inquiry 
		uiAddNewLeadsPageObjects.btnsearch_SRM.click();
		SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
		//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
		Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
		Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
		
		 //click on inquiry Lead
		driver.findElementByXPath(".//*[@id='Lead_body']/table/tbody/tr[2]/th/a").click();
		Thread.sleep(10000);
		
		//Fetch Systudent id
		
		sSyStuID =driver.findElementByXPath("//td[text()='SyStudentID']/following-sibling::td[1]/div").getText();
		Reporter.log(sSyStuID);
		System.out.println(sSyStuID);
		
		//Capturing Systudent Id 
		//uiAddNewLeadsPageObjects.txtSyStudent.getText();
		//System.out.println(uiAddNewLeadsPageObjects.txtSyStudent.getText());
		
		//Scrolling down Page 
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		
		//Clicking on Student information
		uiAddNewLeadsPageObjects.lnkStdntInfrmtn.click();
		Thread.sleep(10000 );
		
		//Scrolling down page to get sif answer
		Robot robot1 = new Robot();
		robot1.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot1.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(10000);
		
		//Clicking on get sif to verify military status 
		driver.switchTo().frame(uiAddNewLeadsPageObjects.IframeSwitch);
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.BtnGetSifAnswer);
		
		//Clicking on Get Sif Answer Button 
		uiAddNewLeadsPageObjects.BtnGetSifAnswer.click();
		UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.TxtMilitaryStatus);
		Thread.sleep(3000);
		
		//verifying Military Status Under Student information Page 
		System.out.println(uiAddNewLeadsPageObjects.TxtMilitaryStatus.getText());
		Assert.assertEquals(uiAddNewLeadsPageObjects.TxtMilitaryStatus.getText(), "Yes – Active Duty");
		Thread.sleep(5000);
		
		//verifying email address under student information page 
		System.out.println(uiAddNewLeadsPageObjects.TxtEmailSif.getText());
		Assert.assertEquals(uiAddNewLeadsPageObjects.TxtEmailSif.getText(), sEmailAddress);
		
		
	}
	catch (Exception e)
	{
		Reporter.log(e.getMessage());
		
	}
	
	
	}
	
	@Test(dependsOnMethods={"VerifyLeadInSRM"})
	public void ConvertingLeadToOppurtunity(Method objMethod) throws InterruptedException
	{
		try
		{
			uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			driver.navigate().refresh();
			//Clicking on Home Button
			uiAddNewLeadsPageObjects.BtnHomeButton.click();
			
			//Searching lead to convert into oppurtunity 
			uiAddNewLeadsPageObjects.search_SRM.clear();
			uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
			//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
				
			//clicking on search button  
			
			uiAddNewLeadsPageObjects.btnsearch_SRM.click();
			SRM_ReusableMethods.WaitSearchInquiry(driver, 40000);
			
			//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
			UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
			Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
			Thread.sleep(3000);
			
			//Click on The Inquiry Lead 
			uiAddNewLeadsPageObjects.lnkSearchResult.click();
			Thread.sleep(7000);
			
			//Clicking on Convert Button 
			uiAddNewLeadsPageObjects.BtnConvert.click();
			Thread.sleep(10000);
			
			/*//Clicking on Converted Oppurtunity Link 
			uiAddNewLeadsPageObjects.lnkConvertedOppurtunity.click();
			
			
			//Window Handles
			String parentHandle = driver.getWindowHandle();
			Set <String> winHandles = driver.getWindowHandles();
			for (String currentWindowHandle : winHandles) {
				if (!currentWindowHandle.equals(parentHandle)) {
					driver.switchTo().window(currentWindowHandle);
					}
			}
			Thread.sleep(10000);
			//verifying oppurtunity status and military status 
			System.out.println(uiAddNewLeadsPageObjects.txtStatusOpportunities.getText());
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtStatusOpportunities, "Application In-Progress");
			
			//Verifying current military status in oppurtunity Page 
			System.out.println(uiAddNewLeadsPageObjects.txtCurrentMilitarystatus.getText());
			Assert.assertEquals(uiAddNewLeadsPageObjects.txtCurrentMilitarystatus.getText(),"Military SIF");*/
			
			
			
		}
		catch (Exception e)
		{
			Reporter.log(e.getMessage());
		}
	}
		@Test (dependsOnMethods="ConvertingLeadToOppurtunity")
		public void VerifyOpportunityStatus(Method obMethod) throws InterruptedException
		{
			try{
				uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
				uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
										
				//Navigate to Homepage by opening the URL
				driver.get(EnvironmentVariables.sSRM_Url);
				
				//Search for the Lead/opportunity
				uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
				//uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
				uiAddNewLeadsPageObjects.btnsearch_SRM.click();
				
				//call Wait for Search Opportunity Method
				SRM_ReusableMethods.WaitSearchOpportunity(driver, 30000);
										
				//Opportunities and Accounts Elements
				WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'Opportunities')]"));
				WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(), 'Accounts')]"));

				//Assert Opportunities and Accounts
				Assert.assertTrue(ele.isDisplayed());
				Assert.assertTrue(ele1.isDisplayed());
				
				//click to open the Opportunity
				uiSRM_LeadFlow_PageObjects.LnkOppLead.click();
				UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtOppurtunitystatus);
				
				
				//verifying oppurtunity status and military status 
				System.out.println(uiAddNewLeadsPageObjects.txtOppurtunitystatus.getText());
				Assert.assertEquals(uiAddNewLeadsPageObjects.txtOppurtunitystatus.getText(), "Application In-Progress");
			    UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtCurrentMilitarystatus);
				
				//Verifying current military status in oppurtunity Page 
				System.out.println(uiAddNewLeadsPageObjects.txtCurrentMilitarystatus.getText());
				Assert.assertEquals(uiAddNewLeadsPageObjects.txtCurrentMilitarystatus.getText(),"Military SIF");
				
				driver.navigate().refresh();
				
				uiReusableMethods_PageObjects.lnkDropDown.click();
				Thread.sleep(3000);
				uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				Reporter.log(e.getMessage());
				
			}
		}
			@Test (dependsOnMethods="VerifyOpportunityStatus")
			public void ExecutingQueryInDeveloperConsole(Method obMethod) throws InterruptedException
			{
				try{
					//Initializing Page Objects Drivers
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
					
					//Clicking on Home Button
					uiAddNewLeadsPageObjects.BtnHomeButton.click();
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.DrpdwnUserArrow);
					
					//Clicking User Extension DropDown List 
					uiAddNewLeadsPageObjects.DrpdwnUserArrow.click();
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkDeveloperConsole);
					
					//Clicking on Developer Console link 
					uiAddNewLeadsPageObjects.lnkDeveloperConsole.click();
					Thread.sleep(5000);
					 

					//Window Handles
					
					String parentHandle = driver.getWindowHandle();
					System.out.println(parentHandle);
					Set <String> winHandles = driver.getWindowHandles();
					for (String currentWindowHandle : winHandles) {
						if (!currentWindowHandle.equals(parentHandle)) {
							driver.switchTo().window(currentWindowHandle);
							}
					}
					System.out.println(driver.getTitle());
					driver.manage().window().maximize();
					//UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.tabQueryEditor);
					Thread.sleep(15000);
					System.out.println("WINDOW SWITCHED DONE");
					
									
					//Clicking on QueryEditor Tab
					uiAddNewLeadsPageObjects.tabQueryEditor.click();
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtQueryEditorField);
					
					//Clicking on Query Editor Text Field 
					uiAddNewLeadsPageObjects.txtQueryEditorField.click();
					Thread.sleep(7000);
					String sQuery = "SELECT Is_Military_Formula1__c, Military_Status__c, syStudentID__c FROM Opportunity WHERE syStudentID__c ='"+sSyStuID+"'";
					//Sending Query to the Query Editor Text Field 
					System.out.println(sQuery);
					uiAddNewLeadsPageObjects.txtQueryEditorField.clear();
					uiAddNewLeadsPageObjects.txtQueryEditorField.sendKeys(sQuery);
					System.out.println("QUERY ENTERED");
					
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.TabQueryExecute);
					
					//Clicking on Execute Button 
					uiAddNewLeadsPageObjects.TabQueryExecute.click();
					System.out.println("CLICK EXECUTE");
					Thread.sleep(25000);
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.TextTrueMilitaryFormula);
					
					//Verifying military lead is true or false 
					System.out.println(uiAddNewLeadsPageObjects.TextTrueMilitaryFormula.getText().trim());
					Assert.assertEquals(uiAddNewLeadsPageObjects.TextTrueMilitaryFormula.getText().trim(),"True");
					Thread.sleep(10000);
					
				    //Verifying military status in Console 
					System.out.println(uiAddNewLeadsPageObjects.TextMilitarayStatusConsole.getText().trim());
					Assert.assertEquals(uiAddNewLeadsPageObjects.TextMilitarayStatusConsole.getText().trim(), "Military SIF");
					Thread.sleep(5000);
					
					//Verifying syStudentId in Console 
					System.out.println(uiAddNewLeadsPageObjects.TextsyStudentIDConsole.getText().trim());
					Assert.assertEquals(uiAddNewLeadsPageObjects.TextsyStudentIDConsole.getText().trim(), sSyStuID);
					Thread.sleep(10000);
					
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/a")).click();
					
					}
				catch(Exception e)
				{
					Reporter.log(e.getMessage());
				}
			}
			
		

		
	
	

}