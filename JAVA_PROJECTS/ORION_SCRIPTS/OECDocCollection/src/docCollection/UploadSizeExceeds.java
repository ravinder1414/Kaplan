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

public class UploadSizeExceeds {

	public String UID, PWD, OEC_URL;
	public static String temp_BrowserName;
	private RemoteWebDriver driver;
	
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

	//@Test(groups={"FileSizeExceed"}, dependsOnGroups={"UnSupportedFile"})
	@Test(alwaysRun = true)
  public void uploadFileSizeExceed() throws InterruptedException {
		
	driver.get(OEC_URL);
	if(temp_BrowserName.contains("internet explorer"))
		driver.navigate().to("javascript:document.getElementById('overridelink').click()");

			LocalLibrary.login(driver, UID, PWD);
			
			System.out.println("*********************Upload File Size Exceeds Limits****************");
		  //Verify My Documents link
		  String actual = driver.findElement(By.xpath("//a[contains(text(),'My Documents')]")).getText();
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
		    driver.findElement(By.xpath("//a[contains(text(),'My Documents')]")).click();
		    driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
			 //Click attachment icon
		    driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
		    driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
		    
		  //Set invalid file
		    driver.findElement(By.name("ctl00$ctl11$ctl03")).sendKeys("\\\\10.78.7.60\\Selenium_Scripts\\Doc2.doc");
		    
		  //Click upload icon
		    driver.findElement(By.name("ctl00$ctl11$ctl05")).click();
		    driver.manage().timeouts().pageLoadTimeout(8, TimeUnit.MINUTES);
		    
		    try{
		    	Assert.assertEquals(driver.findElement(By.xpath("//div/table/tbody/tr/td[2]/span")).getText(), "The file you attempted to upload exceeds our size limit of 5 MB.", "In correct message is appearing correctly after uploading the file which is of exceeded limits");
		    	Reporter.log("Correct message is appearing correctly after uploading the file which is of exceeded limits");
		    	System.out.println("Correct message is appearing correctly after uploading the file which is of exceeded limits");
		    }catch(Error e){Reporter.log("In correct message is appearing correctly after uploading the file which is of exceeded limits"+e.getMessage());}
		    
		  //Application link click
		    driver.findElement(By.linkText("APPLICATION")).click();
		    
				Thread.sleep(4000);
				LocalLibrary.logOff(driver);
				driver.quit();

  }
	/*@AfterClass
	public void exitDriver(){
		driver.quit();
	}*/

}
