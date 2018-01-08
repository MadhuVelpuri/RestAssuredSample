
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Sample {
	HSSFWorkbook wb;
	HSSFSheet sh;

	@Test(enabled = true)
	public void f() {
		data();

	}

	public void data() {

		// CreatingJson g = new CreatingJson();
		for (int i = 0; i < 100; i++) {
			String myJson = CreatingJson.json();
			io.restassured.RestAssured.baseURI = "http://regservice-app-qa01.prf.iad1.medscape.com:8080/regservice/registration/V1";
			int guid = given().header("Accept", "application/json").header("isEncrypted", false)
					.contentType("application/json").body(myJson).when().post("/register").then().statusCode(200)
					.extract().path("guid");

			/*
			 * String myJson1 = g.json(); Response r = given().header("Accept",
			 * "application/json").header("isEncrypted", false)
			 * .contentType("application/json").body(myJson1).when().post(
			 * "/register");
			 */
			System.out.println(guid);

		}
		/*
		 * String myJson = g.json(); RestAssured.baseURI =
		 * "http://regservice-app-qa00.prf.iad1.medscape.com:8080/regservice/registration/V1";
		 * Response r = given().header("Accept",
		 * "application/json").header("isEncrypted", false)
		 * .contentType("application/json").body(myJson).when().post("/register"
		 * );
		 * 
		 * r.getBody().prettyPrint();
		 */
		/*
		 * myJson = g.json(); given().header("Accept",
		 * "application/json").header("isEncrypted",
		 * false).contentType("application/json")
		 * .body(myJson).when().post("/register").then().body("status",
		 * equalTo((1)));
		 */
	}

	public void setup() throws FileNotFoundException, IOException {
		wb = new HSSFWorkbook(new FileInputStream("D:\\Mars\\Json\\TestData_EN_ContentSearchServiceAPI.xls"));
		sh = wb.getSheet("API");
	}

	@Test(dataProvider = "Apis", enabled = false)
	public void search(String URL) throws FileNotFoundException, IOException {

		RestAssured.baseURI = "http://api.medscape.com/contentsearchservice";

		System.out.println(URL);
		given().when().get(URL).getBody().prettyPrint();

		/*
		 * given().contentType("application/json").when().body(
		 * "{\"username\": \"aamunabolu@webmd.net\",\"password\":\"6ndTeaNN?\"}"
		 * ) .post("https://jira.webmd.net/jira/rest/auth/1/session")
		 * .getBody().prettyPrint();
		 */

	}

	@Test(enabled = false)
	public void auth() {
		StringBuilder build = new StringBuilder("Demo");
		build = build.reverse();
		System.out.println(build);
	}

	@DataProvider(name = "Apis")
	public Object[][] getAPIURL() throws FileNotFoundException, IOException, InterruptedException {
		setup();
		int rows = sh.getPhysicalNumberOfRows();
		System.out.println(rows);
		Thread.sleep(2000);
		Object[][] obj = new Object[rows - 1][1];
		for (int i = 1; i <= rows - 1; i++) {
			obj[i - 1][0] = sh.getRow(i).getCell(0).toString();
		}
		return obj;
	}

}
