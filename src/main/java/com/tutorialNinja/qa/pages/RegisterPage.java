package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;

	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstNameElement;

	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement lastNameElement;

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailElement;

	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement telephonElement;

	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordElement;

	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement confirmPasswordElement;

	@FindBy(xpath = "//input[@name='newsletter'][1]")
	private WebElement newslaterElement;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement agreeButtonElement;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButtonElement;

	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement AcCreatedExpectedMessegElement;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alreadyGetExpectedMessgeElement;

	// Messages
	@FindBy(xpath = "//*[@id='account-register']/div[1]")
	private WebElement accountRegisterElement;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstInputElement;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastInputElement;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailInputElement;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneInputElement;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordInputElement;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		firstNameElement.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameElement.sendKeys(lastName);
	}

	public void enterEmail(String Email) {
		emailElement.sendKeys(Email);
	}

	public void enterTelephonElement(String Telephon) {
		telephonElement.sendKeys(Telephon);
	}

	public void enterPassword(String Password) {
		passwordElement.sendKeys(Password);
	}

	public void enterConfirmPassword(String ConfirmPassword) {
		confirmPasswordElement.sendKeys(ConfirmPassword);

	}

	public void clickNewsLaterButton() {
		newslaterElement.click();
	}

	public void clickAgreeButton() {
		agreeButtonElement.click();
	}

	public void clickContinuButton() {
		continueButtonElement.click();

	}

	public String yourAcHasCreatedExpectedMessege() {
		return AcCreatedExpectedMessegElement.getText();

	}

	public String emailAlreadyRegister() {
		return alreadyGetExpectedMessgeElement.getText();

	}

	public String accountRegisterWarning() {
		return accountRegisterElement.getText();
	}

	public String firstNameWarning() {
		return firstInputElement.getText();
	}

	public String lastNameWarning() {
		return lastInputElement.getText();
	}

	public String emailInputWarning() {
		return emailInputElement.getText();

	}

	public String telephoneInputWarning() {
		return telephoneInputElement.getText();

	}

	public String passwordInputWarning() {
		return passwordInputElement.getText();

	}

}
