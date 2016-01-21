package uiMap_Orion3_SRM;
//Import files
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class DuplicateLeadCompletionPageObjects {
	
	public DuplicateLeadCompletionPageObjects(WebDriver driver){
	
    //Initialize A Duplicate Lead Completion Page Objects 
	PageFactory.initElements(driver, this);
}
	
	// login credentials for iwd username 
	@FindBy(how=How.XPATH, using="html/body/div[1]/div[5]/div[2]/div[1]/form/div[1]/input")
	public WebElement Username;
	
	// login credentials for iwd Password
	@FindBy(how=How.XPATH, using="html/body/div[1]/div[5]/div[2]/div[1]/form/div[2]/input[1]")
	public WebElement Password;
	
	//submit user credentials
	@FindBy(how=How.XPATH, using="html/body/div[1]/div[5]/div[2]/div[1]/form/div[4]/input")
	public WebElement Submit;
	
	//Genesys  Global Task List
	@FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[3]/div[2]/div/div[3]/div[2]/div/div/div[7]/a")
	public WebElement GlobalTaskList;
	
	
	//Kaplan Test link from Global list
	@FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[3]/div[2]/div/div[5]/div[2]/div/div[4]/div[2]/div/div/span/table[1]/tbody/tr/td[2]/div/a ")
	public WebElement kaplanTESTLink;
	
	
	//Capture SY ID 
	@FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[1]/div[2]/div/div/span/div/div/div[1]/table/tbody/tr/td[7]/div/input")
	public WebElement CaptureSYStudentID;
	public String strCaptureSYStudentID = "CaptureSYStudentID";
	
	//Find Capture SY ID  
	@FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[1]/div[2]/div/div/span/div/div/div[1]/table/tbody/tr/td[8]/input")
	public WebElement FindCapturedSYID;
	
	
	//Student Record for the Searched Captured ID 
	@FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[1]/div[2]/div/div/span/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div[3]/a/span")
	public WebElement CapturedIDRecord;
	
	
	//Verifying Email
	 @FindBy(how=How.XPATH, using="//div[@id='taskAttribute_Email']/span")
	 public WebElement VerifyEmail;
	 
	 
	 
	// Verifying Lead First Name 
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[34]/div[2]/div")
	 public WebElement VerifyLeadFirstName;
	 
	 
	 //Lead Last Name Label 
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[33]/div[1]")
	 public WebElement LeadLastName;
	 
	 
	 
	 //Verify Lead Last Name  
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[33]/div[2]/div/span")
	 public WebElement VerifyLeadLastName;
	 
	 
	 //Email ID Label
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[11]/div[1]")
	 public WebElement Email;
	 
	 
	 //Verify Email Id 
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[11]/div[2]/div/span")
	 public WebElement VerifyEmailID;
	 
	 
	 //Sy Student ID label
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[65]/div[1]")
	 public WebElement SyStudendID;
		 
	 
	 //Verify Sy Student ID 
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[65]/div[2]/div/span")
	 public WebElement VerifySyStudendID;
	 
	 
	 //phone number label 
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[52]/div[1]")
	 public WebElement Phone1;
	 
	 
	 // Verify phone number
	 @FindBy(how=How.XPATH, using="html/body/form[2]/div[2]/div/div[6]/div[2]/div/div[7]/div[2]/div/div/div[2]/div[2]/div/div[3]/div[52]/div[2]/div/span")
	 public WebElement VerifyPhone1;
	 
	 
	 //Mk Lead Import ID Label 
	 @FindBy(how=How.XPATH, using="//div[@id='taskAttribute_MkLeadImportID']/span")
	 public WebElement MkLeadImportID;
	 
	 
	//school id text
	 @FindBy(how=How.XPATH, using="//div[@id='taskAttribute_CurrentSchoolID']")
	 public WebElement VerifyCurrentSchoolID;
	 
	 
	 
	 
	 
	 
	 
	 
	 
		 
	
	
}
