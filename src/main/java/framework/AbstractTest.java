package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractTest {
	private static ExecutionContext context;

	@BeforeTest
	public void setup() {
		context = ExecutionContext.getInstance();
	}

	@AfterTest
	public void cleanUp() {
		context.quit();
	}
}