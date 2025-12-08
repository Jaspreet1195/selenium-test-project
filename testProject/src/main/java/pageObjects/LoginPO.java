package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.BasePage;
import base.PropertyReader;

public class LoginPO extends BasePage {

	Alert alert;

	// Constructor initializes PageFactory
	public LoginPO(WebDriver driver) {
		super(driver);
	}

	// Page Locator
	@FindBy(xpath = "//h1[contains(text(),'LOGIN PORTAL')]")
	private WebElement loginPortalHeader;

	@FindBy(xpath = "//input[@placeholder = 'Username']")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	// Page actions
	public WebElement getClickLoginButton() {
		return loginPortalHeader;
	}

	public WebElement getUserNameButton() {
		return username;
	}

	public WebElement getPassowrdButton() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	// re-usable methods
	public void loginFailed() {

		switchToNewWindow(loginPortalHeader);

		// Perform some action after switching
		System.out.println("Currently in window: " + driver.getTitle());

		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Login Portal"));
		sendKeys(username, PropertyReader.get("username"));
		sendKeys(password, PropertyReader.get("password"));
		clickElement(loginButton);
		checkForAlertTextAndAccept("validation failed");
		closeCurrentAndReturn();
	}

}
