package com.cognizant.VehicleInsuranceSystem.dao;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

public interface ICard {
	public boolean PayPremium(PolicyTO userto) throws DatabaseOperationException, ApplicationException;
}
