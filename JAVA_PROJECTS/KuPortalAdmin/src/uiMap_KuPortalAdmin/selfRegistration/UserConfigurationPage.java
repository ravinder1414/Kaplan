package uiMap_KuPortalAdmin.selfRegistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserConfigurationPage {

	public UserConfigurationPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Self Registration: User Configuration";
	
	//tblStudentConfigHeader
	@FindBy(how=How.XPATH, using = "//table[@class='dataTable']/thead/tr/th[1]")
	public WebElement tblStudentConfigHeader;
	public String sTableHeader = "Student Type";
}
