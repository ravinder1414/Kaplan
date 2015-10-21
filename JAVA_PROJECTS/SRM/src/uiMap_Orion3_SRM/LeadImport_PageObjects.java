package uiMap_Orion3_SRM;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;


	public class LeadImport_PageObjects  {
		 
	public LeadImport_PageObjects(WebDriver driver) {
					
	//Initialize A SearchPageObjects.
	 PageFactory.initElements(driver, this);
			}

			public String sSearchMain_WindowName ="";
			
			
			//Student Information XML
			
				@FindBy(how=How.NAME, using="studentInformationXML")
				public WebElement txtStudentInformationXML;
			   
			   
			    //Button Invoke
			    
			    @FindBy(how=How.XPATH, using = ".//*[@id='content']/span/form/table/tbody/tr[3]/td[2]/input")
				public WebElement btnInvoke;
			    
			    //Lead Import value
			    
			    @FindBy(how=How.CSS, using = "string")
				public WebElement txtLeadImport;
			    
			    
			    
				
				
	}



