import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

public class GmailDownloadAttachmentsTest {

    private WebDriver driver;
    private WebDriverWait wait;

 @Before
 public void setUp() throws MalformedURLException {
 ChromeOptions options = new ChromeOptions();
 //we try to add some configuration opton
 options.addArguments("--width=800");
 options.addArguments("--no-sandbox");
 driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
 driver.manage().window().maximize();
 wait = new WebDriverWait(driver, 20);
 }
    @Test
    public void testDownloadAttachments() {
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

        // Find the first email in the inbox that has multiple attachments
        WebElement email = driver.findElement(By.xpath("//div/div[2]/div/table/tbody/tr[1]"));
        // Click on the email to open it
        email.click();

        // Wait for the email to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Download all attachments']")));

        // Click on the "Download all attachments" button
        WebElement downloadAllButton = driver.findElement(By.xpath("//div[@aria-label='Download all attachments']"));
        downloadAllButton.click();
        

        // Verify that the files were downloaded successfully
        Path filePath = Paths.get("/Home/seluser/Downloads/attachment.zip");
        boolean fileExists = Files.exists(filePath);
        driver.close();
}

    @After
    public void tearDown() {
        // Close the Chrome driver
        if (driver != null) {
            driver.quit();
        }
    }
}
