package uiMap_KuPortalAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class LandingPage {

	
	public LandingPage(WebDriver driver) {
		//Initialize PageObjects.
				PageFactory.initElements(driver, this);
	}
	
	//Username
	@FindBy(how=How.ID, using = "username")
	public WebElement txtUserName;
	@FindBy(how=How.XPATH, using = "//td[text()='Username:']")
	public WebElement lblUsername;
	
	
	//Password
	@FindBy(how=How.ID, using = "password")
	public WebElement txtPassword;
	@FindBy(how=How.XPATH, using = "//td[text()='Password:']")
	public WebElement lblPassword;
	
	//Login Domain
	@FindBy(how=How.XPATH, using = "//select[@id='domain']")
	public WebElement ddlLoginDomain;
	
	//Login button	
	@FindBy(how=How.XPATH, using = "//input[@type='submit']")
	public WebElement btnLogin;
		
	
	public void LoginToAdminPortal(String sUsername, String sPassword)
	{
		txtUserName.sendKeys(sUsername);
		txtPassword.sendKeys(sPassword);
		Select objSelect = new Select(ddlLoginDomain);
		objSelect.selectByVisibleText("Admin");
		btnLogin.click();
		
	}
}
