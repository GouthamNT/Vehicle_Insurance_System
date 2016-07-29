package com.cognizant.VehicleInsuranceSystem.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cognizant.VehicleInsuranceSystem.dao.IVehicle;
import com.cognizant.VehicleInsuranceSystem.dao.VehicleDAO;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;
import com.cognizant.VehicleInsuranceSystem.exception.ValidationException;
import com.cognizant.VehicleInsuranceSystem.to.VehicleTO;




public class VehicleBO {

	public boolean validate(VehicleTO vehicleto) throws ValidationException, DatabaseOperationException, ApplicationException
	{

		boolean flag=true;
		Map<String,String> errormap=new HashMap<String,String>(); 
		boolean flag1=validateName(vehicleto.getName());
		if(!flag1)
			errormap.put("uname", "Name is Mandatory and should have alphabets");
		boolean flag2=validateregstate(vehicleto.getRegistering_state());
		if(!flag2)
			errormap.put("registering_state","Registration State should belongs to India");
		boolean flag3=validateEngnumber(vehicleto.getEngine_number());
		if(!flag3)
			errormap.put("engine_number","Enter valid engine number");
		boolean flag4=validateYearofMake(vehicleto.getYear_of_make());
		if(!flag4)
			errormap.put("year","Year should be numeric");
		boolean flag5=validateReglocation(vehicleto.getRegistering_location(),vehicleto.getRegistering_state());
		if(!flag5)
			errormap.put("registering_location","Location should belong to India and same as registering state");
		boolean flag6=validatePrice(vehicleto.getPrice());
		if(!flag6)
			errormap.put("price", "Price should be numeric");
		boolean flag7=validateDop(vehicleto.getDop());
		if(!flag7)
			errormap.put("dop","DOP must be less than current date");
		boolean flag8=validateVehicleClass(vehicleto.getVehicle_class());
		if(!flag8)
			errormap.put("cls","Select a vehicle class");
		boolean flag9=validateVehicleType(vehicleto.getVehicle_type());
		if(!flag9)
			errormap.put("vehicletype","Select a Vehicle Type");
		boolean flag10=validateVehicleManufacturer(vehicleto.getManufacturer());
		if(!flag10)
			errormap.put("vehiclemanufact","Select a Vehicle Manufacturer");
		boolean flag11=validateVehicleAddress(vehicleto.getRegistering_address());
		if(!flag11)
			errormap.put("registering_address","Address should not contain special characters other than white space. Blank not allowed");
		boolean flag12=validateVehicleRC(vehicleto.getRcnumber());
		if(!flag12)
			errormap.put("rcnumber","RC number is mandatory and should be numberic with 7 digits");
		boolean flag13=validateID(vehicleto.getCid());
		if(!flag13)
			errormap.put("cid","Enter a valid CustomerID");


		if(errormap.size()>0)
		{
			flag=false;
			ValidationException excep= new ValidationException();
			excep.setErrormap(errormap);
			throw excep;
		}
		return flag;

	}

