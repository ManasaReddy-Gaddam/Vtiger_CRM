package POMImplimentation;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import POM.CreateToDoPage;
import POM.EventInformationPage;
import POM.HomePage;
import POM.LoginPage;
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
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		CreateToDoPage createToDo = new CreateToDoPage(driver);
		EventInformationPage eventInfo = new EventInformationPage(driver);
		
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			webUtility.quitAllWindows();
		login.loginToVtiger(propertyUtil.readFromProperties("username"),propertyUtil.readFromProperties("password"));
	
		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			webUtility.quitAllWindows();

		Map<String, String> map = excel.readFromExcel("EventTestDate", "Create new Event");
		home.selectFromQuickCreateDD(webUtility, map.get("Quick Create"));
		jUtil.waiting(3000);
		
		String subject = map.get("subject") + jUtil.generateRandomNum(100);
		createToDo.setSubject(subject);
		createToDo.clickStartDateWidget();
		createToDo.datePicker(jUtil, webUtility, map.get("start date"));
		jUtil.waiting(2000);
		createToDo.clickDueDateWidget();
		createToDo.datePicker(jUtil, webUtility, map.get("Due Date"));
		createToDo.clickSaveBTN();
		
		if(eventInfo.getPageHeader().contains(subject)) {
			System.out.println("Event created");
			excel.writeToExcel("Event test data", "Create new Events", "Pass");
		} else
		{
			System.out.println("Event not created");
			excel.writeToExcel("Event test data", "Create new Events", "Fail");
		}
		eventInfo.clickDeleteBTN();
		webUtility.handleAlert("ok");
		
		excel.saveExcel(IConstantPath.EXCEL_PATH);
		home.signOutOfVtiger(webUtility);
		excel.closeExcel();
		webUtility.quitAllWindows();
	}
}