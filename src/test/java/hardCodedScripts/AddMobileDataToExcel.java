// open flipkart.com, search for mobiles
//print mobilename and price in excel
package hardCodedScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddMobileDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
//		driver.findElement(By.className("_30XB9F")).click();
		driver.findElement(By.name("q")).sendKeys("mobiles");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		List<WebElement> mobileNames = driver.findElements(By.className("KzDlHZ"));
//		System.out.println(mobileNames);

		FileInputStream fis = new FileInputStream("./src/test/resources/selenium_.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet4");
		sheet.createRow(0).createCell(0).setCellValue("Mobile name");
		sheet.getRow(0).createCell(1).setCellValue("Price");
		for (int i = 0; i < mobileNames.size(); i++) {
			String name = mobileNames.get(i).getText();
			String price = driver.findElement(By.xpath("//div[text()='"+name+"']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']")).getText();
			sheet.createRow(i+1).createCell(0).setCellValue(name);
			sheet.getRow(i+1).createCell(1).setCellValue(price);
		}
		FileOutputStream fos = new FileOutputStream("./src/test/resources/selenium_.xlsx");
		wb.write(fos);
		wb.close();
	
	}
}
//ctrl + shift + f = to format the code