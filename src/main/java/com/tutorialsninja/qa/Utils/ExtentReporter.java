package com.tutorialsninja.qa.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    public Properties prop;

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReports = new ExtentReports();

        File extentReportFile = new File("D:\\Selenium Projects\\tutorialNinjaProject\\test-output\\ExtentReport\\extentreport.html");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFile);
        
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("TutorialNinja Automation Result");
        extentSparkReporter.config().setDocumentTitle("TN Automation Report");
        extentSparkReporter.config().setTimeStampFormat("DD-MM-YYYY HH:MM:SS");
        
        extentReports.attachReporter(extentSparkReporter);

        Properties prop = new Properties();
        try {
            File file = new File("D:\\Selenium Projects\\tutorialNinjaProject\\src\\main\\java\\com\\tutorialNinja\\qa\\config\\Config.properties");
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load config properties file.");
        }

        extentReports.setSystemInfo("Name", prop.getProperty("username", "QA-Gopikisan Pund"));
        extentReports.setSystemInfo("Environment", prop.getProperty("environment", "QA"));
        extentReports.setSystemInfo("User", prop.getProperty("user", "GopikisanPundTech"));

        return extentReports;
    }
}
