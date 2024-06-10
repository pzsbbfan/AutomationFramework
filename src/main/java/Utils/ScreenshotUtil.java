package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ScreenshotUtil {

    public static String tekeScreenShot(WebDriver driver) {
        String base64Screenshot  =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("screenshotPath");
        String destination = System.getProperty("user.dir") + screenshotPath + screenshotName + ".png";
        try {
            // Ensure the screenshots directory exists
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + screenshotPath));
            // Copy the screenshot to the destination path
            Files.copy(source.toPath(), Paths.get(destination));
            System.out.println("Screenshot saved at: " + destination); // Log the screenshot path
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
