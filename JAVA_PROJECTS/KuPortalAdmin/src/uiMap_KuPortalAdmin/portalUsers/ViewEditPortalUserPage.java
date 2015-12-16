package uiMap_KuPortalAdmin.portalUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ViewEditPortalUserPage {

	public ViewEditPortalUserPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
	public WebElement lblPageHeader;
	public String sPageHeaderTxt = "View / Edit Portal User";
	
	//Generate New Username
	@FindBy(how=How.XPATH, using = "//*[@id='divGeneralinformation']/span[3]/a")
	public WebElement lnkGenerateNewUserName;

	//lnkUserName
	@FindBy(how=How.XPATH, using = "//*[@id='divGeneralinformation']/span[2]/a")
	public WebElement lnkUserName;

	//lnkCampusVueStudentTab
	@FindBy(how=How.XPATH, using = "//*[@id='tabs']/ul/li[2]/a")
	public WebElement lnkCampusVueStudentTab;
	
	//lnkCampusVueInstructorTab
	@FindBy(how=How.XPATH, using = "//*[@id='tabs']/ul/li[3]/a")
	public WebElement lnkCampusVueInstructorTab;
	
	//lnkResetStudentPasswordTab
	@FindBy(how=How.XPATH, using = "//*[@id='tabs']/ul/li[4]/a")
	public WebElement lnkResetStudentPasswordTab;

	
	//Portal Account - Generate New Username heading
	@FindBy(how=How.XPATH, using = "//*[@id='PortalUserAccount']/form/h3")
	public WebElement lblGenerateNewUsernameHeading;
	public String sGenerateUserNameHeading = "Generate New Portal Username";
	
	//Portal Account - Current UserName
	@FindBy(how=How.XPATH, using = "//*[@id='PortalUserAccount']/form/table/tbody/tr[2]/td[3]")
	public WebElement lblCurrentUsername;
	
	//Portal Account - New UserName
	@FindBy(how=How.XPATH, using = "//*[@id='PortalUserAccount']/form/table/tbody/tr[2]/td[4]/b/u")
	public WebElement lblNewUsername;
	
	//Portal Account - Generate New Username heading
	@FindBy(how=How.ID, using = "btnAction")
	public WebElement btnSave	;
	
	//ResetStudentPasswordTab - Student Identity Verified
	@FindBy(how=How.ID, using = "chkVerication")
	public WebElement chkStudentIdentity;
	
	//ResetStudentPasswordTab - btnChangePassword
	@FindBy(how=How.ID, using = "btnChangePassword")
	public WebElement btnChangePassword;

	//ResetStudentPasswordTab - txtAdminPassword
	@FindBy(how=How.ID, using = "txtAdminPassword")
	public WebElement txtAdminPassword;
	
	//ResetStudentPasswordTab - txtNewPassword
	@FindBy(how=How.ID, using = "txtNewPassword")
	public WebElement txtNewPassword;
	
	//ResetStudentPasswordTab - txtConfirmPassword
	@FindBy(how=How.ID, using = "txtConfirmPassword")
	public WebElement txtConfirmPassword;
	
	//Reset Password MEssage
	@FindBy(how=How.ID, using = "resetResult")
	public WebElement lblResetResult;

}
