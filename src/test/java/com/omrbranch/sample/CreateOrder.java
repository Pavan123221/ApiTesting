package com.omrbranch.sample;

import java.util.ArrayList;
import java.util.List;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.additem.AddProduct_Input_Pojo;
import com.omrbranch.pojo.additem.AddProduct_Output_Pojo;
import com.omrbranch.pojo.address.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.SetAddress_Input_Pojo;
import com.omrbranch.pojo.cartitems.GetCartItem_Output_Pojo;
import com.omrbranch.pojo.categoryList.CategoryList;
import com.omrbranch.pojo.categoryList.CategoryList_Output_Pojo;
import com.omrbranch.pojo.categoryList.ChildCatList;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.pojo.createorder.CreateOrder_Input_Pojo;
import com.omrbranch.pojo.createorder.CreateOrder_Output_Pojo;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.pojo.productlist.ChildProductList;
import com.omrbranch.pojo.productlist.ProductList_Input_Pojo;
import com.omrbranch.pojo.productlist.ProductList_Output_Pojo;
import com.omrbranch.pojo.productlist.Variation;
import com.omrbranch.pojo.statelist.Datum;
import com.omrbranch.pojo.statelist.StateList_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CreateOrder extends BaseClass {
	String logtoken;
	String textCategoryId;
	String textProductid;
	String variantId;
	String stateIdText;
	int cityId;
	int stateId;
	String textCartId;
	int address_id;
	String address_idText;

	public void addFavouritesDetails() {
		initRestAssured();
		addHeader("accept", "application/json");
		Response response = getRequest("GET", "/categoryList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		CategoryList_Output_Pojo categoryList_output_Pojo = response.as(CategoryList_Output_Pojo.class);
		ArrayList<CategoryList> categoryLists = categoryList_output_Pojo.getData();
		for (CategoryList categoryList : categoryLists) {
			String name = categoryList.getName();
			if (name.equals("Grocery")) {
				ArrayList<ChildCatList> child_cat_list = categoryList.getChild_cat_list();
				for (ChildCatList childCategoryList : child_cat_list) {
					String name2 = childCategoryList.getName();
					if (name2.equals("Fruit & Nuts")) {
						int categoryId = childCategoryList.getId();
						textCategoryId = String.valueOf(categoryId);
						System.out.println("categoryID : "+categoryId);
					}

				}
			}
		}
	}

	public void productList() {
		initRestAssured();
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		ProductList_Input_Pojo productList_Input_Pojo = new ProductList_Input_Pojo(textCategoryId, "0");
		addPayloadObj(productList_Input_Pojo);

		Response response = getRequest("POST", "/productList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		ProductList_Output_Pojo productList_Output_Pojo = response.as(ProductList_Output_Pojo.class);
		ArrayList<ChildProductList> data = productList_Output_Pojo.getData();
		for (ChildProductList childProductList : data) {
			String name = childProductList.getName();
			if (name.equals("Nuts & Seeds - Raw Peanut")) {
				int productId = childProductList.getId();
				System.out.println("productId: "+productId);
				textProductid = String.valueOf(productId);
				ArrayList<Variation> variations = childProductList.getVariations();
				for (Variation eachVariations : variations) {
					String specifications = eachVariations.getSpecifications();
					if (specifications.equals("1 kg")) {
						int id = eachVariations.getId();
						System.out.println( "variantID: "+id);
						variantId = String.valueOf(id);

					}
				}

			}

		}
	}

	public void login() {
		initRestAssured();
		addHeader("accept", "application/json");
		addBasicAuth("pavanjypsy@gmail.com", "Power@007");
		Response response = getRequest("POST", "/postmanBasicAuthLogin");
		PostmanBasicAuthLogin_Output_Pojo output = response.as(PostmanBasicAuthLogin_Output_Pojo.class);

		String first_name = output.getData().getFirst_name();
		System.out.println("name: "+first_name);

		logtoken = output.getData().getLogtoken();
		getStatusCode(response);
	}

	public void addToCart() {
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

		AddProduct_Input_Pojo addProduct_Input_Pojo = new AddProduct_Input_Pojo(textProductid, variantId, "plus");
		addPayloadObj(addProduct_Input_Pojo);

		Response response = getRequest("POST", "/addToCart");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		AddProduct_Output_Pojo addProduct_Output_Pojo = response.as(AddProduct_Output_Pojo.class);
		String message = addProduct_Output_Pojo.getMessage();
		System.out.println(message);
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
				System.out.println("StateID: "+stateId);
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
				System.out.println("cityId: "+cityId);
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
		System.out.println("addressId: "+address_id);

	}

	public void getCartItems() {
		initRestAssured();
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		Response response = getRequest("GET", "/getCartItems");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		GetCartItem_Output_Pojo getCartItem_Output_Pojo = response.as(GetCartItem_Output_Pojo.class);
		String message = getCartItem_Output_Pojo.getMessage();
		System.out.println(message);
		ArrayList<com.omrbranch.pojo.cartitems.Datum> data = getCartItem_Output_Pojo.getData();
		com.omrbranch.pojo.cartitems.Datum datum = data.get(0);
		int cart_id = datum.getCart_id();
		textCartId = String.valueOf(cart_id);
		System.out.println("cartId: "+cart_id);

	}

	public void createOrderDetails() {
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
		CreateOrder_Input_Pojo createOrder_Input_Pojo = new CreateOrder_Input_Pojo("debit_card", "5555555555552222",
				"visa", "2026", "03", "123");
		addPayloadObj(createOrder_Input_Pojo);

		Response response = getRequest("POST", "/createOrder");
		int statusCode = getStatusCode(response);
		
		CreateOrder_Output_Pojo createOrder_Output_Pojo = response.as(CreateOrder_Output_Pojo.class);
		com.omrbranch.pojo.createorder.Datum data = createOrder_Output_Pojo.getData();
		int order_no = data.getOrder_no();
		System.out.println("orderNumber: "+order_no);
		String message = createOrder_Output_Pojo.getMessage();
		System.out.println(message);
	}

	public void setAddressData() {
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

		SetAddress_Input_Pojo setAddress_Input_Pojo = new SetAddress_Input_Pojo(address_idText, textCartId);
		addPayloadObj(setAddress_Input_Pojo);

		Response response = getRequest("POST", "/setAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

	}

	public static void main(String[] args) {
		CreateOrder createOrder = new CreateOrder();
		createOrder.login();
		createOrder.addFavouritesDetails();
		createOrder.productList();
		createOrder.addToCart();
		createOrder.getCartItems();
		createOrder.getStateList();
		createOrder.getCityList();
		createOrder.addUserAddress();
		createOrder.setAddressData();
		createOrder.createOrderDetails();

	}

}
