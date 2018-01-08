package restAssured;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class userActivityServices {

	@Test
	public void test_UserActivity() {

		/*
		 * given().header("Cookie",
		 * "mednet=Q+wbds8l4BiS7qq2bVlChY9Hw5DyhbABIsniVbveb4gODfIoXJ8HWltXkFVoZ3xv")
		 * .header("Accept", "application/json").when() .get(
		 * "http://useractivityservice01-app-qa01.prf.iad1.medscape.com:8080/useractivityservice/get/cme01?offset=1&limit=1")
		 * .then().statusCode(200);
		 */

		Response response = given()
				.header("Cookie", "mednet=Q+wbds8l4BiS7qq2bVlChY9Hw5DyhbABIsniVbveb4gODfIoXJ8HWltXkFVoZ3xv")
				.header("Accept", "application/json").when()
				.get("http://useractivityservice01-app-qa01.prf.iad1.medscape.com:8080/useractivityservice/get/cme01?offset=1&limit=1");

		response.prettyPrint();
		
		//JSONArray values = response.getJSONArray("values");

		String jsonString = response.getBody().asString();
		JsonPath jsonPath = JsonPath.from(jsonString);

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {

			String errorCode = jsonPath.get("data[0].tagName");
			int active = jsonPath.getInt("data[0].active");
			int count = jsonPath.getInt("count");

			System.out.println(statusCode);
			System.out.println(errorCode + " " + active + " " + count);

		}
	}

	@Test
	public void test_cmeActivity() throws IOException {

		String jsonBody = new String(Files.readAllBytes(Paths.get(
				"D:\\ResponsiveFramework\\viewcontent\\viewcontent\\src\\it\\resources\\TestInputs\\userActivity.json")));

		Response response = given()
				.header("Cookie", "mednet=Q+wbds8l4BiS7qq2bVlChY9Hw5DyhbABIsniVbveb4gODfIoXJ8HWltXkFVoZ3xv")
				.header("Accept", "application/json").contentType("application/json").body(jsonBody).when()
				.post("http://useractivityservice01-app-qa01.prf.iad1.medscape.com:8080/useractivityservice/save");

		response.prettyPrint();

		String jsonString = response.getBody().asString();
		JsonPath jsonPath = JsonPath.from(jsonString);

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {

			int errorCode = jsonPath.get("errorCode");
			System.out.println("Error Code is : " + errorCode);
		}

	}

}
