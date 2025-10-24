package com.omrbranch.sample;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Samplers {
	
	public void getSingleFlight() {
		RequestSpecification reqSpec;

		// 1. Initialize the rest assured class
		reqSpec = RestAssured.given();

		// 2. Headers,req body,auth
		reqSpec = reqSpec.header("Content-Type", "application/json");

		// 3.EndPoint,req type
		Response response = reqSpec.get("https://omrbranch.com/api/flight/52791");

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
	public void getListFlight() {
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

	public void createFlight() {
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


	}
	public void updateFlight() {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		//req body or payload
		reqSpec=reqSpec.body("{\r\n"
				+ "    \"flightName\": \"AirIndia\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": 37,\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		
		Response response = reqSpec.put("https://omrbranch.com/api/flight/52990");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	}
	
	public void updateDes() {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		//req body or payload
		reqSpec=reqSpec.body("{\r\n"
				+ "    \"Destinations\": 86\r\n"
				+ "}");
		
		Response response = reqSpec.patch("https://omrbranch.com/api/flight/52990");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// response body--> asString
		String asString = response.asString();
		System.out.println(asString);

		// Response Body---> asPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	
	public void deleteFlight() {
		RequestSpecification reqSpec;

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		
		
		Response response = reqSpec.delete("https://omrbranch.com/api/flight/52990");

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
		Samplers s=new Samplers();
		System.out.println("GEt Single FLight");
		s.getSingleFlight();
		System.out.println("get List Flights");
		s.getListFlight();
		System.out.println("update the flight");
		s.updateFlight();
		System.out.println("update the destination only");
		s.updateDes();
		System.out.println("delete the flight");
		s.deleteFlight();
		
	}

}
