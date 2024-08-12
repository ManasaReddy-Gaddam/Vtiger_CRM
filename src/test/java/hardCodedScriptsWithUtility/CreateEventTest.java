package hardCodedScriptsWithUtility;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.DataType;
import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateEventTest {

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

		Long time = (Long) jUtil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), DataType.LONG);
		webUtility.waitTillElementFound(time);

		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			webUtility.quitAllWindows();

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			webUtility.quitAllWindows();

		WebElement quickCreateDD = driver.findElement(By.id("qccombo"));
		Select select = new Select(quickCreateDD);
		select.selectByValue("Events");

		driver.findElement(By.name("subject")).sendKeys("Exhibition at Novotel");
		driver.findElement(By.id("jscal_trigger_date_start")).click();

		int reqYear = 2026;
		String reqDate = "10";
		String reqMonth = "November";

		String currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		String[] str = currentMonthYear.split(", ");
		int currentYear = Integer.parseInt(str[1]);

		while (currentYear < reqYear) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']"))
					.click();

			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = currentMonthYear.split(", ");
			currentYear = Integer.parseInt(str[1]);
		}

		int currentMonth = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
				.get(ChronoField.MONTH_OF_YEAR);
		int requiredMonth = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(reqMonth)
				.get(ChronoField.MONTH_OF_YEAR);

		System.out.println(currentMonth);
		System.out.println(requiredMonth);

		while (currentMonth < requiredMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='>'"))
					.click();
			currentMonth = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
					.get(ChronoField.MONTH_OF_YEAR);
		}
	}
}