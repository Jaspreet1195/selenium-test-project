package smokeTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePagePO;
import pageObjects.ScrollingPO;
import pageObjects.ToDoListPO;

public class ToDoList extends BaseTest {
	ToDoListPO toDoListPO;
	HomePagePO homePagePO;
	
	@BeforeMethod
	public void setup() {
		toDoListPO= new ToDoListPO(driver);
		homePagePO=new HomePagePO(driver);
		
	} 
	
	@Test
	public void addIntoToDoList() {
		homePagePO.switchToNewWindow(homePagePO.getToDoListHeader());
		System.out.println(driver.getTitle());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | To Do List"));
		toDoListPO.sendKeysPressEnter(toDoListPO.getAddInput(),"jaspreet");
		boolean found = false;
		List<WebElement> items = toDoListPO.getItems();
		for(WebElement item : items) {
			if (item.getText().equals("jaspreet")) {
		        found = true;
		        break; // stop loop once found
		    }

		}

		// Fail test if not found
		Assert.assertTrue(found, "Text jaspreet was NOT found in the todo list!");

	}
	
	
	@Test	
	public void deleteFromList() throws InterruptedException {
		homePagePO.switchToNewWindow(homePagePO.getToDoListHeader());
		System.out.println(driver.getTitle());
		AssertJUnit.assertTrue(driver.getTitle().equalsIgnoreCase("WebDriver | To Do List"));
		
		boolean found = false;
		List<WebElement> items = toDoListPO.getItems();
		int index = -1;  // default = not found

		for (int i = 0; i <= items.size(); i++) {
			System.out.println("index outside if loop is: "+ i);
		    if (items.get(i).getText().equals("Practice magic")) {
		    	System.out.println("index is: "+ i);
		        index = i+ 1;
		        break; // stop once found
		    }
		}
	
		toDoListPO.moveAndClick(driver, toDoListPO.getDeleteButton(index));
	}

}
