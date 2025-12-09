package base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements AutoCloseable {

  private final Path testDataFilePath;
  private final XSSFWorkbook workbook;

  public ExcelReader() throws IOException {
    this("QATestData.xlsx");
  }

  public ExcelReader(String relativePath) throws IOException {
    this.testDataFilePath = Paths.get(relativePath);
    try (InputStream fis = Files.newInputStream(this.testDataFilePath)) {
      this.workbook = new XSSFWorkbook(fis);
    }
  }

  // Helper method to safely get cell value as String
  private String getCellValue(Cell cell) {
    if (cell == null) return "";
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue().trim();
      case NUMERIC:
        // remove ".0" if it's an integer
        double num = cell.getNumericCellValue();
        if (num == (int) num) {
          return String.valueOf((int) num);
        }
        return String.valueOf(num);
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case BLANK:
        return "";
      default:
        return cell.toString().trim();
    }
  }

  // read complete row
  public HashMap<String, List<String>> readExcelData(String sheetName, String testCase) {
    HashMap<String, List<String>> data = new HashMap<>();
    XSSFSheet sheet = workbook.getSheet(sheetName);

    if (sheet == null) {
      throw new IllegalArgumentException("Sheet not found: " + sheetName);
    }

    Row firstHeaderRow = sheet.getRow(0);
    if (firstHeaderRow == null) {
      throw new IllegalStateException("No header row found in sheet: " + sheetName);
    }

    boolean testCaseFound = false;
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      if (row == null) continue;

      String rowKey = getCellValue(row.getCell(0));
      if (rowKey.equalsIgnoreCase(testCase)) {
        testCaseFound = true;
        for (int j = 0; j < firstHeaderRow.getLastCellNum(); j++) {
          String header = getCellValue(firstHeaderRow.getCell(j));
          String cellValue = getCellValue(row.getCell(j));

          if (cellValue == null || cellValue.isEmpty()) {
            data.put(header, null);
          } else {
            String[] values = cellValue.split(",");
            data.put(header, Arrays.asList(values));
          }
        }
        break;
      }
    }

    if (!testCaseFound) {
      throw new IllegalArgumentException("Test case not found in sheet: " + testCase);
    }

    return data;
  }

  @Override
  public void close() throws IOException {
    workbook.close();
  }
}
