package com.cognizant.VehicleInsuranceSystem.to;

public class AccidentClaimTO {
	private String username;
	private String amount;
	private String accidenttype;
	private String image;
	private int res;
	private int accres;
	private double accamt;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private String weightage;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public int getAccres() {
		return accres;
	}
	public void setAccres(int accres) {
		this.accres = accres;
	}
	public String getAccidenttype() {
		return accidenttype;
	}
	public void setAccidenttype(String accidenttype) {
		this.accidenttype = accidenttype;
	}
	public String getWeightage() {
		return weightage;
	}
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
	public void setAccamt(double accamt) {
		this.accamt = accamt;
	}
	public double getAccamt() {
		return accamt;
	}
	

}
