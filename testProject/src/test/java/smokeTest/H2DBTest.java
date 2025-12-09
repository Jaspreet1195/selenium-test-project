package smokeTest;

import base.DatabaseManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class H2DBTest {

  @Test(groups = "integration")
  public void testDB() throws SQLException {
    try (DatabaseManager db = new DatabaseManager()) {
      db.createTable(
          "CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(255), role VARCHAR(255))");

      Assert.assertEquals(
          db.insert(
              "INSERT INTO employees (id, name, role) VALUES (?, ?, ?)", 1, "Alice", "Developer"),
          1);
      Assert.assertEquals(
          db.insert("INSERT INTO employees (id, name, role) VALUES (?, ?, ?)", 2, "Bob", "Tester"),
          1);

      List<Map<String, Object>> employees = db.read("SELECT * FROM employees");
      Assert.assertEquals(employees.size(), 2, "Two employees should be present after inserts");

      Assert.assertEquals(
          db.update("UPDATE employees SET role = ? WHERE id = ?", "Senior Developer", 1),
          1,
          "Exactly one row should update");

      List<Map<String, Object>> updated = db.read("SELECT role FROM employees WHERE id = ?", 1);
      Assert.assertEquals(updated.get(0).get("ROLE"), "Senior Developer");

      Assert.assertEquals(
          db.delete("DELETE FROM employees WHERE id = ?", 2),
          1,
          "Deleting employee id=2 should affect one row");

      List<Map<String, Object>> finalResult = db.read("SELECT * FROM employees");
      Assert.assertEquals(finalResult.size(), 1, "Only one employee should remain after delete");
    }
  }
}
