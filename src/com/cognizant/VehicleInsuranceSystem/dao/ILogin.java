package com.cognizant.VehicleInsuranceSystem.dao;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.LoginTO;

public interface ILogin {
	public boolean validate(LoginTO loginto) throws DatabaseOperationException, ApplicationException;

}
