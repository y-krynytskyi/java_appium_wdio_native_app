package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.BasePage;
import pages.common.LoginPageBase;

/**
 * Concrete implementation of the LoginPageBase for the Android platform.
 * Inherits utility methods from BasePage and uses platform-specific locators.
 */
public class LoginPage extends BasePage implements LoginPageBase {

    // --- SECTION 1: Locators (The "WHERE" on the screen for Android) ---
//    @FindBy(accessibility = "Login-screen")
//    private WebElement loginScreen;
    private final By loginScreen = By.xpath("//android.widget.ScrollView[@content-desc=\"Login-screen\"]");
//    @AndroidFindBy(xpath = "/android.widget.TextView[@text=\"Login / Sign up Form\"]")
//    private WebElement loginSignUpForm;
    private final By loginSignUpForm = By.xpath("/android.widget.TextView[@text=\"Login / Sign up Form\"]");
//    @AndroidFindBy(accessibility = "input-email")
//    private WebElement emailField;
    private final By emailField = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
//    @AndroidFindBy(accessibility = "input-password")
//    private WebElement passwordField;
    private final By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
//    @AndroidFindBy(accessibility = "button-LOGIN")
//    private WebElement loginButton;
    private final By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]/android.view.ViewGroup");
    // Locator for an error message after failed login
//    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address\"]")
//    private WebElement loginInputErrorMessage;
    private final By loginInputErrorMessage = By.xpath("//android.widget.TextView[@text=\"Please enter a valid email address\"]");
//    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter at least 8 characters\"]")
//    private WebElement passwordInputErrorMessage;
    private final By passwordInputErrorMessage = By.xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");


    // --- SECTION 2: Constructor (Initialization) ---
    public LoginPage(AppiumDriver driver) {
        // Initializes the driver, WaitHelper, and PageFactory elements
        super(driver);
    }

    // --- SECTION 3: Business Logic (Implementing the Contract) ---
    @Override
    public void enterEmail(String email) {
        type(driver.findElement(emailField), email);
    }

    @Override
    public void enterPassword(String password) {
        type(driver.findElement(passwordField), password);
    }

    @Override
    public void clickLoginButton() {
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
        return getText(driver.findElement(loginInputErrorMessage));
    }
}
