package hardCodedScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AddDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/selenium_.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		
		sheet.createRow(5).createCell(0).setCellValue("Subject");
		sheet.getRow(5).createCell(1).setCellValue("Selenium");
		sheet.createRow(6).createCell(0).setCellValue("Login");
		sheet.getRow(6).createCell(1).setCellValue("manasa@123");
		sheet.createRow(7).createCell(0).setCellValue("Profile");
		sheet.getRow(7).createCell(1).setCellValue("manasa");
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/selenium_.xlsx");
		
		wb.write(fos);
		wb.close();
	}
}

