package framework.pages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
@RequiredArgsConstructor
public class OfferPage extends BasePage {
	private static final String URL = "/phone/offer-page";

	@FindBy(how = How.XPATH, using = "//span[@data-auto='userLoanAmount']")
	private WebElement userLoanAmount;

	@FindBy(how = How.XPATH, using = "//div[@data-auto='defaultMoreInfoAPR']")
	private WebElement apr;

	@FindBy(how = How.XPATH, using = "//div[@data-auto='defaultLoanTerm']")
	private WebElement loanTerm;

	@FindBy(how = How.XPATH, using = "//span[@data-auto='defaultMonthlyPayment']")
	private WebElement monthlyPayment;

	@FindBy(how = How.XPATH, using = "//label[@for='header-nav-toggle']")
	private WebElement menuNavigationToggle;

	@FindBy(how = How.XPATH, using = "//a[@href='/phone/logout']")
	private WebElement signOut;

	@Override
	protected String getUrl() {
		return URL;
	}

	public OfferPage open() {
		log.info("Loading offer page");
		open(this);

		return this;
	}

	public String getOfferedLoanAmount() {
		waitUntil(ExpectedConditions.visibilityOf(userLoanAmount));
		String loanAmountOffered = userLoanAmount.getText();
		log.info("Loan amount offered={}", loanAmountOffered);
		return loanAmountOffered;
	}

	public String getOfferedApr() {
		waitUntil(ExpectedConditions.visibilityOf(apr));
		String aprOffered = apr.getText();
		log.info("APR offered={}", aprOffered);
		return aprOffered;
	}

	public String getOfferedLoanTerm() {
		waitUntil(ExpectedConditions.visibilityOf(loanTerm));
		String loanTermOffered = loanTerm.getText();
		log.info("Loan term offered={}", loanTermOffered);
		return loanTermOffered;
	}

	public String getOfferedMonthlyPayment() {
		waitUntil(ExpectedConditions.visibilityOf(monthlyPayment));
		String monthlyPaymentOffered = monthlyPayment.getText();
		log.info("Monthly payment offered={}", monthlyPaymentOffered);
		return monthlyPaymentOffered;
	}

	public OfferPage clickOnMenu() throws InterruptedException {
		log.info("Click on the menu");
		//Added hardcoded wait due to a frontend javascript issue on the website
		Thread.sleep(3000);
		menuNavigationToggle.click();
		return this;
	}

	public OfferPage signOut(){
		log.info("Click on sign out");
		waitUntil(ExpectedConditions.visibilityOf(signOut));
		signOut.click();
		return this;
	}

	public OfferPage verifyStoredInformation(String loanAmountOffered, String aprOffered, String loanTermOffered, String monthlyPaymentOffered) {
		SoftAssertions soft = new SoftAssertions();
		waitUntil(ExpectedConditions.textToBePresentInElement(userLoanAmount, loanAmountOffered));
		soft.assertThat(userLoanAmount.getText()).isEqualToIgnoringCase(loanAmountOffered);
		soft.assertThat(apr.getText()).isEqualToIgnoringCase(aprOffered);

		soft.assertThat(loanTerm.getText()).isEqualToIgnoringCase(loanTermOffered);

		soft.assertThat(monthlyPayment.getText()).isEqualToIgnoringCase(monthlyPaymentOffered);
		soft.assertAll();
		return this;
	}

	public OfferPage validateUrl() {
		return validateUrl(this);
	}
}

