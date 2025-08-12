package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.ConfigLoader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            // Create report directory if not exists
            String reportDirc = ConfigLoader.get("reportDir");
            new File(reportDirc).mkdirs();

            // Add timestamp to avoid overwriting old reports
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss_").format(new Date());
            String filePath = reportDirc + File.separator + "FlightBookingAPI_" + timeStamp + ".html";

            // Use ExtentSparkReporter
            ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
            spark.config().setDocumentTitle("FlightBooking API Automation Report");
            spark.config().setReportName("FlightBooking API Test Results");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Add system info
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Aditya Singh");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}