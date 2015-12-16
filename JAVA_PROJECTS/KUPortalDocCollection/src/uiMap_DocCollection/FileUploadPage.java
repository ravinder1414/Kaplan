package uiMap_DocCollection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FileUploadPage {
	public FileUploadPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}	
	//Choose File to upload label
	@FindBy(how=How.ID, using="lblFile")
	public WebElement lblupFile;
	public String slblupfile = "Choose a file to upload:";
	
	//browse to select File
	@FindBy(how=How.ID, using="upfile")
	public WebElement btnBrowToSeFile;
	
	//Note for acceptable file types and size
	@FindBy(how=How.ID, using="lblFileTypes")
	public WebElement lblFileTypes;
	public String slblFileTypes="(Acceptable types are; *.doc, *.docx, *.pdf, *.jpg, *.jpeg, *.tif and *.tiff. Maximum file size is 5 MB.)";
	
	//Close button at bottom
	@FindBy(how=How.CSS, using="input.cmsbutton1")
	public WebElement btnClose;
	
	//Upload File button at bottom
	@FindBy(how=How.ID, using="uploadButton")
	public WebElement btnUpload;
	
	//Close link
	@FindBy(how=How.LINK_TEXT, using="close")
	public WebElement lnkClose;
	
	//Image Alert for eXCEED SIZE Validations
	@FindBy(how=How.ID, using= "imgalertmsg")
	public WebElement imgAlertES;
	
	//Image Alert for Invalid File Type Validations
		@FindBy(how=How.ID, using= "imgalert")
		public WebElement imgAlertIFT;
	
	//Validation message for size exceed
		@FindBy(how=How.ID, using= "errMsg")
		public WebElement msgSizeExceed;
		public String smsgSizeExceed = "The file you attempted to upload exceeds our size limit of 5 MB.";
	
	//Validation message for InvalidFileType
	@FindBy(how=How.ID, using= "validationcomments")
	public WebElement msgInvalidFiletype;
	public String smsgInvalidFileType= "Please select an acceptable file type to upload. Acceptable files types include *.doc, *.docx, *.pdf, *.jpg, *.tif., *.jpeg, *.tiff.";
		
  }
