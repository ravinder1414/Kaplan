package uiMap_Orion3.TicketAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudentManagerPanelPageObjects {
	
	public StudentManagerPanelPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Ticket Admin > Student Manager Panel";
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnSearch")
	public WebElement btnView;
	public String strViewLabel = "View";

}
