package uiMap_KuPortalAdmin.portalUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewAdministratorMappingPage {

	public NewAdministratorMappingPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Map New Portal-Administrator Account";
	
	//btnSearch
	@FindBy(how=How.XPATH, using = "//input[@type='submit']")
	public WebElement btnSearch;
}
