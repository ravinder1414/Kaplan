package docCollection;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyDocColumns {
	
	public String UID, PWD, OEC_URL;
	public static String temp_BrowserName;

	
	public RemoteWebDriver driver;
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

	
  //@Test(groups={"MyDocCols"}, dependsOnGroups={"FaxCoverIcon"})
	@Test(alwaysRun = true)
  public void verifyMyDocCloumns() throws InterruptedException {
	  
	  driver.get(OEC_URL);
	  if(temp_BrowserName.contains("internet explorer"))
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
	  
	  LocalLibrary.login(driver, UID, PWD);
	  
	  //Verify My Documents link
	  System.out.println("*********************My Document Columns****************");
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
		  System.out.println("My Documents Link is not appearing"+e.getMessage());Reporter.log("My Documents Link is not appearing"+e.getMessage());
	  }
	  
	  //Click on My Documents link  
	    driver.findElement(By.xpath(OR.xpathMyDocumentLnk)).click();
	    Thread.sleep(4000);

	  //Verify Columns on the Document Tracking page
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathDocTrackHead1)).getText(), "Document Tracking", "Document Tracking - is not displaying correctly");
	    System.out.println("Document Tracking - is not displaying correctly");
	    }catch(Exception e){Reporter.log("Document Tracking - is not displaying correctly"+e.getMessage());System.out.println("Document Tracking - is not displaying correctly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathDocCurrentlyDue)).getText(), "Documents Currently Due", "Documents Currently Due - is correctly displaying on the page");
	    System.out.println("Documents Currently Due - is correctly displaying on the page");
	    }catch(Exception e){Reporter.log("Documents Currently Due - is correctly displaying on the page"+e.getMessage());System.out.println("Documents Currently Due - is correctly displaying on the page"+e.getMessage());}
	   /* try{
	    Assert.assertEquals(driver.findElement(By.xpath("//td[@id='ctl00_ctl11_tcMainCell3']/table/tbody/tr[2]/td[2]")).getText(), "Red Text - Indicates Documents that are past due.\nRows Per Page: All 10 25 50", "Red Text - Indicates Documents that are past due.\nRows Per Page: All 10 25 50 - message displaying incorrectly");
	    System.out.println("Red Text - Indicates Documents that are past due.\nRows Per Page: All 10 25 50 - message displaying incorrectly");
	    }catch(Exception e){Reporter.log("Red Text - Indicates Documents that are past due.\nRows Per Page: All 10 25 50"); System.out.println("Red Text - Indicates Documents that are past due.\nRows Per Page: All 10 25 50");}
	    */
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnFile)).getText(), "File", "File column - correctly displaying");
	    System.out.println("File column - correctly displaying");
	    }catch(Exception e){Reporter.log("File column - correctly displaying"+e.getMessage());System.out.println("File column - correctly displaying"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnUpload)).getText(), "Upload", "Upload columns - correctly displaying");
	    System.out.println("Upload columns - correctly displaying");
	    }catch(Exception e){Reporter.log("Upload columns - correctly displaying"+e.getMessage());System.out.println("Upload columns - correctly displaying"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnFaxCoverSheet)).getText(), "Fax Cover Sheet", "Fax Cover Sheet columns - displaying correctly");
	    System.out.println("Fax Cover Sheet columns - displaying correctly");
	    }catch(Exception e){Reporter.log("Fax Cover Sheet columns - displaying correctly"+e.getMessage());System.out.println("Fax Cover Sheet columns - displaying correctly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDocName)).getText(), "Document Name", "Document Name columns - displaying incorrectly");
	    System.out.println("Document Name columns - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Document Name columns - displaying incorrectly"+e.getMessage());System.out.println("Document Name columns - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnStatus)).getText(), "Status", "Status columns  - displaying incorrectly");
	    System.out.println("Status columns  - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Status columns  - displaying incorrectly"+e.getMessage());System.out.println("Status columns  - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDateRequested)).getText(), "Date Requested" , "Date Requested columns - displaying incorrectly");
	    System.out.println("Date Requested columns - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Date Requested columns - displaying incorrectly"+e.getMessage());System.out.println("Date Requested columns - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDueDate)).getText(), "Date Due", "Date Due column - displaying incorrectly");
	    System.out.println("Date Due column - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Date Due column - displaying incorrectly"+e.getMessage());System.out.println("Date Due column - displaying incorrectly"+e.getMessage());}
	    
	    //Document Tracking section column names
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDocHistory)).getText(), "Document History", "Document History - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Document History - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnFileDocTrk)).getText(), "File", "File - displaying in correctly");
	    }catch(Exception e){Reporter.log("File - displaying in correctly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDocNameDocTrk)).getText(), "Document Name", "Document Name - displaying in correctly");
	    }catch(Exception e){Reporter.log("Document Name - displaying in correctly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnStatusDocTrk)).getText(), "Status", "Status - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Status - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDateRequestedDocTrk)).getText(), "Date Requested", "Date Requested - displaying in correctly");
	    }catch(Exception e){Reporter.log("Date Requested - displaying in correctly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDueDateDocTrk)).getText(), "Date Due", "Date Due - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Date Due - displaying incorrectly"+e.getMessage());}
	    try{
	    Assert.assertEquals(driver.findElement(By.xpath(OR.xpathColumnDateReceivedDocTrk)).getText(), "Date Received", "Date Received - displaying incorrectly");
	    }catch(Exception e){Reporter.log("Date Received - displaying incorrectly"+e.getMessage());}
	    
	    //Application link click
	    driver.findElement(By.linkText("APPLICATION")).click();
	    Thread.sleep(4000);
	    LocalLibrary.logOff(driver);
	    driver.quit();
  }
	
}
