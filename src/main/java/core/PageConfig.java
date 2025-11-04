package core;

import org.openqa.selenium.Platform;
import pages.common.BottomNavigationBase;
import pages.common.LoginPageBase;
import pages.ios.HomePage;
import pages.ios.LoginPage;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Central configuration mapping page interfaces (the contract) to their concrete
 * platform-specific implementations. This allows the PageObjectManager to dynamically
 * create pages without hardcoded switch statements, making the framework highly scalable.
 */
public final class PageConfig {

    // Map: Platform -> (Interface -> Concrete Class)
    public static final Map<Platform, Map<Class<?>, Class<?>>> MAPPINGS;

    static {
        // --- Android Mappings ---
        Map<Class<?>, Class<?>> androidPages = new HashMap<>();
        // Registering the Login Page: LoginPageBase (Interface) -> LoginPage (Android Impl)
        androidPages.put(LoginPageBase.class, pages.android.LoginPage.class);
        androidPages.put(BottomNavigationBase.class, pages.android.HomePage.class);
        // Add other Android page mappings here (e.g., DashboardPageBase.class, AndroidDashboardPage.class)

        // --- iOS Mappings ---
        Map<Class<?>, Class<?>> iosPages = new HashMap<>();
        // Registering the Login Page: LoginPageBase (Interface) -> LoginPage (iOS Impl)
        iosPages.put(LoginPageBase.class, LoginPage.class);
        iosPages.put(BottomNavigationBase.class, HomePage.class);
        // Add other iOS page mappings here (e.g., DashboardPageBase.class, IosDashboardPage.class)

        // --- Final Global Mappings ---
        Map<Platform, Map<Class<?>, Class<?>>> tempMap = new EnumMap<>(Platform.class);
        tempMap.put(Platform.ANDROID, Collections.unmodifiableMap(androidPages));
        tempMap.put(Platform.IOS, Collections.unmodifiableMap(iosPages));

        MAPPINGS = Collections.unmodifiableMap(tempMap);
    }

    private PageConfig() {
        // Private constructor to prevent instantiation
    }
}
