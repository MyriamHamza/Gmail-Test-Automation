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

public class GmailDragAndDropTest extends GmailTestBase {

    @Test
    public void testDragAndDropEmail() {
        
        //sign in
        signIn();

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