package smokeTest;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DropDowCheckboxRadioButtonPO;
import pageObjects.HomePagePO;

public class DropDowCheckboxRadioButton extends BaseTest {

  private DropDowCheckboxRadioButtonPO dropdownPage;
  private HomePagePO homePagePO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    dropdownPage = new DropDowCheckboxRadioButtonPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void dropdownSelectionsShouldPersist() {
    dropdownPage.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
    Assert.assertEquals(
        getDriver().getTitle(), "WebDriver | Dropdown Menu(s) | Checkboxe(s) | Radio Button(s)");

    dropdownPage.selectFromDropdown(dropdownPage.getDropdownMenu1(), "Python");
    dropdownPage.selectFromDropdown(dropdownPage.getDropdownMenu2(), "TestNG");
    dropdownPage.selectFromDropdown(dropdownPage.getDropdownMenu3(), "CSS");

    Assert.assertEquals(
        new Select(dropdownPage.getDropdownMenu1()).getFirstSelectedOption().getText(), "Python");
    Assert.assertEquals(
        new Select(dropdownPage.getDropdownMenu2()).getFirstSelectedOption().getText(), "TestNG");
    Assert.assertEquals(
        new Select(dropdownPage.getDropdownMenu3()).getFirstSelectedOption().getText(), "CSS");

    dropdownPage.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void checkboxSelectionsCanBeToggled() {
    dropdownPage.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
    for (WebElement checkbox : dropdownPage.getCheckboxes()) {
      if (!checkbox.isSelected()) {
        dropdownPage.clickElement(checkbox);
      }
      Assert.assertTrue(
          checkbox.isSelected(), "Checkbox should be selected: " + checkbox.getAttribute("value"));
    }
    dropdownPage.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void radioButtonsAllowSingleSelection() {
    dropdownPage.switchToNewWindow(homePagePO.getDropdownCheckboxesHeader());
    for (WebElement radioButton : dropdownPage.getRadioButtons()) {
      if (radioButton.getAttribute("value").equalsIgnoreCase("green")) {
        dropdownPage.clickElement(radioButton);
        Assert.assertTrue(radioButton.isSelected());
        break;
      }
    }
    dropdownPage.closeCurrentAndReturn();
  }
}
