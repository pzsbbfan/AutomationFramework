package TestCases;

import SuiteRunner.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface ITestScenario {
    void executeScenario(WebDriver driver, ExtentTest extentTest, Map<String, String> data) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
