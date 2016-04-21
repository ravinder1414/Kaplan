package uiMap_Orion3_SRM;



	//Import files
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class SRM_Genesys_ValidateMilitary_PageObjects {
			
			public SRM_Genesys_ValidateMilitary_PageObjects(WebDriver driver) {
				
				
				PageFactory.initElements(driver, this);
			}
			
			//Student Information Form Submissions
			
			@FindBy(how=How.XPATH, using="//span[text()='Student Information Form Submissions']")
			public WebElement lnkStudentInformationSubmission;
			
			//Student Information users
			
			@FindBy(how=How.XPATH, using= "//table/tbody/tr[2]/th/a")
			public WebElement lnkStudentInformationUsers;
			
			//Text Systudent id
			
			@FindBy(how=How.XPATH, using= ".//*[@id='00Ni000000Ea5uD_ileinner']")
			public WebElement txtSyStudentid;
			
			
			
			//Get SIF Answers link
			
			@FindBy(how=How.XPATH, using=".//*[@id='j_id0:j_id2:sifbutton']")
			public WebElement lnkSIFAnswers;
		
			
			//Military Type link
			
			@FindBy(how=How.ID, using="j_id0:j_id2:j_id34:j_id35:8:j_id39")
			public WebElement txtMilitaryTypeLink;
			
			//Military Status DropDown value
			
			@FindBy(how=How.ID, using="00Ni000000BVIy9")
			public WebElement ddMilitaryStatus;
			
			
			
			
            
			
			
			
			
			
			
			
			
			
			
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
			



