package uiMap_KuPortalAdmin.portalUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PortalPreviewPage {

	public PortalPreviewPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "//*[@id='aspnetForm']/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Portal Preview";
	
	//ddlUserType
	@FindBy(how=How.ID, using = "dll_UserType")
	public WebElement ddlUserType;
	
}
