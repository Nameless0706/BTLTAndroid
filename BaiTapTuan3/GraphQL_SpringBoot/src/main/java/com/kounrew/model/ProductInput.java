package com.kounrew.model;

import lombok.Data;

@Data
public class ProductInput {
	private String title;
	private String description;
	private String image;
	private int amount;
}