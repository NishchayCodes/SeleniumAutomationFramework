package AutomationTesting.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {

	
	public static ExtentReports getReportObject() {
		
	
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	//with help of ExtentSparkReporter class, we can customize our report .
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setReportName("Automation Test Results");
	reporter.config().setDocumentTitle("Test Results");
	
	//main Extent reports class where we attach reporter
	 ExtentReports extent=new ExtentReports();
	 extent.attachReporter(reporter);
	 extent.setSystemInfo("Tester", "Nishchay Anand");
	 return extent;
}
}