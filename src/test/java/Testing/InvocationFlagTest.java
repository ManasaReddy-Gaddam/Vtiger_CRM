package Testing;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvocationFlagTest {
	@Test(invocationCount = 4)
	public void demo1() {
		Reporter.log("Demo _ 1", true);
	}

	@Test
	public void demo2() {
		Reporter.log("Demo _ 2", true);
	}

	@Test(invocationCount = 3)
	public void demo3() {
		Reporter.log("Demo _ 3", true);
	}

	@Test(invocationCount = 2)
	public void demo4() {
		Reporter.log("Demo _ 4", true);
	}
}
