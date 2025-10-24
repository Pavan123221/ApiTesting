
package com.omrbranch.baseclass;

	import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

	public class BaseClass {
		 RequestSpecification reqSpec;
		  Response response;
		
		static {
			RestAssured.baseURI = "https://www.omrbranch.com/api";
		}
		
	 public void addBasicAuth(String userName,String password) {
		 reqSpec=reqSpec.auth().preemptive().basic(userName, password);
	 }
	
	  public void initRestAssured() {
	    reqSpec = RestAssured.given();

	  }

	  public void addHeader(String key, String value) {
	    reqSpec = reqSpec.header(key, value);

	  }
	  public void addHeaders(Headers headers) {
		    reqSpec = reqSpec.headers(headers);

		  }

	  public void addPayload(String body) {
	    reqSpec = reqSpec.body(body);
	  }
	  
	  public void addPayloadObj(Object body) {
		    reqSpec = reqSpec.body(body);
		  }
	  
	  public void addPayloadFile( String key,File file ) {
        reqSpec = reqSpec.multiPart(key, file);
	}

	  public Response getRequest(String type, String endpoint) {
	    switch (type) {
	    case "GET":
	      response = reqSpec.get(endpoint);

	      break;
	    case "POST":
	      response = reqSpec.post(endpoint);

	      break;
	    case "PUT":
	      response = reqSpec.put(endpoint);

	      break;
	    case "PATCH":
	      response = reqSpec.patch(endpoint);

	      break;
	    case "DELETE":
	      response = reqSpec.delete(endpoint);

	      break;

	    default:
	      break;
	    }
	    return response;
	  }

	  public int getStatusCode(Response response) {
	    int statusCode = response.getStatusCode();
	    return statusCode;
	  }

	  public String getResBodyAsPrettyString(Response response) {
	    String asPrettyString = response.asPrettyString();
	    return asPrettyString;
	  }
	  
	  public void printResponse(Response response) {
		    int statusCode = response.getStatusCode();
		    System.out.println("Status Code  " + statusCode);

		    String asPrettyString = response.asPrettyString();
		    System.out.println("Response Body " + asPrettyString);

		  }

		  public String getJsonPathString(Response response, String path) {
		    String value = response.jsonPath().getString(path);
		    return value;
		  }

		}

	
