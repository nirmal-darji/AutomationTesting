package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;		// Static needed to ensure log file wont get null for new test
	public RequestSpecification requestSpecification() throws IOException {
		if(req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));		// Creates file dynamically and used to add all logs
			req =	new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))		// Logs Request
					.addFilter(ResponseLoggingFilter.logResponseTo(log))	// Logs Response
					.setContentType(ContentType.JSON)
					.build();
			return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);		// For Parsing JSON
		String actualKeyValue = js.getString(key);
		return actualKeyValue;
	}
}
