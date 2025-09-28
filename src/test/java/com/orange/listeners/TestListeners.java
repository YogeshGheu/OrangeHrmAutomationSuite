package com.orange.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.orange.reporters.ExtentReportManager;
import com.orange.tests.base.BaseTest;
import com.orange.utils.EmailUtility;
import com.orange.utils.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestListeners implements ITestListener {
    ExtentReports reports;

    public void onTestStart(ITestResult result) {
        System.out.println("testing is started!");
        ExtentReportManager.createTest(result.getName());
        ExtentReportManager.getTest().log(Status.INFO, "Testing is started!");
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "test is passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        ScreenshotUtility screenshotUtility = new ScreenshotUtility(driver);
        String screenshotPath = screenshotUtility.takeScreenshotAsBase64();
        ExtentReportManager.getTest().fail("test is failed, please check the screenshot below :" + result.getName(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "test is skipped: " + result.getName());
    }

    public void onStart(ITestContext context) {
        reports = ExtentReportManager.getReport();
    }

    public void onFinish(ITestContext context) {
        reports.flush();
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/config.properties")){
        if (input == null) {
            System.out.println("unable to find config.properties");
            return;
        }
        properties.load(input);
        String[] recipients = properties.getProperty("recipients").split(",");
        EmailUtility.sendEmailWithAttachment(
                recipients,
                "test execution report - orange HRM",
                "Please find the attached test execution report",
                "target/reports/extent-report.html");
    }catch (Exception e){
            System.out.println("Error while sending the email: " + e.getMessage());
        }
    }
}
