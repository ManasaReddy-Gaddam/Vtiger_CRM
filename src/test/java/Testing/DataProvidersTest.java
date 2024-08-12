package Testing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidersTest {
	@Test
	public void bookTicketTest(String from, String to) {
		System.out.println("From: " + from + "\tT0: " + to);
	}
	
	@DataProvider
	public Object[][] data() {
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "Hyderabad";
		obj[0][1] = "Chennai";
		obj[1][0] = "Hyderabad";
		obj[1][1] = "Delhi";
		obj[2][0] = "Hyderabad";
		obj[2][1] = "Chennai";
		
		return obj;
		
	}
}

//surefire plugin is used to executes xml files on mvn commandline & in jenkins