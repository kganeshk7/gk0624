package com.demo.gk0624.exception.model;

import lombok.Data;

@Data
public class FieldValidationError {

	private String field;
	private String code;
	private Object rejectedValue;
	private String errorMsg;

	public FieldValidationError(String field, String code, Object rejectedValue, String errorMsg) {
		super();
		this.field = field;
		this.code = code;
		this.rejectedValue = rejectedValue;
		this.errorMsg = errorMsg;
	}
}