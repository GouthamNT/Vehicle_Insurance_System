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
import com.cognizant.VehicleInsuranceSystem.to.LoginTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;


public class LoginDAO implements ILogin{
	
	public static final Logger LOG = Logger.getLogger("LoginDAO");

	@Override
	public boolean validate(LoginTO loginto) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method  validate in LoginDAO class");
		Connection conn=null;		
		ResultSet result;
		PreparedStatement ps=null;
		boolean flag=false;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.CHECK_LOGIN);
			ps.setString(1,loginto.getUsername());
			ps.setString(2,loginto.getPassword());
			result=ps.executeQuery();
			if(result.next())
			{ 
				flag=true;
				String user=result.getString(1);
				System.out.println(user);
				loginto.setUsertype(user);
			}
			LOG.info("Exit - method  validate in LoginDAO class");
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

}
