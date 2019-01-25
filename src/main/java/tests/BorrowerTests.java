package tests;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.AbstractTest;
import framework.User;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.OfferPage;

public class BorrowerTests extends AbstractTest {

	private String loanAmount;
	private String loanPurpose;
	private String userIncome;
	private String userAdditionalIncome;
	private String offeredLoanAmount;
	private String offeredApr;
	private String offeredLoanTerm;
	private String offeredMonthlyPayment;
	private OfferPage offerPage;
	private User user;

	@BeforeSuite
	@Parameters({"loanAmount", "loanPurpose", "userIncome", "userAdditionalIncome"})
	public void setup(String loanAmount, String loanPurpose, String userIncome, String userAdditionalIncome) {
		this.loanAmount = loanAmount;
		this.loanPurpose = loanPurpose;
		this.userIncome = userIncome;
		this.userAdditionalIncome = userAdditionalIncome;
		this.user = User.random();
	}

	@Test
	public void checkYourRate() throws InterruptedException {
		offerPage = new HomePage()
			.enterLoanAmount(loanAmount)
			.clickLoanPurpose()
			.selectLoanPurpose(loanPurpose)
			.clickCheckYourRateButton()
			.enterUserInformation()
			.enterIncome(userIncome)
			.enterAdditionalIncome(userAdditionalIncome)
			.createAccount(user)
			.acceptTermsOfUse(true)
			.clickCheckYourRate();
		offeredLoanAmount = offerPage
			.getOfferedLoanAmount();
		offeredApr = offerPage
			.getOfferedApr();
		offeredLoanTerm = offerPage
			.getOfferedLoanTerm();
		offeredMonthlyPayment = offerPage
			.getOfferedMonthlyPayment();
		offerPage
			.clickOnMenu()
			.signOut();
	}

	@Test(dependsOnMethods = "checkYourRate")
	public void login() {
		offerPage = new LoginPage()
			.open()
			.enterCredentials(user)
			.clickSignIn()
			.verifyStoredInformation(offeredLoanAmount, offeredApr, offeredLoanTerm, offeredMonthlyPayment)
			.validateUrl();
	}
}
