package EcommerceProject.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"\\report\\index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setDocumentTitle("Live Tech Panda");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Tester", "Sushmitha");
		return reports;
		
		
	}

}
