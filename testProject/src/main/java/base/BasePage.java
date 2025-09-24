package base;

//import static org.testng.Assert.assertTrue;

import java.time.Duration;

import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private String originalWindowHandle;
	private String newWindowHandle;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void clickElement(WebElement element) {
//    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void javascriptClickElement(WebDriver driver, WebElement element) {
		try {
			// wait until it's present and clickable
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			// click with JS
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		} catch (Exception e) {
			System.out.println("Failed to click element with JS: " + e.getMessage());
		}
	}

	public void moveAndClick(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
	
	public void sendKeys(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}
	
	public void sendKeysPressEnter(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text+ Keys.ENTER);
	}

	public String getElementText(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public void shortWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void mediumWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void longWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToNewWindow(WebElement clickableElement) {
		originalWindowHandle = driver.getWindowHandle();
		Set<String> oldWindows = driver.getWindowHandles();

		clickableElement.click();

		// Wait for new window
		int retries = 0;
		while (driver.getWindowHandles().size() <= oldWindows.size() && retries < 5) {
			try {
				Thread.sleep(1000);
				retries++;
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		Set<String> allWindows = driver.getWindowHandles();
		for (String handle : allWindows) {
			if (!oldWindows.contains(handle)) {
				newWindowHandle = handle;
				driver.switchTo().window(newWindowHandle);
				return;
			}
		}

		throw new RuntimeException("New window not found.");
	}

	/**
	 * Switches back to the original window if it was stored.
	 */
	public void switchToOriginalWindow() {
		if (originalWindowHandle != null) {
			driver.switchTo().window(originalWindowHandle);
		} else {
			throw new IllegalStateException("Original window handle is not stored.");
		}
	}

	/**
	 * (Optional) Close current window and switch to original
	 */
	public void closeCurrentAndReturn() {
		driver.close();
		switchToOriginalWindow();
	}
	
	public String checkForAlertTextAndAccept(String expectedMessage) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        System.out.println("ALERT FOUND: " + alertText);

	        if (alertText.equalsIgnoreCase(expectedMessage)) {
	            alert.accept();
	            return "Alert matched and accepted: " + alertText;
	        } else {
	            alert.dismiss();
	            return "Alert text did not match. Expected: " + expectedMessage + " | Found: " + alertText;
	        }
	    } catch (NoAlertPresentException ex) {
	        return "No alert found.";
	    }
	}


	public boolean isDisplay(WebElement element) {
		try {
			element.isDisplayed();

			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void selectFromDropdown(WebElement dropdown, String value) {
		new Select(dropdown).selectByVisibleText(value);
//        new Select(dropdown).selectByValue(value);
	}

	public void checkUncheckRadioButton(WebElement element) {
		element.click();
	}
	
	public enum ScrollType{
	    SCROLL_INTO_ELEMENT {
	        @Override
	        public void scroll(WebDriver driver, WebElement element) {
	        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        }
	    },
	    SCROLL_WITH_XY {
	        @Override
	        public void scroll(WebDriver driver, WebElement element) {
	            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250);");
	        }
	    },
	    SCROLL_TO_BOTTOM {
	        @Override
	        public void scroll(WebDriver driver, WebElement element) {
	            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        }
	    };

	    public abstract void scroll(WebDriver driver, WebElement element);
	}
	
	public void scrollIntoView(WebElement element, ScrollType type) {
	    type.scroll(driver, element);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
		// Create Actions instance
		Actions actions = new Actions(driver);

		// Perform drag and drop
		actions.dragAndDrop(sourceElement, targetElement).perform();

		//	actions.clickAndHold(sourceElement)  // Click on the source
//	       .moveToElement(targetElement) // Move to target
//	       .release()             // Release mouse
//	       .build()               // Build the action
//	 
			
	}
	
	public void doubleClickUsingAction(WebElement element) {
		// Create Actions instance
				Actions actions = new Actions(driver);
				actions.moveToElement(element).doubleClick().build().perform();
//				actions.doubleClick(element);
	}
	
	public void releaseAndHoldUsingAction(WebElement element) {
		// Create Actions instance
				Actions actions = new Actions(driver);
				actions.moveToElement(element).clickAndHold().build().perform();
	}
	
	public void releaseButton(WebElement element) {
		// Create Actions instance
				Actions actions = new Actions(driver);
				actions.moveToElement(element).release(element).perform();;
	}
	
	
	
}
