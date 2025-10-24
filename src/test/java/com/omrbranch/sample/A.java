package com.omrbranch.sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omrbranch.pojo1.Address;
import com.omrbranch.pojo1.Datum;
import com.omrbranch.pojo1.Employee;
import com.omrbranch.pojo1.Support;

public class A {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		File file = new File("C:\\Users\\DELL\\eclipse-workspace\\ApiTesting\\src\\test\\resources\\Hello.json");

		ObjectMapper object = new ObjectMapper();
		
		ArrayList<Datum> data = new ArrayList<>();
		Datum d1 = new Datum(548, "AirIndia", "India","87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d2 = new Datum(573, "AirIndia", "India", "54", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d3 = new Datum(576, "AirIndia", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d4 = new Datum(630, "AirIndia", "India", "67", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d5 = new Datum(636, "AirIndia", "India", "97", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d6 = new Datum(655, "AirIndia", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		data.add(d1);
		data.add(d2);
		data.add(d3);
		data.add(d4);
		data.add(d5);
		data.add(d6);
		
		Support support = new Support("https:\\/\\/omrbranch.com",
				"For Joining Automation Course, Please Contact-Velmurugan 9944152058");
		

		Employee employee = new Employee(1, 6, 16961, 2930, data, support);
		object.writeValue(file, employee);
		System.out.println("update done in github");

	}

}
