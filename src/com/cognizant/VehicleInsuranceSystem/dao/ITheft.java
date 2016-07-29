package com.cognizant.VehicleInsuranceSystem.dao;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;

public interface ITheft {

	int update(double amount, String datetheft, String datecomplaint,
			String firnum, String policebranch, double theftclaimamount,
			String theftid) throws DatabaseOperationException, ApplicationException;

	boolean validate(String psbranch) throws DatabaseOperationException, ApplicationException;

}
