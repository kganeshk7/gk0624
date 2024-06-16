package com.demo.gk0624.exception.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String requestDetails;
	private Object errorDetails;
	public ErrorDetails(Date timestamp, String message, Object errorDetails, String requestDetails) {
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
		this.requestDetails = requestDetails;
	}
}