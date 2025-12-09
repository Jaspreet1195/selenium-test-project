package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Handles OAuth client-credentials token retrieval and caching. Keeps SRP by separating token
 * concerns from request helpers.
 */
public class OAuthTokenProvider {

  private final String baseUrl;
  private final String tokenEndpoint;
  private final String clientId;
  private final String clientSecret;

  private String cachedToken;
  private long tokenExpiryTime = 0;
  private final Object tokenLock = new Object();

  public OAuthTokenProvider() {
    this(
        PropertyReader.getOrDefault("apiBaseURL", ""),
        PropertyReader.get("tokenEndPoint"),
        PropertyReader.get("apiUsername"),
        PropertyReader.get("apiPassword"));
  }

  public OAuthTokenProvider(
      String baseUrl, String tokenEndpoint, String clientId, String clientSecret) {
    this.baseUrl = baseUrl;
    this.tokenEndpoint = tokenEndpoint;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  /** Returns a valid cached token or fetches a new one if missing/expired. */
  public String getToken() {
    synchronized (tokenLock) {
      long now = System.currentTimeMillis();
      if (cachedToken != null && now < tokenExpiryTime) {
        return cachedToken;
      }

      Response response =
          RestAssured.given()
              .baseUri(baseUrl)
              .auth()
              .preemptive()
              .basic(clientId, clientSecret)
              .header("Content-Type", "application/x-www-form-urlencoded")
              .formParam("grant_type", "client_credentials")
              .when()
              .post(tokenEndpoint)
              .then()
              .extract()
              .response();

      if (response.statusCode() != 200) {
        throw new IllegalStateException("Failed to generate token: " + response.asString());
      }

      cachedToken = response.jsonPath().getString("access_token");
      int expiresInSeconds = response.jsonPath().getInt("expires_in");
      // Refresh 10s early to avoid edge expiry.
      tokenExpiryTime = now + (expiresInSeconds - 10) * 1000L;
      return cachedToken;
    }
  }
}
