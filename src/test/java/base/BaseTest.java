package base;

import core.DriverFactory;
import core.PageObjectManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Base class for all test classes. It handles driver initialization and teardown,
 * using ThreadLocal to ensure thread-safety for parallel execution.
 * It also initializes the PageObjectManager.
 */
public class BaseTest {

    // 1. Driver ThreadLocal: Stores a separate AppiumDriver instance for each thread.
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    // 2. PageObjectManager ThreadLocal: Stores a separate PageObjectManager for each thread.
    private static final ThreadLocal<PageObjectManager> pageManagerThreadLocal = new ThreadLocal<>();

    /**
     * Retrieves the driver instance bound to the current thread.
     * Used by the PageObjectManager and other utility classes.
     */
    public AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Retrieves the PageObjectManager instance bound to the current thread.
     * This is the primary method used by test classes to access Page Objects.
     * @return The thread-safe PageObjectManager.
     */
    public PageObjectManager getPageManager() {
        return pageManagerThreadLocal.get();
    }

    /**
     * Sets the driver instance for the current thread. Used internally by setup.
     */
    private void setDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    /**
     * Sets the PageObjectManager instance for the current thread.
     */
    private void setPageManager(PageObjectManager manager) {
        pageManagerThreadLocal.set(manager);
    }


    /**
     * Initializes the driver and PageObjectManager before each test method runs.
     * @param platform The mobile platform (ANDROID or IOS) passed from testng.xml.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"platform"})
    public void setupDriver(String platform) {
        try {
            // 1. Create and set the driver
            AppiumDriver driver = DriverFactory.initializeDriver(platform);
            setDriver(driver);
            Platform plat = Platform.fromString(platform);

            // 2. Initialize and set the PageObjectManager using the newly created driver
            PageObjectManager manager = new PageObjectManager(driver, plat);
            setPageManager(manager);

            System.out.println("Framework setup successful for platform: " + platform + " on thread: " + Thread.currentThread().getId());
        } catch (Exception e) {
            System.err.println("Failed to initialize driver and manager for " + platform + ": " + e.getMessage());
            throw new RuntimeException("Driver setup failed.", e);
        }
    }

    /**
     * Quits the driver and removes instances from ThreadLocal storage after each test method.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.remove();
        pageManagerThreadLocal.remove(); // Also clean up the PageObjectManager
        System.out.println("Driver and Manager successfully cleaned up on thread: " + Thread.currentThread().getId());
    }
}
