package docCollection;

public class OR {
	
	//----FaxCover Icon TCs
	public final static String xpathMyDocumentLnk = "//a[contains(text(),'My Documents')]";
	public final static String xpathDocTrackHead = "//span/div";
	public final static String xpathFaxCoverImage = "(//input[@type='image'])[2]";
	
	//-----My Doc Columns
	public final static String xpathDocTrackHead1 = "//span[@id='ctl00_ctl10_4992']/div";    //Doc Tracking
	public final static String xpathDocCurrentlyDue = "//td[@id='ctl00_ctl11_tcMainCell3']/table/tbody/tr/td/span"; //doc currently due
	public final static String xpathColumnFile = "//table[@id='ctl00_ctl11_grdCurrentlyDue']/tbody/tr/th";   //file
	public final static String xpathColumnUpload = "//table[@id='ctl00_ctl11_grdCurrentlyDue']/tbody/tr/th[2]";    //upload
	public final static String xpathColumnFaxCoverSheet = "//table[@id='ctl00_ctl11_grdCurrentlyDue']/tbody/tr/th[3]";     //faxcoversheet
	public final static String xpathColumnDocName= "//a[contains(text(),'Document Name')]";     //document name
	public final static String xpathColumnStatus = "//a[contains(text(),'Status')]";         //status
	public final static String xpathColumnDateRequested = "//a[contains(text(),'Date Requested')]";     //date requested
	public final static String xpathColumnDueDate = "//a[contains(text(),'Date Due')]";          //due date
	public final static String xpathColumnDocHistory = "//td[@id='ctl00_ctl11_tcMainCell4']/table/tbody/tr/td/span";   //doc history
	 //Document Tracking section column names
	public final static String xpathColumnFileDocTrk = "//table[@id='ctl00_ctl11_grdDocumentHistory']/tbody/tr/th";      //file
	public final static String xpathColumnDocNameDocTrk = "(//a[contains(text(),'Document Name')])[2]";        //doc name
	public final static String xpathColumnStatusDocTrk = "(//a[contains(text(),'Status')])[2]";            //status
	public final static String xpathColumnDateRequestedDocTrk = "(//a[contains(text(),'Date Requested')])[2]";           //date requested
	public final static String xpathColumnDueDateDocTrk = "(//a[contains(text(),'Date Due')])[2]";            //date due
	public final static String xpathColumnDateReceivedDocTrk = "//a[contains(text(),'Date Received')]";         //date received

	//-----UnSupported File
	public final static String xpathAttachmentIcon  = "input[type=\"image\"]"; //attachment icon
	public final static String xpathFileTextbox = "ctl00$ctl11$ctl03";             //filetextbox 
	public final static String xpathUploadButton = "ctl00$ctl11$ctl05";             //upload button
	public final static String xpathNoteMessage = "//td[@id='ctl00_ctl11_tcMainCell7']/table/tbody/tr[2]/td/span";             //note message
	public final static String xpathErrorMsg = "//div/table/tbody/tr/td[2]/span";           //error message
	
	
}
