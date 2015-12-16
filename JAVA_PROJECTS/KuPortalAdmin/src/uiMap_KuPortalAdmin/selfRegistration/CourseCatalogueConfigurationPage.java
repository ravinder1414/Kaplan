package uiMap_KuPortalAdmin.selfRegistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CourseCatalogueConfigurationPage {

	public CourseCatalogueConfigurationPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Self Registration: Course Catalogue Configuration";
	
	//tblCourseReqHeader
	@FindBy(how=How.XPATH, using = "//table[@class='dataTable']/thead/tr/th[1]")
	public WebElement tblCourseReqHeader;
	public String sTableHeader = "Course";
	
}
