package uiMap_KuPortalAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LiveHelpPage {

		  //Initialize Homepage elemnets
		  public LiveHelpPage(WebDriver driver) {
			  PageFactory.initElements(driver, this);
		  }
		  
		//PageHeader
			@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
			public WebElement lblPageHeader;
			public String sPageHeaderTxt = "Live Help";
			
			//Create New Role Button
			@FindBy(how=How.CLASS_NAME, using = "dataTable")
			public WebElement tblLiveHelpConfig;
}
