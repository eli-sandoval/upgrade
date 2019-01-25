package framework;

import java.util.Objects;

import lombok.Getter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExecutionContext {
	private static ExecutionContext instance;
	@Getter
	private WebDriver driver;

	private ExecutionContext() {
		System.setProperty("webdriver.chrome.driver", "/Users/eli.sandoval/Downloads/chromedriver");
		this.driver = new ChromeDriver();
	}

	public static ExecutionContext getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ExecutionContext();
		}
		return instance;
	}

	void quit() {
		driver.quit();
		instance = null;
	}
}

