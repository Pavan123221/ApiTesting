package com.omrbranch.sample;

import java.util.ArrayList;
import java.util.List;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.additem.AddProduct_Input_Pojo;
import com.omrbranch.pojo.additem.AddProduct_Output_Pojo;
import com.omrbranch.pojo.categoryList.CategoryList;
import com.omrbranch.pojo.categoryList.CategoryList_Output_Pojo;
import com.omrbranch.pojo.categoryList.ChildCatList;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.pojo.productlist.ChildProductList;
import com.omrbranch.pojo.productlist.ProductList_Input_Pojo;
import com.omrbranch.pojo.productlist.ProductList_Output_Pojo;
import com.omrbranch.pojo.productlist.Variation;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddProduct extends BaseClass {

	String logtoken;
	String textCategoryId;
	String textProductid;
	String variantId;

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
						System.out.println(categoryId);
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
				System.out.println(productId);
				textProductid = String.valueOf(productId);
				ArrayList<Variation> variations = childProductList.getVariations();
				for (Variation eachVariations : variations) {
					String specifications = eachVariations.getSpecifications();
					if (specifications.equals("1 kg")) {
						int id = eachVariations.getId();
						System.out.println(id);
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
		System.out.println(first_name);

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

	public static void main(String[] args) {
		AddProduct addProduct = new AddProduct();
		addProduct.login();
		addProduct.addFavouritesDetails();
		addProduct.productList();
		addProduct.addToCart();

	}

}
