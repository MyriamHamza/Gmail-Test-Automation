import java.net.MalformedURLException;
import java.net.URL;

import java.lang.Exception;
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

public class GmailLogOutTest extends GmailTestBase {
    @Test
    public void testSignOut() {
        signIn();

        // Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='navigation']")));

        // Click the user icon to open the account menu
        WebElement userIcon = driver.findElement(By.xpath("//div[3]/div[1]/div[2]"));
        userIcon.click();

        // Wait for the account menu to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='account']")));

        // Switch to the account menu iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='account']")));

        // Click the sign out button
        WebElement signOutButton = driver.findElement(By.xpath("//div[2]/div[2]/span/a"));
        signOutButton.click();

        //close
        driver.close();
    }
}
