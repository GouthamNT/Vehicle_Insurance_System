package com.cognizant.VehicleInsuranceSystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.constants.QueryConstants;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.to.VehicleTO;
import com.cognizant.VehicleInsuranceSystem.util.DButil;


public class VehicleDAO implements IVehicle {
	
	public static final Logger LOG = Logger.getLogger("VehicleDAO");
	
	@Override
	public boolean validateID(long cid) throws DatabaseOperationException, ApplicationException {
		boolean flag=false;
		long cust_id=0;
		Connection conn=null;
		PreparedStatement ps=null;

		try {
			LOG.info("Inside - method  validateID in VehicleDAO class");
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.SELECT_CID);
			ps.setLong(1,cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				cust_id=rs.getLong("CUSTOMER_ID");
			}
			if(cid==cust_id)
			{
				flag=true;
			}
			LOG.info("Exit - method  validateID in VehicleDAO class");
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


	@Override
	public List<String> modeldrop(String manufact,String vehtype) throws ApplicationException, DatabaseOperationException {
		List<String> mdllist=new ArrayList<String>();
		LOG.info("Inside - method  modeldrop in VehicleDAO class");
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
		
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.POPULATE_MODEL);
			ps.setString(1,vehtype);
			ps.setString(2,manufact);
			rs=ps.executeQuery();
			while(rs.next())
			{
				mdllist.add(rs.getString(1));

			}
			LOG.info("Exit - method  modeldrop in VehicleDAO class");


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
		return mdllist;
	}



	@Override
	public List<String> manudrop(String vtype) throws ApplicationException, DatabaseOperationException 
	{
		LOG.info("Inside - method  manudrop in VehicleDAO class");
		List<String> manulist=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.POPULATE_MANUFACTURER);
			ps.setString(1,vtype);

			rs=ps.executeQuery();
			while(rs.next())
			{
				manulist.add(rs.getString(1));

			}
			LOG.info("Exit - method  manudrop in VehicleDAO class");


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
		return manulist;
	}	

	@Override
	public boolean registerVehicle(VehicleTO vehicleto) throws ApplicationException, DatabaseOperationException {
		
		LOG.info("Inside - method  registerVehicle in VehicleDAO class");
		boolean flag=false;
		int result=0;
		Connection conn=null;
		PreparedStatement ps=null;
		java.util.Date d=null;
		java.sql.Date d1=null;
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			conn=DButil.getConnection();
			ps=conn.prepareStatement(QueryConstants.INSERT_VEHICLE);
			ps.setString(1,vehicleto.getName());
			ps.setString(2,vehicleto.getRegistering_state());
			ps.setString(3,vehicleto.getVehicle_type());
			ps.setString(4,vehicleto.getVehicle_class());
			ps.setString(5,vehicleto.getManufacturer());
			ps.setString(6,vehicleto.getModel());
			ps.setString(7,vehicleto.getEngine_number());
			ps.setInt(8,vehicleto.getYear_of_make());
			ps.setString(9,vehicleto.getRegistering_location());
			ps.setDouble(10,vehicleto.getPrice());
			try{
				d=sm.parse(vehicleto.getDop());	
				d1=new java.sql.Date(d.getTime());  
			}
			catch(ParseException p){
				p.printStackTrace();
			}
			ps.setDate(11,d1);
			double pm= PremiumAmount(vehicleto.getVehicle_class(),vehicleto.getPrice());
			double dis=Discount(vehicleto.getDop(),pm);
			double iamt=InsuranceAmount(vehicleto.getYear_of_make(),vehicleto.getPrice());
			Calendar sd=Calendar.getInstance();
			java.sql.Date dt=MaturityDate(sd);
			String pid=GeneratePID(vehicleto.getVehicle_type());
			ps.setDouble(12,pm);
			vehicleto.setPremium_amount(pm);
			ps.setDouble(13,dis);
			ps.setDouble(14,iamt);
			ps.setDate(15,dt);
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String mt = df.format(dt);
			vehicleto.setMaturity_date(mt);
			vehicleto.setPolicy_id(pid);
			ps.setString(16,pid);
			ps.setLong(17,vehicleto.getCid());
			ps.setString(18,vehicleto.getRegistering_address());
			ps.setString(19,vehicleto.getRcnumber());
			result=ps.executeUpdate();
			if(result>0)
				flag=true;
			LOG.info("Exit - method  registerVehicle in VehicleDAO class");
		}catch( ClassNotFoundException e){ 
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

	public double PremiumAmount(String cls,double p)
	{ 
		double pamt=0;
		double pt=0;
		if(cls.equalsIgnoreCase("OWN"))
		{
			pamt=p*0.065;
		}
		else if(cls.equalsIgnoreCase("Commercial"))
		{
			double tax=0.034*p;
			pamt=tax+(p*0.065);
		}
		NumberFormat nm=new DecimalFormat("#0.00");
		String frm=nm.format(pamt);
		pt=Double.parseDouble(frm);
		return pt;
	}



	public double Discount(String  d,double pat)
	{ 
		double discount=0;
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		try{
			Date da=sd.parse(d);  
			Date d1=new Date();

			cal.setTime(d1);
			int y=cal.get(Calendar.YEAR);
			int m=cal.get(Calendar.MONTH);
			cal.setTime(da);
			int y1=cal.get(Calendar.YEAR);
			int m1=cal.get(Calendar.MONTH);
			NumberFormat nm=new DecimalFormat("#0.00");
			if(y==y1)
			{

				if(Math.abs(m-m1)<=1)
				{
					discount=pat*0.03;
					String dd=nm.format(discount);
					discount=Double.parseDouble(dd);

				}
				else
				{
					discount=0;

				}	
			}
			else
			{
				discount=0;
			}
		}
		catch(ParseException p)
		{
			p.printStackTrace();
		}

		return discount;
	}




	public double InsuranceAmount(int y,double p)
	{
		double iat=0;
		NumberFormat nm=new DecimalFormat("#0.00");
		Calendar c=Calendar.getInstance();
		int y1=c.get(Calendar.YEAR);
		if(y1>=5)
		{ 
			iat=p/2;
			String dd=nm.format(iat);
			iat=Double.parseDouble(dd);

		}
		else
		{
			double amt=p*0.02;
			iat=amt+p;
			String dd=nm.format(iat);
			iat=Double.parseDouble(dd);
		}
		return iat;
	}


	public java.sql.Date MaturityDate(Calendar sd){

		sd.add(Calendar.YEAR,1);
		Date d=sd.getTime();
		java.sql.Date d1=new java.sql.Date(d.getTime());

		return d1;
	}





	public String GeneratePID(String pid)
	{
		String policyid=null;
		Random rn=new Random();
		SimpleDateFormat sdf1=new SimpleDateFormat("yy");
		Date d1=new Date();
		String yr=sdf1.format(d1);
		int i=rn.nextInt(9999);
		NumberFormat nm=new DecimalFormat("0000");
		String seq=nm.format(i);
		if(pid.equalsIgnoreCase("TW"))
		{
			policyid="TW"+yr+seq; 
		}
		else if(pid.equalsIgnoreCase("FW"))
		{
			policyid="FW"+yr+seq; 
		}
		else if(pid.equalsIgnoreCase("OT")){
			policyid="OT"+yr+seq;
		}
		return policyid;

	}

}
