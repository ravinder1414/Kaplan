package uiMap_Orion3;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
  
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
 
import org.openqa.selenium.support.How;
 
import org.openqa.selenium.support.PageFactory;

import uiMap_Orion3.Academics.AutoAssignHistoryPageObjects;
import uiMap_Orion3.Academics.RepGroupMgrPageObjects;
import uiMap_Orion3.Admissions.AdmissionsManagerPageObjects;
import uiMap_Orion3.Admissions.EnrollmentExceptionsPageObjects;
import uiMap_Orion3.Admissions.OpsUpdateArchivePageObjects;
import uiMap_Orion3.Admissions.ReassignLeadDelegationPageObjects;
import uiMap_Orion3.Admissions.ReassignLeadsPageObjects;
import uiMap_Orion3.Admissions.SearchLeadPageObjects;
import uiMap_Orion3.Admissions.SearchLeadsAllNewPageObjects;
import uiMap_Orion3.Admissions.SearchRepsPageObjects;
import uiMap_Orion3.FinanceAid.FASearchPageObjects;
import uiMap_Orion3.OpsAdmin.ALCCampMngUploadLeadsPageObjects;
import uiMap_Orion3.OpsAdmin.ARAManageTiersPageObjects;
import uiMap_Orion3.OpsAdmin.EmailTemplateMgrAdmPageObjects;
import uiMap_Orion3.OpsAdmin.EmailTemplateMgrPostEnrollPageObjects;
import uiMap_Orion3.OpsAdmin.IQMCancelLeadTransferPageObjects;
import uiMap_Orion3.OpsAdmin.IQMLeadTransferPageObjects;
import uiMap_Orion3.OpsAdmin.StudentSuccessManagerPageObjects;
import uiMap_Orion3.SystemAdmin.BusinessUnitMgrPageObjects;
import uiMap_Orion3.SystemAdmin.DepartmentMgrPageObjects;
import uiMap_Orion3.SystemAdmin.NavigationMgrPageObjects;
import uiMap_Orion3.SystemAdmin.SecurityGroupMgrPageObjects;
import uiMap_Orion3.SystemAdmin.SecurityRoleMgrPageObjects;
import uiMap_Orion3.SystemAdmin.TeamTypeMgrPageObjects;
import uiMap_Orion3.SystemAdmin.UserAccountMgrPageObjects;
import uiMap_Orion3.TicketAdmin.ActionCategoryManagerPageObjects;
import uiMap_Orion3.TicketAdmin.ActionResultsMgrFCDispositionPageObjects;
import uiMap_Orion3.TicketAdmin.StudentManagerPanelPageObjects;
import uiMap_Orion3.TicketAdmin.TicketQueueManagerPageObjects;
import uiMap_Orion3.TicketAdmin.TicketTypeManagerPageObjects;
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
	public RepGroupMgrPageObjects ClickEducationAdvisorRepManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAcademicsTab);
		UserExtension.IsElementPresent(driver, lnkEducationAdvisorRepMgr);
		lnkEducationAdvisorRepMgr.click();
		RepGroupMgrPageObjects objPage = new RepGroupMgrPageObjects(driver);
		return(objPage);
	}
	//Click on Academics - > AutoAssignHistorys
	public AutoAssignHistoryPageObjects ClickEducationAdvisorAutoAssignHistory(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkAcademicsTab);
		UserExtension.IsElementPresent(driver, lnkEducationAdvisorAutoAssignHistory);
		lnkEducationAdvisorAutoAssignHistory.click();
		AutoAssignHistoryPageObjects objPage = new AutoAssignHistoryPageObjects(driver);
		return(objPage);
	}

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

	//Click on Admissions - > Reassign Leads Delegations
	public FASearchPageObjects ClickFASearch(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkFinancialAidTab);
		UserExtension.IsElementPresent(driver, lnkFASearch);
		lnkFASearch.click();
		FASearchPageObjects objPage = new FASearchPageObjects(driver);
		return(objPage);
	}
	
	//Click on OpsAdmin - > ARA -> ManageTiers
	public ARAManageTiersPageObjects ClickARA_ManageTiers(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkOpsAdminTab);
		UserExtension.IsElementPresent(driver, lnkARA);
		UserExtension.MouseOver(driver, lnkARA);
		UserExtension.IsElementPresent(driver, lnkARAManager_ManageTiers);
		lnkARAManager_ManageTiers.click();
		ARAManageTiersPageObjects objPage = new ARAManageTiersPageObjects(driver);
		return(objPage);
	}

	//Click on OpsAdmin - > lnkStudentSuccessMgr
	public StudentSuccessManagerPageObjects ClickStudentSuccessMgr(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkOpsAdminTab);
		UserExtension.IsElementPresent(driver, lnkStudentSuccessMgr);
		lnkStudentSuccessMgr.click();
		StudentSuccessManagerPageObjects objPage = new StudentSuccessManagerPageObjects(driver);
		return(objPage);
	}

	//Click on OpsAdmin - > lnkEmailTemplateMgrAdm
	public EmailTemplateMgrAdmPageObjects ClickEmailTemplateMgrAdm(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkOpsAdminTab);
		UserExtension.IsElementPresent(driver, lnkEmailTemplateMgrAdm);
		lnkEmailTemplateMgrAdm.click();
		EmailTemplateMgrAdmPageObjects objPage = new EmailTemplateMgrAdmPageObjects(driver);
		return(objPage);
	}
	
	//Click on OpsAdmin - > lnkEmailTemplateMgrPostEnrollment
		public EmailTemplateMgrPostEnrollPageObjects ClickEmailTemplateMgrPostEnroll(WebDriver driver)
		{
			UserExtension.MouseOver(driver, lnkOpsAdminTab);
			UserExtension.IsElementPresent(driver, lnkEmailTemplateMgrPostEnroll);
			lnkEmailTemplateMgrPostEnroll.click();
			EmailTemplateMgrPostEnrollPageObjects objPage = new EmailTemplateMgrPostEnrollPageObjects(driver);
			return(objPage);
		}

		//Click on OpsAdmin - > lnkALCCampaignMgrUploadLeads
		public ALCCampMngUploadLeadsPageObjects ClickALCCampaignManagerUplodLeads(WebDriver driver)
		{
			UserExtension.MouseOver(driver, lnkOpsAdminTab);
			UserExtension.IsElementPresent(driver, lnkALCCampaignMgrUploadLeads);
			lnkALCCampaignMgrUploadLeads.click();
			ALCCampMngUploadLeadsPageObjects objPage = new ALCCampMngUploadLeadsPageObjects(driver);
			return(objPage);
		}
		
	//Click on OpsAdmin - > iQM - > Lead Transfer
	public IQMLeadTransferPageObjects ClickIQM_LeadTransfer(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkOpsAdminTab);
		UserExtension.IsElementPresent(driver, lnkIQM);
		UserExtension.MouseOver(driver, lnkIQM);
		UserExtension.IsElementPresent(driver, lnkIQMLeadTransfer);
		lnkIQMLeadTransfer.click();
		IQMLeadTransferPageObjects objPage = new IQMLeadTransferPageObjects(driver);
		return(objPage);
	}

	//Click on OpsAdmin - > iQM - > Cancel Lead Transfer
	public IQMCancelLeadTransferPageObjects ClickIQM_CancelLeadTransfer(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkOpsAdminTab);
		UserExtension.IsElementPresent(driver, lnkIQM);
		UserExtension.MouseOver(driver, lnkIQM);
		UserExtension.IsElementPresent(driver, lnkIQMCancelLeadTransfer);
		lnkIQMCancelLeadTransfer.click();
		IQMCancelLeadTransferPageObjects objPage = new IQMCancelLeadTransferPageObjects(driver);
		return(objPage);
	}

	//Click on TicketAdmin - > lnkActionResultsManagerFCDisposition
	public ActionResultsMgrFCDispositionPageObjects ClickActionResultManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkTicketAdminTab);		
		UserExtension.IsElementPresent(driver, lnkActionResultsManagerFCDisposition);
		lnkActionResultsManagerFCDisposition.click();
		ActionResultsMgrFCDispositionPageObjects objPage = new ActionResultsMgrFCDispositionPageObjects(driver);
		return(objPage);
	}

	//Click on TicketAdmin - > lnkActionCategoryManager
	public ActionCategoryManagerPageObjects ClickActionCategoryManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkTicketAdminTab);		
		UserExtension.IsElementPresent(driver, lnkActionCategoryManager);
		lnkActionCategoryManager.click();
		ActionCategoryManagerPageObjects objPage = new ActionCategoryManagerPageObjects(driver);
		return(objPage);
	}

	//Click on TicketAdmin - > lnkTicketTypeManager
	public TicketTypeManagerPageObjects ClickTicketTypeManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkTicketAdminTab);		
		UserExtension.IsElementPresent(driver, lnkTicketTypeManager);
		lnkTicketTypeManager.click();
		TicketTypeManagerPageObjects objPage = new TicketTypeManagerPageObjects(driver);
		return(objPage);
	}

	//Click on TicketAdmin - > lnkTicketQueueManager
	public TicketQueueManagerPageObjects ClickTicketQueueManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkTicketAdminTab);		
		UserExtension.IsElementPresent(driver, lnkTicketQueueManager);
		lnkTicketQueueManager.click();
		TicketQueueManagerPageObjects objPage = new TicketQueueManagerPageObjects(driver);
		return(objPage);
	}

	//Click on TicketAdmin - > lnkStudentManagerPanel
	public StudentManagerPanelPageObjects ClickStudentManagerPanel(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkTicketAdminTab);		
		UserExtension.IsElementPresent(driver, lnkStudentManagerPanel);
		lnkStudentManagerPanel.click();
		StudentManagerPanelPageObjects objPage = new StudentManagerPanelPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkBusinessUnitManager
	public BusinessUnitMgrPageObjects ClickBusinessUnitManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkBusinessUnitManager);
		lnkBusinessUnitManager.click();
		BusinessUnitMgrPageObjects objPage = new BusinessUnitMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkDepartmentManager
	public DepartmentMgrPageObjects ClickDepartmentManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkDepartmentManager);
		lnkDepartmentManager.click();
		DepartmentMgrPageObjects objPage = new DepartmentMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkNavigationManager
	public NavigationMgrPageObjects ClickNavigationManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkNavigationManager);
		lnkNavigationManager.click();
		NavigationMgrPageObjects objPage = new NavigationMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkSecurityGroupManager
	public SecurityGroupMgrPageObjects ClickSecurityGroupManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkSecurityGroupManager);
		lnkSecurityGroupManager.click();
		SecurityGroupMgrPageObjects objPage = new SecurityGroupMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkSecurityRoleManager
	public SecurityRoleMgrPageObjects ClickSecurityRoleManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkSecurityRoleManager);
		lnkSecurityRoleManager.click();
		SecurityRoleMgrPageObjects objPage = new SecurityRoleMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkTeamTypeManager
	public TeamTypeMgrPageObjects ClickTeamTypeManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkTeamTypeManager);
		lnkTeamTypeManager.click();
		TeamTypeMgrPageObjects objPage = new TeamTypeMgrPageObjects(driver);
		return(objPage);
	}

	//Click on SystemAdmin - > lnkUserAccountManager
	public UserAccountMgrPageObjects ClickUserAccountManager(WebDriver driver)
	{
		UserExtension.MouseOver(driver, lnkSystemAdminTab);		
		UserExtension.IsElementPresent(driver, lnkUserAccountManager);
		lnkUserAccountManager.click();
		UserAccountMgrPageObjects objPage = new UserAccountMgrPageObjects(driver);
		return(objPage);
	}
	
}
