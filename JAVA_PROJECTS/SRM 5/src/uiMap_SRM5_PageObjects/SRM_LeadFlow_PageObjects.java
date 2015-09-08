package uiMap_SRM5_PageObjects;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class SRM_LeadFlow_PageObjects {
		
		public SRM_LeadFlow_PageObjects(WebDriver driver) {
			
			//Initialize A SRMLeadFlowPageObjects.
			PageFactory.initElements(driver, this);
		}
		
		//Click on created Lead
		
		@FindBy(how=How.XPATH, using=".//*[@id='Lead_body']/table/tbody/tr[2]/th/a")
		public WebElement clickOnCreatedLead;
		
	
		//Edit button
		@FindBy(how=How.XPATH, using=".//*[@id='topButtonRow']/input[3]")
		public WebElement btnEdit;
		

		//Inquiry Status DropDown
		@FindBy(how=How.ID, using="lea13")
		public WebElement ddInquiryDropDown;
	                                                

		//Save Button
		@FindBy(how=How.NAME, using="save")
		public WebElement btnSave;
		
		//Mouse Hover to Activity History
		
	     @FindBy(how=How.XPATH, using="//span[text()='Activity History']")
	     public WebElement lnkActivityHistory;
	
	     //Link Activity History
				
		//@FindBy(how=How.XPATH, using=".//*[@id='00Qf000000Alskv_RelatedHistoryList_link']/span")
		//public WebElement lnkActivityHistory;
		
		//Send SEP Email Link
		
		@FindBy(how=How.NAME, using="send_sep_email")
		public WebElement lnkSendSEPEmail;
		
		//SEP URL
		
		@FindBy(how=How.XPATH, using=".//*[@id='00Ni000000Ea5pn_ileinner']/a")
		public WebElement lnkSEPURL;
		
		//New Password
		
		@FindBy(how=How.ID, using="txtNewPassword")
		public WebElement txtNewPassword;
		
		//Confirm Password
		
		@FindBy(how=How.ID, using="txtConfirmPassword")
		public WebElement txtConfirmPassword;
		
		//OK Button
		
		@FindBy(how=How.ID, using="change-password")
		public WebElement btnOK;
		
		//Permanent Street Address
		@FindBy(how=How.NAME, using="PermanentStreetAddress")
		public WebElement txtPermanentStreetAddress;
		
		//City
		
		@FindBy(how=How.NAME, using="PermanentCity")
		public WebElement txtPermanentCity;
		
		//Shipping Address checkBox
		
		@FindBy(how=How.ID, using="copyAddress")
		public WebElement checkBoxShippingAddress;
		

		//Save and Continue Button
		
		@FindBy(how=How.ID, using="save-continue-button")
		public WebElement rbtnSaveAndContinue;
		
		
		//Date Of Birth
		
		@FindBy(how=How.NAME, using="DateOfBirth")
		public WebElement txtDateofBirth;
		
		//Gender
		
		@FindBy(how=How.NAME, using="Gender")
		public WebElement ddGender;
		
		//Martial Status
		
		@FindBy(how=How.NAME, using="MaritalStatus")
		public WebElement ddMaritalStatus;
		
		//Citizen ship status
		@FindBy(how=How.NAME, using="CitizenshipStatus")
		public WebElement ddCitizenshipStatus;
		
		//Citizenship Country
		
		@FindBy(how=How.NAME, using="CountryOfCitizenship")
		public WebElement ddCountryCitizenship;
		
		
		
		
	    //Social Security Number
		
		@FindBy(how=How.NAME, using="SocialSecurityNumber")
		public WebElement txtSocialSecurityNumber;
		
		
		//Radio Button Incarcerated
		
		@FindBy(how=How.ID, using="WasIncarcerated_No")
		public WebElement rbtnIncarcerated_No;
		
		//Parents First Name
		
		@FindBy(how=How.ID, using="ParentFirstName")
		public WebElement txtParentsFirstName;
		
		//Parents Last Name
		
		@FindBy(how=How.ID, using="ParentLastName")
		public WebElement txtParentsLastName;
		
		//Parents Email Address
		
		@FindBy(how=How.ID, using="ParentEmail")
		public WebElement txtParentsEmail;
		
		//Radio Button Hispanic/Latino
		
		@FindBy(how=How.NAME, using="01R")
		public WebElement rbtnHispanic_No;
		
		//Dependent of US Military Yes
		
		@FindBy(how=How.NAME, using="01S")
		public WebElement rbtnUsMilitary_Yes;
		
		
	    //Dependent of US Military No
		
		@FindBy(how=How.NAME, using="01T")
		public WebElement rbtnUsMilitary_No;
		
		//Employer Information Radio Button
		
		@FindBy(how=How.ID, using="IsEmployed_Yes")
		public WebElement rbtnEmployer_Yes;
		
		
		@FindBy(how=How.ID, using="IsEmployed_No")
		public WebElement rbtnEmployer_No;
		
		
		@FindBy(how=How.ID, using="WasIncarcerated_No")
		public WebElement rbtnIncarcerate1d_No;
		
		//Authentication Race
		
		@FindBy(how=How.ID, using="AI")
		public WebElement checkBoxAmericanIndian;
		
	   //Progress Image
		
		@FindBy(how=How.ID, using="p.ng-binding")
		public WebElement txtProgressImage;
		
	     //High School Type
	
	        @FindBy(how=How.NAME, using="HighSchoolType")
	        public WebElement ddlHighSchoolType;
	        
	        //High School State
	        
	        @FindBy(how=How.NAME, using="State")
	        public WebElement ddlHighSchoolState;
	        
	        //Graduation Certificate Date
	        
	        @FindBy(how=How.NAME, using="CertificateDate")
	        public WebElement txtGraduationCertificate;
	        
	        //Terms and Condition Checkbox
	        
	        @FindBy(how=How.ID, using="IsAcknowledged")
	        public WebElement checkboxTerms;
	        
	        //Attended College or University
	        
	        @FindBy(how=How.XPATH, using=".//*[@id='form-content']/div[2]/form[1]/div[1]/div[3]/div[1]/div[1]/div/div[2]/div[2]/div/span/input")
	        public WebElement rbtnAttendedCollege_No;
	        
	        
	        //Equivalent Credit Radio Button
	        
	        @FindBy(how=How.ID, using="CreditNo")
	        public WebElement rbtnEquivalentCredit_No;
	        
	        
	        //Area of Study
	        
	        @FindBy(how=How.NAME, using="AreaOfStudy")
	        public WebElement ddAreaofStudy;
	        
	        //Degree Level
	        
	        @FindBy(how=How.NAME, using="DegreeLevel")
	        public WebElement ddDegreeLevel;
	        
	        //Program of Study
	        
	        @FindBy(how=How.NAME, using="ProgramOfStudy")
	        public WebElement ddProgramofStudy;
	        
	        
	        //Emphasis Area Required
	        
	        @FindBy(how=How.NAME, using="EmphasisArea")
	        public WebElement ddEmphasisArea;
	        
	        //Enrollment Type
	        
	        @FindBy(how=How.NAME, using="EnrollmentType")
	        public WebElement ddEnrollmentType;
	        
	        //Terms Start Date
	        
	        @FindBy(how=How.NAME, using="TermStartDate")
	        public WebElement ddTermStartDate;
	        
	        
	        //Primary Funding Time
	        
	        @FindBy(how=How.NAME, using="PrimaryFundingSource")
	        public WebElement ddPrimaryFundingTime;
	        
           //Question section
	        
	        @FindBy(how=How.ID, using="100")
	        public WebElement rbtMyInterest;
	        
	        //My Goals
	        
	        @FindBy(how=How.ID, using="105")
	        public WebElement rbtMyGoals;
	        
	        //One Goal to another
	        
	        @FindBy(how=How.ID, using="110")
	        public WebElement rbtOneGoaltoAnother;
	        
	        //More Projects
	        
	        @FindBy(how=How.ID, using="115")
	        public WebElement rbtMoreProjects;
	        
	        //Stop for Finishing Project
	        

	        @FindBy(how=How.ID, using="120")
	        public WebElement rbtStopFinishingProject;
	        
	        //Computers on Your Residence
	        
	        @FindBy(how=How.ID, using="27")
	        public WebElement rbtComputeronResidence;
	        
	        //Current Job Years
	        
	        @FindBy(how=How.ID, using="41")
	        public WebElement rbtCurrentJobYears;
	        
	        //Hours you work
	        
	        @FindBy(how=How.ID, using="45")
	        public WebElement rbtHoursyouWork;
	        
	        //Spend Hours in schooling
	        
	        @FindBy(how=How.ID, using="50")
	        public WebElement rbtSpendsHourSchooling;
	        
	        
	        //Supporting 
	        
	        @FindBy(how=How.ID, using="68")
	        public WebElement rbtSupporting;
	        
	        // No Permission To Register
	        
	        @FindBy(how=How.ID, using="Permission_No")
	        public WebElement rbtGrandPermissionToRegister;
	        
	        //Submit Application
	        
	        @FindBy(how=How.XPATH, using=".//*[@id='ng-app']/body/div[6]/div/div/div[3]/button[2]")
	        public WebElement btnSubmitApplication;
	        
            //Save Button
	        
	        @FindBy(how=How.ID, using="save-button")
	        public WebElement rbnSave;
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	
	}
	
	
	
	
	
	
