package com.demo.gk0624.util;

public enum ApplicationConstants {
	VALIDATION_FAILED("Validation failed");
	private final String text;
	private ApplicationConstants(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}


}