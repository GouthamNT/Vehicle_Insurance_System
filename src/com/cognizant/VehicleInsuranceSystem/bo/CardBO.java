package com.cognizant.VehicleInsuranceSystem.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cognizant.VehicleInsuranceSystem.dao.PolicyDAO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.CardTO;
import com.cognizant.VehicleInsuranceSystem.to.PolicyTO;

public class CardBO {
	public boolean CardValid(CardTO cardto) throws ValidationException{
		boolean flag=true;
		Map<String,String> errormap=new HashMap<String,String>(); 
		boolean flag1=ValidateCardno(cardto.getCardStr());
		boolean flag2=ValidateCardname(cardto.getCardname());
		boolean flag3=ValidateCVV(cardto.getCVV());
		boolean flag4=ValidateExp(cardto.getMonth(), cardto.getYear());
		if(!flag1)
			errormap.put("cardno", "Card Number is Mandatory and should be numeric with 10 digits");
		if(!flag2)
			errormap.put("cardname","Card Name is Mandatory and should have alphabets only");
		if(!flag3)
			errormap.put("cvv","CVV Number is Mandatory and should be numeric with 3 digits");
		if(!flag4)
			errormap.put("exp","Check Card Exp date");
		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep= new ValidationException();
			excep.setErrormap_pay_card(errormap);
			throw excep;
		}
		return flag;
	}
	private boolean ValidateCardno(String cardno)
	{
		boolean flag=false;
		int lenstr=cardno.length();
		if(lenstr==16)
		{
			for(int i=0;i<lenstr;i++)
			{
				char ch=cardno.charAt(i);
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
	private boolean ValidateCardname(String cardname){
		boolean flag=false;
		int lenname=cardname.length();
		if(lenname>0)
		{
			for(int i=0;i<lenname;i++)
			{
				char ch=cardname.charAt(i);
				if(Character.isAlphabetic(ch)||Character.isWhitespace(ch))
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
	private boolean ValidateCVV(String cvv)
	{
		boolean flag=false;
		int lencvv=cvv.length();
		if(lencvv==3)
		{
			for(int i=0;i<lencvv;i++)
			{
				char ch=cvv.charAt(i);
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
	private boolean ValidateExp(int month,int year)
	{
		boolean flag=true;
		Date d=new Date();
		Calendar ca=Calendar.getInstance();
		ca.setTime(d);
		int month_cal=ca.get(Calendar.MONTH);
		int year_cal=ca.get(Calendar.YEAR);
		if(year==year_cal)
		{
			if(month<month_cal)
				flag=false;
		}
		return flag;
	}


	public boolean Store(PolicyTO userto) throws DatabaseOperationException, ApplicationException
	{
		PolicyDAO userdao=new PolicyDAO();
		boolean flag=userdao.Store(userto);
		System.out.println(flag);
		return flag;
	}
	public boolean checkBank(String bank) {
		boolean flag=true;
		try{
			if(bank.length()==0)
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
