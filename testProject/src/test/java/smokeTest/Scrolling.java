package smokeTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import base.BasePage.ScrollType;
import pageObjects.HomePagePO;
import pageObjects.ScrollingPO;
import pageObjects.ScrollingPO.ScrollType;

public class Scrolling extends BaseTest {
	ScrollingPO scrollingPO;
	HomePagePO homePagePO;
	
	@BeforeMethod
	public void setup() {
		scrollingPO= new ScrollingPO(driver);
		homePagePO=new HomePagePO(driver);
		
	}

	@Test
	public void scollToZone1() {
		scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Scrolling"));
		
		scrollingPO.moveAndClick(driver, scrollingPO.getZone1Header());
//		scrollingPO.scrollIntoView(scrollingPO.getZone1Header(), ScrollType.SCROLL_INTO_ELEMENT);
		AssertJUnit.assertTrue(scrollingPO.isDisplay(scrollingPO.getZone1HeaderAfterHover()));
		scrollingPO.closeCurrentAndReturn();
	}
	
	@Test
	public void scollToZone2() {
		scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Scrolling"));
		scrollingPO.moveAndClick(driver, scrollingPO.getZone2Header());
		AssertJUnit.assertTrue(scrollingPO.getZone2Header().getText().equalsIgnoreCase("1 Entries"));
		scrollingPO.closeCurrentAndReturn();
	}

	@Test
	public void scollToZone3() {
		scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Scrolling"));
		scrollingPO.moveAndClick(driver, scrollingPO.getZone3Header());
		AssertJUnit.assertTrue(scrollingPO.getZone3Header().getText().equalsIgnoreCase("1 Entries"));
		scrollingPO.closeCurrentAndReturn();
	}
	

	@Test
	public void scollToZone4() {
		scrollingPO.switchToNewWindow(homePagePO.getScrollingHeader());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | Scrolling"));
		scrollingPO.moveAndClick(driver, scrollingPO.getZone4Header());
		AssertJUnit.assertTrue(scrollingPO.getZone4Header().getText().contains("X") 
			    && scrollingPO.getZone4Header().getText().contains("Y"));
		scrollingPO.closeCurrentAndReturn();
	}

	
}
