package com.tutorialsninja.qa.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class Utilities 
{


    public static final int IMPLICIT_PAGE_LOAD_TIME = 5;

    public static String generateTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static Object[][] getTestDataFromExcelSheet(String sheetName) {
        File file = new File("D:\\Selenium Projects\\tutorialNinjaProject\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\TutorialNinjaTestData.xlsx");
        XSSFWorkbook workbook = null;

        try (FileInputStream fisExcel = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fisExcel);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum() + 1;
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);

            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case STRING:
                        data[i - 1][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                        break;
                }
            }
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    public static String captureScreenshots(WebDriver driver, ExtentTest extentTest, String testName) {
        String destinationScreenshotPath = null;
        if (driver != null) {
            destinationScreenshotPath = "D:\\Selenium Projects\\tutorialNinjaProject\\Screenshots\\" + testName + ".png";
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcScreenshot, new File(destinationScreenshotPath));
                System.out.println("Screenshot taken for test case: " + testName);
                extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save screenshot for test case: " + testName);
            }
        } else {
            System.out.println("Driver is null, unable to take screenshot for test case: " + testName);
        }
        return destinationScreenshotPath;
    }
}
