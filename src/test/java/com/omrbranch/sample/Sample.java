package com.omrbranch.sample;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sample {
	public static void main(String[] args) throws IOException, ParseException {
		// 1.Mention the path of the file
		FileReader fileReader = new FileReader(
				"C:\\Users\\DELL\\eclipse-workspace\\ApiTesting\\src\\test\\resources\\Employee.json");

		// 2.create object for JSONParser class
		JSONParser jsonParser = new JSONParser();

		// 3. pass the json file to fetch value
		Object object = jsonParser.parse(fileReader);

		// convert to jsonObject
		JSONObject jsonObject = (JSONObject) object;

		Object object2 = jsonObject.get("page");
		System.out.println("page: " + object2);

		Object data = jsonObject.get("per_page");
		System.out.println("perpage: " + data);

		Object data2 = jsonObject.get("total");
		System.out.println("total: " + data2);

		Object object3 = jsonObject.get("total_pages");
		System.out.println("total pages: " + object3);

		Object object4 = jsonObject.get("data");
		JSONArray jsonArray = (JSONArray) object4;
		for (int i = 0; i < jsonArray.size(); i++) {
			Object object5 = jsonArray.get(i);

			JSONObject jsonObject2 = (JSONObject) object5;
			Object object6 = jsonObject2.get("id");
			System.out.println("id :" + object6);

			Object object7 = jsonObject2.get("flightName");
			System.out.println("Flight Name:" + object7);

			Object object8 = jsonObject2.get("Country");
			System.out.println("Country: " + object8);

			Object object9 = jsonObject2.get("Destinations");
			System.out.println("Designation :" + object9);

			Object object10 = jsonObject2.get("URL");
			System.out.println("URl: " + object10);

		}

		Object object5 = jsonObject.get("support");
		JSONObject jsonObject3 = (JSONObject) object5;
		Object object6 = jsonObject3.get("url");
		System.out.println("url: " + object6);

		Object object7 = jsonObject3.get("text");
		System.out.println("text: " + object7);

	}

}
