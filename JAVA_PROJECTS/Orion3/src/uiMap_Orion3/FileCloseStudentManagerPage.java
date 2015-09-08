package uiMap_Orion3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


				public class FileCloseStudentManagerPage {
				public FileCloseStudentManagerPage(WebDriver driver) {
						
						//Initialize A AddALeadReferralPageObjects.
						PageFactory.initElements(driver, this);
					}

				public String sStudent_ManagerFileClose_WindowName="FileClose";
				public String sStudentManager_WindowName = "StuMgr";

				//Appointment Tab
				@FindBy(how=How.XPATH, using=".//*[@id='ctl00_PageBodyPlaceHolder_SMAccordion']/div[2]/div/ul/li[2]/a/span")
				public WebElement lnkAppointmentTab;
			    public String strAppointmentText = "Appointment";
			    
				    //FileClose Button
				    
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ctl01_ctl04_btnFileClose")
					public WebElement btnFileClose;
					
					//File close Icon on Admission Page
					
					  @FindBy(how=How.XPATH, using="//td[14]/a/img")
					    public WebElement lnkAdmissionPageFileClose;
				  
	                //Select Reason DropDown
				    
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ddlCRMParentReason")
					public WebElement ddSelectReason;
					
					//Select Sub Reason Actions
					
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_ddlChildActionResult")
					public WebElement ddSubReason;
					
					//Lead Contacted
					
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_cbContacted")
					public WebElement checkBoxLeadContacted;
					
					//Text Notes Required
					
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_txtNote")
					public WebElement txtNotesRequired;
					
					
					//Save Button
					
					@FindBy(how=How.ID, using="ctl00_PageBodyPlaceHolder_btnClose")
					public WebElement btnSave;
					
					//File close text verification
					
					
					@FindBy(how=How.XPATH, using="//td[11]")
					public WebElement txtFileCloseStatus;
					
					

				}

