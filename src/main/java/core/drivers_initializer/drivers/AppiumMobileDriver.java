package core.drivers_initializer.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;
import static utils.reading_helper.FileHelper.getFileAbsolutePath;
import static utils.readers.TextReader.readTxtFile;


/**
 * The type Mobile driver.
 */
public abstract class AppiumMobileDriver<D extends AppiumDriver> {

    private final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * This method initialize capabilities values and return Capabilities object
     * <p>
     * Instantiates a new Mobile driver.
     */

    @NotNull
    static DesiredCapabilities init(String capabilitiesFile) {
        if (isNotValid(capabilitiesFile))
            fail("Please provide a capabilitiesFile for mobile.");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        List<String> lines = readTxtFile(capabilitiesFile);
        if (isNullLines(lines))
            fail("Please provide capabilities file for mobile");

        for (String line : lines) {
            System.out.println("lines"+ line);
            @NonNls String key = line.split("=").length > 0 ? line.split("=")[0] : "";
            //System.out.println("key"+ key);
            String value = line.split("=").length > 1 ? line.split("=")[1] : "";
            //System.out.println("value" + value);
            // Check if app provided then return absolute path
            if (isApp(key) && !value.contains("cloud")) {
                value = getFileAbsolutePath(value, true);
                if (isNullValue(value) || value.isEmpty())
                    fail("Please provide the mobile app name or check if the app exists in your project resource folder");
            }
            if(key.contains("accessKey")) {
                if (System.getenv("AccessKey") != null) {
                    value = System.getenv("AccessKey");
                }
            }
            if (!key.isEmpty() && !value.isEmpty())
                desiredCapabilities.setCapability(key, value);
        }
        return desiredCapabilities;
    }


    private static boolean isNullValue(String value) {
        return value == null;
    }

    private static boolean isApp(String key) {
        return key.equalsIgnoreCase("app");
    }

    private static boolean isNullLines(List<String> lines) {
        return lines == null;
    }

    private static boolean isNotFound(String capabilitiesFile) {
        File file = new File(capabilitiesFile);
        return !file.exists();
    }

    private static boolean isNotValid(String capabilitiesFile) {
        return isNull(capabilitiesFile) || capabilitiesFile.isEmpty() || isNotFound(capabilitiesFile);
    }

    private static boolean isNull(String capabilitiesFile) {
        return capabilitiesFile == null;
    }

    abstract D createDriver();

    public AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    public AndroidDriver getAndroidDriver() {
        return (AndroidDriver) driverThreadLocal.get();
    }

    public IOSDriver getIosDriver() {
        return (IOSDriver) driverThreadLocal.get();
    }

    public void setup() {
        driverThreadLocal.set(createDriver());
    }

    public void removeDriver() {
        driverThreadLocal.remove();
    }

    public WebElement findElement(By by) {
        return getDriver().findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return getDriver().findElements(by);
    }
}