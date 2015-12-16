package uiMap_DocCollection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	//Logout link
	@FindBy(how=How.LINK_TEXT, using= "Logout")
	public WebElement lnkLogout;
	
	//Home link
	@FindBy(how=How.LINK_TEXT, using= "Home")
	public WebElement lnkHome;
	
	//My Account link
	@FindBy(how=How.XPATH , using= ".//li/a[text()='My Account']")
	public WebElement lnkMyAccount;
	
	//My Account Documents link
	@FindBy(how=How.LINK_TEXT, using="My Account Documents")
	public WebElement lnkMyAccountDocuments;
	 
	//Navigate to My Accounts Page
	public void NavigateToMyAccDocs(WebDriver driver){
		Actions act = new Actions(driver);
		 act.moveToElement(lnkMyAccount);
		 act.build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 lnkMyAccountDocuments.click();
	}

}
