package UIMapKuPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LandingPage {


	public static String urlLandingPage = "Security//Login";	
	public static String linkForgotPassword = "Forgot Password?";
	public static String linkOutSchool = "Out of school and need to make a payment? ";
	public static String linkAllPrograms = "All Programs";
	public static String linkApply = "Apply";
	public static String linkAboutKU = "About Kaplan University";
	public static String linkTuitionFinancialAid = "Tuition and Financial Aid";
	public static String linkMission = "Mission";
	public static String linkPressRoom = "Press Room";
	public static String linkContactUs = "Contact Us";
	public static String linkJoinFaculty = "Join Our Faculty";
	public static String linkBusiness = "Business";
	public static String linkEduction = "Education";
	public static String linkHealthSci = "Health Sciences";
	public static String linkInformationTech = "Information Technology";
	public static String linkNursing = "Nursing";
	public static String linkProfessionalContinuingEdu = "Professional and Continuing Education";
	public static String linkSocialBehavioralSci = "Social and Behavioral Sciences";
	public static String linkTTY = "TTY";
	public static String linkRequestInfo = "Request Information";

	public static void LoginToKuPortal(WebDriver driver, String Username, String Password ) {
		  
		  driver.findElement(By.id(HomePage.txtboxUserName_ID)).sendKeys(Username);
		  driver.findElement(By.id(HomePage.txtboxPassword_ID)).sendKeys(Password);
		  driver.findElement(By.id(HomePage.buttonLogin_ID)).click();
		  commonfunctions.UserExtension.IsElementPresent(driver, By.linkText(HomePage.linkHOME), 60);
		  
	  }
	
}
