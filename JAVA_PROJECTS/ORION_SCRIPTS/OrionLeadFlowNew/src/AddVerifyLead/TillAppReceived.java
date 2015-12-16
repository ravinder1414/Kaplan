package AddVerifyLead;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import java.net.MalformedURLException;
//import java.text.NumberFormat;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;

public class TillAppReceived {
	
	public static int varLeadId ;
	private WebDriver driver;
	private String winHandle;
	public static final String FN =TestData.Lead_First_Name, LN = TestData.Lead_Last_Name; 
	//public static int fn=216, ln=48;
	public static String varOECUID, varOECPWD; 
	public static String AppURL;
	public String HP, ZipCode, Zip_Code_Loc;
	public Number ZC;
	@BeforeSuite
	public void testData1() throws IOException{
		System.out.println("Before Test Suite");
		System.out.println("class path"+System.getProperty("java.classpath")+"--");
		//TestData o = new TestData();
		//TestData.testData();
		AppURL = TestData.URL;
		System.out.println("BeforeSuite:- "+TestData.URL);
		
	}
	
	  @Parameters({"browser", "port"})
	  @BeforeClass
	  public void beforeSuite(String browser, String port) throws IOException, MalformedURLException{
	  //public void beforeSuite() throws IOException, MalformedURLException {
		//driver = new FirefoxDriver();
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName(browser);
		
		//driver= new RemoteWebDriver(new URL("http://10.78.4.12:".concat(port).concat("/wd/hub")), capability);
		driver= new RemoteWebDriver(new URL("http://localhost:".concat(port).concat("/wd/hub")), capability);
		
		//Runtime.getRuntime().exec("C:\\Authentication.exe");				
		Runtime.getRuntime().exec("wscript C:\\LoginWindow.vbs");
		//URL f = new URL("file://10.78.4.12//login//LoginWindow.vbs");
		
		//Runtime.getRuntime().exec("wscript z:\\LoginWindow.vbs");
		
		driver.get(TestData.URL);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		winHandle = driver.getWindowHandle(); 
		

	}
	 
