package uiMap_Orion3_SRM;


	//Import files
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class SEPDyanamicURL_Pageobject {
			
			public SEPDyanamicURL_Pageobject(WebDriver driver) {
				
				
				PageFactory.initElements(driver, this);
			}
			
			//Set up Link
			
			@FindBy(how=How.ID, using="setupLink")
			public WebElement lnkSetup;
			
			//Manage Users
			@FindBy(how=How.ID, using= "Users_font")
			public WebElement lnkManageUsers;
			
			//Profiles
			
			@FindBy(how=How.ID, using="EnhancedProfiles_font")
			public WebElement lnkProfiles;
			
			//Admission console link
			
			@FindBy(how=How.XPATH, using=".//*[@id='00ei0000001FNpq_ProfileName']/a/span")
			public WebElement lnkAdmissionConsole;
			
			
			//View Users Link
			
				@FindBy(how=How.XPATH, using= ".//*[@id='topButtonRow']/input[6]")
				public WebElement lnkViewUsers;
			
			
			//Admission Advisor Login users
				
				@FindBy(how=How.XPATH, using= ".//*[@id='ResetForm']/div[2]/table/tbody/tr[2]/td[1]/a[2]")
				public WebElement lnkAdmissionAdvisorLoginUser;
				
				//Back to Admission Console link
				
				@FindBy(how=How.XPATH, using= ".//*[@id='BackToServiceDesk_Tab']/a")
				public WebElement lnkBacktoAdmissionConsole;
				
				
				//Edit Button
				
				@FindBy(how=How.NAME, using= "edit")
				public WebElement lnkEditButton;
				
				
				
				//sep url link
				
				//@FindBy(how=How.XPATH, using= ".//*[@id='ep']/div[2]/div[21]/table/tbody/tr[4]/td[4]")
				//public WebElement lnkSEPUrl;
				
				
				
				
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
			



