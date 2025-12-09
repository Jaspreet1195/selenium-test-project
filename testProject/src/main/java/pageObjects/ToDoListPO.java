package pageObjects;

import base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToDoListPO extends BasePage {

  public ToDoListPO(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//h1[contains(text(),'TO-DO LIST ')]")
  private WebElement toDoList;

  // i tag → plus icon
  @FindBy(id = "plus-icon")
  private WebElement plusIcon;

  @FindBy(tagName = "li")
  private List<WebElement> items;

  @FindBy(xpath = "//input[@type='text']")
  private WebElement addInput;

  // ✅ Getter method for todoList
  public WebElement getTodoList() {
    return toDoList;
  }

  // ✅ Getter method for plusIcon
  public WebElement getPlusIcon() {
    return plusIcon;
  }

  public List<WebElement> getItems() {
    return items;
  }

  public WebElement getAddInput() {
    return addInput;
  }

  public WebElement getDeleteButton(int i) {
    String deleteButton = "//li[" + i + "]/span/i[@class='fa fa-trash']";
    driver.findElement(By.xpath(deleteButton));
    return driver.findElement(By.xpath(deleteButton));
  }
}
