package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.TokenManager;

import static org.testng.Assert.*;

public class AuthTests extends TestBase {

    @Test
    public void testGetAccessToken() {
        String token = TokenManager.getAccessToken();
        getTest().info("Access token length: " + (token == null ? "null" : token.length()));
        assertNotNull(token, "Access token should not be null");
    }
}
