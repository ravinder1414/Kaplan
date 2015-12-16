package docCollection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

enum EnvironmentDetails{
	Stage("http://10.78.58.42","ttestbiztalk", "Apple123"), 
	Build("http://10.78.6.59", "HTEST1","Password01"),
	CIDev("http://10.78.101.37", "TTESTBIZTALK", "Apple123"),
	Dev("http://10.78.5.132","TTESTBIZTALK", "Apple123");

	String OEC_URL, OEC_UID, OEC_PWD;
	private EnvironmentDetails(String OEC_URL, String OEC_UID, String OEC_PWD) {
		this.OEC_URL = OEC_URL;
		this.OEC_UID = OEC_UID;
		this.OEC_PWD = OEC_PWD;
		
	}
	public String getOECURL(){return OEC_URL;}
	public String getOECUID(){return OEC_UID;}
	public String getOECPWD(){return OEC_PWD;}		
}

enum HubConfiguration{
	Hub("10.75.16.226", "4444");
	
	String HUB_IP, HUB_PORT;
	private HubConfiguration(String HUB_IP, String HUB_PORT){
		this.HUB_IP=HUB_IP;
		this.HUB_PORT=HUB_PORT;
	}
	public String getHubIP(){return HUB_IP;}
	public String getHubPort(){return HUB_PORT;}
	
}

public class LocalLibrary {
		
		RemoteWebDriver driver;
		
		private DesiredCapabilities setDesireCapabilities(String browsername) {
	        DesiredCapabilities capability = null;
	        switch (browsername) {
	            case "firefox":
	            	 FirefoxProfile profile = new FirefoxProfile();
	            	 profile.setAssumeUntrustedCertificateIssuer(false);
	                capability = DesiredCapabilities.firefox();
	                capability.setCapability(FirefoxDriver.PROFILE, profile);
	                //capability.setVersion("15.0.1");
	                break;
	            case "chrome":
	            	capability = DesiredCapabilities.chrome();
	            	capability.setCapability("ignore-certificate-errors", true);	            	
	                break;
	            case "internet explorer":
	            	//System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer_Win32_2.32.2\\IEDriverServer.exe");
	                capability = DesiredCapabilities.internetExplorer();
	                capability.setCapability("ignoreProtectedModeSettings", true);
	                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	                break;
	        }
	        if (capability != null) {
	            capability.setBrowserName(browsername);
	        }
	        return capability;
	    }
		
		public RemoteWebDriver getDriver(String browsername, String port, String hubPort) throws MalformedURLException, InterruptedException{
			DesiredCapabilities capability;
			capability=setDesireCapabilities(browsername);
			driver = new RemoteWebDriver(new URL("http://".concat(hubPort).concat(":").concat(port).concat("/wd/hub")), capability);
			return driver;
		}
		
	@Test
	public static void login(WebDriver driver, String UID, String PWD) throws InterruptedException{
		//Synchronization - function
		driver.findElement(By.id("ctl00_Login1_UserName")).sendKeys(UID);
		driver.findElement(By.id("ctl00_Login1_Password")).sendKeys(PWD);
		driver.findElement(By.id("ctl00_Login1_LoginButton")).click();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	}
	
	@Test
	public static void logOff(WebDriver driver) throws InterruptedException{
		//Click logout link
		try{
			driver.findElement(By.id("ctl00_ctl06_lnkLO")).click();
		    driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		}catch(Exception e){System.out.println("Logoff with error!!");}
	    
	}
	
	//Synchronization functions
	
	//Read Excel functions
	
	
}
