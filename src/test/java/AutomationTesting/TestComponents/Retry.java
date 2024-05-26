package AutomationTesting.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer{
	
	int count=0;
	int maxTry=2;

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			
			//This code will erun test that many times we give in maxTry variable
			//if this function returns tue only then it will re execute
			//we need to pass this as @Test(retryAnalyzer=classname.class) fo all flaky test as in submit ordertest and errorvalidation test
			count++;
			return true;
		}
		return false;
	}

}
