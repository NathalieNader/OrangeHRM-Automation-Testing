import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PIM
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
    public void AddNewEmployee()
    {
        new PIMpage(driver).AddNewEmployee();
    }

    @Test
    public void SearchEmployeeInList()
    {
        new PIMpage(driver).AddNewEmployee().SearchEmployeeInList("Nath");
    }

    @Test
    public void SearchEmployee()
    {
        new PIMpage(driver).AddNewEmployee().SearchEmployee();
    }

    @Test
    public void EditEmployeeName()
    {
        new PIMpage(driver).EditEmployeeName();
    }

    @Test
    public void SearchEmployeeAfterEdit()
    {
        new PIMpage(driver).EditEmployeeName().SearchEmployeeInList("Jane");
    }
}
