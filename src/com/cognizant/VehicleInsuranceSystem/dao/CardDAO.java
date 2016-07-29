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
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;

public class CardDAO implements ICard {

	public static final Logger LOG = Logger.getLogger("CardDao");
	@Override
	public boolean PayPremium(PolicyTO userto) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method PayPremium in CardDao class");
		Connection conn=null;
		boolean flag=false;
		PreparedStatement ps=null;
		try {
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.SELECT_POLICY);//queryconstants
			ps.setString(1, userto.getPolicyID());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				if(userto.getPolicyID().equalsIgnoreCase(rs.getString("policy_id")))
					flag=true;
				else
					flag=false;
			}
			LOG.info("Exit - method PayPremium in CardDao class");
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
