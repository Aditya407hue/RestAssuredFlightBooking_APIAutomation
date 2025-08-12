package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.PayloadBuilder;
import utils.TokenManager;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class AddPassengerTest extends TestBase {

    @Test
    public void testAddPassengerPositive() {
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_positive.json");
        given()
            .auth().oauth2(TokenManager.getAccessToken())
            .contentType(ContentType.URLENC)
            .formParams((Map)payload.get("user1"))
        .when()
            .post("/addPassenger")
        .then()
            .statusCode(200)
            .body(containsString("passengerId"));
        getTest().pass("Add passenger positive validated for user1");
    }

    @Test
    public void testAddPassengerPositive1() {
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_positive.json");
        given()
                .auth().oauth2(TokenManager.getAccessToken())
                .contentType(ContentType.URLENC)
                .formParams((Map)payload.get("user2"))
                .when()
                .post("/addPassenger")
                .then()
                .statusCode(200)
                .body(containsString("passengerId"));
        getTest().pass("Add passenger positive validated for user2");
    }

    @Test(priority = 1)
    public void testAddPassengerNegative() {
        Map<String, Object> payload = PayloadBuilder.loadJsonAsMap("testdata/addPassenger_negative.json");
        given()
            .auth().oauth2(TokenManager.getAccessToken())
            .contentType(ContentType.URLENC)
            .formParams((Map)payload.get("user"))
        .when()
            .post("/addPassenger")
        .then()
            .statusCode(anyOf(equalTo(400), equalTo(422)));
        getTest().pass("Add passenger negative validated");
    }
}
