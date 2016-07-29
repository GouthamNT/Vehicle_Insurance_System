package com.cognizant.VehicleInsuranceSystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.constants.QueryConstants;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.util.DButil;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;

public class AccidentClaimDAO implements IAccident {
	
	public static final Logger LOG = Logger.getLogger("AccidentClaimDao");
	@Override
	public int update(String acctype,String image,double claimamount, String claimid) throws DatabaseOperationException, ApplicationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method update in AccidentClaimDao class");
		Connection conn=null;
		PreparedStatement ps=null;
		int result=0;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.ACCIDENTCLAIMDETAILS);
			ps.setString(1,acctype);
			ps.setString(2,image);
			ps.setDouble(3,claimamount);
			ps.setString(4,claimid);
			result=ps.executeUpdate();
			LOG.info("Exit - method update in AccidentClaimDao class");
		}
		catch( ClassNotFoundException e){ 
			throw new ApplicationException(e);
			}
		catch(IOException ex){
			throw new ApplicationException(ex);
			}
		catch(SQLException  e){
			throw new DatabaseOperationException(e);
			}
		finally
		{
			try{
				conn.close();
			}
			catch(SQLException e){
				throw new DatabaseOperationException(e);
			}
		}
		return result;
	}

}
