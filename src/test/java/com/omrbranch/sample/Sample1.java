
package com.omrbranch.sample;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Sample1 {

	public String createFlight() {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		//req body or payload
		reqSpec=reqSpec.body("{\r\n"
				+ "    \"flightName\": \"AirIndia\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		
		Response response = reqSpec.post("https://omrbranch.com/api/flights");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		
		String flightId = response.jsonPath().getString("data.id");
		System.out.println(flightId);
		return flightId;

	}

	public void getSingleFlight(String flightId) {
		RequestSpecification reqSpec;

		// 1. Initialize the rest assured class
		reqSpec = RestAssured.given();

		// 2. Headers,req body,auth
		reqSpec = reqSpec.header("Content-Type", "application/json");

		// 3.EndPoint,req type
		Response response = reqSpec.get("https://omrbranch.com/api/flight/"+flightId+"");

		// 4. get the status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	}

	public void updateFlight(String flightId) {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		//req body or payload
		reqSpec=reqSpec.body("{\r\n"
				+ "    \"flightName\": \"AirInda\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": 37,\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		
		Response response = reqSpec.put("https://omrbranch.com/api/flight/"+flightId+"");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	}

	public void updateFlightDes(String flightId) {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		//req body or payload
		reqSpec=reqSpec.body("{\r\n"
				+ "    \"Destinations\": 86\r\n"
				+ "}");
		
		Response response = reqSpec.patch("https://omrbranch.com/api/flight/"+flightId+"");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	public void deleteFlight(String flightId) {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		
		
		Response response = reqSpec.delete("https://omrbranch.com/api/flight/"+flightId+"");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	public void getListFlights() {
		RequestSpecification reqSpec;

		// 1. Initialize the rest assured class
		reqSpec = RestAssured.given();

		// 2. Headers,req body,auth
		reqSpec = reqSpec.header("Content-Type", "application/json");

		// 3.EndPoint,req type
		Response response = reqSpec.get("https://omrbranch.com/api/flights?page=1");

		// 4. get the status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	public static void main(String[] args) {
		Sample1 sample = new Sample1();
		String flight = sample.createFlight();

		sample.getSingleFlight(flight);
		sample.updateFlight(flight);
		sample.updateFlightDes(flight);
		sample.deleteFlight(flight);
		sample.getListFlights();

	}

}
