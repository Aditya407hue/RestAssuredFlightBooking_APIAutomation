package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import utils.ConfigLoader;

public class TestBase {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getInstance();
        String base = ConfigLoader.get("baseUrl");
        RestAssured.baseURI = base;
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) extent.flush();
    }

    protected ExtentTest getTest() {
        return test.get();
    }

    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        test.set(extent.createTest(method.getName()));
    }

    @AfterMethod
    public void afterMethod(org.testng.ITestResult result) {
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            getTest().fail(result.getThrowable());
        } else if (result.getStatus() == org.testng.ITestResult.SUCCESS) {
            getTest().pass("Test passed");
        } else if (result.getStatus() == org.testng.ITestResult.SKIP) {
            getTest().skip("Test skipped");
        }
    }
}
