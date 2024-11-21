package tests.apiTests;

import common.Endpoints;
import common.testData.dataProvider.NtpServerDataProvider;
import dto.apiDto.IpAddressInfoDTO;
import org.testng.annotations.Test;
import pages.base.BaseApi;

import static common.testData.HttpRequests.getRequestForIpAddress;
import static org.assertj.core.api.Assertions.assertThat;


public class NtpServerTest extends BaseApi {

    @Test(dataProvider = "ntpServers", dataProviderClass = NtpServerDataProvider.class)
    public void testNtpServer(String domain) {

        IpAddressInfoDTO expectedResponse = IpAddressInfoDTO.builder()
                .country("Ukraine")
                .build();

        stepName("1. GET " + Endpoints.IP_API_URL + domain + "?fields=66842623&lang=en");
        IpAddressInfoDTO actualResponse = getRequestForIpAddress()
                .get(Endpoints.IP_API_URL + domain + "?fields=66842623&lang=en")
                .then()
                .statusCode(200)
                .extract()
                .as(IpAddressInfoDTO.class);

        stepName("2. Verify that location is in Ukraine");
        assertThat(actualResponse)
                .usingRecursiveComparison()
                .comparingOnlyFields("country")
                .isEqualTo(expectedResponse);
    }
}
