package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyOfferResponse {
    private int cart_value;

	public int getCart_value() {
		return cart_value;
	}

	public void setCart_value(int cart_value) {
		this.cart_value = cart_value;
	}
}