  @Test(groups={"tillAppReceive"})
  public void addLead () throws InterruptedException, ParseException {

		
	  	Reporter.log("Lead Flow tested on-->"+TestData.URL);
	  	Reporter.log("Add Lead functionality");
	  Actions builder = new Actions(driver);
	  
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.switchTo().frame("Orion");	
	
		driver.findElement(By.xpath("//*[@id='_ctl2_TopNavRow']/td[2]")).click();
		driver.findElement(By.name("btnGo")).click();
		System.out.println("Go button clicked successfully");
		
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");
		
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		//driver.findElement(By.xpath("//*[@id='tabnavi_TabRow']/td[18]/a/img")).click();
		driver.findElement(By.xpath("//*[@id='tabnavi_TabRow']/td[20]/a/img")).click(); //Dev Env Add New Lead button
									
		System.out.println("Add Lead window open successfully");
		
		for (String handle : driver.getWindowHandles()) {
		    driver.switchTo().window(handle);
		    System.out.println("handle---->"+handle);
		   }
	try{	
		builder.click(driver.findElement(By.xpath("//*[@id='CtlApplyForm1_RadCheck_3']"))).build().perform();//WarmTrfer Check box
		Thread.sleep(1000);
		
		builder.click(driver.findElement(By.xpath("//*[@id='CtlApplyForm1_radChannelGroup_1']"))).build().perform(); //Click Email check box
		Thread.sleep(1000);
		
		new Select(driver.findElement(By.id("CtlApplyForm1_ddPromotion"))).selectByVisibleText(TestData.Promotion);
		//new Select(driver.findElement(By.id("CtlApplyForm1_ddPromotion"))).selectByIndex(2);
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("CtlApplyForm1_ddAreaStudy"))).selectByVisibleText(TestData.Area_Of_Study);
		//new Select(driver.findElement(By.id("CtlApplyForm1_ddAreaStudy"))).selectByIndex(3);
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("CtlApplyForm1_ddProgramInterest"))).selectByVisibleText(TestData.Program_Of_Interest);
		//new Select(driver.findElement(By.id("CtlApplyForm1_ddProgramInterest"))).selectByIndex(2);
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLRFname")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLRFname")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLRFname")).sendKeys(Keys.BACK_SPACE);
		
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLRFname")), TestData.Lead_First_Name).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLRLname")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLRLname")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLRLname")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLRLname")),TestData.Lead_Last_Name).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLREmail")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLREmail")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLREmail")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLREmail")),TestData.Email).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLRHPhone")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLRHPhone")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLRHPhone")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLRHPhone")), TestData.Home_Phone).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLNZip")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLNZip")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLNZip")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLNZip")), TestData.Zip_Code).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLRAddress")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLRAddress")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLRAddress")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLRAddress")), TestData.Addr_Line_1).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("CtlApplyForm1_XMLRCity")).clear();
		driver.findElement(By.id("CtlApplyForm1_XMLRCity")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("CtlApplyForm1_XMLRCity")).sendKeys(Keys.BACK_SPACE);
		builder.sendKeys(driver.findElement(By.id("CtlApplyForm1_XMLRCity")),TestData.City).build().perform();
		Thread.sleep(1000);
		
		new Select(driver.findElement(By.id("CtlApplyForm1_XMLNState"))).selectByVisibleText(TestData.State);
		
				//----------------
		builder.click(driver.findElement(By.cssSelector("input[value=\"NO\"][name=\"CtlApplyForm1:XMLRanotherProgram\"]"))).build().perform();
		Thread.sleep(2000);
		builder.click(driver.findElement(By.cssSelector("input[value=\"NO\"][name=\"CtlApplyForm1:XMLRAttendedLawSchool\"]"))).build().perform();
		Thread.sleep(2000);
		builder.click(driver.findElement(By.cssSelector("input[value=\"NO\"][name=\"CtlApplyForm1:XMLRBachelors\"]"))).build().perform();
		Thread.sleep(2000);
		builder.click(driver.findElement(By.cssSelector("input[value=\"No\"][name=\"CtlApplyForm1:XMLRTCPA_Disc\"]"))).build().perform();
		Thread.sleep(2000);
		
		//-----------------------------
		
		
		Thread.sleep(2000);
		}catch(Exception e)
		{
		driver.quit();
		}
		
		//click Add button
		//driver.findElement(By.id("CtlApplyForm1_btnAddLead")).sendKeys(Keys.ENTER);
		builder.click(driver.findElement(By.id("CtlApplyForm1_btnAddLead"))).build().perform();
					System.out.println("No of Firefox Windows Open:- "+driver.getWindowHandles().size());			
		for (String handle : driver.getWindowHandles()) {
		    driver.switchTo().window(handle);
		    System.out.println("handle---->"+handle);
		}
		
		
		driver.switchTo().window(winHandle);
		driver.switchTo().frame("Orion");

		//verify lead created successfully
					
		String a1= TestData.Lead_First_Name+", "+TestData.Lead_Last_Name;
		System.out.println("Value of a-->"+a1);
		
		for (int i=0;i<20;i++)
		{
			driver.navigate().refresh();		
			driver.switchTo().frame("Orion");	
			driver.findElement(By.xpath("//*[@id='_ctl2_TopNavRow']/td[2]")).click();
			driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
			driver.findElement(By.name("btnGo")).click();
			driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
			System.out.println("Go button clicked");
			try{
				Thread.sleep(8000);
			}catch(InterruptedException e){System.out.println("Interrupted Execption");}
			System.out.println("After sleep");
			/*try{
				driver.switchTo().frame("Orion");
				System.out.println("orion frame found");
			}catch(Error e){System.out.println("Orion-Frame not found"+e.getMessage());}
			*/
			/*WebDriverWait wait = new WebDriverWait(driver, 10);
			Boolean element = wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//tr[2]/td[4]"), a1));*/
			try{
			if(driver.findElement(By.xpath("//tr[2]/td[4]/a/span")).getText().contains(a1))
			//if(element)
			break;
			}catch(Exception e){System.out.println("Unable to find the lead trying again"+e.getMessage());}
			
		}
		
		System.out.println("Leads link in mail window clicked");
			
		
		if (driver.findElement(By.xpath("//tr[2]/td[4]")).getText().contains(a1))
		{
			System.out.println("Lead created successfully"); 
			Reporter.log("Lead created successfully",3, true);
			
			
		}
		else
			System.out.println("Lead not created");
			Reporter.log("Lead not created",3);
		//driver.quit();

  }
  
  @Test(groups={"tillAppReceive"}, dependsOnMethods = {"addLead"}, alwaysRun=false)
  public void verifyLead() throws InterruptedException, ParseException{
	  //NumberFormat nf =NumberFormat.getInstance();
	  System.out.println("1. VERIFY_LEAD_&_SAVE_INFO");
	  Reporter.log("1. VERIFY_LEAD_&_SAVE_INFO");
	  //Click student manager window icon
	  driver.findElement(By.cssSelector("img[alt=\"Schedule Appointment/Interview\"]")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  driver.switchTo().window("StudentInfo");
	  
	  //navigate to contact information pop up
	  driver.findElement(By.cssSelector("a.subnav")).click();
	  Thread.sleep(4000);
	  	   
	  if (driver.findElement(By.id("_ctl3_cifirstname")).getAttribute("value").contains(TestData.Lead_First_Name)) 	  
		{System.out.println("first name match"); Reporter.log("first name match", true);} 
	  else {System.out.println("First Name not matched");Reporter.log("First Name not matched", true);}
	      
	  if(driver.findElement(By.id("_ctl3_ciemail")).getAttribute("value").contains(TestData.Email))
		  {System.out.println("last name match");Reporter.log("last name match", true); }
	  else {System.out.println("Last Name not matched");Reporter.log("Last Name not matched", true);}
	  if(driver.findElement(By.id("_ctl3_cizip")).getAttribute("value").contains(TestData.Zip_Code))
		  {System.out.println("Zip COde match");Reporter.log("Zip COde match", true);}
	  else {System.out.println("Zip Code not matched");Reporter.log("Zip Code not matched", true);}
	  
	   
	  varLeadId = Integer.parseInt(driver.findElement(By.id("_ctl3_lblcileadid")).getText());
	  System.out.println("Lead id is :- "+varLeadId);
	
	  driver.findElement(By.id("_ctl3_cizip")).clear();
	  driver.findElement(By.id("_ctl3_cizip")).sendKeys(Keys.SPACE);
	  driver.findElement(By.id("_ctl3_cizip")).sendKeys(Keys.BACK_SPACE);
	  new Actions(driver).sendKeys(driver.findElement(By.id("_ctl3_cizip")), TestData.Zip_Code).build().perform();
	  Thread.sleep(1000);
	  	  
	  //select program
	  new Select(driver.findElement(By.id("_ctl3_dpProgram"))).selectByVisibleText("Executive JD Program - No Emphasis");
	  
	  driver.findElement(By.id("_ctl3_ciaddr")).clear();
	  driver.findElement(By.id("_ctl3_ciaddr")).sendKeys(Keys.SPACE);
	  driver.findElement(By.id("_ctl3_ciaddr")).sendKeys(Keys.BACK_SPACE);
	  new Actions(driver).sendKeys(driver.findElement(By.id("_ctl3_ciaddr")), TestData.Address_Line_2).build().perform();
	  
	  //Click Save button
	  driver.findElement(By.id("_ctl3_btnSaveAndClose")).sendKeys(Keys.ENTER);
	  driver.findElement(By.id("_ctl3_btnSaveAndClose")).click();
	  Thread.sleep(4000);
	  //Date Select
	  new Select(driver.findElement(By.id("_ctl3_dpStartDate"))).selectByIndex(1);

	  //Click Save button
	  driver.findElement(By.id("_ctl3_btnSaveAndClose")).sendKeys(Keys.ENTER);
	  driver.findElement(By.id("_ctl3_btnSaveAndClose")).click();
	  Thread.sleep(4000);
	  driver.close();
	  
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");

	  
  }
  
  @Test(groups={"tillAppReceive"}, dependsOnMethods = {"verifyLead"}, alwaysRun=false)
  public void contactAttempted() throws InterruptedException{
	  System.out.println("2. MOVE_LEAD_TO_CONTACT_ATTEMPT");
	  Reporter.log("2. MOVE_LEAD_TO_CONTACT_ATTEMPT");
	  driver.findElement(By.id("con"+varLeadId)).click();
	  
	  driver.switchTo().window("Nothing");
	  new Select(driver.findElement(By.id("ddStatusReasons"))).selectByVisibleText("Left Message");
	  
	  driver.findElement(By.id("txtNote")).sendKeys("This is comment");
	  driver.findElement(By.id("btnSaveAndClose")).click();
	  Thread.sleep(4000);
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  
	  driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
	  Thread.sleep(5000);
	  
  }

@Test(groups={"tillAppReceive"}, dependsOnMethods = {"contactAttempted"}, alwaysRun=false)
	 
  public void moveLeadToContact() throws InterruptedException, ParseException{
	// NumberFormat nf = NumberFormat.getInstance();
	  System.out.println("3. MOVE_LEAD_TO_CONTACT");
	  Reporter.log("3. MOVE_LEAD_TO_CONTACT");
	  driver.findElement(By.cssSelector("img[alt=\"Schedule Appointment/Interview\"]")).click();
	  //Contact Information Click
	  driver.switchTo().window("StudentInfo");
	  
	  Thread.sleep(3000);
	  driver.findElement(By.cssSelector("a.subnav")).click();
	  							   
	  if(driver.findElement(By.id("_ctl3_cilastname")).getAttribute("value").contains(TestData.Lead_Last_Name))
	  	{System.out.println("First Name matched");Reporter.log("First Name matched");}
	  else {System.out.println("First Name not matched");Reporter.log("First Name not matched");}
		  
	  if(driver.findElement(By.id("_ctl3_ciemail")).getAttribute("value").contains(TestData.Email))
		  {System.out.println("Email Addr Matched");Reporter.log("Email Addr Matched");} 
		  else {System.out.println("Email Addr Not Matched");Reporter.log("Email Addr Not Matched"); }
	  if(driver.findElement(By.id("_ctl3_cizip")).getAttribute("value").contains(TestData.Zip_Code))
	  {System.out.println("Zip Code Matched");Reporter.log("Zip Code Matched");}
	  else {System.out.println("Zip Code Not Matched");Reporter.log("Zip Code Not Matched");}
	 
	  if(driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText().contains("Contact Attempted"))
	  { System.out.println("lead Status chaged to: Contact Attempted");Reporter.log("lead Status chaged to: Contact Attempted");}
	  else {System.out.println("lead Status not chaged to: Contact Attempted");Reporter.log("lead Status not chaged to: Contact Attempted");} 
	  
	  //Click Save button
	  driver.findElement(By.id("_ctl3_btnStatusChange")).sendKeys(Keys.ENTER);
	  driver.findElement(By.id("_ctl3_btnStatusChange")).click();
	  Thread.sleep(4000);
	  //Check the lead status "Contact"
	  if(driver.findElement(By.id("_ctl3_lblcicurrentstatus")).getText().contains("Contact"))
		  {System.out.println("lead Status change to Contact");Reporter.log("lead Status change to Contact");}
	  	else{System.out.println("lead Status not change to Contact");Reporter.log("lead Status not change to Contact");}
	  
	  driver.close();
	  
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  
	  
	 }
  
  @Test(groups={"tillAppReceive"}, dependsOnMethods={"moveLeadToContact"}, alwaysRun=false)
	 // @Test
  public void intervComplete() throws InterruptedException{
	  
	  System.out.println("4. MOVE_LEAD_TO_INTERVIEW_COMPLETE.Html");
	  Reporter.log("4. MOVE_LEAD_TO_INTERVIEW_COMPLETE.Html");
	  driver.findElement(By.cssSelector("img[alt=\"Schedule Appointment/Interview\"]")).click();
	  Thread.sleep(4000);
	  
	  driver.switchTo().window("StudentInfo");
	  
	  driver.findElement(By.cssSelector("strong > a.subnav")).click();
	  Thread.sleep(4000);
	  
	  new Select(driver.findElement(By.id("_ctl3_dpInterviewHour"))).selectByVisibleText("10");
	  new Select(driver.findElement(By.id("_ctl3_dpInterviewMinute"))).selectByVisibleText("30");
	  //save button click
	  driver.findElement(By.id("_ctl3_btnSave")).sendKeys(Keys.ENTER);
	  driver.findElement(By.id("_ctl3_btnSave")).click();
	  Thread.sleep(4000);
	  
	  driver.findElement(By.cssSelector("a.subnav")).click();
	  Thread.sleep(4000);
	  
	  if (driver.findElement(By.cssSelector("#_ctl3_lblcicurrentstatus")).getText().contains("Interview Scheduled"))
	  	{ System.out.println("Lead Status changed to --  Interview Scheduled");Reporter.log("Lead Status changed to --  Interview Scheduled");}
	  else {System.out.println("Lead Status not changed to --  Interview Scheduled");Reporter.log("Lead Status not changed to --  Interview Scheduled");}
	  
	  driver.findElement(By.id("_ctl3_btnStatusChange")).sendKeys(Keys.ENTER);
	  driver.findElement(By.id("_ctl3_btnStatusChange")).click();
	  
	  Thread.sleep(4000);
	  
	  if (driver.findElement(By.cssSelector("#_ctl3_lblcicurrentstatus")).getText().contains("Interview Complete"))
		  {System.out.println("Lead Status changed to --  Interview Complete");Reporter.log("Lead Status changed to --  Interview Complete");}
	  	else {System.out.println("Lead Status not changed to --  Interview Complete");Reporter.log("Lead Status not changed to --  Interview Complete");}
	  driver.findElement(By.id("_ctl3_btnStatusChange")).click();
	  
	  driver.close();
	  
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	    
  }
  
  @Test(groups={"tillAppReceive"}, dependsOnMethods = {"intervComplete"}, alwaysRun=false)
  public void examPendingRece() throws InterruptedException
  {
	  
	  System.out.println("5. MOVE_LEAD_TO_Exam_Pending_Received.html");
	  Reporter.log("5. MOVE_LEAD_TO_Exam_Pending_Received.html");
	  //Search Link click
	  driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
	  Thread.sleep(4000);
	  
	  driver.findElement(By.id("tbSearchAllLeadID")).sendKeys(Integer.toString(varLeadId));
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  Thread.sleep(6000);
	  
	  System.out.println("Search button clicked");
	  //driver.findElement(By.id("nm"+Integer.toString(varLeadId))).click();
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  driver.findElement(By.id("nm"+varLeadId)).click();
	  driver.switchTo().window("StudentInfo");
	  
	  //OEC UID and PWD is received from the SM window
	  varOECUID = driver.findElement(By.id("_ctl3_lblOECUsername")).getText();
	  varOECPWD = driver.findElement(By.id("_ctl3_lblOECPassword")).getText();
	  System.out.println("OEC UID:---->"+varOECUID+ "Password:--->"+varOECPWD);
	  Reporter.log("OEC UID:- "+varOECUID);
	  Reporter.log("OEC PWD:-"+varOECPWD);
	  
	  
	  
	  driver.findElement(By.id("_ctl3_btnStatusChange")).click();
	  Thread.sleep(4000);
	  
	  if(driver.findElement(By.cssSelector("#_ctl3_lblcicurrentstatus")).getText().contains("Exam Pending"))
		  {System.out.println("Lead Status changed to:---- Exam Pending");Reporter.log("Lead Status changed to:---- Exam Pending");}
	  else{System.out.println("Lead Status not changed to:---- Exam Pending");Reporter.log("Lead Status not changed to:---- Exam Pending");}
	  
	  driver.findElement(By.id("_ctl3_btnSaveAndClose")).click();
	  Thread.sleep(4000);
	  
	  if(driver.findElement(By.cssSelector("#_ctl3_lblcicurrentstatus")).getText().contains("Exam Received"))
		  {System.out.println("Lead Status changed to:---- Exam Received");Reporter.log("Lead Status changed to:---- Exam Received");}else
			  {System.out.println("Lead Status not changed to:---- Exam Received");Reporter.log("Lead Status not changed to:---- Exam Received");}
	  
	  
	  driver.close();
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  
	  if(driver.findElement(By.id("ss"+varLeadId)).getText().contains("Exam Received"))
		  {System.out.println("Lead Status correct displaying in search result grid as- Lead Status");Reporter.log("Lead Status correct displaying in search result grid as- Lead Status");}else
			  {System.out.println("Lead Status in-correct displaying in search result grid as- Lead Status");Reporter.log("Lead Status in-correct displaying in search result grid as- Lead Status");}
	  }
  /*
  @Test(groups={"tillAppReceive"}, dependsOnMethods={"examPendingRece"}, alwaysRun=false)
  public void efcReceived() throws InterruptedException{
	  System.out.println("6. MOVE_LEAD_TO_EFC_RECEIVED.Html");
	  Reporter.log("6. MOVE_LEAD_TO_EFC_RECEIVED.Html");
	  //Search Link click
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  
	  
	  driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
	  
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	    
	  driver.findElement(By.id("tbSearchAllLeadID")).sendKeys(Integer.toString(varLeadId));
	  driver.findElement(By.id("btnSearchAllFilter")).click();
	  driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	  
	  //driver.switchTo().frame("Orion");
	  //driver.findElement(By.id("nm"+Integer.toString(varLeadId))).click();
	  driver.findElement(By.id("nm"+varLeadId)).click();
	  Thread.sleep(4000);
	  driver.switchTo().window("StudentInfo");
	  
	  varOECUID = driver.findElement(By.id("_ctl3_lblOECUsername")).getText();
	  varOECPWD = driver.findElement(By.id("_ctl3_lblOECPassword")).getText();
	  System.out.println("OEC UID:---->"+varOECUID+ "Password:--->"+varOECPWD);
	  Reporter.log("OEC UID:- "+varOECUID);
	  Reporter.log("OEC PWD:-"+varOECPWD);
	  
	  driver.findElement(By.xpath("//a[contains(text(),'OEC')]")).click();
	  Thread.sleep(4000);
	  driver.findElement(By.cssSelector("td.datagridcell > a")).click();
	  Thread.sleep(4000);
	  
	  driver.findElement(By.id("dgEFCTextBox")).clear();
	  driver.findElement(By.id("dgEFCTextBox")).sendKeys("1000");
	  driver.findElement(By.cssSelector("td.datagridcell > a")).click();
	  Thread.sleep(4000);
	  System.out.println("update button clicked");
	  driver.findElement(By.cssSelector("a.subnav")).click();
	  Thread.sleep(4000);
	  
	  System.out.println("Contact Information button clicked");
	  try{
		  Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'EFC Received')]")).getText(), "EFC Received", "Lead Status not changed to:- EFC Received ");
	  	  Reporter.log("Lead Status changed to:- EFC Received ");System.out.println("Lead Status changed to:- EFC Received ");
	  }catch(Exception e){
		  Reporter.log("Lead Status not changed to:- EFC Received "+e.getMessage());
		  System.out.println("Lead Status not changed to:- EFC Received "+e.getMessage());
	  }
	  if(driver.findElement(By.xpath("//a[contains(text(),'EFC Received')]")).getText().contains("EFC Received"))
		  							  
		  {System.out.println("Lead Status changed to EFC Received");Reporter.log("Lead Status changed to EFC Received");}
	  	else {System.out.println("Lead Status not changed to EFC Received");Reporter.log("Lead Status not changed to EFC Received");}
	  
	  driver.switchTo().window(winHandle);
	  driver.switchTo().frame("Orion");
	  //driver.close();
	
	 
	  
  }*/
  
  @AfterClass
  public void afterClass() {
	 driver.quit();
	  
  }

}
