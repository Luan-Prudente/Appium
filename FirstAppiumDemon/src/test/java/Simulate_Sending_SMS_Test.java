import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Simulate_Sending_SMS_Test {

    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:appPackage", "com.google.android.apps.messaging");
        caps.setCapability("appium:appActivity",".ui.ConversationListActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }

    @Test
    public void  send_SMS(){
        driver.sendSMS("555-123-4567","Hello from TAU");
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
