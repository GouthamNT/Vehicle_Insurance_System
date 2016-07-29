package com.cognizant.VehicleInsuranceSystem.bo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.cognizant.VehicleInsuranceSystem.dao.CustomerRegisterDAO;
import com.cognizant.VehicleInsuranceSystem.dao.ICustomerRegister;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.CustomerRegisterTO;


public class CustomerRegisterBO extends CustomerRegisterTO{


	public boolean registerUser(CustomerRegisterTO cusregisterto) throws DatabaseOperationException, ApplicationException
	{
		boolean flag=false;
		ICustomerRegister cusregisterdao=new CustomerRegisterDAO();
		flag=cusregisterdao.registerUser(cusregisterto);
		return flag;
	}
	public boolean validate(CustomerRegisterTO createaccountto) throws ValidationException
	{
		Map<String,String> errormap=new HashMap<String,String>();

		boolean flag=true;

		boolean flag1=validateUsername(createaccountto.getCusName());
		boolean flag2=validatePwd(createaccountto.getCusPassword());
		boolean flag3=validatePwd2(createaccountto.getCusPassword(),createaccountto.getCusRe_password());
		boolean flag4=validateEmailid(createaccountto.getCusEmail());
		boolean flag5=validatePin(createaccountto.getCusPincode());
		boolean flag6=validateContact(createaccountto.getCusContact_no());
		boolean flag7=validateAgeAbove(createaccountto.getCusDate_of_birth());
		boolean flag7a=validateAgeBelow(createaccountto.getCusDate_of_birth());
		boolean flag7b=validateAge(createaccountto.getCusDate_of_birth());
		boolean flag8=validateAddress(createaccountto.getCusAddress());
		boolean flag9=validateCity(createaccountto.getCusCity());
		boolean flag10=validateState(createaccountto.getCusState());
		boolean flag11=validateCountry(createaccountto.getCusCountry());
		boolean flag12=validateGender(createaccountto.getCusGender());

		if(!flag1)
			errormap.put("username", "The name should contain only alphabets and space.");
		if(!flag2)
			errormap.put("pwd","Password should contain at least one lowercase, one uppercase and one digit");
		if(!flag3)
			errormap.put("pwd2","Password Mismatch");
		if(!flag4)
			errormap.put("email","Enter Valid email id(Ex-abc@gmail.com)");
		if(!flag5)
			errormap.put("pin", "Pin code should be 6 digits");
		if(!flag6)
			errormap.put("Contact", "Contact no should be 10 digits");
		if(!flag7b)
			errormap.put("age", "Enter valid Age");
		else{
			if(!flag7)
				errormap.put("age", "Age Shouldn't be greater than \"90\" years");
			if(!flag7a)
				errormap.put("age", "Age Shouldn't be less than \"18\" years");
		}
		if(!flag8)
			errormap.put("address", "Address should not contain special characters other than white space. Blank not allowed");
		if(!flag9)
			errormap.put("city", "Enter valid city name");
		if(!flag11)
			errormap.put("country", "Enter valid country name");
		if(!flag10)
			errormap.put("state", "Enter valid state name");
		if(!flag12)
			errormap.put("gender", "Please select a gender");


		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep=new ValidationException();
			excep.setErrormap(errormap);
			throw  excep;
		}
		return flag;

	}




	private boolean validateGender(String cusGender) {
		boolean flag=true;
		try{
			if(cusGender.length()==0)
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
	private boolean validateCountry(String cusCountry) {
		boolean flag=false;
		if(cusCountry.length()==0)
		{
			flag=false;
		}
		for(int i=0;i<cusCountry.length();i++)
		{
			char a=cusCountry.charAt(i);
			if(Character.isAlphabetic(a))
			{
				flag=true;
			}
			else
			{
				flag=false;
				break;
			}
		}

		return flag;
	}
	private boolean validateCity(String cusCity) {
		boolean flag=false;
		if(cusCity.length()==0)
		{
			flag=false;
		}
		for(int i=0;i<cusCity.length();i++)
		{
			char a=cusCity.charAt(i);
			if(Character.isAlphabetic(a))
			{
				flag=true;
			}
			else
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	private boolean validateAddress(String cusAddress) {
		boolean flag=false;
		int len=cusAddress.length();
		if(cusAddress.length()==0)
		{
			flag=false;
		}
		else
		{
			for(int i=0;i<len;i++)
			{

				if(Character.isAlphabetic(cusAddress.charAt(i))||Character.isDigit(cusAddress.charAt(i))||Character.isWhitespace(cusAddress.charAt(i)))
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
		return flag;
	}

	private boolean validateState(String state) {
		boolean flag=false;
		String[] states={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu & Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","TamilNadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","WestBengal"};
		if(state.length()==0)
		{
			flag=false;
		}
		else
		{
			for(int i=0;i<states.length;i++)
			{
				if(state.equalsIgnoreCase(states[i]))
				{
					flag=true;
					break;
				}
			}
		}
		return flag;
	}


	private boolean validateAgeAbove(String cusDate_of_birth) {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		Date d2=new Date();
		long res=0;
		try
		{
			sdf.setLenient(false);
			Date d1=sdf.parse(cusDate_of_birth);
			c.setTime(d1);
			long n=c.getTimeInMillis();
			c.setTime(d2);
			long n1=c.getTimeInMillis();
			res=Math.abs(n-n1);
			res=(int)Math.abs(res/(1000*3600*24));
			res=(int)res/365;
			if(res<=90)
				flag=true;
			else
				flag=false;
		}
		catch(ParseException e)
		{
		}
		return flag;

	}

	private boolean validateAgeBelow(String cusDate_of_birth) {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		Date d2=new Date();
		long res=0;
		try
		{
			sdf.setLenient(false);
			Date d1=sdf.parse(cusDate_of_birth);
			c.setTime(d1);
			long n=c.getTimeInMillis();
			c.setTime(d2);
			long n1=c.getTimeInMillis();
			res=Math.abs(n-n1);
			res=(int)Math.abs(res/(1000*3600*24));
			res=(int)res/365;
			if(res>=18)
				flag=true;
			else
				flag=false;
		}
		catch(ParseException e)
		{
		}
		return flag;
	}
	private boolean validateAge(String cusDate_of_birth) {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		Date d2=new Date();
		long res=0;
		try
		{
			sdf.setLenient(false);
			Date d1=sdf.parse(cusDate_of_birth);
			c.setTime(d1);
			long n=c.getTimeInMillis();
			c.setTime(d2);
			long n1=c.getTimeInMillis();
			res=(n1-n);
			System.out.println(res);
			if(res>=0)
				flag=true;
			else
				flag=false;
		}
		catch(ParseException e)
		{
		}
		return flag;
	}
	private boolean validatePin(String cusPincode) {
		boolean flag=true;
		int len=cusPincode.length();
		if(len==6)
		{
			for(int i=0;i<len;i++)
			{
				if(!Character.isDigit(cusPincode.charAt(i)))
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
	private boolean validateContact(String cusContact_no) {
		boolean flag=true;
		int len=cusContact_no.length();
		if(len==10)
		{
			for(int i=0;i<len;i++)
			{
				if(Character.isAlphabetic(cusContact_no.charAt(i)))
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
	private boolean validateUsername(String name)
	{
		boolean flag=false;
		int len=name.length();
		for(int i=0;i<len;i++)
		{

			if(Character.isAlphabetic(name.charAt(i))||Character.isWhitespace(name.charAt(i)))
			{
				flag=true;
			}
			else
			{
				flag=false;
				break;
			}

		}
		return flag;
	}

	private boolean validatePwd(String pwd)
	{
		boolean flag=false;
		int len=pwd.length();
		int count_upper=0,count_lower=0,count_digit=0;
		for(int i=0;i<len;i++)
		{
			if(Character.isDigit(pwd.charAt(i)))
			{
				count_digit++;
			}
			else if(Character.isUpperCase(pwd.charAt(i)))
			{
				count_upper++;
			}
			else if(Character.isLowerCase(pwd.charAt(i)))
			{
				count_lower++;
			}
		}
		if(count_digit>=1 && count_lower>=1 && count_upper>=1)
			flag=true;
		else
			flag=false;
		return flag;         
	}


	private boolean validatePwd2(String pwd,String pwd2)
	{ 
		boolean flag=false;
		if(pwd.equals(pwd2))
		{
			flag=true;
		}
		return flag;
	}

	private boolean validateEmailid(String email)
	{              int count=0;
	String a=null;

	boolean flag=false;
	List<String> l=new ArrayList<String>();
	StringTokenizer st=new StringTokenizer(email,"@");

	while(st.hasMoreTokens())
	{
		a=st.nextToken();

		count=count+1;
	}
	if(count==2)
	{
		count=0;
		StringTokenizer st2=new StringTokenizer(a,".");
		while(st2.hasMoreTokens())
		{ l.add(st2.nextToken());
		count=count+1;
		}
		if(count==2)
		{                                              
			String c=l.get(0);
			String d=l.get(1);
			if(c.equals("gmail")||c.equals("yahoo")||c.equals("rediff")||c.equals("hotmail"))
			{
				if(d.equals("com"))
					flag=true;
			}
		}
	}
	else 
		flag=false;

	return flag;

	}

}
