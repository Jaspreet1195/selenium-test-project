package pageObjects;

import base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropDowCheckboxRadioButtonPO extends BasePage {

  // Constructor
  public DropDowCheckboxRadioButtonPO(WebDriver driver) {
    super(driver);
  }

  // ===== DROPDOWN MENUS =====
  @FindBy(id = "dropdowm-menu-1")
  private WebElement dropdownMenu1;

  @FindBy(id = "dropdowm-menu-2")
  private WebElement dropdownMenu2;

  @FindBy(id = "dropdowm-menu-3")
  private WebElement dropdownMenu3;

  // ===== CHECKBOXES =====
  @FindBy(id = "checkboxes")
  private WebElement checkboxSection;

  @FindBy(css = "#checkboxes input[type='checkbox']")
  private List<WebElement> checkboxes;

  // ===== RADIO BUTTONS =====
  @FindBy(id = "radio-buttons")
  private WebElement radioButtonSection;

  @FindBy(css = "#radio-buttons input[type='radio']")
  private List<WebElement> radioButtons;

  // ===== SELECTED & DISABLED SECTION =====
  @FindBy(id = "radio-buttons-selected-disabled")
  private WebElement radioButtonsSelectedDisabled;

  @FindBy(id = "fruit-selects")
  private WebElement fruitSelect;

  // ===== GETTERS =====

  public WebElement getDropdownMenu1() {
    return dropdownMenu1;
  }

  public WebElement getDropdownMenu2() {
    return dropdownMenu2;
  }

  public WebElement getDropdownMenu3() {
    return dropdownMenu3;
  }

  public WebElement getCheckboxSection() {
    return checkboxSection;
  }

  public List<WebElement> getCheckboxes() {
    return checkboxes;
  }

  public WebElement getRadioButtonSection() {
    return radioButtonSection;
  }

  public List<WebElement> getRadioButtons() {
    return radioButtons;
  }

  public WebElement getRadioButtonsSelectedDisabled() {
    return radioButtonsSelectedDisabled;
  }

  public WebElement getFruitSelect() {
    return fruitSelect;
  }

  // ===== METHODS =====

  public void checkAllCheckboxes() {
    for (WebElement checkbox : checkboxes) {
      if (!checkbox.isSelected()) {
        checkbox.click();
      }
    }
  }

  public void selectRadioButton(String value) {
    for (WebElement radio : radioButtons) {
      if (radio.getAttribute("value").equalsIgnoreCase(value)) {
        radio.click();
        break;
      }
    }
  }

  public boolean isFruitOptionDisabled(String value) {
    WebElement option =
        driver.findElement(By.cssSelector("select#fruit-selects option[value='" + value + "']"));
    return !option.isEnabled();
  }
}
