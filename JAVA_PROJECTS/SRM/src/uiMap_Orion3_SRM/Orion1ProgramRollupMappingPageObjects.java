package uiMap_Orion3_SRM;


	//Import files
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

	public class Orion1ProgramRollupMappingPageObjects {
			
			public Orion1ProgramRollupMappingPageObjects(WebDriver driver) {
				
				//Initialize a Orion1ProgramRollupMappingPageObjects.
				PageFactory.initElements(driver, this);
			}
			
			
			
			//Objects for SRM Navigation
			
			
			
			
			// Program Maintenance Home button
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl2_TopNavRow']/td[13]")
			public WebElement programMaintenancehomepage;
			public String sProgramMaintenanceText = "Program Maintenance";
			
			
			//Verify Use Name Label present
			@FindBy(how=How.XPATH, using="//*[@id='_ctl2_UserNameLabel']")
			public WebElement VerifyUserNameLabelPresent;
			
			
			// Program Rollup Maintenance button
			@FindBy(how=How.XPATH, using="//a[contains(text(),'Program Rollup Maintenance')]")
			public WebElement programRollupMaintenance;
			
			
			
			//Program Rollup button
			@FindBy(how=How.XPATH, using="//*[@id='ProgramRollupNavTab1_TabRow']/td[2]/strong/a")
			public WebElement programRollupButton;
			
			
			
			//Program Name to be Selected
			@FindBy(how=How.XPATH, using="//span/a")
			public WebElement programNametobeSelected;
			
			
			//Program Emphasis to be Selected
			@FindBy(how=How.XPATH, using="//td[3]/span")
			public WebElement programEmphasis;
			
			
			
			
			
			
			
			//Objects to Verify Operations on Mapping Window
			
			
			
			// Verify Program Mapping tab is present
			@FindBy(how=How.XPATH, using="//a[contains(text(),'Progam Mapping')]")
			public WebElement verifyProgramMappingTabPresent;
			
			
			// Program Mapping
			@FindBy(how=How.XPATH, using="//a[contains(text(),'Progam Mapping')]")
			public WebElement programMapping;
			
			
			// To Select the top value of Program version	
			@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_lbCVueAdProgramVersion']/option[1]")
			public WebElement firstSelectedValueOfProgramVersion;
			
			
			// Input box of Program version	
			@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_tbCVueAdProgramVersion']")
			public WebElement InputBoxofProgramVersion;
			public String partialTextNotPresentInStringText="Partial Text is not present in the selected String";
			
			// Program Version values of Box one	
			@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_lbCVueAdProgramVersion']")
			public WebElement programVersionValuesBoxOne;
			public String programVersionValuesBoxOneText="Program Version does not contain the partial text for Box One";
			public String programVersionValuesBoxOneAfterRemovalText="Box One does not contain selected values After Removal";
			
			// Program Version values of Box Two	
			@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_lbAddedCVueAdProgramVersion']")
			public WebElement programVersionValuesBoxTwo;
			public String programVersionValuesBoxTwoText="Box 2 does not contain selected values";
			
			//Add Button Button
			@FindBy(how=How.XPATH, using= ".//*[@id='_ctl1_btnAdd']")
			public WebElement addButton;
			
			
			//Remove Button Button
			@FindBy(how=How.XPATH, using= ".//*[@id='_ctl1_btnRemove']")
			public WebElement removeButton;
			
			
			//State Code Drop Down Button
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_btnAssignState']")
			public WebElement stateCodeDropDownButton;
			
			
			// Search Program Version Input Box
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_tbCVueAdProgramVersion']")
			public WebElement searchProgramVersionInputBox;
			
			
			
			// Add Program Version Button
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_btnAdd']")
			public WebElement addProgramVersionButton;
			
			
			
			// Remove Program Version Button
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_btnRemove']")
			public WebElement removeProgramVersionButton;
			
			
			//Assign State Button
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_btnAssignState']")
			public WebElement assignStateButton;
			public String assignStateText="Assign State Button is not Enable";
			
			//Assign State drop down Button
			
			@FindBy(how=How.XPATH, using= ".//*[@id='_ctl1_ddlStates']")
			public WebElement assignStateDropDownButton;
			
			
			// Save Mappings Button
			
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl1_btnSave']")
			public WebElement saveMappingsButton;
			
			
			
			
			
			
			
			
			// Objects for Product Search in Sales force
			
			
			
			// Menu Bar Extension Plus sign
			
			@FindBy(how=How.XPATH, using= "//img[@alt='All Tabs']")
			public WebElement menuBarExtensionPlusSign;
			
			
			
			// Products Link
			
			@FindBy(how=How.XPATH, using= "//a[contains(text(),'Products')]")
			public WebElement productsLink;
			
			
			
			// Products Input Box
			
			@FindBy(how=How.XPATH, using= "//*[@id='srch_Input']")
			public WebElement productsInputBox;
						
			
			// Find Products Button
			
			@FindBy(how=How.XPATH, using= "//input[@name='go']")
			public WebElement findProductsButton;
			
			
			
			// Product Name To Be Clicked
			
			@FindBy(how=How.XPATH, using= "//*[@id='Product2_body']/table/tbody/tr[2]/th/a")
			public WebElement productNameToBeClicked;
			
			
			// More Filter Link
			
			@FindBy(how=How.XPATH, using= "//a[contains(text(),'More filters >>')]")
			public WebElement moreFilterLink;
			
			
			// First Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2col0']") 
			public WebElement firstFieldFirstRow;
			public String valueToEnterForFirstFieldFirstRow="Emphasis";
			
			// Second Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2oper0']")
			public WebElement secondFieldFirstRow;
			public String valueToEnterForSecondFieldFirstRow="equals";
			
			// Third Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2fval0']")
			public WebElement thirdFieldFirstRow;
			
			
			// First Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2col1']") 
			public WebElement firstFieldSecondRow;
			public String valueToEnterForFirstFieldSecondRow="Product Name";
			
			// Second Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2oper1']")
			public WebElement secondFieldSecondRow;
			public String valueToEnterForSecondFieldSecondRow="equals";
			
			// Third Field of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2fval1']")
			public WebElement thirdFieldSecondRow;
			
			// Search button of More Filter link
			@FindBy(how=How.XPATH, using= ".//*[@id='save_filter_Product2']")
			public WebElement searchButton;
			
			
			// Click Program Name on Salesforce page
			@FindBy(how=How.XPATH, using= ".//*[@id='Product2_body']/table/tbody/tr[2]/th/a")
			public WebElement programNameOnSalesforcePage;
			
			
			// For Program Roll Up Name On SalesForce Page
			@FindBy(how=How.XPATH, using= ".//a[text()='AR - XX! BS in Business Administration - General Business']/parent::td/preceding-sibling::th/a")
			public WebElement programRollUpNameOnSalesForcePage;
			
			// Program Version ID for Roll Up
			@FindBy(how=How.XPATH, using= "//tr[2]/td[2]/div/a")
			public WebElement programVersionIDForRollUp;
			public String programVersionIDForRollUpText="Program Version Id is not correct on Roll up page";
			
			
			
			// State Code for Roll Up
			
			@FindBy(how=How.XPATH, using= "//tr[3]/td[2]/div")
			public WebElement stateCodeForRollUp;
			public String stateCodeForRollUpText="State Code is not correct on Roll up page";
							
			
			
			// Current date for Roll Up
			
			@FindBy(how=How.XPATH, using= "//td[4]/div")
			public WebElement dateFromRollUp;
			public String dateFromRollUpText="Current date is not dsiplayed on Roll up Page";
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
			



