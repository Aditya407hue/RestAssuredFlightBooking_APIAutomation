package tests;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ViewPassengerTests extends TestBase {

    @Test
    public void testViewPassengerByIdPositive() {
        getTest().info("Viewing passenger with id=2");
        given()
            .accept("application/json")
        .when()
            .get("/viewPassengerById/2")
        .then()
            .statusCode(200)
            .body("passengerId", equalTo(2));
        getTest().pass("viewPassengerById positive");
    }

    @Test
    public void testViewPassengerByIdNegative() {
        getTest().info("Viewing passenger with id=99999 (should not exist)");
        given()
            .accept("application/json")
        .when()
            .get("/viewPassengerById/99999")
        .then()
            .statusCode(anyOf(equalTo(404), equalTo(204)));
        getTest().pass("viewPassengerById negative");
    }

    @Test
    public void testViewPassengerList() {
        getTest().info("Viewing full passenger list");
        given()
            .accept("application/json")
        .when()
            .get("/viewPassengerList")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
        getTest().pass("viewPassengerList validated");
    }

    @Test
    public void testViewPassengerByNameMobile() {
        getTest().info("Viewing by name and mobile - Rakesh/1234567890");
        given()
            .accept("application/json")
        .when()
            .get("/viewPassengerByNameMobile/Rakesh/1234567890")
        .then()
            .statusCode(200)
            .body("passengerName", equalTo("Rakesh"));
        getTest().pass("viewPassengerByNameMobile validated");
    }
}
