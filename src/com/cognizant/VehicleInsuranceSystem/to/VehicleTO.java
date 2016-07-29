package com.cognizant.VehicleInsuranceSystem.to;

public class VehicleTO {

	private long cid;
	private String name;
	private String registering_state;
	private String vehicle_type;
	private String vehicle_class;
	private String manufacturer;
	private String model;
	private String engine_number;
	private int year_of_make;
	private String registering_location;
	private double price;
	private String dop;
	private String policy_id;
	private double premium_amount;
	private String Maturity_date;
	private Long CustomerID;
	private String registering_address;
	private String rcnumber;
	
	
	public String getRegistering_address() {
		return registering_address;
	}
	public void setRegistering_address(String registering_address) {
		this.registering_address = registering_address;
	}
	public String getRcnumber() {
		return rcnumber;
	}
	public void setRcnumber(String rcnumber) {
		this.rcnumber = rcnumber;
	}
	public Long getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
	public String getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(String policy_id) {
		this.policy_id = policy_id;
	}
	public Double getPremium_amount() {
		return premium_amount;
	}
	public void setPremium_amount(double premium_amount) {
		this.premium_amount = premium_amount;
	}
	public String getMaturity_date() {
		return Maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		Maturity_date = maturity_date;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid =cid;
    	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistering_state() {
		return registering_state;
	}
	public void setRegistering_state(String registering_state) {
		this.registering_state = registering_state;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getVehicle_class() {
		return vehicle_class;
	}
	public void setVehicle_class(String vehicle_class) {
		this.vehicle_class = vehicle_class;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEngine_number() {
		return engine_number;
	}
	public void setEngine_number(String engine_number) {
		this.engine_number = engine_number;
	}
	public int getYear_of_make() {
		return year_of_make;
	}
	public void setYear_of_make(int year_of_make) {
		this.year_of_make = year_of_make;
	}
	public String getRegistering_location() {
		return registering_location;
	}
	public void setRegistering_location(String registering_location) {
		this.registering_location = registering_location;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDop() {
		return dop;
	}
	public void setDop(String dop) {
		this.dop = dop;
	}
}