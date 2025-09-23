package com.orange.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports reports;

    private static final ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();


    public static ExtentReports getReport() {
        if (reports == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/reports/extent-report.html");
            reporter.config().setReportName("Test Execution Report - OrangeHRM");
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Test Execution Report - OrangeHRM");

            reports = new ExtentReports();
            reports.attachReporter(reporter);
            reports.setSystemInfo("QA", "Yogesh Gheu");
            reports.setSystemInfo("Environment", "PROD");
        }
        return reports;
    }

    public static void createTest(String name){
        ExtentTest test = reports.createTest(name);
        threadLocalTest.set(test);
    }

    public static ExtentTest getTest(){
        return threadLocalTest.get();
    }
}
