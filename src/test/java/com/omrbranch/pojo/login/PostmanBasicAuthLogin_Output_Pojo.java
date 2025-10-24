package com.omrbranch.pojo.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;  

@lombok.Data

@AllArgsConstructor
@NoArgsConstructor

public class PostmanBasicAuthLogin_Output_Pojo {
	
	 private int status;
	 private String message;
	 private Data data;
	 private String refer_msg;
	 private int cart_count;
	 private String role;

}
