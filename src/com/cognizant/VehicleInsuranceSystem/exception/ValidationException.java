package com.cognizant.VehicleInsuranceSystem.exception;

import java.util.Map;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String,String> errormap;
	private Map<String,String> errormap_pay;
	private Map<String,String> errormap_pay_card;
	private Map<String,String> errormap_claim;

	
	public Map<String, String> getErrormap_claim() {
		return errormap_claim;
	}

	public void setErrormap_claim(Map<String, String> errormap_claim) {
		this.errormap_claim = errormap_claim;
	}

	public Map<String, String> getErrormap_pay() {
		return errormap_pay;
	}

	public void setErrormap_pay(Map<String, String> errormap_pay) {
		this.errormap_pay = errormap_pay;
	}

	public Map<String, String> getErrormap_pay_card() {
		return errormap_pay_card;
	}

	public void setErrormap_pay_card(Map<String, String> errormap_pay_card) {
		this.errormap_pay_card = errormap_pay_card;
	}

	public Map<String, String> getErrormap() {
		return errormap;
	}

	public void setErrormap(Map<String, String> errormap) {
		this.errormap = errormap;
	}
	
	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException() {

	}

}

