package docCollection;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FaxCoverIcon {
	
	public String UID, PWD, OEC_URL;
	public static String temp_BrowserName;
	public RemoteWebDriver driver;
	public RemoteWebDriver backupDriver;
	
	@BeforeClass
	@Parameters({"environment", "browserName"})
	public void getDriverfrmLib(String environment, String browserName) throws MalformedURLException, InterruptedException{
		LocalLibrary objLocalLib = new LocalLibrary();
		driver = objLocalLib.getDriver( browserName,  HubConfiguration.Hub.HUB_PORT,HubConfiguration.Hub.HUB_IP);
		temp_BrowserName=browserName;
		//Getting the OEC URL/UID/PWD for the environments.
		if(environment.toLowerCase().contains("stage")){
			this.OEC_URL = EnvironmentDetails.Stage.getOECURL(); System.out.println("OEC URL in side function:- "+this.OEC_URL);
			this.UID=EnvironmentDetails.Stage.getOECUID(); this.PWD=EnvironmentDetails.Stage.getOECPWD(); 
		}else if(environment.toLowerCase().contains("build")){
			OEC_URL = EnvironmentDetails.Build.getOECURL(); System.out.println("OEC URL in side function:- "+this.OEC_URL); 
			this.UID=EnvironmentDetails.Build.getOECUID(); 
			this.PWD=EnvironmentDetails.Build.getOECPWD();
		}else if(environment.toLowerCase().contains("cidev")){
			OEC_URL = EnvironmentDetails.CIDev.getOECURL(); System.out.println("OEC URL in side function:- "+this.OEC_URL); 
			this.UID=EnvironmentDetails.CIDev.getOECUID(); 
			this.PWD=EnvironmentDetails.CIDev.getOECPWD();
		}else if(environment.toLowerCase().contains("dev")){
			OEC_URL = EnvironmentDetails.Dev.getOECURL(); System.out.println("OEC URL in side function:- "+this.OEC_URL); 
			this.UID=EnvironmentDetails.Dev.getOECUID(); 
			this.PWD=EnvironmentDetails.Dev.getOECPWD();
		}
	}
	
  //@Test(groups={"FaxCoverIcon"})
	@Test(alwaysRun = true)
  public void varifyFaxCoverIcon() throws InterruptedException, IOException {
		driver.get(OEC_URL);
		if(temp_BrowserName.contains("internet explorer"))
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		LocalLibrary.login(driver, UID, PWD);

		//Verify My Documents link
	  
	  System.out.println("*********************Fax Cover Icon****************");
	  String actual = driver.findElement(By.xpath(OR.xpathMyDocumentLnk)).getText();
	  try{
	    Assert.assertEquals(actual, "MY DOCUMENTS", "My Documents Link is not appearing");
	    if(actual.equalsIgnoreCase("My Documents"))
	    	{
	    		System.out.println("My Documents Link is appearing"); Reporter.log("My Documents Link is appearing");
	       	}
	    	else{
	    		System.out.println("My Documents Link is not appearing");Reporter.log("My Documents Link is not appearing");
/*	    		 //Take screenshot
	    		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\MyDoc.png"));*/
	    		  
	    	}
	  }catch(Exception e){
		  System.out.println("My Documents Link is not appearing"+e.getMessage());Reporter.log("My Documents Link is not appearing"+e.getMessage());
 		 /*//Take screenshot
		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\MyDoc.png"));*/
	  }
	  //Click on My Documents link  
	    driver.findElement(By.xpath(OR.xpathMyDocumentLnk)).click();
	    Thread.sleep(4000);
	  
	    //Verify Document Tracking heading
	    actual =driver.findElement(By.xpath(OR.xpathDocTrackHead)).getText();
	    try{
	    Assert.assertEquals(actual, "Document Tracking", "Document Tracking - is not displaying correctly");
	    if(actual.contains("Document Tracking")){
	    	System.out.println("Document Tracking - is appearing correctly on page heading");Reporter.log("Document Tracking - is appearing correctly on page heading");
	    }else{
	    	System.out.println("Document Tracking - is appearing incorrectly on page heading");Reporter.log("Document Tracking - is appearing incorrectly on page heading");
   		/* //Take screenshot
  		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\DocTracking.png"));*/
	    }
	    }catch(Exception e){
	    	System.out.println("Document Tracking - is appearing incorrectly on page heading"+e.getMessage());Reporter.log("Document Tracking - is appearing incorrectly on page heading"+e.getMessage());
   		 /*//Take screenshot
  		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\DocTracking.png"));*/
	    }
	    	
	   //VerifyFax Cover sheet icon
	    try{	    
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathFaxCoverImage)).getAttribute("src").contains("FaxCoverIcon.png"), true, "Fax Cover Icon is not appearing in Document Tracking page");
	    
	    if(driver.findElement(By.xpath(OR.xpathFaxCoverImage)).getAttribute("src").contains("FaxCoverIcon.png")){
	    	System.out.println("Fax Cover Icon is displaying correctly");Reporter.log("Fax Cover Icon is displaying correctly");
	    }else{
	    	System.out.println("Fax Cover Icon is displaying incorrectly");Reporter.log("Fax Cover Icon is displaying incorrectly");
   		 /*//Take screenshot
  		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\FaxCoverIcon.png"));*/
	    }
	    }catch(Exception e){
	    	System.out.println("Fax Cover Icon is displaying incorrectly"+e.getMessage());Reporter.log("Fax Cover Icon is displaying incorrectly"+e.getMessage());
   		 /*//Take screenshot
  		  File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		  FileUtils.copyFile(srcFile, new File(".\\ScreenShots\\FaxCoverIcon.png"));*/
	    }
	    
	    //Application link click
	    driver.findElement(By.linkText("APPLICATION")).click();
	    Thread.sleep(4000);
	    
	    //Logoff
	    LocalLibrary.logOff(driver);
	    driver.quit();
	            
  }


	
	
	
	
}
