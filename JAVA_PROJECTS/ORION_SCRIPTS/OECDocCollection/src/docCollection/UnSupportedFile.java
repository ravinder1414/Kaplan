package docCollection;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UnSupportedFile {
	public String UID, PWD, OEC_URL;
	public static String temp_BrowserName;
	private RemoteWebDriver driver ;
		
	@BeforeClass
	@Parameters({"environment", "browserName"})
	public void getDriverfrmLib(String environment, String browserName) throws MalformedURLException, InterruptedException{
		
		LocalLibrary objLocalLib = new LocalLibrary();
		driver = objLocalLib.getDriver(browserName, HubConfiguration.Hub.HUB_PORT,HubConfiguration.Hub.HUB_IP);
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

	//@Test(groups={"UnSupportedFile"}, dependsOnGroups={"UploadPageVerification"})
	@Test(alwaysRun = true)
  public void unsupportedFileUpload() throws InterruptedException {
		
	  driver.get(OEC_URL);
	  if(temp_BrowserName.contains("internet explorer"))
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
	  
	  
	  
		LocalLibrary.login(driver, UID, PWD);
	  
		System.out.println("*********************UnSupported File Type Upload****************");
	  //Verify My Documents link
	  String actual = driver.findElement(By.xpath(OR.xpathMyDocumentLnk)).getText();
	  try{
	    Assert.assertEquals(actual, "MY DOCUMENTS", "My Documents Link is not appearing");
	    if(actual.equalsIgnoreCase("My Documents"))
	    	{
	    		System.out.println("My Documents Link is appearing"); Reporter.log("My Documents Link is appearing");
	       	}
	    	else{
	    		System.out.println("My Documents Link is not appearing");Reporter.log("My Documents Link is not appearing");
	    	}
	  }catch(Exception e){
		  System.out.println("My Documents Link is not appearing");Reporter.log("My Documents Link is not appearing");
	  }
	  
	  //Click on My Documents link  
	    driver.findElement(By.xpath(OR.xpathMyDocumentLnk)).click();
	    driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		 //Click attachment icon
	    driver.findElement(By.cssSelector(OR.xpathAttachmentIcon)).click();
	    driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
	    
	  //Set invalid file
	    driver.findElement(By.name(OR.xpathFileTextbox)).sendKeys("\\\\10.78.7.60\\Selenium_Scripts\\di_roles.txt");
	    
	  //Click upload icon
	    driver.findElement(By.name(OR.xpathUploadButton)).click();
	    driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	    
	    try{
	    	//Assert.assertEquals(driver.findElement(By.xpath("//td[@id='ctl00_ctl11_tcMainCell7']/table/tbody/tr[2]/td/span")).getText(), "Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB.", "Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB. - message displaying incorrectly");
	    	Assert.assertEquals(driver.findElement(By.xpath(OR.xpathNoteMessage)).getText(), "(Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB.)", "Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB. - message displaying incorrectly");
	    	Reporter.log("Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB. - message displaying correctly");
	    	System.out.println("Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB. - message displaying correctly");
	    }catch(Error e){Reporter.log("Acceptable types are; *.doc, *.docx, *.pdf, *.jpg and *.tif. Maximum file size is 5 MB. - message displaying incorrectly");System.out.println("Error occured"+e.getMessage());}
	    
	    
	    try{
	    	Assert.assertEquals(driver.findElement(By.xpath(OR.xpathErrorMsg)).getText(), "Please select a file to upload", "In correct message is appearing correctly after uploading the invalid file");
	    	Reporter.log("Correct message is appearing correctly after uploading the invalid file");
	    	System.out.println("Correct message is appearing correctly after uploading the invalid file");
	    }catch(Error e){Reporter.log("In correct message is appearing correctly after uploading the invalid file"+e.getMessage());}
	    
	  //Application link click
	    driver.findElement(By.linkText("APPLICATION")).click();
	    
			Thread.sleep(4000);
			LocalLibrary.logOff(driver);
			driver.quit();
  }
	

}
