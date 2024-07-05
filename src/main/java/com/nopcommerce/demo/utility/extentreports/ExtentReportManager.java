package com.nopcommerce.demo.utility.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

/**
 * Created by Jay Vaghani
 */
public class ExtentReportManager {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports reports;
    public static ExtentTest test;

    public static ExtentReports getReports() {
        if (reports == null) {
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extent.html")
                    .viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST,
                            ViewName.CATEGORY}).apply();
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("nop-commerce-demo");
            sparkReporter.config().setTheme(Theme.DARK);
            reports = new ExtentReports();
            reports.attachReporter(sparkReporter);
            reports.setSystemInfo("User Name", System.getProperty("user.name"));
            reports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
            reports.setSystemInfo("OS", System.getProperty("os.name"));
            reports.setSystemInfo("Java Version", System.getProperty("java.version"));
            reports.setSystemInfo("Selenium", "4.15.0");
        }
        return reports;
    }
}
