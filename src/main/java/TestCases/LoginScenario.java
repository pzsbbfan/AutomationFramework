package TestCases;

import Pages.LoginPage;
import Utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import SuiteRunner.LoggerHelper;
import SuiteRunner.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginScenario implements ITestScenario {

    private WebDriver driver;
    private static final Logger log = LoggerHelper.getLogger(LoginScenario.class);
    private ExtentTest extentTest;




    public LoginScenario() {
        this.driver = WebDriverManager.getDriver();
    }

    @Override
    public void executeScenario(WebDriver driver, ExtentTest extentTest, Map<String, String> data) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.extentTest = extentTest;

        log.info("Executing scenario: " + data.get("CaseName"));
        try {
            Method method = this.getClass().getDeclaredMethod(data.get("CaseName"), WebDriver.class, Map.class);
            method.invoke(this, driver, data);
        } catch (Exception e) {
            extentTest.fail("Scenario execution failed: " + data.get("CaseName") + " with exception: " + e.getMessage());
            extentTest.fail("Screenshot on failure: ", MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.tekeScreenShot(driver)).build());
            throw e;
        }
    }

    private void loginTest(WebDriver driver, Map<String, String> data) {
        log.info("test1  called");
        driver.get(System.getProperty("baseUrl"));
        log.info("Executing login test with data: " + data);
        extentTest.log(Status.INFO,"Hi  from loginTest1");
    }


    private void loginTest2(WebDriver driver, Map<String, String> data) throws IOException {

        log.info("Executing login test with data: " + data);
        int urlCounter;

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.amazon.ca");
        extentTest.log(Status.INFO,MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.tekeScreenShot(driver),"Homepage").build());



        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
        LoginPage loginPage =  new LoginPage(driver);



        List<WebElement> links = loginPage.getLinks();
        log.info("Page Header: "  +  loginPage.title.getText());
        log.info("account list:  "  +  loginPage.accountList.getText());


        //get # of urls
        urlCounter = links.size();


        links.forEach(link ->{
            if (!link.getText().isEmpty()){
                System.out.println(link.getText());
            }
        });
        System.out.println("Amazon Home page has total "+ urlCounter+" urls");

    }

    private void loginTest3(WebDriver driver, Map<String, String> data) {
        // Implement the second login test using the data
        log.info("Executing login test with data: " + data);
        extentTest.log(Status.INFO,"Hi  from loginTest3");
        driver.get(System.getProperty("baseUrl"));
        System.out.println(driver.getCurrentUrl());
    }


}
