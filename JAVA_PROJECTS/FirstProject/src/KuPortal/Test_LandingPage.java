package KuPortal;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;



import org.testng.annotations.*;
import org.testng.Assert;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;




import org.openqa.selenium.WebDriver;


import commonfunctions.*;

@Listeners({KuPortal.MethodFailure.class})
public class Test_LandingPage {

	public WebDriver driver = null;	
	public BrowserManagement objBrowseMgr = null;
	public String sLandingPageHandleName = "Kaplan University | KU Campus - Login";
	public String Path_HREFFile = ".\\Resources\\UtilityFiles\\KuPortal_HREF_Data.properties";	
	
	Properties propKuPortalHREF = new Properties();
	
	@Parameters({"Browser"})
	@BeforeClass
	public void BeforeClass(String sBrowser)
	{
		
		try {
			objBrowseMgr = new BrowserManagement(sBrowser);
			driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowseMgr.capability);
			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
				
		driver.get(EnvironmentVariables.sUrl_StudentPortal);
	}
	
	@AfterClass
	public void AfterClassTestLandingPage()
	{
		if(driver != null)
		{
			driver.quit();
		}
	}
	
	@BeforeMethod
	public void BeforeMethod(Method method)
	{
		ReportExtn.MethodReportingLayout(method.getName());
	}
		
	 @Test(groups = {"Grp_Navigation"})
	 //LinkReference_LandingPage: Checks the outside reference for the class
 	  public void LinkReference_LandingPage(Method objMethod) {
						
		int iFailCount = 0, iRow = 0, iCol = 0;
		String sCellValue = "";
		String sWorkSheetTitle = "HREF_Details";
		
		
		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		 
		try {
			fileInputStream = new FileInputStream(".\\Resources\\UtilityFiles\\HREF_Details.xls");
		} catch (FileNotFoundException e1) {		
			e1.printStackTrace();
		}		
		try {
			workbook = new HSSFWorkbook(fileInputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}
		HSSFSheet worksheet = workbook.getSheet(sWorkSheetTitle);
		
		driver.get(EnvironmentVariables.sUrl_StudentPortal);		
		List<WebElement> linksLandingPage = UserExtension.FindAllLinks(driver);
		String sExpectedHRefValue=null;
		boolean bLinkFound =  false;
		int iRowCount =  ExcelReader.GetRowCount(worksheet);
		
		for(int k=0;k<iRowCount;k++)
		{
			
			sCellValue =  ExcelReader.ReadCellValue(worksheet, iRow, iCol);
			bLinkFound = false;
			for(int i=0;i<linksLandingPage.size();i++)
			{				
				if(sCellValue.equalsIgnoreCase(linksLandingPage.get(i).getText()))
				{
					bLinkFound = true;
					sExpectedHRefValue = ExcelReader.ReadCellValue(worksheet, iRow, 1);
					if(sExpectedHRefValue.equalsIgnoreCase(linksLandingPage.get(i).getAttribute("href")))
					{
						ReportExtn.Pass(objMethod, linksLandingPage.get(i).getText() + " link reference is correct");
					}
					else
					{
						ReportExtn.Fail(objMethod, sExpectedHRefValue, linksLandingPage.get(i).getAttribute("href"), " link reference is INCORRECT");
						iFailCount++;
					}
					if(bLinkFound)
					{
						break;
					}
					
				}
			}
			if(!bLinkFound)
			{
				ReportExtn.Fail(objMethod, sCellValue, null, " link not found on Landing Page.");
				iFailCount++;
			}
			iRow++;
		}		

		if(iFailCount>0)
		{
			Assert.assertEquals(iFailCount,0,"Failure Count is not 0. Please check the Reporter log for failed items");
		}
					 
	 }	  

	 @Test
	 public void AboutKaplanUniversity(){
		 driver.get(EnvironmentVariables.sUrl_StudentPortal);
		 
		 
	 }
}


