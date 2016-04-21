package uiMap_Orion3_SRM;

	//Import files
		import java.util.List;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindAll;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class SRM_Genesys_MarketingPageObjects {
			
			public SRM_Genesys_MarketingPageObjects(WebDriver driver) {
				
				//Initialize A SRMLeadFlowPageObjects.
				PageFactory.initElements(driver, this);
			}
			
			//Account name
			
			@FindBy(how=How.XPATH, using="//a[text()='iWD Test']")
			public WebElement lnkAccountName;
			
			
			
			//Marketing Channel fix
			
			@FindBy(how=How.XPATH, using=".//*[@id='lookupa16i0000002cs9300Ni000000EaSJk']")
			public WebElement txtMarketingChannelFix;
			
			//Edit button
			@FindBy(how=How.XPATH, using=".//*[@id='topButtonRow']/input[3]")
			public WebElement btnEdit;
			
			//Marketing Channel lookup
			
			@FindBy(how=How.XPATH, using=".//*[@id='CF00Ni000000EaSJk_lkwgt']/img")
			public WebElement txtmarketingChannelLookup;
		                                                

			//Lookup search
			@FindBy(how=How.ID, using="lksrch")
			public WebElement txtLookupSearch;
			
			//Go button
			
		     @FindBy(how=How.NAME, using="go")
		     public WebElement btnGo;
		     
		     //Email link
		     
		     @FindBy(how=How.XPATH, using=".//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")
		     public WebElement lnkEmailResultSRM;
		     
		     
		
			
			//Student id value
			
			@FindBy(how=How.ID, using="00Ni000000BVIyA_ileinner")
			public WebElement txtSystudentid;
			
              //Edit button SRM
			
			@FindBy(how=How.NAME, using="edit")
			public WebElement btnEditSRM;
			
			//Primary Phone number
			
			@FindBy(how=How.ID, using="00Ni000000Ea5pf")
			public WebElement txtPrimaryPhoneNumber;
			
			//Save button SRM
			
			@FindBy(how=How.NAME, using="save")
			public WebElement btnSaveSRM;
			
			//SRM All tab
			
			@FindBy(how=How.XPATH, using=".//*[@id='AllTab_Tab']/a/img")
			public WebElement lnkSRMAllTab;
			
	//Account name
			
			@FindBy(how=How.XPATH, using="//a[text()='Marketing Channels']")
			public WebElement lnkMarketingChannels;
			
         //SRM Go button
			
			@FindBy(how=How.NAME, using="go")
			public WebElement btnGoSRM;
			
			//Marketing channel GOK
			
			@FindBy(how=How.XPATH, using=".//*[@id='a16i0000002cs98_Name']/a/span")
			public WebElement lnkMarketingChannelGOK;
			
            //Marketing channel GOK text
			
			@FindBy(how=How.ID, using="Name_ileinner")
			public WebElement txtMarketingChannelGOK;
			
			
			//Marketing channel Aggregator
			
			@FindBy(how=How.XPATH, using=".//*[@id='a16i0000002cs93_Name']/a/span")
			public WebElement lnkMarketingChannelAggregator;
			
           //Marketing channel Aggregator text
			
			@FindBy(how=How.ID, using="Name_ileinner")
			public WebElement txtMarketingChannelAggregator;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//SyStudent ID
			//@FindBy(how=How.XPATH, using="//td[text()='SyStudentID']/following-sibling::td[1]/div")
			//public WebElement txtSyStudentID;
			
		
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		
		}
	
