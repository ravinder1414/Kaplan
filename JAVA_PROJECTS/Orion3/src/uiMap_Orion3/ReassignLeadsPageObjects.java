package uiMap_Orion3;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import commonfunctions.UserExtension;

public class ReassignLeadsPageObjects  {
	 
		public ReassignLeadsPageObjects(WebDriver driver) {
				
				//Initialize A SearchPageObjects.
				PageFactory.initElements(driver, this);
			}

		public String sStudentManager_WindowName = "StuMgr";
		public String sSearchMain_WindowName ="";
		
		
		//Contact Information page details
		
			//Contact Information Tab
			@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[1]/a/span")
			public WebElement ContactInformationTab;
		    public String strContactInformation = "Contact Information"; 
		   
		    //Lead ID
		    
		    @FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_ctl01_ctl04_lblLeadID")
			public WebElement LeadID;	
		    
			//Admissions Tab
			@FindBy(how=How.LINK_TEXT, using="Admissions")
			public WebElement lnkAdmissionsTab;
			
		    //Reassign Leads
		   @FindBy(how=How.ID, using="A204")
			public WebElement lnkReassignLeads;
			
		 //Click on Admissions - > Reassign Leads
			public ReassignLeadsPageObjects ClickReassignLeads(WebDriver driver)
			{
				UserExtension.MouseOver(driver, lnkAdmissionsTab);
				UserExtension.IsElementPresent(driver, lnkReassignLeads);
				lnkReassignLeads.click();
				ReassignLeadsPageObjects objPage = new ReassignLeadsPageObjects(driver);
				return(objPage);
			}
			
			
			//Text fields on Reassign Leads page
			
			//Lead Type DropDown
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ddlTaskTypes2")
			public WebElement leadTypeDropDown;
			public String sleadTypeDropDownText = "Choose a Lead Type";
			
			//Lead ID Text Box

			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_txtStudentId")
			public WebElement txtLeadID;
			
			//Search Button
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnSearchById")
			public WebElement btnSearchLeads;
			
			
			//Search Leads on the basis of LeadID
			
			@FindBy(how=How.XPATH, using = ".//*[@id='tblTicketsToReassign']/tbody/tr/td[2]")
			public WebElement txtSearchLeadsID;
			
			//Check Box
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lvTicketsToReassign_ctrl0_ckbTicketSelected")
			public WebElement selectCheckBox;
			
			
			
			
			//Fields for Lead Assignment
			
			
			//Team DropDown
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_ddlToTeam")
			public WebElement txtTeamDropDown;
			
			//Assign Leads dropDown
			
			//@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lbRepList")
			//public WebElement txtAssignLeadsDropDown;
			
			
			
			@FindBy(how=How.XPATH, using = ".//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lbRepList']")
			public WebElement txtAssignLeadsDropDown;
			
			//Reassign Button
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnTakeAssignAction")
			public WebElement btnReAssignLeads;
			
			//Processing close button
			
			@FindBy(how=How.ID, using = "ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnCloseProcessingPopUp")
			public WebElement btnProcessingClose;
			
			//Check Status
			
			@FindBy(how=How.XPATH, using = ".//*[@id='tblTicketsToReassign']/tbody/tr/td[5]")
			public WebElement txtReassignedText;
			
			//Current Rep
			
			@FindBy(how=How.XPATH, using = ".//*[@id='tblTicketsToReassign']/tbody/tr/td[11]")
			public WebElement txtCurrentRepText;
			
			
			
			
}




