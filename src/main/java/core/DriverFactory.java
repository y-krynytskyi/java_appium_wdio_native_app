package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Factory class responsible for initializing and configuring platform-specific Appium Drivers.
 * It centralizes all driver initialization logic, using advanced Appium 2.x Options
 * for robust, cross-platform execution.
 */
public class DriverFactory {

    // Centralized Appium Server URL initialization (best practice)
    private static final URL APPIUM_SERVER_URL;

    static {
        try {
            APPIUM_SERVER_URL = new URL(MobileConfig.APPIUM_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium Server URL: " + e.getMessage(), e);
        }
    }

    /**
     * Initializes and returns an AppiumDriver instance based on the specified platform.
     * This method is called by BaseTest.
     * @param platform The mobile platform (ANDROID or IOS).
     * @return The initialized AppiumDriver.
     */
    public static AppiumDriver initializeDriver(String platform) {
        // Normalize platform input to prevent errors (e.g., "android" -> ANDROID)
        Platform plat = Platform.fromString(platform.toUpperCase());

        System.out.println("Initializing driver for platform: " + plat);
        AppiumDriver driver;

        try {
            // Use switch expression to create the correct driver type
            driver = switch (plat) {
                case ANDROID -> createAndroidDriver();
                case IOS -> createIOSDriver();
                default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
            };

            // Set a global implicit wait after the driver is created
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("Driver initialized successfully.");
            return driver;

        } catch (Exception e) {
            System.err.println("Failed to initialize driver for " + platform + ". Error: " + e.getMessage());
            // Throw a runtime exception to stop tests immediately on configuration failure
            throw new RuntimeException("Appium Driver initialization failed.", e);
        }
    }

    /**
     * Builds comprehensive capabilities and creates the AndroidDriver.
     */
    private static AndroidDriver createAndroidDriver() {
        // Capabilities based on the requested options for Android
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554") // Generic name for CI/Cloud integration
                .setApp(MobileConfig.ANDROID_APP_PATH) // Connects the external .apk file from the 'apps' folder
                .setAppPackage(MobileConfig.ANDROID_APP_PACKAGE)
                .setAutomationName("UiAutomator2")
                // CI/CD and Stability settings
                .setNoReset(false) // Prevents data wipe between tests
                .setSkipDeviceInitialization(false) // Speeds up session startup
                .setSkipServerInstallation(false)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(60)); // Standard timeout

        System.out.println("Android capabilities set. Launching driver.");
        return new AndroidDriver(APPIUM_SERVER_URL, options);
    }

    /**
     * Builds comprehensive capabilities and creates the IOSDriver.
     */
    private static IOSDriver createIOSDriver() {
        // Capabilities based on the requested options for iOS
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName("iPhone 16e") // Generic name for simulator/CI
                .setPlatformVersion("26.0") // IMPORTANT: Change this to match your target iOS simulator version
                .setApp(MobileConfig.IOS_APP_PATH) // Connects the external .app file from the 'apps' folder
                .setBundleId(MobileConfig.IOS_BUNDLE_ID)
                .setAutomationName("XCUITest")
                // CI/CD and Stability settings
                .setWdaLaunchTimeout(Duration.ofSeconds(120)) // WebDriverAgent launch can take time
                .setNewCommandTimeout(Duration.ofSeconds(3600));

        System.out.println("iOS capabilities set. Launching driver.");
        return new IOSDriver(APPIUM_SERVER_URL, options);
    }
}