package framework.pages;

import static com.google.common.base.Preconditions.checkNotNull;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@Slf4j
public class HomePage extends BasePage {
	private static final String URL = Strings.EMPTY;

	@FindBy(how = How.NAME, using = "desiredAmount")
	private WebElement loanAmountInput;

	@FindBy(how = How.ID, using = "loan-purpose-select")
	private WebElement loanPurposeDropdown;

	@FindBy(how = How.ID, using = "loan-form-submit")
	private WebElement checkYourRateButton;

	public HomePage() {
		super();
		open(this);
		log.info("Loading home page");
	}

	private Select getLoanPurposeDropdown(String loanPurpose) {
		Select purposeOption = new Select(loanPurposeDropdown);
		purposeOption.selectByVisibleText(loanPurpose);
		return purposeOption;
	}

	public HomePage enterLoanAmount(String loanAmount) {
		checkNotNull(loanAmount, "The loan amount cannot be null");
		log.info("Entering loan amount={} ", loanAmount);
		waitUntil(ExpectedConditions.visibilityOf(loanAmountInput));
		loanAmountInput.click();
		loanAmountInput.sendKeys(loanAmount);
		return this;
	}

	public HomePage clickLoanPurpose() {
		log.info("Clicking on loan purpose dropdown");
		loanPurposeDropdown.click();
		return this;
	}

	public HomePage selectLoanPurpose(String loanPurpose) {
		checkNotNull(loanPurpose, "The loan purpose cannot be null");
		log.info("Clicking on loan purpose={} ", loanPurpose);
		loanPurposeDropdown.click();
		getLoanPurposeDropdown(loanPurpose);
		return this;
	}

	public CheckYourRatePage clickCheckYourRateButton() {
		log.info("Clicking on check your rate button");
		checkYourRateButton.click();
		log.info("Loading check your rate page");
		return new CheckYourRatePage();
	}

	@Override
	protected String getUrl() {
		return URL;
	}
}
