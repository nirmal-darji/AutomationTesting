package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class BuildTestData {

	public AddPlace addPlacePayload(String name, String language) {
		AddPlace addPlacePayload = new AddPlace();
		addPlacePayload.setAccuracy(50);
		addPlacePayload.setAddress("99, Outside layout, Gohen 909");
		addPlacePayload.setLanguage(language);
		addPlacePayload.setWebsite("http://facebook.com");
		addPlacePayload.setPhone_number("(+91) 983 893 3937");
		addPlacePayload.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoeeepppaark");
		myList.add("ShoppeeeOn");
		addPlacePayload.setTypes(myList);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addPlacePayload.setLocation(loc);
		return addPlacePayload;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
