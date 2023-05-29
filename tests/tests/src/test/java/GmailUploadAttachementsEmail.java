import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

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

public class GmailUploadAttachementsEmail {

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
public void testAttachFile() {
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

    // Click the compose button to start a new email
    WebElement composeButton = driver.findElement(By.xpath("//div[contains(@class, 'T-I') and contains(@class, 'T-I-KE')]"));
    composeButton.click();

    // Wait for the compose window to appear
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'M9')]")));

    // Find the attach file button and click it
    WebElement attachFileButton = driver.findElement(By.id("//div[@aria-label='Attach files']"));
    attachFileButton.click();

    // Wait for the compose window to appear
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));

    // Use the Robot class to interact with the file upload dialog
    StringSelection filePath = new StringSelection("/path/to/file.txt");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
    
    try {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    } catch (AWTException e) {
        // Handle the exception
        e.printStackTrace();
    }

    // Wait for the file to be uploaded
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":1hb")));

    // Verify that the file was attached successfully
    WebElement attachment = driver.findElement(By.id(":1hb"));
    assertTrue(attachment.isDisplayed());
}

 @After
 public void close() {
 if (driver != null) {
 driver.quit();
 }
 }
}   
