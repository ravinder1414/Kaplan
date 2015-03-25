package uiMap_Orion3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import commonfunctions.UserExtension;

public class SearchLeadsPageObjects {
	 
		public SearchLeadsPageObjects(WebDriver driver) {
				
				//Initialize A SearchPageObjects.
				PageFactory.initElements(driver, this);
			}

		public String sStudentManager_WindowName = "StuMgr";
		public String sSearchMain_WindowName ="";
		
		
		//Contact Information page details
		
		//Email Address
		
			@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbEmailAddress")
			public WebElement txtEmailAddress;
			
			//Contact Information Tab
			@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[1]/a/span")
			public WebElement ContactInformationTab;
		    public String strContactInformation = "Contact Information"; 
		    
		    //First Name
		    
			@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbFirstName")
			public WebElement txtFirstName;
			
			//Last Name
			@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_tbLastName")
			public WebElement txtLastName;
			
		    //Lead ID
		    
		    @FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_ctl01_ctl04_lblLeadID")
			public WebElement LeadID;	
		    
			//Admissions Tab
			@FindBy(how=How.LINK_TEXT, using="Admissions")
			public WebElement lnkAdmissionsTab;
			
		    //Search Leads
		   @FindBy(how=How.ID, using="A6762")
			public WebElement lnkSearchLeads;
			
		 //Click on Admissions - > Search Leads
			public SearchLeadsPageObjects ClickSearchLeads(WebDriver driver)
			{
				UserExtension.MouseOver(driver, lnkAdmissionsTab);
				UserExtension.IsElementPresent(driver, lnkSearchLeads);
				lnkSearchLeads.click();
				SearchLeadsPageObjects objPage = new SearchLeadsPageObjects(driver);
				return(objPage);
			}
			
			
			//text fields on Search Leads page
			
			//Lead Id text box

			@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_tbLeadID")
			public WebElement txtSearchLeadID;
			
			//First Name Text Box
			
			@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_tbFirstName")
			public WebElement txtSearchLeadsFirstName;
			
			//Last Name Text Box
			
			//@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_tbLastName")
			//public WebElement txtSearchLeadsLastName;
			
			
			
			//Last Name
			
			@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_tbLastName")
			public WebElement txtSearchLeadsLastName;
			
			//Email Text Box
			@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_tbEmail")
			public WebElement txtSearchLeadsEmail;
		
			
			//Leads Search Button
			@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_btnSearch")
			public WebElement btnLeadsSearch;
			
			//Email Result fields
			@FindBy(how=How.XPATH, using= "//td[6]")
			public WebElement resultsEmailField;
			
				// First Search Result
				
				@FindBy(how=How.XPATH, using=".//*[@id='nm26866051']")
					
				public WebElement firstSearchResult;
				
			
}







	
