import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class Drag_Drop_Test {

    public AndroidDriver driver;

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

    private void  drag(WebElement inicio, WebElement fim){

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence scroll =  new Sequence(finger,1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.fromElement(inicio),0,0))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.fromElement(fim),0,0))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(scroll));
    }

    @Test
    public void drag_drop(){

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement inicio = driver.findElement(By.id("drag_dot_1"));
        WebElement fim = driver.findElement(By.id("drag_dot_2"));

        drag(inicio,fim);
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
