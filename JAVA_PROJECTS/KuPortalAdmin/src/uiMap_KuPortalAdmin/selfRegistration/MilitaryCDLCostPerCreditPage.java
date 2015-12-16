package uiMap_KuPortalAdmin.selfRegistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MilitaryCDLCostPerCreditPage {

	public MilitaryCDLCostPerCreditPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Military Course Description Letter Cost Per Credit";
	
	//btnAddNew
	@FindBy(how=How.ID, using = "btnAdd")
	public WebElement btnAddNew;
}
