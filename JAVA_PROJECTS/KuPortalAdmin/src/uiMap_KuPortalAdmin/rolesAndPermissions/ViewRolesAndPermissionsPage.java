package uiMap_KuPortalAdmin.rolesAndPermissions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ViewRolesAndPermissionsPage {

	public ViewRolesAndPermissionsPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "All Roles";
	
	//Create New Role Button
	@FindBy(how=How.LINK_TEXT, using = "Create New Role")
	public WebElement btnCreateNewRole;
}
