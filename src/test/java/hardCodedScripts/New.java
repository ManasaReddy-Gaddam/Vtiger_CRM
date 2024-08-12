package hardCodedScripts;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class New {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/selenium_.xlsx");
		//1.open workbook
		Workbook wb = WorkbookFactory.create(fis);
		//2.open sheet (control to sheet)
//		Sheet sheet = wb.getSheet("Sheet1");
//		//3.control to row
//		Row row = sheet.getRow(1);
//		//4.Control to cell
//		Cell cell = row.getCell(1);
//		//5.Fetch the value
//		System.out.println(cell.getStringCellValue());
		
		//Method Chaining
		String print = wb.getSheet("sheet1").getRow(3).getCell(1).getStringCellValue();
		System.out.println(print);
		wb.close();
	}
}
