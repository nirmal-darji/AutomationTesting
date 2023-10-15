package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import resources.BuildTestData;
import resources.Endpoints;
import resources.GlobalVariables;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefination extends Utils {
	RequestSpecification requestSpecification;
	ResponseSpecification buildResponse;
	Response response;
	BuildTestData testData = new BuildTestData();
	GlobalVariables globalVariable = new GlobalVariables();
	static String place_id;
	
	@Given("I have AddPlace API payload with {string} and {string}")
	public void i_have_add_place_api_payload(String name, String language) throws IOException {																		
		requestSpecification =	given().spec(requestSpecification()).body(testData.addPlacePayload(name,language));
	}

	@When("I hit the {string} API with {string} method")
	public void i_hit_the_api(String endpoint, String httpMethod) {
		Endpoints endpointAPI= Endpoints.valueOf(endpoint);
		
		if(httpMethod.equalsIgnoreCase("POST")) {
			response= requestSpecification.
					when().post(endpointAPI.getEndpoint()).
					then().
					extract().response();	
		}else if (httpMethod.equalsIgnoreCase("GET")){
			response= requestSpecification.
					when().get(endpointAPI.getEndpoint()).
					then().
					extract().response();	
		}			
		
		String responseString = response.asString();
		System.out.println("Response of -> "+httpMethod+" method for "+endpoint +" endpoint");
		System.out.println(responseString);
		System.out.println("---------------------------------");
	}

	@Then("I verify status code is {int}")
	public void i_verify_status_code_is(Integer expectedStatusCode) {
		assertEquals(response.getStatusCode(), expectedStatusCode);
	}

	@Then("I verify {string} in the response body is {string}")
	public void i_verify_in_the_response_body_is(String key, String expectedValue) {
		String actualValue = getJsonPath(response,key);
		assertEquals(actualValue, expectedValue);
	}
	
	@Then("I get {string} from the response body")
	public void i_get_from_the_response_body(String key) {
		String keyValue = getJsonPath(response,key);		// For Parsing JSON
		if(key.equalsIgnoreCase("place_id")) {
			globalVariable.setPlaceID(keyValue);	// Stored Place ID to the Global Variable
		}
	}
	
	@Then("I verify {string} created maps to {string} using {string} API")
	public void i_verify_created_maps_to_using_api(String key, String expectedValueOfResponseKey, String endpoint) throws IOException {
		place_id =  getJsonPath(response,"place_id");
		requestSpecification =	given().spec(requestSpecification()).queryParam("place_id", place_id);
		i_hit_the_api(endpoint, "GET");
		String actualValueOfResponseKey =  getJsonPath(response,"name");
		assertEquals(actualValueOfResponseKey, expectedValueOfResponseKey);
	}
	
	@Given("I have DeletePlace API payload")
	public void i_have_delete_place_api_payload() throws IOException {
		requestSpecification =	given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
	}


}