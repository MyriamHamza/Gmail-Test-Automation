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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class GmailBackButtonTest {

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
    public void testBackButton() {
        // Navigate to Gmail Home page
        driver.get("https://www.google.com/intl/fr/gmail/about/");

        // Click on the "Create account" link
        WebElement createAccountLink = driver.findElement(By.xpath("//a[@data-action='sign in']"));
        createAccountLink.click();

        // Click on the browser back button
        driver.navigate().back();

        // Verify that we are back on the Gmail sign-in page
        String expectedUrl = "https://www.google.com/intl/fr/gmail/about/";
        assertEquals(expectedUrl, driver.getCurrentUrl());
        //close
        driver.close();
    }

    @After
    public void close() {
        // Close the Chrome driver
        if (driver != null) {
            driver.quit();
        }
    }
}