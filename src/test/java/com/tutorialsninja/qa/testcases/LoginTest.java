package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.AccountPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.LoginPage;
import com.tutorialsninja.qa.Utils.Utilities;
import com.tutorialsninja.qa.base.BaseClass;

public class LoginTest extends BaseClass {
	 WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	AccountPage accountPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1, dataProvider = "validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		System.out.println("Testing with: " + email + " / " + password);

		// Perform login with provided credentials
		login(email, password);

		// Initialize AccountPage after successful login
		accountPage = new AccountPage(driver);

		// Verify if "Edit your account information" link is displayed
		Assert.assertTrue(accountPage.getDisplayStatusOfAccountInformationOption(),
				"Edit your account information link is not displayed");
	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {
		return Utilities.getTestDataFromExcelSheet("Sheet1");
	}

	@Test(priority = 2)
	public void verifyLoginWithoutPassword() {
		loginPage = new LoginPage(driver);

		// Debugging statement to check the email property
		String email = prop.getProperty("valideEmail");
		System.out.println("Email from properties: " + email);

		// Check if the email property is null or empty
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Valid email is not provided in the properties file.");
		}

		loginPage.enterEmail(email);
		loginPage.enterPassword(""); // Empty password
		loginPage.clickLoginButton();

		String warningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessege();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(warningMessage.contains(expectedMessage),
				"Expected warning message not found! Actual: " + warningMessage);
	}

	@Test(priority = 3)
	public void verifyLoginWithoutUserID() {
		loginPage = new LoginPage(driver);

		// Debugging statement to check the password property
		String password = prop.getProperty("validePassword");
		System.out.println("Password from properties: " + password);

		// Check if the password property is null or empty
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Valid password is not provided in the properties file.");
		}

		loginPage.enterEmail(""); // Empty email
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

		String warningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessege();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(warningMessage.contains(expectedMessage),
				"Expected warning message not found! Actual: " + warningMessage);
	}

	@Test(priority = 4)
	public void verifyLoginWithBlankCredentials() {
		loginPage = new LoginPage(driver);
		loginPage.enterEmail("");
		loginPage.enterPassword("");
		loginPage.clickLoginButton();

		String warningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessege();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(warningMessage.contains(expectedMessage),
				"Expected warning message not found! Actual: " + warningMessage);
	}

	@Test(priority = 5)
	public void verifyLoginWithInvalidCredentials() {
		// Debugging statements to check the property values
		String invalidEmail = dataprop.getProperty("invalideEmailID") + Utilities.generateTimeStamp() + "@gmail.com";
		String invalidPassword = dataprop.getProperty("invalidePasswordID");
		String expectedWarningMessage = dataprop.getProperty("expectedWarningMessage");

		System.out.println("Invalid Email from properties: " + invalidEmail);
		System.out.println("Invalid Password from properties: " + invalidPassword);
		System.out.println("Expected Warning Message from properties: " + expectedWarningMessage);

		// Check if the required properties are null or empty
		if (invalidEmail == null || invalidEmail.isEmpty()) {
			throw new IllegalArgumentException("Invalid email is not provided in the properties file.");
		}
		if (invalidPassword == null || invalidPassword.isEmpty()) {
			throw new IllegalArgumentException("Invalid password is not provided in the properties file.");
		}
		if (expectedWarningMessage == null || expectedWarningMessage.isEmpty()) {
			throw new IllegalArgumentException("Expected warning message is not provided in the properties file.");
		}

		loginPage = new LoginPage(driver);
		loginPage.enterEmail(invalidEmail);
		loginPage.enterPassword(invalidPassword);
		loginPage.clickLoginButton();

		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessege();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message not found! Actual: " + actualWarningMessage);
	}

	private void navigateToLoginPage() {
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountButton();
		homePage.selectLoginOption();
	}

	private void login(String email, String password) {
		loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
	}
}
