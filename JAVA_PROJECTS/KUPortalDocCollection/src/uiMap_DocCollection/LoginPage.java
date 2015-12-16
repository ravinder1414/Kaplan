package uiMap_DocCollection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
  // userName
	@FindBy(how=How.ID, using="username")
	public WebElement txtUserName;
  
	// Password
	@FindBy(how=How.ID, using="password")
	public WebElement txtPassword;
		
	// UserType
	@FindBy(how=How.ID, using="userType")
	public WebElement ddUserType;
	
	// Forgot Password
	@FindBy(how=How.ID, using="passwordRecover")
	public WebElement lnkForgotPassword;
	
	// Login button
	@FindBy(how=How.ID, using="Login")
	public WebElement btnLogin;
	
	
	public void LoginToKUPortal(String Username, String Password){
		txtUserName.sendKeys(Username);
		txtPassword.sendKeys(Password);
		btnLogin.click();
	}
}
