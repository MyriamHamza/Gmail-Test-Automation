import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.lang.Exception;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailInputsAndButtonsTest extends GmailTestBase {
    @Test
    public void testFormFilling() {
        
        //sign in
        signIn();

        // Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']")));

        // Wait for the account menu to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nH aUx']")));

        WebElement settingsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gb']//div[@class='FI']")));
        settingsIcon.click();

        // Wait for the settings page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='IU nn']")));

        WebElement allsettingsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Tj']")));
        allsettingsButton.click();

        //------------------------------Radio buttons tests ----------------------------//

        // Wait for the settings page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nH bkK']")));

        // Find the radio for "Right-to-left editing support on" button element and interact with it
        WebElement radioButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[1]/input")));
        radioButton1.click();

        // Verify that the radio button is selected
        assertTrue(radioButton1.isSelected());

        // Find the radio for "Disable hover actions" button element and interact with it
        WebElement radioButton2 = driver.findElement(By.xpath("//tr[6]//table[2]//td[1]/input"));
        radioButton2.click();

        // Verify that the radio button is selected
        assertTrue(radioButton2.isSelected());

        //------------------------------Check Box buttons tests----------------------------//

        // Find the checkbox for "Enable input tools" button element and interact with it
        WebElement checkBoxButton1 = driver.findElement(By.xpath("//tr[1]/td[2]/div[2]/div/input"));
        if (checkBoxButton1.isSelected()) {
            checkBoxButton1.click();
            // Verify that the checkbox button is unselected
            assertFalse(checkBoxButton1.isSelected());
        } else {
            checkBoxButton1.click();
            // Verify that the checkbox button is selected
            assertTrue(checkBoxButton1.isSelected());
        }

        // Find the checkbox for "Suggest emails to reply to " button element and interact with it
        WebElement checkBoxButton2 = driver.findElement(By.xpath("//tr[17]/td[2]//tr[1]/td/input"));
        if (checkBoxButton2.isSelected()) {
            checkBoxButton2.click();
            // Verify that the checkbox button is unselected
            assertTrue(checkBoxButton2.isSelected());
        } else {
            checkBoxButton2.click();
            // Verify that the checkbox button is selected
            assertTrue(checkBoxButton2.isSelected());
        }
        //------------------------------ Drop down ----------------------------//

        // Find the language dropdown and interact with it
        Select languageDropdown = new Select(driver.findElement(By.xpath("//tr[1]/td[2]/div[1]/select")));

        // Scroll to the French option
        WebElement magyarOption = languageDropdown.getOptions().stream()
            .filter(option -> option.getText().equals("Magyar"))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Could not find Magyar option"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", magyarOption);

        // Select the French option
        languageDropdown.selectByVisibleText("Magyar");


        // Verify that the selected option is correct
        assertEquals("Magyar", languageDropdown.getFirstSelectedOption().getText());

        //------------------------------Text Input---------------------------------------//

        // Find the text input element and interact with it
        WebElement objectTextInput = driver.findElement(By.xpath("//tr[30]/td[2]//tbody/tr[4]/td[3]/input"));
        objectTextInput.sendKeys("test object input");

        // Verify that the text input contains the expected value
        assertEquals("test object input", objectTextInput.getAttribute("value"));

        //close
        driver.close();

    }
}