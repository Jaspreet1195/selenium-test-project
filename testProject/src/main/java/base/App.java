package base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/** Hello world! */
public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

    try (ExcelReader ex = new ExcelReader()) {
      hm = ex.readExcelData("regressionTest", "Correct");
    } catch (IOException e) {
      System.out.println("Failed to read excel test data.");
      e.printStackTrace();
    }

    //		// 1. Print the entire map
    System.out.println("Complete Map:");
    System.out.println(hm);
  }
}
