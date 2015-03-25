package uiMap_Orion3.Academics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RepGroupMgrPageObjects {

	public RepGroupMgrPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
				PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.ID_OR_NAME,using="ctl00_ctl00_CookieNav")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Academics > Education Advisor Rep Manager";
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnSearch")
	public WebElement btnView;
	public String strViewButtonLabel="View";
}
