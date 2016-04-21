package uiMap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProgramGroupPageObjects {
	

public 	ProgramGroupPageObjects (WebDriver driver)
{
            //Initialize A ProgramGroupPageObjects.
			PageFactory.initElements(driver, this);

}

           //************************Adding new program group objects************************************
           //Program Maintenance Tab 
			@FindBy(how=How.XPATH, using= "//*[@id='_ctl2_TopNavRow']/td[13]")
			public WebElement TbprogramMaintenancehomepage;
			
			
			
			// Program Rollup Maintenance Link 
			@FindBy(how=How.XPATH, using="//a[contains(text(),'Program Rollup Maintenance')]")
			public WebElement lnkprogramRollupMaintenance;
			
			//Program Group Button 
			@FindBy(how=How.XPATH, using=".//*[@id='ProgramRollupNavTab1_TabRow']/td[4]/a")
			public WebElement BtnProgramGroup;
			
			//Add New Program Button 
			@FindBy(how=How.XPATH, using=".//*[@id='NewMapButton']")
			public WebElement BtnAddnew;
			
			//Adding Program Name in the Textbox 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups__ctl3_tbProgramGroup']")
			public WebElement txtAddProgram;
			
			//School Drop Down Button 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[2]/select")
			public WebElement DrpBtnSchool;
			
			//Selecting School From the DropDown List 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[2]/select/option[2]")
			public WebElement slctSchool;
			
			//Degree Type Drop Down Button
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[3]/select")
			public WebElement DrpBtnDegreeType;
			
			//Selecting Degree From the List 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[3]/select/option[2]")
			public WebElement slctDegreeType;
			
			//Orion School DropDown Button
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[4]/select")
			public WebElement DrpBtnOrionSchool;
			
			//Selecting Orion School From the List 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[4]/select/option[2]")
			public WebElement slctOrionSchool;
			
			//Update Button
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[7]/a[1]")
			public WebElement BtnUpdate;
			
			//Program group Update Success Message 
			@FindBy(how=How.XPATH, using=".//*[@id='lblMessage']")
			public WebElement MsgUpdateSuccess;
			
			
			
			
			//********************Salesforce Verification Part Objects***********************
			
		
			
			//Search Text Field 
			@FindBy(how=How.XPATH, using=".//*[@id='phSearchInput']")
			public WebElement txtSearchfield;
			
			//Search Button 
			@FindBy(how=How.XPATH, using=".//*[@id='phSearchButton']")
			public WebElement BtnSearch;
			
			//Search all link 
			@FindBy(how=How.XPATH, using=".//*[@id='searchAll']")
			public WebElement lnkSearchAll;
			
			//Program group label 
			@FindBy(how=How.XPATH, using="//*[contains(text(),'Program Groups')]")
			public WebElement lblProgramGroup;
			
			//Program group Label After Update 
			@FindBy(how=How.XPATH, using="//*[contains(text(),'Program Groups (1)')]")
			public WebElement lblProgramGroup1;
			
			//Clicking on Search Result
			@FindBy(how=How.XPATH, using=".//*[@id='Program_Group__c_body']/table/tbody/tr[2]/th/a")
			public WebElement lnkSearchResult;
			
			//Program Group Name Value  
			@FindBy(how=How.XPATH, using="//td[text()='Program Group Name']/following-sibling::td[1]/div")
			public WebElement txtProgramGroupName;
			
			//Program Group ID value
			@FindBy(how=How.XPATH, using="//td[text()='Program Group ID']/following-sibling::td[1]/div")
			public WebElement txtProgramGroupID;
			
			//School Name Value 
			@FindBy(how=How.XPATH, using="//td[text()='School']/following-sibling::td[1]/div")
			public WebElement txtSchool;
			
			//Degree type Value 
			@FindBy(how=How.XPATH, using="//td[text()='DegreeType']/following-sibling::td[1]/div")
			public WebElement txtDegreeType;
			
			//********************************Updating program group navigation Objects*******************************************
			
			//Group Name Search Field 
			@FindBy(how=How.XPATH, using=".//*[@id='tbGroupNameSearch']")
			public WebElement txtGroupNameSearch;
			
			//Program Group Search button
			@FindBy(how=How.XPATH, using=".//*[@id='btnProgramGroup_Filter']")
			public WebElement BtnPgmGrpSearch;
			
			//Program Group Edit link
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[7]/a")
			public WebElement lnkEdit;
			
			//Second Record In  School List 
			@FindBy(how=How.XPATH, using=".//*[@id='dgProgramGroups']/tbody/tr[2]/td[2]/select/option[3]")
			public WebElement txtSchoolUpdate;
			
			
			//Orion Program Group Update Success Message 
			@FindBy(how=How.XPATH, using=".//*[@id='lblMessage']")
			public WebElement MsgPrgmGrpUpdate;
			
			
			
			
			
			
}
