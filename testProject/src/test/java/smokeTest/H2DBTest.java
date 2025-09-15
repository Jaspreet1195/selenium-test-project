package smokeTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.DatabaseManager;
import pageObjects.DatePickerPO;
import pageObjects.HomePagePO;

public class H2DBTest {
	H2DBTest h2DBTest;
	
	
	@Test
	public void testDB() throws Exception {
        DatabaseManager db = new DatabaseManager();

        try {
            // Create table
            db.createTable("CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(255), role VARCHAR(255))");

            // Insert
            db.insert("INSERT INTO employees (id, name, role) VALUES (?, ?, ?)", 1, "Alice", "Developer");
            db.insert("INSERT INTO employees (id, name, role) VALUES (?, ?, ?)", 2, "Bob", "Tester");

            // Read
            db.read("SELECT * FROM employees");

            // Update
            db.update("UPDATE employees SET role = ? WHERE id = ?", "Senior Developer", 1);

            // Read again
            db.read("SELECT * FROM employees");

            // Delete
            db.delete("DELETE FROM employees WHERE id = ?", 2);

            // Final Read
            db.read("SELECT * FROM employees");

        }  finally {
            db.close();
        }
    }

}
