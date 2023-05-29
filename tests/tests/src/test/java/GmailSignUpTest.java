import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;


import java.net.MalformedURLException;
import java.net.URL;

public class GmailSignUpTest {

   private WebDriver driver;
   private WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        }
@Test
public void testFormFilling() {
    // Navigate to the Google account sign-up page
    driver.get("https://accounts.google.com/signup/v2/webcreateaccount?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=SignUp");

    // Find the form elements and fill them out with test data
    WebElement firstNameInput = driver.findElement(By.id("firstName"));
    firstNameInput.sendKeys("Test");

    WebElement lastNameInput = driver.findElement(By.id("lastName"));
    lastNameInput.sendKeys("User");

    WebElement usernameInput = driver.findElement(By.id("username"));
    usernameInput.sendKeys("youtest0652");

    WebElement passwordInput = driver.findElement(By.name("Passwd"));
    passwordInput.sendKeys("testui123");

    WebElement confirmPasswordInput = driver.findElement(By.name("ConfirmPasswd"));
    confirmPasswordInput.sendKeys("testui123");

    // Click on confirm button
    WebElement nextButton1 = driver.findElement(By.xpath("//div/button"));
    nextButton1.click();
}

    

@After
public void close() {
    if (driver != null) {
        driver.quit();
        }
        }
}
