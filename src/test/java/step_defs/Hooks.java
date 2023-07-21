package step_defs;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.drivers_initializer.instance.MobileDriverInstance;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {

//    public static AppiumMobileDriver appiumMobileDriver;
//
//
//    public void embedScreenshot(Scenario scenario) {
//        try {
//            final byte[] screenshot = ((TakesScreenshot) appiumMobileDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "embedScreenShot");
//        } catch (WebDriverException | NullPointerException e) {
//            System.out.println("Failed to take embed Screenshot");
//        }
//    }
//
//    /**
//     * Run once and then run again if the driver is null only
//     */
//    @Before(order = 1)
//    public void beforeScenario() {
//        if(appiumMobileDriver == null) {
//            appiumMobileDriver = MobileDriverInstance.getAppiumMobileDriver();
//            appiumMobileDriver.setup();
//        } else if (appiumMobileDriver.getDriver() == null){
//            appiumMobileDriver = MobileDriverInstance.getAppiumMobileDriver();
//            appiumMobileDriver.setup();
//        }
//    }
//
//    /**
//     * Run after every scenario
//     */
//    @After(order = 1)
//    public void afterScenario(Scenario scenario) {
//        embedScreenshot(scenario);
//        if(appiumMobileDriver != null && appiumMobileDriver.getDriver() != null) appiumMobileDriver.getDriver().resetApp();
//    }
//
//    /**
//     * Run after all scenarios
//     */
//    @AfterAll
//    public static void afterAll() {
//        if(appiumMobileDriver != null && appiumMobileDriver.getDriver() != null) {
//            appiumMobileDriver.getDriver().quit();
//            appiumMobileDriver.removeDriver();
//        }
//    }

    public static AppiumDriver appiumDriver;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "com.jeffprod.todo");
        caps.setCapability("appium:appActivity", "com.jeffprod.todo.ActivityMain");
        caps.setCapability("appium:noReset", "true");
        caps.setCapability("appium:autoGrantPermissions", "true");

        // create a new instance of the AppiumDriver with the desired capabilities
        appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }

}