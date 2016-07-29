package com.cognizant.VehicleInsuranceSystem.dao;

import java.util.List;

import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.VehicleTO;


public interface IVehicle {
	public boolean validateID(long customer_id) throws DatabaseOperationException, ApplicationException;
	public boolean registerVehicle(VehicleTO vehicleto) throws DatabaseOperationException, ApplicationException;
	public List<String> modeldrop(String manufact,String vehtype) throws DatabaseOperationException, ApplicationException;
	public List<String> manudrop(String vtype) throws DatabaseOperationException, ApplicationException;
	
}
