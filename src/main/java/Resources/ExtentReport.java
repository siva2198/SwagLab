package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "/Reports/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Swag Lab Report");
        reporter.config().setReportName("Swag Lab Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("TESTER","Sivaraman M");
        return extent;
    }
}