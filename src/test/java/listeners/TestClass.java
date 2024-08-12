package listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.ListenersImplimentation.class)
 class TestClass extends BaseClass{
	@Test
	public void demo() {
		System.out.println("@Test");
		Assert.fail();
	}
}
