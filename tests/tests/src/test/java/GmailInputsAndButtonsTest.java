import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

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

public class GmailInputsAndButtonsTest {

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
public void testFormFilling() {
    
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
    WebElement radioButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=':68']")));
    radioButton1.click();

    // Verify that the radio button is selected
    assertTrue(radioButton1.isSelected());
    
    // Find the radio for "Disable hover actions" button element and interact with it
    WebElement radioButton2 = driver.findElement(By.id(":7m"));
    radioButton2.click();

    // Verify that the radio button is selected
    assertTrue(radioButton2.isSelected());



     //------------------------------Check Box buttons tests----------------------------//

    // Find the checkbox for "Enable input tools" button element and interact with it
    WebElement checkBoxButton1 = driver.findElement(By.id(":7r"));
    if (checkBoxButton1.isSelected()) {
        checkBoxButton1.click();
        // Verify that the checkbox button is unselected
        assertFalse(checkBoxButton1.isSelected());
    }else{
        checkBoxButton1.click();
        // Verify that the checkbox button is selected
        assertTrue(checkBoxButton1.isSelected());
    }

    // Find the checkbox for "Enable input tools" button element and interact with it
    WebElement checkBoxButton2 = driver.findElement(By.id(":8c"));
    if (checkBoxButton2.isSelected()) {
        checkBoxButton2.click();
        // Verify that the checkbox button is unselected
        assertFalse(checkBoxButton2.isSelected());
    }else{
        checkBoxButton2.click();
        // Verify that the checkbox button is selected
        assertTrue(checkBoxButton2.isSelected());
    }
//------------------------------ Drop down ----------------------------//


    // Find the language dropdown and interact with it
    Select languageDropdown = new Select(driver.findElement(By.id(":65")));

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

    // Scroll the page to bring the text input element into view
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("document.getElementById(':6w').scrollIntoView();");

    // Find the text input element and interact with it
    WebElement objectTextInput = driver.findElement(By.id(":6w"));
    objectTextInput.sendKeys("test object input");

    // Verify that the text input contains the expected value
    assertEquals("test object input", objectTextInput.getAttribute("value"));
    
}

 @After
 public void close() {
 if (driver != null) {
 driver.quit();
 }
 }

}
