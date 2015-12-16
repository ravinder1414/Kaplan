package uiMap_KuPortalAdmin.portalContent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PresentationManagerPage {

	public PresentationManagerPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}
	
	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Presentation Manager";

	//Search Button
	@FindBy(how=How.ID, using = "searchBtn")
	public WebElement btnSearch;

	//createPresentationLink
	@FindBy(how=How.ID, using = "createPresentationLink")
	public WebElement lnkCreatePresentation;

	//Assign Presentation To School Link
	@FindBy(how=How.LINK_TEXT, using = "Assign Presentations to School")
	public WebElement lnkAssignPresentationsToSchool;

	//Assign Presentation To Group Link
	@FindBy(how=How.LINK_TEXT, using = "Assign Presentations to Group")
	public WebElement lnkAssignPresentationsToGroup;
	
}
