package com.tutorialNinja.qa.MyListeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.Utils.ExtentReporter;
import com.tutorialsninja.qa.Utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Tests started");
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, testName + " Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println("Attempting to take screenshot for test: " + testName);

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			System.out.println("Driver retrieved: " + (driver != null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String destinationScreenshotPath = Utilities.captureScreenshots(driver, extentTest, testName);

		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.SKIP, testName + " Test Skipped");
		extentTest.log(Status.INFO, result.getThrowable());
	}

	@Override
	 public void onFinish(ITestContext context) {
        System.out.println("Finished Test Project Execution");
        extentReport.flush(); // Assuming extentReport is a properly initialized ExtentReports object

        String pathOfExtentReport = "D:\\Selenium Projects\\tutorialNinjaProject\\test-output\\ExtentReport\\extentreport.html";
        File extentReportFile = new File(pathOfExtentReport);
        
        if (extentReportFile.exists()) {
            try {
                Desktop.getDesktop().browse(extentReportFile.toURI());
               
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to open the ExtentReport in the browser.");
            }
        } else {
            System.out.println("ExtentReport file does not exist: " + pathOfExtentReport);
        }
    }

}
