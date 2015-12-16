package uiMap_KuPortalAdmin.portalContent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ECollegeResourceCenterPage {


		public ECollegeResourceCenterPage(WebDriver driver) {
			//Initialize PageObjects.
					PageFactory.initElements(driver, this);
		}
	
		//PageHeader
		@FindBy(how=How.XPATH, using = "html/body/div[2]/div/div[2]/div/div/div/h1")
		public WebElement lblPageHeader;
		public String sPageHeaderTxt = "eCollege Resource Center Admin";
		
		//MAP STUDENT GRP TAB
		@FindBy(how=How.ID, using = "STUDGRP")
		public WebElement lnkMapStudentGrpTab;
		
		//MAP PROGRAM VERSION TAB
		@FindBy(how=How.ID, using = "PRGVRS")
		public WebElement lnkMapProgramVersionTab;
		
		//Campus Drop Down
		@FindBy(how=How.ID, using = "campus")
		public WebElement ddlCampus;

		//Student Group Name Textbox
		@FindBy(how=How.ID, using = "eCollegeClassname")
		public WebElement txtStudentGroupName;

		//Course Drop Down
		@FindBy(how=How.ID, using = "courses")
		public WebElement ddlCourses;

		//classes Drop Down
		@FindBy(how=How.ID, using = "classes")
		public WebElement ddlClass;

		//Filter Button
		@FindBy(how=How.ID, using = "btnFilter")
		public WebElement btnFilter;
				
		//classes Drop Down
		@FindBy(how=How.ID, using = "assigment")
		public WebElement divAssignment;		

		//Assignment Course Drop Down
		@FindBy(how=How.ID, using = "AssignCourse")
		public WebElement ddlAssignCourses;

		//Assignment classes Drop Down
		@FindBy(how=How.ID, using = "AssignClass")
		public WebElement ddlAssignClass;
		
		//Assign To All Results
		@FindBy(how=How.ID, using = "btnAssign")
		public WebElement btnAsssignToAllResults;
				
		//DataTable
		@FindBy(how=How.XPATH, using = "//*[@id='prgmView']/table/thead")
		public WebElement tblDataTable;
		
	
}
