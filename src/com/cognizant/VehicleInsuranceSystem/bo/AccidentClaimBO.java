package com.cognizant.VehicleInsuranceSystem.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.cognizant.VehicleInsuranceSystem.dao.AccidentClaimDAO;
import com.cognizant.VehicleInsuranceSystem.dao.IAccident;
import com.cognizant.VehicleInsuranceSystem.exception.ApplicationException;
import com.cognizant.VehicleInsuranceSystem.exception.DatabaseOperationException;

public class AccidentClaimBO {
	
	public static final Logger LOG = Logger.getLogger("AccidentClaimBO");
	
			public String check(String policy)
				// TODO Auto-generated method stub
			{
				String value=null;
				if(policy!="")
					value=null;
				if(policy.substring(0, 2).equalsIgnoreCase("TW"))
					value="TW";
				else if(policy.substring(0, 2).equalsIgnoreCase("FW"))
						value="FW";
				else if(policy.substring(0, 2).equalsIgnoreCase("OT"))
					value="OT";
				return value;

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
	           int accid=i;
	           int count=0;
	             while(i>0)
	               {
	        	  i=i/10;
	        	  count++;
	        	  
	          }
	          	if(count==1)
					accid=accid*250;
				else if(count==2)
					accid=accid*20;
		            id=mon.toUpperCase()+yr+accid;
					return id;
					
	        }
			public int update(String acctype,String image,double claimamount,String claimid) throws DatabaseOperationException, ApplicationException
			{
				IAccident accidentdao=new AccidentClaimDAO();
				int res=accidentdao.update(acctype,image,claimamount,claimid);
				return res;
			}
		

		}



