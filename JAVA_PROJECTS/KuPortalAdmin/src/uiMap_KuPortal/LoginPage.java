package uiMap_KuPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}

	//PageHeader
	@FindBy(how=How.ID, using = "Login")
	public WebElement btnLogin;
	
	//txtUsername
	@FindBy(how=How.ID, using = "username")
	public WebElement txtUsername;

	//password
	@FindBy(how=How.ID, using = "password")
	public WebElement txtPassword;

	//logout link
	@FindBy(how=How.LINK_TEXT, using = "Logout")
	public WebElement lnkLogout;

	public void  LoginToKuPortal(String sUsername, String sPassword)
	{
		txtUsername.sendKeys(sUsername);
		txtPassword.sendKeys(sPassword);
		btnLogin.click();
	}
}
