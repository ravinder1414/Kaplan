package uiMap_Orion3_SRM;
//Import files
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;




import commonfunctions.UserExtension;

	public class SRMLead_CreationPageObject {
		
		public SRMLead_CreationPageObject(WebDriver driver) {
			
			//Initialize A AddALeadReferralPageObjects.
			PageFactory.initElements(driver, this);
		}
		
		//Admission Console DropDown
		@FindBy(how=How.LINK_TEXT, using="Admissions Console")
		public WebElement lnkAdmissionConsole;
		
		//ORS Console DropDown
			@FindBy(how=How.LINK_TEXT, using="ORS Console")
			public WebElement lnkORSConsole;
			
			//Back to Admission Console link
			
			@FindBy(how=How.LINK_TEXT, using="Back to Admissions Console")
			public WebElement lnkBackToAdmissionConsole;
		
		
		//Admission Console DropDown
				
		@FindBy(how=How.ID, using="tsidLabel")
		public WebElement lnkdropdown;
		
		@FindBy(how=How.XPATH, using=".//*[@id='tsidLabel']")
		public WebElement lnkDropDown;
		
		
		
		//Kaplan SRM DropDown
		@FindBy(how=How.LINK_TEXT, using="Kaplan SRM")
		public WebElement lnkKaplanSRM;
		
		
		//Home Add an Inquiry
		@FindBy(how=How.ID, using="ext-gen60")
		public WebElement ddHomeAddInquiry;
		
		//DropDown Inquiry field
		
		@FindBy(how=How.XPATH, using="//div[4]/div/div/table/tbody/tr[2]/td[2]/em")
		public WebElement ddInquiryDropDown;
		
		//Link Home
		
	     @FindBy(how=How.XPATH, using="//span[text()='Home']")
	     public WebElement lnkHomeDropDown;
		

		//Add an Inquiry Text
		@FindBy(how=How.XPATH, using="//span[text()='Add An Inquiry']")
		public WebElement lnkAddInquiry;
	                                               

		//Radio Button Referral
		//@FindBy(how=How.XPATH, using="//input[@value='Referral']")
		//public WebElement rbnReferral;
		
		
		
		//Radio Button Referral
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id33:1")
		public WebElement rbnReferral;
		
		
		//First Name Text
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr[1]/td[3]/input")
		public WebElement txtFirstName;
		
		//Last Name Text
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr[2]/td[3]/input")
		public WebElement txtLastName;
		
		//Email Address Text Box
		@FindBy(how=How.XPATH, using="//span[2]/table/tbody/tr[3]/td[3]/input")
		public WebElement txtEmailAddress;
		
		
		//Home Time Phone Text
		@FindBy(how=How.XPATH, using="//span[2]/table/tbody/tr[4]/td[3]/input")
		public WebElement txtHomeTimePhoneNo;


		//Day Time Phone Number Text box
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr[5]/td[3]/input")
		public WebElement txtDayTimePhoneNo;
		
		
		
		//City Text box
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr[6]/td[3]/input")
		public WebElement txtCity;

		//State Text Box
		@FindBy(how=How.XPATH, using="//span[2]/table/tbody/tr[7]/td[3]/input")
		public WebElement ddState;
		
		//ZIP Code Text Box
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr[8]/td[3]/input")
		public WebElement txtZipCode;
		
		//Country DropDown
		@FindBy(how=How.XPATH, using="//span[2]/table/tbody/tr[9]/td[3]/input")
		public WebElement ddCountry;

		
		//TCPA Disclosure Yes Radio Button
		@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id44:9:j_id50:0")
		public WebElement rbtnTCPA_Disclosure_Yes;
		
		
		
		//TCPA Disclosure NO Radio Button
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id44:9:j_id50:1")
		public WebElement rbtnTCPA_Disclosure_No;
		

		//Spouse Military Radio Button Yes
		//@FindBy(how=How.XPATH, using= "//tr[11]/td[3]/fieldset/table/tbody/tr/td[2]/input")
		//public WebElement rbtnSpouse_Yes;
		
		//Spouse Military Radio Button Yes
				@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id44:10:j_id50:0")
				public WebElement rbtnSpouse_Yes;
		
		
		//Spouse Military Radio Button No
		//@FindBy(how=How.XPATH, using="//tr[11]/td[3]/fieldset/table/tbody/tr/td[3]/input")
		//public WebElement rbtnSpouse_No;
		
		//Spouse Military Radio Button No
				@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id44:10:j_id50:0")
				public WebElement rbtnSpouse_No;
		
		
		
		//Highest Level of Education
		
		@FindBy(how=How.XPATH, using="//tr[12]/td[3]/select")
		public WebElement ddHighestLevelEducation;
		
		//Add an Inquiry
		
		//@FindBy(how=How.XPATH, using="//input[@value='Add An Inquiry']")
		//public WebElement txtAddAnInquiry;
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:addALeadButtonId")
		public WebElement txtAddAnInquiry;
		
		
		
		//Created Lead Success Message
	
		//@FindBy(how=How.XPATH, using=".//*[@id='ext-gen43']")
		//public WebElement txtCreatedLeadSuccess;
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:successmsgid")
		public WebElement txtCreatedLeadSuccess;
		
		
		//Search Result of Lead 
		
		@FindBy(how=How.XPATH, using="html/body/div[1]/div[2]/table/tbody/tr/td/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[2]/table/tbody/tr[2]/th/a")
		public WebElement lnkSearchResult;
		
		//SyStudent id 
		@FindBy(how=How.XPATH, using="//td[text()='syStudentID']/following-sibling::td/div")
		public WebElement txtSyStudent;
		
		}
		
		
		
	

