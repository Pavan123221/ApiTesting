package com.omrbranch.pojo.additem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct_Input_Pojo {
	private String product_id;
	private String product_variation_id;
	private String type;
}
