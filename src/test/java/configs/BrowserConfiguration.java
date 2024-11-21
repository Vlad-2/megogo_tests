package configs;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Slf4j
public class BrowserConfiguration {
    public static MutableCapabilities getOptions(String browserName) {
        switch (browserName) {
            case "chrome":
                return getChromeOptions();
            case "firefox":
                return getFirefoxOptions();
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
    }

    public static ChromeOptions getChromeOptions() {
        System.out.println("Configuring Chrome driver with optimized settings...");

        ChromeOptions chromeOptions = new ChromeOptions();

        // Отключение уведомлений и запросов на доступ к медиа-устройствам
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 2);
        chromePrefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        chromePrefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        chromePrefs.put("profile.default_content_setting_values.geolocation", 2);
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);

        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        // Оптимизированные аргументы для Chrome
        chromeOptions.addArguments("window-size=1920,1080");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--disable-gpu");

        // Логирование только критичных ошибок для Chrome
        LoggingPreferences chromeLogPrefs = new LoggingPreferences();
        chromeLogPrefs.enable(LogType.BROWSER, Level.SEVERE);
        chromeOptions.setCapability("goog:loggingPrefs", chromeLogPrefs);

        chromeOptions.setCapability("acceptInsecureCerts", true);

        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        System.out.println("Configuring Firefox driver with optimized settings...");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();

        // Отключение уведомлений и запросов на доступ к устройствам
        firefoxProfile.setPreference("permissions.default.desktop-notification", 2);
        firefoxProfile.setPreference("media.navigator.permission.disabled", true);
        firefoxProfile.setPreference("geo.enabled", false);

        // Отключение автоматических обновлений для стабильности тестов
        firefoxProfile.setPreference("app.update.auto", false);
        firefoxProfile.setPreference("app.update.enabled", false);

        // Отключение push-уведомлений для стабильности (если не требуется)
        firefoxProfile.setPreference("dom.push.enabled", false);

        // Настройка разрешения экрана для CI/CD окружения
        HashMap<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("screenResolution", "1920x1080x24");
        firefoxOptions.setCapability("selenoid:options", selenoidOptions);

        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");

        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.setCapability("acceptInsecureCerts", true);

        return firefoxOptions;
    }
}
