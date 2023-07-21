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
        Logger.logger().info("sdvdebefbebebebebebebebeebe");

    }

    @When("I click on burger icon")
    public void iClickOnBurgerIcon() {
        onboardingAbstract.clickOnBurgerIcon();
        Logger.logger().warn("sdvdebefbebebebebebebebeebe");

    }

    @And("I click on today")
    public void iClickOnToday() {
        onboardingAbstract.selectTodayFromBurgerIconList();
    }

    @Then("I should see today word at the navbar")
    public void iShouldSeeTodayWordAtTheNavbar() {
        Assert.assertTrue(onboardingAbstract.isTodayIsDisplayedAtNavbar()
                ,"Today is not displayed in navbar");
    }

    @When("I click on plus button")
    public void iClickOnPlusButton() {
        onboardingAbstract.clickOnPlusBtn();
    }

    @And("I enter title for thing i will do")
    public void iEnterTitleForThingIWillDo() {
        onboardingAbstract.enterTitle("Appium Studying");
    }

    @And("I enter note for thing i will do")
    public void iEnterNoteForThingIWillDo() {
        onboardingAbstract.enterNote("from basic to advanced");
    }

    @And("I click on tag")
    public void iClickOnTag() {
        onboardingAbstract.clickOnTagTxtBox();
    }

    @Then("I should see quick action menu displayed to select tag")
    public void iShouldSeeQuickActionMenuDisplayedToSelectTag() {
        Assert.assertTrue(onboardingAbstract.isQuickActionTagMenuDisplayed()
        ,"Menu is not displayed");
    }

    @And("I select work tag")
    public void iSelectWorkTag() {
        onboardingAbstract.clickOnWorkTag();
    }

    @And("I click on ok button")
    public void iClickOnOkButton() {
        onboardingAbstract.clickOnOk();
    }

    @When("I click on start date")
    public void iClickOnStartDate() {
        onboardingAbstract.clickOntStartDatePiker();
    }

    @Then("I should see start date piker displayed")
    public void iShouldSeeStartDatePikerDisplayed() {
        Assert.assertTrue(onboardingAbstract.isStartDatePikerMenuDisplayed()
        ,"The Start date menu is not displayed");
    }

    @And("I select today from start date piker")
    public void iSelectTodayFromThePiker() {
        onboardingAbstract.selectStartDate();
    }

    @Then("I should see today selected")
    public void iShouldSeeTodaySelected() {
        Assert.assertEquals(onboardingAbstract.getTodayAttributeValue(),"Today"
        ,"The values are not match");
    }

    @When("I click on due date")
    public void iClickOnDueDate() {
        onboardingAbstract.clickOnDueDatePiker();
    }

    @Then("I should see due date piker displayed")
    public void iShouldSeeDueDatePikerDisplayed() {
        Assert.assertTrue(onboardingAbstract.isDueDatePikerMenuDisplayed()
        ,"The due date menu is not displayed");
    }

    @When("I click on priority flag text field")
    public void iClickOnPriorityFlagTextField() {
        onboardingAbstract.clickOnPriorityTxtBox();
    }

    @Then("I should see quick action menu for priority displayed")
    public void iShouldSeePriorityQuickActionMenuDisplayed() {
        Assert.assertTrue(onboardingAbstract.isPriorityMenuDisplayed()
        ,"The priority menu is not displayed");
    }

    @And("I select medium priority")
    public void iSelectMediumPriority() {
        onboardingAbstract.selectMediumPriority();
    }

    @And("I click on save button")
    public void iClickOnSaveButton() {
        onboardingAbstract.clickOnSaveBtn();
    }

    @Then("I should see in today board card with the title i entered")
    public void iShouldSeeInTodayBoardCardWithTheTitleIEntered() {
        Assert.assertEquals(onboardingAbstract.getCardTitle(),"Appium Studying"
        ,"The title is not the same");
    }

    @And("I select today from due date piker")
    public void iSelectTodayFromDueDatePiker() {
        onboardingAbstract.selectDueDate();
    }

    @Given("I click on the card")
    public void iClickOnTheCard() {
        onboardingAbstract.clickOnCard();
    }

    @And("I select personal tag")
    public void iSelectPersonalTag() {
        onboardingAbstract.clickOnPersonalTag();
    }

    @And("I select 31 from due date piker")
    public void iSelectFromDueDatePiker() {
        onboardingAbstract.select31DueDate();
    }

    @Then("I should see mon 31 jul selected")
    public void iShouldSeeMonJulSelected() {
        Assert.assertEquals(onboardingAbstract.getJul31Value(),"Mon 31 Jul"
        ,"The two values are not identical");
    }

    @And("I click on update button")
    public void iClickOnUpdateButton() {
        onboardingAbstract.clickOnUpdateBtn();
    }
}
