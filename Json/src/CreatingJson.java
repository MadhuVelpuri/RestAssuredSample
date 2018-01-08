import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreatingJson {

	public static String json() {
		String output = null;

		new RandomStringUtils();
		String ranValue = RandomStringUtils.randomAlphabetic(4).toLowerCase();

		JSONArray address = new JSONArray();
		JSONArray member = new JSONArray();
		JSONObject addressJson = new JSONObject();
		JSONObject userDetails = new JSONObject();
		JSONObject register = new JSONObject();
		JSONObject userEmail = new JSONObject();
		JSONObject professions = new JSONObject();
		JSONObject profDetails = new JSONObject();

		professions.put("type", "med");
		professions.put("siteId", "MED");
		professions.put("password", "medscape");
		professions.put("userName", "sam" + ranValue + "@gmail.com");

		// Address
		addressJson.put("contactType", 1);
		addressJson.put("countryAbbreviation", "us");
		addressJson.put("zipCode", 10110);

		professions.put("address", address.put(addressJson));

		// Address
		addressJson.put("contactType", 2);
		addressJson.put("countryAbbreviation", "de");
		addressJson.put("zipCode", 10110);

		professions.put("address", address.put(addressJson));
		// person
		userDetails.put("firstName", "Sam");
		userDetails.put("lastName", "kumar");
		userEmail.put("address", "sam" + ranValue + "@gmail.com");
		userDetails.put("email", userEmail);
		professions.put("person", userDetails);

		// 32 cardilogy MOC , 45
		// profession
		profDetails.put("locale", "en_us");
		profDetails.put("professionId", 10);
		profDetails.put("specialtyId", 32);
		profDetails.put("degreeId", 3);
		profDetails.put("occupationId", JSONObject.NULL);
		profDetails.put("medSchoolId", 401);

		register.put("en_us", profDetails);
		professions.put("professions", register);

		professions.put("registeredOn", "en_us");
		professions.put("userName", "sam" + ranValue + "@gmail.com");
		professions.put("memberDomains", member.put("en_us"));
		output = professions.toString();
		// System.out.println(output);
		System.out.println("sam" + ranValue + "@gmail.com");
		System.out.println(output);
		return output;

	}

	public static void main(String[] args) throws JSONException {
		json();
	}

}
