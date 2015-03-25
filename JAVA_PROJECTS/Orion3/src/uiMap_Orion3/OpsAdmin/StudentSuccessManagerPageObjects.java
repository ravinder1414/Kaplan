package uiMap_Orion3.OpsAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudentSuccessManagerPageObjects {
	public StudentSuccessManagerPageObjects(WebDriver driver) {
		//Initialize A PageObjects.
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(how=How.ID_OR_NAME,using="nameplateLeft")
	public WebElement lblNamePlateLeft;
	public String strNamePlateLeft = "KHE ORION 3 > Ops Admin > Student Success Manager";
	
	@FindBy(how=How.CSS, using=".tabstrip > li:nth-of-type(1) > a")
	public WebElement lnkPayoutDates;
	
	@FindBy(how=How.CSS, using=".tabstrip > li:nth-of-type(2) > a")
	public WebElement lnkBonusCredential;

	@FindBy(how=How.CSS, using=".tabstrip > li:nth-of-type(3) > a")
	public WebElement lnkBonusEligibility;

	@FindBy(how=How.CSS, using=".tabstrip > li:nth-of-type(4) > a")
	public WebElement lnkManageBonus;
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_SearchButton")
	public WebElement btnSearchPayoutDates;

	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_SearchButton")
	public WebElement btnSearchBonusCredentials;
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_SearchButton")
	public WebElement btnSearchBonusAdjustment;
	
	@FindBy(how=How.ID, using="ctl00_ctl00_PageBodyPlaceHolder_PageBodyPlaceHolder_UpdatePanelBonusEligibility")
	public WebElement tblBonusEligibility;
	
	
}
