package core;

import java.io.File;

/**
 * Configuration class to hold constants related to application paths and settings.
 * This ensures clean separation of configuration from test logic and makes it CI/CD ready.
 */
public class MobileConfig {

    // --- Application Paths (Must be updated to point to your local files) ---

    // Path to the Android Application Package (.apk)
    // Assumes file is in the project root.
    public static final String ANDROID_APP_PATH = new File("apps/android.wdio.native.app.v1.0.8.apk").getAbsolutePath();

    // Path to the iOS Application Bundle (.app)
    // Assumes file is in the project root.
    public static final String IOS_APP_PATH = new File("apps/ios/wdiodemoapp.app").getAbsolutePath();

    // --- Appium Server Details (Standard local URL) ---
    public static final String APPIUM_URL = "http://127.0.0.1:4723";

    // --- App Identifiers ---
    public static final String ANDROID_APP_PACKAGE = "com.wdiodemoapp";
    public static final String IOS_BUNDLE_ID = "org.reactjs.native.example.wdiodemoapp"; // Use your app's Bundle ID
}

