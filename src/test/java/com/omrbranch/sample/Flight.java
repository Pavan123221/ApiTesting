package com.omrbranch.sample;

import com.omrbranch.baseclass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Flight extends BaseClass {
	

	public String createFlight() {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		addPayload("{\r\n" + "    \"flightName\": \"AirIndia\",\r\n" + "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n" + "}");
		Response response = getRequest("POST", "/flights");
		printResponse(response);
		String flightId = getJsonPathString(response, "data.id");
		System.out.println(flightId);
		return flightId;
	}

	public void getSingleFlight(String flightId) {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		Response response = getRequest("GET", "/flight/" + flightId);
		printResponse(response);
	}

	public void getListFLight() {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		Response response = getRequest("GET", "/flights");
		printResponse(response);
	}

	public void updateFlight(String flightId) {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		addPayload("{\r\n" + "    \"flightName\": \"AirIndia\",\r\n" + "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": 65,\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n" + "}");
		Response response = getRequest("PUT", "/flight/" + flightId);
		printResponse(response);
	}

	public void updateFlightDes(String flightId) {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		addPayload("{\r\n" + "    \"Destinations\": 60\r\n" + "}");
		Response response = getRequest("PATCH", "/flight/" + flightId);
		printResponse(response);
	}

	public void deleteFlight(String flightId) {
		initRestAssured();
		addHeader("Content-Type", "application/json");
		Response response = getRequest("DELETE", "/flight/" + flightId);
		printResponse(response);
	}

	public static void main(String[] args) {
		Flight sample = new Flight();
		String flight = sample.createFlight();
		System.out.println("####get Single Flight###");
		sample.getSingleFlight(flight);
		System.out.println("####get List Flights####");
		sample.getListFLight();
		System.out.println("#### update Flight ####");
		sample.updateFlight(flight);
		System.out.println("#### update destination only###");
		sample.updateFlightDes(flight);
		System.out.println("### delete FLight");
		sample.deleteFlight(flight);
	}

}
