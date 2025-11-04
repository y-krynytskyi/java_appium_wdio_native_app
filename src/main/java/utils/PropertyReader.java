package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to load properties from a file.
 */
public class PropertyReader {

    private final Properties properties;

    /**
     * Loads properties from the given file path.
     * @param filePath The path to the properties file (relative to project root).
     */
    public PropertyReader(String filePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ERROR: Could not load properties from file: " + filePath);
            e.printStackTrace(); //todo: look at log4g library
            throw new RuntimeException("Failed to load configuration properties.", e);
        }
    }

    /**
     * Gets a property value by key.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

