package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "a")
    List<WebElement> links;

    @FindBy(xpath = "//title[contains(text(),'Amazon.ca')]")
    public WebElement  title;

    @FindBy(id = "//*[@id=\"nav-link-accountList\"]")
    public WebElement  accountList;

    public List<WebElement> getLinks() {
        return links;
    }
}
