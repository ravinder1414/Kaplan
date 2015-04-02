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
  
  //New Lead Window name
  public String sNewLeadWinName="script";
   	
 }