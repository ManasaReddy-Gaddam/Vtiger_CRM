package TestNGImplimentation;

import java.util.Map;

import org.testng.annotations.Test;

import POM.CreatingNewOrganizationPage;
import POM.OrganizationInformationPage;
import POM.OrganizationsPage;
import genericUtilities.BaseClass;
import genericUtilities.TabNames;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "organizations")
	public void createOrgTest() {
		OrganizationsPage organization = pageObjectManager.getOrganization();
		CreatingNewOrganizationPage createOrg = pageObjectManager.getCreateOrg();
		OrganizationInformationPage orgInfo = pageObjectManager.getOrgInfo();

		home.clickRequiredTab(driverUtil, TabNames.ORGANIZATIONS);

		soft.assertTrue(driver.getTitle().contains("Organizations"));

		organization.clickCreateOrgBTN();

		soft.assertTrue(createOrg.getPageHeder().equalsIgnoreCase("creating new organization"));

		Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization");

		String orgName = map.get("Organization Name") + jutil.generateRandomNum(100);
		createOrg.setOrganizationName(orgName);
		createOrg.clickSaveBTN();

		soft.assertTrue(orgInfo.getPageHeader().contains(orgName));

		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");

		soft.assertTrue(driver.getTitle().contains("Organizations"));
		if (driver.getTitle().contains("Organizations"))
			excel.writeToExcel("OrganizationsTestData", "Create Organization", "Pass");
		else
			excel.writeToExcel("OrganizationsTestData", "Create Organization", "Fail");
		soft.assertAll();
	}
}
