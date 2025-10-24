package com.omrbranch.sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.address.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.DeleteAddress_Output_Pojo;
import com.omrbranch.pojo.address.DeleteUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.UpdateAddress_Intput_Pojo;
import com.omrbranch.pojo.address.UpdateAddress_Output_Pojo;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.pojo.statelist.Datum;
import com.omrbranch.pojo.statelist.StateList_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoginApi extends BaseClass {

	String stateIdText;
	int cityId;
	int stateId;
	String logtoken;
	int address_id;
	String address_idText;
	

	public void login() {
		initRestAssured();
		addHeader("accept", "application/json");
		addBasicAuth("pavanjypsy@gmail.com", "Power@007");
		Response response = getRequest("POST", "/postmanBasicAuthLogin");
		PostmanBasicAuthLogin_Output_Pojo output = response.as(PostmanBasicAuthLogin_Output_Pojo.class);

		String first_name = output.getData().getFirst_name();
		System.out.println(first_name);

		logtoken = output.getData().getLogtoken();
		System.out.println(logtoken);
		getStatusCode(response);
	}

	public void getStateList() {
		initRestAssured();
		addHeader("accept", "application/json");
		Response response = getRequest("GET", "/stateList");
		StateList_Output_Pojo output = response.as(StateList_Output_Pojo.class);
		ArrayList<Datum> data = output.getData();
		for (Datum eachStateList : data) {
			String name = eachStateList.getName();
			if (name.equals("Tamil Nadu")) {
				stateId = eachStateList.getId();
				System.out.println(stateId);
				stateIdText = String.valueOf(stateId);

				break;
			}
		}
	}

	public void getCityList() {
		initRestAssured();
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		CityList_Input_Pojo input = new CityList_Input_Pojo(stateIdText);
		addPayloadObj(input);

		Response response = getRequest("POST", "/cityList");

		CityList_Output_Pojo output = response.as(CityList_Output_Pojo.class);
		ArrayList<com.omrbranch.pojo.citylist.Datum> cityList = output.getData();
		for (com.omrbranch.pojo.citylist.Datum eachCityList : cityList) {
			String cityName = eachCityList.getName();
			if (cityName.equals("Yercaud")) {
				cityId = eachCityList.getId();
				System.out.println(cityId);
			}

		}

	}

	public void addUserAddress() {
		initRestAssured();
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("Raj", "V", "123456789",
				"AK", stateId, cityId, 101, "123456", "Chennai", "Office");
		addPayloadObj(addUserAddress_Input_Pojo);

		Response response = getRequest("POST", "/addUserAddress");
		printResponse(response);

		AddUserAddress_Output_Pojo output = response.as(AddUserAddress_Output_Pojo.class);
		address_id = output.getAddress_id();
		 address_idText = String.valueOf(address_id);
		System.out.println(address_id);

	}

	public void updateUserAddress() {
		initRestAssured();
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		UpdateAddress_Intput_Pojo addUserAddress_Input_Pojo = new UpdateAddress_Intput_Pojo(address_idText, "Pavan", "JY",
				"9108821236", "VK", stateId, cityId, 101, "600097", "Bengaluru", "Home");
		addPayloadObj(addUserAddress_Input_Pojo);

		Response response = getRequest("PUT", "/updateUserAddress");
		printResponse(response);

		UpdateAddress_Output_Pojo output = response.as(UpdateAddress_Output_Pojo.class);
		String message = output.getMessage();
		System.out.println(message);
	}

	public void deleteUserAddress() {
		initRestAssured();
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		DeleteUserAddress_Input_Pojo delete = new DeleteUserAddress_Input_Pojo(address_idText);
		addPayloadObj(delete);

		Response response = getRequest("DELETE", "/deleteAddress");
		printResponse(response);
		DeleteAddress_Output_Pojo as = response.as(DeleteAddress_Output_Pojo.class);
		String message = as.getMessage();
		System.out.println(message);
	}
	
	
	public  void addPrpfile() {
		initRestAssured();
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", " multipart/form-data");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		addPayloadFile("profile_picture", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Data\\screenshot.png"));
		
		Response response = getRequest("POST", "/changeProfilePic");
		
		printResponse(response);
		
		
	}

	public static void main(String[] args) {
		LoginApi loginAPI = new LoginApi();
		loginAPI.login();
		//loginAPI.getStateList();
		//loginAPI.getCityList();
		//loginAPI.addUserAddress();
		//loginAPI.updateUserAddress();
		//loginAPI.deleteUserAddress();
		loginAPI.addPrpfile();

	}

}
