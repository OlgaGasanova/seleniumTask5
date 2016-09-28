package utils;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import tests.Tests;
import ui.webdriver.Driver;

public class ScreenShotListener implements ITestListener{

	private void screenMake(ITestResult result){
		Object instance = result.getInstance();
		if (instance == null){
			return;
		}
		
		if (!(instance instanceof Tests)){
			return;
		}

		WebDriver driver = Driver.getWebDriverInstance();
		if (driver != null){
			ScreenShot.make(driver);
		}
	}

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {	
	}

	public void onTestFailure(ITestResult result) {
		screenMake(result);
	}

	public void onTestSkipped(ITestResult result) {
		screenMake(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		screenMake(result);
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

}
