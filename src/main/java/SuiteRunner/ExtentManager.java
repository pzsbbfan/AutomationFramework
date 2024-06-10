package SuiteRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            htmlReporter = new ExtentSparkReporter("extent-reports/extent-report.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Test Report");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest test) {
        ExtentManager.test = test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
