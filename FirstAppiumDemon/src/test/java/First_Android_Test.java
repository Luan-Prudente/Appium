import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class First_Android_Test {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }

    @Test
    public void click_App_Button(){
        driver.findElement(AppiumBy.accessibilityId("App")).click();
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
