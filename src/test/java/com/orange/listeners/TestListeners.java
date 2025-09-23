package com.orange.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.orange.reporters.ExtentReportManager;
import com.orange.tests.base.BaseTest;
import com.orange.utils.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
    }
}
