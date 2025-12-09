package pageObjects;

import base.BasePage;
import com.google.common.collect.Range;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DatePickerPO extends BasePage {

  public DatePickerPO(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//span[@class='input-group-addon']")
  private WebElement calendarIcon;

  public WebElement getCalendarIcon() {
    return calendarIcon;
  }

  @FindBy(xpath = "//table[@class=' table-condensed']//th[@class='datepicker-switch']")
  private WebElement checkMonthYear;

  public WebElement getCheckMonthYear() {
    return checkMonthYear;
  }

  @FindBy(xpath = "//div[@class='datepicker-months']//table//thead//th[@class='datepicker-switch']")
  private WebElement checkYearHeader;

  public WebElement getCheckYearHeader() {
    return checkYearHeader;
  }

  @FindBy(xpath = "//div[@class='datepicker-years']//table//thead//th[@class='datepicker-switch']")
  private WebElement checkYearRangeHeader;

  public WebElement getCheckYearRangeHeader() {
    return checkYearRangeHeader;
  }

  public WebElement getSelectYear(int year) {
    return (driver.findElement(
        By.xpath("//div[@class='datepicker-years']//td//span[contains(text(),'" + year + "')]")));
  }

  @FindBy(xpath = "//div[@class='datepicker-years']//table//thead//th[@class='next']")
  private WebElement nextYear;

  public WebElement getNextYear() {
    return nextYear;
  }

  @FindBy(xpath = "//div[@class='datepicker-years']//table//thead//th[@class='prev']")
  private WebElement previousYear;

  public WebElement getPreviousYear() {
    return previousYear;
  }

  @FindBy(xpath = "//div[@class='datepicker-months']//td//span[@class='month active']")
  private WebElement currentMonth;

  public WebElement getCurrentMonth() {
    return currentMonth;
  }

  public WebElement getSelectMonth(String text) {
    return (driver.findElement(
        By.xpath("//div[@class='datepicker-months']//td//span[contains(text(),'" + text + "')]")));
  }

  @FindBy(xpath = "//div[@class='datepicker-days']//td[@class='today active day']")
  private WebElement currentDay;

  public WebElement getCurrentDay() {
    return currentDay;
  }

  public WebElement getSelectDay(String text) {
    return (driver.findElement(
        By.xpath("//div[@class='datepicker-days']//td[contains(text(),'" + text + "')]")));
  }

  public void selectPreviousDate(String date, String month, int year) {

    clickElement(getCalendarIcon());
    String[] dateMonth = getCheckMonthYear().getText().split(" ");
    String currentSelectedMonth = dateMonth[0];
    int currentSelectedYear = Integer.parseInt(dateMonth[1]);

    if (currentSelectedYear != year) {
      clickElement(getCheckMonthYear());
      clickElement(getCheckYearHeader());

      while (true) {
        // get the latest year range text every iteration
        String[] yearRangeText = getCheckYearRangeHeader().getText().split("-");
        int startYearRange = Integer.parseInt(yearRangeText[0].trim());
        int endYearRange = Integer.parseInt(yearRangeText[1].trim());

        // check if desired year is inside the visible range
        if (Range.closed(startYearRange, endYearRange).contains(year)) {
          clickElement(getSelectYear(year));
          break; // found correct range → exit loop
        }

        // if year is greater than visible range → click Next
        if (year > endYearRange) {
          clickElement(getNextYear());
        }
        // if year is smaller than visible range → click Previous
        else if (year < startYearRange) {
          clickElement(getPreviousYear());
        }
      }

      clickElement(getSelectMonth(month));

      clickElement(getSelectDay(date));

    } else if (currentSelectedMonth != month) {
      clickElement(getCheckMonthYear());
      clickElement(getSelectMonth(month));
      clickElement(getSelectDay(date));
    } else {
      clickElement(getSelectDay(date));
    }
  }
}

//	PREVIOUS_MONTH_CURRENT_YEAR:{},

//	PREVIOUS_MONTH_PREVIOUS_YEAR:{};

//   public abstract void get(String Month, String Year, )
