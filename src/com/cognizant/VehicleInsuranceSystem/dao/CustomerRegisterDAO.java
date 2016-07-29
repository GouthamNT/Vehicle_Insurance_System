package com.cognizant.VehicleInsuranceSystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.constants.QueryConstants;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.CustomerRegisterTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;

public class CustomerRegisterDAO implements ICustomerRegister{
	
	public static final Logger LOG = Logger.getLogger("CustomerRegisterDAO");

	@Override
	public boolean registerUser(CustomerRegisterTO cusregisterto) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method  registerUser in CustomerRegisterDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		boolean flag=false;
		//SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
		//java.util.Date d=null;
		//java.sql.Date d1=null;
		try {
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.INSERT_CUSTOMER);

			Long cid=GeneratePID();
			cusregisterto.setCustomer_id(cid);
			ps.setLong(1,cid);
			ps.setString(2,cusregisterto.getCusName());
			ps.setString(3,cusregisterto.getCusPassword());
			ps.setString(4,cusregisterto.getCusAddress());
			ps.setString(5,cusregisterto.getCusCity());
			ps.setString(6,cusregisterto.getCusState());
			ps.setString(7,cusregisterto.getCusCountry());
			ps.setLong(8,cusregisterto.getCusPincode_num());
			//ps.setString(8,cusregisterto.getCusPincode());
			ps.setString(9,cusregisterto.getCusEmail());
			ps.setString(10,cusregisterto.getCusGender());
			ps.setLong(11,cusregisterto.getCusContact_no1());
			//ps.setString(11,cusregisterto.getCusContact_no());
			/*try{
				System.out.println(cusregisterto.getCusDate_of_birth());
				d=sm.parse(cusregisterto.getCusDate_of_birth());	
				d1=new java.sql.Date(d.getTime());
			}
			catch(ParseException p){
				p.printStackTrace();
			}*/
			//ps.setDate(12,d1);
			ps.setString(12,cusregisterto.getCusDate_of_birth());
			ps.executeUpdate();
			ps=conn.prepareStatement(QueryConstants.INSERT_LOGIN);
			ps.setLong(1,cid);
			ps.setString(2,cusregisterto.getCusPassword());
			ps.setString(3, "Customer");
			ps.executeUpdate();
			flag=true;
			LOG.info("Exit - method  registerUser in CustomerRegisterDAO class");
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

	private long GeneratePID() {
		long cid=0;
		Calendar cal=Calendar.getInstance();
		int y=cal.get(Calendar.YEAR);
		int m=cal.get(Calendar.MONTH)+1;
		Random rn=new Random();
		int i=rn.nextInt(9999);
		NumberFormat nm=new DecimalFormat("0000");
		String seq=nm.format(i);
		NumberFormat nm1=new DecimalFormat("00");
		String mm=nm1.format(m);
		String custid=y+mm+seq;
		cid=Long.parseLong(custid);
		return cid;
	}


}
