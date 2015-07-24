package uiMap_Orion3_SRM;

	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3.Admissions.OpsUpdateArchivePageObjects;

import commonfunctions.UserExtension;

	public class AddInquiry_Referral_Lead_Pageobjects {
		
		public AddInquiry_Referral_Lead_Pageobjects(WebDriver driver) {
			
			//Initialize A AddALeadReferralPageObjects.
			PageFactory.initElements(driver, this);
		}
		
		//Admission Console DropDown
		@FindBy(how=How.LINK_TEXT, using="Admissions Console")
		public WebElement lnkAdmissionConsole;
		
		@FindBy(how=How.XPATH, using=".//*[@id='tsidLabel']")
		public WebElement lnkAdmissionConsoleDROPDOWN;
		
		
		
		
		//Home Add an Inquiry
		@FindBy(how=How.ID, using="ext-gen60")
		public WebElement ddHomeAddInquiry;
		
		//Add an Inquiry
		
		@FindBy(how=How.XPATH, using="//div[4]/div/div/table/tbody/tr[2]/td[2]/em")
		public WebElement ddANINQUIRY;
		
		
		
		
		

		//Add an Inquiry Text
		@FindBy(how=How.XPATH, using="//span[text()='Add An Inquiry']")
		public WebElement lnkAddInquiry;
	                                               

		//Radio Button Referral
		@FindBy(how=How.XPATH, using="//input[@value='Referral']")
		public WebElement rbnReferral;
		
		//First Name Text
		@FindBy(how=How.XPATH, using= "//span[2]/table/tbody/tr/td[3]/input")
		public WebElement txtFirstName;
		

		//Last Name Text
		@FindBy(how=How.XPATH, using= "//tr[2]/td[3]/input")
		public WebElement txtLastName;
		
		//Email Address Text Box
		@FindBy(how=How.XPATH, using="//tr[3]/td[3]/input")
		public WebElement txtEmailAddress;
		
		
		//Home Time Phone Text
		@FindBy(how=How.XPATH, using="//tr[4]/td[3]/input")
		public WebElement txtHomeTimePhoneNo;


		//Day Time Phone Number Text box
		@FindBy(how=How.XPATH, using= "//tr[5]/td[3]/input")
		public WebElement txtDayTimePhoneNo;
		
		//City Text box
		@FindBy(how=How.XPATH, using= "//tr[6]/td[3]/input")
		public WebElement txtCity;

		//State Text Box
		@FindBy(how=How.XPATH, using="//tr[7]/td[3]/input")
		public WebElement ddState;
		
		//ZIP Code Text Box
		@FindBy(how=How.XPATH, using= "//tr[8]/td[3]/input")
		public WebElement txtZipCode;
		
		//Country DropDown
		@FindBy(how=How.XPATH, using="//tr[9]/td[3]/input")
		public WebElement ddCountry;


		//TCPA Disclosure Yes Radio Button
		@FindBy(how=How.XPATH, using= "//td[3]/fieldset/table/tbody/tr/td[2]/input")
		public WebElement rbtnTCPA_Disclosure_Yes;
		
		//TCPA Disclosure NO Radio Button
		@FindBy(how=How.XPATH, using="//td[3]/fieldset/table/tbody/tr/td[4]/input")
		public WebElement rbtnTCPA_Disclosure_No;
		

		//Spouse Military Radio Button Yes
		@FindBy(how=How.XPATH, using= "//tr[11]/td[3]/fieldset/table/tbody/tr/td[2]/input")
		public WebElement rbtnSpouse_Yes;
		
		//Spouse Military Radio Button No
		@FindBy(how=How.XPATH, using="//tr[11]/td[3]/fieldset/table/tbody/tr/td[3]/input")
		public WebElement rbtnSpouse_No;
		
		//Highest Level of Education
		
		@FindBy(how=How.XPATH, using="//tr[12]/td[3]/select")
		public WebElement ddHighestLevelEducation;
		
		//Add an Inquiry
		
		@FindBy(how=How.XPATH, using="//input[@value='Add An Inquiry']")
		public WebElement txtAddAnInquiry;
		
		//Created Lead Success Message
	
		@FindBy(how=How.XPATH, using=".//*[@id='ext-gen43']")
		public WebElement txtCreatedLeadSuccess;
		
		//Click on Admissions Console
		public AddInquiry_Referral_Lead_Pageobjects ClickonAdmissionConsole(WebDriver driver)
		{
			UserExtension.MouseOver(driver, lnkAdmissionConsole);
			UserExtension.IsElementPresent(driver, lnkAdmissionConsole);
			lnkAdmissionConsole.click();
			AddInquiry_Referral_Lead_Pageobjects objPage = new AddInquiry_Referral_Lead_Pageobjects(driver);
			return(objPage);
		}
		
		
		//Click on Admissions - > Admissions Manager
		public AddInquiry_Referral_Lead_Pageobjects ClickAddanInquiry(WebDriver driver)
		{
			UserExtension.MouseOver(driver, ddHomeAddInquiry);
			UserExtension.IsElementPresent(driver, ddHomeAddInquiry);
			ddHomeAddInquiry.click();
			AddInquiry_Referral_Lead_Pageobjects objPage = new AddInquiry_Referral_Lead_Pageobjects(driver);
			return(objPage);
		}
		
		}
		
		
	

