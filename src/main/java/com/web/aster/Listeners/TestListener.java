package com.web.aster.Listeners;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.web.aster.Base.BaseClass;
import com.web.aster.Utilities.TestUtils;



public class TestListener implements ITestListener{
	
	TestUtils utils = new TestUtils();
	BaseClass base = new BaseClass();

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
//		base.takeScreenshot(result.getName(), result);
//		if(result.getThrowable() != null) {
//			  StringWriter sw = new StringWriter();
//			  PrintWriter pw = new PrintWriter(sw);
//			  result.getThrowable().printStackTrace(pw);
//			  utils.log().error(sw.toString());	
//		}
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		
	}

}
