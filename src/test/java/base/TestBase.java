package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import utils.ConfigLoader;

/**
 * The TestBase class provides a foundational setup for TestNG-based test execution.
 * It includes lifecycle methods for initializing and cleaning up resources,
 * ensuring a consistent testing environment. Additionally, it integrates with
 * ExtentReports for detailed test reporting.
 */
public class TestBase {
    // Shared ExtentReports instance for generating test reports
    protected static ExtentReports extent;

    // Thread-local storage for ExtentTest instances to ensure thread safety
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initializes the ExtentReports instance and sets the base URI for REST API testing.
     * This method is executed once before all tests in the suite.
     */
    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getInstance(); // Initialize ExtentReports
        String base = ConfigLoader.get("baseUrl"); // Load base URL from configuration
        RestAssured.baseURI = base; // Set the base URI for RestAssured
    }

    /**
     * Flushes the ExtentReports instance to finalize the report generation.
     * This method is executed once after all tests in the suite.
     */
    @AfterSuite
    public void afterSuite() {
        if (extent != null) extent.flush(); // Ensure all logs are written to the report
    }

    /**
     * Retrieves the current thread's ExtentTest instance.
     *
     * @return The ExtentTest instance associated with the current thread.
     */
    protected ExtentTest getTest() {
        return test.get();
    }

    /**
     * Creates a new ExtentTest instance for the current test method.
     * This method is executed before each test method.
     *
     * @param method The test method being executed.
     */
    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        test.set(extent.createTest(method.getName())); // Create a test entry in the report
    }

    /**
     * Logs the test result to the ExtentTest instance based on the test outcome.
     * This method is executed after each test method.
     *
     * @param result The result of the executed test method.
     */
    @AfterMethod
    public void afterMethod(org.testng.ITestResult result) {
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            getTest().fail(result.getThrowable()); // Log failure details
        } else if (result.getStatus() == org.testng.ITestResult.SUCCESS) {
            getTest().pass("Test passed"); // Log success message
        } else if (result.getStatus() == org.testng.ITestResult.SKIP) {
            getTest().skip("Test skipped"); // Log skipped message
        }
    }
}