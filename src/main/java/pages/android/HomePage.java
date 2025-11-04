package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.common.BasePage;
import pages.common.BottomNavigationBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete Page Object Model for the Android Dashboard Screen.
 * Implements the DashboardPageBase contract using Android-specific locators.
 */
public class HomePage extends BasePage implements BottomNavigationBase {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    // --- SECTION 1: Bottom Navigation Bar Locators (Using Accessibility ID) ---
    @AndroidFindBy(accessibility = "Home-screen")
    private WebElement homeScreen;

    @AndroidFindBy(accessibility = "Home")
    private WebElement homeTab;

    @AndroidFindBy(accessibility = "Webview")
    private WebElement webviewTab;

//    @AndroidFindBy(accessibility = "Login")
//    private WebElement loginTab;

    private final By loginTab = By.xpath("//android.view.View[@content-desc=\"Login\"]");

    @AndroidFindBy(accessibility = "Forms")
    private WebElement formsTab;

    @AndroidFindBy(accessibility = "Swipe")
    private WebElement swipeTab;

    @AndroidFindBy(accessibility = "Drag")
    private WebElement dragTab;


    // --- SECTION 2: Constructor (Initialization) ---
    public HomePage(AppiumDriver driver) {
        super(driver);
        logger.debug("Android Home Page initialized.");
    }

    // --- SECTION 3: Business Logic (Implementing the Contract) ---

    @Override
    public void goToHome() {
        click(homeTab);
    }

    @Override
    public void goToWebview() {
        click(webviewTab);
    }

    @Override
    public void goToLogin() {
        click(driver.findElement(loginTab));

    }

    @Override
    public void goToForms() {
        click(formsTab);

    }

    @Override
    public void goToSwipe() {
        click(swipeTab);
    }

    @Override
    public void goToDrag() {
        click(dragTab);
    }

    @Override
    public boolean isHomeScreenDisplayed() {
        return isDisplayed(homeScreen);
    }
}