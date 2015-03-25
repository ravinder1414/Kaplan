package uiMap_Orion3.SystemAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SecurityGroupMgrPageObjects {
	
	public SecurityGroupMgrPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > System Admin > Security Group Manager";
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_btnView")
	public WebElement btnView;
	public String strViewLabel = "View";

}
