package uiMap_Orion3_SRM;


	//Import files
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class VerifyOppertunities_ReassignedUserPageObjects {
			
	public VerifyOppertunities_ReassignedUserPageObjects(WebDriver driver) {
				
				
				PageFactory.initElements(driver, this);
			}
			
			//Set up Link
			
			@FindBy(how=How.ID, using="setupLink")
			public WebElement lnkSetup;
			
			//Workflow create
			@FindBy(how=How.ID, using= "DevTools_font")
			public WebElement lnkCreate;
			
			
			
			//Workflow Approval
			@FindBy(how=How.ID, using= "Workflow_font")
			public WebElement lnkWorkflowApproval;
			
			//Workflow rules link
			
			@FindBy(how=How.ID, using="WorkflowRules_font")
			public WebElement lnkWorkflowRules;
			
			//Continue Button
			
			@FindBy(how=How.NAME, using="save")
			public WebElement btnContinue;
			
			
			//Link G on Top
			
				@FindBy(how=How.XPATH, using= ".//*[@id='bodyCell']/div[4]/div/div/a[7]/span")
				public WebElement lnkG;
			
			
			//DeActivate Link
				
				@FindBy(how=How.XPATH, using= ".//*[@id='bodyCell']/div[5]/div/div[2]/table/tbody/tr[3]/td[1]/a[3]")
				public WebElement lnkDeactivateUser;
				
				//Back to Admission Console link
				
				@FindBy(how=How.XPATH, using= ".//*[@id='BackToServiceDesk_Tab']/a")
				public WebElement lnkBacktoAdmissionConsole;
				
				
				//Inquiry Status change
				
				@FindBy(how=How.XPATH, using= "//a[contains(text(),'[Change]')]")
				public WebElement lnkInquiryStatusChange;
				
				
				//Edit Button
				
				@FindBy(how=How.NAME, using= "edit")
				public WebElement lnkEditButton;
				
				//Select Queue
				
				@FindBy(how=How.ID, using= "newOwn_mlktp")
				public WebElement ddSelectUser;
				
				
				//Lookup link
				
				@FindBy(how=How.XPATH, using= ".//*[@id='newOwn_lkwgt']/img")
				public WebElement lnkLookup;
				
				//Core Inactive queue
				
				@FindBy(how=How.XPATH, using= ".//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")
				public WebElement lnkCoreInactiveQueue;
				
				//Owner save
				

				@FindBy(how=How.NAME, using= "save")
				public WebElement btnSaveOwner;
				
				
				
				
				//SEP URL link
				
			//@FindBy(how=How.XPATH, using= ".//*[@id='00Ni000000Ea5pn_ileinner']/a")
		   //public WebElement lnkSEPUrl;
			
			
			//SEP Url link after edit
			
			@FindBy(how=How.XPATH, using= "//*[@id='ep']/div[2]/div[21]/table/tbody/tr[4]/td[4]")
			   public WebElement lnkSEPUrl;
			
			@FindBy(how=How.NAME, using= "save")
			   public WebElement btnSave;
			
			//Logged in user DropDown link
			
			@FindBy(how=How.ID, using= "userNavLabel")
			   public WebElement lnkLoggedinUser;
			
			//Logout Button
			
			@FindBy(how=How.LINK_TEXT, using="Logout")
			   public WebElement lnkLogout;
			
			//User profile search link S
			
			@FindBy(how=How.XPATH, using= ".//*[@id='00Bi0000003yTTf_rolodex']/a[19]/span")
			   public WebElement lnkProfileS;
			
			
			//Student Success Link
			
			@FindBy(how=How.XPATH, using= "//span[contains(text(),'Student Success')]")
			   public WebElement lnkStudentSuccess;
			
			//Login Student Success User
			
			@FindBy(how=How.XPATH, using=".//*[@id='ResetForm']/div[2]/table/tbody/tr[2]/td[1]/a[2]")
			   public WebElement lnkLoginStudentSuccess;
			
			//Users Tab
			

			@FindBy(how=How.ID, using="ManageUsers_font")
			   public WebElement lnkUsers;
			
			
			
			//Link for Profile R
			
			@FindBy(how=How.XPATH, using=".//*[@id='bodyCell']/div[4]/div/div/a[18]/span")
			   public WebElement lnkProfileR;
			
			//More users link
			
			@FindBy(how=How.LINK_TEXT, using="more")
			   public WebElement lnkMoreUsers;
			
			//Rowe Rowan User
			
			@FindBy(how=How.XPATH, using="//a[contains(text(),'Rowe, Rowan')]")
			   public WebElement lnkRoweRowanUser;
			
			//Rowe Rowan Login Button
			
			@FindBy(how=How.XPATH, using=".//*[@id='topButtonRow']/input[4]")
			   public WebElement btnRoweRowanLogin;
			
			
			
			
			
			
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				
			
			
			
			}
			



