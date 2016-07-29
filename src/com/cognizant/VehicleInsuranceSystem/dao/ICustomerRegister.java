package com.cognizant.VehicleInsuranceSystem.dao;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.CustomerRegisterTO;


public interface ICustomerRegister {
	
	public boolean registerUser(CustomerRegisterTO cusregisterto) throws DatabaseOperationException, ApplicationException;

}
