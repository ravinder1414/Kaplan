package uiMap_KuPortalAdmin.portalUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchExistingUserPage {

	public SearchExistingUserPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "Search Portal Users";
	
	//btnSearch
	@FindBy(how=How.XPATH, using = "//input[@type='submit']")
	public WebElement btnSearch;
	
	//Last Name Label
	@FindBy(how=How.XPATH, using = "//div[@class='contentInner clearfix']/form/div/nobr[1]/label")
	public WebElement lblLastName;
	
	//Last Name Field
	@FindBy(how=How.ID, using = "LastName")
	public WebElement txtLastName;

	//First Name Label
	@FindBy(how=How.XPATH, using = "//div[@class='contentInner clearfix']/form/div/nobr[2]/label")
	public WebElement lblFirstName;
	
	//First Name Field
	@FindBy(how=How.ID, using = "FirstName")
	public WebElement txtFirstName;

	//User Name Label
	@FindBy(how=How.XPATH, using = "//div[@class='contentInner clearfix']/form/div/nobr[3]/label")
	public WebElement lblUserName;
	
	//User Name Field
	@FindBy(how=How.ID, using = "UserName")
	public WebElement txtUserName;

	//User Name Label
	@FindBy(how=How.XPATH, using = "//div[@class='contentInner clearfix']/form/div/nobr[4]/label")
	public WebElement lblUserType;

	//User Name Field
	@FindBy(how=How.ID, using = "UserType")
	public WebElement ddlUserType;

	//User Name Label
	@FindBy(how=How.XPATH, using = "//div[@class='contentInner clearfix']/form/div/nobr[5]/label")
	public WebElement lblCampus;

	//User Name Field
	@FindBy(how=How.ID, using = "CampusID")
	public WebElement ddlCampusId;
	
	//UserTable Field
	@FindBy(how=How.XPATH, using = "//table[@class='dataTable']")
	public WebElement tblUser;
	
	//Table Columns
	public int iCol_PortalUserID = 1;
	public int iCol_FirstName = 2;
	public int iCol_LastName = 3;
	public int iCol_UserName = 4;
	public int iCol_ViewEditLink = 9;
	
}
