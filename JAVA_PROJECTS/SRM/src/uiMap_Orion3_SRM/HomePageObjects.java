package uiMap_Orion3_SRM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
  
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
 
import org.openqa.selenium.support.How;
 
import org.openqa.selenium.support.PageFactory;


import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3.Admissions.EnrollmentExceptionsPageObjects;
import uiMap_Orion3.Admissions.OpsUpdateArchivePageObjects;
import uiMap_Orion3.Admissions.ReassignLeadDelegationPageObjects;
import uiMap_Orion3.Admissions.ReassignLeadsPageObjects;
import uiMap_Orion3.Admissions.SearchLeadPageObjects;
import uiMap_Orion3.Admissions.SearchLeadsAllNewPageObjects;
import uiMap_Orion3.Admissions.SearchRepsPageObjects;

import commonfunctions.UserExtension;


public class HomePageObjects {

	public HomePageObjects(WebDriver driver) {
	
		//Initialize Home Page.
		PageFactory.initElements(driver, this);
	}
	
	//Home Tab
	@FindBy(how=How.LINK_TEXT, using="Home")
	public WebElement lnkHomeTab;

	//Tickets Tab
	@FindBy(how=How.LINK_TEXT, using="Tickets")
	public WebElement lnkTicketsTab;
	
	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Admissions")
	public WebElement lnkAdmissionsTab;

	//Academics Tab
	@FindBy(how=How.LINK_TEXT, using="Academics")
	public WebElement lnkAcademicsTab;

	//Finance Tab
	@FindBy(how=How.LINK_TEXT, using="Finance")
	public WebElement lnkFinanceTab;

	//Financial Aid Tab
	@FindBy(how=How.LINK_TEXT, using="Financial Aid")
	public WebElement lnkFinancialAidTab;

	//OpsAdmin Tab
	@FindBy(how=How.LINK_TEXT, using="Ops Admin")
	public WebElement lnkOpsAdminTab;

	//TicketAdmin Tab
	@FindBy(how=How.LINK_TEXT, using="Ticket Admin")
	public WebElement lnkTicketAdminTab;
	
	//SystemAdmin Tab
	@FindBy(how=How.LINK_TEXT, using="System Admin")
	public WebElement lnkSystemAdminTab;
	
	//Add a Lead/Referral Tab
	@FindBy(how=How.LINK_TEXT, using="Add Lead/Referral")
	public WebElement lnkAddaLeadReferral;
	
	//Academics Tab
	@FindBy(how=How.LINK_TEXT, using="Education Advisor Rep Manager")
	public WebElement lnkEducationAdvisorRepMgr;
	
	//Academics Tab
	@FindBy(how=How.LINK_TEXT, using="Education Advisor Auto Assign History")
	public WebElement lnkEducationAdvisorAutoAssignHistory;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Search Leads")
	public WebElement lnkSearchLeads;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Search Leads*")
	public WebElement lnkSearchLeadsAllNew;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Search Reps")
	public WebElement lnkSearchReps;

	//Admissions Tab
	@FindBy(how=How.ID, using="A291")
	public WebElement lnkAdmissionsManager;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Ops Update Archive")
	public WebElement lnkOpsUpdateArchive;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Enrollment Exceptions")
	public WebElement lnkEnrollmentExceptions;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Reassign Leads")
	public WebElement lnkReassignLeads;

	//Admissions Tab
	@FindBy(how=How.LINK_TEXT, using="Reassign Leads Delegation")
	public WebElement lnkReassignLeadsDelegation;
	
	//Financial Tab
	@FindBy(how=How.LINK_TEXT, using="FA Search")
	public WebElement lnkFASearch;

