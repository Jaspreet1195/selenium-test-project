package smokeTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DatePickerPO;
import pageObjects.HomePagePO;

public class DatePicker extends BaseTest {

  private HomePagePO homePagePO;
  private DatePickerPO datePickerPO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    datePickerPO = new DatePickerPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void selectDateOfLastMonth() {
    datePickerPO.switchToNewWindow(homePagePO.getDatepickerHeader());
    Assert.assertEquals(homePagePO.getTitle(), "WebDriver | Datepicker");

    datePickerPO.clickElement(datePickerPO.getCalendarIcon());
    datePickerPO.selectPreviousDate("25", "Sep", 2025);
    Assert.assertTrue(datePickerPO.getTitle().contains("Datepicker"));
  }
}
