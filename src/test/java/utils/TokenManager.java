package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {

    public static String getAccessToken() {
        String authUrl = ConfigLoader.get("authUrl");
        String clientId = ConfigLoader.get("client_id");
        String clientSecret = ConfigLoader.get("client_secret");

        Response res = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .when()
                .post(authUrl)
                .then()
                .extract().response();

        try {
            return res.jsonPath().getString("access_token");
        } catch (Exception e) {
            return null;
        }
    }
}
