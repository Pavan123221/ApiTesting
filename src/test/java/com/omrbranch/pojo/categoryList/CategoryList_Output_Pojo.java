package com.omrbranch.pojo.categoryList;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CategoryList_Output_Pojo {
	private int status;
    private String message;
    private ArrayList<CategoryList> data;
}
