package Utility.Listeners;

import TestBase.TestBase;
import Utility.Extent.ExtentManager;
import Utility.Extent.ExtentTestManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Utility.Extent.ExtentManager.takeScreenshot;

public class TestListener extends TestBase implements ITestListener {
    private static long endTime;
    private static void setStartTime(long startTime) {
    }
    private static void setEndTime(long endTime) {
        TestListener.endTime = endTime;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("On Test Start");
        System.out.println("--------- Executing :- " + getSimpleMethodName(iTestResult) + " ---------");
        ExtentTestManager.createTest(iTestResult.getName(),iTestResult.getMethod().getDescription());
        ExtentTestManager.setCategoryName(getSimpleClassName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On Test Success");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(iTestResult));
        addExtentLabelToTest(iTestResult);
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("On Test Failure");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(iTestResult));
        ExtentTestManager.getTest().log(Status.FAIL, iTestResult.getName() + " Test is failed" + iTestResult.getThrowable());
        try {
            ExtentTestManager.getTest().fail("<br><font color= red>"+"Screenshot of Web"+"</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(getSimpleMethodName(iTestResult))).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        addExtentLabelToTest(iTestResult);
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On Test Skipped");
        ExtentTestManager.getTest().log(Status.SKIP, iTestResult.getName() + " Test is Skipped" +  iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("On Test Success Percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("On Start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("On Finish");
        setStartTime(iTestContext.getStartDate().getTime());
        setEndTime(iTestContext.getEndDate().getTime());
    }

    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
    }
    private synchronized String getSimpleMethodName(ITestResult result) {
        return result.getName();
    }
    private synchronized void addExtentLabelToTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
        else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
        } else
            ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
    }
}
