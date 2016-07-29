package com.cognizant.VehicleInsuranceSystem.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cognizant.VehicleInsuranceSystem.dao.ITheft;
import com.cognizant.VehicleInsuranceSystem.dao.TheftClaimDAO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.TheftClaimTO;

public class TheftClaimBO {

	public boolean validate(TheftClaimTO theftto) throws ValidationException, DatabaseOperationException, ApplicationException
	// TODO Auto-generated method stub
	{
		boolean flag=true;
		Map<String,String> errormap=new HashMap<String,String>(); 
		boolean flag1=validateTheft(theftto.getDateoftheft(),theftto.getComplaint());
		if(!flag1)
			errormap.put("dtheft", "Enter valid Date of theft");
		boolean flag2=validateComplaint(theftto.getDateoftheft(),theftto.getComplaint());
		if(!flag2)
			errormap.put("dcomplaint","Enter valid Date of complaint");
		boolean flag3=validateFirno(theftto.getFirno());
		if(!flag3)
			errormap.put("firno","Enter 10 digits FIR Number");
		boolean flag4=validateBranch(theftto.getState());
		if(!flag4)
			errormap.put("psbranch","Registering Location Mismatch");
		boolean flag5=difference(theftto.getDateoftheft(),theftto.getComplaint());
		if(!flag5)
			errormap.put("diff","Difference between theft and complaint date must not exceed 10 days");

		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep= new ValidationException();
			excep.setErrormap(errormap);
			throw excep;
		}
		return flag;

	}	
	private boolean validateTheft(String dtheft,String dcomplaint) {
		boolean flag=false;
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		Date d1=null;
		Date d2=null;
		try {
			d1 = sdf.parse(dtheft);
			d2 = sdf.parse(dcomplaint);
		} catch (ParseException e) {
		}
		if((sdf.format(d1).compareTo(sdf.format(d))>=1)&&(sdf.format(d1).compareTo(sdf.format(d2))>=1))
			flag=false;
		else
		{
			flag=true;
		}

		return flag;
	}

	private boolean validateComplaint(String dtheft,String dcomplaint) {

		boolean flag=false;
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		Date d1=null;
		Date d2=null;
		try {
			d1 = sdf.parse(dcomplaint);
			d2 = sdf.parse(dtheft);
		} catch (ParseException e) {
		}
		if((sdf.format(d1).compareTo(sdf.format(d))>=1)||(sdf.format(d2).compareTo(sdf.format(d1))>=1))
			flag=false;
		else
		{
			flag=true;
		}
		return flag;
	}

	private boolean validateBranch(String psbranch) throws DatabaseOperationException, ApplicationException
	{
		boolean flag=false;
		ITheft theftdao=new TheftClaimDAO();
		if(!psbranch.equals(""))
		{
			flag=theftdao.validate(psbranch);
			return flag;
		}
		else
			return flag;
	}

	private boolean validateFirno(String firno)
	{
		int flag=0;
		if(firno.length()<=10)
		{
			for(int i=0;i<firno.length();i++)	
			{
				if(!(Character.isDigit(firno.charAt(i))))
				{
					flag=1;
					break;
				}
			}
			if(flag==1)
				return false;
			else
				return true;
		}
		else
			return false;
	}
	public boolean difference(String dtheft,String dcomplaint)
	// TODO Auto-generated method stub
	{
		// TODO Auto-generated method stub
		boolean r=false;
		long res=0;
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		Calendar c=Calendar.getInstance();
		try
		{
			sdf.setLenient(false);
			Date d1=sdf.parse(dtheft);
			Date d2=sdf.parse(dcomplaint);
			c.setTime(d1);
			long n=c.getTimeInMillis();
			c.setTime(d2);
			long n1=c.getTimeInMillis();
			res=Math.abs(n-n1);
			res=(int)Math.abs(res/(1000*3600*24));
			if(res<=10)
				r=true;
		}
		catch(ParseException e)
		{
			r=false;
		}
		return r;

	}
	public String generate()
	{
		String id="";

		Random rn=new Random();
		SimpleDateFormat sdf=new SimpleDateFormat("MMM");
		SimpleDateFormat sdf1=new SimpleDateFormat("yy");
		Date d1=new Date();
		String mon=sdf.format(d1);
		String yr=sdf1.format(d1);
		int i=rn.nextInt(999);
		int theftid=i;
		int count=0;
		while(i>0)
		{
			i=i/10;
			count++;

		}

		if(count==1)
			theftid=theftid*100;
		else if(count==2)
			theftid=theftid*20;
		id=mon.toUpperCase()+yr+theftid;
		return id;

	}

	public int update(double amount, String datetheft,
			String datecomplaint, String firnum,
			String policebranch, double theftclaimamount,
			String theftid) throws DatabaseOperationException, ApplicationException {
		ITheft theftdao=new TheftClaimDAO();
		int res=theftdao.update(amount,datetheft,datecomplaint,firnum,policebranch,theftclaimamount,theftid);
		return res;

	}
}

