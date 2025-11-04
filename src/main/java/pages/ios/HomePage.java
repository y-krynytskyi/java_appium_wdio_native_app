package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.BasePage;
import pages.common.BottomNavigationBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete Page Object Model for the iOS Dashboard Screen.
 * Implements the DashboardPageBase contract using iOS-specific locators.
 * Note: Accessibility IDs are often the same on both platforms.
 */
public class HomePage extends BasePage implements BottomNavigationBase {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    // --- SECTION 1: Bottom Navigation Bar Locators (Using Accessibility ID) ---

//    @iOSXCUITFindBy(accessibility = "Home-screen")
//    private WebElement homeScreen;
//
//    @iOSXCUITFindBy(accessibility = "Home")
//    private WebElement homeTab;
//
//    @iOSXCUITFindBy(accessibility = "Webview")
//    private WebElement webviewTab;

//    @iOSXCUITFindBy(accessibility = "Login")
    private final By loginTab = By.xpath("//XCUIElementTypeButton[@name=\"Login\"]");
//
//    @iOSXCUITFindBy(accessibility = "Forms")
//    private WebElement formsTab;
//
//    @iOSXCUITFindBy(accessibility = "Swipe")
//    private WebElement swipeTab;
//
//    @iOSXCUITFindBy(accessibility = "Drag")
//    private WebElement dragTab;


    // --- SECTION 2: Constructor (Initialization) ---
    public HomePage(AppiumDriver driver) {
        super(driver);
        logger.debug("iOS Ios Home Page initialized.");
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void goToWebview() {

    }

    @Override
    public void goToLogin() {
        click(driver.findElement(loginTab));

    }

    @Override
    public void goToForms() {

    }

    @Override
    public void goToSwipe() {

    }

    @Override
    public void goToDrag() {

    }

    @Override
    public boolean isHomeScreenDisplayed() {
        return false;
    }

}