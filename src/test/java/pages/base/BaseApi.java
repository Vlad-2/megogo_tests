package pages.base;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;


@Slf4j
public class BaseApi {
    public static String getCurrentDate() {
        return LocalDate.now().toString();
    }
    public static String getLocalDateMinus30Days() {
        return LocalDate.now().minusDays(30).toString();
    }

    @Step("    {step}")
    public void stepName(String step) {
        log.info(step);
    }
    public void sleep(int milliseconds) {
        Selenide.sleep(milliseconds);
    }
}
