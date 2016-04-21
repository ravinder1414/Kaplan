package uiMap_Orion3.Admissions;

//Import files

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class AddNewLeadPageObjects {

	public AddNewLeadPageObjects(WebDriver driver) {
		
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	public String sAddNewLead_WindowName = "NewLead";
//	Lead Origination Type
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblLeadOriginationType_0")
	public WebElement rbtnLeadType_InfoCall;

	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblLeadOriginationType_1")
	public WebElement rbtnLeadType_Referral;

	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblLeadOriginationType_2")
	public WebElement rbtnLeadType_LiveChat;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblLeadOriginationType_3")
	public WebElement rbtnLeadType_WarmTransfer;
	
//	Source Code
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_TextBoxSourceCode")
	public WebElement txtSourceCode;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_BtnSourceCode")
	public WebElement btnSourceCode;
	
//	ChannelGroups 
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_0")
	public WebElement rbtnCG_Internet;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_1")
	public WebElement rbtnCG_Email;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_2")
	public WebElement rbtnCG_DirectMail;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_3")
	public WebElement rbtnCG_GOK;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_4")
	public WebElement rbtnCG_B2B;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_5")
	public WebElement rbtnCG_WordOfMouth;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_6")
	public WebElement rbtnCG_Events;
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_7")
	public WebElement rbtnCG_UnSourced;
	
	
	@FindBy(how=How.ID_OR_NAME,using="ctl00_PageBodyPlaceHolder_RblChannelGroups_8")
	public WebElement rbtnCG_Print;
	
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_DdlPromotionCode")
	public WebElement ddlPromotionCode;	
	public By ddlPromotionCodeLocator = new By.ById("ctl00_PageBodyPlaceHolder_DdlPromotionCode");
	
	public String sPromotionCodeDefaultText_before =  "Please Select a Channel Group First";
	public String sPromotionCodeDefaultText_after = "Please Choose a Promotion";
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_DdlAreaOfStudy")
	public WebElement ddlAreaOfStudyTemp;
	public String sAreaofStudyDefaultText = "Select An Area of Study";
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_DdlProgramOfInterest")
	public WebElement ddlProgramOfInterest;
	public String sProgramOfInterest = "Select a Program";

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Fname")
	public WebElement txtFirstName;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Lname")
	public WebElement txtLastName;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Email")
	public WebElement txtEmailAddress;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_HPhone")
	public WebElement txtHomePhone;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_WPhone")
	public WebElement txtDayPhone;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Address")
	public WebElement txtAddress;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Address2")
	public WebElement txtAddress2;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_City")
	public WebElement txtCity;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_State")
	public WebElement ddlState;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Zip")
	public WebElement txtZipCode;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Country")
	public WebElement ddlCountry;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_HighestEducation")
	public WebElement ddlHightestEduction;

	@FindBy(how=How.XPATH, using = ".//*[@id='j_id0:addaleadid:leadblock:j_id44:10:j_id50:0']")
	public WebElement rbtnMilitarySpouse_Yes;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Military_1")
	public WebElement rbtnMilitarySpouse_No;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Military2")
	public WebElement ddlMilitary;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_TCPA_Disc_0")
	public WebElement rtbnTCPA_Yes;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_TCPA_Disc_1")
	public WebElement rtbnTCPA_No;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_ButtonSubmitSif")
	public WebElement btnAddALead;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_SubmissionSuccessMessage")
	public WebElement lblSuccessMsg;
	public String sLeadSuccessMsg = "Success: Your lead is being created";

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_ButtonClosePage")
	public WebElement btnClosePage;

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_ButtonResetPage")
	public WebElement btnResetPage;
	
	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_AddALeadUpdateProgressPanel")
	public WebElement imgProgressPanel;
	
	
	//Reusable Methods
	public void SelectLeadOriginatedFromAs(String sLeadType)
	{
		if(sLeadType.equalsIgnoreCase("InfoCall"))
		{
			rbtnLeadType_InfoCall.click();
		}
		else if(sLeadType.equalsIgnoreCase("LiveChat"))
		{
			rbtnLeadType_LiveChat.click();
		}
		else if(sLeadType.equalsIgnoreCase("Referral"))
		{
			rbtnLeadType_Referral.click();
		}
		else if(sLeadType.equalsIgnoreCase("WarmTransfer"))
		{
			rbtnLeadType_WarmTransfer.click();
		}
	}
	
	public void SelectChannelGroupAs(String sChannelGroup)
	{
		if(sChannelGroup.equalsIgnoreCase("Internet"))
		{
			rbtnCG_Internet.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("Email"))
		{
			rbtnCG_Email.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("DirectMail"))
		{
			rbtnCG_DirectMail.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("GOK"))
		{
			rbtnCG_GOK.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("B2B"))
		{
			rbtnCG_B2B.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("WordofMouth"))
		{
			rbtnCG_WordOfMouth.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("Events"))
		{
			rbtnCG_Events.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("Unsourced"))
		{
			rbtnCG_UnSourced.click();
		}
		else if(sChannelGroup.equalsIgnoreCase("Print"))
		{
			rbtnCG_Print.click();
		}

	}
		@FindBy(how=How.ID, using = "phSearchInput")
		public WebElement search_SRM;
		
		@FindBy(how=How.ID, using = "phSearchButton")
		public WebElement btnsearch_SRM;
		
		@FindBy(how=How.ID, using = "secondSearchText")
		public WebElement txtSecond_search_SRM;
		
		@FindBy(how=How.ID, using = "secondSearchButton")
		public WebElement btnsearch_again;
		
		//Search All Button
		
		@FindBy(how=How.ID, using = "searchAllSummaryView")
		public WebElement btnsearch_All;
		
		
		
		@FindBy(how=How.XPATH, using = ".//*[@id='Lead_body']/table/tbody/tr[2]/td[7]")
		public WebElement txtInquiryStatus;
		

		@FindBy(how=How.XPATH, using = ".//*[@id='Opportunity_body']/table/tbody/tr[3]/th/a")
		public WebElement txtOpportunityresult;
		
		@FindBy(how=How.XPATH, using = ".//*[@id='Lead_body']/table/tbody/tr[2]/td[6]/a")
		public WebElement txtEmailAddressVerification;
		
		@FindBy(how=How.XPATH, using = "//h3/span[contains(text(), 'Inquiries')]")
		public WebElement headingInquiry;
		
		@FindBy(how=How.XPATH, using = "//h3/span[contains(text(), 'Opportunities')]")
		public WebElement headingOppertunities;
		//Search Result of Lead 
		
	    @FindBy(how=How.XPATH, using="html/body/div[1]/div[2]/table/tbody/tr/td/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[2]/table/tbody/tr[2]/th/a")
		public WebElement lnkSearchResult;
	    
	    //Is Military SIF Check Box in Inquiry Details page 

	    @FindBy(how=How.XPATH, using="(//td[text()='Is Military SIF']/following-sibling::td/div/img)[1]")
		public WebElement cbIsMilitarySIF;
	    
	   	//Is Military check box in Inquiry Lead Details Page 
	    @FindBy(how=How.XPATH, using="(//td[text()='Is Military']/following-sibling::td/div/img)[1]")
	    public WebElement cbIsMilitary;
	    
	    //SyStudent id 
	  	@FindBy(how=How.XPATH, using="//td[text()='syStudentID']/following-sibling::td/div")
	  	public WebElement txtSyStudent;
	  		
	  	@FindBy(how=How.XPATH, using="(//td[text()='Re-SIF']/following-sibling::td/div/img)[1]")
	 	public WebElement cbReSIF;
	  	
	  	//Master sif Master Id 
	  	@FindBy(how=How.XPATH, using="(//td[text()='mkSIFMasterID']/following-sibling::td/div)[1]")
	  	public WebElement txtMasterSifID;
	  	
	  	//Master sif id in opportunity 
	  	@FindBy(how=How.XPATH, using="//span[text()='mkSIFMasterID']/parent::td/following-sibling::td[1]/div")
	  	public WebElement txtMasterSifIDOpp;
	  	
	  	//inquiry status 
	  	
	  	@FindBy(how=How.XPATH, using="(//td[text()='Inquiry Status']/following-sibling::td/div)[1]")
	  	public WebElement txtInquiryStatusLead;	
	  	
	  	//Opportunity Stage 
	  	@FindBy(how=How.XPATH, using="(//td[text()='Stage']/following-sibling::td/div)[1]")
	  	public WebElement txtInquiryStageOppLead;	
	  	
	  	//Remarketing Reason 
	  	@FindBy(how=How.XPATH, using="(//td[text()='Remarketing Reason']/following-sibling::td/div)[1]")
	  	public WebElement txtRemarketingReason;	
	  	
		
	  	//Remarketing Reason opportunity
	  	@FindBy(how=How.XPATH, using="(//td[text()='Remarketing Reason']/following-sibling::td/div)[1]")
	  	public WebElement txtRemarketingReasonOpp;	
	  	
	  	//Remarketing sub Reason 
	  	@FindBy(how=How.XPATH, using="(//td[text()='Remarketing Sub Reason']/following-sibling::td/div)[1]")
	  	public WebElement txtRemarketingsubReason;
	  	
	  	//Remarketing sub Reason 
	  	@FindBy(how=How.XPATH, using="(//td[text()='Remarketing Sub Reason']/following-sibling::td/div)[1]")
	  	public WebElement txtRemarketingsubReasonOpp;
	  	
		//***********************************Military and oppurtunity page objects****************************
		
		
		@FindBy(how=How.XPATH, using = ".//*[@id='Opportunity_body']/table/tbody/tr[2]/td[8]")
		public WebElement txtStatusOppertunities;
		
		//opportunity link 
		@FindBy(how=How.XPATH, using = "//label")
		public WebElement lnkOpportunity;
		//Convert Button 
		@FindBy(how=How.XPATH, using = ".//*[@id='topButtonRow']/input[4]")
		public WebElement BtnConvert;
		
		//Lead Converstion message 
		@FindBy(how=How.XPATH, using = ".//*[@id='j_id0:j_id2:j_id33:j_id34:j_id36']")
		public WebElement MsgConvert;
		
		//Converted Oppurtunity link 
		@FindBy(how=How.XPATH, using = ".//*[@id='j_id0:j_id3:j_id18']/div[2]/table/tbody/tr[1]/td/a/label")
		public WebElement lnkConvertedOppurtunity;
		
		//Student information form submission 
		
		@FindBy(how=How.XPATH, using = ".//table/tbody/tr/td[2]/div[7]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")
		public WebElement lnkStdntInfrmtn;
		
		//Switching to IFrame in Student Information Page 
		public String IframeSwitch = "066i0000004ef9y";
		
				
		//Get Sif Answer Button
		
		@FindBy(how=How.XPATH, using = "//body/form/div[1]/input")
		public WebElement BtnGetSifAnswer;
		
		//Get military Status under Student information Page (Get Sif Answer)
		@FindBy(how=How.XPATH, using = "//td[text()='Military Type']/following-sibling::td[1]")
		public WebElement TxtMilitaryStatus;
		
		//Verifying Email Adress under Student information Page (Get Sif Answer)
		@FindBy(how=How.XPATH, using = "//td[text()='Email Address']/following-sibling::td[1]")
		public WebElement TxtEmailSif;
		
		//oppurtunity//lead status 
		@FindBy(how=How.XPATH, using = "//td[text()='Stage']/following-sibling::td[1]/div")
		public WebElement txtOppurtunitystatus;
		
		//Oppurtunity//Current military status
		@FindBy(how=How.XPATH, using = "//td[text()='Current Military Status']/following-sibling::td[1]/div")
		public WebElement txtCurrentMilitarystatus;
		
		//military status under military information 
		@FindBy(how=How.XPATH, using = "//td[text()='Military Status']/following-sibling::td[1]/div")
		public WebElement txtMilitarystatusinformation;
		
		
		
		//*******************************************Developer Console in SRM Properties ***********************************
		
		//User Control arrow 
		@FindBy(how=How.XPATH, using = ".//*[@id='userNav-arrow']")
		public WebElement DrpdwnUserArrow;
				
		//Developer Console link
		@FindBy(how=How.XPATH, using = ".//*[@id='userNav-menuItems']/a[3]")
		public WebElement lnkDeveloperConsole;
		
		//Query Editor Tab
		@FindBy(how=How.XPATH, using = "//button/span[text()='Query Editor']")
		public WebElement tabQueryEditor;
		
		//Query Editor Text Field 
		@FindBy(how=How.XPATH, using = ".//*[@id='queryEditorText-inputEl']")
		public WebElement txtQueryEditorField;
		
		
		//Query Execute Tab 
		@FindBy(how=How.XPATH, using = ".//*[@id='queryExecuteButton-btnEl']")
		public WebElement TabQueryExecute;
		
		//Is_Military_formula__c text true 
		@FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/div[3]/div/table/tbody/tr[2]/td[1]/div")
		public WebElement TextTrueMilitaryFormula;
		
		//Military Status test field 
		@FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/div[3]/div/table/tbody/tr[2]/td[2]/div")
		public WebElement TextMilitarayStatusConsole;
		
		//syStudentID in Console 
		@FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/div[3]/div/table/tbody/tr[2]/td[3]/div")
		public WebElement TextsyStudentIDConsole;
		
		
		public boolean WaitTillElementValue(WebDriver driver, WebElement element)
		{
			boolean flag;
			flag = false;
			WebDriverWait objWaitDriver  = new WebDriverWait(driver, 10);
			for(int i =0;i<20;)
			{
				try		
				{
					if (element.isDisplayed())
					{i=21;
						
					}
					else
					{
						
						flag = true;
						i = 21;
						i++;
						objWaitDriver.until(ExpectedConditions.visibilityOf(element));	
						//cli srch
					}
								
				}catch(org.openqa.selenium.TimeoutException e)
				{
					
				}
				
			}
			return flag;		
		}
		
		//********************************************TCPA Functionality Page Objects  **** ********************************
		//Clicking on Searched Lead 
		@FindBy(how=How.XPATH, using = ".//*[@id='Lead_body']/table/tbody/tr[2]/th")
		public WebElement linkSearchedLead;
		
		// Ewc Text Field 
		@FindBy(how=How.XPATH, using = " //td[text()='EWC']/following-sibling::td[1]/div")
		public WebElement txtEwcField;
		
		//Do Not Email Text Field 
		@FindBy(how=How.XPATH, using = " //td[text()='Do Not Email']/following-sibling::td[1]/div")
		public WebElement txtDoNotEmail;
		
		
		//Edit Button
		@FindBy(how=How.XPATH, using = ".//*[@id='topButtonRow']/input[3]")
		public WebElement lblEditButton;
				
		//Ewc Drop Down Button to Edit 
		@FindBy(how=How.XPATH, using = "//label[text()='EWC']/parent::td/following-sibling::td/span")
		public WebElement DrpdnBtnEWC;
		
		//Ewc Drop Down List Option "No"
		 @FindBy(how=How.XPATH, using = ".//*[@id='00Ni000000Ea5ow']/option[3]")
		 public WebElement lstNoEWC;
		 
		//Ewc Drop Down List Option "Yes"
		 @FindBy(how=How.XPATH, using = ".//*[@id='00Ni000000Ea5ow']/option[2]")
		 public WebElement lstYesEWC;
		 
		 //Do Not Email Drop Down Button 
		 @FindBy(how=How.XPATH, using = "//label[text()='Do Not Email']/parent::td/following-sibling::td/span")
		 public WebElement DrpDnBttnDoNotEmail;
		 
		 //Do Not Email Drop Down Option "Yes"
		 @FindBy(how=How.XPATH, using = ".//*[@id='00Ni000000Ea5ov']/option[2]")
		 public WebElement lstDoNotEmailYes;
		 
		 //Do Not Email Drop Down Option "No"
		 @FindBy(how=How.XPATH, using = ".//*[@id='00Ni000000Ea5ov']/option[3]")
		 public WebElement lstDoNotEmailNo;
		 
		 //Save Button
		 @FindBy(how=How.XPATH, using = ".//*[@id='topButtonRow']/input[1]")
		 public WebElement TabSave;
		 
		 //Verifying Inquiry History  Record 1
		 @FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[9]/div[1]/div/div[2]/table/tbody/tr[2]/td[2]")
		 public WebElement lstInquiryHistory1;
		 
		 //Verifying Inquiry History  Record 2
		 @FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[9]/div[1]/div/div[2]/table/tbody/tr[3]/td[2]")
		 public WebElement lstInquiryHistory2;
		 
		 //Verify inquiry History record 3
		 @FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[9]/div[1]/div/div[2]/table/tbody/tr[4]/td[2]")
		 public WebElement lstInquiryHistory3;
		 
		//Verify inquiry History record 4
		 @FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[9]/div[1]/div/div[2]/table/tbody/tr[5]/td[2]")
		 public WebElement lstInquiryHistory4;
		 
		//Verify inquiry History record 5
		 @FindBy(how=How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[9]/div[1]/div/div[2]/table/tbody/tr[6]/td[2]")
		 public WebElement lstInquiryHistory5;
		 
		 
		 //Lead Status Text Field
		 @FindBy(how=How.XPATH, using = ".//*[@id='lea13']")
		 public WebElement txtInquiryLeadstatus;
		 
		 //Selecting Remarketing In Inquiry Lead Status 
		 @FindBy(how=How.XPATH, using = ".//*[@id='lea13']")
		 public WebElement lstLeadstatus;
		 
		 //Menu Extension + 
		 @FindBy(how=How.XPATH, using = ".//*[@id='AllTab_Tab']/a/img")
		 public WebElement ExtMenu;
		 
		 //All Inquiries Link
		 @FindBy(how=How.XPATH, using = "//td[contains(@class, 'dataCol leadBlock')]/a[text()='Inquiries']")
		 public WebElement lnkAllInquiry;
		 
		 //View DropDown List 
		 @FindBy(how=How.XPATH, using = ".//*[@id='fcf']")
		 public WebElement DrpDwnInquiryView;
		 
		 //Selecting my Inquiries from the Drop Down 
		 @FindBy(how=How.XPATH, using = ".//*[@id='fcf']")
		 public WebElement MyInquiryView; 
		 
		 //Go Button
		 @FindBy(how=How.XPATH, using = ".//*[@id='filter_element']/div/span/span[1]/input")
		 public WebElement BtnGo; 
		 
		 //Clicking on Home Button 
		 @FindBy(how=How.XPATH, using =".//*[@id='home_Tab']/a")
		 public WebElement BtnHomeButton;
	
		 
	}
	
	
	
	

