package common.testData;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class HttpRequests {
    public static RequestSpecification getRequestForIpAddress() {
        return given()
                .header("Accept", "*/*\n")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Host", "demo.ip-api.com")
                .header("Origin", "https://ip-api.com")
                .header("Referer", "https://ip-api.com/")
                .header("Accept-Language", "en-US,en;q=0.9");
    }
}
