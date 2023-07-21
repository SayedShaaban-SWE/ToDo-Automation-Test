package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.drivers_initializer.drivers.BaseAndroidDriver;
import core.drivers_initializer.drivers.BaseIOSDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public abstract class ActionsHelper {

    public static By ANDROID_LOCATION_FIELD = By.name("Location");
    public static By ANDROID_LOCATION_TOGGLE = By.id("switch_widget");
    public static By ANDROID_Biometrics = By.name("Biometrics and security");
    public static By IOS_SETTINGS = By.id("Settings");
    public static By IOS_PRIVACY = By.id("Privacy");
    public static By IOS_LOCATION_SERVICES_FIELD = By.id("Location Services");
    public static By IOS_LOCATION_SERVICES_TOGGLE = By.className("UIASwitch");
    public static By IOS_TURN_OFF_LOCATION_BUTTON = By.id("Turn Off");
    public static By IOS_NATIVE_POPUP = By.id("Allow While Using App");
    public static By IOS_ALLOW_ONCE_LOCATION_PERMISSION_OPTION = By.id("Allow Once");
    public static By ANDROID_NATIVE_POPUP_OPTIONS_APP_INFO = By.xpath("//*[@text='Only this time']");

    /*Check single element is clickable*/
    public static boolean isClickable(AppiumMobileDriver appiumMobileDriver, By locator, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumMobileDriver.getDriver(), time);
            wait.until(ExpectedConditions.elementToBeClickable(appiumMobileDriver.getDriver().findElement(locator)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*Check element on list is clickable*/
    public static boolean isClickable(AppiumMobileDriver appiumMobileDriver, By locator, int index, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumMobileDriver.getDriver(), time);
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) appiumMobileDriver.getDriver().findElements(locator).get(index)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*Click on single element */
    public static void clickWhileVisible(AppiumMobileDriver appiumMobileDriver, By locator) {
        WaitHelper.waitVisibility(appiumMobileDriver, locator);
        appiumMobileDriver.getDriver().findElement(locator).click();
    }

    /*Click on element from list */
    public static void clickWhileVisible(AppiumMobileDriver appiumMobileDriver, By locator, int index) {
        WaitHelper.waitVisibility(appiumMobileDriver, locator);
        WebElement element = (WebElement) appiumMobileDriver.getDriver().findElements(locator).get(index);
        element.click();
    }

    /*Send keys to element*/
    public static void setTextWhileVisible(AppiumMobileDriver appiumMobileDriver, By locator, String text) {
        WaitHelper.waitVisibility(appiumMobileDriver, locator);
        appiumMobileDriver.getDriver().findElement(locator).sendKeys(text);

    }

    /*Get Text from element */
    public static String getTextWhileVisible(AppiumMobileDriver appiumMobileDriver, By locator) {
        WaitHelper.waitVisibility(appiumMobileDriver, locator);
        return appiumMobileDriver.getDriver().findElement(locator).getText();

    }

    public static void moveToElementAndClick(AppiumMobileDriver appiumMobileDriver, By locator) {
        try {
            Actions actions = new Actions(appiumMobileDriver.getDriver());
            actions.moveToElement(appiumMobileDriver.getDriver().findElement(locator)).click().perform();
        } catch (Exception e) {
            Logger.info("can't perform move and click element action" + e);
            throw new RuntimeException(e);
        }
    }

    public static void enter(AppiumMobileDriver driver) {
        Actions actions = new Actions(driver.getDriver());
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public static void hideKeyboard(AppiumMobileDriver driver) {
        if (driver instanceof BaseAndroidDriver) {
            driver.getAndroidDriver().hideKeyboard();
        } else if (driver instanceof BaseIOSDriver) {
            driver.getDriver().findElement(By.name("Done")).click();
        } else {
            driver.getDriver().hideKeyboard();
        }
    }


    public static void doTap(AppiumMobileDriver driver, Point point, int duration) {
        PointerInput FINGER = new PointerInput(TOUCH, "finger");
        Sequence tap = new Sequence(FINGER, 1).addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY())).addAction(FINGER.createPointerDown(LEFT.asArg())).addAction(new Pause(FINGER, ofMillis(duration))).addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.getDriver().perform(Arrays.asList(tap));
    }

    public static String getAttributeValue(AppiumMobileDriver appiumMobileDriver, String attribute, By locator) {
        String value = appiumMobileDriver.getDriver().findElement(locator).getAttribute(attribute);
        return value;
    }

    public void setContext(AppiumMobileDriver driver, ContextEnum contextEnum) {

        if (driver instanceof BaseAndroidDriver) {
            if (contextEnum.equals(ContextEnum.NATIVE_APP)) {
                driver.getAndroidDriver().context(contextEnum.toString());
            } else {
                Set<String> contextNames = driver.getAndroidDriver().getContextHandles();
                driver.getAndroidDriver().context((String) contextNames.toArray()[1]);
            }
        } else {
            if (driver instanceof BaseIOSDriver) {
                if (contextEnum.equals(ContextEnum.NATIVE_APP)) {
                    driver.getIosDriver().context(contextEnum.toString());
                } else {
                    Set<String> contextNames = driver.getIosDriver().getContextHandles();
                    driver.getIosDriver().context((String) contextNames.toArray()[1]);
                }
            }
        }

    }

    //Open Android device settings
    public static void navigateToAndroidDeviceSettings(AppiumMobileDriver appiumMobileDriver) {
        try {
            ((AndroidDriver) appiumMobileDriver.getDriver()).startActivity(new Activity("com.android.settings", ".Settings"));
        } catch (Exception e) {
            System.out.println("Start Activity Exception");
        }
    }

    //turn on WIFI on Android
    public static void turnOnWIFI(AppiumMobileDriver appiumMobileDriver) {
        System.out.println("WIFI connection" + ((AndroidDriver) appiumMobileDriver.getDriver()).getConnection().isWiFiEnabled());
        if (!((AndroidDriver) appiumMobileDriver.getDriver()).getConnection().isWiFiEnabled()) {
            ((AndroidDriver) appiumMobileDriver.getDriver()).toggleWifi();
        }
    }

    public static void navigateToIOSDeviceSettings(AppiumMobileDriver appiumMobileDriver) {
        scrollHorizontalToInvisibleElement(appiumMobileDriver, IOS_SETTINGS);
        WaitHelper.waitVisibility(appiumMobileDriver, IOS_SETTINGS);
        clickWhileVisible(appiumMobileDriver, IOS_SETTINGS);
    }

    //Navigate to android device location
    public static void navigateToAndroidDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        ScrollHelper.scrollToElement(appiumMobileDriver, ANDROID_LOCATION_FIELD);

        if (!VerifyHelper.isElementDisplay(appiumMobileDriver, ANDROID_LOCATION_FIELD)) {
            ScrollHelper.scrollUp(appiumMobileDriver, ANDROID_Biometrics);
            ActionsHelper.clickWhileVisible(appiumMobileDriver, ANDROID_Biometrics);
            ScrollHelper.scrollToElement(appiumMobileDriver, ANDROID_LOCATION_FIELD);
        } else {
            WaitHelper.waitVisibility(appiumMobileDriver, ANDROID_LOCATION_FIELD);
            ActionsHelper.clickWhileVisible(appiumMobileDriver, ANDROID_LOCATION_FIELD);
        }

    }

    public static void navigateToIOSDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        if (VerifyHelper.isElementDisplay(appiumMobileDriver, IOS_LOCATION_SERVICES_TOGGLE) && VerifyHelper.isElementDisplay(appiumMobileDriver, IOS_LOCATION_SERVICES_FIELD))
            return;
        else {
            ScrollHelper.scrollToElement(appiumMobileDriver, IOS_PRIVACY);
            if (!VerifyHelper.isElementDisplay(appiumMobileDriver, IOS_PRIVACY)) {
                ScrollHelper.scrollUp(appiumMobileDriver, IOS_PRIVACY);
                clickWhileVisible(appiumMobileDriver, IOS_PRIVACY);
                WaitHelper.waitVisibility(appiumMobileDriver, IOS_LOCATION_SERVICES_FIELD);
                clickWhileVisible(appiumMobileDriver, IOS_LOCATION_SERVICES_FIELD);
            } else {
                clickWhileVisible(appiumMobileDriver, IOS_PRIVACY);
                WaitHelper.waitVisibility(appiumMobileDriver, IOS_LOCATION_SERVICES_FIELD);
                clickWhileVisible(appiumMobileDriver, IOS_LOCATION_SERVICES_FIELD);
            }
        }
    }

    public static void removeApp(AppiumMobileDriver appiumMobileDriver, String appName) {
        try {
            appiumMobileDriver.getDriver().removeApp(appName);
            appiumMobileDriver.removeDriver();
        } catch (Exception e) {
            appiumMobileDriver.removeDriver();
        }
    }

    //get app from background
    public static void getAppFromBackground(AppiumMobileDriver appiumMobileDriver) {

        appiumMobileDriver.getDriver().runAppInBackground(Duration.ofSeconds(1));
    }

    public static void goBackAndroid(AppiumMobileDriver appiumMobileDriver) {

        appiumMobileDriver.getDriver().navigate().back();
    }

    public static void sendAppToBackground(AppiumMobileDriver appiumMobileDriver) {
        appiumMobileDriver.getDriver().runAppInBackground(Duration.ofSeconds(-1));
    }

    public static void enableAndroidLocationToggle(AppiumMobileDriver appiumMobileDriver) {
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "checked", ANDROID_LOCATION_TOGGLE).equals("false"))
            clickOnDeviceToggle(appiumMobileDriver, ANDROID_LOCATION_TOGGLE);
        else {
            System.out.println("Location Already Enabled");
        }
    }

    public static void disableAndroidLocationToggle(AppiumMobileDriver appiumMobileDriver) {
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "checked", ANDROID_LOCATION_TOGGLE).equals("true")) {
            WaitHelper.waitElement(90);
            clickOnDeviceToggle(appiumMobileDriver, ANDROID_LOCATION_TOGGLE);
        } else {
            System.out.println("Location Already Disables");
        }
    }

    //Turn off device Location
    public static void turnOffAndroidDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        navigateToAndroidDeviceSettings(appiumMobileDriver);
        navigateToAndroidDeviceLocation(appiumMobileDriver);
        disableAndroidLocationToggle(appiumMobileDriver);
        getAppFromBackground(appiumMobileDriver);
    }

    public static void turnOnAndroidDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        navigateToAndroidDeviceSettings(appiumMobileDriver);
        navigateToAndroidDeviceLocation(appiumMobileDriver);
        enableAndroidLocationToggle(appiumMobileDriver);
        getAppFromBackground(appiumMobileDriver);
    }

    public static void clickOnDeviceToggle(AppiumMobileDriver appiumMobileDriver, By Locator) {
        WaitHelper.waitVisibility(appiumMobileDriver, Locator);
        clickWhileVisible(appiumMobileDriver, Locator);
    }

    public static void turnOffIOSDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        sendAppToBackground(appiumMobileDriver);
        navigateToIOSDeviceSettings(appiumMobileDriver);
        navigateToIOSDeviceLocation(appiumMobileDriver);
        disableIOSLocationToggle(appiumMobileDriver);
        getAppFromBackground(appiumMobileDriver);

    }

    public static void turnOnIOSDeviceLocation(AppiumMobileDriver appiumMobileDriver) {
        sendAppToBackground(appiumMobileDriver);
        navigateToIOSDeviceSettings(appiumMobileDriver);
        navigateToIOSDeviceLocation(appiumMobileDriver);
        enableIOSLocationToggle(appiumMobileDriver);
        getAppFromBackground(appiumMobileDriver);

    }

    public static void disableIOSLocationToggle(AppiumMobileDriver appiumMobileDriver) {
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "value", IOS_LOCATION_SERVICES_TOGGLE).equals("1")) {
            clickOnDeviceToggle(appiumMobileDriver, IOS_LOCATION_SERVICES_TOGGLE);
            WaitHelper.waitVisibility(appiumMobileDriver, IOS_TURN_OFF_LOCATION_BUTTON);
            clickWhileVisible(appiumMobileDriver, IOS_TURN_OFF_LOCATION_BUTTON);
        } else {
            System.out.println("Location Already Disabled");
        }
    }

    public static void enableIOSLocationToggle(AppiumMobileDriver appiumMobileDriver) {
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "value", IOS_LOCATION_SERVICES_TOGGLE).equals("0"))
            clickOnDeviceToggle(appiumMobileDriver, IOS_LOCATION_SERVICES_TOGGLE);
        else {
            System.out.println("Location Already Disabled");
        }
    }

    public static void scrollHorizontalToInvisibleElement(AppiumMobileDriver appiumMobileDriver, By locator) {
        TouchAction action = new TouchAction(appiumMobileDriver.getDriver());
        Dimension size = appiumMobileDriver.getDriver().manage().window().getSize();
        int endx = (int) (size.width * 0.8);
        int endy = size.height - size.height / 4;

        int startx = (int) (size.width * 0.2);
        int starty = size.height - size.height / 4;

        while (!VerifyHelper.isElementDisplay(appiumMobileDriver, locator)) {

            action.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endx, endy)).release().perform();
        }
    }

    //Kill the app
    public static void killApp(AppiumMobileDriver appiumMobileDriver) {
        appiumMobileDriver.getDriver().closeApp();

    }

    public static void killAppAndReopen(AppiumMobileDriver appiumMobileDriver) {

        appiumMobileDriver.getDriver().closeApp();
        appiumMobileDriver.getDriver().runAppInBackground(Duration.ofSeconds(1));
        getAppFromBackground(appiumMobileDriver);
        //appiumMobileDriver.getDriver().launchApp();

    }

    //Reopen the app
    public static void ReopenApp(AppiumMobileDriver appiumMobileDriver) {
        appiumMobileDriver.getDriver().launchApp();
    }

    private enum ContextEnum {
        NATIVE_APP("NATIVE_APP"), WEBVIEW("WEBVIEW");
        private final String contextEnum;

        ContextEnum(String context) {
            this.contextEnum = context;
        }
    }

    public static void allowOnceLocationPermissionForIOS(AppiumMobileDriver appiumMobileDriver) {
        WaitHelper.waitVisibility(appiumMobileDriver, IOS_ALLOW_ONCE_LOCATION_PERMISSION_OPTION);
        clickWhileVisible(appiumMobileDriver, IOS_ALLOW_ONCE_LOCATION_PERMISSION_OPTION);
    }

    public static boolean checkDeviceLocationOnOrOFFAndroid(AppiumMobileDriver appiumMobileDriver) {
        navigateToAndroidDeviceSettings(appiumMobileDriver);
        navigateToAndroidDeviceLocation(appiumMobileDriver);
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "checked", ANDROID_LOCATION_TOGGLE).equals("true")) {
            return true;
        } else {
            System.out.println("Location off");
            return false;
        }
    }

    public static boolean checkDeviceLocationOnOrOFFiOS(AppiumMobileDriver appiumMobileDriver) {
        sendAppToBackground(appiumMobileDriver);
        navigateToIOSDeviceSettings(appiumMobileDriver);
        navigateToIOSDeviceLocation(appiumMobileDriver);
        if (ActionsHelper.getAttributeValue(appiumMobileDriver, "value", IOS_LOCATION_SERVICES_TOGGLE).equals("0")) {
            System.out.println("Location off");
            return false;
        } else
            return true;
    }

    /**
     * @param appiumMobileDriver
     * @param partialID
     * @param index
     * @return Applied to elements that have ID then index. Ex: QuickLinks1
     */
    public static By getElementUsingIdIndex(AppiumMobileDriver appiumMobileDriver, String partialID, int index) {
        WaitHelper.waitVisibility(appiumMobileDriver, By.id(partialID + index));
        return By.id(partialID + index);
    }

    /**
     * @param appiumMobileDriver
     * @param partialID
     * @return Applied to elements that have ID then index. Ex: QuickLinks1
     */
    public static int getSizeOfElementsWithIdIndex(AppiumMobileDriver appiumMobileDriver, String partialID) {
        WaitHelper.waitVisibility(appiumMobileDriver, By.xpath("//*[contains(@id, '" + partialID + "')]"));
        //Added "-1" because the title will return as part of this ID
        return appiumMobileDriver.getDriver().findElements(By.xpath("//*[contains(@id, '" + partialID + "')]")).size()-1;
    }

    /**
     * @param appiumMobileDriver
     * @param containText
     * @return Applied to elements that have ID then index. Ex: QuickLinks1
     */
    public static int getElementIndexFromListOfPartialId(AppiumMobileDriver appiumMobileDriver, String partialID, String containText) {
        int index = 0;
        for (int i = 1; i <= getSizeOfElementsWithIdIndex(appiumMobileDriver, partialID); i++) {
            if (getTextWhileVisible(appiumMobileDriver, getElementUsingIdIndex(appiumMobileDriver, partialID, i)).toLowerCase().contains(containText.toLowerCase())) {
                index = i;
            }
        }
        return index;
    }
}