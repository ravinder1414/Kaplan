package uiMap_SRM5_PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SEP_CreateAccount_PageObjects {
	
	public SEP_CreateAccount_PageObjects(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
	}
	
	//Begin Application lbl
	@FindBy(how=How.XPATH, using=".//*[@id='hero-wrapper']/div/div[1]/div/ul/li[1]/span/div/div/div/p[3]/a/img")
	public WebElement lblBeginApplication;
	

	//Radio Button Yes Attended Kaplan University Text
	@FindBy(how=How.ID, using="ReturningStudentYes")
	public WebElement rbnAttendedKaplanYes;
                                               

	//Radio Button No Attended Kaplan University Text
	@FindBy(how=How.ID, using="ReturningStudentNo")
	public WebElement rbnAttendedKaplanNo;
	

	//Country DropDown
	@FindBy(how=How.ID, using= "CountryId")
	public WebElement ddCountry;
	
	//State DropDown
	@FindBy(how=How.ID, using="StateCode")
	public WebElement ddState;
	
	//First Name Text box
	@FindBy(how=How.ID, using= "FirstName")
	public WebElement txtFirstName;
	

	//Last Name Text box
	@FindBy(how=How.ID, using= "LastName")
	public WebElement txtLastName;
	
	//Primary Phone Number Text
	@FindBy(how=How.ID, using="PhoneNumber")
	public WebElement txtPrimaryPhoneNo;


	//Secondary Phone Number Text box
	@FindBy(how=How.ID, using= "AlternatePhone")
	public WebElement txtSecondaryPhoneNo;
	
	//Email Address Text Box
	@FindBy(how=How.ID, using="Email")
	public WebElement txtEmailAddress;
	
	
	//Confirm Email Address Text box
	@FindBy(how=How.ID, using= "ConfirmEmail")
	public WebElement txtConfirmEmail;

	//Spouse Radio Button Yes
	@FindBy(how=How.ID, using="MilitaryYes")
	public WebElement rbtnSpouse_Yes;
	
	//Spouse Radio Button No
	@FindBy(how=How.ID, using= "MilitaryNo")
	public WebElement rbtnSpouse_No;
	
	//Degree Radio Button
	@FindBy(how=How.ID, using="Graduate")
	public WebElement rbtnDegreeGraduate;


	//Undergraduate Degree Radio Button
	@FindBy(how=How.ID, using= "Undergraduate")
	public WebElement rbtnDegreeUndergraduate;
	
	//Nursing Radio Button Yes
	@FindBy(how=How.ID, using="NursingYes")
	public WebElement rbtnNurshing_Yes;
	

	//Nursing Radio Button No
	@FindBy(how=How.ID, using= "NursingNo")
	public WebElement rbtnNurshing_No;
	
	//Password
	@FindBy(how=How.ID, using="txtPassword")
	public WebElement txtPassword;


	//Confirm Password
	@FindBy(how=How.ID, using= "txtConfirmPassword")
	public WebElement txtConfirmPassword;
	
	//TCPA CheckBox
	@FindBy(how=How.ID, using="TCPA")
	public WebElement checkBoxTCPA;
	

	//Create Account Button
	@FindBy(how=How.ID, using= "create-account")
	public WebElement btnCreateAccount;
	
	//Account Created Successfully message
	
	@FindBy(how=How.XPATH, using= ".//*[@id='account-creation-modal']/div[1]")
	public WebElement txtAccountCreationMessage;
	
	//Click on Ok button
	
	@FindBy(how=How.ID, using= "ok-mini")
	public WebElement btnOK;
	
	
	
	
	

}
