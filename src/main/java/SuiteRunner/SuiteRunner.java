package SuiteRunner;


import org.apache.logging.log4j.Logger;
import org.testng.TestNG;


import java.io.IOException;
import java.util.*;

public class SuiteRunner {

    private static final Logger log = LoggerHelper.getLogger(SuiteRunner.class);
    public static Properties config;
    public static List<Map<String, String>> testData;

    public static void main(String[] args) {

        log.info("Starting the test suite");
        //can implement Jenkins algorithm  here


        //can open DB connection to retrieve data from database for the testcase

        try {
            DataLoader.loadConfig("src/main/resources/config/application.properties");
            testData = DataLoader.loadTestData("src/main/resources/testdata.xlsx");
            System.setProperty("chromeDriverPath", System.getProperty("user.dir") + System.getProperty("chromeDriverPath"));

            // Initialize Extent Report
            ExtentManager.getInstance();

            // Instantiate TestNG and set the test classes
            TestNG testng = new TestNG();
            testng.setTestClasses(new Class[] {Keyword.class});
            testng.run();

        }  catch (IOException e) {
        log.error("Error during test suite execution", e);
    }

        log.info("Test suite execution completed");
}

}
