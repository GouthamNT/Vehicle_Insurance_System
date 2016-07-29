package com.cognizant.VehicleInsuranceSystem.dao;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.AccidentClaimTO;
import com.cognizant.VehicleInsuranceSystem.to.ClaimPolicyTO;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;


public interface IClaim {
	public boolean validate(String policyid,AccidentClaimTO accidentto) throws DatabaseOperationException, ApplicationException;
	public boolean checkifvalid(String policyid,TheftClaimTO theftto) throws DatabaseOperationException, ApplicationException;
	public int insert(String claimid,String policy,String claimtype) throws DatabaseOperationException, ApplicationException;
	public int polidcheck(ClaimPolicyTO claimto) throws DatabaseOperationException, ApplicationException;

}
