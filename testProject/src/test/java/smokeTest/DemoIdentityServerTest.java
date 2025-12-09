package smokeTest;

import base.OAuthTokenProvider;
import base.PropertyReader;
import base.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoIdentityServerTest {

  private final RestUtils restUtils = new RestUtils();
  private final OAuthTokenProvider tokenProvider = new OAuthTokenProvider();

  @Test(groups = {"api", "integration"})
  public void genToken() {
    Response tokenResp =
        restUtils.postFormWithBasicAuth(
            PropertyReader.get("tokenEndPoint"),
            PropertyReader.get("apiUsername"),
            PropertyReader.get("apiPassword"));

    tokenResp.then().statusCode(200);
    String accessToken = tokenResp.jsonPath().getString("access_token");

    Assert.assertNotNull(accessToken, "Access token should be returned");
    Assert.assertFalse(accessToken.isBlank(), "Access token must not be blank");
  }

  @Test(
      dependsOnMethods = "genToken",
      groups = {"api", "integration"})
  public void getApplications() {
    String token = tokenProvider.getToken();
    Assert.assertNotNull(token);

    Response response =
        restUtils
            .getWithOAuth2(PropertyReader.get("applicationEndPoint"), token)
            .then()
            .extract()
            .response();
    response.then().statusCode(200);

    JsonPath jsonPath = response.jsonPath();
    String issuer = jsonPath.getString("find { it.type == 'iss' }.value");
    Assert.assertNotNull(issuer, "Issuer must be present in the response");
  }
}
