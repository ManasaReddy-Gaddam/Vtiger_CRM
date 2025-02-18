package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	//Declaration
		@FindBy(css= "span.dvHeaderText")
		private WebElement pageHeader;
		
		@FindBy(xpath = "//input[@name='Delete']")
		private WebElement deleteBTN;
		
		//Initalization		
		public ContactInformationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//Utilization	
		/**
		 * This method fetches the page header
		 */
		public String getPageHeader() {
			return pageHeader.getText();
		}	
		/**
		 * This method is used to click on delete button
		 */
		public void clickDeleteBTN() {
			deleteBTN.click();
		}
}
