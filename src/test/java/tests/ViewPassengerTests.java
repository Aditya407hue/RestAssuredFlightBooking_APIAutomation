package tests;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * The ViewPassengerTests class contains TestNG test methods to validate the functionality
 * of various `/viewPassenger` API endpoints. It includes positive and negative test cases
 * to ensure the API behaves as expected when retrieving passenger details.
 */
public class ViewPassengerTests extends TestBase {

    /**
     * Validates the positive scenario for viewing a passenger by a valid ID.
     * The test sends a GET request to the API and checks for a 200 status code
     * and the correct passenger ID in the response body.
     */
    @Test
    public void testViewPassengerByIdPositive() {
        // Log the action of viewing a passenger with ID 2
        getTest().info("Viewing passenger with id=2");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .get("/viewPassengerById/2") // Send GET request to the endpoint
                .then()
                .statusCode(200) // Validate the response status code
                .body("passengerId", equalTo(2)); // Check that the passenger ID matches
        getTest().pass("viewPassengerById positive"); // Log test success
    }

    /**
     * Validates the negative scenario for viewing a passenger by an invalid ID.
     * The test sends a GET request to the API with a non-existent ID and expects
     * a status code of 404 (Not Found) or 204 (No Content).
     */
    @Test
    public void testViewPassengerByIdNegative() {
        // Log the action of attempting to view a non-existent passenger with ID 99999
        getTest().info("Viewing passenger with id=99999 (should not exist)");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .get("/viewPassengerById/99999") // Send GET request to the endpoint
                .then()
                .statusCode(anyOf(equalTo(404), equalTo(204))); // Validate the response status code
        getTest().pass("viewPassengerById negative"); // Log test success
    }

    /**
     * Validates the functionality of viewing the full passenger list.
     * The test sends a GET request to the API and checks for a 200 status code
     * and ensures the response contains at least one passenger.
     */
    @Test
    public void testViewPassengerList() {
        // Log the action of viewing the full passenger list
        getTest().info("Viewing full passenger list");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .get("/viewPassengerList") // Send GET request to the endpoint
                .then()
                .statusCode(200) // Validate the response status code
                .body("size()", greaterThan(0)); // Ensure the response contains at least one passenger
        getTest().pass("viewPassengerList validated"); // Log test success
    }

    /**
     * Validates the functionality of viewing a passenger by name and mobile number.
     * The test sends a GET request to the API and checks for a 200 status code
     * and the correct passenger name in the response body.
     */
    @Test
    public void testViewPassengerByNameMobile() {
        // Log the action of viewing a passenger by name and mobile number
        getTest().info("Viewing by name and mobile - Rakesh/1234567890");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .get("/viewPassengerByNameMobile/Rakesh/1234567890") // Send GET request to the endpoint
                .then()
                .statusCode(200) // Validate the response status code
                .body("passengerName", equalTo("Rakesh")); // Check that the passenger name matches
        getTest().pass("viewPassengerByNameMobile validated"); // Log test success
    }
}