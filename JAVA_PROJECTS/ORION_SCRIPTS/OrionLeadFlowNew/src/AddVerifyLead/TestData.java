package AddVerifyLead;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import packGlobalFunctions.GenericFunctions;
import packGlobalFunctions.readXLS;
public class TestData {
	public static String Promotion, Area_Of_Study, Program_Of_Interest, Lead_First_Name, Lead_Last_Name, Email, Addr_Line_1;
	public static String Address_Line_2, City, State, ZipCode, HomePhone, Zip_Code, Home_Phone; //Zip_Code
	public static String URL, OEC_URL;
	public static Number ZC, HP;
	/*public static void main(String[] args) throws IOException {
		System.out.println("Insode main");
	}*/
	static{
		String data[][]=null;
		String fileLoc = ".\\InputTestData\\InputData.xls";
		//String fileLoc = "C:\\ORION_SCRIPTS\\OrionLeadFlowNew\\InputTestData\\InputData.xls";
		//readXLS obj;
		
			try {
				//obj = new readXLS(fileLoc, "LeadDetails");
				new readXLS(fileLoc, "LeadDetails");
				data = readXLS.insertXLSDataInArray();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		String rand = GenericFunctions.randomString(5);
		for(int i=0;i<readXLS.ROW_COUNT;i++)
			for(int j=0;j<readXLS.COLUMN_COUNT;j++)
			{
				System.out.println("//"+data[i][j]);
				
				if (data[i][j].compareToIgnoreCase("Promotion")==0)
					Promotion = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("Area Of Study") == 0)
					Area_Of_Study = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("Program Of Interest") == 0)
					Program_Of_Interest = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("Lead First Name") == 0)
					Lead_First_Name = data[i+1][j]+rand;
				if (data[i][j].compareToIgnoreCase("Lead Last Name") == 0)
					Lead_Last_Name = data[i+1][j]+rand;
				if (data[i][j].compareToIgnoreCase("Email") ==0)
					Email = Lead_First_Name+"@kap.edu";
				if (data[i][j].compareToIgnoreCase("Address Line 1") == 0)
					Addr_Line_1 = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("Address Line 2") == 0)
					Address_Line_2 = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("City") == 0)
					City = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("State") == 0)
					State = data[i+1][j];
				if (data[i][j].compareToIgnoreCase("Zip Code") == 0)
					{
						Zip_Code = data[i+1][j]; 
						try {
							NumberFormat nf = NumberFormat.getInstance(); 
							ZC=nf.parse(Zip_Code);
							Zip_Code = ZC.toString();
					}catch(ParseException e){}
				}
				if (data[i][j].compareToIgnoreCase("Home Phone") == 0)
					{
						Home_Phone = data[i+1][j];
					try{
						NumberFormat nf = NumberFormat.getInstance(); 
						HP=nf.parse(Home_Phone);
						Home_Phone = HP.toString();
					}catch(ParseException e){
						System.out.println(e.getMessage());
				}
				}
			}
		System.out.println("inside TestData class");
		//Environment data
		//readXLS obj1;

			try {
				//obj1 = new readXLS(fileLoc, "Env");
				 new readXLS(fileLoc, "Env");
				data = readXLS.insertXLSDataInArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
		for(int i=0;i<readXLS.ROW_COUNT; i++)
			for(int j=0;j<readXLS.COLUMN_COUNT; j++){
				System.out.println(data[i][j]);
				if(data[i][j].compareToIgnoreCase("Orion URL")==0)
				URL= data[i+1][j];
				
				if(data[i][j].compareToIgnoreCase("OEC URL")==0)
				OEC_URL =data[i=1][j];
			}
		System.out.println(URL + OEC_URL);
		}
	
	//sso - login
	public void ssoLogin(WebDriver driver,String ssoUID, String ssoPWD){
	    //driver.get(baseUrl + "/orion/login.aspx?sso=1");
	    try {
	      Assert.assertEquals("Login", driver.findElement(By.cssSelector("td.headermain")).getText());
	    } catch (Error e) {
	      System.out.println("SSO Login screen not open");
	    }
	    driver.findElement(By.id("UserNameTextBox")).clear();
	    driver.findElement(By.id("UserNameTextBox")).sendKeys(ssoUID);
	    driver.findElement(By.id("PasswordTextBox")).clear();
	    driver.findElement(By.id("PasswordTextBox")).sendKeys(ssoPWD);
	    driver.findElement(By.id("LoginButton")).click();
	    
	    driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	    
	}
	
}
