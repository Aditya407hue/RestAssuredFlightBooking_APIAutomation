package tests;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeletePassengerTest extends TestBase {

    @Test
    public void testDeletePassengerPositive() {
        getTest().info("Deleting passenger id=11");
        given()
            .accept("application/json")
        .when()
            .delete("/deletePassengerById/11")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(204)));
        getTest().pass("delete passenger positive");
    }

    @Test
    public void testDeletePassengerNegative() {
        getTest().info("Deleting non-existent passenger id=99999");
        given()
            .accept("application/json")
        .when()
            .delete("/deletePassengerById/99999")
        .then()
            .statusCode(anyOf(equalTo(404), equalTo(204)));
        getTest().pass("delete passenger negative");
    }
}
