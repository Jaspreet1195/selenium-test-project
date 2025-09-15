package smokeTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePagePO;
import pageObjects.PopUpAndAlertsPO;

public class PopUpAndAlerts extends BaseTest {

	PopUpAndAlertsPO popUpAndAlertsPO;
	HomePagePO homePagePO;

	@BeforeMethod
	public void setup() {
		popUpAndAlertsPO = new PopUpAndAlertsPO(driver);
		homePagePO= new HomePagePO(driver);
	}

//	@Test
	public void javaScriptLoader() {
		popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
		System.out.println(driver.getTitle());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Popups & Alerts"));
		popUpAndAlertsPO.javascriptClickElement(driver, popUpAndAlertsPO.getJsAlertButton());
		popUpAndAlertsPO.checkForAlertTextAndAccept("I am an alert box!");
		popUpAndAlertsPO.closeCurrentAndReturn();
	}

	public void modalPopup() {

	}

	@Test
	public void ajaxLoader() {
		popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
		System.out.println(driver.getTitle());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Popups & Alerts"));
		popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getAjaxLoaderButton());
		popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getClickMeButton());
		popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getCloseButton());
		popUpAndAlertsPO.closeCurrentAndReturn();
	}

//	@Test
	public void javaScriptConfirmBoxAccept() {
		popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
		System.out.println(driver.getTitle());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Popups & Alerts"));
		popUpAndAlertsPO.javascriptClickElement(driver, popUpAndAlertsPO.getJsConfirmButton());
		popUpAndAlertsPO.checkForAlertTextAndAccept("Press a button!");
		AssertJUnit.assertTrue(popUpAndAlertsPO.isDisplay(popUpAndAlertsPO.getConfirmAlertBoxText()));
		popUpAndAlertsPO.closeCurrentAndReturn();
	}
}
