package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreatingNewOrganizationPage {
	@FindBy(xpath = "//span[@class='ivtHeaderText']")
	private WebElement pageHeader;

	@FindBy(name = "accountname")
	private WebElement OrganizationNameTF;

	@FindBy(xpath = "//input[contains(@title,'save')]")
	private WebElement saveBTN;

	@FindBy(name = "industry")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	// Initialization
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	/**
	 * This method fetches the page header
	 * 
	 * @return String
	 */
	public String getPageHeder() {
		return pageHeader.getText();
	}

	/**
	 * This method sets the organization name into the organization name text field
	 * 
	 * @param name
	 */
	public void setOrganizationName(String name) {
		OrganizationNameTF.sendKeys(name);
	}

	/**
	 * This method clicks on save button
	 */
	public void clickSaveBTN() {
		saveBTN.click();
	}

	/**
	 * This method is used to select an industry from industry drop down
	 * 
	 * @param driverUtil
	 * @param value
	 */
	public void selectFromIndustryDD(WebDriverUtility driverUtil, String value) {
		driverUtil.handleDropdown(industryDD, value);
	}

	/**
	 * This method is used to select a type from type drop down
	 * 
	 * @param driverUtil
	 * @param value
	 */

	public void selectFromTypeDD(WebDriverUtility driverUtil, String value) {
		driverUtil.handleDropdown(typeDD, value);
	}
}
