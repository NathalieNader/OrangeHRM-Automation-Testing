import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PIMpage
{
    WebDriver driver;
    By PIMnavigate= By.cssSelector("li:nth-child(2) > a.oxd-main-menu-item");
    By Add = By.cssSelector("div:nth-child(1)>button.oxd-button");
    By FirstName = By.cssSelector("[name=\"firstName\"]");
    By LastName = By.cssSelector("[name=\"lastName\"]");
    By Submit = By.cssSelector("[type=\"submit\"]");
    By msg = By.cssSelector("div>div.oxd-toast--success");
    By NameSearchInList = By.cssSelector("div.oxd-table-cell:nth-child(3)>div");
    By NextBut = By.cssSelector("ul.oxd-pagination__ul>li>button>i.bi-chevron-right");
    By EmployeeList = By.cssSelector("li>a.oxd-topbar-body-nav-tab-item:nth-child(1)");
    By EmployeeNameSearch = By.cssSelector("div.oxd-autocomplete-text-input>input:nth-child(2)");
    By EditButton = By.cssSelector("div.oxd-table-cell-actions>button:nth-child(1)");
    By EditName = By.cssSelector("div>input.orangehrm-firstname");
    By SaveBtn = By.cssSelector("[type=\"submit\"]:nth-child(1)");

    public  PIMpage(WebDriver driver)
    {
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

    public PIMpage AddNewEmployee()
    {
        waiting(PIMnavigate);
        driver.findElement(PIMnavigate).click();
        waiting(Add);
        driver.findElement(Add).click();
        waiting(FirstName);
        driver.findElement(FirstName).sendKeys("Nath");
        driver.findElement(LastName).sendKeys("N");
        driver.findElement(Submit).click();
        waiting(msg);
        String message = driver.findElement(msg).getText();
        Assert.assertTrue(message.contains("Success"));
        return this;
    }


    public void SearchEmployee()
    {
        waiting(PIMnavigate);
        driver.findElement(PIMnavigate).click();
        waiting(EmployeeList);
        driver.findElement(EmployeeList).click();
        waiting(EmployeeNameSearch);
        var firstInput = driver.findElements(EmployeeNameSearch).get(0);
        firstInput.sendKeys("Nath");
        driver.findElement(Submit).click();
        waiting(NameSearchInList);
        String name = driver.findElement(NameSearchInList).getText();
        Assert.assertTrue(name.equals("Nath"));
    }

    public PIMpage EditEmployeeName()
    {
        waiting(PIMnavigate);
        driver.findElement(PIMnavigate).click();
        waiting(EditButton);
        var Edit = driver.findElements(EditButton).get(0);
        Edit.click();
        waiting(EditName);
        WebElement NameEdit = driver.findElement(EditName);
        NameEdit.click();
        NameEdit.sendKeys(Keys.CONTROL + "a");
        NameEdit.sendKeys(Keys.DELETE);
        NameEdit.sendKeys("Jane");
        driver.findElement(SaveBtn).click();
        waiting(msg);
        String message = driver.findElement(msg).getText();
        Assert.assertTrue(message.contains("Success"));
        Assert.assertEquals(NameEdit.getAttribute("value"), "Jane");
        return this;
    }

    public void SearchEmployeeInList(String targetName)
    {
        waiting(PIMnavigate);
        driver.findElement(PIMnavigate).click();

        waiting(EmployeeList);
        driver.findElement(EmployeeList).click();

        boolean found = false;

        while (!found)
        {
            waiting(NameSearchInList);
            var names = driver.findElements(NameSearchInList);

            for (var name : names)
            {
                if (name.getText().trim().equals(targetName))
                {
                    Assert.assertTrue(name.isDisplayed());
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                var next = driver.findElements(NextBut);

                if (next.isEmpty())
                {
                    break;
                }

                next.get(0).click();

                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.stalenessOf(names.get(0)));
            }
        }

        Assert.assertTrue(found, "Employee was not found");
    }
}
