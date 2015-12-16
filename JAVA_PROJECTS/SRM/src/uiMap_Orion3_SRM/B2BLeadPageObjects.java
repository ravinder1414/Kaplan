package uiMap_Orion3_SRM;

//Import files
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class B2BLeadPageObjects  {
	 
public B2BLeadPageObjects(WebDriver driver) {
				
//Initialize A SearchPageObjects.
 PageFactory.initElements(driver, this);
		}

		public String sSearchMain_WindowName ="";
		
		
		//Contact Information page details
		
			//Event ID
			@FindBy(how=How.ID, using="eventnumber")
			public WebElement txtEventID;
		   
		   
		    //Drop Down Event Category
		    
		    @FindBy(how=How.ID, using = "EventCategory")
			public WebElement ddEventCategory;	
		    
			//Event Date
			@FindBy(how=How.ID, using="EventDate")
			public WebElement txtEventDate;
			
		    //Drop Down Event Type
		   @FindBy(how=How.ID, using="EventType")
			public WebElement txtEventType;
			
			//Drop Down Area Of Study
			
			@FindBy(how=How.ID, using = "AreaStudy")
			public WebElement ddAreaOfStudy;
			
			
			//Drop Down Program

			@FindBy(how=How.ID, using = "Program")
			public WebElement ddProgram;
			
			//First Name
			
			@FindBy(how=How.ID, using = "Fname")
			public WebElement txtFirstName;
			
			
			//Search Leads on the basis of LeadID
			
			@FindBy(how=How.ID, using = "Lname")
			public WebElement txtLastName;
			
			//Address Line1
			
			@FindBy(how=How.ID, using = "Address")
			public WebElement txtAddressLine1;
			
            //Address Line2
			
			@FindBy(how=How.ID, using = "Address2")
			public WebElement txtAddressLine2;
			
            //City
			
			@FindBy(how=How.ID, using = "City")
			public WebElement txtCity;
			
             //State
			
			@FindBy(how=How.ID, using = "State")
			public WebElement txtState;
			
			
            //ZIP
			
			@FindBy(how=How.ID, using = "Zip")
			public WebElement txtZIP;
			
			//Email
			
			@FindBy(how=How.ID, using = "Email")
			public WebElement txtEmail;
			
			//Country
			
			@FindBy(how=How.ID, using = "Country")
			public WebElement ddCountry;
			
			//Employer
			
			@FindBy(how=How.ID, using = "Employer")
			public WebElement txtEmployer;
			
			//Tuition Assistant
			
			@FindBy(how=How.ID, using = "TuitionAssist")
			public WebElement txtTutionAssistant;
			
			//DropDown Reimbursed
			
			@FindBy(how=How.ID, using = "Reimburse")
			public WebElement ddReimburse;
			
			//Spouse Military Yes
			
			@FindBy(how=How.ID, using = "Military_0")
			public WebElement rbnSpouse_Military_Yes;
			
             //Spouse Military No
			
			@FindBy(how=How.ID, using = "Military_1")
			public WebElement rbnSpouse_Military_No;
			
			//Military Branch DropDown
			
			@FindBy(how=How.ID, using = "MilitaryB2B")
			public WebElement ddMilitaryBranch;
			
			//DropDown Military Status
			
			@FindBy(how=How.ID, using = "MilitaryStatus")
			public WebElement ddMilitaryStatus;
			
			//DropDown Highest Degree
			
			@FindBy(how=How.ID, using = "Degree")
			public WebElement ddHighestDegree;
			
			//Major Highest Degree Held
			
			@FindBy(how=How.ID, using = "Major")
			public WebElement ddMajorHighestDegree;
			
	        //Degree Completed Year
			
			@FindBy(how=How.ID, using = "DegreeCompletedYear")
			public WebElement txtDegreeCompletedYear;
			
			//Other Degree
			
			@FindBy(how=How.ID, using = "OtherDegree")
			public WebElement txtOtherDegree;
			
			//DropDown Corporate Alliance Partner
			
			@FindBy(how=How.ID, using = "CAPPartner")
			public WebElement ddCorporateAPPartner;
			
			//DropDown KHESchool
			
			@FindBy(how=How.ID, using = "KHESchool")
			public WebElement ddKHESchool;
			
			//Credit of Degree Plan
			
			@FindBy(how=How.ID, using = "DegreeCredits")
			public WebElement ddDegreeCredits;
			
			//DropDown Best Day of Week
			
			@FindBy(how=How.ID, using = "BestDay")
			public WebElement ddBestDay;
			
			//DropDown Best Time to contact
			
			@FindBy(how=How.ID, using = "BestTime")
			public WebElement ddBestTime;
			
			//Day Time Phone
			
			@FindBy(how=How.ID, using = "WPhone")
			public WebElement ddDayTimePhone;
			
			//Radio Button Community College Yes
			
			@FindBy(how=How.ID, using = "CCLead_0")
			public WebElement rbnCommunityCollege_Yes;
			
            //Radio Button Community College No
			
			@FindBy(how=How.ID, using = "CCLead_1")
			public WebElement rbnCommunityCollege_No;
			
			//Radio Button Student Discounted Tuition
			
			@FindBy(how=How.ID, using = "discounttuition_0")
			public WebElement rbnDiscounted_Yes;
			
            //Radio Button Student Discounted Tuition
			
			@FindBy(how=How.ID, using = "discounttuition_1")
			public WebElement rbnDiscounted_No;
			
			//KHEC Graduation Date
			
			@FindBy(how=How.ID, using = "KHECCGradDate")
			public WebElement txtGraduationDates;
			
			//DropDown Student Enroll Dates
			

			@FindBy(how=How.ID, using = "enrolldates")
			public WebElement ddenrolldates;
			
			//Source code
			
			@FindBy(how=How.ID, using = "SourceCode")
			public WebElement ddSourceCode;
			
			//Comments text box
			
			@FindBy(how=How.ID, using = "Comments")
			public WebElement txtComments;
			
			//DropDown Community College Name
			
			@FindBy(how=How.ID, using = "CommunityCollegeName")
			public WebElement ddCommunityCollegeName;
			
			//Radio Button TCPA Disclosure Yes
			
			@FindBy(how=How.ID, using = "TCPA_Disc_0")
			public WebElement rbnTCPADisclosure_Yes;
			
              //Radio Button TCPA Disclosure No
			
			@FindBy(how=How.ID, using = "TCPA_Disc_1")
			public WebElement rbnTCPADisclosure_No;
			
			//Button Submit
			
			@FindBy(how=How.ID, using = "btnSubmit")
			public WebElement btnSubmit;
			
			//Lead Successfully Created
			
			@FindBy(how=How.ID, using = "lblErrorMessage")
			public WebElement txtLeadCreatedMessage;
			
			
			
			
}

