package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noProductMessageElement;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String searchNotValideName() {
        return noProductMessageElement.getText();
    }
}
