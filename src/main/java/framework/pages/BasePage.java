package framework.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ExecutionContext;
import framework.exceptions.ConditionNotMetException;

@Slf4j
public abstract class BasePage {
	private final ExecutionContext executionContext = ExecutionContext.getInstance();

	private static final String BASE_URL = "https://www.credify.tech";
	private static final String TIME_OUT = System.getProperty("timeout");
	private static final String POLLING_EVERY = System.getProperty("pollingevery");

	BasePage() {
		PageFactory.initElements(executionContext.getDriver(), this);
	}

	BasePage waitUntil(ExpectedCondition expectedCondition) {
		WebDriverWait webDriverWait = new WebDriverWait(executionContext.getDriver(), Integer.valueOf(System.getProperty("timeout")));
		webDriverWait.until(expectedCondition);
		return this;
	}

	public BasePage waitUntil(ExpectedCondition expectedCondition, int timeout) {
		WebDriverWait webDriverWait = new WebDriverWait(executionContext.getDriver(), timeout);
		webDriverWait.until(expectedCondition);
		return this;
	}

	//this method was created in order to click on the "menu" sidebar, which has an issue related to javascript and selenium.
	public BasePage waitForJavaScript(String className) {
		waitUntil(ExpectedConditions.jsReturnsValue(String.format("return document.getElementsByClassName('%s')[0] !== undefined", className)));
		return this;
	}

	public BasePage fluentWait(Predicate<WebDriver>condition, int timeout, int pollingEvery) {
		if (!new FluentWait<>(executionContext.getDriver())
			.ignoring(NoSuchElementException.class)
			.withTimeout(Duration.ofSeconds((long) timeout))
			.pollingEvery(Duration.ofSeconds((long) pollingEvery))
			.withMessage("Waiting for element")
			.until(condition::test)){
			throw new ConditionNotMetException(String.format("Condition not met after %s seconds", TIME_OUT));
		}
		return this;
	}

	public BasePage fluentWait(Predicate<WebDriver> condition) {
		if (!(new FluentWait<>(executionContext.getDriver())
			.withTimeout(Duration.ofSeconds(Long.parseLong(TIME_OUT)))
			.pollingEvery(Duration.ofSeconds(Long.parseLong(POLLING_EVERY)))
			.ignoring(NoSuchElementException.class)
			.withMessage("Waiting for element")
			.until(condition::test))) {
		throw new ConditionNotMetException(String.format("Condition not met after %s seconds", TIME_OUT));
		}
		return this;
	}

	BasePage open(BasePage page) {
		executionContext.getDriver().get(BASE_URL + page.getUrl());
		return this;
	}

	public void close() {
		executionContext.getDriver().close();
	}

	<T extends BasePage> T validateUrl(T page) {
		waitUntil(ExpectedConditions.urlContains(this.getUrl()));
		assertThat(executionContext.getDriver().getCurrentUrl()).contains(this.getUrl());
		log.info("Assertion passed. The URL contains {}", this.getUrl());
		return page;
	}

	protected abstract String getUrl();
}
