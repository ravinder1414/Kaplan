package uiMap_KuPortalAdmin.Miscellaneous;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OnlinePaymentsPage {

	public OnlinePaymentsPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Online Payments";

	//View
	@FindBy(how=How.ID, using = "savePhone")
	public WebElement btnSavePhone;

}
