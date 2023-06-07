import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

public class GmailDownloadAttachmentsTest extends GmailTestBase {

    @Test
    public void testDownloadAttachments()  {

        //sign in
        signIn();

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
    public void close() {
        // Close the Chrome driver
        if (driver != null) {
            driver.quit();
        }
    }
}