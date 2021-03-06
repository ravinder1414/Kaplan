package uiMap_KuPortalAdmin.rolesAndPermissions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateRolesPage {

	public CreateRolesPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Create Roles and Permissions";
	
	//Create New Role Button
	@FindBy(how=How.ID_OR_NAME, using = "CreateRoleBttn")
	public WebElement btnCreateNewRole;
}
