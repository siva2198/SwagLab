package ConfigurationHelper.Utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "/Reports/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Swag Lab Report");
        reporter.config().setReportName("Swag Lab Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Sivaraman M- siva2198","Github");
        return extent;
    }
    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        } else {
            throw new IllegalStateException("ExtentTest is not initialized. Call startTest() first.");
        }
    }
}