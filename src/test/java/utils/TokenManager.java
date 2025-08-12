package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * The TokenManager class is responsible for managing OAuth2 access tokens.
 * It retrieves an access token by making a POST request to the authentication server
 * using client credentials.
 */
public class TokenManager {

    /**
     * Retrieves an OAuth2 access token using the client credentials grant type.
     * The method reads the authentication URL, client ID, and client secret
     * from the configuration and sends a POST request to obtain the token.
     *
     * @return The access token as a String, or null if the token cannot be retrieved.
     */
    public static String getAccessToken() {
        // Retrieve authentication details from the configuration
        String authUrl = ConfigLoader.get("authUrl");
        String clientId = ConfigLoader.get("client_id");
        String clientSecret = ConfigLoader.get("client_secret");

        // Send a POST request to the authentication server to get the access token
        Response res = RestAssured.given()
                .relaxedHTTPSValidation() // Allow relaxed HTTPS validation
                .contentType("application/x-www-form-urlencoded") // Set content type
                .formParam("grant_type", "client_credentials") // Specify grant type
                .formParam("client_id", clientId) // Add client ID
                .formParam("client_secret", clientSecret) // Add client secret
                .when()
                .post(authUrl) // Send POST request to the auth URL
                .then()
                .extract().response(); // Extract the response

        try {
            // Extract and return the access token from the response
            return res.jsonPath().getString("access_token");
        } catch (Exception e) {
            // Return null if an exception occurs while extracting the token
            return null;
        }
    }
}