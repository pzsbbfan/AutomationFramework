package SuiteRunner;
import SuiteRunner.SuiteRunner;
import TestCases.ITestScenario;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Keyword {
    private Properties config;
    private List<Map<String, String>> testData;
    private WebDriver driver;

    private ExtentTest extentTest;


    @DataProvider(name = "testDataProvider")
    public static Object[][] getData() {
        List<Map<String, String>> testData = SuiteRunner.testData;
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @BeforeClass
    public void setUp() {
        this.config = SuiteRunner.config;
        this.driver = WebDriverManager.getDriver();
        this.testData = SuiteRunner.testData;
    }

    @Test(dataProvider = "testDataProvider")
    public void executeTest(Map<String, String> data) {
        String testScenario = data.get("TestScenario");
        String testCaseName = data.get("CaseName");
        extentTest = ExtentManager.createTest(testCaseName, "Executing test case: " + testCaseName);
        try {
            ITestScenario scenarioInstance = getScenarioInstance(testScenario);
            scenarioInstance.executeScenario(driver,extentTest,data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        WebDriverManager.quitDriver();
    }

    private ITestScenario getScenarioInstance(String scenario) throws Exception {
        String className = "TestCases." + scenario +"Scenario";
        Class<?> scenarioClass = Class.forName(className);
        return (ITestScenario) scenarioClass.getDeclaredConstructor().newInstance();
    }

}

