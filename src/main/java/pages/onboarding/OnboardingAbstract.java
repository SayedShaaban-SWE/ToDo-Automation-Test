package pages.onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class OnboardingAbstract {
    public static By NO_THING_HERE=By.id("com.jeffprod.todo:id/textViewNothing");
    public static By BUGER_ICON=By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]");
    public static By SELECT_TODAY_FROM_BURGER_LIST=By.id("com.jeffprod.todo:id/design_menu_item_text");
    public static By TODAY_IN_NAVBAR=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView");
    public static By PLUS_BTN=By.id("com.jeffprod.todo:id/fab");
    public static By TITLE_TXT_BOX=By.id("com.jeffprod.todo:id/editTextTitre");
    public static By NOTE_TXT_BOX=By.id("com.jeffprod.todo:id/editTextNote");
    public static By TAG=By.id("com.jeffprod.todo:id/editTextTag");
    public static By QUICK_TAG_MENU=By.xpath("/hierarchy/android.widget.FrameLayout");
    public static By WORK_TAG=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ListView/android.widget.CheckedTextView[4]");
    public static By PERSONAL_TAG=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ListView/android.widget.CheckedTextView[1]");
    public static By OK=By.id("android:id/button1");
    public static By START_DATE_PIKER=By.id("com.jeffprod.todo:id/buttonStartDate");
    public static By START_DATE_PIKER_MENU=By.xpath("/hierarchy/android.widget.FrameLayout");
    public static By TODAY_START_DATE_PIKER_MENU=By.xpath("//android.view.View[@content-desc=\"17 July 2023\"]");
    public static By DUE_DATE_PIKER=By.id("com.jeffprod.todo:id/buttonDeadline");
    public static By DUE_DATE_PIKER_MENU=By.xpath("/hierarchy/android.widget.FrameLayout");
    public static By TODAY_DUE_DATE_PIKER_MENU=By.xpath("//android.view.View[@content-desc=\"17 July 2023\"]");
    public static By JUL31_DUE_DATE_PIKER_MENU=By.xpath(" //android.view.View[@content-desc=\"31 July 2023\"]");
    public static By FLAG_PRIORITY_TXT_BOX=By.id("com.jeffprod.todo:id/editTextPriority");
    public static By MEDIUM_PRIORITY=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
    public static By PRIORITY_MENU=By.xpath("/hierarchy/android.widget.FrameLayout");
    public static By SAVE_BTN=By.id("com.jeffprod.todo:id/action_save");
    public static By UPDATE_BTN=By.id("com.jeffprod.todo:id/action_save");
    public static By CARD=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout");
    public static By CARD_TITLE=By.id("com.jeffprod.todo:id/textViewListView");
    public static By JUL31_2023=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.Button[2]");


    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public OnboardingAbstract(AppiumDriver appiumDriver){
        super();
        this.appiumDriver=appiumDriver;
        wait=new WebDriverWait(appiumDriver,30);
    }

    public boolean isTheAppIsOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(NO_THING_HERE));
        AndroidElement noThingHere = (AndroidElement) appiumDriver.findElement(NO_THING_HERE);
        return noThingHere.isDisplayed();
    }

    public void clickOnBurgerIcon(){
        appiumDriver.findElement(BUGER_ICON).click();
    }
    public void selectTodayFromBurgerIconList(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SELECT_TODAY_FROM_BURGER_LIST));
        appiumDriver.findElement(SELECT_TODAY_FROM_BURGER_LIST).click();
    }

    public boolean isTodayIsDisplayedAtNavbar(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TODAY_IN_NAVBAR));
        AndroidElement today = (AndroidElement) appiumDriver.findElement(TODAY_IN_NAVBAR);
        return today.isDisplayed();
    }
    public void clickOnPlusBtn(){
        appiumDriver.findElement(PLUS_BTN).click();
    }
    public void enterTitle(String title){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_TXT_BOX));
        appiumDriver.findElement(TITLE_TXT_BOX).sendKeys(title);
    }
    public void enterNote(String note){
        appiumDriver.findElement(NOTE_TXT_BOX).sendKeys(note);
    }
    public void clickOnTagTxtBox(){
        appiumDriver.findElement(TAG).click();
    }
    public boolean isQuickActionTagMenuDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(QUICK_TAG_MENU));
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(QUICK_TAG_MENU);
        return menu.isDisplayed();
    }
    public void clickOnWorkTag(){
        appiumDriver.findElement(WORK_TAG).click();
    }
    public void clickOnOk(){
        appiumDriver.findElement(OK).click();
    }
    public void clickOntStartDatePiker(){
        appiumDriver.findElement(START_DATE_PIKER).click();
    }
    public boolean isStartDatePikerMenuDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(START_DATE_PIKER_MENU));
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(START_DATE_PIKER_MENU);
        return menu.isDisplayed();
    }
    public void selectStartDate(){
        appiumDriver.findElement(TODAY_START_DATE_PIKER_MENU).click();
    }

    public String getTodayAttributeValue(){
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(START_DATE_PIKER);
        return menu.getAttribute("text");
    }
    public void clickOnDueDatePiker(){
        appiumDriver.findElement(DUE_DATE_PIKER).click();
    }
    public boolean isDueDatePikerMenuDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(DUE_DATE_PIKER_MENU));
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(DUE_DATE_PIKER_MENU);
        return menu.isDisplayed();
    }
    public void selectDueDate(){
        appiumDriver.findElement(TODAY_DUE_DATE_PIKER_MENU).click();
    }
    public void clickOnPriorityTxtBox(){
        appiumDriver.findElement(FLAG_PRIORITY_TXT_BOX).click();
    }
    public boolean isPriorityMenuDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRIORITY_MENU));
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(PRIORITY_MENU);
        return menu.isDisplayed();
    }
    public void selectMediumPriority(){
        appiumDriver.findElement(MEDIUM_PRIORITY).click();
    }

    public void clickOnSaveBtn(){
        appiumDriver.findElement(SAVE_BTN).click();
    }

    public String getCardTitle(){
        AndroidElement cardTitle = (AndroidElement) appiumDriver.findElement(CARD_TITLE);
        return cardTitle.getAttribute("text");
    }

    public void clickOnCard(){
        appiumDriver.findElement(CARD).click();
    }
    public void clickOnPersonalTag(){
        appiumDriver.findElement(PERSONAL_TAG).click();
    }
    public void select31DueDate(){
        appiumDriver.findElement(JUL31_DUE_DATE_PIKER_MENU).click();
    }
    public void clickOnUpdateBtn(){
        appiumDriver.findElement(UPDATE_BTN).click();
    }
    public String getJul31Value(){
        AndroidElement menu = (AndroidElement) appiumDriver.findElement(JUL31_2023);
        return menu.getAttribute("text");
    }



}
