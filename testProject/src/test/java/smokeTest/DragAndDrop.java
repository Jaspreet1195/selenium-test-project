package smokeTest;

import org.testng.annotations.Test;

import baseTest.BaseTest;

import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DragAndDropPO;
import pageObjects.DropDowCheckboxRadioButtonPO;
import pageObjects.HomePagePO;

public class DragAndDrop extends BaseTest{
	DragAndDropPO dragAndDropPO;
	HomePagePO homePagePO;
	

	@BeforeMethod
	public void setup() {
		dragAndDropPO = new DragAndDropPO(getDriver());
		homePagePO = new HomePagePO(getDriver());
	}
	
	
	@Test
	public void dragAndDropTest() {
		dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
		AssertJUnit.assertTrue(dragAndDropPO.getTitle().trim().equalsIgnoreCase("WebDriver | Actions"));
		dragAndDropPO.dragAndDrop(dragAndDropPO.getDraggableText(),dragAndDropPO.getDroppableText());
		AssertJUnit.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getDroppedText()));
	}
	
	@Test
	public void doubleClick() {
		dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
		AssertJUnit.assertTrue(dragAndDropPO.getTitle().trim().equalsIgnoreCase("WebDriver | Actions"));
		dragAndDropPO.doubleClickUsingAction(dragAndDropPO.getDoubleClickText());
		AssertJUnit.assertTrue(dragAndDropPO.getdoubleClickToGetAttribute().getAttribute("class").equalsIgnoreCase("div-double-click double"));
	}
	
	@Test
	public void clickAndHold() {
		dragAndDropPO.switchToNewWindow(homePagePO.getActionsHeader());
		AssertJUnit.assertTrue(dragAndDropPO.getTitle().trim().equalsIgnoreCase("WebDriver | Actions"));
		dragAndDropPO.releaseAndHoldUsingAction(dragAndDropPO.getClickBoxText());	
		AssertJUnit.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getClickBoxTextWhileHoldingButton()));
		dragAndDropPO.releaseButton(dragAndDropPO.getClickBoxText());
		AssertJUnit.assertTrue(dragAndDropPO.isDisplay(dragAndDropPO.getClickBoxTextAfterReleased()));
	}

}
