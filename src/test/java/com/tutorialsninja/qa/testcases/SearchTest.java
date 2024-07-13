package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.SearchPage;
import com.tutorialsninja.qa.base.BaseClass;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	// Constructor calls the parent class constructor
	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homePage = new HomePage(driver); // Initialize homePage here
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		// Debugging: Check the value of searchItem
		String searchItem = dataprop.getProperty("searchItem");
		System.out.println("searchItem: " + searchItem);
		Assert.assertNotNull(searchItem, "searchItem property is null");

		homePage.enterProductNameIntoSearchBoxField(searchItem);
		homePage.clickOnSearchButton();

		// Using WebDriverWait to wait for the element
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchItemElement;
		try {
			searchItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("HP LP3065")));
		} catch (NoSuchElementException e) {
			Assert.fail("Valid product HP LP3065 is not displayed in the search result");
			return;
		}

		Assert.assertTrue(searchItemElement.isDisplayed(),
				"Valid product HP LP3065 is not displayed in the search result");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProducts() {
		// Debugging: Check the value of searchItem
		String searchItem = dataprop.getProperty("invalideSearchData");
		System.out.println("searchItem: " + searchItem);
		Assert.assertNotNull(searchItem, "searchItem property is null");

		homePage.enterProductNameIntoSearchBoxField(searchItem);
		homePage.clickOnSearchButton();

		searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.searchNotValideName();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No products message in search result is not displayed");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		homePage.clickOnSearchButton();

		searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.searchNotValideName();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No products message in search result is not displayed");
	}
}
