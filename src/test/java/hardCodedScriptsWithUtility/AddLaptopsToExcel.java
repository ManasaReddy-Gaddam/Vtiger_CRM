package hardCodedScriptsWithUtility;

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

public class AddLaptopsToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Hp laptops");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).submit(); 
		List<WebElement> laptops = driver.findElements(By.xpath("//span[contains(@class,'a-size-base-plus a-color-base a-text-normal')]"));
		System.out.println(laptops.size());
		FileInputStream fis = new FileInputStream("./src/test/resources/selenium_.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet2");
		
		sheet.createRow(0).createCell(0).setCellValue("Laptop name");
		sheet.getRow(0).createCell(1).setCellValue("Price");
		for(int i = 0; i < laptops.size();i++) {
			String name = laptops.get(i).getText();
//			String price = driver.findElement(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']/ancestor::div[@class='a-section a-spacing-base']/descendant::span[@class='a-price-whole']")).getText();

			String price = driver.findElement(By.xpath("//span[normalize-space(text())='a-size-base-plus a-color-base a-text-normal']/ancestor::div[@class='a-section a-spacing-base']/descendant::span[@class='a-price-whole']")).getText();
			sheet.createRow(i+1).createCell(0).setCellValue(name);
			sheet.getRow(i+1).createCell(1).setCellValue(price);
		}
		FileOutputStream fos = new FileOutputStream("./src/test/resources/selenium_.xlsx");
		wb.write(fos);
		wb.close();
	

	}

}
