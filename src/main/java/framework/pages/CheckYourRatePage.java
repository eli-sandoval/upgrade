package framework.pages;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.User;
import framework.UserInformationEnum;

@RequiredArgsConstructor
@Slf4j
public class CheckYourRatePage extends BasePage {
	private static final String URL = "/phone/personal-information-1";
	private static Map<UserInformationEnum, String> userAdressInformation;

	private String userFirstName = RandomStringUtils.randomAlphabetic(10);
	private String userLastName = RandomStringUtils.randomAlphabetic(10);

	@FindBy(how = How.NAME, using = "borrowerFirstName")
	private WebElement borrowerFirstName;

	@FindBy(how = How.NAME, using = "borrowerLastName")
	private WebElement borrowerLastName;

	@FindBy(how = How.NAME, using = "borrowerStreet")
	private WebElement borrowerStreetAddress;

	@FindBy(how = How.NAME, using = "borrowerCity")
	private WebElement borrowerCity;

	@FindBy(how = How.NAME, using = "borrowerState")
	private WebElement borrowerState;

	@FindBy(how = How.NAME, using = "borrowerZipCode")
	private WebElement borrowerZipCode;

	@FindBy(how = How.NAME, using = "borrowerDateOfBirth")
	private WebElement borrowerDateOfBirth;

	@FindBy(how = How.NAME, using = "borrowerIncome")
	private WebElement borrowerIncome;

	@FindBy(how = How.NAME, using = "borrowerAdditionalIncome")
	private WebElement borrowerAdditionalIncome;

	@FindBy(how = How.NAME, using = "username")
	private WebElement borrowerUsername;

	@FindBy(how = How.NAME, using = "password")
	private WebElement borrowerPassword;

	@FindBy(how = How.CSS, using = "input[name='agreements']")
	private WebElement agreementsCheckBox;

	@FindBy(how = How.XPATH, using = "//button[@data-auto='submitPersonalInfo']")
	private WebElement submitCheckYourRate;

	public CheckYourRatePage enterUserInformation() {
		initializeUserAddressInformationMap();
		waitUntil(ExpectedConditions.visibilityOf(borrowerFirstName));
		log.info("Enter user first name={}", userFirstName);
		borrowerFirstName.sendKeys(userFirstName);
		waitUntil(ExpectedConditions.visibilityOf(borrowerLastName));
		log.info("Enter user last name={}", userLastName);
		borrowerLastName.sendKeys(userLastName);
		waitUntil(ExpectedConditions.visibilityOf(borrowerStreetAddress));
		log.info("Enter user street address={}", userAdressInformation.get(UserInformationEnum.STREET_ADDRESS));
		borrowerStreetAddress.sendKeys(userAdressInformation.get(UserInformationEnum.STREET_ADDRESS));
		waitUntil(ExpectedConditions.visibilityOf(borrowerState));
		log.info("Enter city={}", userAdressInformation.get(UserInformationEnum.CITY));
		borrowerCity.sendKeys(userAdressInformation.get(UserInformationEnum.CITY));
		waitUntil(ExpectedConditions.visibilityOf(borrowerState));
		log.info("Enter state={}", userAdressInformation.get(UserInformationEnum.STATE));
		borrowerState.sendKeys(userAdressInformation.get(UserInformationEnum.STATE));
		waitUntil(ExpectedConditions.visibilityOf(borrowerZipCode));
		log.info("Enter zip code={}", userAdressInformation.get(UserInformationEnum.ZIP_CODE));
		borrowerZipCode.sendKeys(userAdressInformation.get(UserInformationEnum.ZIP_CODE));
		waitUntil(ExpectedConditions.visibilityOf(borrowerDateOfBirth));
		log.info("Enter state={}", userAdressInformation.get(UserInformationEnum.BIRTH_DATE));
		borrowerDateOfBirth.sendKeys(userAdressInformation.get(UserInformationEnum.BIRTH_DATE));
		return this;
	}


	public CheckYourRatePage createAccount(User user) {
		checkNotNull(user.getCredentials().getEmail(), "The username cannot be null");
		log.info("Entering username={}", user.getCredentials().getEmail());
		borrowerUsername.sendKeys(user.getCredentials().getEmail());
		checkNotNull(user.getCredentials().getPassword(), "The password cannot be null");
		log.info("Entering password");
		borrowerPassword.sendKeys(user.getCredentials().getPassword());
		return this;
	}

	public CheckYourRatePage enterIncome(String userIncome){
		checkNotNull(userIncome, "The income cannot be null");
		log.info("Entering income={}", userIncome);
		borrowerIncome.sendKeys(userIncome);
		return this;
	}

	public CheckYourRatePage enterAdditionalIncome(String userAdditionalIncome){
		checkNotNull(userAdditionalIncome, "The additional income cannot be null");
		log.info("Entering additional income={}", userAdditionalIncome);
		borrowerAdditionalIncome.sendKeys(userAdditionalIncome);
		return this;
	}

	public CheckYourRatePage acceptTermsOfUse(Boolean toggleTermsOfUse){
		if (toggleTermsOfUse) {
			fluentWait(driver -> {
				if (agreementsCheckBox.isEnabled()) {
					//A javascript executor was needed since the frontend did not allow me to click on the checkbox even though it was found by selenium.
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", agreementsCheckBox);
					return true;
				} else {
					return false;
				}
			});
			log.info("Accepting terms and conditions");
		}
		return this;
	}

	public OfferPage clickCheckYourRate(){
		waitUntil(ExpectedConditions.elementToBeClickable(submitCheckYourRate));
		log.info("Clicking check your rate button");
		submitCheckYourRate.click();
		log.info("Loading offer page");
		return new OfferPage();
	}

	public void initializeUserAddressInformationMap() {
		userAdressInformation = new HashMap<>();
		userAdressInformation.put(UserInformationEnum.STREET_ADDRESS, "4763 August Lane");
		userAdressInformation.put(UserInformationEnum.CITY, "Monroe");
		userAdressInformation.put(UserInformationEnum.STATE, "LA");
		userAdressInformation.put(UserInformationEnum.ZIP_CODE, "04009");
		userAdressInformation.put(UserInformationEnum.BIRTH_DATE, "01/01/1980");
	}

	@Override
	protected String getUrl() {
		return URL;
	}

	public CheckYourRatePage open() {
		log.info("Loading check your rate page");
		open(this);
		return this;
	}
}
