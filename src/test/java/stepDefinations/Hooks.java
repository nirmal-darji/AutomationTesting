package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		// Write a code to get Place Id
		// Execute this code when Place Id is null
		
		stepDefination stepDef = new stepDefination();
		if(stepDefination.place_id==null) {
			stepDef.i_have_add_place_api_payload("NirmalHouse", "Spanish");
			stepDef.i_hit_the_api("addPlace", "POST");
			stepDef.i_verify_created_maps_to_using_api("place_id", "NirmalHouse", "getPlace");
		}
	}
}
