package com.omrbranch.pojo.cartitems;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetCartItem_Output_Pojo {
	private int status;
	private String message;
	private String currency;
	private ArrayList<Datum> data;
	private int cart_count;
	private String payment;
	private String wallet;
	private String total_amount;
	private String coupon_discount;
	private String shipping_fee;
	private String grand_total;
	private String savings;
	private int credit_usage;
	private String credits_used;
	private String payment_key;
	private Address address;
}
