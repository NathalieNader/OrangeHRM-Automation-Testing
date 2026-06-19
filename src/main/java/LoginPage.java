import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPage
{
    //webdriver
    private static WebDriver driver;

    //locators
    private static final By Username = By.cssSelector("[placeholder=\"Username\"]");
    private static final By Password = By.cssSelector("[name=\"password\"]");
    private static final By Button = By.cssSelector("[type=\"submit\"]");
    private final By msg = By.cssSelector("div > p.oxd-text.oxd-text--p.oxd-alert-content-text");
    private final By Req = By.cssSelector("div>span");
    private final By FilledField = By.cssSelector("[class=\"oxd-input oxd-input--active\"]");

    //Constructor
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //actions
    public void waiting(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public LoginPage Login(String username, String password)
    {
        waiting(Username);
        driver.findElement(Username).sendKeys(username);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(Button).click();
        return this;
    }

    public void isLoggedIn(String expectedURL)
    {
        Assert.assertTrue(driver.getCurrentUrl().equals(expectedURL));
    }

    public void msgassert(String expectedMessage)
    {
        waiting(msg);
        String actualMessage = driver.findElement(msg).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    public void ReqGet(String expectedMessage1, String expectedMessage2)
    {
        waiting(Req);
        List<WebElement> elements = driver.findElements(Req);
        WebElement FirstSpan = elements.get(0);
        WebElement SecondSpan = elements.get(1);
        String firstMessage = FirstSpan.getText();
        String secondMessage = SecondSpan.getText();
        Assert.assertTrue(firstMessage.contains(expectedMessage1));
        Assert.assertTrue(secondMessage.contains(expectedMessage2));
    }

    public void FilledField(String expectedMessage)
    {
        waiting(Req);
        String SpanMessage = driver.findElement(Req).getText();
        WebElement element = driver.findElement(FilledField);
        Assert.assertTrue(SpanMessage.contains(expectedMessage));
        Assert.assertFalse(element.getAttribute("value").isEmpty());
    }

}
