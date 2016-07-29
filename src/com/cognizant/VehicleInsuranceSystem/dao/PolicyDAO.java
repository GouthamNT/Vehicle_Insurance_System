package com.cognizant.VehicleInsuranceSystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.constants.QueryConstants;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;

public class PolicyDAO implements IUser{
	
	public static final Logger LOG = Logger.getLogger("PolicyDAO");

	@Override
	public boolean PayPremium(PolicyTO userto) throws DatabaseOperationException, ApplicationException {

		LOG.info("Inside - method  PayPremium in PolicyDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			conn=DButil.getConnection();
			String paytype=userto.getPaytype();
			if(paytype.equalsIgnoreCase("direct"))
			{
				ps=conn.prepareStatement(QueryConstants.SELECT_POLICY_DIRECT);//queryconstants
				ps.setString(1, userto.getPolicyID());
			}
			else
			{
				ps=conn.prepareStatement(QueryConstants.SELECT_POLICY);//queryconstants
				ps.setString(1, userto.getPolicyID());
				ps.setString(2, userto.getUserID());
			}
			ResultSet rs=null;
			rs=ps.executeQuery();
			if(rs.next())
			{
				if(userto.getPolicyID().equalsIgnoreCase(rs.getString("policy_id")))
				{

					java.sql.Date duedate=rs.getDate(2);
					userto.setSqldue(duedate);
					userto.setDue(duedate.toString());
					flag=true;
					ps=conn.prepareStatement(QueryConstants.GET_VEHICLEDETAILS);
					ps.setString(1, userto.getPolicyID());
					rs=ps.executeQuery();
					if(rs.next())
					{
						userto.setState(rs.getString(1));
						userto.setVehicle_type(rs.getString(2));
						userto.setModel(rs.getString(3));
						userto.setAmountactual(rs.getDouble(4));
						Double amount=DueAmount(rs.getDouble(4),userto.getDue());
						ps=conn.prepareStatement(QueryConstants.GET_PAYDETAILS);
						ps.setString(1, userto.getPolicyID());
						rs=ps.executeQuery();
						if(rs.next())
						{
							Double paidamount=rs.getDouble(1);
							if(paidamount>amount)
							{
								int n=(int) ((paidamount/amount)+1);
								amount=(amount*n)-paidamount;
								DecimalFormat fmt=new DecimalFormat("0.00");
								String round=fmt.format(amount);
								amount=Double.parseDouble(round);
							}
						}
						userto.setAmount(amount.toString());
					}
				}
			}
			LOG.info("Exit - method  PayPremium in PolicyDAO class");
		} catch( ClassNotFoundException e){ 
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

	public boolean Store(PolicyTO userto) throws DatabaseOperationException, ApplicationException{
		
		LOG.info("Inside - method  Store in PolicyDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		String payment=null;
		ResultSet rs=null;
		java.util.Date due=null;
		try {
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.INSERT_DETAILS);//queryconstants
			java.util.Date d=new java.util.Date();
			SimpleDateFormat smt=new SimpleDateFormat("MMM");
			java.sql.Date payday=new java.sql.Date(d.getTime());
			String pid=smt.format(d).toUpperCase();
			payment=GeneratePID(pid);
			userto.setPaymentID(payment);
			ps.setString(1, payment);
			ps.setString(2, userto.getPolicyID());
			ps.setString(3, userto.getModeofpayment());
			Double amount=Double.parseDouble(userto.getAmount());
			if(userto.getPaytype().equalsIgnoreCase("direct"))
			{
				userto.setAmountactual(amount);
			}
			ps.setDouble(4, userto.getAmountactual());
			if(userto.getModeofpayment().equalsIgnoreCase("credit_card"))
			{
				amount=amount+(amount*.023);
			}
			ps.setDouble(5, amount);
			ps.setDate(6, payday);
			ps.executeUpdate();
			ps=conn.prepareStatement(QueryConstants.GET_DUE);
			ps.setString(1, userto.getPolicyID());
			rs=ps.executeQuery();
			if(rs.next())
			{
				int n=1;
				due=rs.getDate(1);
				ps=conn.prepareStatement(QueryConstants.GET_VEHICLEDETAILS);
				ps.setString(1, userto.getPolicyID());
				rs=ps.executeQuery();
				if(rs.next())
				{
					amount=rs.getDouble(4);
					Double paidamount=userto.getAmountactual();
					if(paidamount>amount)
					{

						n=(int) ((paidamount/amount));
					}
				}
				SimpleDateFormat smt1=new SimpleDateFormat("dd-MM-yyyy");
				Calendar ca=Calendar.getInstance();
				ca.setTime(due);
				ca.add(Calendar.YEAR,n);
				due=ca.getTime();
				java.sql.Date duesql=new java.sql.Date(due.getTime());
				String dueformatted=smt1.format(due);
				userto.setDue(dueformatted);
				ps=conn.prepareStatement(QueryConstants.UPDATE_DUE);
				ps.setDate(1, duesql);
				ps.setString(2, userto.getPolicyID());
				ps.executeUpdate();
			}
			LOG.info("Exit - method  Store in PolicyDAO class");
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
		return true;
	}
	public String GeneratePID(String pid)
	{
		String policyid=null;
		Random rn=new Random();
		int i=rn.nextInt(9999);
		NumberFormat nm=new DecimalFormat("0000");
		String num=nm.format(i);
		policyid=pid+num;
		return policyid;

	}
	public double DueAmount(Double amount,String Due)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date dueDate=sdf.parse(Due);
			java.util.Date d=new java.util.Date();
			boolean flag=false;
			Calendar ca=Calendar.getInstance();
			ca.setTime(dueDate);
			int dueDay=ca.get(Calendar.DAY_OF_MONTH);
			int dueMonth=ca.get(Calendar.MONTH);
			int dueYear=ca.get(Calendar.YEAR);
			ca.setTime(d);
			int currentDay=ca.get(Calendar.DAY_OF_MONTH);
			int currentMonth=ca.get(Calendar.MONTH);
			int currentYear=ca.get(Calendar.YEAR);
			if(dueYear>currentYear)
			{
				flag=true;
			}
			else if(dueYear==currentYear)
			{
				if(dueMonth>currentMonth)
				{
					flag=true;
				}
				else if(dueMonth==currentMonth)
				{
					if(dueDay>=currentDay)
					{
						flag=true;
					}
					else
						flag=false;
				}
				else
					flag=false;
			}
			else
				flag=false;
			if(!flag)
			{
				amount=(double)(((currentDay-dueDay)*(0.0056*amount))+amount);
				NumberFormat fmt=new DecimalFormat("0.00");
				String amt=fmt.format(amount);
				amount=Double.parseDouble(amt);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}

}
