package Testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PriorityFlagTest {
	@Test(priority = 1)
	public void demo1() {
		Reporter.log("Demo _ 1",true);
	}
	@Test
	public void demo2() {
		Reporter.log("Demo _ 2",true);
	}
	@Test(priority = 2)
	public void demo3() {
		Reporter.log("Demo _ 3",true);
	}
	@Test(priority = -2)
	public void demo4() {
		Reporter.log("Demo _ 4",false);
	}
	@Test(priority = -1)
	public void demo5() {
		Reporter.log("Demo _ 5",true);
	}
	public void mavenParameterization() {
		WebDriver driver = null;
		String browser = System.getProperty("BROWSER");
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else
			System.out.println("Invalid browser");
		
		driver.manage().window().maximize();
		driver.get(System.getProperty("URL"));
		System.out.println(driver.getTitle());		
		driver.quit();
	}
}
