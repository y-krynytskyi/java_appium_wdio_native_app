package pages.common;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

/**
 * Base class for all Page Object Model (POM) classes.
 * Provides driver access, initializes PageFactory elements, and encapsulates common mobile interactions.
 */
public abstract class BasePage {

    private static final Logger log = LogManager.getLogger(BasePage.class);
    protected AppiumDriver driver;
    protected WaitHelper waitHelper;

    /**
     * Constructor initializes the driver and PageFactory elements.
     * @param driver The AppiumDriver instance obtained from DriverFactory.
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        // Initializes all @FindBy, @AndroidFindBy, @iOSXCUITFindBy elements defined in the subclass
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks a web element after ensuring it is visible and clickable.
     */
    protected void click(WebElement element) {
        try {
            waitHelper.waitForClickability(element).click();
            System.out.println("Clicked on element: ");
        } catch (Exception e) {
            System.err.println("Failed to click  " + e.getMessage());
            // Optional: add screenshot logic here for better debugging
            throw new RuntimeException("Element not clickable: ", e);
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitHelper.waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sends text to a web element after ensuring it is visible.
     */
    protected void type(WebElement element, String text) {
        try {
            WebElement visibleElement = waitHelper.waitForVisibility(element);
            visibleElement.clear();
            visibleElement.sendKeys(text);
            System.out.println("Entered text '" + text + "' into " + element);
        } catch (Exception e) {
            System.err.println("Failed to enter text into " + element + ". Error: " + e.getMessage());
            throw new RuntimeException("Cannot enter text into field: " + element, e);
        }
    }

    /**
     * Returns the text of a web element after ensuring it is visible.
     */
    protected String getText(WebElement element) {
        try {
            return waitHelper.waitForVisibility(element).getText();
        } catch (Exception e) {
            System.err.println("Failed to get text from " + element + ". Error: " + e.getMessage());
            return null;
        }
    }

    // TODO: Add common gesture methods here (e.g., protected void swipeUp() { ... })
}
