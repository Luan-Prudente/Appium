import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Sending_Photos_Android_Test {

    public AndroidDriver driver;

    private static By BTN_BACKUP = By.id("onboarding_toggle");
    private static By BTN_BAKCUP_OFF = By.id("onboarding_action_button");
    private static By BTN_PERMISSION = AppiumBy.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']");
    private static By PHOTO = By.xpath("//android.widget.ImageView[contains(@content-desc,'Photo taken')]");

    File classPath, imageDir, img;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","16.0");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:appPackage", "com.google.android.apps.photos");
        caps.setCapability("appium:appActivity",".home.HomeActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
    }


    @Test
    public void send_Photo() throws IOException {
        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "/resources/images");
        img = new File(imageDir.getCanonicalFile(),"TAU-logo.png");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(BTN_BACKUP)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(BTN_BAKCUP_OFF)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(BTN_PERMISSION)).click();

        String Android_Photo_Path = "/sdcard/Pictures";
        driver.pushFile(Android_Photo_Path + "/" + img.getName(),img);

        ExpectedCondition condition = ExpectedConditions.visibilityOfElementLocated(PHOTO);
        wait.until(condition);
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
