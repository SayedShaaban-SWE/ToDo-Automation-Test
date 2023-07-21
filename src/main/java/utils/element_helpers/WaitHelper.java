package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.interfaces.ImplicitHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.reading_helper.ImplicitTimeReader;
import utils.Logger;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class WaitHelper {

    public static void handleImplicit(WebDriver webDriver) {
        ImplicitHandler implicit = (webDriver1, time) -> setImplicit(webDriver1, time);
        implicit.handleImplicitTime(webDriver, ImplicitTimeReader.readTime());
    }

    //TODO remove
    private static void setImplicit(WebDriver webDriver, long time) {
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static boolean waitVisibility(AppiumMobileDriver appiumMobileDriver, By locator) {
        boolean isElementPresent = false;
        try {
            WebDriverWait wait = new WebDriverWait(appiumMobileDriver.getDriver(), 60);

            wait.until(ExpectedConditions.visibilityOf(appiumMobileDriver.getDriver().findElement(locator)));
            isElementPresent = true;
        } catch (Exception e) {
            Logger.info("The element on waitVisibility method not found " + e);
        }
        return isElementPresent;
    }

    public static boolean waitForInvisibility(AppiumMobileDriver appiumMobileDriver, By locator) {
        boolean isElementNotPresent = false;
        try {
            WebDriverWait wait = new WebDriverWait(appiumMobileDriver.getDriver(), 10);

            wait.until(ExpectedConditions.invisibilityOf(appiumMobileDriver.getDriver().findElement(locator)));
            isElementNotPresent = true;
        } catch (Exception e) {
            Logger.info("The element on waitVisibility method not found " + e);
        }
        return isElementNotPresent;
    }

    public static void waitElement(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}