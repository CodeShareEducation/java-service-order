package br.com.codeshare.enums;

public enum ErrorCode {

	PHONE_HAS_SERVICE_ORDER("100");
	
	String errorCode;
	
	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
