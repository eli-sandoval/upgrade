package framework.pages;

import static com.google.common.base.Preconditions.checkNotNull;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.User;

@Slf4j
public class LoginPage extends BasePage{
	private static final String URL = "/portal/login";

	@FindBy(how = How.NAME, using = "username")
	private WebElement usernameInput;

	@FindBy(how = How.NAME, using = "password")
	private WebElement passwordInput;

	@FindBy(how = How.CSS, using = "button[data-auto='login']")
	private WebElement signInButton;

	public LoginPage enterUsername(User user) {
		checkNotNull(user.getCredentials().getEmail(), "The email cannot be null");
		waitUntil(ExpectedConditions.visibilityOf(usernameInput));
		log.info("Entering username={}", user.getCredentials().getEmail());
		usernameInput.sendKeys(user.getCredentials().getEmail());
		return this;
	}

	public LoginPage enterPassword(User user) {
		checkNotNull(user.getCredentials().getPassword(), "The password cannot be null");
		log.info("Entering password");
		passwordInput.sendKeys(user.getCredentials().getPassword());
		return this;
	}

	public LoginPage enterCredentials(User user) {
		checkNotNull(user.getCredentials().getEmail(), "The username cannot be null");
		log.info("Entering username");
		checkNotNull(user.getCredentials().getPassword(), "The password cannot be null");
		log.info("Entering password");
		usernameInput.sendKeys(user.getCredentials().getEmail());
		passwordInput.sendKeys(user.getCredentials().getPassword());
		return this;
	}

	public OfferPage clickSignIn() {
		waitUntil(ExpectedConditions.visibilityOf(signInButton));
		log.info("Clicking on sign in button");
		signInButton.click();
		return new OfferPage();
	}

	@Override
	protected String getUrl() {
		return URL;
	}

	public LoginPage open() {
		log.info("Loading log in page");
		open(this);
		return this;
	}
}
