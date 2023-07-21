package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

import java.util.List;
import java.util.Random;

public abstract class TextHelper {

    public static boolean checkElementNotEmptyFromListByIndex(List<WebElement> elementsList, int index) {
        boolean isElementEmpty = false;
        try {
            isElementEmpty = elementsList.get(index).getText().isEmpty();
            System.out.println(elementsList.get(index).getText());
            System.out.println(elementsList.get(index).getText().isEmpty());

        } catch (Exception e) {
            Logger.info("This" + elementsList.get(index).getText() + "is empty or not found" + e);
        }
        return isElementEmpty;
    }

    public static boolean checkElementFromListByName(List<WebElement> elementsList, String elementName) {
        boolean isElementNameExist = false;

        for (int i = 0; i <= elementsList.size() - 1; i++) {
            String name = elementsList.get(i).getText();
            if (name.equals(elementName)) {
                isElementNameExist = true;
                break;
            }
        }
        return isElementNameExist;
    }

    public static WebElement findElementUsingText(AppiumMobileDriver driver, String searchText) {
        return driver.findElement(By.xpath("//*[@text='" + searchText + "']"));
    }

    public static WebElement findElementUsingValue(AppiumMobileDriver driver, String searchText) {
        return driver.findElement(By.xpath("//*[@value='" + searchText + "']"));
    }

    public static String generateRandomName(AppiumMobileDriver driver) {
        Random random = new Random();
        String name = "Automation" + random.nextInt();
        return name;
    }
}