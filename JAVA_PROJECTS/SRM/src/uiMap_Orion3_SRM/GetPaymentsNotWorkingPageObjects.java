package uiMap_Orion3_SRM;


	//Import files
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

	public class GetPaymentsNotWorkingPageObjects {
			
			public GetPaymentsNotWorkingPageObjects(WebDriver driver) {
				
				//Initialize a Orion1ProgramRollupMappingPageObjects.
				PageFactory.initElements(driver, this);
			}
			
			
			
		
			
			
			
			
			// Search Input Text Box
			@FindBy(how=How.XPATH, using= "//input[@id='phSearchInput']")
			public WebElement searchInputTextBox;
			
			

			// Search Input Button
			@FindBy(how=How.XPATH, using= "//input[@id='phSearchButton']")
			public WebElement searchButton;
			
			
			// Recent Payments button
			@FindBy(how=How.XPATH, using="//input[@name='recent_deposits']")
			public WebElement recentPaymentsButton;
			
			
			
			//Payment Amount
			@FindBy(how=How.XPATH, using="//td[6]")
			public WebElement paymentAmountVerify;
			public String paymentAmountVerifyText="Verification of Amount on Payment Page failed";
			
			
			
			
			
			
		
			
			
			
			}
			



