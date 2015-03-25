package uiMap_Orion3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commonfunctions.UserExtension;

public class StudentManagerPageObjects {
public StudentManagerPageObjects(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
	}

public String sStudentManager_WindowName = "StuMgr";
//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Admissions")
	public WebElement lnkAdmissionsTab;
	
	//Admissions Manager
	@FindBy(how=How.LINK_TEXT, using="Admissions Manager")
	public WebElement lnkAdmissionsManager;
	
	//Add New Lead link
	
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_lnkAddNewLead")
	public WebElement lnkAddNewLead;
	
	//New Lead Tab
	@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_ActionViewControl1_queueList_ctl01_lbQueueNav']/span")
	public WebElement NewLeadTab;
	public String strNewLeadTab = "New Leads";

	//Contact Information Tab
	@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[1]/a/span")
	public WebElement ContactInformationTab;
    public String strContactInformation = "Contact Information"; 
    
    //Address Line1
    
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbAddressLine1")
	public WebElement addressLine1;
	
	//City
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbCity")
	public WebElement city;
	
   
    //Program DropDown
    
    @FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlProgramRollups")
	public WebElement ProgramDropDown;	
	//public By ProgramDropDownValue = new By.ById("ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlProgramRollups");
    public String sProgramDropDownText="Select One";
	
	//Lead Classification DropDown
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlLeadClassification")
	public WebElement leadClassificationDropDown;
	public String sLeadClassificationText = "Non-applicable";
	
	//Start Date
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_ctl01_ctl04_ddlStartDate")
	public WebElement startDateDropDown;
	
	//Contact Button
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")
	public WebElement btnContact;
	public String strContactButton = "Contact";

	//Save button
	@FindBy(how=How.ID, using= "ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnSave")
	public WebElement btnSave;
	
	
	//Click on Admissions Tab --------> Admissions Manager
		public StudentManagerPageObjects ClickAdmissionsManager(WebDriver driver)
		{
			UserExtension.MouseOver(driver, lnkAdmissionsTab);
			UserExtension.IsElementPresent(driver, lnkAdmissionsManager);
			lnkAdmissionsManager.click();
			StudentManagerPageObjects objPage = new StudentManagerPageObjects(driver);
			return(objPage);
		}
		
		// First Link in table
		
		@FindBy(how=How.XPATH, using="//*[@id='maintable']/tbody[3]/tr[1]/td[3]/a")
			
		public WebElement lnkFirstLeadInTable;
		
		//Appointment Tab
		@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[2]/a/span")
		public WebElement lnkAppointmentTab;
	    public String strAppointmentText = "Appointment";
	    
	    //Appointment Notes text field
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbnotes")
		public WebElement txtAppointmentNotes;
	   
	    
	    //Appointment Date Text
	    
	    @FindBy(how=How.XPATH, using=".//*[@id='tableCalendar']/tbody/tr[1]/td")
		public WebElement txtAppointmentDate;
	    public String strAppointmentDateText = "Appointment Date";
	    
	    //Appointment Save button
	    
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnSave")
		public WebElement btnAppointmentSave;
	   
	    
	    //Appointment Yes link
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnSaveYes")
		public WebElement btnYes;
	   
	   
	    //Notes Tab
	    @FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[3]/a/span")
		public WebElement lnkNotes;
	    public String strNotesTabText = "Notes";
	    
	    //AddNotes text field
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_txtNote")
		public WebElement txtAddNotes;
	    
	    //Add Note button
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnOkAddNote")
		public WebElement btnAddNote;
	    //Check Interview Schedule text on Contact Information Tab
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_lblAdmissionsStatus")
		public WebElement txtInterviewSchedule;
	   
	    //Interview complete link
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")
		public WebElement btnInterviewComplete;
	    
	    //Application Received tab
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")
		public WebElement btnApplicationReceived;
	    public String strApplicationReceivedText = "Application Received";
	    
	    //Button Enrollment Confirmed
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_TakeNextAction1_btnTakeNextAction")
		public WebElement btnEnrollmentConfirmed;
	    public String strEnrollemntConfirmedText = "Enrollment Confirmed";
	    
	    //Text Enrollment Confirmed
	    
	    @FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_lblAdmissionsStatus")
		public WebElement txtEnrollmentConfirmed;
	    public String EnrollemntConfirmedText = "Enrollment Confirmed";
	    
	  
	    
	    
	    
	    
	    
	    
		
	    
	    
	    
	
}