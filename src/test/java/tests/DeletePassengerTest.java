package tests;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * The DeletePassengerTest class contains TestNG test methods to validate the functionality
 * of the `/deletePassengerById` API endpoint. It includes positive and negative test cases
 * to ensure the API behaves as expected when deleting passengers.
 */
public class DeletePassengerTest extends TestBase {

    /**
     * Validates the positive scenario for deleting a passenger with a valid ID.
     * The test sends a DELETE request to the API and checks for a status code of 200 (OK)
     * or 204 (No Content), indicating successful deletion.
     */
    @Test
    public void testDeletePassengerPositive() {
        // Log the action of deleting a passenger with ID 11
        getTest().info("Deleting passenger id=11");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .delete("/deletePassengerById/11") // Send DELETE request to the endpoint
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(204))); // Validate the response status code
        getTest().pass("delete passenger positive"); // Log test success
    }

    /**
     * Validates the negative scenario for deleting a non-existent passenger.
     * The test sends a DELETE request to the API with an invalid ID and expects
     * a status code of 404 (Not Found) or 204 (No Content), indicating no action taken.
     */
    @Test
    public void testDeletePassengerNegative() {
        // Log the action of attempting to delete a non-existent passenger with ID 99999
        getTest().info("Deleting non-existent passenger id=99999");
        given()
                .accept("application/json") // Specify the expected response format
                .when()
                .delete("/deletePassengerById/99999") // Send DELETE request to the endpoint
                .then()
                .statusCode(anyOf(equalTo(404), equalTo(204))); // Validate the response status code
        getTest().pass("delete passenger negative"); // Log test success
    }
}