package inquiry_Leads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
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

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import srm_Variables.EnvironmentVariables;
import reusableMethods_PageObject.ReusableMethods_PageObjects;
import reusableMethods_PageObject.SRM_ReusableMethods;
import uiMap_Orion1.DocSchedulePageObject;
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

	public class ValidateODINotification {
		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				//Home Page Page Object Model
				public HomePageObjects uiHomePageObjects;
				public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				public AddInquiry_Referral_Lead_Pageobjects uiAddInquiry_Referral_Lead_Pageobjects;
				public InfoCallLeadPageObjects uiInfoCallLeadPageObjects;
				public SRM_LeadFlow_PageObjects uiSRM_LeadFlow_PageObjects;
				public ReusableMethods_PageObjects uiReusableMethods_PageObjects;
				public DocSchedulePageObject uiDocScheulePage;
				
				
				//Variables from Properties file
				String sFirstName1;
				String sLastName1;
			    String sEmailAddress1;
				public String sMilitaryType;
				public String sSpouseMilitary;
				public String sTCPA;
				public String sHighestEducation;
				public  String sCampus= "";
				public  String sDocCategory= "";
				public  String sDocName= "";
				
				
				//Static variable
				String sRandStr = RandomStringUtils.randomAlphabetic(5);
				public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
				public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
				public String sEmailAddress = sFirstName + "IC@kap.com";
				public String sDayPhone = "9545151234";
				public String sZipCode = "30256";
				public String sStunum, sStuid;
				
				
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
				
					sTCPA = objProperties.getProperty("sTCPADisclosure");
					sMilitaryType = objProperties.getProperty("sMilitaryType");
					sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
					sHighestEducation = objProperties.getProperty("sHighestEduction");
					sCampus = objProperties.getProperty("Campus");
					sDocCategory = objProperties.getProperty("DocCategory");
					sDocName = objProperties.getProperty("DocName");
					
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
						driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
						ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
					}
					catch(Exception ex)
					{	
						ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
					}
					driver.get(EnvironmentVariables.sSRM_Url);
					driver.manage().window().maximize();
					uiHomePageObjects = new HomePageObjects(driver);
					uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
					uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
					uiDocScheulePage = new DocSchedulePageObject(driver);
					
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
				public void BrowseToAddInfoCallLeadPage(Method objMethod) throws InterruptedException
				{
					uiAddInquiry_Referral_Lead_Pageobjects = new AddInquiry_Referral_Lead_Pageobjects(driver);
					uiInfoCallLeadPageObjects = new InfoCallLeadPageObjects(driver);
					uiReusableMethods_PageObjects =new ReusableMethods_PageObjects(driver);

                    Thread.sleep(30000);
                    
                    
                    
                  //How to Read files when changes are not on all the environments  
                    
                    //if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
					{
                    	//uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
					}
					//else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
					{
						//uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);			
					}
					
					//else
					{
						//sPath_AppProperties = ".//Resources//ApplicationProperties/TestApplication.properties";			
					}
					
                    
                    uiReusableMethods_PageObjects.NavigateAdmissionConsoleSTAGE(driver);
					
				
					WebDriverWait wait = new WebDriverWait(driver, 10000);
					//driver.switchTo().frame("ext-comp-1006");
					
					driver.switchTo().frame("ext-comp-1005");
					
					
					UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.rbnInternet);
					
					uiInfoCallLeadPageObjects.rbnInternet.click();
					Thread.sleep(20000);
					UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddPromotional);
					//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:prmotionid")));
					Thread.sleep(20000);
					UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddPromotional);
					Select ddlPromotionCode = new Select(uiInfoCallLeadPageObjects.ddPromotional);
					ddlPromotionCode.selectByIndex(1);
					Thread.sleep(20000);
					
					//Select Area of Study
					
					Select ddlAreaOfStudy = new Select(uiInfoCallLeadPageObjects.ddAreaofStudy);				
					ddlAreaOfStudy.selectByIndex(1);
					Thread.sleep(15000);				
					
					//Program of Interest
					UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.ddProgramofInterest);
					
					//WebElement element6 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:progid")));
					
					Select ddlProgramOfInterest = new Select(uiInfoCallLeadPageObjects.ddProgramofInterest);
					
					ddlProgramOfInterest.selectByIndex(2);
					Thread.sleep(15000);
					
					uiInfoCallLeadPageObjects.txtFirstName.sendKeys(sFirstName);
					uiInfoCallLeadPageObjects.txtLastName.sendKeys(sLastName);
					uiInfoCallLeadPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
					
					sEmailAddress1 =uiInfoCallLeadPageObjects.txtEmailAddress.getAttribute("value");
					
					uiInfoCallLeadPageObjects.txtDayTimePhoneNo.sendKeys(sDayPhone);
					uiInfoCallLeadPageObjects.txtZipCode.sendKeys(sZipCode);
					
					//Spouse Military Status
					if(sSpouseMilitary.equalsIgnoreCase("yes"))
					{
						
						uiInfoCallLeadPageObjects.rbtnSpouse_Yes.click();
						
					}
					else
					{
						uiInfoCallLeadPageObjects.rbtnSpouse_No.click();
					}
                   //	Military Type DropDown values
					
					Select ddlMilitaryType = new Select(uiInfoCallLeadPageObjects.dropDownMilitaryType);				
					
					ddlMilitaryType.selectByIndex(1);
					
					
					//TCPA Disclosure
					if(sTCPA.equalsIgnoreCase("yes"))
					{
						uiInfoCallLeadPageObjects.rbtnTCPA_Disclosure_Yes.click();					
					}
					else
					{
						uiInfoCallLeadPageObjects.rbtnTCPA_Disclosure_No.click();
					}
					
					
					//Highest Level of Education
					Select ddlHightestEdution = new Select(uiInfoCallLeadPageObjects.ddHighestLevelEducation);
					ddlHightestEdution.selectByVisibleText(sHighestEducation);
					
					Thread.sleep(30000);
					
					//WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:addALeadButtonId")));
					uiInfoCallLeadPageObjects.txtAddAnInquiry.click();
					
					Thread.sleep(30000);
					
					UserExtension.IsElementPresent(driver, uiInfoCallLeadPageObjects.txtCreatedLeadSuccess);
					
					//WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:addaleadid:leadblock:successmsgid")));
					
					Assert.assertEquals(uiInfoCallLeadPageObjects.txtCreatedLeadSuccess.getText().trim(), "Success:Your lead is being created");
					
					driver.navigate().refresh();
					
					uiReusableMethods_PageObjects.lnkDropDown.click();
					Thread.sleep(30000);
					uiAddInquiry_Referral_Lead_Pageobjects.lnkKaplanSRM.click();
					//uiReusableMethods_PageObjects.BackToKaplanSRM(driver);
					Thread.sleep(10000);
					
					
				}			
					
				//Verify Created Lead in SRM				
				@Test(dependsOnMethods={"BrowseToAddInfoCallLeadPage"})
				public void VerifyLeadInSRM(Method objMethod) throws InterruptedException
				
				{try{
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					driver.get(EnvironmentVariables.sSRM_Url);
					
					//Search for the Lead/opportunity
					uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
					//uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
					uiAddNewLeadsPageObjects.btnsearch_SRM.click();
					
					//call Wait for Search Opportunity Method
					SRM_ReusableMethods.WaitSearchInquiry(driver, 30000);
					
					
//					uiAddNewLeadsPageObjects.search_SRM.clear();
//					uiAddNewLeadsPageObjects.search_SRM.sendKeys(sEmailAddress1);
//					WebDriverWait wait = new WebDriverWait(driver, 5000);
//					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("phSearchButton")));
//						
//					uiAddNewLeadsPageObjects.btnsearch_SRM.click();
//					
//					Thread.sleep(10000);
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
//					uiAddNewLeadsPageObjects.btnsearch_again.click();
//					Thread.sleep(70000);
//					
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
//					uiAddNewLeadsPageObjects.btnsearch_again.click();
//					Thread.sleep(70000);
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.clear();
//					uiAddNewLeadsPageObjects.txtSecond_search_SRM.sendKeys(sEmailAddress1);
//					uiAddNewLeadsPageObjects.btnsearch_again.click();
//					Thread.sleep(30000);
//					
					//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='Lead_body']/table/tbody/tr[2]/td[8]/a")));
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtInquiryStatus);
					Assert.assertEquals(uiAddNewLeadsPageObjects.txtInquiryStatus.getText().trim(), "New");
					
					Assert.assertTrue(uiAddNewLeadsPageObjects.txtEmailAddressVerification.getText().equalsIgnoreCase(sEmailAddress1), "Email searched successfully");
							
				}catch (Exception e)
				{Reporter.log(e.getMessage());
					
				}
							
				}
				
				//Progress Lead to contacted through SRM	
				@Test (dependsOnMethods="VerifyLeadInSRM")
				public void ProgressLeadtoContacted(Method obMethod) throws InterruptedException{
					try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						uiSRM_LeadFlow_PageObjects.clickOnCreatedLead.click();
						uiSRM_LeadFlow_PageObjects.btnEdit.click();
						Select ddlInquiry = new Select(uiSRM_LeadFlow_PageObjects.ddInquiryDropDown);				
						ddlInquiry.selectByVisibleText("Contacted");
						Thread.sleep(30000);
						uiSRM_LeadFlow_PageObjects.btnSave.click();
				}
					catch(Exception e){Reporter.log(e.getMessage());}
				}
				
				//convert Lead to OppoRtunity
				@Test (dependsOnMethods="ProgressLeadtoContacted")
				public void ConvertOpportunityAccounts(Method obMethod) throws InterruptedException{
					try{
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						uiSRM_LeadFlow_PageObjects =new SRM_LeadFlow_PageObjects(driver);
						uiSRM_LeadFlow_PageObjects.btnConvert.click();
						Thread.sleep(10000);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.hdngConvInquiry);
						Thread.sleep(10000);
						Assert.assertTrue(uiSRM_LeadFlow_PageObjects.hdngConvInquiry.isDisplayed());
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}
				
				//Verify Opportunity and Lead in SRM SalesForce
				@Test (dependsOnMethods="ConvertOpportunityAccounts")
				public void VerifyOpportunityAccounts(Method obMethod) throws InterruptedException{
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
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtCVUEStunum);
						
						//Store CVUE Student Number and SystudentID
						sStunum = uiSRM_LeadFlow_PageObjects.txtCVUEStunum.getText().trim() ; 
						sStuid = uiSRM_LeadFlow_PageObjects.txtSyStudentID.getText().trim() ; 
						Reporter.log(sStunum +", " +sStuid);
						
						
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}

				
				//Schedule Doc in Orion1
				@Test (dependsOnMethods="VerifyOpportunityAccounts")
				public void DocSchedule(Method obMethod) throws InterruptedException{
					try{
						//open Orion1
						driver.get(EnvironmentVariables.sOrion1_URL);
						String DocImgURL = EnvironmentVariables.sOrion1_URL+"/DocumentImaging/DIScheduling.htm?cn=43&St="+sStuid+"&nomc="+sFirstName+"%20"+sFirstName;
						driver.get(DocImgURL);
						driver.switchTo().frame(uiDocScheulePage.ifrDocSchedule);
						Thread.sleep(10000);
						//select the Doc category
						//get text from date requested
						String dt = uiDocScheulePage.txtDtreq.getAttribute("value").trim();
						System.out.println(dt);
						WebElement ddDcoCategory=uiDocScheulePage.ddDocCategory;
						Select ddlDcoCategory = new Select(ddDcoCategory);
						ddlDcoCategory.selectByVisibleText("      Work-Study");
						Thread.sleep(3000);
						String Courselocator = "//span[text()='"+sDocName+"']/parent::nobr/parent::td/preceding-sibling::td/input";
						driver.findElement(By.xpath(Courselocator)).click();
						Thread.sleep(3000);
						driver.findElement(By.id("DIschedulingPopUP1_btnAddDocs")).click();
						Thread.sleep(3000);
						driver.findElement(By.id("DIschedulingPopUP1_dgDocSelected__ctl2_chkDocSelected")).click();
						Thread.sleep(3000);
						driver.findElement(By.id("DIschedulingPopUP1_btnFinish")).click();
						Thread.sleep(20000);
						Assert.assertEquals(uiDocScheulePage.txtDtreq.getAttribute("value").trim(), "");
												
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}
				
				
				//Verify Scheduled Docs in SRM
				@Test (dependsOnMethods="DocSchedule")
				public void VerifyDocSchedinSRM(Method obMethod) throws InterruptedException{
					try{
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
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtCVUEStunum);
						Thread.sleep(5000);
						//Switch to iFrame 
						driver.switchTo().frame(uiSRM_LeadFlow_PageObjects.idIFrame);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.btnLoadStuDocs);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.tabDefault);
						uiSRM_LeadFlow_PageObjects.btnLoadStuDocs.click();
						Thread.sleep(5000);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.lblWorkStudDocCat);
						Assert.assertTrue(uiSRM_LeadFlow_PageObjects.lblWorkStudDocCat.isDisplayed());
						Assert.assertTrue(uiSRM_LeadFlow_PageObjects.lblFACWSDocName.isDisplayed());
						
						
						
					}
					catch(Exception e){Reporter.log(e.getMessage());}
					
				}
				
				
				//Delete doc from Orion1
				@Test (dependsOnMethods="VerifyDocSchedinSRM")
				public void DeleteDocOrion1(Method obMethod) throws InterruptedException{
					try{
						//open Orion1
						driver.get(EnvironmentVariables.sOrion1_URL);
						driver.switchTo().frame("Orion");
						uiDocScheulePage.TabDocImaging.click();
						Select ddCampus= new Select (uiDocScheulePage.ddCampus);
						ddCampus.selectByVisibleText(sCampus);
						uiDocScheulePage.txtStuNum.sendKeys(sStunum);
						uiDocScheulePage.btnSearchStu.click();
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						WebElement ele = driver.findElement(By.id("name"+sStuid));
						ele.click();
						Thread.sleep(10000);
						UserExtension.IsElementPresent(driver, uiDocScheulePage.lblWorkStudyPrg);
						
						//Get Doc Sched id from get attribute	
						String Docidstr=uiDocScheulePage.lblWorkStudyPrg.getAttribute("id");
						System.out.println(Docidstr);
						String S1 = Docidstr.replaceAll("spanStudDoc_Default_Cat42Doc", "");
						String DocSchdid = S1.replaceAll("Link", "");
						System.out.println(DocSchdid);	
						//Create Doc View URL
						String DocVwrURL = EnvironmentVariables.sOrion1_URL+"/DocumentImaging/Viewer/DocumentFrameset.aspx?sid="+sStuid+"&dsid="+DocSchdid+"&did=0&cid=43";
						driver.get(DocVwrURL);
	
						//Switch to Delete button frame
						driver.switchTo().frame(uiDocScheulePage.sDocDetailsFrame);
						
						//click delete
						UserExtension.IsElementPresent(driver, uiDocScheulePage.btnDocDelete);
						uiDocScheulePage.btnDocDelete.click();
						Thread.sleep(10000);
						//Switch to Default win
						driver.switchTo().defaultContent();
						//Switch to DocumentAttributesFrame
						driver.switchTo().frame("DocumentAttributesFrame");
						UserExtension.IsElementPresent(driver, uiDocScheulePage.ddReasonDelete);
						Select seldt = new Select (uiDocScheulePage.ddReasonDelete);
						seldt.selectByVisibleText(uiDocScheulePage.sReasonDelete);
						Thread.sleep(5000);
						uiDocScheulePage.btnFinalDelete.click();
						
						try{Thread.sleep(5000);}
						catch(Exception e){}
						//Verify and Handle Alert
						Assert.assertTrue(driver.switchTo().alert().getText().trim().equalsIgnoreCase(uiDocScheulePage.msgAlertDelete));
						System.out.println(driver.switchTo().alert().getText().trim());
						driver.switchTo().alert().accept();
											
					}
					catch(Exception e){
						
						Reporter.log(e.getMessage());}
						Assert.assertTrue(false);
				}
			
				
				//Verify Scheduled Docs in SRM
				@Test (dependsOnMethods="DeleteDocOrion1")
				public void VerifyDeleteFlagSRM(Method obMethod) throws InterruptedException{
					try{
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
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.txtCVUEStunum);
						Thread.sleep(5000);
						//Switch to iFrame 
						driver.switchTo().frame(uiSRM_LeadFlow_PageObjects.idIFrame);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.btnLoadStuDocs);
						UserExtension.IsElementPresent(driver, uiSRM_LeadFlow_PageObjects.tabDefault);
						uiSRM_LeadFlow_PageObjects.btnLoadStuDocs.click();
						Thread.sleep(15000);
						
						List <WebElement> eles= uiSRM_LeadFlow_PageObjects.LstProgName;
						System.out.println(eles.size());
						for (int i=0;i<eles.size();i++){
							System.out.println(eles.get(i).getText());
							if((!eles.get(i).getText().equalsIgnoreCase("FA CWS Payroll Data"))){
									
								if(i==eles.size()-1){
									System.out.println("Doc Deleted");
									Assert.assertTrue(true, "Deletion flag reflected in SRM");
									}
						
							}
						}
						
						
						
					}
					catch(Exception e){
						
						Reporter.log(e.getMessage());
						Assert.assertTrue(false);}
					
				}
				
				
	}
				
				
	

	

