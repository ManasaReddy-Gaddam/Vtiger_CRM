package hardCodedScriptsWithUtility;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateLeadAndDuplicate {
	public static void main(String[] args) {

		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();

		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);

		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("chrome"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));

		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		// validate the home page
		if (driver.getTitle().contains("Home - vtiger")) {
			System.out.println("Home page displayed :) ");
		} else {
			System.out.println("Home page not displayed :( ");
		}
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'action=index')]")).click();

		// validate the home page
		if (driver.getTitle().contains("Leads - vtiger")) {
			System.out.println("Leads page displayed :) ");
		} else {
			System.out.println("Leads page not displayed :( ");
		}
		driver.findElement(By.xpath("//img[contains(@src,'btnL3Add.gif')]")).click();

		// Validate the creating lead page
		String createLead = driver.findElement(By.className("lvtHeaderText")).getText();
		if (createLead.contains("Creating New Lead")) {
			System.out.println("Creating New Lead page displayed :) ");
		} else {
			System.out.println("Creating New Lead page not displayed :( ");
		}

		Map<String, String> map = excel.readFromExcel("LeadsTestData", "Create and Duplicate Lead");
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(map.get("First Name Salutation"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(map.get("Last Name"));
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Company");
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		String leadName = driver.findElement(By.className("dvHeaderText")).getText();
		String dupLeadName = driver.findElement(By.className("dvHeaderText")).getText();
		if (leadName.contains(map.get("Last Name"))) {
			System.out.println("Lead is created :)");
		} else {
			System.out.println("lead is not created :(");
		}
		driver.findElement(By.xpath("//input[contains(@title,'Duplicate')]")).click();
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// validate duplicate lead created
		if (leadName.contains(dupLeadName)) {
			System.out.println("Duplicate lead is created :)");
			excel.writeToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass");
		} else {
			System.out.println("Duplicate is not created :(");
			excel.writeToExcel("LeadsTestData", "Create and Duplicate Lead", "Fail");
		}
		excel.saveExcel(IConstantPath.EXCEL_PATH);
		// user logged out
		WebElement adminIcon = driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
//		driverUtil.mouseHover(adminIcon);
		driverUtil.mouseHover(adminIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		excel.closeWorkbook();

		driverUtil.quitAllWindows();

	}
}
