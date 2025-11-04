package core;

import java.io.File;

/**
 * Configuration class to hold constants related to application paths and settings.
 * This ensures clean separation of configuration from test logic and makes it CI/CD ready.
 *
 * CRITICAL UPDATE: This class now determines the application path dynamically
 * based on the execution environment (Local vs. CI).
 */
public class MobileConfig {

    // --- Appium Server Details (Standard local URL) ---
    public static final String APPIUM_URL = "http://127.0.0.1:4723";

    // --- App Identifiers ---
    public static final String ANDROID_APP_PACKAGE = "com.wdiodemoapp";
    public static final String IOS_BUNDLE_ID = "org.reactjs.native.example.wdiodemoapp"; // Use your app's Bundle ID

    // --- Application Path Constants (Relative paths used for both CI and Local) ---
    private static final String RELATIVE_ANDROID_APP_PATH = "apps/android.wdio.native.app.v1.0.8.apk";
    private static final String RELATIVE_IOS_APP_PATH = "apps/ios/wdiodemoapp.app";


    /**
     * Determines if the current environment is running on GitHub Actions CI.
     * @return true if running in CI, false otherwise.
     */
    public static boolean isRunningInCI() {
        // GitHub Actions always sets this environment variable.
        return System.getenv("GITHUB_ACTIONS") != null && System.getenv("GITHUB_ACTIONS").equals("true");
    }

    /**
     * Gets the dynamically calculated full path for the Android application.
     * In CI: Returns the simple relative path (e.g., "apps/android.apk")
     * Locally: Returns the absolute path (e.g., "/Users/user/project/apps/android.apk")
     */
    public static String getAndroidAppPath() {
        if (isRunningInCI()) {
            // CI checks out the repo and knows the relative path directly from the root
            return System.getProperty("user.dir") + "/" + RELATIVE_ANDROID_APP_PATH;
        } else {
            // Locally, we need the absolute path for Appium to find the file correctly
            return new File(RELATIVE_ANDROID_APP_PATH).getAbsolutePath();
        }
    }

    /**
     * Gets the dynamically calculated full path for the iOS application.
     * In CI: Returns the simple relative path (e.g., "apps/ios/wdiodemoapp.app")
     * Locally: Returns the absolute path.
     */
    public static String getIosAppPath() {
        if (isRunningInCI()) {
            return System.getProperty("user.dir") + "/" + RELATIVE_IOS_APP_PATH;
        } else {
            return new File(RELATIVE_IOS_APP_PATH).getAbsolutePath();
        }
    }
}
