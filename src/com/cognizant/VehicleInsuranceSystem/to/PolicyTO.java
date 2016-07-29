package com.cognizant.VehicleInsuranceSystem.to;



public class PolicyTO {
	private String userID;
	private String policyID;
	private String amount;
	private double amt;
	private double amountactual;
	private String modeofpayment;
	private String contactno;
	private long contact;
	private String paymentID;
	private String State;
	private String Vehicle_type;
	private String Model;
	private String due;
	private String paytype;
	private java.sql.Date sqldue;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public double getAmountactual() {
		return amountactual;
	}
	public void setAmountactual(double amountactual) {
		this.amountactual = amountactual;
	}
	public java.sql.Date getSqldue() {
		return sqldue;
	}
	public void setSqldue(java.sql.Date sqldue) {
		this.sqldue = sqldue;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public String getPolicyID() {
		return policyID;
	}
	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getModeofpayment() {
		return modeofpayment;
	}
	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getVehicle_type() {
		return Vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		Vehicle_type = vehicle_type;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	
}
