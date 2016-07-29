package com.cognizant.VehicleInsuranceSystem.dao;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;

public interface IAccident {

	int update(String acctype,String image,double claimamount,String claimid) throws DatabaseOperationException, ApplicationException;

}
