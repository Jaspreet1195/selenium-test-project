package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {

	private String baseUrl = PropertyReader.get("apiBaseURL");

	// Cached token and expiry info
	private String cachedToken;
	private long tokenExpiryTime = 0; // in epoch millis

	/**
	 * GET request with Basic Auth
	 */
	public Response getWithBasicAuth(String endpoint, String username, String password) {
		return RestAssured.given().baseUri(baseUrl).auth().preemptive().basic(username, password).when().get(endpoint)
				.then().extract().response();
	}

	public Response postWithBasicAuth(String endpoint, String username, String password) {
		return RestAssured.given().baseUri(baseUrl).auth().preemptive().basic(username, password)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials").formParam("scope", "api").when().post("/connect/token")
				.then().statusCode(200).extract().response();

	}

	/**
	 * GET request with OAuth2 Token
	 */
	public Response getWithOAuth2(String endpoint, String token) {
		return RestAssured.given().baseUri(baseUrl).auth().oauth2(token).when().get(endpoint).then().extract()
				.response();
	}

	/**
	 * POST request with OAuth2 Token
	 */
	public Response postWithOAuth2(String endpoint, String token, String body) {
		return RestAssured.given().baseUri(baseUrl).auth().oauth2(token).header("Content-Type", "application/json")
				.body(body).when().post(endpoint).then().extract().response();
	}

	public Response postWithOAuth2WithoutBody(String endpoint )
    {
      return RestAssured.given()
            .baseUri(baseUrl)
            .auth().oauth2(cachedToken)
            .get(endpoint);
}

	/**
	 * Generate OAuth2 Token if not cached or expired
	 */
	public String getValidToken(String tokenEndpoint, String clientId, String clientSecret) {
		long currentTime = System.currentTimeMillis();

		// ✅ Reuse token if still valid
		if (cachedToken != null && currentTime < tokenExpiryTime) {
			return cachedToken;
		}

		// 🔑 Otherwise, generate a new token
		Response response = RestAssured.given().baseUri(baseUrl).auth().preemptive().basic(clientId, clientSecret) // client
																													// creds
				.header("Content-Type", "application/x-www-form-urlencoded").formParam("grant_type", "client_credentials").when()
				.post(tokenEndpoint).then().extract().response();

		if (response.statusCode() == 200) {
			cachedToken = response.jsonPath().getString("access_token");
			int expiresIn = response.jsonPath().getInt("expires_in"); // usually in seconds

			// Store expiry in millis (slightly before actual expiry for safety, e.g., 10s
			// buffer)
			tokenExpiryTime = currentTime + (expiresIn - 10) * 1000L;

			System.out.println("🔑 New Token Generated: " + cachedToken);
			return cachedToken;
		} else {
			throw new RuntimeException("Failed to generate token: " + response.asString());
		}
	}
}
