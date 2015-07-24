package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdmissionsPage {
	//constructor to intialize page elements
  public AdmissionsPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Skills/Cut Off Manager Link
  @FindBy(how=How.LINK_TEXT, using="Skills/Cut Off Manager")
  public WebElement lnkSkillsCutoffMgr;
   
//Script Manager link
@FindBy(how=How.LINK_TEXT, using="Script Manager")
public WebElement lnkScriptMgr;
 
//Admin link
@FindBy(how=How.LINK_TEXT, using="Admin")
public WebElement lnkadmin;

//Operations link
@FindBy(how=How.LINK_TEXT, using="Operations")
public WebElement lnkOperations;

//Admissions Manager link
@FindBy(how=How.LINK_TEXT, using="Admissions Manager")
public WebElement lnkAdmissionsManager;

//Appraisals link
@FindBy(how=How.LINK_TEXT, using="Appraisals")
public WebElement lnkAppraisals;

//Bonus Tracker link
@FindBy(how=How.LINK_TEXT, using="Bonus Tracker")
public WebElement lnkBonusTracker;

//Enrollment Exceptions link
@FindBy(how=How.LINK_TEXT, using="Enrollment Exceptions")
public WebElement lnkEnrollmentExceptions;

//Settings link
@FindBy(how=How.LINK_TEXT, using="Settings")
public WebElement lnkSettings;
 }