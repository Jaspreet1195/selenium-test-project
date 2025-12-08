package smokeTest;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.PropertyReader;
import pageObjects.ButtonClicksPO;

public class ButtonClickTest extends BaseTest {
	ButtonClicksPO buttonClicksPO ;
	
	@BeforeMethod
	public void setup() {
	 buttonClicksPO = new ButtonClicksPO(getDriver());
	}

	@Test
	public void webeElemntClick() {
		buttonClicksPO.buttonClicks();
		buttonClicksPO.javascriptClick();
		buttonClicksPO.actionMoveClick();
	}
}
