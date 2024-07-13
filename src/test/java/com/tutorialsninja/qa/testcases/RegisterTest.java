package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.AccountSucessPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.Utils.Utilities;
import com.tutorialsninja.qa.base.BaseClass;

public class RegisterTest extends BaseClass {
	public WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSucessPage accountSucessPage;

	// it login constructor call to parent class of name is base class constructor
	public RegisterTest() {

		super();

	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void verifyRegisterAccountWithMandatoryFields() {
		fillRegistrationForm(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), generateUniqueEmail(),
				dataprop.getProperty("phonno"), dataprop.getProperty("Password"), true);
		accountSucessPage = new AccountSucessPage(driver);
		String text = accountSucessPage.yourAcHasCreatedExpectedMessege();
		System.out.println(text);
		// test failed for check report
		Assert.assertTrue(text.contains("Your Account Has Been Created!(Test is Failed for perpose)"));
	}

	@Test(priority = 2, dependsOnMethods = "verifyRegisterAccountWithMandatoryFields")
	public void verifyRegisterAccountByProvidingAllFields() {
		fillRegistrationForm(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), generateUniqueEmail(),
				dataprop.getProperty("phonno"), dataprop.getProperty("Password"), true);
		accountSucessPage = new AccountSucessPage(driver);
		String text = accountSucessPage.yourAcHasCreatedExpectedMessege();
		System.out.println(text);
		Assert.assertTrue(text.contains("Your Account Has Been Created!"));

	}

	@Test(priority = 3)
	public void verifyRegisterAccountByExistingEmailId() {
		fillRegistrationForm(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				prop.getProperty("valideEmail"), dataprop.getProperty("phonno"), prop.getProperty("validePassword"),
				true);

		String actualWarning = registerPage.emailAlreadyRegister();

		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),
				"Warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisterAccountWithoutAnyDetails() {
		registerPage = new RegisterPage(driver);
		registerPage.clickContinuButton();

		Assert.assertTrue(
				registerPage.accountRegisterWarning().contains("Warning: You must agree to the Privacy Policy!"),
				"Privacy Policy warning is missing");

		Assert.assertEquals(registerPage.firstNameWarning(), "First Name must be between 1 and 32 characters!",
				"First Name warning message is not present");

		Assert.assertEquals(registerPage.lastNameWarning(), "Last Name must be between 1 and 32 characters!",
				"Last Name warning message is not present");

		Assert.assertEquals(registerPage.emailInputWarning(), "E-Mail Address does not appear to be valid!",
				"Email warning message is not present");

		Assert.assertEquals(registerPage.telephoneInputWarning(), "Telephone must be between 3 and 32 characters!",
				"Telephone warning message is not present");

		Assert.assertEquals(registerPage.passwordInputWarning(), "Password must be between 4 and 20 characters!",
				"Password warning message is not present");
	}

	private void navigateToRegisterPage() {
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountButton();
		homePage.selectRegisterOption();

		/*
		 * driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click(
		 * ); driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		 */

	}

	private void fillRegistrationForm(String firstName, String lastName, String email, String telephone,
			String password, boolean subscribe) {
		registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(email);
		registerPage.enterTelephonElement(telephone);
		registerPage.enterPassword(password);
		registerPage.enterConfirmPassword(password);

		/*
		 * driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(
		 * firstName);
		 * driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(
		 * lastName);
		 * driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		 * driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(
		 * telephone);
		 * driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(
		 * password);
		 * driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(
		 * password);
		 */
		if (subscribe) {
			// driver.findElement(By.xpath("//input[@name='newsletter'][1]")).click();
			registerPage.clickNewsLaterButton();
		}
		registerPage.clickAgreeButton();
		registerPage.clickContinuButton();
		/*
		 * driver.findElement(By.xpath("//input[@name='agree']")).click();
		 * driver.findElement(By.xpath("//input[@value='Continue']")).click();
		 */

	}

	private String generateUniqueEmail() {
		return dataprop.getProperty("gmailName") + Utilities.generateTimeStamp() + "@gmail.com";
	}
}
