package TestCases;

import Pages.OracleEBSLoginPage;
import SuiteRunner.LoggerHelper;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import SuiteRunner.WebDriverManager;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

public class OracleEBSScenario implements ITestScenario{

    private WebDriver driver;
    private static final Logger log = LoggerHelper.getLogger(LoginScenario.class);
    private ExtentTest extentTest;

    public OracleEBSScenario() {
        this.driver = WebDriverManager.getDriver();
    }


    @Override
    public void executeScenario(WebDriver driver, ExtentTest extentTest, Map<String, String> data) {
        this.extentTest = extentTest;

        log.info("Executing scenario: " + data.get("CaseName"));
        try {
            Method method = this.getClass().getDeclaredMethod(data.get("CaseName"), WebDriver.class, Map.class);
            method.invoke(this, driver, data);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Scenario execution failed: " + data.get("CaseName"));
        }
    }


    private void loginToOracleEBS(WebDriver driver, Map<String, String> data) {
        OracleEBSLoginPage loginPage = new OracleEBSLoginPage(driver);
        loginPage.login(System.getProperty("username"), System.getProperty("password"));

    }
}