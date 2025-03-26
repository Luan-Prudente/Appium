package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends AbstractTestNGCucumberTests {

    public static AppiumDriver driver;

    public static void Android_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ToDo_1.24_APKPure.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }

    public static void iOS_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "iOS");
        caps.setCapability("appium:platformVersion", "14.2");
        caps.setCapability("appium:deviceName", "iPhone 12 mini");
        caps.setCapability("appium:appPackage", "com.jeffprod.todo");
        caps.setCapability("appium:appActivity", ".ActivityMain");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
    }

    public static void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
