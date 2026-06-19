import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DashBoard
{
    WebDriver driver;

    @BeforeMethod
    public void Login()
    {
        LoginTest loginTest = new LoginTest();
        loginTest.setUp();       // this creates the driver
        loginTest.ValidLogIn();  // this logs in
        driver = loginTest.driver;
    }

    @Test
    public void PageLoadCorrect()
    {
        new DashBoardPage(driver).PageLoadCorrect();
    }

    @Test
    public void AdminUsernameDisplayed()
    {
        new DashBoardPage(driver).AdminUsernameDisplayed();
    }

    @Test
    public void MenuItemsVisibleBeforeToggle()
    {
        new DashBoardPage(driver).MenuItemsVisibleText().MenuItemsVisibleIcons();
    }

    @Test
    public void MenuItemsVisibleAfterToggle()
    {
        new DashBoardPage(driver).ToggleMenu().MenuItemsVisibleTextFalse().MenuItemsVisibleIcons();
    }

    @Test
    public void QuickLaunchVisible()
    {
        new DashBoardPage(driver).QuickLaunchVisible();
    }
}
