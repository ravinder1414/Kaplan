package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdmissionsManagerPage {
	//constructor to intialize page elements
  public AdmissionsManagerPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Add New Lead button
  @FindBy(how=How.XPATH , using=".//*[@id='tabnavi_TabRow']/td[18]/a/img")
  public WebElement btnAddNewLead;
  
//Follow Up link
  @FindBy(how=How.ID , using= "Follow Up")
  public WebElement lnkFollowUp;
  
//Leads link
  @FindBy(how=How.ID , using= "Leads")
  public WebElement lnkLeads;
  
//Interviews link
  @FindBy(how=How.ID , using= "Interviews")
  public WebElement lnkInterviews;
  
//Funding link
  @FindBy(how=How.ID , using= "Funding")
  public WebElement lnkFunding;
  
//Pending Enrollments link
  @FindBy(how=How.ID , using= "Pending Enrollments")
  public WebElement lnkPendingEnrollments;
  
//Start Manager link
  @FindBy(how=How.ID , using= "Start Manager")
  public WebElement lnkStartManager;
  
//Current link
  @FindBy(how=How.ID , using= "Current")
  public WebElement lnkCurrent;
  
//Search link
  @FindBy(how=How.ID , using= "Search")
  public WebElement lnkSearch;
 }