package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailAddressFieldElement;

	@FindBy(id = "input-password")
	private WebElement passwordAddressFieldElement;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
	private WebElement loginButtonElement;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void enterEmail(String emailText) {
		emailAddressFieldElement.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		passwordAddressFieldElement.sendKeys(passwordText);
	}

	public void clickLoginButton() {
		loginButtonElement.click();
	}

	public String retriveEmailPasswordNotMatchingWarningMessege() {
		String warningtext = emailPasswordNotMatchingWarning.getText();
		return warningtext;

	}
}
