package com.cognizant.VehicleInsuranceSystem.constants;

public class QueryConstants {
	public static final String SELECT_POLICY="select policy_id,MATURITY_DATE from vehicle where policy_id=? and customer_id=?";
	public static final String SELECT_POLICY_DIRECT="select policy_id,MATURITY_DATE from vehicle where policy_id=?";
	public static final String INSERT_DETAILS="insert into PAYMENT_DETAILS(PAYMENT_ID,POLICY_ID,MODE_OF_PAYMENT,AMOUNT,AMOUNT_TAX_OR_DUE,DATE_OF_PAYMENT) values(?,?,?,?,?,?)";
	public static final String GET_VEHICLEDETAILS="select REGISTRATION_STATE,vehicle_type,model,PREMIUM_AMOUNT from vehicle where policy_id=?";
	public static final String GET_PAYDETAILS="select AMOUNT from PAYMENT_DETAILS where policy_id=? order by DATE_OF_PAYMENT desc";
	public static final String GET_DUE="select MATURITY_DATE from vehicle where policy_id=?";
	public static final String UPDATE_DUE="update vehicle set MATURITY_DATE=? where policy_id=?";
	
	public static final String INSERT_CUSTOMER = "insert into CUSTOMER(CUSTOMER_ID,NAME,PASSWORD,ADDRESS,CITY,STATE,COUNTRY,PINCODE,EMAIL_ADDRESS,GENDER,CONTACT_NO,DOB) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_LOGIN="insert into LOGIN values(?,?,?)";
	public static final String CHECK_LOGIN="select USER_TYPE from LOGIN where user_id=? and password=?";
	
	public static final String CLAIM="select distinct v.POLICY_ID,v.CUSTOMER_NAME,v.MANUFACTURER,v.MODEL,v.REGISTRATION_STATE,v.ENGINE_NUMBER,Amount_total from VEHICLE v join PAYMENT_DETAILS p on v.POLICY_ID=p.POLICY_ID join (select POLICY_ID,sum(AMOUNT) as Amount_total from PAYMENT_DETAILS  group by POLICY_ID) temp on p.POLICY_ID=temp.policy_id where v.POLICY_ID=?";
	public static final String CLAIMDETAILS="insert into claim_details(claim_id,policy_id,claim_type) values (?,?,?)";
	public static final String POLICYCHECK="select policy_id from claim_details where policy_id=?";
	public static final String POLICYINVALID="select policy_id from VEHICLE where policy_id=? and customer_id=?";
	public static final String ACCIDENTCLAIMDETAILS="insert into accident_claim(accident_type,accident_proof,claim_amount,claim_id) values (?,?,?,?)";
	public static final String LOC="select REGISTRATION_STATE from VEHICLE where REGISTRATION_STATE=?";
	public static final String THEFTCLAIMDETAILS="insert into theft_details(total_amount,date_of_theft,date_of_complaint,fir_number,police_station_branch,claim_amount,claim_id) values (?,?,?,?,?,?,?)";


	
	public static final String SELECT_CID="select CUSTOMER_ID from CUSTOMER where CUSTOMER_ID=?";
	public static final String INSERT_VEHICLE="insert into VEHICLE values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
	public static final String POPULATE_MANUFACTURER="select distinct MANUFACTURER from VEHICLE_DROP where VEHICLE_TYPE=?";
	public static final String POPULATE_MODEL="select MODEL from VEHICLE_DROP where VEHICLE_TYPE=? and  MANUFACTURER=?";

}
