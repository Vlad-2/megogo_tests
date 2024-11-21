package dto.apiDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class IpAddressInfoDTO {
    private String query;
    private String status;
    private String continent;
    private String continentCode;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String district;
    private String zip;
    private double lat;
    private double lon;
    private String timezone;
    private String offset;
    private String currency;
    private String isp;
    private String org;
    private String as;
    private String asname;
    private boolean mobile;
    private boolean proxy;
    private boolean hosting;
}
