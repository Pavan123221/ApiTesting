package com.omrbranch.pojo.additem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct_Output_Pojo {
	  private int status;
	    private String message;
	    private String currency;
	    private int cart_count;
	    private AddData data;
}
