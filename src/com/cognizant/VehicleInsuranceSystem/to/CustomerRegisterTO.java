package com.cognizant.VehicleInsuranceSystem.to;



public class CustomerRegisterTO {
	
	private String CusName;
	private String CusPassword;
	private String CusRe_password;
	private String CusAddress;
	private String CusCity;
	private String CusState;
	private String CusCountry;
	private String CusPincode;
	private Long CusPincode_num;
	private String CusEmail;
	private String CusGender;
	private String CusContact_no;
	private Long CusContact_no1;
	private String CusDate_of_birth;
	private Long customer_id;
	
	public String getCusDate_of_birth() {
		return CusDate_of_birth;
	}
	public void setCusDate_of_birth(String cusDate_of_birth) {
		this.CusDate_of_birth = cusDate_of_birth;
	}
	public String getCusName() {
		return CusName;
	}
	public void setCusName(String cusName) {
		this.CusName = cusName;
	}
	public String getCusPassword() {
		return CusPassword;
	}
	public void setCusPassword(String cusPassword) {
		this.CusPassword = cusPassword;
	}
	public String getCusRe_password() {
		return CusRe_password;
	}
	public void setCusRe_password(String cusRe_password) {
		this.CusRe_password = cusRe_password;
	}
	public String getCusAddress() {
		return CusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.CusAddress = cusAddress;
	}
	public String getCusCity() {
		return CusCity;
	}
	public void setCusCity(String cusCity) {
		this.CusCity = cusCity;
	}
	public String getCusState() {
		return CusState;
	}
	public void setCusState(String cusState) {
		this.CusState = cusState;
	}
	public String getCusCountry() {
		return CusCountry;
	}
	public void setCusCountry(String cusCountry) {
		this.CusCountry = cusCountry;
	}
	public String getCusPincode() {
		return CusPincode;
	}
	public void setCusPincode(String cusPincode) {
		this.CusPincode = cusPincode;
	}
	public long getCusPincode_num() {
		return CusPincode_num;
	}
	public void setCusPincode_num(long cusPincode_num) {
		this.CusPincode_num = cusPincode_num;
	}
	public String getCusEmail() {
		return CusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.CusEmail = cusEmail;
	}
	public String getCusGender() {
		return CusGender;
	}
	public void setCusGender(String cusGender) {
		this.CusGender = cusGender;
	}
	public String getCusContact_no() {
		return CusContact_no;
	}
	public void setCusContact_no(String cusContact_no) {
		this.CusContact_no = cusContact_no;
	}
	public long getCusContact_no1() {
		return CusContact_no1;
	}
	public void setCusContact_no1(long cusContact_no1) {
		this.CusContact_no1 = cusContact_no1;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	
}

