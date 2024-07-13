package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.Utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;

	public BaseClass() {
		prop = new Properties();
		File file = new File(
				"D:\\Selenium Projects\\tutorialNinjaProject\\src\\main\\java\\com\\tutorialNinja\\qa\\config\\Config.properties");

		dataprop = new Properties();
		File file2 = new File(
				"D:\\Selenium Projects\\tutorialNinjaProject\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\testdata.properties");

		try (FileInputStream fis2 = new FileInputStream(file2)) {
			dataprop.load(fis2);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load data properties file: " + file2.getAbsolutePath());
		}

		try (FileInputStream fis = new FileInputStream(file)) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load config properties file: " + file.getAbsolutePath());
		}
	}

	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.IMPLICIT_PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
