package com.omrbranch.pojo.productlist;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductList_Output_Pojo {
	public int status;
    public String message;
    public String currency;
    public ArrayList<ChildProductList> data;
    public String banner;
    public int cart_count;
}
