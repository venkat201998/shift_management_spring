package com.vn.springboot.rsaserver.dto;

public class CustomResponse {
	private int status;
	private String message;
	private Object data;

	public CustomResponse(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	// Getters and setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
