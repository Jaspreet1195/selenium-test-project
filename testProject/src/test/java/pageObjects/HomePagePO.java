package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePagePO  extends  BasePage{

    WebDriver driver;

 // Constructor initializes PageFactory
 	public HomePagePO(WebDriver driver) {
 		super(driver);
 	}

    // ======= Section Headers (h1 tags) =======

    @FindBy(xpath = "//a[@id='contact-us']//h1")
    private WebElement contactUsHeader;

    @FindBy(xpath = "//a[@id='login-portal']//h1")
    private WebElement loginPortalHeader;

    @FindBy(xpath = "//a[@id='button-clicks']//h1")
    private WebElement buttonClicksHeader;

    @FindBy(xpath = "//a[@id='to-do-list']//h1")
    private WebElement toDoListHeader;

    @FindBy(xpath = "//a[@id='page-object-model']//h1")
    private WebElement pageObjectModelHeader;

    @FindBy(xpath = "//a[@id='dropdown-checkboxes-radiobuttons']//h1")
    private WebElement dropdownCheckboxesHeader;

    @FindBy(xpath = "//a[@id='ajax-loader']//h1")
    private WebElement ajaxLoaderHeader;

    @FindBy(xpath = "//a[@id='actions']//h1")
    private WebElement actionsHeader;

    @FindBy(xpath = "//a[@id='scrolling-around']//h1")
    private WebElement scrollingHeader;

    @FindBy(xpath = "//a[@id='popup-alerts']//h1")
    private WebElement popupAlertsHeader;

    @FindBy(xpath = "//a[@id='iframe']//h1")
    private WebElement iframeHeader;

    @FindBy(xpath = "//a[@id='hidden-elements']//h1")
    private WebElement hiddenElementsHeader;

    @FindBy(xpath = "//a[@id='data-table']//h1")
    private WebElement dataTableHeader;

    @FindBy(xpath = "//a[@id='autocomplete-textfield']//h1")
    private WebElement autocompleteHeader;

    @FindBy(xpath = "//a[@id='file-upload']//h1")
    private WebElement fileUploadHeader;

    @FindBy(xpath = "//a[@id='datepicker']//h1")
    private WebElement datepickerHeader;

 // ======= Getters (Return WebElement directly) =======

    public WebElement getContactUsHeader() { return contactUsHeader; }

    public WebElement getLoginPortalHeader() { return loginPortalHeader; }

    public WebElement getButtonClicksHeader() { return buttonClicksHeader; }

    public WebElement getToDoListHeader() { return toDoListHeader; }

    public WebElement getPageObjectModelHeader() { return pageObjectModelHeader; }

    public WebElement getDropdownCheckboxesHeader() { return dropdownCheckboxesHeader; }

    public WebElement getAjaxLoaderHeader() { return ajaxLoaderHeader; }

    public WebElement getActionsHeader() { return actionsHeader; }

    public WebElement getScrollingHeader() { return scrollingHeader; }

    public WebElement getPopupAlertsHeader() { return popupAlertsHeader; }

    public WebElement getIframeHeader() { return iframeHeader; }

    public WebElement getHiddenElementsHeader() { return hiddenElementsHeader; }

    public WebElement getDataTableHeader() { return dataTableHeader; }

    public WebElement getAutocompleteHeader() { return autocompleteHeader; }

    public WebElement getFileUploadHeader() { return fileUploadHeader; }

    public WebElement getDatepickerHeader() { return datepickerHeader; }
}
