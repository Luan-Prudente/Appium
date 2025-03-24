import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Clipboard_Android_Test {

    AndroidDriver driver;

    private static By BTN_WITHOUT_ACC = By.id("signin_fre_dismiss_button");
    private static By BTN_GOT_IT = By.id("ack_button");
    private static By INPUT_SEARCH = By.id("search_box_text");
    private static By URL_BAR = By.id("url_bar");

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:appPackage", "com.android.chrome");
        caps.setCapability("appium:appActivity","com.google.android.apps.chrome.Main");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }

    @Test
    public void  clipboard_test(){

        String text = "Hello Tau";
        driver.setClipboardText(text);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(BTN_WITHOUT_ACC)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(BTN_GOT_IT)).click();
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(INPUT_SEARCH));
        searchBox.sendKeys(driver.getClipboardText());
        WebElement url = wait.until(ExpectedConditions.presenceOfElementLocated(URL_BAR));
        wait.until(ExpectedConditions.textToBePresentInElement(url, text));
        Assert.assertEquals(url.getText(), text);
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
