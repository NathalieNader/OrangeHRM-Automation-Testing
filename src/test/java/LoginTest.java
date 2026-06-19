import Utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest
{
    WebDriver driver;
    JsonReader reader;

    @BeforeMethod
    public void setUp()
    {
        reader = new JsonReader("Data");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @Test
    public void ValidLogIn()
    {
        new LoginPage(driver).Login(reader.getJsonReader("username"),reader.getJsonReader("password"))
                .isLoggedIn("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void InvalidPassword()
    {
        new LoginPage(driver).Login(reader.getJsonReader("username"),reader.getJsonReader("InvalidPass")).msgassert("Invalid credentials");
    }

    @Test
    public void InvalidUsername()
    {
        new LoginPage(driver).Login(reader.getJsonReader("Invalidname"),reader.getJsonReader("password")).msgassert("Invalid credentials");
    }

    @Test
    public void EmptyUsernameAndPassword()
    {
        new LoginPage(driver).Login(reader.getJsonReader("Emptyname"),reader.getJsonReader("EmptyPass")).ReqGet("Required","Required");
    }

    @Test
    public void EmptyUsername()
    {
       new LoginPage(driver).Login(reader.getJsonReader("Emptyname"),reader.getJsonReader("password")).FilledField("Required");
    }

    @Test
    public void EmptyPassword()
    {
       new LoginPage(driver).Login(reader.getJsonReader("username"),reader.getJsonReader("EmptyPass")).FilledField("Required");
    }


}
