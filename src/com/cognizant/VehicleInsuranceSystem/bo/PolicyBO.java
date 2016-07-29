package com.cognizant.VehicleInsuranceSystem.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.cognizant.VehicleInsuranceSystem.dao.IUser;
import com.cognizant.VehicleInsuranceSystem.dao.PolicyDAO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

public class PolicyBO {
	public boolean PayPremium(PolicyTO userto) throws DatabaseOperationException, ApplicationException
	{
		boolean flag;
		IUser userdao=new PolicyDAO();
		flag=userdao.PayPremium(userto);
		return flag;
	}
	public boolean DueCheck(PolicyTO userto)
	{
		//System.out.println("duecheck");
		boolean flag=true;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date due=userto.getSqldue();
		try {
			sdf.setLenient(false);
			Calendar ca=Calendar.getInstance();
			int currentDay=ca.get(Calendar.DAY_OF_MONTH);
			int currentMonth=ca.get(Calendar.MONTH);
			int currentYear=ca.get(Calendar.YEAR);
			ca.setTime(due);
			int dueDay=ca.get(Calendar.DAY_OF_MONTH);
			int dueMonth=ca.get(Calendar.MONTH);
			int dueYear=ca.get(Calendar.YEAR);			
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public boolean SecondPay(PolicyTO userto)
	{
		boolean flag=true;
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date due=userto.getSqldue();
		long res=0;
		try {
			//java.util.Date dueDate=sdf.parse(due);
			//System.out.println(dueDate);
			Calendar ca=Calendar.getInstance();
			long n=ca.getTimeInMillis();
			ca.setTime(due);
			long n1=ca.getTimeInMillis();
			res=Math.abs(n-n1);
			res=(int)Math.abs(res/(1000*3600*24));
			if(res<=365)
				flag=true;
			else
				flag=false;
		}
		catch(Exception e)
		{
			System.out.println("catch"+res);
		}
		return flag;
	}
	public boolean amountcheck(String amount,String amountgiven)
	{
		boolean flag=false;
		double user_amount=Double.parseDouble(amount);
		double premium_amount=Double.parseDouble(amountgiven);
		if(user_amount>=premium_amount)
			flag=true;
		return flag;
	}

	public boolean Field_valid(PolicyTO userto) throws ValidationException
	{
		boolean flag=true;
		Map<String,String> errormap=new HashMap<String,String>(); 
		boolean flag1=ValidateAmount(userto.getAmount());
		if(!flag1)
			errormap.put("Amount", "Amount is Mandatory and should be numeric");
		boolean flag2=ValidateContact(userto.getContactno());
		if(!flag2)
			errormap.put("contact","Contact No is Mandatory and should be numeric");
		boolean flag3=ValidateMode(userto.getModeofpayment());
		if(!flag3)
			errormap.put("mode","Please choose a mode of Payment");
		
		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep= new ValidationException();
			excep.setErrormap_pay(errormap);
			throw excep;
		}
		return flag;
	}
	private boolean ValidateAmount(String amount)
	{
		boolean flag=false;
		int lenamt=amount.length();
		if(lenamt>0)
		{
			for(int i=0;i<lenamt;i++)
			{
				char ch=amount.charAt(i);
				if(Character.isDigit(ch)||ch=='.')
				{
					flag=true;
				}
				else
				{
					flag=false;
					break;
				}
			}
		}
		else
			flag=false;
		return flag;
	}
	private boolean ValidateContact(String contact)
	{
		boolean flag=false;
		int lenctc=contact.length();
		if(lenctc==10)
		{
			for(int i=0;i<10;i++)
			{
				char ch=contact.charAt(i);
				if(Character.isDigit(ch))
				{
					flag=true;
				}
				else
				{
					flag=false;
					break;
				}
			}
		}
		else
			flag=false;
		return flag;
	}
	private boolean ValidateMode(String Mode) {
		boolean flag=true;
		try{
			if(Mode.length()==0)
			{
				flag=false;
			}
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	}
	
}
