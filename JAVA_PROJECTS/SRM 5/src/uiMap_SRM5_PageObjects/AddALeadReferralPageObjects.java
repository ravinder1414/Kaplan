package uiMap_SRM5_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddALeadReferralPageObjects {
	
	public AddALeadReferralPageObjects(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
	}
	
	//Add a Lead Referral lbl
	@FindBy(how=How.XPATH, using="//div[@id='maincontent']/div/h3/strong")
	public WebElement lblAddALeadReferral;
	public String strAddALeadReferral = "Add New Lead/Referral";

	//Denotes required information Text
	@FindBy(how=How.XPATH, using="//table[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[2]/td[3]")
	public WebElement lblRequiredInfo;
    public String strRequiredInfo = "Denotes required information";                                             

	//First Name Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[3]/td[1]")
	public WebElement lblFirstName;
	public String strFirstName = "First Name";

	//First Name Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbRFName")
	public WebElement txtFirstName;
	
	//Last Name Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[4]/td[1]")
	public WebElement lblLastName;
	public String strLastName = "Last Name";

	//Last Name Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbRLName")
	public WebElement txtLastName;
	
	//Email Address Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[5]/td[1]")
	public WebElement lblEmailAddress;
	public String strEmailAddress = "Email Address";

	//Email Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbREmail")
	public WebElement txtEmailAddress;
	
	//Evening Phone Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[7]/td[1]")
	public WebElement lblEveningPhone;
	public String strEveningPhone = "Evening Phone";
	
	//DayPhone Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbRDayPhone")
	public WebElement txtDayPhone;

	//Daytime Phone Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[6]/td[1]")
	public WebElement lblDaytimePhone;
	public String strDaytimePhone = "Daytime Phone";

	//Night Phone Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbRNightPhone")
	public WebElement txtNightPhone;
	
	//Other  Phone Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[8]/td[1]")
	public WebElement lblOtherPhone;
	public String strOtherPhone = "Other Phone";

	//Other Phone Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbROtherPhone")
	public WebElement txtOtherPhone;
	
	//City Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[9]/td[1]")
	public WebElement lblCity;
	public String strCity = "City";

	//City Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbRCity")
	public WebElement txtCity;
	
	//State Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[10]/td[1]")
	public WebElement lblState;
	public String strState = "State";

	//State drop down list
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ddlState")
	public WebElement ddlState;
	
	//TCPA Disclosure Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ReferralTable']/tbody/tr[11]/td[1]")
	public WebElement lblTCPA;
	public String strTCPA = "TCPA Disclosure";

	//TCPA drop down list
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ddlTCPADisclosure")
	public WebElement ddlTCPA;
	
	//SY Student ID Label
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_StudentReferringTable']/tbody/tr[4]/td[1]")
	public WebElement lblSYStudentID;
	public String strSYStudentID = "SY Student ID";

	//Student Id of Student Referring Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbSRStudentID")
	public WebElement txtStudentIDSR;
	
	//Ref Email Address Labels
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_StudentReferringTable']/tbody/tr[5]/td[1]")
	public WebElement lblRefEmailAddress;
	public String strRefEmailAddress = "Email Address";

	//Email Student Referring Text box
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_tbSREmail")
	public WebElement txtEmailSR;
	
	//Submit button
	@FindBy(how=How.ID, using= "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnSubmit")
	public WebElement btnSubmit;
	
	
	
	
}
	