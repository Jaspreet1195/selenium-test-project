package smokeTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePagePO;
import pageObjects.ScrollingPO;

public class Scrolling extends BaseTest {
  private ScrollingPO scrollingPO;
  private HomePagePO homePagePO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    scrollingPO = new ScrollingPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void scollToZone1() {
    scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Scrolling");

    scrollingPO.moveAndClick(getDriver(), scrollingPO.getZone1Header());
    Assert.assertTrue(scrollingPO.isDisplay(scrollingPO.getZone1HeaderAfterHover()));
    scrollingPO.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void scollToZone2() {
    scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Scrolling");
    scrollingPO.moveAndClick(getDriver(), scrollingPO.getZone2Header());
    Assert.assertEquals(scrollingPO.getZone2Header().getText(), "1 Entries");
    scrollingPO.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void scollToZone3() {
    scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Scrolling");
    scrollingPO.moveAndClick(getDriver(), scrollingPO.getZone3Header());
    Assert.assertEquals(scrollingPO.getZone3Header().getText(), "1 Entries");
    scrollingPO.closeCurrentAndReturn();
  }

  @Test(groups = "ui")
  public void scollToZone4() {
    scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
    Assert.assertEquals(getDriver().getTitle(), "WebDriver | Scrolling");
    scrollingPO.moveAndClick(getDriver(), scrollingPO.getZone4Header());
    Assert.assertTrue(
        scrollingPO.getZone4Header().getText().contains("X")
            && scrollingPO.getZone4Header().getText().contains("Y"));
    scrollingPO.closeCurrentAndReturn();
  }
}
