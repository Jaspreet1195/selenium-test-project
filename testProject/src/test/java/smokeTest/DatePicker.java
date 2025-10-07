package smokeTest;

import org.testng.annotations.Test;

import baseTest.BaseTest;

import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DatePickerPO;
import pageObjects.HomePagePO;
import pageObjects.PopUpAndAlertsPO;

public class DatePicker extends BaseTest{
	
	HomePagePO homePagePO;
	DatePickerPO datePickerPO;

	@BeforeMethod
	public void setup() {
		datePickerPO= new DatePickerPO(getDriver());
		homePagePO = new HomePagePO(getDriver());
	}
	
	
//	String[] dateMonth = datePickerPO.getCheckMonthYear().getText().split(" ");
//	String month = dateMonth[0];
//	String year = dateMonth[1];
	
	
	
	@Test
	public void selectDateOfLastMonth() {
		datePickerPO.switchToNewWindow(homePagePO.getDatepickerHeader());
		AssertJUnit.assertTrue(homePagePO.getTitle().equalsIgnoreCase("WebDriver | Datepicker"));
		datePickerPO.clickElement(datePickerPO.getCalendarIcon());
		
		//same year in window is tested
		//past year is tested
		//double past
		//future year is tested
		//double future
//		datePickerPO.selectPreviousDate("4","Jun", 2044);
		
		
//		year is correct select month only
//		datePickerPO.selectPreviousDate("10", "Aug", 2025);
		
//		year and date is correct select date only
		datePickerPO.selectPreviousDate("25", "Sep", 2025);
//		Assert.assertTrue(false);
		
	}

}
