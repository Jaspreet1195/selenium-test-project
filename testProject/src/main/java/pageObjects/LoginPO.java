package pageObjects;

import base.BasePage;
import base.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BasePage {

  public LoginPO(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//h1[contains(text(),'LOGIN PORTAL')]")
  private WebElement loginPortalHeader;

  @FindBy(xpath = "//input[@placeholder = 'Username']")
  private WebElement username;

  @FindBy(id = "password")
  private WebElement password;

  @FindBy(id = "login-button")
  private WebElement loginButton;

  public String loginFailed() {
    switchToNewWindow(loginPortalHeader);
    validateLoginWindow();
    sendKeys(username, PropertyReader.get("username"));
    sendKeys(password, PropertyReader.get("password"));
    clickElement(loginButton);
    String alertMessage = checkForAlertTextAndAccept("validation failed");
    closeCurrentAndReturn();
    return alertMessage;
  }

  private void validateLoginWindow() {
    if (!driver.getTitle().equalsIgnoreCase("WebDriver | Login Portal")) {
      throw new IllegalStateException("Unexpected login portal title: " + driver.getTitle());
    }
  }
}