	private boolean validateVehicleRC(String manufacturer) {
		boolean flag=true;
		int len=manufacturer.length();
		if(len==7)
		{
			for(int i=0;i<len;i++)
			{
				if(!Character.isDigit(manufacturer.charAt(i)))
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

	private boolean validateVehicleAddress(String registering_address) {
		boolean flag=false;
		int len=registering_address.length();
		if(registering_address.length()==0)
		{
			flag=false;
		}
		else
		{
			for(int i=0;i<len;i++)
			{

				if(Character.isAlphabetic(registering_address.charAt(i))||Character.isDigit(registering_address.charAt(i))||Character.isWhitespace(registering_address.charAt(i)))
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

	private boolean validateVehicleManufacturer(String manufacturer) {
		boolean flag=false;
		try
		{
		if(manufacturer.equalsIgnoreCase("Default"))
		{
			flag=false;
		}
		else{
			flag=true;
		}
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	}

	private boolean validateVehicleType(String vehicle_type) {
		boolean flag=false;
		if(vehicle_type.equalsIgnoreCase("Default"))
		{
			flag=false;
		}else
		{
			flag=true;
		}

		return flag;
	}

	private boolean validateVehicleClass(String vehicle_class)
	{
		boolean flag=false;
		if(vehicle_class.equalsIgnoreCase("Default"))
		{
			flag=false;
		}
		else
		{
			flag=true;	
		}
		return flag;
	}

	private boolean validateYearofMake(int year_of_make) {
		boolean flag=false;
		String yr=Integer.toString(year_of_make);
		Calendar df=Calendar.getInstance();
		int yr1=df.get(Calendar.YEAR);

		if(yr.length()==4)
		{
			for(int i=0;i<yr.length();i++)
			{
				char c=yr.charAt(i);
				if(Character.isDigit(c))
				{
					flag=true;	
				}
			}
			if(year_of_make<=yr1)
			{
				flag=true;	
			}
		}
		return flag;
	}

	private boolean validateDop(String dop) {
		boolean flag=false;
		if(dop.length()==0)
		{
			flag=false;
		}
		else
		{
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			try{
				System.out.println("validatedate"+dop);
				Date d=sd.parse(dop);
				System.out.println(d);
				Calendar calen=Calendar.getInstance();
				long n=calen.getTimeInMillis();
				calen.setTime(d);
				long n1=calen.getTimeInMillis();
				long res=(n-n1);
				System.out.println(res);
				if(res>=0)
					flag=true;
				else
					flag=false;
			}
			catch(ParseException p)
			{
				p.printStackTrace();
			}
		}
		return flag;
	}

	private boolean validatePrice(double price) {
		boolean flag=true;
		int dotcount=0; 
		String p=Double.toString(price);
		if(price<=0)
		{
			flag=false;
		}
		else
		{
			for(int i=0;i<p.length();i++)
			{   char ch=p.charAt(i);
			if(ch=='.')
				dotcount++;
			}
			if(dotcount==1)
			{
				for(int i=0;i<p.length();i++)
				{  
					char c=p.charAt(i);
					if(!(Character.isDigit(c)||c=='.'))
					{
						flag=false;
						break;
					}

				}
			}
		}
		return flag;
	}

	private boolean validateReglocation(String registering_location,String registering_state) {
		boolean flag=false;
		String[] states={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu & Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","TamilNadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","WestBengal"};
		if(registering_location.length()==0)
		{
			flag=false;
		}
		else{
			for(int i=0;i<states.length;i++)
			{
				if(registering_location.equalsIgnoreCase(states[i]))
				{
					if(registering_location.equalsIgnoreCase(registering_state))
					{
						flag=true;  
						break;
					}
				}
			}
		}
		return flag;
	}

	private boolean validateEngnumber(String engine_number) {
		boolean flag=true;
		if(engine_number.length()==0)
		{
			flag=false;
		}
		else{
			for(int i=0;i<engine_number.length();i++)
			{
				char a=engine_number.charAt(i);
				if(!(Character.isAlphabetic(a)||Character.isDigit(a)))
				{
					flag=false;
					break;
				}
			}
		}
		return flag;
	}

	private boolean validateregstate(String registering_state) {
		boolean flag=false;
		String[] states={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu & Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","TamilNadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","WestBengal"};
		if(registering_state.length()==0)
		{
			flag=false;
		}
		else
		{
			for(int i=0;i<states.length;i++)
			{
				if(registering_state.equalsIgnoreCase(states[i]))
				{
					flag=true;
					break;
				}
			}
		}
		return flag;
	}

	private boolean validateName(String name) {
		boolean flag=true;
		if(name.length()==0)
		{
			flag=false;
		}
		for(int i=0;i<name.length();i++)
		{
			char n=name.charAt(i);
			if(Character.isAlphabetic(n)||Character.isWhitespace(n))
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



	private boolean validateID(long cid) throws DatabaseOperationException, ApplicationException
	{
		boolean flag=false;
		IVehicle vehicledao=new VehicleDAO();
		flag=vehicledao.validateID(cid);
		System.out.println("id "+flag);
		return flag;
	}
	public boolean registerVehicle(VehicleTO vehicleto) throws  DatabaseOperationException, ApplicationException

	{
		boolean flag=false;
		IVehicle vdao=new VehicleDAO();
		flag=vdao.registerVehicle(vehicleto);	
		return flag;
	}

	public List<String> manufacturerdrop(String vtype) throws DatabaseOperationException, ApplicationException 
	{
		List<String> manulist=new ArrayList<String>();
		IVehicle vdaom=new VehicleDAO();
		manulist=vdaom.manudrop(vtype);	
		return manulist;
	} 

	public List<String> modeldrop(String manufact,String vtype) throws DatabaseOperationException, ApplicationException 
	{
		// TODO Auto-generated method stub
		List<String> mdllist=new ArrayList<String>();
		IVehicle vdao1=new VehicleDAO();
		mdllist=vdao1.modeldrop(manufact,vtype);	
		return mdllist;
	}

}