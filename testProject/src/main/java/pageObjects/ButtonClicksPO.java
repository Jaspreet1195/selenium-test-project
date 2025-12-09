package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ButtonClicksPO extends BasePage {

  public ButtonClicksPO(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//h1[contains(text(),'BUTTON CLICKS')]")
  private WebElement buttonClicks;

  @FindBy(xpath = "//span[@id='button1']/p[contains(text(), 'CLICK ME')]")
  private WebElement clickWebElement;

  @FindBy(xpath = "//span[@id='button2' and contains(text(), 'CLICK ME')]")
  private WebElement javascriptClick;

  @FindBy(xpath = "//span[@id='button3' and contains(text(), 'CLICK ME')]")
  private WebElement actionMoveClick;

  @FindBy(xpath = "//div[@id = 'myModalClick']//div[@class= 'modal-footer']//button")
  private WebElement closeWebElementButton;

  @FindBy(xpath = "//div[@id = 'myModalJSClick']//div[@class= 'modal-footer']//button")
  private WebElement closeJavaScriptButton;

  @FindBy(xpath = "//div[@id = 'myModalMoveClick']//div[@class= 'modal-footer']//button")
  private WebElement closeActionMoveButton;

  @FindBy(xpath = "//p[normalize-space(.)='Well done for successfully using the click() method!']")
  private WebElement clickWebElementMessage;

  @FindBy(
      xpath =
          "//p[contains(text(),'We can use JavaScript code if all else fails! Remember always try to use the WebDriver Library method(s) first such as WebElement')]")
  private WebElement javascriptClickMessage;

  @FindBy(
      xpath =
          "//p[contains(text(),'Advanced user interactions (API) has been developed to enable you to perform more complex interactions')]")
  private WebElement clickMoveAndActionMessage;

  public WebElement getButtonClicks() {
    return buttonClicks;
  }

  public boolean buttonClicks() {
    switchToButtonClicksWindow();
    clickElement(clickWebElement);
    boolean displayed = isDisplay(clickWebElementMessage);
    clickElement(closeWebElementButton);
    closeCurrentAndReturn();
    return displayed;
  }

  public boolean javascriptClick() {
    switchToButtonClicksWindow();
    javascriptClickElement(driver, javascriptClick);
    boolean displayed = isDisplay(javascriptClickMessage);
    clickElement(closeJavaScriptButton);
    closeCurrentAndReturn();
    return displayed;
  }

  public boolean actionMoveClick() {
    switchToButtonClicksWindow();
    moveAndClick(driver, actionMoveClick);
    boolean displayed = isDisplay(clickMoveAndActionMessage);
    clickElement(closeActionMoveButton);
    closeCurrentAndReturn();
    return displayed;
  }

  private void switchToButtonClicksWindow() {
    switchToNewWindow(buttonClicks);
    if (!driver.getTitle().equalsIgnoreCase("WebDriver | Button Clicks")) {
      throw new IllegalStateException("Unexpected window opened: " + driver.getTitle());
    }
  }
}
