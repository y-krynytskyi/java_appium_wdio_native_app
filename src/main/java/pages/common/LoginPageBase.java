package pages.common;

/**
 * Interface defining the contract for the Login screen interactions.
 * All platform-specific login pages (Android, iOS) must implement these methods.
 * This guarantees consistent functionality across all platforms.
 */
public interface LoginPageBase {

    // Define methods that perform user actions
    void enterEmail(String email);
    void enterPassword(String password);
    void clickLoginButton();

    // Define methods that perform validations
    boolean isLoginScreenDisplayed();
    String getLoginInputErrorMessageText();

    // Note: The driver is usually passed to the constructor of the implementing class.
}
