import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class GmailUploadAttachementsEmail extends GmailTestBase {

    @Test
    public void testAttachFile() {
        //SIGN IN
        signIn();

        // Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']")));

        // Click the compose button to start a new email
        WebElement composeButton = driver.findElement(By.xpath("//div[contains(@class, 'T-I') and contains(@class, 'T-I-KE')]"));
        composeButton.click();

        // Wait for the compose window to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'M9')]")));


        WebElement fileInput = driver.findElement(By.xpath("//td[4]/div/input"));
        // Enter the path of the file to upload
        fileInput.sendKeys("/testingUploadFiles/file.txt");

        // Wait for the file to be uploaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[1]//div/a")));

        // Verify that the file was attached successfully
        WebElement attachment = driver.findElement(By.xpath("//tr[1]//div/a"));
        assertTrue(attachment.isDisplayed());
        //close
        driver.close();
    }
}