package resources;

//enum is a special class in Java which has a collection of constants & methods

public enum Endpoints {
	addPlace("/maps/api/place/add/json"),
	getPlace("/maps/api/place/get/json"),
	deletePlace("/maps/api/place/delete/json");
	
	
	private String endpoint;
	Endpoints(String endpoint) {
		// TODO Auto-generated constructor stub
		this.endpoint = endpoint;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
}
