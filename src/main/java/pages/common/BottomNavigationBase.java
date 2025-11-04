package pages.common;


/**
 * Interface defining the contract for the Dashboard screen and its navigation.
 * All platform-specific Dashboard pages must implement these methods.
 * This guarantees consistent navigation functionality across all platforms.
 */
public interface BottomNavigationBase {

    // Methods for navigating the bottom bar
    void goToHome();
    void goToWebview();
    void goToLogin();
    void goToForms();
    void goToSwipe();
    void goToDrag();

    // Validation method for the dashboard
    boolean isHomeScreenDisplayed();
}