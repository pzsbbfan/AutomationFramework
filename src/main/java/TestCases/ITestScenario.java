package TestCases;

import SuiteRunner.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public interface ITestScenario {
    void executeScenario(WebDriver driver, ExtentTest extentTest, Map<String, String> data);
}
