package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OracleEBSLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "usernameField")
    WebElement username;

    @FindBy(id = "passwordField")
    WebElement password;

    @FindBy(id = "loginButton")
    WebElement loginButton;

    @FindBy(id = "accountsReceivableLink")
    WebElement accountsReceivableLink;

    @FindBy(id = "accountsPayableLink")
    WebElement accountsPayableLink;

    @FindBy(id = "reportsModuleLink")
    WebElement reportsModuleLink;

    @FindBy(id = "generateReportButton")
    WebElement generateReportButton;

    @FindBy(id = "pdfReportLink")
    WebElement pdfReportLink;

    public OracleEBSLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait timeout can be adjusted as needed
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }

    public void navigateToAccountsReceivable() {
        wait.until(ExpectedConditions.elementToBeClickable(accountsReceivableLink));
        accountsReceivableLink.click();
    }

    public void navigateToAccountsPayable() {
        wait.until(ExpectedConditions.elementToBeClickable(accountsPayableLink));
        accountsPayableLink.click();
    }

    public void navigateToReportsModule() {
        wait.until(ExpectedConditions.elementToBeClickable(reportsModuleLink));
        reportsModuleLink.click();
    }

    public void generateReport() {
        wait.until(ExpectedConditions.elementToBeClickable(generateReportButton));
        generateReportButton.click();
    }

    public void downloadPDFReport() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfReportLink));
        pdfReportLink.click();
    }
}
