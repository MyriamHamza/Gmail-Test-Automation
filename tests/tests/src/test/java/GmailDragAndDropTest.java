import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailDragAndDropTest {

    private WebDriver driver;
    private WebDriverWait wait;

 @Before
 public void setUp() throws MalformedURLException {
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--width=800");
 options.addArguments("--no-sandbox");
 driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
 driver.manage().window().maximize();
 wait = new WebDriverWait(driver, 20);
 }
    @Test
    public void testDragAndDropEmail() {
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

        // Find the first email in the Primary tab
        WebElement email = driver.findElement(By.xpath("//div[@role='main']//table//tbody//tr[1]"));

        // Find the target tab (e.g., the Promotions tab)
        WebElement targetTab = driver.findElement(By.xpath("//div[5]/table/tbody/tr[1]/td[2]"));

        // Perform the drag-and-drop operation
        Actions actions = new Actions(driver);
        actions.dragAndDrop(email, targetTab).build().perform();

        // Click on the target tab to open it
        targetTab.click();
        // Wait for the target tab to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div[1]/div/div/div[8]/div/div[2]/div[1]")));

    }

    @After
    public void tearDown() {
        // Close the Chrome driver
        if (driver != null) {
            driver.quit();
        }
    }
}
