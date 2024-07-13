package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSucessPage {
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement AcCreatedExpectedMessegElement;

	public AccountSucessPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String yourAcHasCreatedExpectedMessege() 
	{
		return AcCreatedExpectedMessegElement.getText();

	}

}
