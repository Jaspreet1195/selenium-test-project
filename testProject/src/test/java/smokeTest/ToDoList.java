package smokeTest;

import base.BaseTest;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePagePO;
import pageObjects.ToDoListPO;

public class ToDoList extends BaseTest {
  private ToDoListPO toDoListPO;
  private HomePagePO homePagePO;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    toDoListPO = new ToDoListPO(getDriver());
    homePagePO = new HomePagePO(getDriver());
  }

  @Test(groups = "ui")
  public void addIntoToDoList() {
    homePagePO.switchToNewWindow(homePagePO.getToDoListHeader());
    Assert.assertTrue(getDriver().getTitle().equalsIgnoreCase("WebDriver | To Do List"));

    String taskName = "task-" + System.currentTimeMillis();
    toDoListPO.sendKeysPressEnter(toDoListPO.getAddInput(), taskName);
    Assert.assertTrue(isItemPresent(taskName), "Newly added task should be present in the list.");
  }

  @Test(groups = "ui")
  public void deleteFromList() {
    homePagePO.switchToNewWindow(homePagePO.getToDoListHeader());
    Assert.assertTrue(getDriver().getTitle().equalsIgnoreCase("WebDriver | To Do List"));

    String taskName = "cleanup-" + System.currentTimeMillis();
    toDoListPO.sendKeysPressEnter(toDoListPO.getAddInput(), taskName);
    Assert.assertTrue(isItemPresent(taskName), "Task must exist before deletion");

    int index = findItemIndex(taskName);
    Assert.assertTrue(index >= 0, "Task index should be found");
    toDoListPO.moveAndClick(getDriver(), toDoListPO.getDeleteButton(index + 1));

    Assert.assertFalse(isItemPresent(taskName), "Task should be removed after deletion");
  }

  private boolean isItemPresent(String expectedText) {
    return toDoListPO.getItems().stream()
        .anyMatch(item -> item.getText().equalsIgnoreCase(expectedText));
  }

  private int findItemIndex(String expectedText) {
    List<WebElement> items = toDoListPO.getItems();
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getText().equalsIgnoreCase(expectedText)) {
        return i;
      }
    }
    return -1;
  }
}
