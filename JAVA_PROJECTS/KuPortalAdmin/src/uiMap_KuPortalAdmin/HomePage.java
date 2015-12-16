package uiMap_KuPortalAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
  //Initialize Homepage elemnets
  public HomePage(WebDriver driver) {
	  PageFactory.initElements(driver, this);
  }
   
  //HomePage heading
  @FindBy(how=How.CSS, using="h1")
  public WebElement lblHomePageHeading;
  public String sHomePageHeading= "Welcome to the Administration Center" ;
  
  //Logout link
  @FindBy(how=How.XPATH, using= "//span[contains(@id,'Label1')][text()='Logout']")
  public WebElement lnkLogout;
  
  //CMS Link
  @FindBy(how=How.LINK_TEXT, using="CMS")
  public WebElement lnkCMS;
  
  
  //Portal Users Link
  @FindBy(how=How.XPATH, using= "//*[@id='main_nav']/ul/li[3]")
  public WebElement lnkPortalusers;
  public String strlnkPortalusers="Portal Users";
  
  //Provisioning Exceptions Link
  @FindBy(how=How.LINK_TEXT, using="Provisioning Exceptions")
  public WebElement lnkProvisioningExceptions ;
  
  //Search Existing User Link
  @FindBy(how=How.LINK_TEXT, using="Search Existing Users")
  public WebElement lnkSearchExistingUsers ;
  
  //New Student Mapping
  @FindBy(how=How.LINK_TEXT, using="New Student Mapping")
  public WebElement lnkNewStudentMapping ;
  
//New Instructor Mapping
  @FindBy(how=How.LINK_TEXT, using="New Instructor Mapping")
  public WebElement lnkNewInstructorMapping ;
  
//New Administrator Mapping
  @FindBy(how=How.LINK_TEXT, using="New Administrator Mapping")
  public WebElement lnkNewAdministratorMapping ;
  
  //Portal Preview
  @FindBy(how=How.LINK_TEXT, using="Portal Preview")
  public WebElement lnkPortalPreview ;
  
  //Portal content Link
  @FindBy(how=How.XPATH, using= "//*[@id='main_nav']/ul/li[2]")
  public WebElement lnkPortalcontent;
  public String strlnkPortalcontent="Portal Content";

  //eCollege Resource Center
  @FindBy(how=How.LINK_TEXT, using="eCollege Resource Center")
  public WebElement lnkeCollegeResourceCenter;

  //Events
  @FindBy(how=How.LINK_TEXT, using="Events")
  public WebElement lnkEvents;
  
  //Presentation Manager
  @FindBy(how=How.LINK_TEXT, using="Presentation Manager")
  public WebElement lnkPresentationManager;
  
  //Student Status Blocking
  @FindBy(how=How.LINK_TEXT, using="Student Status Blocking")
  public WebElement lnkStudentStatusBlocking;
  
  //Inquiry View
  @FindBy(how=How.LINK_TEXT, using="Inquiry View")
  public WebElement lnkInquiryView;

  //SME Directory
  @FindBy(how=How.LINK_TEXT, using="SME Directory")
  public WebElement lnkSMEDirectory;
  
//Inquiry Archive
  @FindBy(how=How.LINK_TEXT, using="Inquiry Archive")
  public WebElement lnkInquiryArchive;
  
 //Roles and Permissions
  @FindBy(how=How.XPATH, using="//*[@id='main_nav']/ul/li[4]")
  public WebElement lnkRolesandPermissions;
  
  //Create Roles
  @FindBy(how=How.LINK_TEXT, using="Create Roles")
  public WebElement lnkCreateRoles;
  
  //View Roles and Permissions
  @FindBy(how=How.LINK_TEXT, using="View Roles and Permissions")
  public WebElement lnkViewRolesandPermissions;
  
  //Self Registration
  @FindBy(how=How.XPATH, using="//*[@id='main_nav']/ul/li[5]")
  public WebElement lnkSelfRegistration;

  //User Configuration
  @FindBy(how=How.LINK_TEXT, using="User Configuration")
  public WebElement lnkUserConfiguration;

  //Course Catalogue Configuration
  @FindBy(how=How.LINK_TEXT, using="Course Catalogue Configuration")
  public WebElement lnkCourseCatalogue;
  
  //Military Course Description Letter Cost Per Credit
  @FindBy(how=How.LINK_TEXT, using="Military Course Description Letter Cost Per Credit")
  public WebElement lnkMilitaryCDL_CPC;

  //Courses & Attendance
  @FindBy(how=How.XPATH, using="//*[@id='main_nav']/ul/li[6]")
  public WebElement lnkCourses_Attendance;

  //KU Attendance
  @FindBy(how=How.LINK_TEXT, using="KU Attendance")
  public WebElement lnkKUAttendance;  

  //KU Attendance Posting
  @FindBy(how=How.LINK_TEXT, using="KU Attendance Posting")
  public WebElement lnkKUAttendancePosting;  
  
  //Miscellaneous
  @FindBy(how=How.XPATH, using="//*[@id='main_nav']/ul/li[7]")
  public WebElement lnkMiscellaneous;
  
  //Online Payments
  @FindBy(how=How.LINK_TEXT, using="Online Payments")
  public WebElement lnkOnlinePayments;
   
  //Live Help
  @FindBy(how=How.XPATH, using="//*[@id='main_nav']/ul/li[8]")
  public WebElement lnkLiveHelp;  
 
  //Pod Heading Roles and Permissions
  @FindBy(how=How.XPATH, using="//div[@class='pod']/h2[contains(text(),'Roles and Permissions')]")
  public WebElement lblPodRolesAndPermissions;  
 
  //Pod Heading Miscellaneous
  @FindBy(how=How.XPATH, using="//div[@class='pod']/h2[contains(text(),'Miscellaneous')]")
  public WebElement lblPodMiscellaneous;  
 
  //Pod Heading Self Registration Config 
  @FindBy(how=How.XPATH, using="//div[@class='pod']/h2[contains(text(),'Self Registration Config')]")
  public WebElement lblPodSelfRegistrationConfig ;  
 
  //Pod Heading Roles and Permissions
  @FindBy(how=How.XPATH, using="//div[@class='pod']/h2[contains(text(),'Courses & Attendance')]")
  public WebElement lblPodCourses_Attendance;  
   
  //Pod Heading Portal Content
  @FindBy(how=How.XPATH, using="//div[@class='podMasterContainer']/div[1]/div[1]/div[1]/h2")
  public WebElement lblPodPortalContent;  
   
  //Pod Heading Roles and Permissions
  @FindBy(how=How.XPATH, using="//div[@class='pod']/h2[contains(text(),'Roles and Permissions')]")
  public WebElement lblPodPortalUsers;  
   
  
  
  
  
}