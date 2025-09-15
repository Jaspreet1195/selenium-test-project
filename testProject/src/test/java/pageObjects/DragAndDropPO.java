package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class DragAndDropPO extends BasePage {

	public DragAndDropPO(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='draggable']//p//b[contains(text(),'DRAG ME TO MY TARGET')]")
	private WebElement draggableText;

	@FindBy(xpath = "//div[@id='droppable']//p//b[contains(text(),'DROP HERE')]")
	private WebElement droppableText;

	@FindBy(xpath = "//div[@id='droppable']//p//b[contains(text(),'Dropped')]")
	private WebElement droppedText;
	
	@FindBy(xpath = "//div[@id='double-click']")
	private WebElement doubleClickToGetAttribute;
	
//	@FindBy(xpath = "//div[@id='double-click']//h2[contains(text(),'Double Click Me!')]")
	@FindBy(xpath = "//div[@id='double-click']")
	private WebElement doubleClickText;
	// 5. Hover Buttons
    @FindBy(xpath = "//div[@id='div-hover']//button[contains(text(),'Hover Over Me First!')]")
    private WebElement hoverButton1;

    @FindBy(xpath = "//div[@id='div-hover']//button[contains(text(),'Hover Over Me Second!')]")
    private WebElement hoverButton2;

    @FindBy(xpath = "//div[@id='div-hover']//button[contains(text(),'Hover Over Me Third!')]")
    private WebElement hoverButton3;
    
//    @FindBy(xpath = "//div[@id='click-box']//p[contains(text(),'Click and Hold!')]")
    @FindBy(xpath = "//div[@id='click-box']")
    private WebElement clickBoxText;
    
    @FindBy(xpath = "//div[@id='click-box' and contains(text(),'keep holding that')]")
    private WebElement clickBoxTextWhileHoldingButton;
    
    @FindBy(xpath = "//div[@id='click-box' and contains(text(),'Dont release me')]")
    private WebElement clickBoxTextAfterReleased;
    
    @FindBy(xpath = "//div[@id='div-hover']//a[contains(text(),'Link 1')]")
    private WebElement dropdownLink1;


	public WebElement getDraggableText() {
		return draggableText;
	}
	
	public WebElement getDroppableText() {
		return droppableText;
	}
	public WebElement getDroppedText() {
		return droppedText;
	}
	
	public WebElement getdoubleClickToGetAttribute() {
		return doubleClickToGetAttribute;
	}

	public WebElement getDoubleClickText() {
		return doubleClickText;
	}
	
	public WebElement getClickBoxText() { return clickBoxText; }

    public WebElement getHoverButton1() { return hoverButton1; }
    public WebElement getHoverButton2() { return hoverButton2; }
    public WebElement getHoverButton3() { return hoverButton3; }
    public WebElement getDropdownLink1() { return dropdownLink1; }
    public WebElement getClickBoxTextWhileHoldingButton() { return clickBoxTextWhileHoldingButton; }
    public WebElement getClickBoxTextAfterReleased() { return clickBoxTextAfterReleased; }
}
