package uiMap_KuPortalAdmin.portalContent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InquiryViewPage {
	
	public InquiryViewPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}
	
	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Inquiry View";

	//View Button
	@FindBy(how=How.ID, using = "viewButton")
	public WebElement btnView;

	//Inquiry Data Table
	@FindBy(how=How.CLASS_NAME, using = "dataTable")
	public WebElement tblInquiryDataTable;
	
	//Response By
	@FindBy(how=How.ID, using = "ResponseSMEID")
	public WebElement ddlResponseBySME;
	
}
