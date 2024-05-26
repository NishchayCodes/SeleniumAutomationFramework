package AutomationTesting.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationTesting.resources.ExtentReporting;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent= ExtentReporting.getReportObject();
	//Threadlocal to avoid synchronization issue of screenshots in extent report while running parallelly.
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//Fetching test method name dynamically and creating test for reporting
		test=extent.createTest(result.getMethod().getMethodName());
		//set the test for threadlocal (will give unique thread id for each test running)
		extentTest.set(test);
	
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//Not mandatory to log for success test
           //test.log(Status.PASS, "Test Passed");
		//for thread safe 
           extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Print the error mssg of failed test
//		test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		//getting driver of the test running which failed
		try{
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch(Exception e1) {
			e1.printStackTrace();
			
		}
		
		//Attaching screenshot of failed test case with extent report using listeners
		String filePath=null;
		try {
			//Passing method name of failed test case to getscreenshot method
			filePath= getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//attaching screenshot with extent report
//		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	public void onFinish(ITestContext context) {
		//To generate the final report
		extent.flush();
	
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		
	}
	

}
