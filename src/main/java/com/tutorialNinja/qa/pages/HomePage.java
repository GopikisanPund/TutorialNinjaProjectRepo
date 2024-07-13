package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    private WebElement clickonMyAccountElement;
    
    @FindBy(xpath = "//a[normalize-space()='Login']")
    private WebElement clickOnLoginElement;
    
    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement clickOnRegisterPagElement;
    
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBoxFieldElement;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchButtonElement;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterProductNameIntoSearchBoxField(String productItem) {
        searchBoxFieldElement.clear();
        searchBoxFieldElement.sendKeys(productItem);
    }

    public void clickOnSearchButton() {
        searchButtonElement.click();
    }
    
    public void clickOnMyAccountButton() 
    {
    	clickonMyAccountElement.click();
		
	}
    
    public void selectLoginOption() 
    {
    	clickOnLoginElement.click();
		
	}
    
    public void selectRegisterOption() 
    {
    	clickOnRegisterPagElement.click();
		
	}
}
