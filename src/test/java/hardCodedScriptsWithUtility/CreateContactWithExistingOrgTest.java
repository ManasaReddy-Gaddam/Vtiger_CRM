package hardCodedScriptsWithUtility;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.DataType;
import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateContactWithExistingOrgTest {

	public static void main(String[] args) {
		PropertiesUtility propertyUtil = new PropertiesUtility();
		WebDriverUtility webUtility = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelUtility excel = new ExcelUtility();
		
		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		WebDriver driver = webUtility.launchBrowser(propertyUtil.readFromProperties("browser"));
		webUtility.maximizeBrowser();
		webUtility.navigateToApp(propertyUtil.readFromProperties("url"));
		
		Long time = (Long) jUtil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"),DataType.LONG);
		webUtility.waitTillElementFound(time);
		

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driver.quit();

		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			webUtility.quitAllWindows();

		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action=index')]")).click();
		if (driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page Displayed");
		else
			webUtility.quitAllWindows();

		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (pageHeader.isDisplayed())
			System.out.println("Creating New Contact Page is Displayed");
		else
			webUtility.quitAllWindows();

		driver.findElement(By.name("lastname")).sendKeys("XYZ");
		driver.findElement(By.xpath("//img[contains(@onclick,'Accounts')]")).click();

		String parentID = driver.getWindowHandle();
		Set<String> windowIDs = driver.getWindowHandles();

		for (String id : windowIDs) {
			driver.switchTo().window(id);
		}
		
		driver.findElement(By.xpath("//a[text()='ABC']")).click();
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();

		String newContactPageHeader = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
		if (newContactPageHeader.contains("XYZ"))
			System.out.println("Contact created successfully");
		else
			webUtility.quitAllWindows();

		webUtility.switchToChildWindow("Account");
		driver.findElement(By.xpath("//input[@name='Delete']")).click();
//		driver.switchTo().alert().accept();
		
		webUtility.switchToChildWindow("Contacts");

		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page is Displayed");
		else
			webUtility.quitAllWindows();

		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(adminWidget).perform();

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		excel.closeExcel();
		webUtility.quitAllWindows();
	}

}