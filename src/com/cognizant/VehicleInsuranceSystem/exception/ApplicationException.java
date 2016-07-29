package com.cognizant.VehicleInsuranceSystem.exception;

/**
 * This class used to handle the ApplicationException.
 */
public class ApplicationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * String object containing the exception error code.
	 */
	
	public ApplicationException() {
		super();
	}
	
	
	public ApplicationException(final  Throwable cause) {
		super(cause);
	}
	
	
}
