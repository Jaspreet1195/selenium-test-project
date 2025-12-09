package smokeTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DragAndDropPO;
import pageObjects.HomePagePO;

public class DragAndDrop extends BaseTest {
  private DragAndDropPO dragAndDropPO;
  private HomePagePO homePagePO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    dragAndDropPO = new DragAndDropPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void dragAndDropTest() {
    dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
    Assert.assertEquals(dragAndDropPO.getTitle().trim(), "WebDriver | Actions");
    dragAndDropPO.dragAndDrop(dragAndDropPO.getDraggableText(), dragAndDropPO.getDroppableText());
    Assert.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getDroppedText()));
  }

  @Test(groups = "ui")
  public void doubleClick() {
    dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
    Assert.assertEquals(dragAndDropPO.getTitle().trim(), "WebDriver | Actions");
    dragAndDropPO.doubleClickUsingAction(dragAndDropPO.getDoubleClickText());
    Assert.assertEquals(
        dragAndDropPO.getdoubleClickToGetAttribute().getAttribute("class"),
        "div-double-click double");
  }

  @Test(groups = "ui")
  public void clickAndHold() {
    dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
    Assert.assertEquals(dragAndDropPO.getTitle().trim(), "WebDriver | Actions");
    dragAndDropPO.releaseAndHoldUsingAction(dragAndDropPO.getClickBoxText());
    Assert.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getClickBoxTextWhileHoldingButton()));
    dragAndDropPO.releaseButton(dragAndDropPO.getClickBoxText());
    Assert.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getClickBoxTextAfterReleased()));
  }
}
