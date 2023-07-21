package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.onboarding.OnboardingAbstract;
import pages.onboarding.OnboardingAndroidLogic;
import utils.loggers.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestCases {
    public AppiumDriver appiumDriver;
    OnboardingAbstract onboardingAbstract;

    @BeforeTest
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
    @Test
    public void addNewCard(){
        onboardingAbstract=new OnboardingAndroidLogic(appiumDriver);
        Assert.assertTrue(onboardingAbstract.isTheAppIsOpened()
                ,"The App is not opened");
        Logger.logger().info("The app is opened successfully");

        onboardingAbstract.clickOnBurgerIcon();
        Logger.logger().info("The burger icon list is opened successfully");

        onboardingAbstract.selectTodayFromBurgerIconList();
        Logger.logger().info("The today is selected");

        Assert.assertTrue(onboardingAbstract.isTodayIsDisplayedAtNavbar()
                ,"Today is not displayed in navbar");

        onboardingAbstract.clickOnPlusBtn();
        Logger.logger().info("The 3rd level screen is opened");

        onboardingAbstract.enterTitle("Appium Studying");
        Logger.logger().info("The title is entered successfully");

        onboardingAbstract.enterNote("from basic to advanced");
        Logger.logger().info("The note is entered successfully");

        onboardingAbstract.clickOnTagTxtBox();
        Logger.logger().info("The quick action menu for tag is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isQuickActionTagMenuDisplayed()
                ,"Menu is not displayed");

        onboardingAbstract.clickOnWorkTag();
        Logger.logger().info("The work tag is selected successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        onboardingAbstract.clickOntStartDatePiker();
        Logger.logger().info("The date piker is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isStartDatePikerMenuDisplayed()
                ,"The Start date menu is not displayed");

        onboardingAbstract.selectStartDate();
        Logger.logger().info("The today is selected successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        Assert.assertEquals(onboardingAbstract.getTodayAttributeValue(),"Today"
                ,"The values are not match");

        onboardingAbstract.clickOnDueDatePiker();
        Logger.logger().info("The date piker is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isDueDatePikerMenuDisplayed()
                ,"The due date menu is not displayed");

        onboardingAbstract.selectDueDate();
        Logger.logger().info("The today is selected successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        Assert.assertEquals(onboardingAbstract.getTodayAttributeValue(),"Today"
                ,"The values are not match");

        onboardingAbstract.clickOnPriorityTxtBox();
        Logger.logger().info("The quick action menu for priority is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isPriorityMenuDisplayed()
                ,"The priority menu is not displayed");

        onboardingAbstract.selectMediumPriority();
        Logger.logger().info("I selected medium priority successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        onboardingAbstract.clickOnSaveBtn();
        Logger.logger().info("I clicked on save button successfully");

        Assert.assertEquals(onboardingAbstract.getCardTitle(),"Appium Studying"
                ,"The title is not the same");

    }
    @Test
    public void updateCard(){
        onboardingAbstract=new OnboardingAndroidLogic(appiumDriver);
        onboardingAbstract.clickOnCard();
        Logger.logger().info("The card with all details is displayed successfully");

        onboardingAbstract.clickOnTagTxtBox();
        Logger.logger().info("The quick action menu for tag is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isQuickActionTagMenuDisplayed()
                ,"Menu is not displayed");

        onboardingAbstract.clickOnPersonalTag();
        Logger.logger().info("The personal tag is selected successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        onboardingAbstract.clickOnDueDatePiker();
        Logger.logger().info("The date piker is displayed successfully");

        Assert.assertTrue(onboardingAbstract.isDueDatePikerMenuDisplayed()
                ,"The due date menu is not displayed");

        onboardingAbstract.select31DueDate();
        Logger.logger().info("The 31 day is selected successfully");

        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");

        Assert.assertEquals(onboardingAbstract.getJul31Value(),"Mon 31 Jul"
                ,"The two values are not identical");

        onboardingAbstract.clickOnUpdateBtn();
        Logger.logger().info("The details of the card is updated successfully");

    }


    @AfterTest
    public void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
