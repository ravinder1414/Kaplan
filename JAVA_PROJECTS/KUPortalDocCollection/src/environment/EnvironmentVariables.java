package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commonfunctions.ReportExtn;

public class EnvironmentVariables {
	
	//Application Environment Variables
		
		public static String sUrl_Kuportal = "";
		public static String sEnv = "";
		public static String sApp = "Kuportal";
		public static String sUserName = "";
		public static String sPassword = "";
		public static String sFile_Exceedsize = "";
		public static String sFile_Invalidtype = "";
		public static String sFile_Validfile="";

		//Execution Environment 
		//Grid Hub and Port
		public static String sHub=null;
		public static String sHubPort=null;	
		
		//Wait for Page\Element to Load
		public static int iTimeout=30;
		
		//Before Execution of the suite
		//Set the variables from TestNG.xml
		//Set the variables from properties file
		@Parameters({ "Environment", "sHub", "sHubPort"})
		@BeforeSuite
		public void BeforeSuite_Orion3(String Environment, @Optional("localhost") String Hub, @Optional("4444") String HubPort, ITestContext context)
		{
			//Load environment variable from properties file
			String sPath_EnvProperties="";
			FileInputStream objFileInputStream = null;
			Properties objProperties = new Properties();
			
			//Set file path as per environment
			if (Environment.equalsIgnoreCase("dev"))
			{
				sPath_EnvProperties = ".//Resources//EnvironmentProperties/DevEnvironment.properties";
			}
			else if (Environment.equalsIgnoreCase("stage"))
			{
				sPath_EnvProperties = ".//Resources//EnvironmentProperties/StageEnvironment.properties";			
			}
			else if (Environment.equalsIgnoreCase("lt"))
			{
				sPath_EnvProperties = ".//Resources//EnvironmentProperties/LtEnvironment.properties";			
			}
			else
			{
				sPath_EnvProperties = ".//Resources//EnvironmentProperties/TestEnvironment.properties";			
			}
			
			//Load the Environment file into File Input Stream.
			File objFileEnvironment = new File(sPath_EnvProperties);
			try
			{
				objFileInputStream = new FileInputStream(objFileEnvironment);
			}catch (FileNotFoundException ex)
			{
				ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
			}
			
			//Load the File Input Stream into the Properties file
			try
			{
				objProperties.load(objFileInputStream);
				
			} catch (IOException ex) {

				ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
			}
			
			//Set the variables from TestNG.xml
			sHub = Hub;
			sHubPort = HubPort;
			sEnv =  Environment;
			
			
			//Set the Environment Variables
			sUrl_Kuportal = (String)objProperties.getProperty("KuPortal_Url");
			iTimeout = Integer.parseInt(objProperties.getProperty("Timeout"));
			sUserName = (String)objProperties.getProperty("Username");
			sPassword = (String)objProperties.getProperty("Password");
			sFile_Exceedsize= (String)objProperties.getProperty("FilePath_Exceedsize");
			sFile_Invalidtype= (String)objProperties.getProperty("FilePath_InvalidType");
			sFile_Validfile = (String)objProperties.getProperty("FilePath_ValidFile");
		}
 

}
