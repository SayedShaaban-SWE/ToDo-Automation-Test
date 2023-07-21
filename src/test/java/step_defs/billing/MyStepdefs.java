package step_defs.billing;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.onboarding.OnboardingAbstract;
import pages.onboarding.OnboardingAndroidLogic;
import utils.loggers.Logger;

import static step_defs.Hooks.appiumDriver;

public class MyStepdefs{
    OnboardingAbstract onboardingAbstract;
    public MyStepdefs(){
        if (appiumDriver instanceof AndroidDriver){
            onboardingAbstract=new OnboardingAndroidLogic(appiumDriver);
        } else if (appiumDriver instanceof IOSDriver){
            onboardingAbstract=new OnboardingAndroidLogic(appiumDriver);
        }


    }

    @Given("I opened the app")
    public void openTheApp(){
        Assert.assertTrue(onboardingAbstract.isTheAppIsOpened()
                ,"The App is not opened");
        Logger.logger().info("The app is opened successfully");

    }

    @When("I click on burger icon")
    public void iClickOnBurgerIcon() {
        onboardingAbstract.clickOnBurgerIcon();
        Logger.logger().info("The burger icon list is opened successfully");

    }

    @And("I click on today")
    public void iClickOnToday() {
        onboardingAbstract.selectTodayFromBurgerIconList();
        Logger.logger().info("The today is selected");
    }

    @Then("I should see today word at the navbar")
    public void iShouldSeeTodayWordAtTheNavbar() {
        Assert.assertTrue(onboardingAbstract.isTodayIsDisplayedAtNavbar()
                ,"Today is not displayed in navbar");
    }

    @When("I click on plus button")
    public void iClickOnPlusButton() {
        onboardingAbstract.clickOnPlusBtn();
        Logger.logger().info("The 3rd level screen is opened");
    }

    @And("I enter title for thing i will do")
    public void iEnterTitleForThingIWillDo() {
        onboardingAbstract.enterTitle("Appium Studying");
        Logger.logger().info("The title is entered successfully");
    }

    @And("I enter note for thing i will do")
    public void iEnterNoteForThingIWillDo() {
        onboardingAbstract.enterNote("from basic to advanced");
        Logger.logger().info("The note is entered successfully");
    }

    @And("I click on tag")
    public void iClickOnTag() {
        onboardingAbstract.clickOnTagTxtBox();
        Logger.logger().info("The quick action menu for tag is displayed successfully");
    }

    @Then("I should see quick action menu displayed to select tag")
    public void iShouldSeeQuickActionMenuDisplayedToSelectTag() {
        Assert.assertTrue(onboardingAbstract.isQuickActionTagMenuDisplayed()
        ,"Menu is not displayed");
    }

    @And("I select work tag")
    public void iSelectWorkTag() {
        onboardingAbstract.clickOnWorkTag();
        Logger.logger().info("The work tag is selected successfully");
    }

    @And("I click on ok button")
    public void iClickOnOkButton() {
        onboardingAbstract.clickOnOk();
        Logger.logger().info("The quick action menu is closed successfully");
    }

    @When("I click on start date")
    public void iClickOnStartDate() {
        onboardingAbstract.clickOntStartDatePiker();
        Logger.logger().info("The date piker is displayed successfully");
    }

    @Then("I should see start date piker displayed")
    public void iShouldSeeStartDatePikerDisplayed() {
        Assert.assertTrue(onboardingAbstract.isStartDatePikerMenuDisplayed()
        ,"The Start date menu is not displayed");
    }

    @And("I select today from start date piker")
    public void iSelectTodayFromThePiker() {
        onboardingAbstract.selectStartDate();
        Logger.logger().info("The today is selected successfully");
    }

    @Then("I should see today selected")
    public void iShouldSeeTodaySelected() {
        Assert.assertEquals(onboardingAbstract.getTodayAttributeValue(),"Today"
        ,"The values are not match");
    }

    @When("I click on due date")
    public void iClickOnDueDate() {
        onboardingAbstract.clickOnDueDatePiker();
        Logger.logger().info("The date piker is displayed successfully");
    }

    @Then("I should see due date piker displayed")
    public void iShouldSeeDueDatePikerDisplayed() {
        Assert.assertTrue(onboardingAbstract.isDueDatePikerMenuDisplayed()
        ,"The due date menu is not displayed");
    }

    @When("I click on priority flag text field")
    public void iClickOnPriorityFlagTextField() {
        onboardingAbstract.clickOnPriorityTxtBox();
        Logger.logger().info("The quick action menu for priority is displayed successfully");
    }

    @Then("I should see quick action menu for priority displayed")
    public void iShouldSeePriorityQuickActionMenuDisplayed() {
        Assert.assertTrue(onboardingAbstract.isPriorityMenuDisplayed()
        ,"The priority menu is not displayed");
    }

    @And("I select medium priority")
    public void iSelectMediumPriority() {
        onboardingAbstract.selectMediumPriority();
        Logger.logger().info("I selected medium priority successfully");
    }

    @And("I click on save button")
    public void iClickOnSaveButton() {
        onboardingAbstract.clickOnSaveBtn();
        Logger.logger().info("I clicked on save button successfully");
    }

    @Then("I should see in today board card with the title i entered")
    public void iShouldSeeInTodayBoardCardWithTheTitleIEntered() {
        Assert.assertEquals(onboardingAbstract.getCardTitle(),"Appium Studying"
        ,"The title is not the same");
    }

    @And("I select today from due date piker")
    public void iSelectTodayFromDueDatePiker() {
        onboardingAbstract.selectDueDate();
        Logger.logger().info("The today is selected successfully");
    }

    @Given("I click on the card")
    public void iClickOnTheCard() {
        onboardingAbstract.clickOnCard();
        Logger.logger().info("The card with all details is displayed successfully");
    }

    @And("I select personal tag")
    public void iSelectPersonalTag() {
        onboardingAbstract.clickOnPersonalTag();
        Logger.logger().info("The personal tag is selected successfully");
    }

    @And("I select 31 from due date piker")
    public void iSelectFromDueDatePiker() {
        onboardingAbstract.select31DueDate();
        Logger.logger().info("The 31 day is selected successfully");
    }

    @Then("I should see mon 31 jul selected")
    public void iShouldSeeMonJulSelected() {
        Assert.assertEquals(onboardingAbstract.getJul31Value(),"Mon 31 Jul"
        ,"The two values are not identical");
    }

    @And("I click on update button")
    public void iClickOnUpdateButton() {
        onboardingAbstract.clickOnUpdateBtn();
        Logger.logger().info("The details of the card is updated successfully");
    }
}
