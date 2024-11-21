package configs;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class EnvConfiguration {

    private static final Properties properties = new Properties();
    private static final String CONFIG_SOURCE_PATH = "src/test/java/resources/config.properties";
    private static final Map<String, String> defaultProperties = new HashMap<>();

    // Константы для часто используемых значений
    private static final String DEFAULT_ENVIRONMENT = "dev";

    // Параметры конфигурации
    public static final String BASE_SELENOID_URL;
    public static final String SELENOID_URL_HUB;
    public static final String BROWSER;
    public static final boolean IS_REMOTE;

    static {
        loadProperties();

        BASE_SELENOID_URL = System.getProperty("selenoid.url", defaultProperties.get("base.selenoid.url"));
        SELENOID_URL_HUB = BASE_SELENOID_URL + "/wd/hub";
        BROWSER = System.getProperty("browser", defaultProperties.get("env.default.browser"));
        IS_REMOTE = Boolean.parseBoolean(System.getProperty("isRemote", defaultProperties.get("is.remote")));
    }

    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream(CONFIG_SOURCE_PATH)) {
            properties.load(fis);
            for (String propertyName : properties.stringPropertyNames()) {
                defaultProperties.put(propertyName, properties.getProperty(propertyName));
            }
        } catch (IOException e) {
            log.error("Failed to load configuration from {}: {}", CONFIG_SOURCE_PATH, e.getMessage());
            throw new IllegalStateException("Configuration file could not be loaded", e);
        }
    }

    public static String getEnv() {
        return System.getProperty("environment", defaultProperties.get("environment"));
    }

    public static String getEnvironmentUrl() {
        String env = getEnv();
        return switch (env) {
            case DEFAULT_ENVIRONMENT -> defaultProperties.get("env.url." + DEFAULT_ENVIRONMENT);
            default -> defaultProperties.get("env.url." + DEFAULT_ENVIRONMENT);
        };
    }
}
