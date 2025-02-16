package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getOffer_type() {
		return offer_type;
	}

	public void setOffer_type(String offer_type) {
		this.offer_type = offer_type;
	}

	public int getOffer_value() {
		return offer_value;
	}

	public void setOffer_value(int offer_value) {
		this.offer_value = offer_value;
	}

	public List<String> getCustomer_segment() {
		return customer_segment;
	}

	public void setCustomer_segment(List<String> customer_segment) {
		this.customer_segment = customer_segment;
	}

	private int restaurant_id;
    private String offer_type;
    private int offer_value;
    private List<String> customer_segment;
}
