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

public class GmailLogOutTest {

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
public void testSignOut() {
    
    GmailSignInTest signInTest = new GmailSignInTest();
    try {
        // Sign in to Gmail
        signInTest.setUp();
        signInTest.signInTest();

    } catch (MalformedURLException e) {
        System.out.println("Opss... Fail to login in !!");
    }

    try {
        Thread.sleep(1000); // Sleep for 5 seconds
        } 
    catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Wait for the page to load
    signInTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='navigation']")));

    // Click the user icon to open the account menu
    WebElement userIcon = signInTest.driver.findElement(By.xpath("//*[@id='gb']//div/a[@class='gb_d gb_3a gb_v']"));
    userIcon.click();

// Wait for the account menu to appear
signInTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='account']")));

// Switch to the account menu iframe
signInTest.driver.switchTo().frame(signInTest.driver.findElement(By.xpath("//iframe[@name='account']")));

// Click the sign out button
WebElement signOutButton = signInTest.driver.findElement(By.xpath("//a[@class='V5tzAf jFfZdd']"));
signOutButton.click();
driver.close();

}


 @After
 public void close() {
 if (driver != null) {
 driver.quit();
 }
 }

}
