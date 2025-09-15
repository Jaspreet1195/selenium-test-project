package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;
//import base.BasePage.ScrollType;

public class ScrollingPO extends BasePage{
	
	public ScrollingPO(WebDriver driver){
		super(driver);
	}
	
	
	// ===== H1 Elements =====

    @FindBy(xpath = "//div[@id='zone1']//h1[contains(text(),'Scroll to me first!')]")
    private WebElement zone1Header;
    
    
    @FindBy(xpath = "//div[contains(text(),'Well done for scrolling to me!')]")
    private WebElement zone1HeaderAfterHover;

    @FindBy(xpath = "//div[@id='zone2']/h1[@id='zone2-entries']")
    private WebElement zone2Header;

//    @FindBy(xpath = "//div[@id='zone2']/h1[@id='zone2-entries']")
//    private WebElement zone2HeaderAfterHover;
    
    @FindBy(xpath = "//div[@id='zone3']/h1[@id='zone3-entries']")
    private WebElement zone3Header;

    @FindBy(xpath = "//div[@id='zone4']/h1[contains(text(),'Dont forget to scroll to me!')]")
    private WebElement zone4Header;


    // ===== Getters =====
    public WebElement getZone1Header() {
        return zone1Header;
    }
    
    public WebElement getZone1HeaderAfterHover() {
        return zone1HeaderAfterHover;
    }
    
    

    public WebElement getZone2Header() {
        return zone2Header;
    }
    
//    public WebElement getZone2HeaderAfterHover() {
//        return zone2HeaderAfterHover;
//    }

    public WebElement getZone3Header() {
        return zone3Header;
    }

    public WebElement getZone4Header() {
        return zone4Header;
    }
    
	public enum ScrollType{
	    SCROLL_INTO_ELEMENT {
	        @Override
	        public void scroll(WebDriver driver, WebElement element) {
//	        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        	try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	Actions actions = new Actions(driver);
	        	actions.moveToElement(element).perform();

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


}
