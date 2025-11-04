package pages.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pages.common.BasePage;
import pages.common.LoginPageBase;

/**
 * Concrete implementation of the LoginPageBase interface for the iOS platform.
 * It inherits utility methods from BasePage and uses platform-specific locators
 * (@iOSXCUITFindBy) to fulfill the login contract.
 */
public class LoginPage extends BasePage implements LoginPageBase {

    // --- SECTION 1: Locators (The "WHERE" on the screen for iOS) ---


    // --- SECTION 1: Locators (The "WHERE" on the screen for Android) ---
//    @FindBy(accessibility = "Login-screen")
//    private WebElement loginScreen;
    private final By loginScreen = By.xpath("//XCUIElementTypeOther[@name = \"Login-screen\"]/XCUIElementTypeScrollView");
    //    @AndroidFindBy(xpath = "/android.widget.TextView[@text=\"Login / Sign up Form\"]")
//    private WebElement loginSignUpForm;
    private final By loginSignUpForm = By.xpath("/android.widget.TextView[@text=\"Login / Sign up Form\"]");
    //    @AndroidFindBy(accessibility = "input-email")
//    private WebElement emailField;
    private final By emailField = By.xpath("//XCUIElementTypeTextField[@name=\"input-email\"]");
    //    @AndroidFindBy(accessibility = "input-password")
//    private WebElement passwordField;
    private final By passwordField = By.xpath("//XCUIElementTypeSecureTextField[@name=\"input-password\"]");
    //    @AndroidFindBy(accessibility = "button-LOGIN")
//    private WebElement loginButton;
    private final By loginButton = By.xpath("//XCUIElementTypeStaticText[@name=\"LOGIN\"]");
    // Locator for an error message after failed login
//    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address\"]")
//    private WebElement loginInputErrorMessage;
    private final By loginInputErrorMessage = By.xpath("//XCUIElementTypeStaticText[@name=\"Please enter a valid email address\"]");
    //    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter at least 8 characters\"]")
//    private WebElement passwordInputErrorMessage;
    private final By passwordInputErrorMessage = By.xpath("//XCUIElementTypeStaticText[@name=\"Please enter at least 8 characters\"]");



    // --- SECTION 2: Constructor (Initialization) ---
    public LoginPage(AppiumDriver driver) {
        // Calls the BasePage constructor to initialize the driver and PageFactory elements
        super(driver);
    }

    // --- SECTION 3: Business Logic (Implementing the Contract) ---

    @Override
    public void enterEmail(String email) {
        // Uses the reusable type() method from BasePage
        type(driver.findElement(emailField), email);
    }

    @Override
    public void enterPassword(String password) {
        // Uses the reusable type() method from BasePage
        type(driver.findElement(passwordField), password);
    }

    @Override
    public void clickLoginButton() {
        // Uses the reusable click() method from BasePage
        click(driver.findElement(loginButton));
    }

    @Override
    public boolean isLoginScreenDisplayed() {
        try {
            return isDisplayed(driver.findElement(loginScreen));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getLoginInputErrorMessageText() {
        // Uses the reusable getText() method from BasePage
        return getText(driver.findElement(loginInputErrorMessage));
    }
}
