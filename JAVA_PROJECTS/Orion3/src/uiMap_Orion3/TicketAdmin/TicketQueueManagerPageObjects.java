package uiMap_Orion3.TicketAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TicketQueueManagerPageObjects {
	

	public TicketQueueManagerPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Ticket Admin > Ticket Queue Manager";
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_OkButton")
	public WebElement btnSearch;
	public String strSearchLabel = "Search";
}
