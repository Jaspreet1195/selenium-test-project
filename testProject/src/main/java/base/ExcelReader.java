package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	String testDataFilePath = "QATestData.xlsx";
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExcelReader() throws IOException {
		fis = new FileInputStream(testDataFilePath);
		workbook = new XSSFWorkbook(fis);
	}

	// Helper method to safely get cell value as String
	private String getCellValue(Cell cell) {
		if (cell == null)
			return "";
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

	//read complete row
	public HashMap<String, List<String>> readExcelData(String sheetName, String testCase) {
		HashMap<String, List<String>> data = new HashMap<>();
		sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			System.out.println("Sheet not found: " + sheetName);
			return data;
		}

		Row firstHeaderRow = sheet.getRow(0);
		if (firstHeaderRow == null) {
			System.out.println("No header row found in sheet: " + sheetName);
			return data;
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;

			String rowKey = getCellValue(row.getCell(0));
			if (rowKey.equalsIgnoreCase(testCase)) {
				for (int j = 0; j < firstHeaderRow.getLastCellNum(); j++) {
					String header = getCellValue(firstHeaderRow.getCell(j));
					String cellValue = getCellValue(row.getCell(j));

					if (cellValue != null) {
						if (cellValue.isEmpty()) {
							data.put(header, null);
						} else {
							String[] values = cellValue.split(",");
							data.put(header, Arrays.asList(values));
						}
					} else {
						data.put(header, null);
					}
				}
				break;
			}
		}
		return data;
	}
	
	//read particular cell
	
}
