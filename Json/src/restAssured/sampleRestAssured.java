package restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class sampleRestAssured {

	@BeforeTest
	public void BaseDetails() {
		RestAssured.baseURI = "http://ergast.com/api";
		RestAssured.basePath = "/f1";
	}

	/**
	 * Simple test method to verify the response data using RestAssured
	 */
	@Test
	public void test_Circuites() {

		given().when().get("/2017/circuits.json").then().assertThat().body("MRData.CircuitTable.Circuits.circuitId",
				hasSize(20));

		Response total_Response = given().when().get("http://ergast.com/api/f1/2017/circuits.json");

		total_Response.prettyPrint(); // To print the response in a json format

	}

	/**
	 * Simple test method to verify the status code, content type and other
	 * headers
	 */
	@Test
	public void test_rcode_ctType() {

		given().when().get("http://ergast.com/api/f1/2017/circuits.json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().header("Content-length", "4551");

	}

	/**
	 * This test method is to use parameters in Rest Assured 1. Query Params
	 * 2.Path Params
	 */
	@Test
	public void test_RAParams() {
		try {
			String year = "2017";
			int size = 20;
			// Using path param : season
			given().pathParam("season", year).when().get("http://ergast.com/api/f1/{season}/circuits.json").then()
					.assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(size));

			// using query param :

			String originalText = "test";
			String expectedMd5CheckSum = "098f6bcd4621d373cade4e832627b4f6";

			given().param("text", originalText).when().get("http://md5.jsontest.com").then().assertThat().body("md5",
					equalTo(expectedMd5CheckSum));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}

	}

	/**
	 * This method is to use the data providers to RestAssured
	 */

	@Test(dataProvider = "seasonNoOfRaces")
	public void test_verifySeasonRaces(String season, int races) {

		try {
			given().pathParam("raceSeason", season).when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
					.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(races));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}

	}

	@DataProvider(name = "seasonNoOfRaces")
	public Object[][] records() {
		return new Object[][] {

				{ "2017", 20 }, { "2016", 21 }, { "1966", 9 } };

	}

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
