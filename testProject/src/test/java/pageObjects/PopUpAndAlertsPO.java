package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.BasePage;

public class PopUpAndAlertsPO extends BasePage {

    // ===== Constructor =====
    public PopUpAndAlertsPO(WebDriver driver) {
        super(driver);
    }
    
    // ===== CLICK ME! Buttons tied with H2 headers =====
    @FindBy(xpath = "//h2[contains(text(),'JavaScript Alert')]/following::span[@id='button1'][1]")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//h2[contains(text(),'Modal Popup')]/following::span[@id='button2'][1]")
    private WebElement modalPopupButton;

    @FindBy(xpath = "//h2[contains(text(),'Ajax Loader')]/following::span[@id='button3'][1]")
    private WebElement ajaxLoaderButton;

    @FindBy(xpath = "//h2[contains(text(),'JavaScript Confirm Box')]/following::span[@id='button4'][1]")
    private WebElement jsConfirmButton;
    
    @FindBy(xpath = "//p[@id='confirm-alert-text' and contains(text(),'You pressed OK!')]")
    private WebElement confirmAlertBoxText;
    
 // Button that appears after AJAX loader
    @FindBy(xpath = "//span[@data-target = '#myModalClick' and @id='button1']")
    private WebElement clickMeButton;
    
    @FindBy(xpath = "//h4[contains(text(),'Well Done For Waiting....!!!']")
    private WebElement ajaxLoaderWaitingAlert;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeButton;

    // ====== Getters ======
    public WebElement getJsAlertButton() {
        return jsAlertButton;
    }

    public WebElement getModalPopupButton() {
        return modalPopupButton;
    }

    public WebElement getAjaxLoaderButton() {
        return ajaxLoaderButton;
    }

    public WebElement getJsConfirmButton() {
        return jsConfirmButton;
    }
    
    public WebElement getConfirmAlertBoxText() {
        return confirmAlertBoxText;
    }
    
    public WebElement getClickMeButton() {
        return clickMeButton;
    }
    
    public  WebElement getAjaxLoaderWaitingAlert() {
    	return ajaxLoaderWaitingAlert;
    }
    
    public WebElement getCloseButton() {
    	return closeButton;
    }
    
}