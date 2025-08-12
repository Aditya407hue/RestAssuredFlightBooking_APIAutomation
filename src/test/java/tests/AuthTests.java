package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.TokenManager;

import static org.testng.Assert.*;

/**
 * The AuthTests class contains TestNG test methods to validate the functionality
 * of authentication-related operations, such as retrieving an access token.
 */
public class AuthTests extends TestBase {

    /**
     * Validates the retrieval of an OAuth2 access token.
     * The test ensures that the access token is not null and logs its length.
     */
    @Test
    public void testGetAccessToken() {
        // Retrieve the access token using the TokenManager utility
        String token = TokenManager.getAccessToken();

        // Log the length of the access token or indicate if it is null
        getTest().info("Access token length: " + (token == null ? "null" : token.length()));

        // Assert that the access token is not null
        assertNotNull(token, "Access token should not be null");
    }
}