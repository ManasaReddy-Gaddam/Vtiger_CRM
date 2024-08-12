package hardCodedScriptsWithUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class New1 {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/selenium_.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		DataFormatter df = new DataFormatter();

		String print = df.formatCellValue(wb.getSheet("Sheet1").getRow(4).getCell(1));
//		String print = wb.getSheet("sheet1").getRow(3).getCell(1).getStringCellValue();
		System.out.println(print);
		wb.close();
	}

}
