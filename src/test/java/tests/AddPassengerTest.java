package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.PayloadBuilder;
import utils.TokenManager;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

/**
 * The AddPassengerTest class contains TestNG test methods to validate the functionality
 * of the `/addPassenger` API endpoint. It includes positive and negative test cases
 * to ensure the API behaves as expected under different scenarios.
 */
public class AddPassengerTest extends TestBase {

    /**
     * Validates the positive scenario for adding a passenger using valid data for user1.
     * The test sends a POST request with form parameters and checks for a 200 status code
     * and the presence of a "passengerId" in the response body.
     */
    @Test
    public void testAddPassengerPositive() {
        // Load the payload for user1 from the JSON file
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_positive.json");
        given()
                .auth().oauth2(TokenManager.getAccessToken()) // Authenticate using OAuth2
                .contentType(ContentType.URLENC) // Set content type to URL-encoded
                .formParams((Map)payload.get("user1")) // Add form parameters from the payload
                .when()
                .post("/addPassenger") // Send POST request to the endpoint
                .then()
                .statusCode(200) // Validate the response status code
                .body(containsString("passengerId")); // Check for "passengerId" in the response
        getTest().pass("Add passenger positive validated for user1"); // Log test success
    }

    /**
     * Validates the positive scenario for adding a passenger using valid data for user2.
     * Similar to the previous test, it ensures the API returns a 200 status code
     * and includes a "passengerId" in the response body.
     */
    @Test
    public void testAddPassengerPositive1() {
        // Load the payload for user2 from the JSON file
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_positive.json");
        given()
                .auth().oauth2(TokenManager.getAccessToken()) // Authenticate using OAuth2
                .contentType(ContentType.URLENC) // Set content type to URL-encoded
                .formParams((Map)payload.get("user2")) // Add form parameters from the payload
                .when()
                .post("/addPassenger") // Send POST request to the endpoint
                .then()
                .statusCode(200) // Validate the response status code
                .body(containsString("passengerId")); // Check for "passengerId" in the response
        getTest().pass("Add passenger positive validated for user2"); // Log test success
    }

    /**
     * Validates the negative scenario for adding a passenger using invalid data.
     * The test sends a POST request with invalid form parameters and expects
     * a status code of 400 (Bad Request) or 422 (Unprocessable Entity).
     */
    @Test(priority = 1)
    public void testAddPassengerNegative() {
        // Load the payload for the negative test case from the JSON file
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_negative.json");
        given()
                .auth().oauth2(TokenManager.getAccessToken()) // Authenticate using OAuth2
                .contentType(ContentType.URLENC) // Set content type to URL-encoded
                .formParams((Map)payload.get("user")) // Add form parameters from the payload
                .when()
                .post("/addPassenger") // Send POST request to the endpoint
                .then()
                .statusCode(anyOf(equalTo(400), equalTo(422))); // Validate the response status code
        getTest().pass("Add passenger negative validated"); // Log test success
    }
}
