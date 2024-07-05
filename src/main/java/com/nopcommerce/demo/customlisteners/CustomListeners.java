package com.nopcommerce.demo.customlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.nopcommerce.demo.utility.extentreports.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import static com.nopcommerce.demo.utility.Utility.takeScreenShot;

/**
 * Created by Jay Vaghani
 */
public class CustomListeners implements ITestListener {

    public static ExtentReports reports;
    public static ExtentTest test;
    public static ExtentTest node;


    @Override
    public void onTestStart(ITestResult iTestResult) {
        node = test.createNode(iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        node.log(Status.PASS, MarkupHelper.createLabel("Test : '" + iTestResult.getName() + "' is Passed",
                ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // This step will
        // take screenshot and store in to test-output/html folder
        String screenshotPath = takeScreenShot(iTestResult.getName());
        // These lines are required for Extent report
        node.log(Status.FAIL, MarkupHelper.createCodeBlock(String.valueOf(iTestResult.getThrowable())));
        node.log(Status.FAIL, MarkupHelper.createLabel("Test : '" + iTestResult.getName() + "' is Failed",
                ExtentColor.RED));
        node.addScreenCaptureFromPath(screenshotPath);
        // These lines are required for ReportNG report
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log("Click to see screenshot");
        Reporter.log("<a target = \"_blank\" href=" + screenshotPath + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target = \"_blank\" href=" + screenshotPath + "><img src=" + screenshotPath +
                " height=200 width=200></img></a>");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        node.log(Status.SKIP, MarkupHelper.createLabel("Test : '" + iTestResult.getName() + "' is Skipped",
                ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        reports = ExtentReportManager.getReports();
        test = reports.createTest(iTestContext.getAllTestMethods()[0].getInstance().getClass().getSimpleName())
                .assignAuthor("Jay").assignCategory("Smoke");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        reports.flush();
    }
}
