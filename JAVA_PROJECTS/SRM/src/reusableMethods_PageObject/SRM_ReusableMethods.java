package reusableMethods_PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SRM_ReusableMethods {
  //This method is used to wait for 2 to appear in SRM
// Prerquisite is that in SRM you have to make the first search	
  public static void WaitSearchOpportunity(WebDriver driver, int timeout) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			//refreshiing /reloading the page
			driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
		  	Thread.sleep(1000);
		  	//verifiation
		  	if(driver.findElement(By.xpath("//h3/span[contains(text(),'Opportunities')]")).isDisplayed())
		  	{
		  		System.out.println("PASS");
		  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
		  		i=21;
		  	}
		  	
		  }
			catch(Exception e)
			{
				System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}

		}
  }
  
  //This method is used to wait for Inquiry to appear in SRM
  public static void WaitSearchInquiry(WebDriver driver, int timeout) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
		  	Thread.sleep(1000);
		  	if(driver.findElement(By.xpath("//h3/span[contains(text(),'Inquiries')]")).isDisplayed())
		  	{
		  		System.out.println("PASS");
		  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
		  		i=21;
		  	}
		  	
		  		
		  }
			catch(Exception e)
			{
				System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}
		}
  }
		
		
		//This method is used to wait for College/University to appear in SRM
		  public static void WaitCollegeUniversity(WebDriver driver, int timeout) {
			  int count =0;
				
				for(int i=0;i<5;i++)
				{ try {	
					Thread.sleep(timeout);
				  	//driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
					driver.navigate().refresh();
				  	Thread.sleep(1000);
				  	if(driver.findElement(By.xpath(".//*[@class='pbBody']/table/tbody/tr[2]/th/a")).isDisplayed())
				  	{
				  		System.out.println("PASS");
				  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
				  		i=21;
				  	}
				  }
					catch(Exception e)
					{
						System.out.println("FAIL");
						count=count+1;
						System.out.println("No of Iterations"+" "+count);
						
				
					}

		}
}
}