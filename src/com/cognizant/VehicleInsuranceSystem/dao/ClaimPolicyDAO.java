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
import com.cognizant.VehicleInsuranceSystem.to.AccidentClaimTO;
import com.cognizant.VehicleInsuranceSystem.to.ClaimPolicyTO;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;


public class ClaimPolicyDAO implements IClaim{
	
	public static final Logger LOG = Logger.getLogger("ClaimPolicyDAO");
	
	public boolean checkifvalid(String policyid,TheftClaimTO theftto) throws ApplicationException, DatabaseOperationException{
		
		LOG.info("Inside - method checkifvalid in ClaimPolicyDAO class");
		Connection conn=null;
		ResultSet result;
		PreparedStatement ps=null;
		boolean flag=false;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.CLAIM);
			ps.setString(1,(policyid));
			result=ps.executeQuery();
			if(result.next())
			{ 
				flag=true;
				theftto.setUsername(result.getString(2));
				theftto.setManufacturer(result.getString(3));
				theftto.setModel(result.getString(4));
				theftto.setState(result.getString(5));
				theftto.setEngineno(result.getString(6));
				theftto.setAmount(result.getString(7));
			}
			LOG.info("Exit - method checkifvalid in ClaimPolicyDAO class");
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
	public boolean validate(String policyid,AccidentClaimTO accidentto) throws ApplicationException, DatabaseOperationException{
		// TODO Auto-generated method stub
		LOG.info("Inside - method validate in ClaimPolicyDAO class");
		Connection conn=null;
		ResultSet result;
		PreparedStatement ps=null;
		boolean flag=false;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.CLAIM);
			ps.setString(1,(policyid));
			result=ps.executeQuery();
			if(result.next())
			{ 
				flag=true;
				accidentto.setUsername(result.getString(2));
				accidentto.setAmount(result.getString(7));
			}
			LOG.info("Exit - method validate in ClaimPolicyDAO class");
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
	public int insert(String claimid,String policy,String claimtype) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method insert in ClaimPolicyDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		int result=0;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.CLAIMDETAILS);
			ps.setString(1,claimid);
			ps.setString(2,policy);
			ps.setString(3,claimtype);
			result=ps.executeUpdate();
			LOG.info("Exit - method insert in ClaimPolicyDAO class");
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
	@Override
	public int polidcheck(ClaimPolicyTO claimto) throws DatabaseOperationException, ApplicationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method  polidcheck in ClaimPolicyDAO class");
		Connection conn=null;
		ResultSet result;
		int policy_check=0;
		String policyid=claimto.getPolicyid();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		try
		{
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.POLICYCHECK);
			ps1=conn.prepareStatement(QueryConstants.POLICYINVALID);
			ps.setString(1,policyid);
			ps1.setString(1,policyid);
			ps1.setLong(2, claimto.getCustomerID());
			result=ps.executeQuery();
			if(result.next())
				policy_check=1;	
			else
			{
				result=ps1.executeQuery();
				if(result.next())
					policy_check=2;
				return policy_check;
			}
			LOG.info("Exit - method  polidcheck in ClaimPolicyDAO class");
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
		

		return policy_check;
	}

}


