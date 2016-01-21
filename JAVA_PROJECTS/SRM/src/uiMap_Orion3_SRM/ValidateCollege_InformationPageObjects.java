package uiMap_Orion3_SRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ValidateCollege_InformationPageObjects {
	

		
		public ValidateCollege_InformationPageObjects(WebDriver driver) {
			
			//Initialize A SRMLeadFlowPageObjects.
			PageFactory.initElements(driver, this);
		}
	

	//State DropDown
	@FindBy(how=How.NAME, using="State")
	public WebElement ddState;
   
   
    //Drop Down City
    
    @FindBy(how=How.NAME, using = "City")
	public WebElement ddCity;	
    
	//Select College
	@FindBy(how=How.NAME, using="College")
	public WebElement ddSelectCollege;
	
    //Drop Down Degree Type
   @FindBy(how=How.NAME, using="DegreeType")
	public WebElement ddDegreeType;
	
	//Drop Down Area Of Study
	
	@FindBy(how=How.NAME, using = "AreaOfStudy")
	public WebElement ddAreaOfStudy;
	
	
	
	//Attended From 1

	@FindBy(how=How.NAME, using = "StartDate")
	public WebElement txtAttendedFrom1;
	
	//Attended From 2
	
	@FindBy(how=How.NAME, using = "EndDate")
	public WebElement txtAttendedFrom2;
	
	//Transfer Credit Radio Button
	
	@FindBy(how=How.ID, using = "transferCreditsNo")
	public WebElement rbtnTransferCreditsNo;
	
	//Done Button
	
	@FindBy(how=How.ID, using = "done")
	public WebElement btnDone;
	
	//College Text
	
	@FindBy(how=How.XPATH, using = ".//*[@id='college-grid']/div[1]/table/tbody/tr[2]/td[3]")
	public WebElement txtCollege;
	
	//Oppurtunities
	
	@FindBy(how=How.XPATH, using = ".//*[@id='Opportunity_body']/table/tbody/tr[2]/th/a")
	public WebElement lnkOppurtunitiesEdit;
	
	
	
	//Mouse Hover to Student Enrollment Portal Data
	
    @FindBy(how=How.XPATH, using="//span[text()='Student Enrollment Portal Data']")
    public WebElement lnkStudentEnrollmentPortalData;
    
    
    @FindBy(how=How.XPATH, using="//th/a")
    public WebElement lnkSEPDataNumber;
    
    //College Information
    
    @FindBy(how=How.XPATH, using=".//*[@class='pbBody']/table/tbody/tr[2]/th/a")
    public WebElement lnkCollegeInformation;
    
    //Validate Billing Method ID
    
    @FindBy(how=How.XPATH, using="//table/tbody/tr[12]/td[3]")
    public WebElement txtBilingMethodID;
    
    
  
    //Opportunity in Progress Link
    
    @FindBy(how=How.ID, using="opp11_ileinner")
    public WebElement lnkOpportunity;
    
    
    //Stage Remarketing
    
    @FindBy(how=How.ID, using="opp11")
    public WebElement ddStageRemarketing;
    
    //OK button
    
    @FindBy(how=How.XPATH, using=".//*[@id='InlineEditDialog_buttons']/input[1]")
    public WebElement btnOK;
    
    //Remarketing Reason DropDown
    
    @FindBy(how=How.ID, using="00Ni000000EaSQ6")
    public WebElement ddRemarketingReason;
    
    
    //Remarketing Sub Reason
    
    @FindBy(how=How.ID, using="00Ni000000EaSQ7")
    public WebElement ddRemarketingSubReason;
    
    
    //Save Button
    
    @FindBy(how=How.NAME, using="save")
    public WebElement btnSave;
    
    //Sign in SEP Login link
    
    @FindBy(how=How.ID, using="signIn")
    public WebElement lnkSEPSignin;
    
  //Sign in SEP Sign Out link
    
    @FindBy(how=How.ID, using="signOut")
    public WebElement lnkSEPSignOut;
    
    
    
    //SEP Username
    
    @FindBy(how=How.ID, using="UserName")
    public WebElement txtSEPUsername;
    
  //SEP Password
    
    @FindBy(how=How.ID, using="txtPassword")
    public WebElement txtSEPPassword;
    
    //Button SignIn
    
    @FindBy(how=How.XPATH, using=".//*[@id='log-on']/div[3]/div[2]/div[2]/button")
    public WebElement btnSignIn;
    
    //Start Date Text
    
    @FindBy(how=How.XPATH, using=".//*[@id='00Ni000000BVIrJ_ileinner']")
    public WebElement txtStartDate;
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
