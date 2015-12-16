package uiMap_KuPortalAdmin.portalContent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SMEDirectoryPage {

	public SMEDirectoryPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}
	
	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "SME Directory";

	//Add New SME/Upload SME Photo
	@FindBy(how=How.LINK_TEXT, using = "Add New SME/Upload SME Photo")
	public WebElement lnkAddNewSME_UploadSMEPhoto;
	
	//SME Directory Data Table
	@FindBy(how=How.XPATH, using = "//*[@class='dataTable']")
	public WebElement tblSMEDirectoryDataTable;
	public int iCol_TotalColumns = 6;
	public String sColumnSequence = "Last Name-First Name-Job Title-Photo-Edit-Actions";
	
	//SME Directory
	@FindBy(how=How.ID, using= "FirstName")
	public WebElement txtFirstName;
	
	//SME Directory: Create - LastName
	@FindBy(how=How.ID, using= "LastName")
	public WebElement txtLastName;
	
	//SME Directory: Create - JobTitle
	@FindBy(how=How.ID, using= "JobTitle")
	public WebElement txtJobTitle;

	//SME Directory: Create - 
	@FindBy(how=How.XPATH, using= "//*[@id='form']/div/div[1]/div/input[4]")
	public WebElement btnBrowsePhoto;

	//SME Directory: Create - 
	@FindBy(how=How.XPATH, using= "//*[@id='form']/div/div[1]/div/input[5]")
	public WebElement btnUploadPhoto;

	//SME Directory: Create - 
	@FindBy(how=How.ID, using= "PhotoPath")
	public WebElement txtPhotoLocation;

	//SME Directory: Create - submit
	@FindBy(how=How.ID, using= "submit")
	public WebElement btnSave;

	//SME Directory: Create - cancel
	@FindBy(how=How.ID, using= "cancel")
	public WebElement btnCancel;

	@FindBy(how=How.XPATH, using= "//*[@id='photoPicker']/table/tbody/tr[1]/td[1]/div/button")
	public WebElement btnSelectPhotoPicker;
	@FindBy(how=How.XPATH, using= "//*[@id='photoPicker']/table/tbody/tr[1]/td[1]/div/img")
	public WebElement imgSelectPhotoPicker;

	


}
