package utilities;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static utilities.ExtentReportManager.reportName;


public class TestNGListener extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReportManager.createExtentReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    ExtentTest test;


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("I am in onStart method " + context.getName());
        context.setAttribute("WebDriver", getDriver());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("I am in onFinish method " + context.getName());
        //Do tier down operations for ExtentReports reporting!
        extent.flush();


    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is starting.");
        test = extent.createTest(result.getTestClass().getName()+ "_" +result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is failed.");
        extentTest.get().log(Status.FAIL, result.getThrowable());
        //Get driver from BaseTest and assign to local webdriver variable.
        //Object testClass = result.getInstance();
       // WebDriver driver = getDriver();
/*
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    */
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(result));
    }
}
