package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class to manage Explicit Waits for mobile elements, improving test stability.
 */
public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(AppiumDriver driver) {
        // Initialize WebDriverWait with the driver and a default timeout.
        // Default wait time
        Duration TIMEOUT = Duration.ofSeconds(15);
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    /**
     * Waits for a WebElement to be visible on the DOM.
     * @param element The element to wait for.
     * @return The visible WebElement.
     */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for a WebElement to be clickable (visible and enabled).
     * @param element The element to wait for.
     * @return The clickable WebElement.
     */
    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the entire page source to contain specific text.
     * @param text The text to search for.
     * @return True if the text is found within the timeout.
     */
    public boolean waitForPageText(String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(null, text));
    }
}
