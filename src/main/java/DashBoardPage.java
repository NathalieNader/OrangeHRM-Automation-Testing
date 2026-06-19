import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class DashBoardPage {
    WebDriver driver;

    By PageTitle= By.cssSelector("span>h6");
    By userDisplay = By.cssSelector("span>p");
    By MenuItems = By.cssSelector("a>span");
    By MenuItemsPic = By.cssSelector("a>svg");
    By Toggle = By.cssSelector("div.oxd-main-menu-search > button");
    By QuickLaunchIcons = By.cssSelector("div>button.orangehrm-quick-launch-icon>svg");
    By QuickLaunchText = By.cssSelector("[class=\"orangehrm-quick-launch-heading\"]");

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }
    public void waiting(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement FindElement(By element)
    {
        return driver.findElement(element);
    }

    public void AssertTrue(Boolean b)
    {
        Assert.assertTrue(b);
    }
    public void AssertFalse(Boolean b)
    {
        Assert.assertFalse(b);
    }

    public DashBoardPage PageLoadCorrect()
    {
        waiting(PageTitle);
        String Title = FindElement(PageTitle).getText();
        Assert.assertTrue(Title.contains("Dashboard"));
        return this;
    }

    public DashBoardPage AdminUsernameDisplayed()
    {
        waiting(userDisplay);
        WebElement usernameDisplay = FindElement(userDisplay);
        Assert.assertTrue(usernameDisplay.isDisplayed());
        return this;
    }

    public DashBoardPage MenuItemsVisibleText()
    {
        waiting(MenuItems);
        List<WebElement> elements = driver.findElements(MenuItems);
        for (WebElement element : elements)
        {
            AssertTrue(element.isDisplayed());
        }
        return this;
    }

    public DashBoardPage MenuItemsVisibleIcons()
    {
        List<WebElement> icons = driver.findElements(MenuItemsPic);

        for (WebElement element : icons)
        {
            AssertTrue(element.isDisplayed());
        }
        return this;
    }

    public DashBoardPage QuickLaunchVisible()
    {
        waiting(QuickLaunchIcons);
        List<WebElement> elements = driver.findElements(QuickLaunchText);
        for (WebElement element : elements)
        {
            Assert.assertTrue(element.isDisplayed());
        }
        List<WebElement> icons = driver.findElements(QuickLaunchIcons);

        for (WebElement element : icons)
        {
            Assert.assertTrue(element.isDisplayed());
        }
        return this;
    }

    public DashBoardPage MenuItemsVisibleTextFalse()
    {
        List<WebElement> elements = driver.findElements(MenuItems);
        for (WebElement element : elements)
        {
            AssertFalse(element.isDisplayed());
        }
        return this;
    }

    public DashBoardPage ToggleMenu()
    {
        waiting(Toggle);
        FindElement(Toggle).click();
        return this;
    }

}
