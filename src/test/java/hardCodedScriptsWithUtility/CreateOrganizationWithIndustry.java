package hardCodedScriptsWithUtility;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.DataType;
import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws InterruptedException {
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();
		
		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));
		
		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), 
																				DataType.LONG);
		driverUtil.waitTillElementFound(time);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();
		
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();
		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action=index')]")).click();
		if (driver.getTitle().contains("Organization"))
			System.out.println("Contacts Page Displayed");
		else
			driverUtil.quitAllWindows();
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		//validate the Organization page 
		if(driver.getTitle().contains("Organizations - vtiger")) {
			System.out.println("Organizations page displayed :) ");
		}else {
			System.out.println("Organizations page not displayed :( ");
		}
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String orgCreate = driver.findElement(By.className("lvtHeaderText")).getText();
		//validate the creating Organization page
		if(orgCreate.contains("Creating New Organization")) {
			System.out.println("Creating organization page displayed :) ");
		}else {
			System.out.println("Creating Organization page not displayed :( ");
		}
		
		Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization With Industry And Type");
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(map.get("Organization Name"));
		
		WebElement industryDropDown =  driver.findElement(By.xpath("//select[@name='industry']"));
		driverUtil.handleDropdown(industryDropDown, map.get("Industry"));
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		driverUtil.handleDropdown(type, map.get("Type"));
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();
		String orgHeader = driver.findElement(By.className("dvHeaderText")).getText();
		//validate the organization data created or not
		if(orgHeader.contains("")) {
			System.out.println("Created data organization page displayed :) ");
		}else {
			System.out.println("Created data Organization page not displayed :( ");
		}
		//delete the organization data
		driver.findElement(By.xpath("//input[@accesskey='D']")).click();
		
		driverUtil.handleAlert("ok");
		//driver.switchTo().alert().accept();
		//validate the organization data deleted or not
		if(driver.getTitle().contains("Organizations - vtiger")) {
			System.out.println("Organizations page displayed :) ");
			excel.writeToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass");
		}else {
			System.out.println("Organizations page not displayed :( ");
			excel.writeToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail");
		}
		excel.saveExcel(IConstantPath.EXCEL_PATH);
		//signout user
		WebElement adminicon = driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
		driverUtil.mouseHover(adminicon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excel.closeWorkbook();
		
		driverUtil.quitAllWindows();
		
		
	}
	
}
