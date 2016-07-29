package com.cognizant.VehicleInsuranceSystem.to;

public class ClaimPolicyTO {
	private String policyid;
	private String accident;
	private String theft;
	private Long CustomerID;
	
	
	public Long getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
	public String getPolicyid() {
		return policyid;
	}
	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}
	public String getAccident() {
		return accident;
	}
	public void setAccident(String accident) {
		this.accident = accident;
	}
	public String getTheft() {
		return theft;
	}
	public void setTheft(String theft) {
		this.theft = theft;
	}
	

}
