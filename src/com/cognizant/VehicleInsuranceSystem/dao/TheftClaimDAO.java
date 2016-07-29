package com.cognizant.VehicleInsuranceSystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.constants.QueryConstants;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.util.DButil;


public class TheftClaimDAO implements ITheft{
	
	public static final Logger LOG = Logger.getLogger("TheftClaimDAO");
	
	public boolean validate(String psbranch) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method  validate in TheftClaimDAO class");
		Connection conn=null;	
		ResultSet result;
		PreparedStatement ps=null;
		boolean flag=false;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.LOC);
			ps.setString(1,(psbranch));
			result=ps.executeQuery();
			if(result.next())
				flag=true;
			LOG.info("Exit - method  validate in TheftClaimDAO class");
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
		return flag;
	}
	public int update(double amount, String datetheft, String datecomplaint,
			String firnum, String policebranch, double theftclaimamount,
			String theftid) throws DatabaseOperationException, ApplicationException {
		LOG.info("Inside - method  update in TheftClaimDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		int result=0;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.THEFTCLAIMDETAILS);
			ps.setDouble(1,amount);
			ps.setString(2,datetheft);
			ps.setString(3,datecomplaint);
			ps.setString(4,firnum);
			ps.setString(5,policebranch);
			ps.setDouble(6,theftclaimamount);
			ps.setString(7,theftid);
			result=ps.executeUpdate();
			LOG.info("Exit - method  update in TheftClaimDAO class");
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
