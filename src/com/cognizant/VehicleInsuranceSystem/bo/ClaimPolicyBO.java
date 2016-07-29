package com.cognizant.VehicleInsuranceSystem.bo;

import java.util.HashMap;
import java.util.Map;

import com.cognizant.VehicleInsuranceSystem.dao.ClaimPolicyDAO;
import com.cognizant.VehicleInsuranceSystem.dao.IClaim;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.AccidentClaimTO;
import com.cognizant.VehicleInsuranceSystem.to.ClaimPolicyTO;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;

public class ClaimPolicyBO {

	public boolean validate(String policyid,AccidentClaimTO accidentto) throws DatabaseOperationException, ApplicationException
	{
		boolean flag=true;
		IClaim claimdao=new ClaimPolicyDAO();
		flag=claimdao.validate(policyid,accidentto);
		return flag;
	}
	public boolean checkifvalid(String policyid,TheftClaimTO theftto) throws DatabaseOperationException, ApplicationException
	{
		boolean flag=true;
		IClaim claimdao=new ClaimPolicyDAO();
		flag=claimdao.checkifvalid(policyid,theftto);
		return flag;

	}
	public int update(String claimid,String policy,String claimtype) throws DatabaseOperationException, ApplicationException
	{
		IClaim claimdao=new ClaimPolicyDAO();
		int res=claimdao.insert(claimid,policy,claimtype);
		return res;
	}

	public boolean polidcheck(ClaimPolicyTO claimto) throws ValidationException, DatabaseOperationException, ApplicationException 
	{
		boolean flag=true;
		int result=0;
		String policyid=claimto.getPolicyid();
		Map<String,String> errormap=new HashMap<String,String>(); 
		IClaim claimdao=new ClaimPolicyDAO();
		result=claimdao.polidcheck(claimto);
		if(policyid.equals(""))
			errormap.put("policy","Enter Policy ID");
		if(result==1)
			errormap.put("policy","You have already claimed with this Policy ID");
		else if(result!=2)
			errormap.put("policy","Invalid Policy ID");

		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep= new ValidationException();
			excep.setErrormap(errormap);
			throw excep;
		}
		return flag;

	}

}



