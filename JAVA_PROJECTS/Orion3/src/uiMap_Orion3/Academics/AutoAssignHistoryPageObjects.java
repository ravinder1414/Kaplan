package uiMap_Orion3.Academics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AutoAssignHistoryPageObjects {
	
	public AutoAssignHistoryPageObjects(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//Left Navigation Name plate
	@FindBy(how=How.ID_OR_NAME,using="ctl00_ctl00_CookieNav")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Academics > Education Advisor Auto Assign History";
	
	//Most recent Academic Advisor Auto Assignment history table
	@FindBy(how=How.XPATH, using="//*[@id='ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_lvList_itemPlaceholderContainer']/tbody/tr[1]/th")
	public WebElement tblMostRecentAssignment;
	public String strMostRecentAssignment = "Most recent Academic Advisor Auto Assignment history";
	
}