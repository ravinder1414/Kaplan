package uiMap_Orion3.OpsAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class IQMCancelLeadTransferPageObjects {

	public IQMCancelLeadTransferPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Ops Admin > iQM > iQM - Cancel Lead Transfer";
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnCancel")
	public WebElement btnSendTransfer;
	public String sSendTransfer = "Send Cancel Request";
}