package br.com.codeshare.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public BusinessException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
