package POMImplimentation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import POM.LoginPage;

public class LoginScript {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoapp.skillrary.com/login.php?type=login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		LoginPage login = new LoginPage(driver);
//		
//		login.setEmail("admin");
//		login.setPassWord("admin");
//		login.clickOnLoginButton();
//		Thread.sleep(2000);
//		
		driver.quit();
	}

}
