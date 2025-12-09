package smokeTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ButtonClicksPO;

public class ButtonClickTest extends BaseTest {
  private ButtonClicksPO buttonClicksPO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    buttonClicksPO = new ButtonClicksPO(getDriver());
  }

  @Test(groups = "ui")
  public void buttonVariantsShouldDisplayConfirmationMessages() {
    Assert.assertTrue(
        buttonClicksPO.buttonClicks(), "Standard click should show confirmation message");
    Assert.assertTrue(
        buttonClicksPO.javascriptClick(), "JavaScript click should show confirmation message");
    Assert.assertTrue(
        buttonClicksPO.actionMoveClick(), "Action API click should show confirmation message");
  }
}
