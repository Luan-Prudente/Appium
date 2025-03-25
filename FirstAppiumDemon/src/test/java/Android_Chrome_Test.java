import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
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
import java.util.concurrent.TimeUnit;

public class Android_Chrome_Test {

    private AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }

    @Test
    public void  userLogin(){
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement username = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']"));
        username.sendKeys("tomsmith");
        WebElement password = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']"));
        password.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElement(AppiumBy.className("android.widget.Button"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/positive_button"))).click();

        String secureArea = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Secure Area']")).getText();

        Assert.assertEquals(secureArea, "Secure Area");
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
