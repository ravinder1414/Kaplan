package uiMap_Orion1;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class DocSchedulePageObject {
public DocSchedulePageObject(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
	}
  

	//Doc Imaging tab
	@FindBy(how=How.XPATH, using="//td[text()='Document Imaging']")
	public WebElement 	TabDocImaging;
	
	//Campus drop down ddlCampus
	@FindBy(how=How.ID, using="ddlCampus")
	public WebElement 	ddCampus;
	
	//Student number 
	@FindBy(how=How.ID, using="stunum")
	public WebElement 	txtStuNum;
	
	//Search Student button 
	@FindBy(how=How.ID, using="searchstu")
	public WebElement 	btnSearchStu;
	
	//Add to list button  
	@FindBy(how=How.ID, using="btn")
	public WebElement 	btnAddtoList;
	
	//Document Category Drop down 
	@FindBy(how=How.ID, using="DIschedulingPopUP1_ddlCategoryName")
	public WebElement 	ddDocCategory;
	
	//Add Dock Arrow link
	@FindBy(how=How.ID, using="DIschedulingPopUP1_btnAddDocs")
	public WebElement 	arrAddDocs;

	//selected Documents check box 
	@FindBy(how=How.ID, using="DIschedulingPopUP1_dgDocSelected__ctl2_chkDocSelected")
	public WebElement 	cbSelectedDocs;
	
	//IFRAME
	@FindBy(how=How.XPATH, using="//iframe[@id='reloader']")
	public WebElement 	ifrDocSchedule;
	
	//Date requested on Doc schedule 
	@FindBy(how=How.ID, using="DIschedulingPopUP1_dtbDateRequested")
	public WebElement 	txtDtreq;

	//Program under work study in Documents pane 
	@FindBy(how=How.XPATH, using="//b[text()='Work-Study']/parent::nobr/parent::span/following-sibling::div/span/nobr/span")
	public WebElement 	lblWorkStudyPrg;

	//document Details Frame
	public String 	sDocDetailsFrame = "DocumentDetailsFrame";

	//document Attributes Frame
	public String 	sDocAttribFrame = "DocumentAttributesFrame";
	
	//Document Delete button
	@FindBy(how=How.ID, using="spanDocStatus")
	public WebElement 	btnDocDelete;
	
	//Reason to Delete document drop down
	@FindBy(how=How.ID, using="ddlSelectDelete")
	public WebElement 	ddReasonDelete;
	
	//Reason to Delete document label
	public String 	sReasonDelete="The Scheduled Document"  ;
		
	
	//Final Delete button 	
	@FindBy(how=How.XPATH, using="//input[@id='btnSelect']")
	public WebElement 	btnFinalDelete;
	
	
	//Alert Delete message
	
	public String msgAlertDelete =  "The Scheduled Document Has Been Deleted.";
	
	
	
	
}
