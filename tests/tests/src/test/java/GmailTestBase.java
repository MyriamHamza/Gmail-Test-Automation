import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailTestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    protected void signIn() {
        // Navigate to the Google account sign-in page
        driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));

        // Find the email field and enter your email address
        WebElement emailField = this.driver.findElement(By.id("identifierId"));
        emailField.sendKeys("misitestmail123@gmail.com");

        // Click the "Next" button
        WebElement nextButton = this.driver.findElement(By.id("identifierNext"));
        nextButton.click();

        // Wait for the next page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));

        WebElement passwordInput = this.driver.findElement(By.name("Passwd"));
        passwordInput.sendKeys("miyamo123");

        WebElement nextPasswordButton = this.driver.findElement(By.id("passwordNext"));
        nextPasswordButton.click();

        // Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']")));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
