package com.kounrew.CRUD_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private Boolean status;
	private String message;
	private Object body;
}
