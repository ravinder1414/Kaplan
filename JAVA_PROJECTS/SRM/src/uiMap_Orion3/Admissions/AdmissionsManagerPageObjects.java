package uiMap_Orion3.Admissions;
//Import files
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



import commonfunctions.UserExtension;

public class AdmissionsManagerPageObjects {

	public AdmissionsManagerPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	public String sAdmissionMgr_WindowName = "";
	public String sStudentManager_WindowName = "StuMgr";
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Admissions > Admissions Manager";
	
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_lnkAddNewLead")
	public WebElement lnkAddNewLead;
	
	@FindBy(how=How.ID, using="maintable")
	public WebElement tblLeadsTable;
	
	@FindBy(how=How.XPATH, using="//*[@id='maintable']/tbody[3]/tr[1]/td[3]/a")
	
	public WebElement lnkFirstLeadInTable;
	
	public AddNewLeadPageObjects ClickAddNewLeadLink(WebDriver driver)
	{
		UserExtension.IsElementPresent(driver, lnkAddNewLead);
		lnkAddNewLead.click();
		AddNewLeadPageObjects objPage = new AddNewLeadPageObjects(driver);
		return(objPage);
	}

	public boolean VerifyLeadAtTopInAdmissionManager(WebDriver driver, String sLeadText)
	{
		UserExtension.IsElementPresent(driver, lnkFirstLeadInTable);
		if(UserExtension.WaitTillGetTextValueIs(driver, lnkFirstLeadInTable, sLeadText))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
}
