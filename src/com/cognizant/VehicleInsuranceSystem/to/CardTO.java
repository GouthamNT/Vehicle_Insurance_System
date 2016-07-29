package com.cognizant.VehicleInsuranceSystem.to;

public class CardTO {
	private String CardStr;
	private long Cardno;
	private String Cardname;
	private int month;
	private int year;
	private String CVV;
	private int cvv;
	
	public String getCardStr() {
		return CardStr;
	}
	public void setCardStr(String cardStr) {
		CardStr = cardStr;
	}
	public long getCardno() {
		return Cardno;
	}
	public void setCardno(long cardno) {
		Cardno = cardno;
	}
	public String getCardname() {
		return Cardname;
	}
	public void setCardname(String cardname) {
		Cardname = cardname;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	

}
