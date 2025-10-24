package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateOrder_Output_Pojo {
	  private int status;
	    private String message;
	    private String currency;
	    private Datum data;
	    private int order_id;
}
