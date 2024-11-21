package common.testData.dataProvider;

import org.testng.annotations.DataProvider;

public class NtpServerDataProvider {

    @DataProvider(name = "ntpServers", parallel = true)
    public static Object[][] provideNtpServers() {
        return new Object[][]{
                {"0.ua.pool.ntp.org"},
                {"1.ua.pool.ntp.org"},
                {"2.ua.pool.ntp.org"},
                {"3.ua.pool.ntp.org"},
                {"4.ua.pool.ntp.org"}
        };
    }
}
