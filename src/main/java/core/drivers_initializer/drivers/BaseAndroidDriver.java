package core.drivers_initializer.drivers;

import io.appium.java_client.android.AndroidDriver;
import utils.PropertiesLoader;

import java.net.MalformedURLException;
import java.net.URL;

import static core.drivers_initializer.drivers.DriverConstants.ANDROID_CAPABILITIES;
import static core.drivers_initializer.drivers.DriverConstants.APPIUM_URL;
import static core.error_handlers.CustomErrorMessages.INVALID_APPIUM_URL_MESSAGE;
import static java.lang.System.getProperty;

public class BaseAndroidDriver extends AppiumMobileDriver<AndroidDriver> {

    public static final String WORKSPACE = getProperty("user.dir");

    @Override
    public AndroidDriver createDriver() {
        try {
            return new AndroidDriver(new URL(PropertiesLoader.readConfig(APPIUM_URL)), init(PropertiesLoader.readConfig(ANDROID_CAPABILITIES)));
        } catch (MalformedURLException e) {
            throw new RuntimeException(INVALID_APPIUM_URL_MESSAGE, e);
        }
    }
}