package KuPortal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class EnvironmentVariables {
	
	public static String sBrowser = null;
	
	public static String sUrl_StudentPortal=null;
	public static String sUsername_Faculty = null;
	public static String sPassword_Faculty = null;
	
	public static String sHub=null;
	public static String sHubPort=null;
	public DesiredCapabilities capability = null;
	
	
	
	@Parameters({ "Environment", "sHub", "sHubPort"})
	@BeforeSuite
	public void BeforeSuite(String Environment, @Optional("localhost") String Hub, @Optional("4444") String HubPort, ITestContext context)
	{ 
		String sPathEnvironmentfile = null;
		FileInputStream objFileInputStream = null;
		Properties propEnvironment = new Properties();
		
		if(Environment.equalsIgnoreCase("test"))
		{
			sPathEnvironmentfile = ".\\Resources\\UtilityFiles\\KUPortal_TEST_EnvVariable.properties";
		}
		
		File fileEnvironment =  new File(sPathEnvironmentfile);
		try {
				objFileInputStream = new FileInputStream(fileEnvironment);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		try {
			
			propEnvironment.load(objFileInputStream);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		//Read Variables
		sUrl_StudentPortal = propEnvironment.getProperty("sUrl_StudentPortal");
		sUsername_Faculty = propEnvironment.getProperty("sUsername_Faculty");
		sPassword_Faculty = propEnvironment.getProperty("sPassword_Faculty");
		
		//Retry
		for (ITestNGMethod method : context.getAllTestMethods()) {
	         method.setRetryAnalyzer(new RetryFailedMethods());
	         Reporter.log(method.getMethodName());
	     }
		
		//Hub
		sHub = Hub;
		sHubPort = HubPort;		
				
		
	}
		
}
