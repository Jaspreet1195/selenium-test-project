package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Thin REST helper focused on request execution. OAuth token handling lives in {@link
 * OAuthTokenProvider}.
 */
public class RestUtils {

  private final String baseUrl;

  public RestUtils() {
    this(PropertyReader.getOrDefault("apiBaseURL", ""));
  }

  public RestUtils(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public Response get(String endpoint) {
    return RestAssured.given().baseUri(baseUrl).when().get(endpoint).then().extract().response();
  }

  public Response getWithBasicAuth(String endpoint, String username, String password) {
    return RestAssured.given()
        .baseUri(baseUrl)
        .auth()
        .preemptive()
        .basic(username, password)
        .when()
        .get(endpoint)
        .then()
        .extract()
        .response();
  }

  public Response postFormWithBasicAuth(String endpoint, String username, String password) {
    return RestAssured.given()
        .baseUri(baseUrl)
        .auth()
        .preemptive()
        .basic(username, password)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .formParam("grant_type", "client_credentials")
        .formParam("scope", "api")
        .when()
        .post(endpoint)
        .then()
        .extract()
        .response();
  }

  public Response getWithOAuth2(String endpoint, String token) {
    return RestAssured.given()
        .baseUri(baseUrl)
        .auth()
        .oauth2(token)
        .when()
        .get(endpoint)
        .then()
        .extract()
        .response();
  }

  public Response postJsonWithOAuth2(String endpoint, String token, String body) {
    return RestAssured.given()
        .baseUri(baseUrl)
        .auth()
        .oauth2(token)
        .header("Content-Type", "application/json")
        .body(body)
        .when()
        .post(endpoint)
        .then()
        .extract()
        .response();
  }
}
