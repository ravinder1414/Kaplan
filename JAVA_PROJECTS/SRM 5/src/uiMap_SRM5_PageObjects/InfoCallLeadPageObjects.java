package uiMap_SRM5_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InfoCallLeadPageObjects {
		
		public InfoCallLeadPageObjects(WebDriver driver) {
			
			//Initialize A AddInfoCallLeadPageObjects.
			PageFactory.initElements(driver, this);
		}
		
		//Radio Button InfoCall
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id33:0")
		public WebElement rbnInfoCall;
		
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
		
		
		//Area of Study
			
			@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:asid")
			public WebElement ddAreaofStudy;
			
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
		@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id120:12:j_id126:0")
		public WebElement rbtnTCPA_Disclosure_Yes;
		
		//TCPA Disclosure NO Radio Button
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id120:12:j_id126:1")
		public WebElement rbtnTCPA_Disclosure_No;
		

		//Spouse Military Radio Button Yes
				@FindBy(how=How.ID, using= "j_id0:addaleadid:leadblock:j_id120:11:j_id126:0")
				public WebElement rbtnSpouse_Yes;
	
		
		//Spouse Military Radio Button No
				@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:j_id120:11:j_id126:1")
				public WebElement rbtnSpouse_No;
		
		
		//Highest Level of Education
		
		@FindBy(how=How.XPATH, using="//tr[14]/td[3]/select")
		public WebElement ddHighestLevelEducation;
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:addALeadButtonId")
		public WebElement txtAddAnInquiry;
	
		
		@FindBy(how=How.ID, using="j_id0:addaleadid:leadblock:successmsgid")
		public WebElement txtCreatedLeadSuccess;
		
		
		}
		