	//OpsAdmin
	@FindBy(how=How.ID, using="A6503")
	public WebElement lnkARA;
	
	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="ARA Manager - Manage Tiers")
	public WebElement lnkARAManager_ManageTiers;

	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="ALC Campaign Manager - Upload Leads")
	public WebElement lnkALCCampaignMgrUploadLeads;
	
	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="Student Success Manager")
	public WebElement lnkStudentSuccessMgr;

	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="Email Template Manager - Post Enrollment")
	public WebElement lnkEmailTemplateMgrPostEnroll;

	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="Email Template Manager - Admissions")
	public WebElement lnkEmailTemplateMgrAdm;

	//OpsAdmin
	@FindBy(how=How.ID, using="A6504")
	public WebElement lnkIQM;

	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="iQM - Lead Transfer")
	public WebElement lnkIQMLeadTransfer;

	//OpsAdmin
	@FindBy(how=How.LINK_TEXT, using="iQM - Cancel Lead Transfer")
	public WebElement lnkIQMCancelLeadTransfer;
	
	
	//TicketAdmin
	@FindBy(how=How.LINK_TEXT, using="Action Results Manager - File Close Disposition")
	public WebElement lnkActionResultsManagerFCDisposition;
	
	//TicketAdmin
	@FindBy(how=How.LINK_TEXT, using="Action Category Manager")
	public WebElement lnkActionCategoryManager;
	
	//TicketAdmin
	@FindBy(how=How.LINK_TEXT, using="Ticket Type Manager")
	public WebElement lnkTicketTypeManager;

	//TicketAdmin
	@FindBy(how=How.LINK_TEXT, using="Ticket Queue Manager")
	public WebElement lnkTicketQueueManager;

	//TicketAdmin
	@FindBy(how=How.LINK_TEXT, using="Student Manager Panel")
	public WebElement lnkStudentManagerPanel;
	
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Business Unit Manager")
	public WebElement lnkBusinessUnitManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Team Type Manager")
	public WebElement lnkTeamTypeManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Security Role Manager")
	public WebElement lnkSecurityRoleManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="User Account Manager")
	public WebElement lnkUserAccountManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Security Group Manager")
	public WebElement lnkSecurityGroupManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Navigation Manager")
	public WebElement lnkNavigationManager;
	
	//SystemAdmin
	@FindBy(how=How.LINK_TEXT, using="Department Manager")
	public WebElement lnkDepartmentManager;
	
	
	
	//Methods
	//Verify home tab link
	public boolean VerifyHomeTabPresent()
	{		
		if(lnkHomeTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Verify Admissions Tab link
	public boolean VerifyAdmissionsTabPresent()
	{		
		if(lnkAdmissionsTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	
	//Verify Academics Tab link
	public boolean VerifyAcademicsTabPresent()
	{		
		if(lnkAcademicsTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify Finance Tab link
	public boolean VerifyFinanceTabPresent()
	{		
		if(lnkFinanceTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify FinancialAid Tab link
	public boolean VerifyFinancialAidTabPresent()
	{		
		if(lnkFinancialAidTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify OpsAdmin Tab link
	public boolean VerifyOpsAdminTabPresent()
	{		
		if(lnkOpsAdminTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify SystemAdmin Tab link
	public boolean VerifySystemAdminTabPresent()
	{		
		if(lnkSystemAdminTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify Tickets Tab link
	public boolean VerifyTicketsTabPresent()
	{		
		if(lnkTicketsTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify TicketAdmin Tab link
	public boolean VerifyTicketAdminTabPresent()
	{		
		if(lnkTicketAdminTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Verify VerifyAddaLeadRefferalLinkPresent Tab link
	public boolean VerifyAddaLeadReferalLinkPresent()
	{		
		if(lnkAddaLeadReferral.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Click on Add a lead RefferalLink
	public AddALeadReferralPageObjects ClickAddaLeadReferralLink(WebDriver driver)
	{
		lnkAddaLeadReferral.click();
		AddALeadReferralPageObjects objPage = new AddALeadReferralPageObjects(driver);
		return(objPage);
	}
	//Click on Academics - > Eduction Advisor Rep Manager
	
	//Click on Admissions - > Search Leads
	public SearchLeadPageObjects ClickSearchLeads(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkSearchLeads);
		lnkSearchLeads.click();
		SearchLeadPageObjects objPage = new SearchLeadPageObjects(driver);
		return(objPage);
	}

	//Click on Admissions - > Search Leads*
	public SearchLeadsAllNewPageObjects ClickSearchLeadsAllNew(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkSearchLeadsAllNew);
		lnkSearchLeadsAllNew.click();
		SearchLeadsAllNewPageObjects objPage = new SearchLeadsAllNewPageObjects(driver);
		return(objPage);
	}
	
	//Click on Admissions - > Search Reps
	public SearchRepsPageObjects ClickSearchReps(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkSearchReps);
		lnkSearchReps.click();
		SearchRepsPageObjects objPage = new SearchRepsPageObjects(driver);
		return(objPage);
	}
	//Click on Admissions - > Admissions Manager
	public AdmissionsManagerPageObjects ClickAdmissionsManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkAdmissionsManager);
		lnkAdmissionsManager.click();
		AdmissionsManagerPageObjects objPage = new AdmissionsManagerPageObjects(driver);
		return(objPage);
	}
	//Click on Admissions - > Ops Update Archive
	public OpsUpdateArchivePageObjects ClickOpsUpdateArchive(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkOpsUpdateArchive);
		lnkOpsUpdateArchive.click();
		OpsUpdateArchivePageObjects objPage = new OpsUpdateArchivePageObjects(driver);
		return(objPage);
	}
	//Click on Admissions - > Enrollment Exceptions
	public EnrollmentExceptionsPageObjects ClickEnrollmentExceptions(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkEnrollmentExceptions);
		lnkEnrollmentExceptions.click();
		EnrollmentExceptionsPageObjects objPage = new EnrollmentExceptionsPageObjects(driver);
		return(objPage);
	}
	//Click on Admissions - > Reassign Leads
	public ReassignLeadsPageObjects ClickReassignLeads(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkReassignLeads);
		lnkReassignLeads.click();
		ReassignLeadsPageObjects objPage = new ReassignLeadsPageObjects(driver);
		return(objPage);
	}
	
	//Click on Admissions - > Reassign Leads Delegations
	public ReassignLeadDelegationPageObjects ClickReassignLeadsDelegation(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAdmissionsTab);
		UserExtension.IsElementPresent(driver, lnkReassignLeadsDelegation);
		lnkReassignLeadsDelegation.click();
		ReassignLeadDelegationPageObjects objPage = new ReassignLeadDelegationPageObjects(driver);
		return(objPage);
	}

	
	}
	

