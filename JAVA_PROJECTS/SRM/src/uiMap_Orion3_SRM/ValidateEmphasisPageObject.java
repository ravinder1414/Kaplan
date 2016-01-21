package uiMap_Orion3_SRM;


	//Import files
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class ValidateEmphasisPageObject {
			
			public ValidateEmphasisPageObject(WebDriver driver) {
				
				//Initialize A AddInfoCallLeadPageObjects.
				PageFactory.initElements(driver, this);
			}
			
			//Program Maintenance Link
			
			@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_ApplicationsNavRow']")
			public WebElement lnkProgramMaintenance;
			
			//Link Program RollUp Maintenance
			
			@FindBy(how=How.LINK_TEXT, using= "Program Rollup Maintenance")
			public WebElement lnkProgramRollMaintenance;
			
           //Link Program Emphasis
			
			@FindBy(how=How.LINK_TEXT, using= "Program Emphasis")
			public WebElement lnkProgramEmphasis;
			
			
	       //Add New Item button
			  @FindBy(how=How.ID, using= "btnNewItem")
			  public WebElement lnkAddNewItem;
			  
			  
				//Emphasis Text Area box
				  @FindBy(how=How.ID, using= "dgProgramEmphasis__ctl3_txtEmphasis_Edit")
				  public WebElement txtEmphasisTextArea;
				  
				  
				  //Active checkbox
				  @FindBy(how=How.ID, using= "dgProgramEmphasis__ctl3_cbxActive_Edit")
				  public WebElement txtActiveCheckBox;
				  
				  //Update Button
				  
				  @FindBy(how=How.LINK_TEXT, using= "Update  |")
					public WebElement btnUpdate;
				  
				  //Program Roll up
				  @FindBy(how=How.LINK_TEXT, using= "Program Rollup")
					public WebElement lnkProgramRollup;
				  
				  //Program Group
				  
				  @FindBy(how=How.ID, using= "dgProgramRollup__ctl3_ddlProgramGroup_Edit")
					public WebElement ddProgramGroup;
				  
				  //Program Description DropDown
				  
				  @FindBy(how=How.ID, using= "dgProgramRollup__ctl3_txtProgramDescription_Edit")
					public WebElement txtProgramDescription;
				  
                  //Program Emphasis DropDown
				  
				  @FindBy(how=How.ID, using= "dgProgramRollup__ctl3_ddlProgramEmphasis_Edit")
					public WebElement ddProgramEmphasis;
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
			  
			  
			  
			
			
			
			
			
			
			
			
			
			//Add Lead/Referral heading
			  @FindBy(how=How.XPATH, using= "//td[contains(text(), 'Add New Lead/Referral')]")
			  public WebElement hdAddLead;
			  
			//Referral Information label
			  @FindBy(how=How.XPATH, using= "//strong[contains(text(),'Referral')]")
			  public WebElement lblRefInfo;
			    
			//Student providing the referral  label
			  @FindBy(how=How.XPATH, using= "//strong[contains(text(),'Student providing the referral')]")
			  public WebElement lblStuProRef;
			  
			
			

			  //Email text box
			  @FindBy(how=How.ID, using= "tbREmail")
			  public WebElement tbEmail;
			  
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//Channel Group Internet/Online Search Listing Text
			@FindBy(how=How.XPATH, using= ".//*[@id='j_id0:addaleadid:leadblock:channelTable:0:j_id72']/input")
			public WebElement rbnInternet;
			
			//Radio Button Live Chat
			
			@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id33:2")
			public WebElement rbnLiveChat;
			
			//Radio Button Warm Transfer
			
			@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id33:3")
			public WebElement rbnWarmTransfer;
			
			
			//Promotional DropDown Text
				@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:prmotionid")
				public WebElement ddPromotional;
			
			
			
				
				//Program of Interest DropDown
				
				@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:progid")
				public WebElement ddProgramofInterest;
				
				
			
			//First Name Text
			@FindBy(how=How.XPATH, using= "//span[3]/span/table/tbody/tr[1]/td[3]/input")
			public WebElement txtFirstName;
			
			//Last Name Text
			@FindBy(how=How.XPATH, using= "//span[3]/span/table/tbody/tr[2]/td[3]/input")
			public WebElement txtLastName;
			
			//Email Address Text Box
			@FindBy(how=How.XPATH, using="//span[3]/span/table/tbody/tr[3]/td[3]/input")
			public WebElement txtEmailAddress;
			
			//Day Time Phone Number Text box
			@FindBy(how=How.XPATH, using= "//span[3]/span/table/tbody/tr[5]/td[3]/input")
			public WebElement txtDayTimePhoneNo;
			
			
			//City Text box
			@FindBy(how=How.XPATH, using= "//span[3]/span/table/tbody/tr[8]/td[3]/input")
			public WebElement txtCity;

			
			//ZIP Code Text Box
			@FindBy(how=How.XPATH, using= "//span[3]/span/table/tbody/tr[10]/td[3]/input")
			public WebElement txtZipCode;
			
			
			//TCPA Disclosure Yes Radio Button
			@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id120:13:j_id126:0")
			public WebElement rbtnTCPA_Disclosure_Yes;
			
			//TCPA Disclosure NO Radio Button
			@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id120:13:j_id126:1")
			public WebElement rbtnTCPA_Disclosure_No;
			

			//Spouse Military Radio Button Yes
					@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id120:11:j_id126:0")
					public WebElement rbtnSpouse_Yes;
		
			
			//Spouse Military Radio Button No
					@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id120:11:j_id126:1")
					public WebElement rbtnSpouse_No;
		
			
			//Highest Level of Education
			
					@FindBy(how=How.XPATH, using="//tr[15]/td[3]/select")
					public WebElement ddHighestLevelEducation;
			
			@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:addALeadButtonId")
			public WebElement txtAddAnInquiry;
		
			
			@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:successmsgid")
			public WebElement txtCreatedLeadSuccess;
			
			//Military Type Drop Down
			
			@FindBy(how=How.NAME, using="j_id0:addaleadid:leadblock:j_id120:12:j_id124")
			public WebElement dropDownMilitaryType;
			
			
			
			
			}
			
