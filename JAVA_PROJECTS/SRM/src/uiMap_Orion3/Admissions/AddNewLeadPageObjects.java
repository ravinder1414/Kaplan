package uiMap_Orion3.Admissions;
//Import files1
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

	@FindBy(how=How.ID, using = "ctl00_PageBodyPlaceHolder_SifControl_Military_0")
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
		
		@FindBy(how=How.XPATH, using = ".//*[@id='Lead_body']/table/tbody/tr[2]/td[6]/a")
		public WebElement txtEmailAddressVerification;
		
		@FindBy(how=How.XPATH, using = "//h3/span[contains(text(), 'Inquiries')]")
		public WebElement headingInquiry;
		
		@FindBy(how=How.XPATH, using = "//h3/span[contains(text(), 'Opportunities')]")
		public WebElement headingOppertunities;
		
		
		@FindBy(how=How.XPATH, using = ".//*[@id='Opportunity_body']/table/tbody/tr[2]/td[8]")
		public WebElement txtStatusOppertunities;
		
		
		
		
		
		
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
		
		
		
		
		
		
	}
	
	
	
	

