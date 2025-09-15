package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.BasePage;
import base.PropertyReader;

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
	
//	@FindBy(xpath = "//div[h2[text()='Action Move & Click']]//span[contains(., 'CLICK ME!')]")
	
	@FindBy(xpath = "//div[@id = 'myModalMoveClick']//div[@class= 'modal-footer']//button")
	private WebElement closeActionMoveButton;

	@FindBy(xpath = "//p[normalize-space(.)='Well done for successfully using the click() method!']")
	private WebElement clickWebElementMessage;
	
	@FindBy(xpath = "//p[contains(text(),'We can use JavaScript code if all else fails! Remember always try to use the WebDriver Library method(s) first such as WebElement')]")
	private WebElement javascriptClickMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Advanced user interactions (API) has been developed to enable you to perform more complex interactions')]")
	private WebElement clickMoveAndActionMessage;

	public WebElement getButtonClicks() {
		return buttonClicks;
	}

	public WebElement getClickWebElement() {
		return clickWebElement;
	}

	public WebElement getJavascriptClick() {
		return javascriptClick;
	}

	public WebElement getActionMoveClick() {
		return actionMoveClick;
	}

	public WebElement getClickWebElementMessage() {
		return clickWebElementMessage;
	}

	public void buttonClicks() {

		switchToNewWindow(buttonClicks);

		// Perform some action after switching
		System.out.println("Currently in window: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Button Clicks"));
		clickElement(clickWebElement);
		isDisplay(clickWebElementMessage);
		clickElement(closeWebElementButton);
		closeCurrentAndReturn();
	}
	
	public void javascriptClick() {

		switchToNewWindow(buttonClicks);

		// Perform some action after switching
		System.out.println("Currently in window: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Button Clicks"));
		javascriptClickElement(driver,javascriptClick);
		isDisplay(javascriptClickMessage);
		clickElement(closeJavaScriptButton);
		closeCurrentAndReturn();
	}
	
	public void actionMoveClick() {

		switchToNewWindow(buttonClicks);

		// Perform some action after switching
		System.out.println("Currently in window: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Button Clicks"));
		moveAndClick(driver,actionMoveClick);
		isDisplay(clickMoveAndActionMessage);
		clickElement(closeActionMoveButton);
		closeCurrentAndReturn();
	}


}
