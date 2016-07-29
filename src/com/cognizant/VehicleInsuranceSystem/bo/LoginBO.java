package com.cognizant.VehicleInsuranceSystem.bo;

import com.cognizant.VehicleInsuranceSystem.dao.ILogin;
import com.cognizant.VehicleInsuranceSystem.dao.LoginDAO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.LoginTO;

public class LoginBO {

	public boolean userandpw(LoginTO loginto) throws DatabaseOperationException, ApplicationException
		// TODO Auto-generated method stub
	{
		boolean flag=false;
		//LoginTO loginto=null;
		ILogin userdao=new LoginDAO();
		flag=userdao.validate(loginto);
		return flag;

	}

}
