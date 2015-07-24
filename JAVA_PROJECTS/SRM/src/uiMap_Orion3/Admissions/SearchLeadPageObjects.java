package uiMap_Orion3.Admissions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchLeadPageObjects {
	
	public SearchLeadPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Admissions > Search Leads";
	
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_btnSearch")
	public WebElement btnSearchLeads;
	public String strSearchButtonLabel="Search Leads";
	
	@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ifContent")
	public WebElement frmSearchLead;

}
