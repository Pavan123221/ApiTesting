package com.omrbranch.pojo1;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private ArrayList<Datum> data;
	private Support support;

	
}
