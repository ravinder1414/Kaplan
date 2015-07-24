package KuPortal;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import commonfunctions.ReportExtn;
import commonfunctions.UserExtension;
import commonfunctions.BrowserManagement;
import UIMapKuPortal.AcademicSupportCenter;
import UIMapKuPortal.HomePage;
import UIMapKuPortal.LandingPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

public class Test_HomePage {

public RemoteWebDriver driver = null;
public BrowserManagement objBrowserMgr = null;

@Parameters({"Browser"})
@BeforeClass
public void BeforeClass_HomePage(String sBrowser){
	
	try {
		objBrowserMgr = new BrowserManagement(sBrowser);
		objBrowserMgr.capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
		
	} catch (Exception e) {			
		e.printStackTrace();
	}
			
	driver.get(EnvironmentVariables.sUrl_StudentPortal);
	LandingPage.LoginToKuPortal(driver, EnvironmentVariables.sUsername_Faculty, EnvironmentVariables.sPassword_Faculty);
}

@BeforeMethod
public void BeforeMethod(Method method)
{
	ReportExtn.MethodReportingLayout(method.getName());
	
}

@AfterClass
public void AfterClassHomePage()
{
	if(driver != null)
	{
		driver.quit();
	}
}

  
  @Test(groups = {"Grp_Navigation"})
  	  public void CheckMyStudies(Method objMethod) throws IOException {	  
	  
	  driver.get(EnvironmentVariables.sUrl_StudentPortal);
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.elementToBeClickable(By.linkText(HomePage.linkMYSTUDIES)));
//	  File srcFile = driver.getScreenshotAs(OutputType.FILE);
//		
//	  FileUtils.copyFile(srcFile, new File("c:\\temp.png"));
//	  
//	  
	  WebDriver augmentedDriver = new Augmenter().augment(driver);
      File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcFile, new File("c:\\temp.png"));
      
	  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkMYSTUDIES)), "My Studies link is not present");
	  ReportExtn.Pass(objMethod, "MY Studies is Present");	  
	  
	 
		
	  Actions action = new Actions(driver);
	  action.moveToElement(driver.findElement(By.linkText(HomePage.linkMYSTUDIES))).build().perform();	 
	  
	  UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkAcademicSupportCenter), 5);
	  driver.findElement(By.linkText(HomePage.linkAcademicSupportCenter)).click();	
	  UserExtension.IsElementPresent(driver, By.cssSelector("h1"), 5);
	  if(driver.findElement(By.cssSelector("h1")).getText().compareTo(AcademicSupportCenter.sHeaderText)==0)
	  {
		  
		  ReportExtn.Pass(objMethod, AcademicSupportCenter.sHeaderText + " Header Text present");
		  
	  }
	  else
	  {
		  ReportExtn.Fail(objMethod, driver.findElement(By.cssSelector("h1")).getText(), AcademicSupportCenter.sHeaderText, " Header Text not present");
	  }
		  	  
  }
	  @Test(groups = {"Grp_Navigation"})
	  public void CheckHome(Method objMethod) {	 
		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkHOME)), "linkHOME is not present");
		 ReportExtn.Data(objMethod, "linkHOME is Present");		 
  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckMyAccount() {	 
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkMYACCOUNT)), "linkMYACCOUNT is not present");
//		 ReportExtn.Pass(objMethod, "linkMYACCOUNT is Present");		  
//  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckMyTools() {	 
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkMYTOOLS)), "linkMYTOOLS is not present");
//		 ReportExtn.Pass(objMethod, "linkMYTOOLS is Present");		  		  		  
//  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckeUniversityInfo() {	 
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkUNIVERSITYINFO)), "linkUNIVERSITYINFO is not present");
//		 ReportExtn.Pass(objMethod, "linkUNIVERSITYINFO is Present");			  		  
//  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckUniversityCatalog() {
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkUNIVERSITYCATALOG)), "linkUNIVERSITYCATALOG is not present");
//		 ReportExtn.Pass(objMethod, "linkUNIVERSITYCATALOG is Present");	
//  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckCommunityCenter() {	 
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkCOMMUNITYCENTER)), "linkCOMMUNITYCENTER is not present");
//		 ReportExtn.Pass(objMethod, "linkCOMMUNITYCENTER is Present");	
//  }
//	  @Test(groups = {"Grp_Navigation"})
//	  public void CheckCareerNetwork() {	 
//		  driver.get(EnvironmentVariables.sUrl_StudentPortal);
//		  Assert.assertTrue(UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkCAREERNETWORK)), "linkCAREERNETWORK is not present");
//		 ReportExtn.Pass(objMethod, "linkCAREERNETWORK is Present");	
//  } 
//	   
};
