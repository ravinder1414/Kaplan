package uiMap_Orion3_SRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ValidateChangeInOwnerShipPageObjects {
	
	
public ValidateChangeInOwnerShipPageObjects(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
		
	}


   //**********************Inquiry Details Page PageObjects *****************
//inquiry details page 

//Fetching Owner name 

@FindBy(how=How.XPATH, using= "( //td[text()='Inquiry Owner']/following-sibling::td[1]/div[1]/span/a)[2]")
public WebElement txtOwnernameLeadPage;

//Fetching Owner Name from Oppurtunity Page 

@FindBy(how=How.XPATH, using= "(//td[text()='Opportunity Owner']/following-sibling::td[1]/div[1]/span/a)[2]")
public WebElement txtOwnernameOpportunityPage;	
//Process Email Tab 
@FindBy(how=How.XPATH, using= "//input[@Value='Process Emails']")
public WebElement tbProcessEmail;

//Change link in Inquiry details Page 
@FindBy(how=How.XPATH, using= " //td[text()='Inquiry Owner']/following-sibling::td[1]/div/a")
public WebElement lnkChange;

//Change link in Oppurtunity details Page 
@FindBy(how=How.XPATH, using= " //td[text()='Opportunity Owner']/following-sibling::td[1]/div/a")
public WebElement lnkChangeOppurtunity;



//Select Template to be sent page 
//Email Template Drop down List 
@FindBy(how=How.XPATH, using= ".//*[@id='j_id0:j_id26:pb:j_id30']/div[2]/table/tbody/tr/td/select")
public WebElement DrpDwnEmailTemplate;

//Preview template Tab 
@FindBy(how=How.XPATH, using= "//input[@value='Preview Template']")
public WebElement tbPreviewTemplate;

//switch to frame 
public String frameSwitch = "RLPanelFrame";

//switching to frame in Template Page 
public String contentframeswitch ="contentFrame";

//Owner Name in Template page 
@FindBy(how=How.XPATH, using= "//b[text()='Piyush Mishra']")
public WebElement txtOwnerNameintemplate;

//Owner Name in Change Owner Template page 
@FindBy(how=How.XPATH, using= "//b[text()='Manish Narang']")
public WebElement txtChangedOwnerNameintemplate;

//Converted Opportunity name in owner template page 
@FindBy(how=How.XPATH, using= "//b[text()='Jitendra Kumar']")
public WebElement txtCovertedOppurtunityOwnerNameintemplate;

//Opportunity Name in Owner Template Page \
@FindBy(how=How.XPATH, using= "( //td[text()='Opportunity Owner']/following-sibling::td[1]/div[1]/span/a)[2]")
public WebElement txtChangedOwnerOpportunity;

//Dropdown Owner type in Change owner Page 
@FindBy(how=How.XPATH, using= "//select")
public WebElement DrpDwnOwnerType;

//New owner Name text Field
@FindBy(how=How.XPATH, using= ".//*[@id='newOwn']")
public WebElement TxtNewOwnerName;

//Save Button In Change Owner Page 
@FindBy(how=How.XPATH, using= ".//*[@id='bottomButtonRow']/input[1]")
public WebElement TabsaveOwner;

//Account Name in Opportunity detail page 

@FindBy(how=How.XPATH, using= "//td[text()='Account Name']/following-sibling::td/div/a")
public WebElement lnkAccountNameopp;

//opportunities link in opportunities page 
@FindBy(how=How.XPATH, using= "//span[text()='Opportunities']")
public WebElement OppurtunitiesHistory;

//New Opportunity Button in Opportunity Page 
@FindBy(how=How.XPATH, using= "//input[@Value='New Opportunity']")
public WebElement TabNewOppurtunity;

//Record Type Of New Record in New Opportunity Page 
@FindBy(how=How.XPATH, using= "//select")
public WebElement DrpdwnNewrecordType;

//Continue Button In New Opportunity Page 

@FindBy(how=How.XPATH, using= ".//*[@id='bottomButtonRow']/input[1]")
public WebElement TabContinue;

//Edit Button In Opportunity field 
@FindBy(how=How.XPATH, using= ".//*[@id='topButtonRow']/input[3]")
public WebElement BtnEditOpp;

//opportunity name in opportunity 
@FindBy(how=How.XPATH, using= "(//td[text()='Opportunity Name']/following-sibling::td/div)[1]")
public WebElement TxtOpportunityname;

//opportunity name in Edit opportunity Page
@FindBy(how=How.XPATH, using= ".//*[@id='opp3']")
public WebElement TxtOpportunitynameEdit;


//Close date for the opportunity in opportunity page 
@FindBy(how=How.XPATH, using= "//label[text()='Close Date']/parent::td/following-sibling::td/div/span/span/a")
public WebElement lnkClosedate;

//Stage field in opportunity page 
@FindBy(how=How.XPATH, using= "//label[text()='Stage']/parent::td/following-sibling::td/div/span/select")
public WebElement DrpdwnStage ;

//Save Button In Opportunity Page 

@FindBy(how=How.XPATH, using= ".//*[@id='topButtonRow']/input[1]")
public WebElement BtnSaveOpp;











  

}
