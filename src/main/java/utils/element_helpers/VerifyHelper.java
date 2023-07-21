package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class VerifyHelper {

    public static void verifyElementNotExist(AppiumMobileDriver appiumMobileDriver, By locator) throws Exception {
        if (!appiumMobileDriver.getDriver().findElement(locator).isDisplayed()) {
            throw new Exception(appiumMobileDriver.getDriver().findElement(locator) + " (appiumMobileDriver.getDriver().findElement(locator) is present)");
        }
    }

    public static void verifyElementListIsEmpty(AppiumMobileDriver appiumMobileDriver, By elements) throws Exception {
        if (!appiumMobileDriver.getDriver().findElements(elements).isEmpty()) {
            throw new Exception(appiumMobileDriver.getDriver().findElement(elements) + " (Element is present)");
        }
    }



    public static boolean isElementDisplay( AppiumMobileDriver  appiumMobileDriver,By locator) {
        boolean isDisplyed = false;
        try {
            new WebDriverWait( appiumMobileDriver.getDriver(), 30).until(ExpectedConditions.visibilityOf(appiumMobileDriver.getDriver().findElement(locator)));
            isDisplyed = true;
        } catch (Exception e) {
            System.out.println("Element not Displayed " + locator);
            e.printStackTrace();
            isDisplyed = false;
        }
        return isDisplyed;
    }
}