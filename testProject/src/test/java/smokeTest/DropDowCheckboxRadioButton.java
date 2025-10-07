package smokeTest;

import org.testng.annotations.Test;

import baseTest.BaseTest;

import org.testng.AssertJUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ButtonClicksPO;
import pageObjects.DropDowCheckboxRadioButtonPO;
import pageObjects.HomePagePO;

public class DropDowCheckboxRadioButton extends BaseTest{
	
	DropDowCheckboxRadioButtonPO dropDowCheckboxRadioButtonPO;
	HomePagePO homePagePO;
	
	@BeforeMethod
	public void setup() {
		dropDowCheckboxRadioButtonPO = new DropDowCheckboxRadioButtonPO(getDriver());
		homePagePO = new HomePagePO(getDriver());
	}
	
//	@Test
	//Test dropDown
	public void dropdown() {
        dropDowCheckboxRadioButtonPO.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
        
		// Perform some action after switching
		System.out.println("Currently in window: " + getDriver().getTitle());
		AssertJUnit.assertTrue(getDriver().getTitle().equalsIgnoreCase("WebDriver | Dropdown Menu(s) | Checkboxe(s) | Radio Button(s)"));
		
		dropDowCheckboxRadioButtonPO.selectFromDropdown(dropDowCheckboxRadioButtonPO.getDropdownMenu1(), "Python");
		dropDowCheckboxRadioButtonPO.selectFromDropdown(dropDowCheckboxRadioButtonPO.getDropdownMenu2(), "TestNG");
		dropDowCheckboxRadioButtonPO.selectFromDropdown(dropDowCheckboxRadioButtonPO.getDropdownMenu3(), "CSS");
		
		
	}
	
//	@Test
	//Test checkbox
	public void checkbox() {
		    dropDowCheckboxRadioButtonPO.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
	        
			// Perform some action after switching
			System.out.println("Currently in window: " + getDriver().getTitle());
			AssertJUnit.assertTrue(getDriver().getTitle().equalsIgnoreCase("WebDriver | Dropdown Menu(s) | Checkboxe(s) | Radio Button(s)"));
	        for (WebElement checkbox : dropDowCheckboxRadioButtonPO.getCheckboxes()) {	  
	            if (checkbox.getAttribute("value").equalsIgnoreCase("option-1") || checkbox.getAttribute("value").equalsIgnoreCase("option-2")) {
	            	dropDowCheckboxRadioButtonPO.clickElement(checkbox);	               
	            }	  	            
	            else if(checkbox.getAttribute("value").equalsIgnoreCase("option-3") && checkbox.isEnabled()) {
	            	dropDowCheckboxRadioButtonPO.clickElement(checkbox);
	            }	            
	        }
	        
	        dropDowCheckboxRadioButtonPO.closeCurrentAndReturn();
	    }
	
	@Test
	public void RadioButton() {
	
		dropDowCheckboxRadioButtonPO.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
		// Perform some action after switching
		System.out.println("Currently in window: " + getDriver().getTitle());
		AssertJUnit.assertTrue(getDriver().getTitle().equalsIgnoreCase("WebDriver | Dropdown Menu(s) | Checkboxe(s) | Radio Button(s)"));
		for (WebElement radioButton : dropDowCheckboxRadioButtonPO.getRadioButtons()) {
            if (radioButton.getAttribute("value").equalsIgnoreCase("green")) {
            	dropDowCheckboxRadioButtonPO.clickElement(radioButton);	               
            }	  	                        
        }
		  dropDowCheckboxRadioButtonPO.closeCurrentAndReturn();
	}
	
	
	}
