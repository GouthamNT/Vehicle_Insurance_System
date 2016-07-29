package com.cognizant.VehicleInsuranceSystem.exception;

public class DatabaseOperationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseOperationException(Exception e) {
		super(e);
	}

	public DatabaseOperationException(String message) {
		super(message);
	}
	public DatabaseOperationException(final String message, Throwable cause) {
		super(message, cause);
	}
}
