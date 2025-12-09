package smokeTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePagePO;
import pageObjects.PopUpAndAlertsPO;

public class PopUpAndAlerts extends BaseTest {

  private PopUpAndAlertsPO popUpAndAlertsPO;
  private HomePagePO homePagePO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    popUpAndAlertsPO = new PopUpAndAlertsPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void javaScriptLoader() {
    popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Popups & Alerts");
    popUpAndAlertsPO.javascriptClickElement(getDriver(), popUpAndAlertsPO.getJsAlertButton());
    String alertMessage = popUpAndAlertsPO.checkForAlertTextAndAccept("I am an alert box!");
    Assert.assertTrue(alertMessage.contains("Alert matched"));
    popUpAndAlertsPO.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void ajaxLoader() {
    popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Popups & Alerts");
    popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getAjaxLoaderButton());
    popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getClickMeButton());
    Assert.assertTrue(popUpAndAlertsPO.isDisplay(popUpAndAlertsPO.getAjaxLoaderWaitingAlert()));
    popUpAndAlertsPO.clickElement(popUpAndAlertsPO.getCloseButton());
    popUpAndAlertsPO.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void javaScriptConfirmBoxAccept() {
    popUpAndAlertsPO.switchToNewWindow(homePagePO.getPopupAlertsHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Popups & Alerts");
    popUpAndAlertsPO.javascriptClickElement(getDriver(), popUpAndAlertsPO.getJsConfirmButton());
    String confirmText = popUpAndAlertsPO.checkForAlertTextAndAccept("Press a button!");
    Assert.assertTrue(confirmText.contains("Alert matched"));
    Assert.assertTrue(popUpAndAlertsPO.isDisplay(popUpAndAlertsPO.getConfirmAlertBoxText()));
    popUpAndAlertsPO.closeCurrentAndReturn();
  }
}
