package uiMap_DocCollection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyAccountDocumentsPage {
	public MyAccountDocumentsPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
  	//Document Tracking label
	@FindBy(how=How.CSS, using="h1")
	public WebElement lblDocTracking ;
	public String sdocTracking= "Document Tracking";
	
	//Documents  History label
	@FindBy(how=How.XPATH, using="//h2[text()='Document History']")
	public WebElement lblDocHistory;
	public String sdocHistory= "Document History";
	
	//Documents  Currently Due label
		@FindBy(how=How.XPATH, using="//h2[text()='Documents Currently Due']")
		public WebElement lblDocCurrDue;
		public String sdocCurrDue= "Documents Currently Due";

		//Live Help
	@FindBy(how=How.XPATH, using=".//img[@alt='live help']")
	public WebElement lblLiveHelp;
	
	//Table Column heading File
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='File']")
	public WebElement colhdngFile;
		
	//Table Column heading upload
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Upload']")
	public WebElement colhdngUpload;
		
	//Table Column heading f a x Cover Sheet
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Fax Cover Sheet']")
	public WebElement colhdngFaxCoverSheet;
	
	//Table Column heading Document Name
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Document Name']")
	public WebElement colhdngDocumentName;
		
	//Table Column heading Status
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Status']")
	public WebElement colhdngStatus;
		
	//Table Column heading Date Requested
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Date Requested']")
	public WebElement colhdngDateRequested;
	
	//Table Column heading Date Due
	@FindBy(how=How.XPATH, using=".//*[@id='overdueDocumentsGrid']/thead/tr/th/span[text()='Date Due']")
	public WebElement colhdngDateDue;
	
	//Upload File icon
	@FindBy(how=How.XPATH, using=".//img[@alt='Click to Upload a file']")
	public WebElement icouploadFile;
	
	//F a x Cover Sheet icon
	@FindBy(how=How.XPATH, using=".//img[@alt='Click to print a Fax Cover Sheet']")
	public WebElement icoFaxCoverSheet;
	
	
}
