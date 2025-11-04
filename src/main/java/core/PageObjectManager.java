package core;

import io.appium.java_client.AppiumDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;

/**
 * Service Locator implementation for Page Objects.
 * This class uses reflection and PageConfig to dynamically instantiate the correct
 * platform-specific page object based on the current execution platform.
 * This approach eliminates the need for hardcoded page getters and keeps the Manager highly scalable.
 */
public class PageObjectManager {

    private final AppiumDriver driver;
    private final Platform platform; // This is the core.Platform enum, which is correct
    private final Logger logger = LogManager.getLogger(PageObjectManager.class);

    // Cache for pages to prevent redundant reflection calls and re-instantiation (Lazy Initialization)
    private final ConcurrentHashMap<Class<?>, Object> pageCache = new ConcurrentHashMap<>();


    /**
     * Constructor takes the driver and the Platform enum.
     * The Platform enum ensures type-safe instantiation and lookup.
     * @param driver The thread-safe AppiumDriver.
     * @param platform The current execution platform (ANDROID or IOS) as the core.Platform enum object.
     */
    public PageObjectManager(AppiumDriver driver, Platform platform) {
        this.driver = driver;
        this.platform = platform;
        logger.info("PageObjectManager initialized for platform: {}", platform);
    }

    /**
     * The single generic method to retrieve any page object. This is the heart of the Service Locator.
     *
     * @param pageInterface The interface (contract) of the page you want (e.g., LoginPageBase.class).
     * @param <T> The type of the interface.
     * @return An instance of the concrete platform-specific page implementing the interface.
     */
    @SuppressWarnings("unchecked")
    public <T> T getPage(Class<T> pageInterface) {
        // 1. Check if the page is already in the cache
        if (pageCache.containsKey(pageInterface)) {
            // Using Log4j2 parameterization with '{}'
            logger.debug("Returning cached instance of: {}", pageInterface.getSimpleName());
            return (T) pageCache.get(pageInterface);
        }

        // 2. Find the concrete class from the PageConfig MAPPINGS
        Class<?> concreteClass = PageConfig.MAPPINGS
                .getOrDefault(platform, Collections.emptyMap())
                .get(pageInterface);

        if (concreteClass == null) {
            logger.error("No concrete implementation found for interface {} on platform {}", pageInterface.getName(), platform);
            throw new IllegalArgumentException(
                    "No concrete implementation found for interface " + pageInterface.getName() + " on platform " + platform
            );
        }

        // 3. Instantiate the concrete class using reflection
        try {
            logger.info("Initializing {} via {}", pageInterface.getSimpleName(), concreteClass.getSimpleName());
            // Get the constructor that accepts an AppiumDriver
            T pageInstance = (T) concreteClass.getDeclaredConstructor(AppiumDriver.class).newInstance(driver);

            // 4. Cache the newly created instance
            pageCache.put(pageInterface, pageInstance);
            return pageInstance;

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error("Failed to instantiate page object: {}. Ensure it has a public constructor accepting AppiumDriver.", concreteClass.getName(), e);
            throw new RuntimeException("Failed to instantiate page object: " + concreteClass.getName() + ". Ensure it has a public constructor accepting AppiumDriver.", e);
        }
    }
}
