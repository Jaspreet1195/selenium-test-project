package smokeTest;

import org.testng.annotations.Test;

import base.PropertyReader;
import baseTest.RestUtils;

//imporom.github.dockerjava.transport.DockerHttpClient.Response;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DemoIdentityServerTest {

	RestUtils restUtils = new RestUtils();
	String accessToken;

	@Test
	public void genToken() {

		Response tokenResp = restUtils.postWithBasicAuth(PropertyReader.get("tokenEndPoint"),
				PropertyReader.get("apiUsername"), PropertyReader.get("apiPassword"));
		tokenResp.then().statusCode(200);
		accessToken = tokenResp.jsonPath().getString("access_token");
		System.out.println("Token: " + accessToken);
		System.out.println(tokenResp.asString());
	}

	@Test
	public void getApplications() {
				
		restUtils.getValidToken(PropertyReader.get("tokenEndPoint"), PropertyReader.get("apiUsername"), PropertyReader.get("apiPassword"));
        
		// Call protected API
		Response response = restUtils.postWithOAuth2WithoutBody(PropertyReader.get("applicationEndPoint"));
		System.out.println("Response is"+ response.asString());
		
		// Parse JSON
        JsonPath jsonPath = response.jsonPath();

        // Example: Get client_id value
        String clientId = jsonPath.getString("find { it.type == 'iss' }.value");
        System.out.println("Client ID: " + clientId);
		
	}

}
