package uiMap_KuPortalAdmin.portalContent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudentStatusBlockingPage {

	public StudentStatusBlockingPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}
	
	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Student Status Blocking - Hold Code Management";

	//Link Hold Code Management
	@FindBy(how=How.XPATH, using = "//*[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/ul/li[1]/span/a")
	public WebElement lnkHoldCodeManagement;

	//Link Hold Code Management
	@FindBy(how=How.XPATH, using = "//a[text()='Student Permissions']")
	public WebElement lnkStudentPermissions;

	//Link Reports
	@FindBy(how=How.XPATH, using = "//a[text()='Reports']")
	public WebElement lnkReports;

	//Hold Code Management - Select Campus
	@FindBy(how=How.ID, using = "Campus")
	public WebElement ddlCampus;

	//Hold Code Management - AdTranscript Hold Code 
	@FindBy(how=How.XPATH, using = "//*[@id='AllHoldCodes']/table/tbody")
	public WebElement tblAllHoldCode;

	//Hold Code Management - ViewEdit
	@FindBy(how=How.ID, using = "BtnSaveEdit")
	public WebElement btnViewEdit;

	//Hold Code Management - AdTranscript Hold Code 
	@FindBy(how=How.XPATH, using = "//*[@id='HoldCodeViewForm']/table/tbody/tr[2]/td[2]")
	public WebElement lblHoldCodeName;

	//Hold Code Management - BtnSave
	@FindBy(how=How.ID, using = "BtnSave")
	public WebElement btnSave;

	//Hold Code Management - BlockActive
	@FindBy(how=How.ID, using = "BlockActive")
	public WebElement chkBlockActive;

	//Hold Code Management - linkPermissions
	@FindBy(how=How.ID, using = "linkPermissions")
	public WebElement lnkPermissions;
	
	//Hold Code Management - BlockMessage
	@FindBy(how=How.ID, using = "BlockMessage")
	public WebElement txtBlockMessage;
	public String sBlockMessage = "Your student record has a transcript hold due to financial or academic reasons; an official transcript cannot be released. Please contact the appropriate financial or advising representative at your campus to resolve this transcript hold.";
	
	//Student Permissions - View Permission By
	@FindBy(how=How.ID, using = "ViewBy")
	public WebElement ddlViewBy;
	
	//Student Permissions - ViewByTypeDropDown
	@FindBy(how=How.ID, using = "ViewByTypeDropDown")
	public WebElement ddlViewByType;
	
	//Student Permissions - Block
	@FindBy(how=How.ID, using = "BtnSaveEdit")
	public WebElement btnBlock;

	//Student Permissions - chkPages - "//input[text()[contains(.,'PDF')]]"
	@FindBy(how=How.XPATH, using = "//*[@id='PermissionForm']/table[1]/tbody")
	public WebElement tblAllPagesFeatures;

	//Student Permissions - Confirmation Text
	@FindBy(how=How.XPATH, using = "//*[@id='TB_ajaxContent']/div/div[2]/h3")
	public WebElement lblConfirmationHeading;

	//Student Permissions - Confirmation Box
	@FindBy(how=How.ID, using = "TB_ajaxContent")
	public WebElement dlgConfimationBox;

	//Student Permissions - Cancel button on Confirmation box
	@FindBy(how=How.XPATH, using = "//*[@id='TB_ajaxContent']//Input[@value='Cancel']")
	public WebElement btnCancel_Confirmation;

	//Student Permissions - Save button on Confirmation box
	@FindBy(how=How.XPATH, using = "//*[@id='TB_ajaxContent']//Input[@value='Save']")
	public WebElement btnSave_Confirmation;

	//Student Permissions - Close link on Confirmation box
	@FindBy(how=How.XPATH, using = "//*[@id='TB_ajaxContent']//Input[@value='Save']")
	public WebElement lnkClose_Confirmation;

	//Reports
	@FindBy(how=How.XPATH, using="//*[@id='ReportTable']/table")
	public WebElement tblReports;
	public int iCol_HoldCode = 1;
	public int iCol_Blocked = 2;
	public int iCol_UnBlocked = 3;
	
}
